package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import principal.Funcionario;

/**Classe para a conexão do classe funcionários com o banco de dados, onde serão contidos, valores e métodos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */

public class FuncionariosBD{

	private PreparedStatement inserir = null;
	private PreparedStatement remover = null;
	private PreparedStatement listar = null;
	private PreparedStatement buscar = null;
	private PreparedStatement buscarlogin = null;
	private ResultSet rs = null;
	private Connection con = null;
	
	
	public FuncionariosBD(){
		
		con = ConexaoBD.getConnection();
		
		try {
			inserir = con.prepareStatement("INSERT INTO funcionarios(nome,endereco,cpf,telefone,tipo,login,senha) "
					+ "VALUE (?,?,?,?,?,?,?)");
			remover = con.prepareStatement("DELETE FROM funcionarios WHERE cod = ?");
			buscar = con.prepareStatement("SELECT * FROM funcionarios WHERE nome = ?");
			buscarlogin = con.prepareStatement("SELECT * FROM funcionarios WHERE login = ?");
			listar = con.prepareStatement("SELECT * FROM Funcionarios");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("problemas com o drive de Banco de dados");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public boolean inserirFuncBD(
			String nome, String endereco, int cpf, String telefone, String tipo, String login, String senha){
		
		boolean inserido = false;
		try {
			inserir.setString(1, nome);
			inserir.setString(2, endereco);
			inserir.setInt(3,cpf);
			inserir.setString(4, telefone);
			inserir.setString(5,tipo);
			inserir.setString(6,login);
			inserir.setString(7,senha);
			
			inserido = inserir.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ConexaoBD.closeConnection(con, inserir);
			e.printStackTrace();
			
		}
		finally{
			ConexaoBD.closeConnection(con);
		}
			
		return inserido;
	}
	public boolean existeBD(String login){
		boolean existe = false;
			try {
				buscarlogin.setString(1, login);
				if((rs = buscarlogin.executeQuery())!=null){
					existe = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				ConexaoBD.closeConnection(con);
			}
			
		 return existe;
		
	}
	
	public Funcionario BuscarFuncBD(String nome){
		Funcionario func = null;
		try {
			buscar.setString(1, nome);
			
			if((rs = buscar.executeQuery())!=null){;
				func = new Funcionario();
				func.setCodigo(rs.getInt("cod"));
				func.setNome(rs.getString("nome"));
				func.setEndereco(rs.getString("endereco"));
				func.setCpf(rs.getInt("cpf"));
				func.setTelefone(rs.getString("telefone"));
				func.setTipo(rs.getString("tipo"));
				func.setLogin("*****");
				func.setSenha("*****");
					
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		finally
		{
			ConexaoBD.closeConnection(con,rs);
		}
		
		return func;
	}
	
	
	public boolean removerFuncBD( String nome){
		boolean removido = false;
		Funcionario func = new Funcionario();
		
		func = this.BuscarFuncBD(nome);
		try {
			remover.setInt(1, func.getCodigo());
			removido = remover.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ConexaoBD.closeConnection(con, remover);
			e.printStackTrace();
			
		}
				
		return removido;
	}
	
	public List<Funcionario> listarFuncBD(){
		List<Funcionario> funcionarios = null;
		
		try {
			if((rs = listar.executeQuery())!=null){
				funcionarios = new ArrayList<Funcionario>();
				while(rs.next()){
					funcionarios.add(new Funcionario(
							rs.getInt("cod"),
							rs.getString("nome"),
							rs.getString("endereco"),
							rs.getInt("cpf"),
							rs.getString("telefone"),
							rs.getString("tipo"),
							rs.getString("login")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return funcionarios;
	}
	
}
