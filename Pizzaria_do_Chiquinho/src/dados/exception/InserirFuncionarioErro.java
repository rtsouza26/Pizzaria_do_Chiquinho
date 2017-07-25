package dados.exception;

public class InserirFuncionarioErro extends Exception{

	private static final long serialVersionUID = 1L;
	
	public InserirFuncionarioErro(){
		super("Erro ao inserir funcionário");
	}
	
}
