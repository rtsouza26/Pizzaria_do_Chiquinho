package conexaoBD;

/**Classe para a conexão do classe funcionários com o banco de dados, onde serão contidos, valores e métodos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */

import java.sql.*;
import java.util.logging.*;

public class FuncionariosBD {

	public boolean checkLogin( String login, String senha){
		boolean check = false;
		PreparedStatement stmp = null;
		ResultSet rs = null;
		Connection con = null;
		
		con = ConexaoBD.getConnection();
		
		
		try {
			stmp = con.prepareStatement("SELECT * FROM funcionarios WHERE login = ? AND senha = ? ");
			stmp.setString(1, login);
			stmp.setString(2, senha);
			
			
			rs = stmp.executeQuery();
			
			if(rs.next()){
				check = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConexaoBD.closeConnection(con, stmp, rs);
		}
		return check;
	}
	
}
