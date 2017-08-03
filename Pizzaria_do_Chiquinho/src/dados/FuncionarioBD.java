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
 * 
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
	private PreparedStatement atualizar=null;
	
	
	public FuncionarioBD() throws ClassNotFoundException, SQLException{
		
		con = ConexaoBD.getConnection();
		
		
			inserir = con.prepareStatement("INSERT INTO funcionarios(nome,cod_endereco,cpf,telefone,cod_tipo,login,senha) "
					+ "VALUE (?,?,?,?,?,?,?)");
			remover = con.prepareStatement("DELETE FROM funcionarios WHERE cpf = ?");
			buscar = con.prepareStatement("SELECT * FROM funcionarios WHERE cpf = ?");
			buscarlogin = con.prepareStatement("SELECT * FROM funcionarios WHERE cpf = ?");
			listar = con.prepareStatement("SELECT * FROM Funcionarios");
			atualizar = con.prepareStatement("UPDATE produto SET nome = ?, cod_endereco = ?, telefone = ?, cod_tipo = ?, login = ?, senha = ? WHERE cpf = ? ");
	}
	
	
	public void inserirFuncBD(Funcionario func) throws SQLException, InserirFuncionarioErro{
		
		
		
			inserir.setString(1, func.getNome());
			inserir.setInt(2, func.getEndereco());
			inserir.setString(3,func.getCpf());
			inserir.setString(4, func.getTelefone());
			inserir.setString(5,func.getTipo());
			inserir.setString(6,func.getLogin());
			inserir.setString(7,func.getSenha());
			
			if(!inserir.execute()){
				throw new InserirFuncionarioErro(); 
			};
			
		
	}
	public boolean existeBD(String cpf) throws SQLException{
		boolean existe = false;
			
				buscarlogin.setString(1, cpf);
				if((rs = buscarlogin.executeQuery())!=null){
					existe = true;
				}
				//ConexaoBD.closeConnection(con);
			
			
		 return existe;
		
	}
	
	public Funcionario buscarFuncBD(String cpf) throws SQLException, BuscarFuncionarioErro{
		Funcionario func = null;

			buscar.setString(1, cpf);
			
			if((rs = buscar.executeQuery())!=null){;
				func = new Funcionario();
				func.setCodigo(rs.getInt("cod"));
				func.setNome(rs.getString("nome"));
				func.setEndereco(rs.getInt("cod_endereco"));
				func.setCpf(rs.getString("cpf"));
				func.setTelefone(rs.getString("telefone"));
				func.setTipo(rs.getString("cod_tipo"));
				func.setLogin("*****");
				func.setSenha("*****");
				
				return func;
			}else{
				throw new BuscarFuncionarioErro();
			}
		
		
	}
	
	
	public void removerFuncBD(String cpf) throws SQLException, RemoverFuncionarioErro, BuscarFuncionarioErro{
	
		
		remover.setInt(1, this.buscarFuncBD(cpf).getCodigo());
		
		if(!remover.execute()){
			throw new RemoverFuncionarioErro();
		}
			
		
	}
	public void atualizarFuncBD(Funcionario func) throws SQLException, BuscarFuncionarioErro, AtualizarFuncionarioErro{
		buscarFuncBD(func.getCpf());
		
		atualizar.setString(1, func.getNome());
		atualizar.setInt(2, func.getEndereco());
		atualizar.setString(3, func.getTelefone());
		atualizar.setString(4, func.getTipo());
		atualizar.setString(5,func.getLogin());
		atualizar.setString(6, func.getSenha());
		atualizar.setString(7,func.getCpf());
		
		if(!atualizar.execute()){
			throw new AtualizarFuncionarioErro();
		}
		
		
	}
	
	public List<Funcionario> listarFuncBD() throws SQLException, ListarFuncionarioErro{
		List<Funcionario> funcionarios = null;
		
		
			if((rs = listar.executeQuery())!=null){
				funcionarios = new ArrayList<Funcionario>();
				while(rs.next()){
					funcionarios.add(new Funcionario(
							rs.getInt("cod"),
							rs.getString("nome"),
							rs.getInt("cod_endereco"),
							rs.getString("cpf"),
							rs.getString("telefone"),
							rs.getString("cod_tipo"),
							rs.getString("login")));
				}
				return funcionarios;
			}else{
			
				throw new ListarFuncionarioErro();
		}
			
		
	}
	
}
