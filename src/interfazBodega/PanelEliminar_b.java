package interfazBodega;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jidesoft.swing.ComboBoxSearchable;

import Controlador.Controlador;
import Mundo.Producto;
import Mundo.Vendedor;
import interfazVendedor.PanelTabla_v;

public class PanelEliminar_b extends JPanel implements ActionListener{

	private Controlador ctrl;
	private JLabel info[]= new JLabel[3];
	private JTextField intro;
	private JComboBox productos;
	private JButton bot_agregar;
	
	public PanelEliminar_b(Controlador ctrl)
	{
		setBackground(new Color(202,255,79));
		setLayout( null );
		setVisible(false);
		//Integar controlador
		this.ctrl=ctrl;
		
		//Texto Agregar
		info[0] = new JLabel("Eliminar: ");
		Font auxFont2=info[0].getFont();
		info[0].setFont(new Font(auxFont2.getFontName(), auxFont2.getStyle(), 20));
		info[0].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[0].setBounds(20, 20, 300, 25);
		add(info[0]);
		
		//Texto Codigo
		info[1] = new JLabel("Producto: ");
		Font auxFont3=info[1].getFont();
		info[1].setFont(new Font(auxFont2.getFontName(), auxFont2.getStyle(), 15));
		info[1].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[1].setBounds(20, 60, 300, 25);
		add(info[1]);
		
				
		//Combox hora
		productos = new JComboBox();
		productos.setEditable(false);
		productos.addItem("--Vacio--");
		setAgregarItem();
		productos.setBounds(110, 65, 260, 20);
		add(productos);
		//Busqueda de combox
		ComboBoxSearchable searchable = new ComboBoxSearchable(productos);
		
		//Boton buscar
		bot_agregar = new JButton("Eliminar");
		bot_agregar.addActionListener(this);
		add(bot_agregar);
		bot_agregar.setBounds(480, 65, 85, 25);
			
	}	
	
	public void actionPerformed(ActionEvent e) 
	{
		
		
	}
	
	public void setEstado(boolean estado)
	{
		if(estado)
		{
			setVisible(true);
		}
		else
		{
			setVisible(false);
		}
	}
	
	public void setAgregarItem()
	{
		ArrayList<Producto> verifi = new ArrayList<Producto>();
		ctrl.setLlenarComboxProducto();
		verifi=ctrl.getLlenarCombox();
		for (Producto producto : verifi) 
		{productos.addItem(producto.getNom_p());}
		
	}

}
