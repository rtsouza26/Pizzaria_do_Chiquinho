package negocio.exception;

public class LoginJaExiste extends Exception {
	private static final long serialVersionUID = 1L;

	public LoginJaExiste() {
		super("O login informado já está sendo usado, tente outro");
		// TODO Auto-generated constructor stub
	}
}
