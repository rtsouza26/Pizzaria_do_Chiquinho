package negocio;

import java.sql.SQLException;
import java.util.List;
import dados.ClienteBD;
import dados.exception.AtualizarClienteErro;
import dados.exception.BuscarClienteErro;
import dados.exception.InserirClienteErro;
import dados.exception.ListarClienteErro;
import dados.exception.RemoverClienteErro;
import negocio.exception.ClienteExistenteErro;
import negocio.exception.ClienteInexistenteErro;
import negocio.exception.ClienteInvalidoErro;
import principal.Cliente;


public class CadastroCliente {

	private ClienteBD clienteBD;
	
	public CadastroCliente() throws ClassNotFoundException, SQLException{
		clienteBD= new ClienteBD();
}
	
	public void inserirCliente(Cliente cliente) throws SQLException, InserirClienteErro, ClienteExistenteErro, ClienteInvalidoErro{
		
		if(cliente != null){
			if(!(clienteBD.existeBD(cliente.getCpf()))){	
				if(clienteBD.inserirClienBD(cliente)){
						System.out.println("Cliente cadastrado com sucesso");
					}else{
						System.out.println("Não foi possível cadastrar o cliente");
					}			
				}else{
					throw new ClienteExistenteErro();
				}			
			}else{
				throw new ClienteInvalidoErro();
			}	
		}
		
	
	public void removerCliente(String cpf) throws SQLException, BuscarClienteErro, RemoverClienteErro, ClienteInexistenteErro, ClienteInvalidoErro{
		
		if(cpf != null){
			if(clienteBD.existeBD(cpf)){
				if(clienteBD.removerClienBD(cpf)){
					System.out.println("Cliente deletado com sucesso");
				}
			}else{
				throw new ClienteInexistenteErro();
			}
		}else{
			throw new ClienteInvalidoErro();
		}
	}
	
	
	public Cliente buscarCliente(String cpf) throws SQLException, BuscarClienteErro, ClienteInexistenteErro, ClienteInvalidoErro {
		Cliente aux = null;
		if(cpf != null){
			if(clienteBD.existeBD(cpf)){
				aux = clienteBD.buscarClienBD(cpf);
			}else{
				throw new ClienteInexistenteErro();
			}
		}else{
			throw new ClienteInvalidoErro();
		}
		return aux;
	}
	
	public void atualizarCliente(Cliente cliente) throws SQLException, InserirClienteErro, BuscarClienteErro, RemoverClienteErro, AtualizarClienteErro, ClienteInexistenteErro, ClienteInvalidoErro{
		
		if(cliente != null){
			if(clienteBD.existeBD(cliente.getCpf())){
				if(clienteBD.atualizarClienBD(cliente)){
					System.out.println("Cliente atualizado com sucesso");
				}
			}else{
				throw new ClienteInexistenteErro();
			}
		}else{
			throw new ClienteInvalidoErro();
		}
	}
	
	
	public List<Cliente> listarCliente() throws SQLException, ListarClienteErro{
		
		return clienteBD.listarClienBD();
	
	}
	
	
}
