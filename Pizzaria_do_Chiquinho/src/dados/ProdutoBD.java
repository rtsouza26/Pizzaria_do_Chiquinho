package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import principal.Pedido;
import principal.Produto;

/**Classe para a conexão do classe produto com o banco de dados, onde serão contidos, valores e métodos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */

public class ProdutoBD {

	private PreparedStatement inserir = null;
	private PreparedStatement remover = null;
	private PreparedStatement listar = null;
	private PreparedStatement buscar = null;
	private PreparedStatement buscarnome = null;
	private PreparedStatement atualizar = null;
	private PreparedStatement buscarquantidade = null;
	private ResultSet rs = null;
	private Connection con = null;
	
	
	public ProdutoBD() throws ClassNotFoundException, SQLException{
		
		con = ConexaoBD.getConnection();
		
		
			inserir = con.prepareStatement("INSERT INTO Produtos(nome,preco,quantidade) "
					+ "VALUE (?,?,?)");
			remover = con.prepareStatement("DELETE FROM Produtos WHERE nome = ?");
			buscar = con.prepareStatement("SELECT * FROM Produtos WHERE cod = ?");
			listar = con.prepareStatement("SELECT * FROM Produtos");
			buscarnome = con.prepareStatement("SELECT * FROM Produtos WHERE nome= ?");
			atualizar = con.prepareStatement("UPDATE Produtos SET quantidade = ? WHERE cod = ?");
			
	}
	
	
	public boolean inserirProdBD(Produto produto) throws SQLException{
		
		boolean inserido = false;
	
			inserir.setString(1, produto.getNome());
			inserir.setDouble(2, produto.getPreco());
			inserir.setInt(3,produto.getQuantidade());
		
			
			inserido = inserir.execute();
		
			
		return inserido;
	}
	public boolean existeBD(String nome) throws SQLException{
		boolean existe = false;
		
				buscarnome.setString(1,nome);
				if((rs = buscarnome.executeQuery())!=null){
					existe = true;
				}
			
			
		 return existe;
		
	}
	
	public Produto buscarProdBD(int cod) throws SQLException{
		Produto prod = null;

			buscar.setInt(1, cod);
			
			if((rs = buscar.executeQuery())!=null){;
				prod = new Produto();
				prod.setCodigo(rs.getInt("cod"));
				prod.setQuantidade(rs.getInt("quantidade"));
				prod.setPreco(rs.getDouble("preco"));
				prod.setNome(rs.getString("nome"));
				
					
			}
			
		
		return prod;
	}
	public Produto buscarProdBD(String nome) throws SQLException{
		Produto prod = null;
	
			buscarnome.setString(1, nome);
			
			if((rs = buscarnome.executeQuery())!=null){;
				prod = new Produto();
				prod.setCodigo(rs.getInt("cod"));
				prod.setQuantidade(rs.getInt("quantidade"));
				prod.setPreco(rs.getDouble("preco"));
				prod.setNome(rs.getString("nome"));
				
					
			}
			
	
		
		return prod;
	}
	
	
	public boolean removerProdBD( String nome) throws SQLException{
		boolean removido = false;
		Produto prod = new Produto();
		
		prod = this.buscarProdBD(nome);

			remover.setString(1, prod.getNome());
			removido = remover.execute();
		
				
		return removido;
	}
	
	public boolean atualizarPedidoBD(Produto produto) throws SQLException{
		boolean atualizado = false;
		
		if(this.removerProdBD(produto.getNome())){
			if(this.inserirProdBD(produto)){
				atualizado = true;
			}
		}
		
		
		return atualizado;
	}
	
	public int buscarQuantidadeBD(String nome) throws SQLException{
		Produto auxproduto = null;
		int aux = -1;
		
		auxproduto = buscarProdBD(nome);
		aux = auxproduto.getQuantidade();
		return aux;
	}
	
	
	public boolean atualizarQuatidadeBD(int quantidade,int cod) throws SQLException{
		boolean atualizado = false;
	
			atualizar.setInt(1, quantidade);
			atualizar.setInt(2, cod);
			if(atualizar.execute()){
				atualizado = true;
			}
		
		return atualizado;
	}
	
	public List<Produto> listarProdBD() throws SQLException{
		List<Produto> produtos = null;
		
		
			if((rs = listar.executeQuery())!=null){
				produtos = new ArrayList<Produto>();
				while(rs.next()){
					produtos.add(new Produto(
							rs.getInt("cod"),
							rs.getString("nome"),
							rs.getInt("quantidade"),
							rs.getDouble("preco")));
				}
			}
		
		
		return produtos;
	}
	
}
