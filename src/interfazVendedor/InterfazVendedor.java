package interfazVendedor;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Controlador.Controlador;
import InterfazApp.EventoVentana;
import Util.Util;


public class InterfazVendedor extends JFrame{
			
	private PanelAgregar_v pnlAgregar_v;
	private PanelModificar_v pnlModificar_v;
	private PanelEliminar_v pnlEliminar_v;
	private PanelTabla_v pnlTabla_v;
	// Atributo controlador
		Controlador ctrl;
		
		
	public InterfazVendedor(Controlador ctrl)
	{
		setTitle("Vendedor");
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
		pnlAgregar_v = new PanelAgregar_v(ctrl);
		pnlAgregar_v.setBounds(10, 10, 775,160);
		pnlModificar_v = new PanelModificar_v (ctrl);
		pnlModificar_v.setBounds(10, 180, 775, 150);
		pnlEliminar_v = new PanelEliminar_v(ctrl);
		pnlEliminar_v.setBounds(10, 340, 775, 150);
		pnlTabla_v = new PanelTabla_v(ctrl);
		pnlTabla_v.setBounds(10, 500, 775, 180);
		
		// Organizar el panel principal. 
	    getContentPane( ).add( pnlAgregar_v);
	    getContentPane( ).add( pnlModificar_v);
	    getContentPane( ).add( pnlEliminar_v);
	    getContentPane( ).add( pnlTabla_v);
	    
	    // Conecta objetos al controlador.
	    ctrl.conectar(pnlAgregar_v, pnlModificar_v, pnlEliminar_v,pnlTabla_v);
	    
	    // Propiedades de la interfaz.
	    setSize(800,720);
	    setResizable(false);
	    
	    //  Centrar ventana.
	    Util.centrarVentana(this);
	    
	    EventoVentana oyenteventana = new  EventoVentana();
		addWindowListener(oyenteventana);
	}
}
