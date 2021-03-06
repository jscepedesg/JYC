package interfazCliente;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controlador.Controlador;
import Mundo.Cliente;
import Mundo.Producto;

public class PanelEliminar_c extends JPanel implements ActionListener{

	private Controlador ctrl;
	private JLabel info[]= new JLabel[8];
	private JButton bot_bus,bot_guar;
	private JTextField introbus;
	
	PanelEliminar_c(Controlador ctrl)
	{
		setBackground(new Color(202,255,79));
		setLayout( null );
		//Integar controlador
		this.ctrl=ctrl;
		
		//Texto Modifcar
		info[0] = new JLabel("Eliminar: ");
		Font auxFont=info[0].getFont();
		info[0].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 20));
		info[0].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[0].setBounds(20, 10, 300, 25);
		add(info[0]);
				
		//Texto Buscar
		info[1] = new JLabel("Buscar: ");
		Font auxFont1=info[1].getFont();
		info[1].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[1].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[1].setBounds(20, 40, 300, 25);
		add(info[1]);
				
		//Texto para buscar
		introbus = new JTextField(100);
		introbus.setBounds(80, 40, 70, 20);
		add(introbus);
		
		//Boton buscar
		bot_bus = new JButton("Buscar");
		bot_bus.addActionListener(this);
		add(bot_bus);
		bot_bus.setBounds(180, 40, 75, 25);
		
		//Texto codigo
		info[2] = new JLabel("Id cliente: ");
		Font auxFont2=info[2].getFont();
		info[2].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[2].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[2].setBounds(20, 80, 300, 25);
		add(info[2]);
		
		//Texto codigo
		info[5] = new JLabel("");
		Font auxFont3=info[5].getFont();
		info[5].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[5].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[5].setBounds(95, 80, 300, 25);
		add(info[5]);
		
		//Texto nombre
		info[3] = new JLabel("Nombre razon social: ");
		Font auxFont4=info[2].getFont();
		info[3].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[3].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[3].setBounds(180, 80, 300, 25);
		add(info[3]);
		
		//Texto codigo
		info[6] = new JLabel("");
		Font auxFont6=info[6].getFont();
		info[6].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[6].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[6].setBounds(340, 80, 300,25);
		add(info[6]);
		
		//Texto precio
		info[4] = new JLabel("Nombre C: ");
		Font auxFont5=info[4].getFont();
		info[4].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[4].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[4].setBounds(500, 80, 300, 25);
		add(info[4]);
		
		//Texto codigo
		info[7] = new JLabel("");
		Font auxFont7=info[7].getFont();
		info[7].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[7].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[7].setBounds(580, 83, 200, 20);
		add(info[7]);
		
		//Boton Generar
		bot_guar = new JButton("Eliminar");
		bot_guar.addActionListener(this);
		add(bot_guar);
		bot_guar.setBounds(20, 120, 80, 25);
			
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		Object btnpuch = e.getSource();
		
		if(btnpuch==bot_bus)
		{
			String cod= introbus.getText();
			
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
				
				if(!introbus.getText().equals(""))
				{	int con=0;
				boolean very= true;
					for (Cliente producto : verifi) 
					{
						con++;
						if(producto.getId_c()==Integer.parseInt(introbus.getText()))
						{
							very=false;
							//System.out.println(producto.getCod_p()+" "+producto.getNom_p()+" "+producto.getPre_p());
							info[5].setText(String.valueOf(producto.getId_c()));
							info[6].setText(producto.getNom_r());
							info[7].setText(producto.getNom_c()+" "+producto.getApel_c());
						}
						if(con>=verifi.size()&&very==true)
						{
							JOptionPane.showMessageDialog(this,"El producto no se encuentra","Atencion",2);
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this,"No ingreso el codigo","Alerta",0);
				}
				
				conexion.close();
				verifi.clear();
				
			}
			catch(Exception g)
			{
				JOptionPane.showMessageDialog(this,"Hubo un erro con la base de datos","Alerta",0);
			}
		}
		else
		{
			try
			{
				//1. Crear conexion 
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
				//2.Crear objecto Statement
				Statement mistatement = conexion.createStatement();
				//Eliminar valores
				if(!introbus.getText().equals(""))
				{	
					String aux=introbus.getText();
					String instruccion_sql = "delete from cliente where Id_Cli="+aux;
					mistatement.executeUpdate(instruccion_sql);
					info[5].setText("");
					info[6].setText("");
					info[7].setText("");
					introbus.setText("");
					PanelTabla_c.setElimino();
					JOptionPane.showMessageDialog(this,"El producto se elimino correctamente","Atenci�n",1);
					
					
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
	}

}
