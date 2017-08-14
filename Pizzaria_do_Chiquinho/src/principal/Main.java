package principal;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dados.ClienteBD;
import dados.FuncionarioBD;
import dados.LoginBD;
import dados.ProdutoBD;
import dados.exception.AtualizarClienteErro;
import dados.exception.AtualizarFuncionarioErro;
import dados.exception.AtualizarProdutoErro;
import dados.exception.BuscaProdutoErro;
import dados.exception.BuscarClienteErro;
import dados.exception.BuscarFuncionarioErro;
import dados.exception.InserirClienteErro;
import dados.exception.InserirFuncionarioErro;
import dados.exception.InserirProdutoErro;
import dados.exception.ListarClienteErro;
import dados.exception.ListarFuncionarioErro;
import dados.exception.ListarProdutoErro;
import dados.exception.RemoverClienteErro;
import dados.exception.RemoverFuncionarioErro;
import dados.exception.RemoverProdutoErro;
import negocio.CadastroCliente;
import negocio.CadastroFuncionario;
import negocio.exception.ClienteExistenteErro;
import negocio.exception.ClienteInexistenteErro;
import negocio.exception.ClienteInvalidoErro;
import negocio.exception.FuncionarioExistente;
import negocio.exception.FuncionarioInexistente;
import negocio.exception.FuncionarioInvalido;
import negocio.exception.LoginJaExiste;
import view.TLogin;

/**Pizzaria do Chiquinho
 * 
 * Projeto que visa criar um sistema de controle de processos de uma loja voltada para a venda de pizzas 
 * e serviços relacionados.
 * 
 * 
 * @author 
 * @version 1.0
 * @since Release 0
 */

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InserirClienteErro, InserirFuncionarioErro, BuscarFuncionarioErro, ListarFuncionarioErro, RemoverFuncionarioErro, AtualizarFuncionarioErro, ClienteExistenteErro, ClienteInvalidoErro, BuscarClienteErro, RemoverClienteErro, AtualizarClienteErro, ListarClienteErro, ClienteInexistenteErro, FuncionarioInvalido, FuncionarioExistente, LoginJaExiste, FuncionarioInexistente, InserirProdutoErro, BuscaProdutoErro, RemoverProdutoErro, AtualizarProdutoErro, ListarProdutoErro {
		// TODO Auto-generated method stub
		
		Produto produto_1 = new Produto();
		
		produto_1.setNome("Coca Cola");
		produto_1.setQuantidade(30);
		produto_1.setPreco(5.50);
		
		Produto produto_2 = new Produto();
		
		produto_2.setNome("Guaraná Antarctica");
		produto_2.setQuantidade(64);
		produto_2.setPreco(5.99);
		
		Produto produto_3 = new Produto();
		
		produto_3.setNome("Fanta");
		produto_3.setQuantidade(40);
		produto_3.setPreco(5.99);

		ProdutoBD produtobd = new ProdutoBD();
		
		
		for(int i = 0; i < produtobd.listarProdBD().size(); i++){
			
			System.out.println("Nome: " + produtobd.listarProdBD().get(i).getNome() + " Quantidade: " + produtobd.listarProdBD().get(i).getQuantidade() + " Preço Unitario: " + produtobd.listarProdBD().get(i).getPreco());
			
		}
		
		//produtobd.atualizarQuatidade("Guaraná Antarctica", 1000);
		
		//System.out.println("Nome: " + produtobd.buscarProdutoBD("Coca Col").getNome() + " Quantidade: " + produtobd.buscarProdutoBD("Coca Cola").getQuantidade() + " Preço: " + produtobd.buscarProdutoBD("Coca Cola").getPreco());

		//System.out.println("Existem: " + produtobd.buscarQuantidade("Guaraná Antarctica") + " " + produtobd.buscarProdutoBD("Guaraná Antarctica").getNome() + " no estoque");
		
		//produtobd.atualizarProdutoBD(produto_2);
		
		//new TLogin().setVisible(true);	
		
	}

}
