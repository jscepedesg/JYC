package interfazBodega;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controlador.Controlador;

public class PanelAgregar_b extends JPanel implements ActionListener{
	
	
	public PanelAgregar_b(Controlador ctrl)
	{
		setBackground(new Color(202,255,79));
		setLayout( null );
		setVisible(true);
		//Integar controlador
		
		
		
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

}
