package Mundo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfazBodega.PanelAgregar_b;

public class Mundo {

	private boolean very;
	private int codigo;
	public Mundo()
	{
		very=true;
	}
	public void setCrearProducto(String cod,String nom,String valor,String linea,String casa)
	{
		very=true;
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();
//-------------------------------------------------------------------------------------------
			//Ejecutar Sql
			ResultSet miresultset = mistatement.executeQuery("select * from producto");
			ArrayList<Integer> verifi = new ArrayList<Integer>();
			//Recorrer el resulset
			while(miresultset.next())
			{
				verifi.add(miresultset.getInt("Id_Pro"));
				
			}
			for (Integer integer : verifi) 
			{
				int prue=integer;
				if(prue==Integer.parseInt(cod))
				{
					JOptionPane.showMessageDialog(null,"Ya ahi un producto con el mismo codigo","Alerta",0);
					 very=false;
				}
			}
//--------------------------------------------------------------------------------------------
			//Insertar valores
			if(very==true)
			{
				String instruccion_sql = "insert into producto values ("+cod+",'"+nom+"',"+valor+",'"+linea+"',"+"'"+casa+"'"+")";
				mistatement.executeUpdate(instruccion_sql);
				JOptionPane.showMessageDialog(null,"El producto se creo correctamente","Atención",1);
			}
			
			conexion.close();
		}
		catch(Exception g)
		{
			JOptionPane.showMessageDialog(null,"Hubo un erro con la base de datos","Alerta",0);
		}
	}
	public void setCrearCliente(String id,String nom_r,String nom_c,String apel_c,String dire_c,String tel,String correo,String dia)
	{
		very=true;
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();
//-------------------------------------------------------------------------------------------
			//Ejecutar Sql
			ResultSet miresultset = mistatement.executeQuery("select * from cliente");
			ArrayList<Integer> verifi = new ArrayList<Integer>();
			//Recorrer el resulset
			while(miresultset.next())
			{
				verifi.add(miresultset.getInt("Id_Cli"));
				
			}
			for (Integer integer : verifi) 
			{
				int prue=integer;
				if(prue==Integer.parseInt(id))
				{
					JOptionPane.showMessageDialog(null,"Ya ahi un cliente con el mismo codigo","Alerta",0);
					 very=false;
				}
			}
//--------------------------------------------------------------------------------------------
			//Insertar valores
			if(very==true)
			{
				String instruccion_sql = "insert into cliente values ("+id+",'"+nom_r+"','"+nom_c+"','"+apel_c+"',"+"'"+dire_c+"','"+tel+"','"+correo+"','"+dia+"')";
				mistatement.executeUpdate(instruccion_sql);
				System.out.println(instruccion_sql);
				JOptionPane.showMessageDialog(null,"El cliente se creo correctamente","Atención",1);
			}
			
			conexion.close();
		}
		catch(Exception g)
		{
			JOptionPane.showMessageDialog(null,"Hubo un erro con la base de datos","Alerta",0);
			System.out.println(g);
		}
	}
	public void setCrearVendedor(String cod, String nom, String apel, String ruta, String tele, String correo) 
	{
		very=true;
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();
//-------------------------------------------------------------------------------------------
			//Ejecutar Sql
			ResultSet miresultset = mistatement.executeQuery("select * from vendedor");
			ArrayList<Integer> verifi = new ArrayList<Integer>();
			//Recorrer el resulset
			while(miresultset.next())
			{
				verifi.add(miresultset.getInt("Id_Ven"));
				
			}
			for (Integer integer : verifi) 
			{
				int prue=integer;
				if(prue==Integer.parseInt(cod))
				{
					JOptionPane.showMessageDialog(null,"Ya ahi un vendedor con el mismo codigo","Alerta",0);
					 very=false;
				}
			}
//--------------------------------------------------------------------------------------------
			//Insertar valores
			if(very==true)
			{
				String instruccion_sql = "insert into vendedor values ("+cod+",'"+nom+"','"+apel+"','"+ruta+"',"+"'"+tele+"','"+correo+"')";
				mistatement.executeUpdate(instruccion_sql);
				JOptionPane.showMessageDialog(null,"El vendedor se creo correctamente","Atención",1);
			}
			
			conexion.close();
		}
		catch(Exception g)
		{
			JOptionPane.showMessageDialog(null,"Hubo un erro con la base de datos","Alerta",0);
		}
	}
	
	public void setAgregarProductoBodega(String nombre,String cantidad)
	{
		very=true;
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();
//-------------------------------------------------------------------------------------------
			//Ejecutar Sql
			ResultSet miresultset = mistatement.executeQuery("select * from producto");
			//Recorrer el resulset
			while(miresultset.next())
			{
				String prue=miresultset.getString("nom_Pro");
				if(prue.contains(nombre))
				{
					 codigo=miresultset.getInt("Id_Pro");
				}
			}
//---------------------------------------------------------------------------------------------------
			//Segunda consulta
			
			//2.Crear objecto Statement
			Statement mistatement1 = conexion.createStatement();
//-------------------------------------------------------------------------------------------
			//Ejecutar Sql
			ResultSet miresultset1 = mistatement1.executeQuery("select * from bodega");
			ArrayList<Integer> verifi = new ArrayList<Integer>();
			//Recorrer el resulset
			while(miresultset1.next())
			{
				verifi.add(miresultset1.getInt("Id_Pro2"));
			}
			
			 for (Integer integer : verifi) 
			{
				int prue=integer;
				if(prue==codigo)
				{
					JOptionPane.showMessageDialog(null,"Ya ahi un producto agregado con el mismo codigo","Alerta",0);
					 very=false;
				}
			}
			 
//--------------------------------------------------------------------------------------------
			//Insertar valores
			if(very==true)
			{
				String instruccion_sql = "insert into bodega values ("+codigo+","+cantidad+","+1+")";
				mistatement.executeUpdate(instruccion_sql);
				JOptionPane.showMessageDialog(null,"El producto se agrego correctamente","Atención",1);
			}
			
			conexion.close();
		}
		catch(Exception g)
		{
			JOptionPane.showMessageDialog(null,"Hubo un erro con la base de datos","Alerta",0);
			System.out.println(g.getMessage());
		}
	}
	
	public ArrayList<Producto> getLlenarCombox()
	{
		ArrayList<Producto> verifi = null;
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();
			//Ejecutar Sql
			ResultSet miresultset = mistatement.executeQuery("select * from producto");
			verifi = new ArrayList<Producto>();
			//Recorrer el resulset
			while(miresultset.next())
			{
				verifi.add(new Producto(miresultset.getInt("Id_Pro"),miresultset.getString("nom_Pro"),miresultset.getInt("Valor_sin_iva"),miresultset.getString("Linea_pro"),miresultset.getString("Casa_de_Export")));
				
			}
			
			
			conexion.close();
			
		}
		catch(Exception g)
		{
			JOptionPane.showMessageDialog(null,"Hubo un erro con la base de datos","Alerta",0);
		}
		
		return verifi;
		
	}
	
	public ArrayList<Bodega> getLlenarCombox1()
	{
		ArrayList<Bodega> verifi = null;
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();
			//Ejecutar Sql
			ResultSet miresultset = mistatement.executeQuery("SELECT b.Id_Pro2, p.nom_Pro, p.Linea_pro, b.cantidad FROM producto p INNER JOIN bodega b ON(p.Id_Pro = b.Id_Pro2)");
			verifi = new ArrayList<Bodega>();
			//Recorrer el resulset
			while(miresultset.next())
			{
				verifi.add(new Bodega(miresultset.getInt("Id_Pro2"),miresultset.getString("nom_Pro"),miresultset.getString("Linea_pro"),miresultset.getInt("cantidad")));
				
			}
			
			
			conexion.close();
			
		}
		catch(Exception g)
		{
			JOptionPane.showMessageDialog(null,"Hubo un erro con la base de datos","Alerta",0);
		}
		
		return verifi;
		
	}
}
