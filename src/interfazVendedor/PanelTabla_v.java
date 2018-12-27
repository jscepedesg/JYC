package interfazVendedor;

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
import Mundo.Vendedor;

public class PanelTabla_v extends JPanel{

	Controlador ctrl;
	private JLabel Lista;
	private static JTable tabla;
	private static DefaultTableModel dtm;
	private static String cabecera[]=new String[3];
	private static String datos[][] = new String[500][3];
	private static int tamaño;
	public PanelTabla_v()
	{
		
	}
	 PanelTabla_v(Controlador ctrl) 
	 {
		 setBackground(new Color(202,255,79));
		 setLayout( null );
		 //Integar controlador
		 this.ctrl=ctrl;
			
		 Lista = new JLabel("Vendedor");
		 Font auxFont=Lista.getFont();
		 Lista.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 25));
		 Lista.setForeground(Color.BLUE.darker().darker().darker().darker());
		 Lista.setBounds(320, 5, 300, 25);
		 add(Lista);
			
		 cabecera[0]="Id vendedor";
		 cabecera[1]="Nombre";
		 cabecera[2]="Ruta";
			
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
		 	String id="",nom="",ruta="";
		 	int con=0;
		 	try
		 	{
			 	//1. Crear conexion 
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
				//2.Crear objecto Statement
				Statement mistatement = conexion.createStatement();
				//Ejecutar Sql
				ResultSet miresultset = mistatement.executeQuery("select * from vendedor");
				ArrayList<Vendedor> verifi = new ArrayList<Vendedor>();
				//Recorrer el resulset
				while(miresultset.next())
				{
					verifi.add(new Vendedor(miresultset.getInt("Id_Ven"),miresultset.getString("nom_Ven"),miresultset.getString("apell_Ven"),miresultset.getString("Ruta_Ven"),miresultset.getString("telefono_Ven"),miresultset.getString("correo_e")));	
				}
				tamaño=verifi.size();
				for (Vendedor producto : verifi) 
				{
					
					id=String.valueOf(producto.getId());
					nom=producto.getNom()+" "+producto.getApel();
					ruta=producto.getRuta();
					setPintarTabla(id,nom,ruta,con);
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
	 public void setPintarTabla(String id,String nom,String ruta,int con)
	 {
			 dtm.setValueAt(id,con,0);
			 dtm.setValueAt(nom,con,1);
			 dtm.setValueAt(ruta,con,2);
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
	private PanelTabla_v pnlTabla_v;
	public HiloPintarTabla()
	{
		senHilo=true;
		pnlTabla_v=new PanelTabla_v();
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
			pnlTabla_v.setPintarTabla();
		}
		
	}
	public void setTerminarHilo()
	{
		senHilo=false;
	}
	
	
	
}