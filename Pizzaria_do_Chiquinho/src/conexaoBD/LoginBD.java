package conexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import principal.Funcionarios;

public class LoginBD {
	
	private PreparedStatement testelogin = null;
	private ResultSet rs = null;
	private Connection con = null;
	private Funcionarios check = null;
	
	public LoginBD(){
		con = ConexaoBD.getConnection();
		try {
			String sql = "SELECT * FROM funcionarios WHERE login =? AND senha =?" ;
			testelogin = con.prepareStatement(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Funcionarios checkLogin( String login, String senha){

		try {
			
			testelogin.setString(1, login);
			testelogin.setString(2, senha);
			rs = testelogin.executeQuery();
			
			if(rs  != null){
				check = new Funcionarios();
				rs.next();
				check.setCodigo(rs.getInt("cod"));
				check.setTipo(rs.getString("tipo"));
					
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConexaoBD.closeConnection(con, rs);
		}
		return check;
	}
	

}
