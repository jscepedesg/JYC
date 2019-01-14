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
import Mundo.Conexion;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class PanelAgregar_c extends JPanel implements ActionListener{

	private Controlador ctrl;
	private JLabel info[]= new JLabel[11];
	private JButton bot_cre;
	private JTextField intro[] = new JTextField[7];
	private static JComboBox combo;
	private JButton bot_reporte;
	
	PanelAgregar_c(Controlador ctrl) 
	{
		setBackground(new Color(202,255,79));
		setLayout( null );
		//Integar controlador
		this.ctrl=ctrl;
		
		//Texto Clientes
		info[0] = new JLabel("Clientes");
		Font auxFont=info[0].getFont();
		info[0].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));
		info[0].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[0].setBounds(320, 10, 300, 25);
		add(info[0]);
	
		//Texto J&C
		info[1] = new JLabel("J&C");
		Font auxFont1=info[1].getFont();
		info[1].setFont(new Font("Elephant", auxFont.getStyle(), 40));
		info[1].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[1].setBounds(20, 10, 150, 30);
		add(info[1]);
		
		//Texto Agregar
		info[2] = new JLabel("Agregar: ");
		Font auxFont2=info[2].getFont();
		info[2].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 20));
		info[2].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[2].setBounds(20, 50, 300, 25);
		add(info[2]);
		
		//Texto Codigo
		info[3] = new JLabel("Id cliente: ");
		Font auxFont3=info[3].getFont();
		info[3].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[3].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[3].setBounds(20, 90, 300, 25);
		add(info[3]);
		
		//Texto para codigo
		intro[0] = new JTextField(100);
		intro[0].setBounds(100, 90, 70, 20);
		add(intro[0]);
		
		//Texto nombre
		info[4] = new JLabel("Nombre razon social: ");
		Font auxFont4=info[4].getFont();
		info[4].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[4].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[4].setBounds(180, 90, 300, 25);
		add(info[4]);
		
		//Texto para nombre
		intro[1] = new JTextField(100);
		intro[1].setBounds(340, 90, 180, 20);
		add(intro[1]);
		
		//Texto precio
		info[5] = new JLabel("Nombre C: ");
		Font auxFont5=info[5].getFont();
		info[5].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[5].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[5].setBounds(540, 90, 300, 25);
		add(info[5]);
		
		//Texto para precio
		intro[2] = new JTextField(99);
		intro[2].setBounds(630, 90, 120, 20);
		add(intro[2]);
		
		//Texto Linea producto
		info[6] = new JLabel("Apellido C: ");
		Font auxFont6=info[6].getFont();
		info[6].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[6].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[6].setBounds(110, 130, 300, 25);
		add(info[6]);
				
		//Texto para Linea producto
		intro[3] = new JTextField(100);
		intro[3].setBounds(200, 130, 120, 20);
		add(intro[3]);
		
		//Texto Casa de exportacion
		info[7] = new JLabel("Dirección: ");
		Font auxFont7=info[7].getFont();
		info[7].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[7].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[7].setBounds(330, 130, 300, 25);
		add(info[7]);
						
		//Texto Casa de exportacion
		intro[4] = new JTextField(100);
		intro[4].setBounds(420, 130, 90, 20);
		add(intro[4]);
		
		//Texto Casa de exportacion
		info[8] = new JLabel("Telefono: ");
		Font auxFont8=info[8].getFont();
		info[8].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[8].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[8].setBounds(540, 130, 300, 25);
		add(info[8]);
								
		//Texto Casa de exportacion
		intro[5] = new JTextField(100);
		intro[5].setBounds(620, 130, 90, 20);
		add(intro[5]);
		
		//Texto Casa de exportacion
		info[9] = new JLabel("Correo e: ");
		Font auxFont9=info[9].getFont();
		info[9].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[9].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[9].setBounds(520, 50, 300, 25);
		add(info[9]);
										
		//Texto Casa de exportacion
		intro[6] = new JTextField(100);
		intro[6].setBounds(600, 50, 160, 20);
		add(intro[6]);
		
		//Texto Casa de exportacion
		info[10] = new JLabel("Dia de atención: ");
		Font auxFont10=info[10].getFont();
		info[10].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[10].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[10].setBounds(120, 50, 300, 25);
		add(info[10]);
		
		combo = new JComboBox();
		combo.setEditable(false);
		combo.addItem("-Ninguno-");
		combo.addItem("Lunes");
		combo.addItem("Martes");
		combo.addItem("Miercoles");
		combo.addItem("Jueves");
		combo.addItem("Viernes");
		combo.addItem("Sabado");
		combo.setBounds(240,50,100,20);
		add(combo);
				
		//Boton Generar
		bot_cre = new JButton("Crear");
		bot_cre.addActionListener(this);
		add(bot_cre);
		bot_cre.setBounds(20, 130, 75, 25);
		
		//Boton buscar
		bot_reporte = new JButton("Generar Reporte");
		bot_reporte.addActionListener(this);
		add(bot_reporte);
		bot_reporte.setBounds(580, 10, 180, 25);
		 
	}

	public void actionPerformed(ActionEvent e) 
	{
		Object btnpuch = e.getSource();
		String id,nom_r,nom_c,apel_c,dire_c,tel,correo,dia;
		if(btnpuch==bot_cre)
		{
			if(!intro[0].getText().equals("") && !intro[1].getText().equals("")&&!intro[2].getText().equals("")
					&& !intro[3].getText().equals("")&& !intro[4].getText().equals("")&& !intro[5].getText().equals("")&& !intro[6].getText().equals("")
					&&!((String)combo.getSelectedItem()).equals("-Ninguno-"))
			{
				id=intro[0].getText();
				nom_r=intro[1].getText();
				nom_c=intro[2].getText();
				apel_c=intro[3].getText();
				dire_c=intro[4].getText();
				tel=intro[5].getText();
				correo=intro[6].getText();
				dia=(String) combo.getSelectedItem();
				ctrl.setCrearCliente(id, nom_r, nom_c, apel_c, dire_c,tel,correo,dia);
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
				JOptionPane.showMessageDialog(this,"Ahí un espacio vacio","Alerta",0);
			}
		}
		else
		{
			try
			{
				Conexion con = new Conexion();
				Connection conn= con.getConexion();
				JasperReport a = null;
				String path = "src\\reportes\\cliente.jasper";
				
				a=(JasperReport) JRLoader.loadObjectFromFile(path);
				JasperPrint fillreport = JasperFillManager.fillReport(a,null, conn);
				JasperViewer jv = new JasperViewer(fillreport,false);
				jv.setVisible(true);
				jv.show();
				
			}
			catch(Exception r)
			{
				JOptionPane.showMessageDialog(this, r);
			}
		}
		
	}
	
}
