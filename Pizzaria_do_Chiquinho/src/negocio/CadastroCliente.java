package negocio;

import java.sql.SQLException;
import java.util.List;
import dados.ClienteBD;
import principal.Cliente;
import principal.Funcionario;


public class CadastroCliente {

	private ClienteBD clienteBD;
	private String invalido = "Cliente Inválido";
	private String naoexiste = "Cliente não existe";
	
	public CadastroCliente() throws ClassNotFoundException, SQLException{
		clienteBD= new ClienteBD();
}
	
	public void inserirCliente(Cliente cliente) throws SQLException{
		
		if(cliente != null){
			if(!(clienteBD.existeBD(cliente.getCpf()))){	
				if(clienteBD.inserirClienBD(cliente)){
						System.out.println("Cliente cadastrado com sucesso");
					}else{
						System.out.println("Não foi possível cadastrar o cliente");
					}			
				}else{
					System.out.println("Funcionario já existe");
				}			
			}else{
				System.out.println(this.invalido);
			}	
		}
		
	
	public void removerCliente(String cpf) throws SQLException{
		
		if(cpf != null){
			if(clienteBD.existeBD(cpf)){
				if(clienteBD.removerClienBD(cpf)){
					System.out.println("Cliente deletado com sucesso");
				}else{
					System.out.println("Erro ao deletar cliente");
				}
			}else{
				System.out.println(this.naoexiste);
			}
		}else{
			System.out.println(this.invalido);
		}
	}
	
	
	public Cliente buscarCliente(String cpf) throws SQLException {
		Cliente aux = null;
		if(cpf != null){
			if(clienteBD.existeBD(cpf)){
				aux = clienteBD.buscarClienBD(cpf);
			}else{
				System.out.println(this.naoexiste);
			}
		}else{
			System.out.println(this.invalido);
		}
		return aux;
	}
	
	public void atualizarCliente(Cliente cliente) throws SQLException{
		
		if(cliente != null){
			if(clienteBD.existeBD(cliente.getCpf())){
				if(clienteBD.atualizarClienBD(cliente)){
					System.out.println("Cliente atualizado com sucesso");
				}else{
					System.out.println("Não foi possivel atulizar o cliente");
				}
			}else{
				System.out.println(naoexiste);
			}
		}else{
			System.out.println(invalido);
		}
	}
	
	
	public List<Cliente> listarCliente() throws SQLException{
		
		return clienteBD.listarClienBD();
	
	}
	
	
}
