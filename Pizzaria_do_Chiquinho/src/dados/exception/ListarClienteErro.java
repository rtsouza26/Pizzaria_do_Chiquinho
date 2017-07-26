package dados.exception;

public class ListarClienteErro extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ListarClienteErro(){
		
		super("Erro ao listar funcionários!");
		
	}
}
