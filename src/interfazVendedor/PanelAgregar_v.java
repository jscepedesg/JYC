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

public class PanelAgregar_v extends JPanel implements ActionListener{

	private Controlador ctrl;
	private JLabel info[]= new JLabel[9];
	private JButton bot_cre;
	private JTextField intro[] = new JTextField[6];
	
	PanelAgregar_v(Controlador ctrl) 
	{
		setBackground(new Color(202,255,79));
		setLayout( null );
		//Integar controlador
		this.ctrl=ctrl;
		
		//Texto Clientes
		info[0] = new JLabel("Vendedor");
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
		info[3] = new JLabel("Id vendedor: ");
		Font auxFont3=info[3].getFont();
		info[3].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[3].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[3].setBounds(20, 90, 300, 25);
		add(info[3]);
		
		//Texto para codigo
		intro[0] = new JTextField(100);
		intro[0].setBounds(120, 90, 70, 20);
		add(intro[0]);
		
		//Texto nombre
		info[4] = new JLabel("Nombre: ");
		Font auxFont4=info[4].getFont();
		info[4].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[4].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[4].setBounds(200, 90, 300, 25);
		add(info[4]);
		
		//Texto para nombre
		intro[1] = new JTextField(100);
		intro[1].setBounds(270, 90, 160, 20);
		add(intro[1]);
		
		//Texto precio
		info[5] = new JLabel("Apellido: ");
		Font auxFont5=info[5].getFont();
		info[5].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[5].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[5].setBounds(440, 90, 300, 25);
		add(info[5]);
		
		//Texto para precio
		intro[2] = new JTextField(99);
		intro[2].setBounds(510, 90, 160, 20);
		add(intro[2]);
		
		//Texto Linea producto
		info[6] = new JLabel("Ruta: ");
		Font auxFont6=info[6].getFont();
		info[6].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[6].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[6].setBounds(120, 130, 300, 25);
		add(info[6]);
				
		//Texto para Linea producto
		intro[3] = new JTextField(100);
		intro[3].setBounds(170, 130, 90, 20);
		add(intro[3]);
		
		//Texto Casa de exportacion
		info[7] = new JLabel("Telefono: ");
		Font auxFont7=info[7].getFont();
		info[7].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[7].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[7].setBounds(280, 130, 300, 25);
		add(info[7]);
						
		//Texto Casa de exportacion
		intro[4] = new JTextField(100);
		intro[4].setBounds(360, 130, 90, 20);
		add(intro[4]);
		
		//Texto Casa de exportacion
		info[8] = new JLabel("Correo e: ");
		Font auxFont8=info[8].getFont();
		info[8].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 15));
		info[8].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[8].setBounds(460, 130, 300, 25);
		add(info[8]);
								
		//Texto Casa de exportacion
		intro[5] = new JTextField(100);
		intro[5].setBounds(540, 130, 200, 20);
		add(intro[5]);
				
		//Boton Generar
		bot_cre = new JButton("Crear");
		bot_cre.addActionListener(this);
		add(bot_cre);
		bot_cre.setBounds(20, 130, 75, 25);
		 
	}

	public void actionPerformed(ActionEvent e) 
	{
		String cod,nom,apel,ruta,tele,correo;
		if(!intro[0].getText().equals("") && !intro[1].getText().equals("")&&!intro[2].getText().equals("")
				&& !intro[3].getText().equals("")&& !intro[4].getText().equals("")&& !intro[5].getText().equals(""))
		{
			cod=intro[0].getText();
			nom=intro[1].getText();
			apel=intro[2].getText();
			ruta=intro[3].getText();
			tele=intro[4].getText();
			correo=intro[5].getText();
			ctrl.setCrearVendedor(cod, nom, apel, ruta,tele,correo);
			intro[1].setText("");
			intro[2].setText("");
			intro[3].setText("");
			intro[4].setText("");
			intro[5].setText("");
			intro[0].setText("");
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Ah� un espacio vacio","Alerta",0);
		}
	}

}
