package interfazProducto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Controlador.Controlador;
import Mundo.Producto;

public class PanelTabla_p extends JPanel{

	Controlador ctrl;
	private JLabel Lista;
	private static JTable tabla;
	private static DefaultTableModel dtm;
	private static String cabecera[]=new String[3];
	private static String datos[][] = new String[500][3];
	private static int tamaño;
	public PanelTabla_p()
	{
		
	}
	 PanelTabla_p(Controlador ctrl) 
	 {
		 setBackground(new Color(202,255,79));
		 setLayout( null );
		 //Integar controlador
		 this.ctrl=ctrl;
			
		 Lista = new JLabel("Productos");
		 Font auxFont=Lista.getFont();
		 Lista.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 25));
		 Lista.setForeground(Color.BLUE.darker().darker().darker().darker());
		 Lista.setBounds(320, 5, 300, 25);
		 add(Lista);
			
		 cabecera[0]="Codigo";
		 cabecera[1]="Nombre";
		 cabecera[2]="Precio";
			
		 dtm= new DefaultTableModel(datos,cabecera);
		 tabla = new JTable(dtm);
		 Font auxFont90=tabla.getFont();
		 tabla.setFont(new Font(auxFont90.getFontName(), auxFont90.getStyle(), 15));
		 tabla.setPreferredScrollableViewportSize(new Dimension(450, 200));
		 tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			  
		 TableColumnModel columnModel = tabla.getColumnModel();
		 
		 columnModel.getColumn(0).setPreferredWidth(90);
		 columnModel.getColumn(1).setPreferredWidth(350);
		 columnModel.getColumn(2).setPreferredWidth(120);
		 tabla.setRowHeight(20);
		 JScrollPane scrollPane = new JScrollPane(tabla);
		 scrollPane.setBounds(100, 35, 578, 140);
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
		 	String id="",nom="",pre="";
		 	int con=0;
		 	try
		 	{
			 	//1. Crear conexion 
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
				//2.Crear objecto Statement
				Statement mistatement = conexion.createStatement();
				//Ejecutar Sql
				ResultSet miresultset = mistatement.executeQuery("select * from producto");
				ArrayList<Producto> verifi = new ArrayList<Producto>();
				//Recorrer el resulset
				while(miresultset.next())
				{
					verifi.add(new Producto(miresultset.getInt("Id_Pro"),miresultset.getString("nom_Pro"),miresultset.getInt("Valor_sin_iva"),miresultset.getString("Linea_pro"),miresultset.getString("Casa_de_Export")));
					
				}
				tamaño=verifi.size();
				for (Producto producto : verifi) 
				{
					
					id=String.valueOf(producto.getCod_p());
					nom=producto.getNom_p();
					pre=String.valueOf(producto.getPre_p());
					setPintarTabla(id,nom,pre,con);
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
	 public void setPintarTabla(String id,String nom,String pre,int con)
	 {
			 dtm.setValueAt(id,con,0);
			 dtm.setValueAt(nom,con,1);
			 dtm.setValueAt(pre,con,2);
	 }
	 public static void setElimino()
	 {
		for (int i = 0; i < tabla.getRowCount(); i++) 
		 {
			dtm.setValueAt(null,i,0);
			dtm.setValueAt(null,i,1);
			dtm.setValueAt(null,i,2); 
		 }
	 }
	 
}
class HiloPintarTabla extends Thread
{
	private static boolean  senHilo;
	private PanelTabla_p pnlTabla_p;
	public HiloPintarTabla()
	{
		senHilo=true;
		pnlTabla_p=new PanelTabla_p();
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
			pnlTabla_p.setPintarTabla();
		}
		
	}
	public void setTerminarHilo()
	{
		senHilo=false;
	}
	
	
	
}