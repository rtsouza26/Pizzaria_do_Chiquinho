package negocio.exception;

public class FuncionarioExistente extends Exception{

	private static final long serialVersionUID = 1L;
	
	public FuncionarioExistente(){
		super("Funcionário já existe");
	}
}
