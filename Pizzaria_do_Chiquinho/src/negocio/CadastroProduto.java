package negocio;

import java.sql.SQLException;
import java.util.List;

import dados.ProdutoBD;
import dados.exception.AtualizarProdutoErro;
import dados.exception.BuscaProdutoErro;
import dados.exception.InserirProdutoErro;
import dados.exception.ListarProdutoErro;
import dados.exception.RemoverProdutoErro;
import negocio.exception.*;
import principal.Produto;


public class CadastroProduto{
	
	private ProdutoBD prodBD;
		
	
	public CadastroProduto() throws ClassNotFoundException, SQLException{
		 prodBD = new ProdutoBD();
		
	}
	
	public void inserirProduto(Produto prod) throws SQLException,InserirProdutoErro,ProdutoInvalidoErro,
	ProdutoExistenteErro,ProdutoPrecoInvalidoErro,ProdutoNomeInvalidoErro,ProdutoQuantidadeInvalidaErro{
		
		if(prod != null){
			if(prod.getQuantidade()>=0){
				if(prod.getNome()!= null){
					if(prod.getPreco()>=0.0){
						if(!(this.prodBD.existe(prod.getNome()))){
							this.prodBD.inserir(prod);
			
						}else{
							throw new ProdutoExistenteErro();
						}
					}else{
						throw new ProdutoPrecoInvalidoErro();
					}
				}else{
					throw new ProdutoNomeInvalidoErro();
				}
			}else{
				throw new ProdutoQuantidadeInvalidaErro();
			}
		}else{
			throw new ProdutoInvalidoErro();
		}
	}
	

	
	public Produto buscarProduto(String nome) throws SQLException,ProdutoInvalidoErro, ClassNotFoundException, BuscaProdutoErro{
		Produto aux = null;
		if(nome !=null){
			aux = prodBD.buscar(nome);
			
		}else{
			throw new ProdutoInvalidoErro() ;
		}
		return aux;
	}
	
	public void removerProduto(String nome) throws SQLException, ClassNotFoundException, BuscaProdutoErro, RemoverProdutoErro, ProdutoInvalidoErro{
		if(nome!=null){
			Produto aux = null;
			aux= this.prodBD.buscar(nome);
			this.prodBD.remover(aux.getCodigo());
			
		}else{
			throw new ProdutoInvalidoErro();
		}
		
	}
	
	public void atualizarProduto(Produto produto) throws SQLException, ClassNotFoundException, BuscaProdutoErro, AtualizarProdutoErro, ProdutoInvalidoErro{

		if(produto!= null){
			this.prodBD.buscar(produto.getCodigo());
			this.prodBD.atualizar(produto);
					
		}else{
			throw new ProdutoInvalidoErro();
		}
		
	}
	
	public List<Produto> listarProdutos() throws SQLException, ListarProdutoErro{
		return this.prodBD.listarProdBD();
	}
}


