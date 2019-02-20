package Mundo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.log.SysoCounter;


public class Servidor implements Runnable{

	private ServerSocket servidor;
	private Socket socket;
	private BufferedReader rd;
	private static InputStreamReader in;
	private String mensaje_texto;
	private Gson gson;
	private ArrayList<Vendedor> vendedor;
	private ArrayList<Cliente> cliente;
	private ArrayList<Producto> producto;
	private String gsonArrayVendedor;
	private Socket socketc;
    private DataOutputStream flujo;
    private PrintWriter pw;
    private Paquete_envio_BDD paquete_de_envio;
	
	
	public Servidor()
	{
		vendedor=null;
		cliente=null;
		producto=null;
		paquete_de_envio=null;
		gsonArrayVendedor="";
		Thread hilo = new Thread(this);
		hilo.start();
	}
	
	public void run() {
		
		try
		{
			
			gson = new Gson();
			while(true)
			{
				servidor = new ServerSocket(5000);
				socket = servidor.accept();
				in = new InputStreamReader(socket.getInputStream());
				rd= new BufferedReader(in);
				mensaje_texto = rd.readLine();
				/*RECIBE JSON
				//Vendedor jsonToJava;
				//jsonToJava = gson.fromJson(mensaje_texto, Vendedor.class);
				 * 
				 */
//	        	···········DETECTA ONLINE = detecta es ip··························
				InetAddress localizacion = socket.getInetAddress();
//				nombre red
				String IpRemota = localizacion.getHostAddress();
				in.close();
				rd.close();
				servidor.close();
				socket.close();
				System.out.println(mensaje_texto);
				//System.out.println("Hola1: "+jsonToJava.getNom()+" "+ jsonToJava.getId()+" "+IpRemota);
				
				if(mensaje_texto.equals("1"))
				{
					setEnviarDatosVendedor(IpRemota);
				}
				else if(mensaje_texto.equals("0"))
				{
					System.out.println("Prueba conexion");
					try
			        {
			            socketc = new Socket(IpRemota,5002);
			            pw = new PrintWriter(socketc.getOutputStream());
			            pw.write("0");
			            pw.flush();
			            pw.close();
			            socketc.close();
			        }
			        catch (Exception e)
			        {
			            e.getMessage();
			        }
					
				}
				
				
				
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	private void setEnviarDatosVendedor(String ip) 
	{
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();
			//Ejecutar Sql
			ResultSet miresultset1 = mistatement.executeQuery("select * from vendedor");
			vendedor = new ArrayList<Vendedor>();
			//Recorrer el resulset
			while(miresultset1.next())
			{
				vendedor.add(new Vendedor(miresultset1.getInt("Id_Ven"),miresultset1.getString("nom_Ven"),
						miresultset1.getString("apell_Ven"),miresultset1.getString("Ruta_Ven"),
						miresultset1.getString("telefono_Ven"),miresultset1.getString("correo_e")));
			}
			
			/*
			 * for (Vendedor integer : vendedor) {
			 * System.out.println(integer.getId()+" "+integer.getNom()+" "+integer.getApel()
			 * +" "+ integer.getRuta()+" "+integer.getCorreo()+" "+integer.getTele()); }
			 */
			
			conexion.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		setLlenarArray_Cliente();
		setLlenarArray_Producto();
		setEnviarArrayVendedor(ip);
	
	}

	private void setEnviarArrayVendedor(String ip) 
	{
		Gson gson = new Gson();
		paquete_de_envio = new Paquete_envio_BDD(vendedor,cliente,producto);
		gsonArrayVendedor=gson.toJson(paquete_de_envio);
		System.out.println(gsonArrayVendedor);
		
		/*DE GSON A ARRAY
		 * String ja =gsonArrayVendedor; ArrayList<Vendedor> vendedor2 = new
		 * ArrayList<Vendedor>(); vendedor2 = gson.fromJson(ja, new
		 * TypeToken<ArrayList<Vendedor>>(){}.getType());
		 * 
		 * for (Vendedor integer : vendedor2) {
		 * System.out.println(integer.getId()+" "+integer.getNom()+" "+integer.getApel()
		 * +" "+ integer.getRuta()+" "+integer.getCorreo()+" "+integer.getTele()); }
		 */
		
		 try
	        {
	            socketc = new Socket(ip,5002);
	            pw = new PrintWriter(socketc.getOutputStream());
	            pw.write(gsonArrayVendedor);
	            pw.flush();
	            pw.close();
	            socketc.close();
	        }
	        catch (Exception e)
	        {
	            e.getMessage();
	        }
	}
	
	public void setLlenarArray_Cliente()
	{
		
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();
			//Ejecutar Sql
			ResultSet miresultset1 = mistatement.executeQuery("select * from cliente");
			cliente = new ArrayList<Cliente>();
			//Recorrer el resulset
			while(miresultset1.next())
			{
				cliente.add(new Cliente(miresultset1.getInt("Id_Cli"),miresultset1.getString("nom_razon_social"),
						miresultset1.getString("nom_Cli"),miresultset1.getString("apell_CLie"),
						miresultset1.getString("direccion"),miresultset1.getString("telefono"),
						miresultset1.getString("correo_e"),miresultset1.getString("dia_atencion")));
			}
			
			conexion.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	public void setLlenarArray_Producto()
	{
		
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();
			//Ejecutar Sql
			ResultSet miresultset1 = mistatement.executeQuery("select * from producto");
			producto = new ArrayList<Producto>();
			//Recorrer el resulset
			while(miresultset1.next())
			{
				producto.add(new Producto(miresultset1.getInt("Id_Pro"),miresultset1.getString("nom_Pro"),
						miresultset1.getInt("Valor_sin_iva"),miresultset1.getString("Linea_pro"),
						miresultset1.getString("Casa_de_Export")));
			}
			
			
			conexion.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
