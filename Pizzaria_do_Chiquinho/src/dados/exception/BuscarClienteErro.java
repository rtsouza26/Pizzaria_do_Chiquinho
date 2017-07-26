package dados.exception;

public class BuscarClienteErro extends Exception{

	private static final long serialVersionUID = 1L;
	
	public BuscarClienteErro(){
		
		super("Cliente não encontrado!");
		
	}

}
