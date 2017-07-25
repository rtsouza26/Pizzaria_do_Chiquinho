package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import dados.exception.*;

import principal.Funcionario;

/**Classe para a conexão do classe funcionários com o banco de dados, onde serão contidos, valores e métodos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */

public class FuncionarioBD{

	private PreparedStatement inserir = null;  
	private PreparedStatement remover = null;
	private PreparedStatement listar = null;
	private PreparedStatement buscar = null;
	private PreparedStatement buscarlogin = null;
	private ResultSet rs = null;
	private Connection con = null;
	
	
	public FuncionarioBD() throws ClassNotFoundException, SQLException{
		
		con = ConexaoBD.getConnection();
		
		
			inserir = con.prepareStatement("INSERT INTO funcionarios(nome,endereco,cpf,telefone,tipo,login,senha) "
					+ "VALUE (?,?,?,?,?,?,?)");
			remover = con.prepareStatement("DELETE FROM funcionarios WHERE cpf = ?");
			buscar = con.prepareStatement("SELECT * FROM funcionarios WHERE cpf = ?");
			buscarlogin = con.prepareStatement("SELECT * FROM funcionarios WHERE cpf = ?");
			listar = con.prepareStatement("SELECT * FROM Funcionarios");
		
	}
	
	
	public boolean inserirFuncBD(Funcionario func) throws SQLException, InserirFuncionarioErro{
		
		boolean inserido = false;
		
			inserir.setString(1, func.getNome());
			inserir.setString(2, func.getEndereco());
			inserir.setString(3,func.getCpf());
			inserir.setString(4, func.getTelefone());
			inserir.setString(5,func.getTipo());
			inserir.setString(6,func.getLogin());
			inserir.setString(7,func.getSenha());
			
			inserido = inserir.execute();
	
			ConexaoBD.closeConnection(con);
		if(inserido==false){
			throw new InserirFuncionarioErro();
		}
			
		return inserido;
	}
	public boolean existeBD(String cpf) throws SQLException{
		boolean existe = false;
			
				buscarlogin.setString(1, cpf);
				if((rs = buscarlogin.executeQuery())!=null){
					existe = true;
				}
				ConexaoBD.closeConnection(con);
			
			
		 return existe;
		
	}
	
	public Funcionario buscarFuncBD(String cpf) throws SQLException, BuscarFuncionarioErro{
		Funcionario func = null;

			buscar.setString(1, cpf);
			
			if((rs = buscar.executeQuery())!=null){;
				func = new Funcionario();
				func.setCodigo(rs.getInt("cod"));
				func.setNome(rs.getString("nome"));
				func.setEndereco(rs.getString("endereco"));
				func.setCpf(rs.getString("cpf"));
				func.setTelefone(rs.getString("telefone"));
				func.setTipo(rs.getString("tipo"));
				func.setLogin("*****");
				func.setSenha("*****");
					
			}
			
		
			ConexaoBD.closeConnection(con,rs);
		if(func==null){
			throw new BuscarFuncionarioErro();
		}
		
		return func;
	}
	
	
	public boolean removerFuncBD(String cpf) throws SQLException, RemoverFuncionarioErro, BuscarFuncionarioErro{
		boolean removido = false;
		Funcionario func = new Funcionario();
		
		func = this.buscarFuncBD(cpf);
		
			remover.setInt(1, func.getCodigo());
			removido = remover.execute();
	
			ConexaoBD.closeConnection(con, remover);
			
		if(removido==false){
			throw new RemoverFuncionarioErro();
		}
			
		return removido;
	}
	public boolean atualizarFuncBD(Funcionario func) throws SQLException, RemoverFuncionarioErro, InserirFuncionarioErro, BuscarFuncionarioErro, AtualizarFuncionarioErro{
		boolean atualizado = false;
		
		if(this.removerFuncBD(func.getNome())){
			if(this.inserirFuncBD(func)){
				atualizado = true;
			}
		}
		if(atualizado==false){
			throw new AtualizarFuncionarioErro();
		}
		
		return atualizado;
	}
	
	public List<Funcionario> listarFuncBD() throws SQLException, ListarFuncionarioErro{
		List<Funcionario> funcionarios = null;
		
		
			if((rs = listar.executeQuery())!=null){
				funcionarios = new ArrayList<Funcionario>();
				while(rs.next()){
					funcionarios.add(new Funcionario(
							rs.getInt("cod"),
							rs.getString("nome"),
							rs.getString("endereco"),
							rs.getString("cpf"),
							rs.getString("telefone"),
							rs.getString("tipo"),
							rs.getString("login")));
				}
			}
		
		if(funcionarios==null){
			throw new ListarFuncionarioErro();
		}
			
		return funcionarios;
	}
	
}
