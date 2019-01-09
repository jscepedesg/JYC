package interfazBodega;

import java.awt.Color;

import javax.swing.JPanel;

import Controlador.Controlador;

public class PanelModificar_b extends JPanel{

	
	private Controlador ctrl;
	
	public PanelModificar_b(Controlador ctrl)
	{
		setBackground(new Color(255,0,0));
		setLayout( null );
		setVisible(false);
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
