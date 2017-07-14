package negocio;

import java.sql.SQLException;
import java.util.List;

import dados.ProdutoBD;
import principal.Produto;


public class CadastroProduto{
	
	private ProdutoBD prodBD;
	private String invalido = "Produto Inválido";
	private String nãoexiste = "Produto não existe";	
	
	public CadastroProduto() throws ClassNotFoundException, SQLException{
		 prodBD = new ProdutoBD();
		
	}
	
	public void inserirProduto(Produto prod) throws SQLException{
		
		if(prod != null){
			if(!(prodBD.existeBD(prod.getNome()))){
				if(prodBD.inserirProdBD(prod)){
					System.out.println("Produto cadastrado com sucesso");
				}else{
					System.out.println("Não foi possível cadastrar o Produto");
				}
			}else{
				System.out.println("Produto já existe");
			}
		}else{
			System.out.println(invalido);
		}
	}
	
	public Produto buscarProduto(String nome) throws SQLException{
		Produto aux = null;
		if(nome !=null){
			if(prodBD.existeBD(nome)){
				prodBD.buscarProdBD(nome);
			}else{
				System.out.println(nãoexiste);
			}
		}else{
			System.out.println(invalido);
		}
		return aux;
	}
	
	public void removerProduto(String nome) throws SQLException{
		if(nome!=null){
			if(prodBD.existeBD(nome)){
				if(this.prodBD.removerProdBD(nome)){
					System.out.println("Produto excluido com sucesso");
				}else{
					System.out.println("Não foi possível deletar Produto");
				}
			}else{
				System.out.println(nãoexiste);
			}
		}else{
			System.out.println(invalido);
		}
		
	}
	
	public void atualizarProduto(Produto produto) throws SQLException{
		
		if(produto!= null){
			if(prodBD.existeBD(produto.getNome())){
				if(prodBD.atualizarPedidoBD(produto)){
					System.out.println("Produto atualizado com sucesso");
				}else{
					System.out.println("Não foi possível atualizar o produto");
				}
			}else{
				System.out.println(nãoexiste);
			}
			
		}else{
			System.out.println(invalido);
		}
		
	}
	
	public List<Produto> listarProdutos() throws SQLException{
		return this.prodBD.listarProdBD();
	}
}


