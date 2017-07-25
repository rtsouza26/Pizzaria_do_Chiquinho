package dados.exception;

public class BuscarFuncionarioErro extends Exception{

	private static final long serialVersionUID = 1L;

	public BuscarFuncionarioErro(){
		super("Erro ao buscar funcionário");
	}
	
}
