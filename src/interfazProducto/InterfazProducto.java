package interfazProducto;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Controlador.Controlador;
import InterfazApp.EventoVentana;
import Util.Util;


public class InterfazProducto extends JFrame{
			
	private PanelAgregar_p pnlAgregar_p;
	private PanelModificar_p pnlModificar_p;
	private PanelEliminar_p pnlEliminar_p;
	private PanelTabla_p pnlTabla_p;
	// Atributo controlador
		Controlador ctrl;
		
		
	public InterfazProducto(Controlador ctrl)
	{
		setTitle("Producto");
		getContentPane( ).setLayout( null );
		//Color JFrame
		this.getContentPane().setBackground(Color.GRAY.darker().darker().darker());
		
		//Icono JFrame
		Toolkit mipantalla=  Toolkit.getDefaultToolkit();
		Image MiIcono=mipantalla.getImage("imagenes/Icon.png");//Icono
		setIconImage(MiIcono);
		
		// Integra el Controlador. 
		this.ctrl=ctrl;
		
		// Instancia los paneles 
		pnlAgregar_p = new PanelAgregar_p(ctrl);
		pnlAgregar_p.setBounds(10, 10, 775,160);
		pnlModificar_p = new PanelModificar_p (ctrl);
		pnlModificar_p.setBounds(10, 180, 775, 150);
		pnlEliminar_p = new PanelEliminar_p(ctrl);
		pnlEliminar_p.setBounds(10, 340, 775, 150);
		pnlTabla_p = new PanelTabla_p(ctrl);
		pnlTabla_p.setBounds(10, 500, 775, 180);
		
		// Organizar el panel principal. 
	    getContentPane( ).add( pnlAgregar_p);
	    getContentPane( ).add( pnlModificar_p);
	    getContentPane( ).add( pnlEliminar_p);
	    getContentPane( ).add( pnlTabla_p);
	    
	    // Conecta objetos al controlador.
	    ctrl.conectar(pnlAgregar_p, pnlModificar_p, pnlEliminar_p,pnlTabla_p);
	    
	    // Propiedades de la interfaz.
	    setSize(800,720);
	    setResizable(false);
	    
	    //  Centrar ventana.
	    Util.centrarVentana(this);
	    
	    EventoVentana oyenteventana = new  EventoVentana();
		addWindowListener(oyenteventana);
	}

}
