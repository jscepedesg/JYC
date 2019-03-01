package interfazCliente;

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
import Mundo.Cliente;
import Mundo.Producto;

public class PanelTabla_c extends JPanel{

	Controlador ctrl;
	private JLabel Lista;
	private static JTable tabla;
	private static DefaultTableModel dtm;
	private static String cabecera[]=new String[4];
	private static String datos[][] = new String[500][4];
	private static int tamaño;
	public PanelTabla_c()
	{
		
	}
	 PanelTabla_c(Controlador ctrl) 
	 {
		 setBackground(new Color(202,255,79));
		 setLayout( null );
		 //Integar controlador
		 this.ctrl=ctrl;
			
		 Lista = new JLabel("Clientes");
		 Font auxFont=Lista.getFont();
		 Lista.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 25));
		 Lista.setForeground(Color.BLUE.darker().darker().darker().darker());
		 Lista.setBounds(320, 5, 300, 25);
		 add(Lista);
			
		 cabecera[0]="Id cliente";
		 cabecera[1]="Nombre razon social";
		 cabecera[2]="Nombre cliente";
		 cabecera[3]="Dia de atención";
			
		 dtm= new DefaultTableModel(datos,cabecera);
		 tabla = new JTable(dtm);
		 Font auxFont90=tabla.getFont();
		 tabla.setFont(new Font(auxFont90.getFontName(), auxFont90.getStyle(), 15));
		 tabla.setPreferredScrollableViewportSize(new Dimension(450, 200));
		 tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			  
		 TableColumnModel columnModel = tabla.getColumnModel();
		 
		 columnModel.getColumn(0).setPreferredWidth(90);
		 columnModel.getColumn(1).setPreferredWidth(250);
		 columnModel.getColumn(2).setPreferredWidth(260);
		 columnModel.getColumn(3).setPreferredWidth(117);
		 tabla.setRowHeight(20);
		 JScrollPane scrollPane = new JScrollPane(tabla);
		 scrollPane.setBounds(20, 35, 735, 140);
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
		 	String id="",nom_r="",nom_c="",dia_a="";
		 	int con=0;
		 	try
		 	{
			 	//1. Crear conexion 
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
				//2.Crear objecto Statement
				Statement mistatement = conexion.createStatement();
				//Ejecutar Sql
				ResultSet miresultset = mistatement.executeQuery("select * from cliente");
				ArrayList<Cliente> verifi = new ArrayList<Cliente>();
				//Recorrer el resulset
				while(miresultset.next())
				{
					verifi.add(new Cliente(miresultset.getInt("Id_Cli"),miresultset.getString("nom_razon_social"),miresultset.getString("nom_Cli"),miresultset.getString("apell_CLie"),miresultset.getString("direccion"),miresultset.getString("telefono"),miresultset.getString("correo_e"),miresultset.getString("dia_atencion"),miresultset.getInt("num_ruta")));
					
				}
				tamaño=verifi.size();
				for (Cliente producto : verifi) 
				{
					
					id=String.valueOf(producto.getId_c());
					nom_r=producto.getNom_r();
					nom_c=producto.getNom_c()+" "+producto.getApel_c();
					dia_a=producto.getDia();
					setPintarTabla(id,nom_r,nom_c,dia_a,con);
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
	 public void setPintarTabla(String id,String nom_r,String nom_c,String dia_a,int con)
	 {
			 dtm.setValueAt(id,con,0);
			 dtm.setValueAt(nom_r,con,1);
			 dtm.setValueAt(nom_c,con,2);
			 dtm.setValueAt(dia_a,con,3);
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
	private PanelTabla_c pnlTabla_c;
	public HiloPintarTabla()
	{
		senHilo=true;
		pnlTabla_c=new PanelTabla_c();
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
			pnlTabla_c.setPintarTabla();
		}
		
	}
	public void setTerminarHilo()
	{
		senHilo=false;
	}
	
	
	
}