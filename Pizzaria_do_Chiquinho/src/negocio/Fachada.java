package negocio;

import java.util.List;

import principal.Pedido;
import principal.Produto;
import principal.Funcionario;

public class Fachada {
	private  static Fachada instance;
	private CadastroPedido pedido;
	private CadastroProduto produto;
	private CadastroFuncionario funcionario;
	
	public Fachada(){
		pedido = new CadastroPedido();
		produto = new CadastroProduto();
	}
	
	public static Fachada getInstance(){
		if(Fachada.instance == null){
			Fachada.instance = new Fachada();
		}
		return Fachada.instance;
	}
	
	public void inserir(Pedido pedido){
		this.pedido.inserirPedido(pedido);
	}
	
	public void remover(Pedido pedido){
		this.pedido.removerPedido(pedido.getCodigo());
	}
	public Pedido buscar(Pedido pedido){
		return this.pedido.buscarPedido(pedido.getCodigo());
	}
	public void atualizar(Pedido pedido){
		this.pedido.atualizarPedido(pedido);
	}
	public List<Pedido> listar(){
		return this.pedido.listarPedido();
	}
	
	public void inserir(Produto produto){
		this.produto.inserirProduto(produto);
	}
	public void remover(Produto produto){
		this.produto.removerProduto(produto.getNome());
	}
	public Produto buscar(Produto produto){
		return this.produto.buscarProduto(produto.getNome());
	}
	public void atualizar(Produto produto){
		this.produto.atualizarProduto(produto);
	}
	public List<Produto> listarProduto(){
		return this.produto.listarProdutos();
	}
	
	public void inserir(Funcionario funcionario){
		this.funcionario.inserirFunc(funcionario);
	}
	public void remover(Funcionario funcionario){
		this.funcionario.removerFunc(funcionario.getLogin());
	}
	public Funcionario buscar(Funcionario funcionario){
		return this.funcionario.buscarFunc(funcionario.getLogin());
	}
	public void atualizar(Funcionario funcionario){
		this.funcionario.atualizarFunc(funcionario);
	}
	public List<Funcionario> listarFuncionario(){
		return this.funcionario.listarFunc();
	}

}
