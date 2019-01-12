package interfazBodega;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class PanelModificar_b extends JPanel implements ActionListener{

	
	private static Controlador ctrl;
	private JLabel info[]= new JLabel[4];
	private JTextField intro;
	private static JComboBox productos;
	private JButton bot_agregar;
	
	public PanelModificar_b(Controlador ctrl)
	{
		setBackground(new Color(202,255,79));
		setLayout( null );
		setVisible(false);
		
		//Integar controlador
		 this.ctrl=ctrl;
		
		//Texto Agregar
		info[0] = new JLabel("Modificar: ");
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
		
		//Texto Codigo
		info[2] = new JLabel("Cantidad: ");
		Font auxFont4=info[2].getFont();
		info[2].setFont(new Font(auxFont2.getFontName(), auxFont2.getStyle(), 15));
		info[2].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[2].setBounds(390, 60, 300, 25);
		add(info[2]);
		
		//Texto Casa de exportacion
		intro = new JTextField(10);
		intro.setBounds(470, 65, 40, 20);
		add(intro);
		
		//Texto Codigo
		info[3] = new JLabel("UNIDAD");
		Font auxFont5=info[3].getFont();
		info[3].setFont(new Font(auxFont2.getFontName(), auxFont2.getStyle(), 10));
		info[3].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[3].setBounds(510, 60, 300, 25);
		add(info[3]);
		
		//Boton buscar
		bot_agregar = new JButton("Guardar");
		bot_agregar.addActionListener(this);
		add(bot_agregar);
		bot_agregar.setBounds(580, 65, 85, 25);
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

	
	public void actionPerformed(ActionEvent e) 
	{
		
		try
		{
			//1. Crear conexion 
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
			Statement mistatement = conexion.createStatement();
			//Eliminar valores
			if(!intro.getText().equals("")&&!((String)productos.getSelectedItem()).equals("--Vacio--"))
			{	
				int aux=Integer.parseInt(intro.getText());
				String nombre=(String) productos.getSelectedItem();
				//2. Preparar la consulta
				String consulta = "UPDATE bodega SET cantidad = "+aux+" WHERE Id_Pro2 = (SELECT p.Id_Pro  FROM producto p WHERE p.nom_Pro = '"+nombre+"')";
				mistatement.executeUpdate(consulta);
				JOptionPane.showMessageDialog(this,"El producto se modifico correctamente","Atención",1);
				intro.setText("");
				productos.setSelectedItem("--Vacio--");
				
				
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Hay campos vacios","Alerta",0);
			}
		    conexion.close();
		}
		catch(Exception r)
		{
			JOptionPane.showMessageDialog(this,"Hubo un erro con la base de datos","Alerta",0);
			System.out.println(r.getMessage());
		}
	}
	
	public static void  setActualizarItems()
	{
		productos.removeAllItems();
		setAgregarItem();
	}
}
