package InterfazApp;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import Controlador.Controlador;
import Mundo.Servidor;
import interfazBodega.InterfazBodega;
import interfazCliente.InterfazCliente;
import interfazCuentaT.InterfazCuentaT;
import interfazProducto.InterfazProducto;
import interfazVendedor.InterfazVendedor;


public class InterfazApp extends JFrame{
	
	private PanelPrin pnlPrin;
	
	public InterfazApp()
	{
		setTitle("J&C");
		
		//Icono JFrame
		Toolkit mipantalla=  Toolkit.getDefaultToolkit();
		Image MiIcono=mipantalla.getImage("imagenes/Icon.png");//Icono
		setIconImage(MiIcono);
		
		// Propiedades de la interfaz.
	    setSize(300,200);
	    setResizable(false);
	    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
	    pnlPrin = new PanelPrin();
	 // Organizar el panel principal. 
	    add( pnlPrin);
	    
	    //Inicia el servidor
	    Servidor servidor = new Servidor();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Lectura de datos
		String usu="",con1="";
		{
			try {
				FileReader entrada = new FileReader("D:/J&C/Usuario de entrada/usuario.jyc");
				BufferedReader mibuffer = new BufferedReader(entrada);
				String linea="";
			
				while(linea!= null)
				{
					linea=mibuffer.readLine();
					if(linea!=null)
					{
					
						usu=linea;
					}
				
				}
				FileReader entrada1 = new FileReader("D:/J&C/Usuario de entrada/contraseña.jyc");
				BufferedReader mibuffer1 = new BufferedReader(entrada1);
				String linea1="";
			
				while(linea1!= null)
				{
					linea1=mibuffer1.readLine();
					if(linea1!=null)
					{
					
						con1=linea1;
					}
				
				}
				entrada.close();
				entrada1.close();
			
			}catch (IOException e) 
			{
				JOptionPane.showMessageDialog(null,"Error","Alerta",0);
			}
		}
		
		//Ingreso y verificacion de informacion
		{
			String user = JOptionPane.showInputDialog(null, "Usuario");
			String con="";
			JPanel panel = new JPanel();
			JLabel label = new JLabel("Ingrese la contraseña:");
			JPasswordField pass = new JPasswordField(10);
			panel.add(label);
			panel.add(pass);
			String[] options = new String[]{"OK", "Cancelar"};
			int option = JOptionPane.showOptionDialog(null, panel, "Contraseña",
		                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		                         null, options, options[1]);
			if(option == 0)
			{
				char[] password = pass.getPassword();
				con=new String(password);
			}
			else if(option == 1)
			{
				System.exit(0);
			}	
			boolean ban=true;
			while(ban)
			{
				if (usu.equals(user) && con1.equals(con)) 
				{
					JOptionPane.showMessageDialog(null, "login ok");
					InterfazApp ventana = new InterfazApp();
					ventana.setVisible(true);
					ban=false;
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "login failed");
					user = JOptionPane.showInputDialog(null, "user");
					option = JOptionPane.showOptionDialog(null, panel, "Contraseña",
				                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				                         null, options, options[1]);
					if(option == 0)
					{
						char[] password = pass.getPassword();
						con=new String(password);
					}
					else if(option == 1)
					{
						System.exit(0);
					}
				}
			}	
		}
	
	}

}
 class PanelPrin extends JPanel implements ActionListener
 {

	 private JButton clie,con,bodega,vendedor;
	 private JButton produc,tablat,facturacion,salir;
	 private static int auxframe;
	 PanelPrin()
	 {
		setBackground(new Color(198,226,255));
		setLayout( null );
		//Boton cliente
		clie = new JButton("Clientes");
		clie.addActionListener(this);
		add(clie);
		clie.setBounds(30, 25, 120, 20);
		clie.setToolTipText("Agrega, elimina o modifica clientes");
		//Boton producto
		produc = new JButton("Productos");
		produc.addActionListener(this);
		add(produc);
		produc.setBounds(150, 25, 120, 20);
		produc.setToolTipText("Agrega, elimina o modifica productos");
		//Boton consolidado
		con = new JButton("Consolidado");
		con.addActionListener(this);
		add(con);
		con.setBounds(30, 60, 120, 20);
		con.setToolTipText("Inventario de mercancia");
		auxframe =0;
		//Boton Tabla t
		tablat = new JButton("Contabilidad");
		tablat.addActionListener(this);
		add(tablat);
		tablat.setBounds(150, 60, 120, 20);
		auxframe =0;
		tablat.setToolTipText("Ayuda con el manejo de la contabilidad");
		//Boton facturacion
		facturacion = new JButton("Facturación");
		facturacion.addActionListener(this);
		add(facturacion);
		facturacion.setBounds(30, 95, 120, 20);
		auxframe =0;
		facturacion.setToolTipText("Realiza la facturación de las rutas");
		//Boton bodega
		bodega = new JButton("Bodega");
		bodega.addActionListener(this);
		add(bodega);
		bodega.setBounds(150, 95, 120, 20);
		auxframe =0;
		bodega.setToolTipText("Hace un inventario de la mercancia en bodega");
		//Boton vendedor
		vendedor = new JButton("Vendedor");
		vendedor.addActionListener(this);
		add(vendedor);
		vendedor.setBounds(30, 130, 120, 20);
		auxframe =0;
		vendedor.setToolTipText("Agrega, elimina o modifica vendedores");
		//Boton salir
		salir = new JButton("Salir");
		salir.addActionListener(this);
		add(salir);
		salir.setBounds(150, 130, 120, 20);
		auxframe =0;
		salir.setToolTipText("Salir de la aplicación");
		
	 }
	 
	public void actionPerformed(ActionEvent e) {
		Object btnpuch = e.getSource();
		if(btnpuch==clie)
		{
			System.out.println(auxframe);
			if(auxframe==0)
			{
				auxframe++;
				InterfazCliente agregar = new InterfazCliente(new Controlador());
				agregar.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(PanelPrin.this,"Ya hay una ventana abierta","Atención",0);
			}
				
		}
		else if(btnpuch==produc)
		{
			if(auxframe==0)
			{
				auxframe++;
				InterfazProducto agregar = new InterfazProducto(new Controlador());
				agregar.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(PanelPrin.this,"Ya hay una ventana abierta","Atención",0);
			}
		}
		else if(btnpuch==con)
		{
			System.out.println("Abrio consolidado");
		}
		else if(btnpuch==tablat)
		{
			if(auxframe==0)
			{
				auxframe++;
				InterfazCuentaT agregar = new InterfazCuentaT(new Controlador());
				agregar.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(PanelPrin.this,"Ya hay una ventana abierta","Atención",0);
			}
		}
		else if(btnpuch==facturacion)
		{
			System.out.println("Abrio Facturación");
		}
		else if(btnpuch==bodega)
		{
			if(auxframe==0)
			{
				auxframe++;
				InterfazBodega agregar = new InterfazBodega(new Controlador());
				agregar.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(PanelPrin.this,"Ya hay una ventana abierta","Atención",0);
			}
		}
		else if(btnpuch==vendedor)
		{
			if(auxframe==0)
			{
				auxframe++;
				InterfazVendedor agregar = new InterfazVendedor(new Controlador());
				agregar.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(PanelPrin.this,"Ya hay una ventana abierta","Atención",0);
			}
		}
		else if(btnpuch==salir)
		{
			System.exit(0);
		}
	}
	public void setVentana(int ven)
	{
		auxframe=ven;
	}
	 
 }