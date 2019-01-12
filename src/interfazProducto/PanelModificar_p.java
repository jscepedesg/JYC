package interfazProducto;

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

public class PanelModificar_p extends JPanel implements ActionListener{

	private Controlador ctrl;
	private JLabel info[]= new JLabel[7];
	private JButton bot_bus,bot_guar;
	private JTextField intro[] = new JTextField[6];
	 PanelModificar_p(Controlador ctrl) 
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
		info[2] = new JLabel("Codigo: ");
		Font auxFont2=info[2].getFont();
		info[2].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[2].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[2].setBounds(20, 80, 300, 25);
		add(info[2]);
		
		//Texto para codigo
		intro[1] = new JTextField(100);
		intro[1].setBounds(80, 80, 70, 20);
		add(intro[1]);
		
		//Texto nombre
		info[3] = new JLabel("Nombre: ");
		Font auxFont4=info[2].getFont();
		info[3].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[3].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[3].setBounds(180, 80, 300, 25);
		add(info[3]);
		
		//Texto para nombre
		intro[2] = new JTextField(100);
		intro[2].setBounds(250, 80, 280, 20);
		add(intro[2]);
		
		//Texto precio
		info[4] = new JLabel("Valor sin iva: ");
		Font auxFont5=info[4].getFont();
		info[4].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[4].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[4].setBounds(560, 80, 300, 25);
		add(info[4]);
				
		//Texto para precio
		intro[3] = new JTextField(99);
		intro[3].setBounds(660, 80, 80, 20);
		add(intro[3]);
		
		//Texto linea producto
		info[5] = new JLabel("Linea Producto: ");
		Font auxFont6=info[5].getFont();
		info[5].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[5].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[5].setBounds(180, 115, 300, 25);
		add(info[5]);
						
		//Texto linea producto
		intro[4] = new JTextField(99);
		intro[4].setBounds(300, 115, 90, 20);
		add(intro[4]);
		
		//Texto Casa de exportacion
		info[6] = new JLabel("Casa de exportacion: ");
		Font auxFont7=info[6].getFont();
		info[6].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[6].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[6].setBounds(420, 115, 300, 25);
		add(info[6]);
		
		//Texto Casa de exportacion
		intro[5] = new JTextField(100);
		intro[5].setBounds(580, 115, 90, 20);
		add(intro[5]);
		
		//Boton Generar
		bot_guar = new JButton("Guardar");
		bot_guar.addActionListener(this);
		add(bot_guar);
		bot_guar.setBounds(20, 120, 80, 25);
		
		
	
	 }

	public void actionPerformed(ActionEvent e) 
	{
		Object btnpuch = e.getSource();
		String cod1,nom,pre,linea,casa;
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
				ResultSet miresultset = mistatement.executeQuery("select * from producto");
				ArrayList<Producto> verifi = new ArrayList<Producto>();
				//Recorrer el resulset
				while(miresultset.next())
				{
					verifi.add(new Producto(miresultset.getInt("Id_Pro"),miresultset.getString("nom_Pro"),miresultset.getInt("Valor_sin_iva"),miresultset.getString("Linea_pro"),miresultset.getString("Casa_de_Export")));
					
				}
				
				if(!intro[0].getText().equals(""))
				{	int con=0;
					boolean very= true;
					for (Producto producto : verifi) 
					{
						con++;
						if(producto.getCod_p()==Integer.parseInt(intro[0].getText()))
						{
							very=false;
							//System.out.println(producto.getCod_p()+" "+producto.getNom_p()+" "+producto.getPre_p());
							intro[1].setText(String.valueOf(producto.getCod_p()));
							intro[2].setText(producto.getNom_p());
							intro[3].setText(String.valueOf(producto.getPre_p()));
							intro[4].setText(producto.getLinea());
							intro[5].setText(producto.getCasa());
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
			cod1=intro[1].getText();
			nom=intro[2].getText();
			pre=intro[3].getText();
			linea=intro[4].getText();
			casa=intro[5].getText();
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
					String instruccion_sql = "delete from producto where Id_Pro="+aux;
					mistatement.executeUpdate(instruccion_sql);
					String instruccion_sql1 = "insert into producto values ("+cod1+",'"+nom+"',"+pre+",'"+linea+"',"+"'"+casa+"'"+")";
					mistatement.executeUpdate(instruccion_sql1);
					JOptionPane.showMessageDialog(this,"El producto se modifico correctamente","Atención",1);
					intro[1].setText("");
					intro[2].setText("");
					intro[3].setText("");
					intro[4].setText("");
					intro[5].setText("");
					intro[0].setText("");
					
				}
				else
				{
					JOptionPane.showMessageDialog(this,"No ha echo la busqueda","Alerta",0);
				}
			    conexion.close();
			}
			catch(Exception r)
			{
				JOptionPane.showMessageDialog(this,"Hubo un erro con la base de datos","Alerta",0);
				System.out.println(r.getMessage());
			}
		}
		
	}


}
