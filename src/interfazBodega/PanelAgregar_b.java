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
	
	
	private Controlador ctrl;
	private JLabel info[]= new JLabel[11];
	private JButton bot_cre;
	private JTextField intro[] = new JTextField[7];
	
	public PanelAgregar_b(Controlador ctrl)
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
		
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		
	}

}
