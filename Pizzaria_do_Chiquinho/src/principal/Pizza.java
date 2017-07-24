package principal;

import java.sql.SQLException;

/**Classe para objetos do tipo PIzza, onde serão contidos, valores e métodos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */

public class Pizza extends Produto {
	public Pizza() throws SQLException, ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	private int peso;
	

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	

}
