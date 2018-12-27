package InterfazApp;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import interfazCliente.PanelTabla_c;
import interfazProducto.PanelTabla_p;
import interfazVendedor.PanelTabla_v;

public class EventoVentana extends WindowAdapter{

public void windowClosing(WindowEvent e) {

	PanelPrin ventana = new PanelPrin();
	PanelTabla_p hilo= new PanelTabla_p();
	PanelTabla_c hilo2= new PanelTabla_c();
	PanelTabla_v hilo3= new PanelTabla_v();
	hilo.setpararHilo();
	hilo2.setpararHilo();
	hilo3.setpararHilo();
	ventana.setVentana(0);
	//System.out.println("Cerrada");
			
}
}
