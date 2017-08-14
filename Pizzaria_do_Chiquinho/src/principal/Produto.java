package principal;

import java.sql.SQLException;

//import dados.CodigosBD;

/**Classe para objetos do tipo Produtos, onde serão contidos, valores e métodos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */

public class Produto {
	
	private int codigo;
	private String nome;
	private int quantidade;
	private double preco;
	
	public Produto() throws SQLException, ClassNotFoundException{
		//CodigosBD codigo = new CodigosBD();
		
		//this.codigo = codigo.buscar("cod_produto").getCodigo_produto();
	}
	
	public Produto( int cod,String nome,int quantidade,double preco){
		this.codigo=cod;
		this.nome=nome;
		this.quantidade=quantidade;
		this.preco=preco;

	}

	
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
	public int getCodigo(){
		return this.codigo;
	}
	public void setCodigo(int codigo){
		this.codigo = codigo;
	}

}
