package interfazBodega;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;

import Controlador.Controlador;
import Mundo.Conexion;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class PanelOpcion_b extends JPanel implements ActionListener{
	
	private Controlador ctrl;
	private JLabel info[]= new JLabel[11];
	private JButton bot_cre;
	private JRadioButton boton1,boton2,boton3;
	private ButtonGroup grupo;
	private JButton bot_reporte;
	
	public PanelOpcion_b(Controlador ctrl)
	{
		setBackground(new Color(202,255,79));
		setLayout( null );
		//Integar controlador
		this.ctrl=ctrl;
		//Texto Clientes
		info[0] = new JLabel("Bodega");
		Font auxFont=info[0].getFont();
		info[0].setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));
		info[0].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[0].setBounds(320, 10, 300, 35);
		add(info[0]);
			
		//Texto J&C
		info[1] = new JLabel("J&C");
		Font auxFont1=info[1].getFont();
		info[1].setFont(new Font("Elephant", auxFont.getStyle(), 40));
		info[1].setForeground(Color.BLUE.darker().darker().darker().darker());
		info[1].setBounds(20, 10, 150, 30);
		add(info[1]);
		
		grupo = new ButtonGroup();
		
		boton1 = new JRadioButton("Agregar",true);
		boton1.setBounds(250,60,85,25);
		boton2 = new JRadioButton("Modificar",false);
		boton2.setBounds(350,60,85,25);
		boton3 = new JRadioButton("Eliminar",false);
		boton3.setBounds(450,60,85,25);
		
		 enventoradio evento = new  enventoradio();
		 boton1.addActionListener(evento);
		 boton2.addActionListener(evento);
		 boton3.addActionListener(evento);
		 
		 grupo.add(boton1);grupo.add(boton2);grupo.add(boton3);
		 
		 add(boton1);add(boton2);add(boton3);
		 
		//Boton buscar
		 bot_reporte = new JButton("Generar Reporte");
		 bot_reporte.addActionListener(this);
		add(bot_reporte);
		bot_reporte.setBounds(580, 60, 180, 25);
		 
		 
		
	}
	
	private class enventoradio implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==boton1)
			{
				System.out.println("pulso en 1");
				ctrl.setOpcionPanelBodega(1);
				
			}
			else if(e.getSource()==boton2)
			{
				System.out.println("pulso en 2");
				ctrl.setOpcionPanelBodega(2);
				
			}
			else if(e.getSource()==boton3)
			{
				System.out.println("pulso en 3");
				ctrl.setOpcionPanelBodega(3);
				
			}
		}
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		try
		{
			Conexion con = new Conexion();
			Connection conn= con.getConexion();
			JasperReport a = null;
			String path = "src\\reportes\\bodega.jasper";
			
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
