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
import Mundo.Bodega;
import Mundo.Producto;
import Mundo.Vendedor;
import interfazCliente.PanelTabla_c;
import interfazVendedor.PanelTabla_v;

public class PanelEliminar_b extends JPanel implements ActionListener{

	private static Controlador ctrl;
	private JLabel info[]= new JLabel[3];
	private static JComboBox productos;
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
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			//2.Crear objecto Statement
			Statement mistatement = conexion.createStatement();
			//Eliminar valores
			if(!((String)productos.getSelectedItem()).equals("--Vacio--"))
				{	
				String nombre=(String) productos.getSelectedItem();
				String instruccion_sql = "delete from bodega where Id_Pro2 = (SELECT p.Id_Pro  FROM producto p WHERE p.nom_Pro = '"+nombre+"')";
				mistatement.executeUpdate(instruccion_sql);
				PanelTabla_b.setElimino();
				PanelModificar_b.setActualizarItems();
				productos.removeAllItems();
				setAgregarItem();
				productos.setSelectedItem("--Vacio--");
				JOptionPane.showMessageDialog(this,"El producto se elimino correctamente","Atención",1);
				
				
			}
			else
			{
				JOptionPane.showMessageDialog(this,"No ha echo la busqueda","Alerta",0);
			}
		    
		}
		catch(Exception r)
		{
			JOptionPane.showMessageDialog(this,"Hubo un erro con la base de datos","Alerta",0);
			System.out.println(r.getMessage());
		}
		
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
	
	public static void setAgregarItem()
	{
		try
		{
			ArrayList<Bodega> verifi = new ArrayList<Bodega>();
			ctrl.setLlenarComboxProducto1();
			verifi=ctrl.getLlenarCombox1();
			for (Bodega producto : verifi) 
			{productos.addItem(producto.getNombre_p());}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}
	
	public static void  setActualizarItems()
	{
		productos.removeAllItems();
		setAgregarItem();
	}

}
