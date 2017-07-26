package dados.exception;

public class RemoverClienteErro extends Exception {

	private static final long serialVersionUID = 1L;
	
	public RemoverClienteErro(){
		
		super("Erro ao remover cliente!");
		
	}

}
