package negocio;

import java.util.List;
import dados.ClienteBD;
import dados.ClienteBD;
import principal.Cliente;


public class CadastroCliente {

	private ClienteBD clienteBD;
	private String invalido = "Cliente Inválido";
	private String nãoexiste = "Cliente não existe";
	
	public CadastroCliente(){
		clienteBD= new ClienteBD();
	}
	
	public void inserirCliente(){}
	
	public void removerCliente(){}
	
	public Cliente buscarCliente(){}
	
}
