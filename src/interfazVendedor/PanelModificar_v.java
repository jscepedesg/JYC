package interfazVendedor;

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
import Mundo.Producto;
import Mundo.Vendedor;

public class PanelModificar_v extends JPanel implements ActionListener{

	private Controlador ctrl;
	private JLabel info[]= new JLabel[8];
	private JButton bot_bus,bot_guar;
	private JTextField intro[] = new JTextField[7];
	 PanelModificar_v(Controlador ctrl) 
	 {
		 setBackground(new Color(202,255,79));
		 setLayout( null );
		 //Integar controlador
		 this.ctrl=ctrl;
		 
		//Texto Modifcar
		info[0] = new JLabel("Modificar: ");
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
		intro[0] = new JTextField(100);
		intro[0].setBounds(80, 40, 70, 20);
		add(intro[0]);
		
		//Boton buscar
		bot_bus = new JButton("Buscar");
		bot_bus.addActionListener(this);
		add(bot_bus);
		bot_bus.setBounds(180, 40, 75, 25);
		
		//Texto codigo
		info[2] = new JLabel("Id vendedor: ");
		Font auxFont2=info[2].getFont();
		info[2].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[2].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[2].setBounds(20, 80, 300, 25);
		add(info[2]);
		
		//Texto para codigo
		intro[1] = new JTextField(100);
		intro[1].setBounds(120, 80, 70, 20);
		add(intro[1]);
		
		//Texto nombre
		info[3] = new JLabel("Nombre: ");
		Font auxFont4=info[2].getFont();
		info[3].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[3].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[3].setBounds(200, 80, 300, 25);
		add(info[3]);
		
		//Texto para nombre
		intro[2] = new JTextField(100);
		intro[2].setBounds(270, 80, 160, 20);
		add(intro[2]);
		
		//Texto precio
		info[4] = new JLabel("Apellido: ");
		Font auxFont5=info[4].getFont();
		info[4].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[4].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[4].setBounds(440, 80, 300, 25);
		add(info[4]);
				
		//Texto para precio
		intro[3] = new JTextField(99);
		intro[3].setBounds(510, 80, 160, 20);
		add(intro[3]);
		
		//Texto linea producto
		info[5] = new JLabel("Ruta: ");
		Font auxFont6=info[5].getFont();
		info[5].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[5].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[5].setBounds(120, 115, 300, 25);
		add(info[5]);
						
		//Texto linea producto
		intro[4] = new JTextField(99);
		intro[4].setBounds(170, 115, 90, 20);
		add(intro[4]);
		
		//Texto Casa de exportacion
		info[6] = new JLabel("Telefono: ");
		Font auxFont7=info[6].getFont();
		info[6].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[6].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[6].setBounds(280, 115, 300, 25);
		add(info[6]);
		
		//Texto Casa de exportacion
		intro[5] = new JTextField(100);
		intro[5].setBounds(360, 115, 90, 20);
		add(intro[5]);
		
		//Texto Casa de exportacion
		info[7] = new JLabel("Correo e: ");
		Font auxFont8=info[7].getFont();
		info[7].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[7].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[7].setBounds(460, 115, 300, 25);
		add(info[7]);
										
		//Texto Casa de exportacion
		intro[6] = new JTextField(100);
		intro[6].setBounds(540, 115, 200, 20);
		add(intro[6]);
		
		//Boton Generar
		bot_guar = new JButton("Guardar");
		bot_guar.addActionListener(this);
		add(bot_guar);
		bot_guar.setBounds(20, 120, 80, 25);
		
		
	
	 }

	public void actionPerformed(ActionEvent e) 
	{
		Object btnpuch = e.getSource();
		String cod1,nom,apel,ruta,tele,correo;
		if(btnpuch==bot_bus)
		{
			String cod= intro[0].getText();
			try
			{
				//1. Crear conexion 
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
				//2.Crear objecto Statement
				Statement mistatement = conexion.createStatement();
				//Ejecutar Sql
				ResultSet miresultset = mistatement.executeQuery("select * from vendedor");
				ArrayList<Vendedor> verifi = new ArrayList<Vendedor>();
				//Recorrer el resulset
				while(miresultset.next())
				{
					verifi.add(new Vendedor(miresultset.getInt("Id_Ven"),miresultset.getString("nom_Ven"),miresultset.getString("apell_Ven"),miresultset.getString("Ruta_Ven"),miresultset.getString("telefono_Ven"),miresultset.getString("correo_e")));
					
				}
				
				if(!intro[0].getText().equals(""))
				{	int con=0;
					boolean very= true;
					for (Vendedor producto : verifi) 
					{
						con++;
						if(producto.getId()==Integer.parseInt(intro[0].getText()))
						{
							very=false;
							//System.out.println(producto.getCod_p()+" "+producto.getNom_p()+" "+producto.getPre_p());
							intro[1].setText(String.valueOf(producto.getId()));
							intro[2].setText(producto.getNom());
							intro[3].setText(producto.getApel());
							intro[4].setText(producto.getRuta());
							intro[5].setText(producto.getTele());
							intro[6].setText(producto.getCorreo());
						}
						if(con>=verifi.size()&&very==true)
						{
							JOptionPane.showMessageDialog(this,"El vendedor no se encuentra","Atencion",2);
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
			cod1=intro[1].getText();
			nom=intro[2].getText();
			apel=intro[3].getText();
			ruta=intro[4].getText();
			tele=intro[5].getText();
			correo=intro[6].getText();
			try
			{
				//1. Crear conexion 
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jyc","root","");
				//2.Crear objecto Statement
				Statement mistatement = conexion.createStatement();
				//Eliminar valores
				if(!intro[0].getText().equals(""))
				{	
					String aux=intro[0].getText();
					String instruccion_sql = "delete from vendedor where Id_Ven="+aux;
					mistatement.executeUpdate(instruccion_sql);
					String instruccion_sql1 = "insert into vendedor values ("+cod1+",'"+nom+"','"+apel+"','"+ruta+"',"+"'"+tele+"','"+correo+"')";					mistatement.executeUpdate(instruccion_sql1);
					JOptionPane.showMessageDialog(this,"El vendedor se modifico correctamente","Atención",1);
					intro[1].setText("");
					intro[2].setText("");
					intro[3].setText("");
					intro[4].setText("");
					intro[5].setText("");
					intro[6].setText("");
					intro[0].setText("");
					
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
