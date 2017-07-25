package dados.exception;

public class RemoverFuncionarioErro extends Exception{

	private static final long serialVersionUID = 1L;
	
	public RemoverFuncionarioErro(){
		super("Erro ao remover funcionário");
	}
}
