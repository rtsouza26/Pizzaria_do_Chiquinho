package negocio.exception;

public class ClienteExistenteErro extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClienteExistenteErro(){
		
		super("Cliente jpa existe!");
		
	}

}
