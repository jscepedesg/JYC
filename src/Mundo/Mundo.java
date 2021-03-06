package Mundo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import interfazBodega.PanelAgregar_b;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Mundo {

	private boolean very;
	private int codigo;
	private static String ruta = "C:\\Users\\Usuario\\Desktop\\factura\\";
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
				JOptionPane.showMessageDialog(null,"El producto se creo correctamente","Atenci�n",1);
			}
			
			conexion.close();
		}
		catch(Exception g)
		{
			JOptionPane.showMessageDialog(null,"Hubo un erro con la base de datos","Alerta",0);
		}
	}
	public void setCrearCliente(String id,String nom_r,String nom_c,String apel_c,String dire_c,String tel,String correo,String dia, int mensaje)
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
				String instruccion_sql = "insert into cliente (nom_razon_social,nom_Cli,apell_CLie,direccion,telefono,correo_e,dia_atencion,num_ruta)"
						+ " values ('"+nom_r+"','"+nom_c+"','"+apel_c+"',"+"'"+dire_c+"','"+tel+"','"+correo+"','"+dia+"',"+id+")";
				mistatement.executeUpdate(instruccion_sql);
				System.out.println(instruccion_sql);
				if(mensaje==1)
				{
					JOptionPane.showMessageDialog(null,"El cliente se creo correctamente","Atenci�n",1);
				}
				
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
				JOptionPane.showMessageDialog(null,"El vendedor se creo correctamente","Atenci�n",1);
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
				JOptionPane.showMessageDialog(null,"El producto se agrego correctamente","Atenci�n",1);
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
	
	public void setCrearFactura(int id_producto, int id_factura, int id_vendedor, int id_cliente
			,int cantidad, String fecha)
	{
		
		
		
		very=true;
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();

//--------------------------------------------------------------------------------------------
			//Insertar valores
			if(very==true)
			{
				String instruccion_sql = "insert into factura (Id_Pro3, id_factura, id_ven1, id_clie1, cantidad, fecha_de_factuarcion)"
						+ " values ("+id_producto+","+id_factura+","+id_vendedor+","+""+id_cliente+","+cantidad+",'"+fecha+"'"+")";
				mistatement.executeUpdate(instruccion_sql);
				System.out.println(instruccion_sql);
				
			}
			
			conexion.close();
		}
		catch(Exception g)
		{
			JOptionPane.showMessageDialog(null,"Hubo un erro con la base de datos","Alerta",0);
			System.out.println(g);
		}
		
		
	}
	public ArrayList<String> getLlenarCombox2() 
	{
		ArrayList<String> verifi = null;
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();
			//Ejecutar Sql
			ResultSet miresultset = mistatement.executeQuery("SELECT fecha_de_factuarcion " + 
					"FROM factura " + 
					"GROUP BY fecha_de_factuarcion " + 
					"ORDER BY fecha_de_factuarcion DESC");
			verifi = new ArrayList<String>();
			//Recorrer el resulset
			while(miresultset.next())
			{
				verifi.add(miresultset.getString("fecha_de_factuarcion"));
				
			}
			
			
			conexion.close();
			
		}
		catch(Exception g)
		{
			JOptionPane.showMessageDialog(null,"Hubo un erro con la base de datos","Alerta",0);
		}
		
		return verifi;
	}
	public ArrayList<String> getLlenarCombox3() 
	{
		ArrayList<String> verifi = null;
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();
			//Ejecutar Sql
			ResultSet miresultset = mistatement.executeQuery("SELECT Casa_de_Export " + 
					"FROM producto " + 
					"GROUP BY Casa_de_Export " + 
					"ORDER BY Casa_de_Export DESC");
			verifi = new ArrayList<String>();
			//Recorrer el resulset
			while(miresultset.next())
			{
				verifi.add(miresultset.getString("Casa_de_Export"));
				
			}
			
			
			conexion.close();
			
		}
		catch(Exception g)
		{
			JOptionPane.showMessageDialog(null,"Hubo un erro con la base de datos","Alerta",0);
		}
		
		return verifi;
	}
	public void setCrearConsolidado(String fecha) 
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
			ResultSet miresultset = mistatement.executeQuery("select * from registro");
			ArrayList<String> verifi = new ArrayList<String>();
			//Recorrer el resulset
			while(miresultset.next())
			{
				verifi.add(miresultset.getString("fecha"));
				System.out.println(verifi.toString());
				
			}
			for (String integer : verifi) 
			{
				String prue=integer;
				if(prue.equals(fecha))
				{
					JOptionPane.showMessageDialog(null,"Ya ahi un consolidado con la misma fecha","Alerta",0);
					 very=false;
					 setGenerarReporteConsolidadoGeneral(fecha);
				}
			}
			System.out.println(very);
//--------------------------------------------------------------------------------------------
			//Insertar valores
			if(very==true)
			{
				int num_regi=0;
				{
					String instruccion_sql1 = "SELECT COUNT(*) AS cuenta FROM registro";
					ResultSet miresultset1 = mistatement.executeQuery(instruccion_sql1);
					//Recorrer el resulset
					while(miresultset1.next())
					{
						num_regi=miresultset1.getInt("cuenta");				
					}
					System.out.println(num_regi);
				}
				
				
				String instruccion_sql = "insert into registro values ("+(num_regi+1)+",'"+fecha+"')";
				mistatement.executeUpdate(instruccion_sql);
				
				{
					String instruccion_sql3 = "SELECT Id_Pro3, SUM(cantidad) AS Cantidad_total " + 
							"FROM factura " + 
							"WHERE fecha_de_factuarcion = '"+fecha+"'" + 
							"GROUP BY Id_Pro3 ORDER BY Cantidad_total DESC";
					ResultSet miresultset3 = mistatement.executeQuery(instruccion_sql3);
					//Recorrer el resulset
					while(miresultset3.next())
					{
						String instruccion_sql2 = "insert into consolidado values ("+miresultset3.getInt("Id_Pro3")+","+miresultset3.getInt("Cantidad_total")+", '"+fecha+"', "+(num_regi+1)+")";
						System.out.println(instruccion_sql2);
						//mistatement.executeUpdate(instruccion_sql2);
						setTerminarConsolidado(instruccion_sql2);
					}
					
				}
				
				
				JOptionPane.showMessageDialog(null,"El Consolidado se creo correctamente","Atenci�n",1);
				setGenerarReporteConsolidadoGeneral(fecha);
			}
			
			conexion.close();
		}
		catch(Exception g)
		{
			JOptionPane.showMessageDialog(null,"Hubo un erro con la base de datos","Alerta",0);
			System.out.println(g.getMessage());
		}
	}
	
	public void setTerminarConsolidado(String consulta)
	{
		very=true;
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();

//--------------------------------------------------------------------------------------------
			//Insertar valores
			if(very==true)
			{
				mistatement.executeUpdate(consulta);
				System.out.println(consulta);
				
			}
			
			conexion.close();
		}
		catch(Exception g)
		{
			JOptionPane.showMessageDialog(null,"Hubo un erro con la base de datos","Alerta",0);
			System.out.println(g);
		}
	}
	
	public void setGenerarReporteConsolidadoGeneral(String fecha)
	{
		//Date date = new Date(fecha);
		System.out.println(fecha);
		try
		{
			Conexion con = new Conexion();
			Connection conn= con.getConexion();
			JasperReport a = null;
			String path = "src\\reportes\\consolidadoGen.jasper";
			
			a=(JasperReport) JRLoader.loadObjectFromFile(path);
			Map parametro = new HashMap();
			parametro.put("fecha", fecha);
			//parametro.put("fecha", date);
			JasperPrint fillreport = JasperFillManager.fillReport(a,parametro, conn);
			JasperViewer jv = new JasperViewer(fillreport,false);
			jv.setVisible(true);
			jv.show();
			
		}
		catch(Exception r)
		{
			JOptionPane.showMessageDialog(null, r);
		}
	}
	public void setCrearConsolidado(String fecha, String empresa) 
	{
		System.out.println(fecha);
		try
		{
			Conexion con = new Conexion();
			Connection conn= con.getConexion();
			JasperReport a = null;
			String path = "src\\reportes\\consolidadoEsp.jasper";
			
			a=(JasperReport) JRLoader.loadObjectFromFile(path);
			Map parametro = new HashMap();
			parametro.put("fecha", fecha);
			parametro.put("empresa", empresa);
			//parametro.put("fecha", date);
			JasperPrint fillreport = JasperFillManager.fillReport(a,parametro, conn);
			JasperViewer jv = new JasperViewer(fillreport,false);
			jv.setVisible(true);
			jv.show();
			
		}
		catch(Exception r)
		{
			JOptionPane.showMessageDialog(null, r);
		}
	}
	
	public void setCrearFactura(String fecha, int id, String valor_total, String ruta)
	{
		try
		{
			Conexion con = new Conexion();
			Connection conn= con.getConexion();
			JasperReport a = null;
			String path = "src\\reportes\\factura.jasper";
			
			a=(JasperReport) JRLoader.loadObjectFromFile(path);
			Map parametro = new HashMap();
			if(id<10)
			{
				parametro.put("numero_factura", "000"+id);
			}
			else
			{
				parametro.put("numero_factura", "00"+id);
			}
			parametro.put("total", valor_total);
			parametro.put("id_factura", id);
			parametro.put("fecha_factura", fecha);
			JasperPrint fillreport = JasperFillManager.fillReport(a,parametro, conn);
			JasperViewer jv = new JasperViewer(fillreport,false);
			//jv.setVisible(true);
			//jv.show();
			
			//C:\Users\Usuario\Desktop\factura
			//setExportToPdf("C:\\Users\\Usuario\\Desktop\\factura\\"+id+".pdf", fillreport);
			setExportToPdf(ruta+id+".pdf", fillreport);
			
		}
		catch(Exception r)
		{
			JOptionPane.showMessageDialog(null,r);
		}
	}
	public void setCrearFacturacion(String fecha) 
	{
		String rutaPro = null;
		System.out.println(numerodefacturas(fecha)); 
		try
		{
			File directorio;
			directorio = new File(ruta+fecha);
			rutaPro=ruta+fecha+"\\";
			directorio.mkdir();
		}
		catch(Exception e)
		{
			
		}
		
		int a = numerodefacturas(fecha);
		for(int i =1; i<=a;i++)
		{
			System.out.println(i);
			//setCrearFactura();
			String valor = getValor_total(fecha,i);
			setCrearFactura(fecha,i,valor,rutaPro);
		}
	}
	
	public int numerodefacturas(String fecha)
	{
		int numero_f=0;
		
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();
			//Ejecutar Sql
			String sentencia = "SELECT COUNT(*) AS cuenta "+ 
					"FROM factura "+ 
					"WHERE fecha_de_factuarcion = '"+fecha+"'" + 
					"GROUP BY id_factura";
			System.out.println(sentencia);
			ResultSet miresultset = mistatement.executeQuery(sentencia);
			//Recorrer el resulset
			while(miresultset.next())
			{
				numero_f++;
				
			}
			
			
			conexion.close();
			
		}
		catch(Exception g)
		{
			JOptionPane.showMessageDialog(null,"Hubo un erro con la base de datos","Alerta",0);
		}
		
		return numero_f;
	}
	
	public String getValor_total(String fecha, int id)
	{
		String valor = null;
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();
			//Ejecutar Sql
			String sentencia = "SELECT ROUND(SUM(ROUND((p.Valor_sin_iva*1.19)*f.cantidad))) AS Total " + 
					"FROM factura f INNER JOIN producto p " + 
					"ON(p.Id_Pro=f.Id_Pro3) " + 
					"WHERE f.id_factura = "+id+" AND f.fecha_de_factuarcion = '"+fecha+"' " + 
					"GROUP BY f.id_factura";
			System.out.println(sentencia);
			ResultSet miresultset = mistatement.executeQuery(sentencia);
			//Recorrer el resulset
			while(miresultset.next())
			{
				valor=miresultset.getString("Total");
				
			}
			
			
			conexion.close();
			
		}
		catch(Exception g)
		{
			JOptionPane.showMessageDialog(null,"Hubo un erro con la base de datos","Alerta",0);
		}
		
		
		return valor;
	}
	
	public void setExportToPdf(String path, JasperPrint reporte)
	{
		try
		{
			JasperExportManager.exportReportToPdfFile(reporte, path);
		}
		catch(Exception e)
		{
			
		}
		
	}
	
}
