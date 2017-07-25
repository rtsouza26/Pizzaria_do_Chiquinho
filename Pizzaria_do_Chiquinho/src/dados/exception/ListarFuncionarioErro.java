package dados.exception;

public class ListarFuncionarioErro extends Exception{

	private static final long serialVersionUID = 1L;

	public ListarFuncionarioErro(){
		super("Erro ao listar funcionários");
	}
	
}
