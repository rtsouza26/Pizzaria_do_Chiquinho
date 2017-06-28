package principal;

import java.util.ArrayList;
import java.util.List;

/**Classe para objetos do tipo Produtos, onde serão contidos, valores e métodos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */

public class Itens_pedido{
	private static double total;
	
	private List <Produto> itensPedido;
	
	public Itens_pedido(){
		itensPedido =  new ArrayList<Produto>();
	}
	
	public Produto buscaProduto(String nomedoproduto) {
		Produto prod = null;
		for(int i =0;i< itensPedido.size();i++){
			if((itensPedido.get(i).getNome()).equals(nomedoproduto)){
				prod = itensPedido.get(i);
			}
		}
		return prod;
	}
	
	public void addProduto(Produto prod) {
		itensPedido.add(prod);
	}
	
	public void removerProduto(String nomedoproduto){
		
		for(int i =0;i< itensPedido.size();i++){
			if((itensPedido.get(i).getNome()).equals(nomedoproduto)){
				 itensPedido.remove(i);
			}
		}
	}
	public int tamanho(){
		return itensPedido.size();
	}
	public Produto getProduto(int indice){
		return itensPedido.get(indice);
	}
}
