package Mundo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;
import com.itextpdf.text.log.SysoCounter;


public class Servidor implements Runnable{

	private ServerSocket servidor;
	private Socket socket;
	private BufferedReader rd;
	private static InputStreamReader in;
	private String mensaje_texto;
	private Gson gson;
	
	
	
	public Servidor()
	{
		Thread hilo = new Thread(this);
		hilo.start();
	}
	
	public void run() {
		
		try
		{
			
			gson = new Gson();
			while(true)
			{
				servidor = new ServerSocket(5000);
				socket = servidor.accept();
				in = new InputStreamReader(socket.getInputStream());
				rd= new BufferedReader(in);
				mensaje_texto = rd.readLine();
				Vendedor jsonToJava;
				jsonToJava = gson.fromJson(mensaje_texto, Vendedor.class);
//	        	···········DETECTA ONLINE = detecta es ip··························
				InetAddress localizacion = socket.getInetAddress();
//				nombre red
				String IpRemota = localizacion.getHostAddress();
				in.close();
				rd.close();
				servidor.close();
				socket.close();
				
				System.out.println("Hola1: "+jsonToJava.getNom()+" "+ jsonToJava.getId()+" "+IpRemota);
				
				
				
			}
			
		}
		catch(Exception e)
		{
			
		}
		
	}

}
