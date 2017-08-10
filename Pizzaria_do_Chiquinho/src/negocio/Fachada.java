package negocio;

import java.sql.SQLException;
import java.util.List;

import dados.exception.AtualizarFuncionarioErro;
import dados.exception.AtualizarProdutoErro;
import dados.exception.BuscaProdutoErro;
import dados.exception.BuscarClienteErro;
import dados.exception.BuscarFuncionarioErro;
import dados.exception.InserirFuncionarioErro;
import dados.exception.InserirProdutoErro;
import dados.exception.ListarFuncionarioErro;
import dados.exception.ListarProdutoErro;
import dados.exception.RemoverFuncionarioErro;
import dados.exception.RemoverItem_pedidoErro;
import dados.exception.RemoverProdutoErro;
import negocio.exception.FuncionarioExistente;
import negocio.exception.FuncionarioInexistente;
import negocio.exception.FuncionarioInvalido;
import negocio.exception.LoginJaExiste;
import negocio.exception.ProdutoExistenteErro;
import negocio.exception.ProdutoInvalidoErro;
import negocio.exception.ProdutoNomeInvalidoErro;
import negocio.exception.ProdutoPrecoInvalidoErro;
import negocio.exception.ProdutoQuantidadeInvalidaErro;
import principal.Pedido;
import principal.Produto;
import principal.Funcionario;

public class Fachada {
	private  static Fachada instance;
	private CadastroPedido pedido;
	private CadastroProduto produto;
	private CadastroFuncionario funcionario;
	
	public Fachada() throws ClassNotFoundException, SQLException{
		pedido = new CadastroPedido();
		produto = new CadastroProduto();
	}
	
	public static Fachada getInstance() throws ClassNotFoundException, SQLException{
		if(Fachada.instance == null){
			Fachada.instance = new Fachada();
		}
		return Fachada.instance;
	}
	
	public void inserir(Pedido pedido) throws ClassNotFoundException, SQLException{
		this.pedido.inserirPedido(pedido);
	}
	
	public void remover(Pedido pedido) throws ClassNotFoundException, SQLException, BuscaProdutoErro, BuscarFuncionarioErro, BuscarClienteErro, RemoverItem_pedidoErro{
		this.pedido.removerPedido(pedido.getCodigo());
	}
	public Pedido buscar(Pedido pedido) throws ClassNotFoundException, SQLException, BuscaProdutoErro, BuscarFuncionarioErro, BuscarClienteErro{
		return this.pedido.buscarPedido(pedido.getCodigo());
	}
	public void atualizar(Pedido pedido) throws ClassNotFoundException, SQLException, BuscaProdutoErro, BuscarFuncionarioErro, BuscarClienteErro, RemoverItem_pedidoErro{
		this.pedido.atualizarPedido(pedido);
	}
	public List<Pedido> listar() throws ClassNotFoundException, SQLException, BuscaProdutoErro, BuscarFuncionarioErro, BuscarClienteErro{
		return this.pedido.listarPedido();
	}
	
	public void inserir(Produto produto) throws SQLException, InserirProdutoErro, ProdutoInvalidoErro, ProdutoExistenteErro, 
	ProdutoPrecoInvalidoErro, ProdutoNomeInvalidoErro, ProdutoQuantidadeInvalidaErro{
		this.produto.inserirProduto(produto);
	}
	public void remover(Produto produto) throws SQLException, ClassNotFoundException, BuscaProdutoErro, RemoverProdutoErro, 
	ProdutoInvalidoErro{
		this.produto.removerProduto(produto.getNome());
	}
	public Produto buscar(Produto produto) throws SQLException, ClassNotFoundException, ProdutoInvalidoErro, BuscaProdutoErro{
		return this.produto.buscarProduto(produto.getNome());
	}
	public void atualizar(Produto produto) throws SQLException, ClassNotFoundException, BuscaProdutoErro, AtualizarProdutoErro, ProdutoInvalidoErro{
		this.produto.atualizarProduto(produto);
	}
	public List<Produto> listarProduto() throws SQLException, ListarProdutoErro{
		return this.produto.listarProdutos();
	}
	
	public void inserir(Funcionario funcionario) throws SQLException, FuncionarioInvalido, FuncionarioExistente, InserirFuncionarioErro, LoginJaExiste{
		this.funcionario.inserirFunc(funcionario);
	}
	public void remover(Funcionario funcionario) throws SQLException, FuncionarioInexistente, FuncionarioInvalido, RemoverFuncionarioErro, BuscarFuncionarioErro{
		this.funcionario.removerFunc(funcionario.getCpf());
	}
	public Funcionario buscar(Funcionario funcionario) throws SQLException, FuncionarioInexistente, FuncionarioInvalido, BuscarFuncionarioErro{
		return this.funcionario.buscarFunc(funcionario.getCpf());
	}
	public void atualizar(Funcionario funcionario) throws SQLException, FuncionarioInexistente, FuncionarioInvalido, RemoverFuncionarioErro, InserirFuncionarioErro, BuscarFuncionarioErro, AtualizarFuncionarioErro{
		this.funcionario.atualizarFunc(funcionario);
	}
	public List<Funcionario> listarFuncionario() throws SQLException, ListarFuncionarioErro{
		return this.funcionario.listarFunc();
	}

}
