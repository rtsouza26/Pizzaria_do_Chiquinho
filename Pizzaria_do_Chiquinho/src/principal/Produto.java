package principal;

/**Classe para objetos do tipo Produtos, onde serão contidos, valores e métodos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */

public class Produto {
	
	private int codi;
	private int quantidade;
	private double preco;
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	

}
