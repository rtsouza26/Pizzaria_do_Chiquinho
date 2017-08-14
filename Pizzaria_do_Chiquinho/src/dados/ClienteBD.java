package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.exception.AtualizarClienteErro;
import dados.exception.BuscarClienteErro;
import dados.exception.InserirClienteErro;
import dados.exception.ListarClienteErro;
import dados.exception.RemoverClienteErro;
import principal.Cliente;

/**Classe para a conexão da classe cliente com o banco de dados, onde serão contidos, valores e métodos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */


public class ClienteBD {

	private PreparedStatement inserir = null;
	private PreparedStatement remover = null;
	private PreparedStatement listar = null;
	private PreparedStatement buscartelefone = null;
	private PreparedStatement atualizar = null;
	private ResultSet rs = null;
	private Connection con = null;


	public ClienteBD() throws ClassNotFoundException, SQLException{
	
		con = ConexaoBD.getConnection();
	
	
			inserir = con.prepareStatement("INSERT INTO clientes(nome,endereco,telefone,cod_forma_pagamento) "
				+ "VALUE (?,?,?,?)");
			remover = con.prepareStatement("DELETE FROM clientes WHERE telefone = ?");
			buscartelefone = con.prepareStatement("SELECT * FROM clientes WHERE telefone = ?");
			listar = con.prepareStatement("SELECT * FROM clientes");
			atualizar = con.prepareStatement("UPDATE clientes SET nome = ?, endereco = ?, cod_forma_pagamento = ? WHERE telefone = ? ");

		
	}
	
	//Função testada e duncionando (Mas precisa ser otimizada)
	public void inserirClienBD(Cliente cliente) throws SQLException, InserirClienteErro{
		
		if(cliente != null){
		
			inserir.setString(1, cliente.getNome());
			inserir.setString(2, cliente.getEndereco());
			inserir.setString(3, cliente.getTelefone());
			inserir.setInt(4, cliente.getCod_forma_pagamento());
			inserir.executeUpdate();
			
		}else{
			
			throw new InserirClienteErro();

		}

	}
	
	//Função testada e funcionando !!!
	public boolean existeBD(String telefone) throws SQLException{
		boolean existe = false;
		
		buscartelefone.setString(1, telefone);	
		rs = buscartelefone.executeQuery();
		
		if(rs.next()){
			existe = true;
			//System.out.println("Passei aqui");
		}
		
		//ConexaoBD.closeConnection(con,rs);
		return existe;	
	}
	
	//Função testada e funcionando !!!
	public Cliente buscarClienBD(String telefone) throws SQLException, BuscarClienteErro{
		Cliente clien = null;
		
			buscartelefone.setString(1, telefone);
			
			rs = buscartelefone.executeQuery();
			
			if(rs.next()){;
				clien = new Cliente();
				clien.setCodigo(rs.getInt("idcliente"));
				clien.setNome(rs.getString("nome"));
				clien.setEndereco(rs.getString("endereco"));
				clien.setTelefone(rs.getString("telefone"));
				clien.setCod_forma_pagamento(rs.getInt("cod_forma_pagamento"));
				
			}else{
				throw new BuscarClienteErro();
			}
			
		//ConexaoBD.closeConnection(con,rs);
		return clien;
	}
	
	//Função testada e funcionando (Mas precisa ser otimizada)
	public boolean removerClienBD(String telefone) throws SQLException, BuscarClienteErro, RemoverClienteErro, ClassNotFoundException{
		
		boolean removido = false;
			
		if(telefone != null){
			
			remover.setString(1, telefone);
			//System.out.println("Removeu");
			remover.execute();
			removido = true;
			
		}else{
			//System.out.println("Não removeu");
			throw new RemoverClienteErro();	
		}
		//ConexaoBD.closeConnection(con, remover);	
		return removido;
	}
	
	//Função testada e funcionando (Mas precisa ser otimizada)
	public boolean atualizarClienBD(Cliente cliente) throws SQLException, InserirClienteErro, BuscarClienteErro, RemoverClienteErro, AtualizarClienteErro{
		
		boolean atualizado = false;
		
		if(this.existeBD(cliente.getTelefone())){
			
			atualizar.setString(1, cliente.getNome());
			atualizar.setString(2, cliente.getEndereco());
			atualizar.setInt(3, cliente.getCod_forma_pagamento());
			atualizar.setString(4, cliente.getTelefone());
			atualizar.executeUpdate();
			
			atualizado = true;
			return atualizado;
			
			//System.out.println("Atualização concluida !!!");
					
		}else{
			
			//System.out.println("Erro na atualização !!!");
			throw new AtualizarClienteErro();
		}
	}	
	
	//Função testada e funcionando !!!
	public List<Cliente> listarClienBD() throws SQLException, ListarClienteErro {
		List<Cliente> clientes = null;	

		rs = null;
		rs = listar.executeQuery(); 
		
		if(rs != null){
			
			clientes = new ArrayList<Cliente>();
			
			while(rs.next()){
				
				Cliente clienteaux = new Cliente();
				clienteaux.setCodigo(rs.getInt("idcliente"));
				clienteaux.setNome(rs.getString("nome"));
				clienteaux.setEndereco(rs.getString("endereco"));
				clienteaux.setTelefone(rs.getString("telefone"));
				clienteaux.setCod_forma_pagamento(rs.getInt("cod_forma_pagamento"));
				
				clientes.add(clienteaux);
			}
		}else{		
			throw new ListarClienteErro();	
		}

		//ConexaoBD.closeConnection(con,rs);
		return clientes;
	}
}

