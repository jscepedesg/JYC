package interfazBodega;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Controlador.Controlador;
import Mundo.Bodega;
import Mundo.Cliente;
import interfazCliente.PanelTabla_c;


public class PanelTabla_b extends JPanel {
	
	Controlador ctrl;
	private JLabel Lista;
	private static JTable tabla;
	private static DefaultTableModel dtm;
	private static String cabecera[]=new String[4];
	private static String datos[][] = new String[500][4];
	private static int tamaño;
	
	public PanelTabla_b()
	{
		
	}
	 PanelTabla_b(Controlador ctrl) 
	 {
		 setBackground(new Color(202,255,79));
		 setLayout( null );
		 //Integar controlador
		 this.ctrl=ctrl;
			
		 Lista = new JLabel("Bodega");
		 Font auxFont=Lista.getFont();
		 Lista.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 25));
		 Lista.setForeground(Color.BLUE.darker().darker().darker().darker());
		 Lista.setBounds(320, 5, 300, 30);
		 add(Lista);
			
		 cabecera[0]="Codigo";
		 cabecera[1]="Nombre";
		 cabecera[2]="Linea";
		 cabecera[3]="Cantidad";
			
		 dtm= new DefaultTableModel(datos,cabecera);
		 tabla = new JTable(dtm);
		 Font auxFont90=tabla.getFont();
		 tabla.setFont(new Font(auxFont90.getFontName(), auxFont90.getStyle(), 15));
		 tabla.setPreferredScrollableViewportSize(new Dimension(450, 200));
		 tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			  
		 TableColumnModel columnModel = tabla.getColumnModel();
		 
		 columnModel.getColumn(0).setPreferredWidth(90);
		 columnModel.getColumn(1).setPreferredWidth(350);
		 columnModel.getColumn(2).setPreferredWidth(160);
		 columnModel.getColumn(3).setPreferredWidth(117);
		 tabla.setRowHeight(20);
		 JScrollPane scrollPane = new JScrollPane(tabla);
		 scrollPane.setBounds(20, 45, 735, 360);
		 add(scrollPane);
		 
		 setPintarTabla();
		 HiloPintarTabla hilo = new HiloPintarTabla();
		 hilo.start();
	 }
	 
	 public void setpararHilo()
	 {
		 HiloPintarTabla hilo = new HiloPintarTabla();
		 hilo.setTerminarHilo();
	 }
	 public void setPintarTabla()
		{
		 	String codigo="",nombre="",linea="",can="";
		 	int con=0;
		 	try
		 	{
			 	//1. Crear conexion 
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
				//2.Crear objecto Statement
				Statement mistatement = conexion.createStatement();
				//Ejecutar Sql
				ResultSet miresultset = mistatement.executeQuery("SELECT b.Id_Pro2, p.nom_Pro, p.Linea_pro, b.cantidad FROM producto p INNER JOIN bodega b ON(p.Id_Pro = b.Id_Pro2)");
				ArrayList<Bodega> verifi = new ArrayList<Bodega>();
				//Recorrer el resulset
				while(miresultset.next())
				{
					verifi.add(new Bodega(miresultset.getInt("Id_Pro2"),miresultset.getString("nom_Pro"),miresultset.getString("Linea_pro"),miresultset.getInt("cantidad")));
					
				}
				tamaño=verifi.size();
				for (Bodega producto : verifi) 
				{
					
					codigo=String.valueOf(producto.getId_p());
					nombre=producto.getNombre_p();
					linea=producto.getLinea_p();
					can=String.valueOf(producto.getCan());
					setPintarTabla(codigo,nombre,linea,can,con);
					con++;
				}
				verifi.clear();
				conexion.close();
		 	}
		 	catch(Exception e)
		 	{
			 	JOptionPane.showMessageDialog(this,"Hubo un erro con la base de datos","Alerta",0);
			 	System.out.println(e.getMessage());
			 	
		 	}
			
		}
	 public void setPintarTabla(String codigo,String nombre,String linea,String cantidad,int con)
	 {
			 dtm.setValueAt(codigo,con,0);
			 dtm.setValueAt(nombre,con,1);
			 dtm.setValueAt(linea,con,2);
			 dtm.setValueAt(cantidad,con,3);
	 }
	 public static void setElimino()
	 {
		for (int i = 0; i < tabla.getRowCount(); i++) 
		 {
			dtm.setValueAt(null,i,0);
			dtm.setValueAt(null,i,1);
			dtm.setValueAt(null,i,2);
			dtm.setValueAt(null,i,3);
		 }
	 }
	 
}
class HiloPintarTabla extends Thread
{
	private static boolean  senHilo;
	private PanelTabla_b pnlTabla_b;
	public HiloPintarTabla()
	{
		senHilo=true;
		pnlTabla_b=new PanelTabla_b();
	}
	public void run()
	{
		while(senHilo)
		{	
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pnlTabla_b.setPintarTabla();
		}
		
	}
	public void setTerminarHilo()
	{
		senHilo=false;
	}

}
