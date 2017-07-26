package negocio.exception;

public class ClienteInexistenteErro extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClienteInexistenteErro(){
		
		super("Cliente não existe!");
	}

}
