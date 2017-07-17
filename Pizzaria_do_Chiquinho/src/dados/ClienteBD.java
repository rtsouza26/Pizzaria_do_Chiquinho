package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import principal.Cliente;
import principal.Funcionario;

/**Classe para a conexão da classe cliente com o banco de dados, onde serão contidos, valores e métodos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */


public class ClienteBD {

	private PreparedStatement inserir = null;
	private PreparedStatement remover = null;
	private PreparedStatement listar = null;
	private PreparedStatement buscar = null;
	private PreparedStatement buscarcpf = null;
	private ResultSet rs = null;
	private Connection con = null;

	
	public ClienteBD() throws ClassNotFoundException, SQLException{
	
		con = ConexaoBD.getConnection();
	
	
			inserir = con.prepareStatement("INSERT INTO Clientes(nome,endereco,cpf,telefone) "
				+ "VALUE (?,?,?,?)");
			remover = con.prepareStatement("DELETE FROM Clientes WHERE cod = ?");
			buscar = con.prepareStatement("SELECT * FROM Clientes WHERE nome = ?");
			buscarcpf = con.prepareStatement("SELECT * FROM Clientes WHERE cpf = ?");
			listar = con.prepareStatement("SELECT * FROM Clientes");
		
	}
	
	public boolean inserirClienBD(Cliente cliente) throws SQLException{
		
		boolean inserido = false;
		try {
			inserir.setString(1, cliente.getNome());
			inserir.setString(2, cliente.getEndereco());
			inserir.setString(3, cliente.getCpf());
			inserir.setString(4, cliente.getTelefone());
			
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
	
	public boolean existeBD(String cpf) throws SQLException{
		boolean existe = false;
			try {
				buscarcpf.setString(3, cpf);
				if((rs = buscarcpf.executeQuery())!=null){
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
	
	public Cliente buscarClienBD(String cpf) throws SQLException{
		Cliente clien = null;
		try {
			buscarcpf.setString(1, cpf);
			
			if((rs = buscar.executeQuery())!=null){;
				clien = new Cliente();
				clien.setCodigo(rs.getInt("cod"));
				clien.setNome(rs.getString("nome"));
				clien.setEndereco(rs.getString("endereco"));
				clien.setCpf(rs.getString("cpf"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		finally
		{
			ConexaoBD.closeConnection(con,rs);
		}
		
		return clien;
	}
	
	public boolean removerClienBD(String cpf) throws SQLException{
		boolean removido = false;
		Cliente clien = new Cliente();
		
		clien = this.buscarClienBD(cpf);
		try {
			remover.setString(3, clien.getCpf());
			removido = remover.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ConexaoBD.closeConnection(con, remover);
			e.printStackTrace();
			
		}
				
		return removido;
	}
	
	public List<Cliente> listarClienBD(){
		List<Cliente> clientes = null;
		
		try {
			if((rs = listar.executeQuery())!=null){
				clientes = new ArrayList<Cliente>();
				while(rs.next()){
					clientes.add(new Cliente(
						rs.getInt("cod"),
						rs.getString("nome"),
						rs.getString("endereco"),
						rs.getString("cpf"),
						rs.getString("telefone")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clientes;
	}
	
	public boolean atualizarClienBD(Cliente cliente) throws SQLException{
		boolean atualizado = false;
		
		if(this.removerClienBD(cliente.getCpf())){
			if(this.inserirClienBD(cliente)){
				atualizado = true;
			}
		}
		
		
		return atualizado;
	}
	
}

