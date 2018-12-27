package interfazCuentaT;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Controlador.Controlador;
import Mundo.Producto;

public class PanelTablaT extends JPanel implements ActionListener{

	private Controlador ctrl;
	private JLabel info[] = new JLabel[8];
	private static JTextField intro[] = new JTextField[2];
	private JButton bot[] = new JButton[7];
	private static JTable tabla,tabla2;
	private static DefaultTableModel dtm,dmt2;
	private static String cabecera[]=new String[3];
	private static String cabecera2[]=new String[3];
	private static String datos[][] = new String[7][3];
	private static String datos2[][] = new String[5][3];
	
	public PanelTablaT(Controlador ctrl) 
	{
		setBackground(new Color(202,255,79));
		setLayout( null );
		//Integar controlador
		this.ctrl=ctrl;
		
		//Texto Clientes
		info[0] = new JLabel("Contabilidad");
		Font auxFont=info[0].getFont();
		info[0].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));
		info[0].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[0].setBounds(290, 10, 300, 25);
		add(info[0]);
		
		//Texto J&C
		info[1] = new JLabel("J&C");
		Font auxFont1=info[1].getFont();
		info[1].setFont(new Font("Elephant", auxFont.getStyle(), 40));
		info[1].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[1].setBounds(20, 10, 150, 30);
		add(info[1]);
				
		//Texto Clientes
		info[2] = new JLabel("Capital");
		Font auxFont2=info[2].getFont();
		info[2].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 20));
		info[2].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[2].setBounds(200, 80, 300, 25);
		add(info[2]);
		
		//Texto para codigo
		intro[0] = new JTextField(100);
		intro[0].setBounds(195, 110, 80, 20);
		add(intro[0]);
		
		//Texto Clientes
		info[3] = new JLabel("Inversión");
		Font auxFont3=info[3].getFont();
		info[3].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 20));
		info[3].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[3].setBounds(480, 80, 300, 25);
		add(info[3]);
		
		//Texto para codigo
		intro[1] = new JTextField(100);
		intro[1].setBounds(485, 110, 80, 20);
		add(intro[1]);
		
		//Boton Generar
		bot[0] = new JButton("Guardar");
		bot[0].addActionListener(this);
		add(bot[0]);
		bot[0].setBounds(335, 95, 80, 25);
		setPintarIni();
		
		cabecera[0]="Día";
		cabecera[1]="Gastos";
		cabecera[2]="Utilidad";
		
		
		
		dtm= new DefaultTableModel(datos,cabecera);
		tabla = new JTable(dtm);
		Font auxFont90=tabla.getFont();
		tabla.setFont(new Font(auxFont90.getFontName(), auxFont90.getStyle(), 15));
		tabla.setPreferredScrollableViewportSize(new Dimension(450, 200));
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumnModel columnModel = tabla.getColumnModel();
		
		columnModel.getColumn(0).setPreferredWidth(90);
		columnModel.getColumn(1).setPreferredWidth(120);
		columnModel.getColumn(2).setPreferredWidth(120);
		tabla.setRowHeight(20);
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(210, 150, 333, 163);
		add(scrollPane);
		
		dtm.setValueAt("Lunes",0,0);
		dtm.setValueAt("Martes",1,0);
		dtm.setValueAt("Miercoles",2,0);
		dtm.setValueAt("Jueves",3,0);
		dtm.setValueAt("Viernes",4,0);
		dtm.setValueAt("Sabado",5,0);
		dtm.setValueAt("Semana",6,0);
		
		
		//Boton Generar
		bot[1] = new JButton("Guardar");
		bot[1].addActionListener(this);
		add(bot[1]);
		bot[1].setBounds(540, 150, 85, 25);
		
		//Boton Generar
		bot[2] = new JButton("Calcular");
		bot[2].addActionListener(this);
		add(bot[2]);
		bot[2].setBounds(540, 174, 85, 25);
		
		//Boton Generar
		bot[5] = new JButton("Borrar");
		bot[5].addActionListener(this);
		add(bot[5]);
		bot[5].setBounds(540, 198, 85, 25);
		
		//Texto Clientes
		info[4] = new JLabel("Total: ");
		Font auxFont4=info[4].getFont();
		info[4].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 25));
		info[4].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[4].setBounds(210, 320, 300, 25);
		add(info[4]);
		
		//Texto Clientes
		info[5] = new JLabel("0 $");
		Font auxFont5=info[5].getFont();
		info[5].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[5].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[5].setBounds(280, 325, 300, 25);
		add(info[5]);
		
		//Texto Clientes
		info[6] = new JLabel("Total: ");
		Font auxFont6=info[6].getFont();
		info[6].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 25));
		info[6].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[6].setBounds(210, 540, 300, 25);
		add(info[6]);
		
		//Texto Clientes
		info[7] = new JLabel("0 $");
		Font auxFont7=info[7].getFont();
		info[7].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[7].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[7].setBounds(280, 545, 300, 25);
		add(info[7]);
		
		cabecera2[0]="Semana";
		cabecera2[1]="Gastos";
		cabecera2[2]="Utilidad";
		
		dmt2 = new DefaultTableModel(datos2,cabecera2);
		tabla2 = new JTable(dmt2);
		Font auxFont91=tabla.getFont();
		tabla2.setFont(new Font(auxFont91.getFontName(), auxFont91.getStyle(), 15));
		tabla2.setPreferredScrollableViewportSize(new Dimension(450, 200));
		tabla2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumnModel columnModel2 = tabla2.getColumnModel();
		
		columnModel2.getColumn(0).setPreferredWidth(90);
		columnModel2.getColumn(1).setPreferredWidth(120);
		columnModel2.getColumn(2).setPreferredWidth(120);
		tabla2.setRowHeight(20);
		JScrollPane scrollPane2 = new JScrollPane(tabla2);
		scrollPane2.setBounds(210, 400, 333, 123);
		add(scrollPane2);
		
		dmt2.setValueAt("Semana 1",0,0);
		dmt2.setValueAt("Semana 2",1,0);
		dmt2.setValueAt("Semana 3",2,0);
		dmt2.setValueAt("Semana 4",3,0);
		dmt2.setValueAt("Mes",4,0);
		
		//Boton Generar
		bot[3] = new JButton("Guardar");
		bot[3].addActionListener(this);
		add(bot[3]);
		bot[3].setBounds(540, 400, 85, 25);
				
		//Boton Generar
		bot[4] = new JButton("Calcular");
		bot[4].addActionListener(this);
		add(bot[4]);
		bot[4].setBounds(540, 424, 85, 25);
		
		//Boton Generar
		bot[6] = new JButton("Borrar");
		bot[6].addActionListener(this);
		add(bot[6]);
		bot[6].setBounds(540, 448, 85, 25);
		
		setPintarTabla1();
		setPintarTabla2();
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		Object btnpuch = e.getSource();
		
		if(btnpuch==bot[0])
		{
			String cap = intro[0].getText();
			String inver = intro[1].getText();
			if(!intro[0].getText().equals("") && !intro[1].getText().equals(""))
			{
				
				try {
					FileWriter escritura = new FileWriter("D:/J&C/Contabilidad/capital.jyc");
					
					for(int i = 0;i<cap.length();i++)
					{
						escritura.write(cap.charAt(i));
					}
					escritura.close();
					FileWriter escritura1 = new FileWriter("D:/J&C/Contabilidad/inversion.jyc");
					
					for(int i = 0;i<inver.length();i++)
					{
						escritura1.write(inver.charAt(i));
					}
					escritura1.close();
				} catch (IOException q) {
					
					q.printStackTrace();
				}
				
				setPintarIni();
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Ahí un espacio vacio","Alerta",0);
			}
		}
		else if(btnpuch==bot[1])
		{
			setGuardar();
		}
		else if(btnpuch==bot[2])
		{
			setCalcular();
		}
		else if(btnpuch==bot[3])
		{
			setGuardar2();
		}
		else if(btnpuch==bot[4])
		{
			setCalcular2();
		}
		else if(btnpuch==bot[5])
		{
			setBorrarTablaDiario();
		}
		else if(btnpuch==bot[6])
		{
			setBorrarTablaMes();
		}
		
	}	

	public void setPintarIni()
	{
		String capital="",inversion="";
		try {
			
				FileReader entrada = new FileReader("D:/J&C/Contabilidad/capital.jyc");
				BufferedReader mibuffer = new BufferedReader(entrada);
			
				String linea="";
			
			
				while(linea!= null)
				{
					linea=mibuffer.readLine();
					if(linea!=null)
					{
						capital=linea;
					}
				
				}
				FileReader entrada1 = new FileReader("D:/J&C/Contabilidad/inversion.jyc");
				BufferedReader mibuffer1 = new BufferedReader(entrada1);
				String linea1="";
			
				while(linea1!= null)
				{
					linea1=mibuffer1.readLine();
					if(linea1!=null)
					{
					
						inversion=linea1;
					}
				
				}
				entrada.close();
				entrada1.close();
			
			}catch (IOException e) 
			{
				System.out.println("No se ha encontrado el archivo");	
			}
		
		intro[0].setText(capital);
		intro[1].setText(inversion);
	}
	
	public void setPintarTabla1()
	{
		//Lunes
		{
			String gastos="",utilidad="";
			try {
				
					FileReader entrada = new FileReader("D:/J&C/Contabilidad/tabla1/lun_gast.jyc");
					BufferedReader mibuffer = new BufferedReader(entrada);
				
					String linea="";
				
				
					while(linea!= null)
					{
						linea=mibuffer.readLine();
						if(linea!=null)
						{
							gastos=linea;
						}
					
					}
					FileReader entrada1 = new FileReader("D:/J&C/Contabilidad/tabla1/lun_util.jyc");
					BufferedReader mibuffer1 = new BufferedReader(entrada1);
					String linea1="";
				
					while(linea1!= null)
					{
						linea1=mibuffer1.readLine();
						if(linea1!=null)
						{
						
							utilidad=linea1;
						}
					
					}
					entrada.close();
					entrada1.close();
				
				}catch (IOException e) 
				{
					System.out.println("No se ha encontrado el archivo");	
				}
			
			dtm.setValueAt(gastos,0,1);
			dtm.setValueAt(utilidad,0,2);
		}
		//Martes
		{
			String gastos="",utilidad="";
			try {
				
				FileReader entrada = new FileReader("D:/J&C/Contabilidad/tabla1/mar_gast.jyc");
				BufferedReader mibuffer = new BufferedReader(entrada);
				
				String linea="";
						
						
				while(linea!= null)
				{
					linea=mibuffer.readLine();
					if(linea!=null)
					{
						gastos=linea;
					}
							
				}
				FileReader entrada1 = new FileReader("D:/J&C/Contabilidad/tabla1/mar_util.jyc");
				BufferedReader mibuffer1 = new BufferedReader(entrada1);
				String linea1="";
						
				while(linea1!= null)
				{
					linea1=mibuffer1.readLine();
					if(linea1!=null)
					{
						
						utilidad=linea1;
					}
							
				}
				entrada.close();
				entrada1.close();
						
			}catch (IOException e) 
			{
				System.out.println("No se ha encontrado el archivo");	
			}
					
			dtm.setValueAt(gastos,1,1);
			dtm.setValueAt(utilidad,1,2);
		}
		
		//miercoles
		{
			String gastos="",utilidad="";
			try {
				
				FileReader entrada = new FileReader("D:/J&C/Contabilidad/tabla1/mier_gast.jyc");
				BufferedReader mibuffer = new BufferedReader(entrada);
				
				String linea="";
						
						
				while(linea!= null)
				{
					linea=mibuffer.readLine();
					if(linea!=null)
					{
						gastos=linea;
					}
							
				}
				FileReader entrada1 = new FileReader("D:/J&C/Contabilidad/tabla1/mier_util.jyc");
				BufferedReader mibuffer1 = new BufferedReader(entrada1);
				String linea1="";
				
				while(linea1!= null)
				{
					linea1=mibuffer1.readLine();
					if(linea1!=null)
					{
						
						utilidad=linea1;
					}
					
				}
				entrada.close();
				entrada1.close();
				
			}catch (IOException e) 
			{
				System.out.println("No se ha encontrado el archivo");	
			}
					
			dtm.setValueAt(gastos,2,1);
			dtm.setValueAt(utilidad,2,2);
		}
				
		//jueves
		{
			String gastos="",utilidad="";
			try {
				
				FileReader entrada = new FileReader("D:/J&C/Contabilidad/tabla1/jue_gast.jyc");
				BufferedReader mibuffer = new BufferedReader(entrada);
				
				String linea="";
				
				
				while(linea!= null)
				{
					linea=mibuffer.readLine();
					if(linea!=null)
					{
						gastos=linea;
					}
					
				}
				FileReader entrada1 = new FileReader("D:/J&C/Contabilidad/tabla1/jue_util.jyc");
				BufferedReader mibuffer1 = new BufferedReader(entrada1);
				String linea1="";
				
				while(linea1!= null)
				{
					linea1=mibuffer1.readLine();
					if(linea1!=null)
					{
						
						utilidad=linea1;
					}
					
				}
				entrada.close();
				entrada1.close();
				
			}catch (IOException e) 
			{
				System.out.println("No se ha encontrado el archivo");	
			}
			
			dtm.setValueAt(gastos,3,1);
			dtm.setValueAt(utilidad,3,2);
		}
		//viernes
		{
			String gastos="",utilidad="";
			try {
				
				FileReader entrada = new FileReader("D:/J&C/Contabilidad/tabla1/vie_gast.jyc");
				BufferedReader mibuffer = new BufferedReader(entrada);
				
				String linea="";
				
				
				while(linea!= null)
				{
					linea=mibuffer.readLine();
					if(linea!=null)
					{
						gastos=linea;
					}
					
				}
				FileReader entrada1 = new FileReader("D:/J&C/Contabilidad/tabla1/vie_util.jyc");
				BufferedReader mibuffer1 = new BufferedReader(entrada1);
				String linea1="";
				
				while(linea1!= null)
				{
					linea1=mibuffer1.readLine();
					if(linea1!=null)
					{
						
						utilidad=linea1;
					}
					
				}
				entrada.close();
				entrada1.close();
				
			}catch (IOException e) 
			{
				System.out.println("No se ha encontrado el archivo");	
			}
			
			dtm.setValueAt(gastos,4,1);
			dtm.setValueAt(utilidad,4,2);
		}
		//sabado
		{
			String gastos="",utilidad="";
			try {
				
				FileReader entrada = new FileReader("D:/J&C/Contabilidad/tabla1/sab_gast.jyc");
				BufferedReader mibuffer = new BufferedReader(entrada);
				
				String linea="";
				
				
				while(linea!= null)
				{
					linea=mibuffer.readLine();
					if(linea!=null)
					{
						gastos=linea;
					}
					
				}
				FileReader entrada1 = new FileReader("D:/J&C/Contabilidad/tabla1/sab_util.jyc");
				BufferedReader mibuffer1 = new BufferedReader(entrada1);
				String linea1="";
				
				while(linea1!= null)
				{
					linea1=mibuffer1.readLine();
					if(linea1!=null)
					{
						
						utilidad=linea1;
					}
					
				}
				entrada.close();
				entrada1.close();
				
			}catch (IOException e) 
			{
				System.out.println("No se ha encontrado el archivo");	
			}
			
			dtm.setValueAt(gastos,5,1);
			dtm.setValueAt(utilidad,5,2);
		}
	}

	public void setPintarTabla2()
	{
		//1
		{
			String gastos="",utilidad="";
			try {
				
					FileReader entrada = new FileReader("D:/J&C/Contabilidad/tabla2/1_gas.jyc");
					BufferedReader mibuffer = new BufferedReader(entrada);
				
					String linea="";
				
				
					while(linea!= null)
					{
						linea=mibuffer.readLine();
						if(linea!=null)
						{
							gastos=linea;
						}
					
					}
					FileReader entrada1 = new FileReader("D:/J&C/Contabilidad/tabla2/1_uti.jyc");
					BufferedReader mibuffer1 = new BufferedReader(entrada1);
					String linea1="";
				
					while(linea1!= null)
					{
						linea1=mibuffer1.readLine();
						if(linea1!=null)
						{
						
							utilidad=linea1;
						}
					
					}
					entrada.close();
					entrada1.close();
				
				}catch (IOException e) 
				{
					System.out.println("No se ha encontrado el archivo");	
				}
			
			dmt2.setValueAt(gastos,0,1);
			dmt2.setValueAt(utilidad,0,2);
		}
		//2
		{
			String gastos="",utilidad="";
			try {
				
				FileReader entrada = new FileReader("D:/J&C/Contabilidad/tabla2/2_gas.jyc");
				BufferedReader mibuffer = new BufferedReader(entrada);
				
				String linea="";
						
						
				while(linea!= null)
				{
					linea=mibuffer.readLine();
					if(linea!=null)
					{
						gastos=linea;
					}
							
				}
				FileReader entrada1 = new FileReader("D:/J&C/Contabilidad/tabla2/2_uti.jyc");
				BufferedReader mibuffer1 = new BufferedReader(entrada1);
				String linea1="";
						
				while(linea1!= null)
				{
					linea1=mibuffer1.readLine();
					if(linea1!=null)
					{
						
						utilidad=linea1;
					}
							
				}
				entrada.close();
				entrada1.close();
						
			}catch (IOException e) 
			{
				System.out.println("No se ha encontrado el archivo");	
			}
					
			dmt2.setValueAt(gastos,1,1);
			dmt2.setValueAt(utilidad,1,2);
		}
		
		//3
		{
			String gastos="",utilidad="";
			try {
				
				FileReader entrada = new FileReader("D:/J&C/Contabilidad/tabla2/3_gas.jyc");
				BufferedReader mibuffer = new BufferedReader(entrada);
				
				String linea="";
						
						
				while(linea!= null)
				{
					linea=mibuffer.readLine();
					if(linea!=null)
					{
						gastos=linea;
					}
							
				}
				FileReader entrada1 = new FileReader("D:/J&C/Contabilidad/tabla2/3_uti.jyc");
				BufferedReader mibuffer1 = new BufferedReader(entrada1);
				String linea1="";
				
				while(linea1!= null)
				{
					linea1=mibuffer1.readLine();
					if(linea1!=null)
					{
						
						utilidad=linea1;
					}
					
				}
				entrada.close();
				entrada1.close();
				
			}catch (IOException e) 
			{
				System.out.println("No se ha encontrado el archivo");	
			}
					
			dmt2.setValueAt(gastos,2,1);
			dmt2.setValueAt(utilidad,2,2);
		}
				
		//4
		{
			String gastos="",utilidad="";
			try {
				
				FileReader entrada = new FileReader("D:/J&C/Contabilidad/tabla2/4_gas.jyc");
				BufferedReader mibuffer = new BufferedReader(entrada);
				
				String linea="";
				
				
				while(linea!= null)
				{
					linea=mibuffer.readLine();
					if(linea!=null)
					{
						gastos=linea;
					}
					
				}
				FileReader entrada1 = new FileReader("D:/J&C/Contabilidad/tabla2/4_uti.jyc");
				BufferedReader mibuffer1 = new BufferedReader(entrada1);
				String linea1="";
				
				while(linea1!= null)
				{
					linea1=mibuffer1.readLine();
					if(linea1!=null)
					{
						
						utilidad=linea1;
					}
					
				}
				entrada.close();
				entrada1.close();
				
			}catch (IOException e) 
			{
				System.out.println("No se ha encontrado el archivo");	
			}
			
			dmt2.setValueAt(gastos,3,1);
			dmt2.setValueAt(utilidad,3,2);
		}
	}
	
	public void setGuardar()
	{
		try {
			//Lunes
			FileWriter escritura = new FileWriter("D:/J&C/Contabilidad/tabla1/lun_gast.jyc");
			
			for(int i = 0;i<((String) dtm.getValueAt(0,1)).length();i++)
			{
				escritura.write(((String) dtm.getValueAt(0,1)).charAt(i));
			}
			escritura.close();
			FileWriter escritura1 = new FileWriter("D:/J&C/Contabilidad/tabla1/lun_util.jyc");
			
			for(int i = 0;i<((String) dtm.getValueAt(0,2)).length();i++)
			{
				escritura1.write(((String) dtm.getValueAt(0,2)).charAt(i));
			}
			escritura1.close();
			//martes
			FileWriter escritura2 = new FileWriter("D:/J&C/Contabilidad/tabla1/mar_gast.jyc");
			
			for(int i = 0;i<((String) dtm.getValueAt(1,1)).length();i++)
			{
				escritura2.write(((String) dtm.getValueAt(1,1)).charAt(i));
			}
			escritura2.close();
			FileWriter escritura3 = new FileWriter("D:/J&C/Contabilidad/tabla1/mar_util.jyc");
			
			for(int i = 0;i<((String) dtm.getValueAt(1,2)).length();i++)
			{
				escritura3.write(((String) dtm.getValueAt(1,2)).charAt(i));
			}
			escritura3.close();
			//miercoles
			FileWriter escritura4 = new FileWriter("D:/J&C/Contabilidad/tabla1/mier_gast.jyc");
			
			for(int i = 0;i<((String) dtm.getValueAt(2,1)).length();i++)
			{
				escritura4.write(((String) dtm.getValueAt(2,1)).charAt(i));
			}
			escritura4.close();
			FileWriter escritura5 = new FileWriter("D:/J&C/Contabilidad/tabla1/mier_util.jyc");
			
			for(int i = 0;i<((String) dtm.getValueAt(2,2)).length();i++)
			{
				escritura5.write(((String) dtm.getValueAt(2,2)).charAt(i));
			}
			escritura5.close();
			//jueves
			FileWriter escritura6 = new FileWriter("D:/J&C/Contabilidad/tabla1/jue_gast.jyc");
			
			for(int i = 0;i<((String) dtm.getValueAt(3,1)).length();i++)
			{
				escritura6.write(((String) dtm.getValueAt(3,1)).charAt(i));
			}
			escritura6.close();
			FileWriter escritura7 = new FileWriter("D:/J&C/Contabilidad/tabla1/jue_util.jyc");
			
			for(int i = 0;i<((String) dtm.getValueAt(3,2)).length();i++)
			{
				escritura7.write(((String) dtm.getValueAt(3,2)).charAt(i));
			}
			escritura7.close();
			//viernes
			FileWriter escritura8 = new FileWriter("D:/J&C/Contabilidad/tabla1/vie_gast.jyc");
			
			for(int i = 0;i<((String) dtm.getValueAt(4,1)).length();i++)
			{
				escritura8.write(((String) dtm.getValueAt(4,1)).charAt(i));
			}
			escritura8.close();
			FileWriter escritura9 = new FileWriter("D:/J&C/Contabilidad/tabla1/vie_util.jyc");
			
			for(int i = 0;i<((String) dtm.getValueAt(4,2)).length();i++)
			{
				escritura9.write(((String) dtm.getValueAt(4,2)).charAt(i));
			}
			escritura9.close();
			//Sabado
			FileWriter escritura10 = new FileWriter("D:/J&C/Contabilidad/tabla1/sab_gast.jyc");
			
			for(int i = 0;i<((String) dtm.getValueAt(5,1)).length();i++)
			{
				escritura10.write(((String) dtm.getValueAt(5,1)).charAt(i));
			}
			escritura10.close();
			FileWriter escritura11 = new FileWriter("D:/J&C/Contabilidad/tabla1/sab_util.jyc");
			
			for(int i = 0;i<((String) dtm.getValueAt(5,2)).length();i++)
			{
				escritura11.write(((String) dtm.getValueAt(5,2)).charAt(i));
			}
			escritura11.close();
		} catch (IOException q) {
			
			q.printStackTrace();
		}
	}
	public void setGuardar2()
	{
		try {
			//1
			FileWriter escritura = new FileWriter("D:/J&C/Contabilidad/tabla2/1_gas.jyc");
			
			for(int i = 0;i<((String) dmt2.getValueAt(0,1)).length();i++)
			{
				escritura.write(((String) dmt2.getValueAt(0,1)).charAt(i));
			}
			escritura.close();
			FileWriter escritura1 = new FileWriter("D:/J&C/Contabilidad/tabla2/1_uti.jyc");
			
			for(int i = 0;i<((String) dmt2.getValueAt(0,2)).length();i++)
			{
				escritura1.write(((String) dmt2.getValueAt(0,2)).charAt(i));
			}
			escritura1.close();
			//2
			FileWriter escritura2 = new FileWriter("D:/J&C/Contabilidad/tabla2/2_gas.jyc");
			
			for(int i = 0;i<((String) dmt2.getValueAt(1,1)).length();i++)
			{
				escritura2.write(((String) dmt2.getValueAt(1,1)).charAt(i));
			}
			escritura2.close();
			FileWriter escritura3 = new FileWriter("D:/J&C/Contabilidad/tabla2/2_uti.jyc");
			
			for(int i = 0;i<((String) dmt2.getValueAt(1,2)).length();i++)
			{
				escritura3.write(((String) dmt2.getValueAt(1,2)).charAt(i));
			}
			escritura3.close();
			//3
			FileWriter escritura4 = new FileWriter("D:/J&C/Contabilidad/tabla2/3_gas.jyc");
			
			for(int i = 0;i<((String) dmt2.getValueAt(2,1)).length();i++)
			{
				escritura4.write(((String) dmt2.getValueAt(2,1)).charAt(i));
			}
			escritura4.close();
			FileWriter escritura5 = new FileWriter("D:/J&C/Contabilidad/tabla2/3_uti.jyc");
			
			for(int i = 0;i<((String) dmt2.getValueAt(2,2)).length();i++)
			{
				escritura5.write(((String) dmt2.getValueAt(2,2)).charAt(i));
			}
			escritura5.close();
			//4
			FileWriter escritura6 = new FileWriter("D:/J&C/Contabilidad/tabla2/4_gas.jyc");
			
			for(int i = 0;i<((String) dmt2.getValueAt(3,1)).length();i++)
			{
				escritura6.write(((String) dmt2.getValueAt(3,1)).charAt(i));
			}
			escritura6.close();
			FileWriter escritura7 = new FileWriter("D:/J&C/Contabilidad/tabla2/4_uti.jyc");
			
			for(int i = 0;i<((String) dmt2.getValueAt(3,2)).length();i++)
			{
				escritura7.write(((String) dmt2.getValueAt(3,2)).charAt(i));
			}
			escritura7.close();
		} catch (IOException q) {
			
			q.printStackTrace();
		}
	}
	
	public void setCalcular()
	{
		int sumagas=0,sumaut=0,resul=0;
		for(int i=0;i<=5;i++)
		{
			sumagas=sumagas+Integer.parseInt((String) (dtm.getValueAt(i,1)));
			sumaut=sumaut+Integer.parseInt((String) (dtm.getValueAt(i,2)));
		}
		dtm.setValueAt(sumagas,6,1);
		dtm.setValueAt(sumaut,6,2);
		resul=sumaut-sumagas;
		if(resul>0)
		{
			info[5].setText(String.valueOf(resul)+" $");
			info[5].setForeground(Color.GREEN.darker().darker());
		}
		else if(resul==0)
		{
			info[5].setText(String.valueOf(resul)+" $");
			info[5].setForeground(Color.YELLOW.darker().darker());
		}
		else
		{
			info[5].setText(String.valueOf(resul)+" $");
			info[5].setForeground(Color.RED);
		}
		
	}
	
	public void setCalcular2()
	{
		int sumagas=0,sumaut=0,resul=0;
		for(int i=0;i<=3;i++)
		{
			sumagas=sumagas+Integer.parseInt((String) (dmt2.getValueAt(i,1)));
			sumaut=sumaut+Integer.parseInt((String) (dmt2.getValueAt(i,2)));
		}
		dmt2.setValueAt(sumagas,4,1);
		dmt2.setValueAt(sumaut,4,2);
		resul=sumaut-sumagas;
		if(resul>0)
		{
			info[7].setText(String.valueOf(resul)+" $");
			info[7].setForeground(Color.GREEN.darker().darker());
		}
		else if(resul==0)
		{
			info[7].setText(String.valueOf(resul)+" $");
			info[7].setForeground(Color.YELLOW.darker().darker());
		}
		else
		{
			info[7].setText(String.valueOf(resul)+" $");
			info[7].setForeground(Color.RED);
		}
		
	}
	
	public void setBorrarTablaDiario()
	{
		for(int i =0;i<6;i++)
		{
			dtm.setValueAt("0",i,1);
			dtm.setValueAt("0",i,2);
		}
		dtm.setValueAt("",6,1);
		dtm.setValueAt("",6,2);
		info[5].setText("0"+" $");
		info[5].setForeground(Color.BLUE.darker().darker().darker().darker());
		JOptionPane.showMessageDialog(null,"No olvide guardar cambios","Atención",1);
	}
	public void setBorrarTablaMes()
	{
		for(int i =0;i<4;i++)
		{
			dmt2.setValueAt("0",i,1);
			dmt2.setValueAt("0",i,2);
		}
		dmt2.setValueAt("",4,1);
		dmt2.setValueAt("",4,2);
		info[7].setText("0"+" $");
		info[7].setForeground(Color.BLUE.darker().darker().darker().darker());
		JOptionPane.showMessageDialog(null,"No olvide guardar cambios","Atención",1);
	}
}
class Auxcon
{
	int capital;
	int inversion;
	public Auxcon(int capital,int inversion)
	{
		this.capital=capital;
		this.inversion=inversion;
		
	}
	public int getCapital() {
		return capital;
	}
	public int getInversion() {
		return inversion;
	}
}