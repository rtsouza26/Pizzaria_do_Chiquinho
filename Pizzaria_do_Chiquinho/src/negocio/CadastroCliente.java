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
	
	//Função testada e funcioando (Mas precisa sr otimizada)
	public void inserirCliente(Cliente cliente) throws SQLException, InserirClienteErro, ClienteExistenteErro, ClienteInvalidoErro{
		
		if(cliente != null){
			if(!(clienteBD.existeBD(cliente.getTelefone()))){	
						clienteBD.inserirClienBD(cliente);
						System.out.println("Cliente cadastrado com sucesso");		
				}else{
					throw new ClienteExistenteErro();
				}			
			}else{
				throw new ClienteInvalidoErro();
			}	
		}
	
	//Função testada e funcionando !!!
	public void removerCliente(String telefone) throws SQLException, BuscarClienteErro, RemoverClienteErro, ClienteInexistenteErro, ClienteInvalidoErro, ClassNotFoundException{
		
		if(telefone != null){
			if(clienteBD.existeBD(telefone)){
				if(clienteBD.removerClienBD(telefone)){
					System.out.println("Cliente deletado com sucesso");
				}
			}else{
				throw new ClienteInexistenteErro();
			}
		}else{
			throw new ClienteInvalidoErro();
		}
	}
	
	//Função testada e funcionando !!!
	public Cliente buscarCliente(String telefone) throws SQLException, BuscarClienteErro, ClienteInexistenteErro, ClienteInvalidoErro {
		Cliente aux = null;
		if(telefone != null){
			if(clienteBD.existeBD(telefone)){
				aux = clienteBD.buscarClienBD(telefone);
			}else{
				throw new ClienteInexistenteErro();
			}
		}else{
			throw new ClienteInvalidoErro();
		}
		return aux;
	}
	
	//Função testada e funcionando !!!
	public void atualizarCliente(Cliente cliente) throws SQLException, InserirClienteErro, BuscarClienteErro, RemoverClienteErro, AtualizarClienteErro, ClienteInexistenteErro, ClienteInvalidoErro{
		
		if(cliente != null){
			if(clienteBD.existeBD(cliente.getTelefone())){
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
	
	//Função testada e funcionando !!!
	public List<Cliente> listarCliente() throws SQLException, ListarClienteErro{
		
		return clienteBD.listarClienBD();
	
	}
	
	
}
