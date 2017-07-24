package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Codigo;

public abstract class PrepararSql {

	protected PreparedStatement inserir = null;
	protected PreparedStatement remover = null;
	protected PreparedStatement listar = null;
	protected PreparedStatement buscar = null;
	protected PreparedStatement atualizar = null;
	protected ResultSet rs = null;
	private Connection con = null;
	
	
	public PrepararSql() throws SQLException, ClassNotFoundException{
		
		con = ConexaoBD.getConnection();
		buscar = con.prepareStatement("SELECT * FROM ? WHERE ? = ?");
		atualizar = con.prepareStatement("UPDATE ? SET ? = ? WHERE id = ?");
		
	}
	
	public abstract Codigo buscar(String campo) throws SQLException;
	public abstract void atualizar(String campo) throws SQLException ;
	
	
	
}
