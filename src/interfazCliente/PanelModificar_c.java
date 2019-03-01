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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controlador.Controlador;
import Mundo.Cliente;
import Mundo.Producto;

public class PanelModificar_c extends JPanel implements ActionListener{

	private Controlador ctrl;
	private JLabel info[]= new JLabel[10];
	private JButton bot_bus,bot_guar;
	private JTextField intro[] = new JTextField[8];
	private static JComboBox combo;
	 PanelModificar_c(Controlador ctrl) 
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
		
		//Texto Codigo
		info[2] = new JLabel("Num Ruta: ");
		Font auxFont2=info[2].getFont();
		info[2].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[2].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[2].setBounds(20, 80, 300, 25);
		add(info[2]);
		
		//Texto para codigo
		intro[1] = new JTextField(100);
		intro[1].setBounds(100, 80, 70, 20);
		add(intro[1]);
			
		//Texto nombre
		info[3] = new JLabel("Nombre razon social: ");
		Font auxFont3=info[3].getFont();
		info[3].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[3].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[3].setBounds(180, 80, 300, 25);
		add(info[3]);
		
		//Texto para nombre
		intro[2] = new JTextField(100);
		intro[2].setBounds(340, 80, 180, 20);
		add(intro[2]);
		
		//Texto precio
		info[4] = new JLabel("Nombre C: ");
		Font auxFont4=info[4].getFont();
		info[4].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[4].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[4].setBounds(540, 80, 300, 25);
		add(info[4]);
		
		//Texto para precio
		intro[3] = new JTextField(99);
		intro[3].setBounds(630, 80, 120, 20);
		add(intro[3]);
		
		//Texto Linea producto
		info[5] = new JLabel("Apellido C: ");
		Font auxFont5=info[5].getFont();
		info[5].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[5].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[5].setBounds(110, 120, 300, 25);
		add(info[5]);
		
		//Texto para Linea producto
			
		intro[4] = new JTextField(100);
		intro[4].setBounds(200, 120, 120, 20);
		add(intro[4]);
		
		//Texto Casa de exportacion
		info[6] = new JLabel("Dirección: ");
		Font auxFont6=info[6].getFont();
		info[6].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[6].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[6].setBounds(330, 120, 300, 25);
		add(info[6]);
		
		//Texto Casa de exportacion
		intro[5] = new JTextField(100);
		intro[5].setBounds(420, 120, 90, 20);
		add(intro[5]);
		
		//Texto Casa de exportacion
		info[7] = new JLabel("Telefono: ");
		Font auxFont7=info[7].getFont();
		info[7].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[7].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[7].setBounds(540, 120, 300, 25);
		add(info[7]);
		
		//Texto Casa de exportacion
		intro[6] = new JTextField(100);
		intro[6].setBounds(620, 120, 90, 20);
		add(intro[6]);
		
		//Texto Casa de exportacion
		info[8] = new JLabel("Correo e: ");
		Font auxFont8=info[8].getFont();
		info[8].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[8].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[8].setBounds(520, 35, 300, 25);
		add(info[8]);
		
		//Texto Casa de exportacion
		intro[7] = new JTextField(100);
		intro[7].setBounds(600, 35, 160, 20);
		add(intro[7]);
		
		//Texto Casa de exportacion
		info[9] = new JLabel("Dia de atención: ");
		Font auxFont9=info[9].getFont();
		info[9].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[9].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[9].setBounds(270, 35, 300, 25);
		add(info[9]);
		
		combo = new JComboBox();
		combo.setEditable(false);
		combo.addItem("-Ninguno-");
		combo.addItem("Lunes");
		combo.addItem("Martes");
		combo.addItem("Miercoles");
		combo.addItem("Jueves");
		combo.addItem("Viernes");
		combo.addItem("Sabado");
		combo.setBounds(390,35,100,20);
		add(combo);
			
		//Boton Generar
		bot_guar = new JButton("Guardar");
		bot_guar.addActionListener(this);
		add(bot_guar);
		bot_guar.setBounds(20, 120, 80, 25);
		
		
	
	 }

	public void actionPerformed(ActionEvent e) 
	{
		Object btnpuch = e.getSource();
		String id,nom_r,nom_c,apel_c,dire_c,tel,correo,dia;
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
				ResultSet miresultset = mistatement.executeQuery("select * from cliente");
				ArrayList<Cliente> verifi = new ArrayList<Cliente>();
				//Recorrer el resulset
				while(miresultset.next())
				{
					verifi.add(new Cliente(miresultset.getInt("Id_Cli"),miresultset.getString("nom_razon_social"),miresultset.getString("nom_Cli"),miresultset.getString("apell_CLie"),miresultset.getString("direccion"),miresultset.getString("telefono"),miresultset.getString("correo_e"),miresultset.getString("dia_atencion"),miresultset.getInt("num_ruta")));
					
				}
				
				if(!intro[0].getText().equals(""))
				{	int con=0;
					boolean very= true;
					for (Cliente cliente : verifi) 
					{
						con++;
						if(cliente.getId_c()==Integer.parseInt(intro[0].getText()))
						{
							very=false;
							//System.out.println(producto.getCod_p()+" "+producto.getNom_p()+" "+producto.getPre_p());
							intro[1].setText(String.valueOf(cliente.getNum_ruta()));
							intro[2].setText(cliente.getNom_r());
							intro[3].setText(cliente.getNom_c());
							intro[4].setText(cliente.getApel_c());
							intro[5].setText(cliente.getDire_c());
							intro[6].setText(cliente.getTele());
							intro[7].setText(cliente.getCorreo());
							combo.setSelectedItem(cliente.getDia());
							intro[0].setEnabled(false);
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
			id=intro[1].getText();
			nom_r=intro[2].getText();
			nom_c=intro[3].getText();
			apel_c=intro[4].getText();
			dire_c=intro[5].getText();
			tel=intro[6].getText();
			correo=intro[7].getText();
			dia=(String) combo.getSelectedItem();
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
					//String instruccion_sql = "delete from cliente where Id_Cli="+aux;
					//mistatement.executeUpdate(instruccion_sql);
					String instruccion_sql1 ="UPDATE cliente "
							+ "SET nom_razon_social='"+nom_r+"', nom_Cli='"+nom_c+"',apell_CLie='"+apel_c+
							"',direccion='"+dire_c+"',telefono='"+tel+"',correo_e='"+correo+"',dia_atencion='"
									+ ""+dia+"',num_ruta="+id+" WHERE Id_Cli="+aux;
					mistatement.executeUpdate(instruccion_sql1);
					JOptionPane.showMessageDialog(this,"El producto se modifico correctamente","Atención",1);
					intro[0].setEnabled(true);
					intro[1].setText("");
					intro[2].setText("");
					intro[3].setText("");
					intro[4].setText("");
					intro[0].setText("");
					intro[5].setText("");
					intro[6].setText("");
					combo.setSelectedItem("-Ninguno-");
					
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
