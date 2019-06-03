package interfazFacturacion;

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

public class PanelPrincipalFactura extends JPanel implements ActionListener
{
	
	private Controlador ctrl;
	private JLabel info[]= new JLabel[5];
	private JComboBox productos;
	private JButton bot_agregar;
	
	
	public PanelPrincipalFactura(Controlador ctrl) 
	{
		setBackground(new Color(202,255,79));
		setLayout( null );
		//Integar controlador
		this.ctrl=ctrl;
		
		//Texto Consolidado
		info[0] = new JLabel("Facturación");
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
		bot_agregar.setToolTipText("Genera las facturas por fecha");
		bot_agregar.addActionListener(this);
		add(bot_agregar);
		bot_agregar.setBounds(280, 65, 85, 25);
		
		
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
				ctrl.setCrearFacturacion(fecha);
				productos.setSelectedItem("--Vacio--");
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Elija una fecha","Alerta",0);
			}
		}
		
	}
	

}
