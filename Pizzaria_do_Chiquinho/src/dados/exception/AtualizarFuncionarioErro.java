package dados.exception;

public class AtualizarFuncionarioErro extends Exception{

	private static final long serialVersionUID = 1L;

	public AtualizarFuncionarioErro(){
		super("Erro ao atualizar funcionário");
	} 
}
