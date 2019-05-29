package interfazConsolidado;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.jidesoft.swing.ComboBoxSearchable;

import Controlador.Controlador;
import Mundo.Producto;

public class PanelPrincipalConsolidado extends JPanel implements ActionListener
{
	
	private Controlador ctrl;
	private JLabel info[]= new JLabel[5];
	private JComboBox productos, casadeimportacion;
	private JButton bot_agregar, bot_casa;
	
	
	public PanelPrincipalConsolidado(Controlador ctrl) 
	{
		setBackground(new Color(202,255,79));
		setLayout( null );
		//Integar controlador
		this.ctrl=ctrl;
		
		//Texto Consolidado
		info[0] = new JLabel("Consolidado");
		Font auxFont=info[0].getFont();
		info[0].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));
		info[0].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[0].setBounds(200, 10, 300, 35);
		add(info[0]);
					
		//Texto J&C
		info[1] = new JLabel("J&C");
		Font auxFont1=info[1].getFont();
		info[1].setFont(new Font("Elephant", auxFont.getStyle(), 40));
		info[1].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[1].setBounds(20, 10, 150, 30);
		add(info[1]);
		
		//Texto Codigo
		info[2] = new JLabel("Fecha: ");
		Font auxFont3=info[2].getFont();
		info[2].setFont(new Font(auxFont3.getFontName(), auxFont3.getStyle(), 15));
		info[2].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[2].setBounds(20, 60, 300, 25);
		add(info[2]);
				
				
		//Combox hora
		productos = new JComboBox();
		productos.setEditable(false);
		productos.addItem("--Vacio--");
		setAgregarItem();
		productos.setBounds(110, 65, 100, 20);
		add(productos);
		//Busqueda de combox
		ComboBoxSearchable searchable = new ComboBoxSearchable(productos);
		
		
		//Boton buscar
		bot_agregar = new JButton("Generar");
		bot_agregar.setToolTipText("Genera un consolidado general");
		bot_agregar.addActionListener(this);
		add(bot_agregar);
		bot_agregar.setBounds(280, 65, 85, 25);
		
		
		//Texto Codigo
		info[3] = new JLabel("Consolidado por casa de Importación: ");
		Font auxFont4=info[3].getFont();
		info[3].setFont(new Font(auxFont4.getFontName(), auxFont4.getStyle(), 18));
		info[3].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[3].setBounds(20, 120, 400, 25);
		add(info[3]);
		
		//Texto Codigo
		info[4] = new JLabel("Empresa: ");
		Font auxFont5=info[4].getFont();
		info[4].setFont(new Font(auxFont5.getFontName(), auxFont5.getStyle(), 15));
		info[4].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[4].setBounds(20, 165, 300, 25);
		add(info[4]);
		
		
		//Combox casa de importacion
		casadeimportacion = new JComboBox();
		casadeimportacion.setEditable(false);
		casadeimportacion.addItem("--Vacio--");
		setAgregarItem2();
		casadeimportacion.setBounds(110, 165, 140, 20);
		add(casadeimportacion);
		//Busqueda de combox
		ComboBoxSearchable searchable1 = new ComboBoxSearchable(casadeimportacion);
		
		//Boton buscar
		bot_casa = new JButton("Generar * Emp");
		bot_casa.setToolTipText("Genera un consolidado por empresa de importacion");
		bot_casa.addActionListener(this);
		add(bot_casa);
		bot_casa.setBounds(280, 165, 135, 25);
		
	}
	
	public void setAgregarItem()
	{
		try
		{
			ArrayList<String> verifi = new ArrayList<String>();
			ctrl.setLlenarFechasConsolidado();
			verifi=ctrl.getLlenarCombox2();
			for (String producto : verifi) 
			{productos.addItem(producto);}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
		
	}
	
	public void setAgregarItem2()
	{
		try
		{
			ArrayList<String> verifi = new ArrayList<String>();
			ctrl.setLlenarEmpresas();
			verifi=ctrl.getLlenarCombox3();
			for (String producto : verifi) 
			{casadeimportacion.addItem(producto);}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		Object btnpuch = e.getSource();
		if(btnpuch==bot_agregar)
		{
			System.out.println("General");
			if(!((String)productos.getSelectedItem()).equals("--Vacio--"))
			{
				String fecha="";
				fecha=(String) productos.getSelectedItem();
				ctrl.setCrearConsolidado(fecha);
				productos.setSelectedItem("--Vacio--");
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Elija una fecha","Alerta",0);
			}
		}
		else
		{
			System.out.println("Empresa");
			if(!((String)productos.getSelectedItem()).equals("--Vacio--")&&!((String)casadeimportacion.getSelectedItem()).equals("--Vacio--"))
			{
				String fecha="", empresa="";
				fecha=(String) productos.getSelectedItem();
				empresa=(String) casadeimportacion.getSelectedItem();
				ctrl.setCrearConsolidado(fecha,empresa);
				productos.setSelectedItem("--Vacio--");
				casadeimportacion.setSelectedItem("--Vacio--");
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Elija una fecha y una empresa","Alerta",0);
			}
		}
		
	}
	

}
