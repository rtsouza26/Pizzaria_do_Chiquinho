package negocio;

import dados.ProdutoBD;
import principal.Produto;


public class CadastroPedido {
private ProdutoBD prodBD;
	
	
	public CadastroProduto(){
		prodBD = new ProdutoBD();
	}
	
	public void inserirProd(Produto prod){
		
		if(prod != null){
			if(!(prodBD.existeBD(prod.getNome()))){
				if(prodBD.inserirprodBD(prod.getNome(), prod.getPreco(), prod.getQuantidade())){
					System.out.println("Produto cadastrado com sucesso");
				}else{
					System.out.println("Não foi possível cadastrar o Produto");
				}
			}else{
				System.out.println("Produto já existe");
			}
		}
	}
	public Produto buscarProd(int valor){
		return this.prodBD.buscarProdBD(valor);
	}
	public void removerProd(String nome){
		if(this.prodBD.removerProdBD(nome)){
			System.out.println("Produto deletado com sucesso");
		}else{
			System.out.println("Não foi possível deletar Produto");
		}
	}
	

}

}
