package Mundo;

public class Vendedor {
	
	private int id;
	private String nom;
	private String apel;
	private String ruta;
	private String tele;
	private String correo;
	
	public Vendedor(int id,String nom,String apel,String ruta,String tele,String correo)
	{
		this.id=id;
		this.nom=nom;
		this.apel=apel;
		this.ruta=ruta;
		this.tele=tele;
		this.correo=correo;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getApel() {
		return apel;
	}

	public String getRuta() {
		return ruta;
	}

	public String getTele() {
		return tele;
	}

	public String getCorreo() {
		return correo;
	}
	
	

}
