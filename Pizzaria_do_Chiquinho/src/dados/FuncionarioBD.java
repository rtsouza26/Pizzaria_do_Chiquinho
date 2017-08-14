package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		
		
			inserir = con.prepareStatement("INSERT INTO funcionarios(nome,endereco,cpf,telefone,cod_tipo,login,senha) "
					+ "VALUE (?,?,?,?,?,?,?)");
			remover = con.prepareStatement("DELETE FROM funcionarios WHERE cpf = ?");
			buscar = con.prepareStatement("SELECT * FROM funcionarios WHERE cpf = ?");
			buscarlogin = con.prepareStatement("SELECT * FROM funcionarios WHERE login = ?");
			listar = con.prepareStatement("SELECT * FROM funcionarios");
			atualizar = con.prepareStatement("UPDATE funcionarios SET nome = ?, endereco = ?, telefone = ?, cod_tipo = ?, login = ?, senha = ? WHERE cpf = ? ");
	}
	
	//Função testada e funcionando (Mas precisa ser otimizada)
	public void inserirFuncBD(Funcionario func) throws SQLException, InserirFuncionarioErro{
			
		if(func != null){
			inserir.setString(1, func.getNome());
			inserir.setString(2, func.getEndereco());
			inserir.setString(3, func.getCpf());
			inserir.setString(4, func.getTelefone());
			inserir.setString(5, func.getTipo());
			inserir.setString(6, func.getLogin());
			inserir.setString(7, func.getSenha());
			inserir.executeUpdate();
			
		}else{
				throw new InserirFuncionarioErro(); 
		}	
	}
	
	//Função testada e funcionando !!!
	public boolean existeLoginBD(String login) throws SQLException{
		boolean existe = false;
		
		buscarlogin.setString(1, login);
		rs = buscarlogin.executeQuery();
		
		if(rs.next()){
			existe = true;
		}
		//ConexaoBD.closeConnection(con);
	
		return existe;

	}
	
	//Função testada e funcionando !!!
	public boolean existeBD(String cpf) throws SQLException{
		boolean existe = false;
			
		buscar.setString(1, cpf);	
		rs = buscar.executeQuery();
				
		if(rs.next()){
			existe = true;
			//System.out.println("Passei aqui");
		}
				
		//ConexaoBD.closeConnection(con, rs);	
		return existe;	
	}
	
	//Função testada e funcionando !!!
	public Funcionario buscarFuncBD(String cpf) throws SQLException, BuscarFuncionarioErro{
		Funcionario func = null;

			buscar.setString(1, cpf);	
			
			rs = buscar.executeQuery();
			
			if(rs.next()){;
				func = new Funcionario();
				func.setCodigo(rs.getInt("idfuncionario"));
				func.setNome(rs.getString("nome"));
				func.setEndereco(rs.getString("endereco"));
				func.setCpf(rs.getString("cpf"));
				func.setTelefone(rs.getString("telefone"));
				func.setTipo(rs.getString("cod_tipo"));
				func.setLogin("*****");
				func.setSenha("*****");
				
				//ConexaoBD.closeConnection(con, rs);	
				return func;
				
			}else{
				
				//ConexaoBD.closeConnection(con, rs);	
				throw new BuscarFuncionarioErro();
			}	
	}
	
	//Função testada e funcionando (Mas precisa ser otimizada)
	public boolean removerFuncBD(String cpf) throws SQLException, RemoverFuncionarioErro, BuscarFuncionarioErro{
		
		boolean removido = false;
		
		if(cpf != null){
			remover.setString(1, cpf);
			removido = remover.execute();
			removido = true;
		
		}else{
			
			throw new RemoverFuncionarioErro();
		}
		//ConexaoBD.closeConnection(con, remover);
		return removido;	
	}
	
	//Função testada e funcionando !!!
	public void atualizarFuncBD(Funcionario func) throws SQLException, BuscarFuncionarioErro, AtualizarFuncionarioErro{

		
		if(existeBD(func.getCpf())){
			
			//System.out.println(func.getCpf());
			
			atualizar.setString(1, func.getNome());
			atualizar.setString(2, func.getEndereco());
			atualizar.setString(3, func.getTelefone());
			atualizar.setString(4, func.getTipo());
			atualizar.setString(5, func.getLogin());
			atualizar.setString(6, func.getSenha());
			atualizar.setString(7, func.getCpf());
			atualizar.executeUpdate();
			
			//System.out.println("Atualização concluida!");
			
		}else{
			throw new AtualizarFuncionarioErro();
		}
		
	}
	
	//Função testada e funcionando !!!
	public ArrayList<Funcionario> listarFuncBD() throws SQLException, ListarFuncionarioErro{
		ArrayList<Funcionario> funcionarios = null;
			
			rs = null;
			rs = listar.executeQuery();
		
			if(rs != null){

				funcionarios = new ArrayList<Funcionario>();

				while(rs.next()){
					
					Funcionario func_aux = new Funcionario();
					func_aux.setNome(rs.getString("nome"));
					func_aux.setEndereco(rs.getString("endereco"));
					func_aux.setCpf(rs.getString("cpf"));
					func_aux.setTelefone(rs.getString("telefone"));
					func_aux.setTipo(rs.getString("cod_tipo"));
					func_aux.setLogin(rs.getString("login"));
					
					funcionarios.add(func_aux);			
				}
					
				return funcionarios;
			}else{
			
				throw new ListarFuncionarioErro();
		}
			
	}
	
}
