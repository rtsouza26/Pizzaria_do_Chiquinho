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
	
	
	public ProdutoBD(){
		
		con = ConexaoBD.getConnection();
		
		try {
			inserir = con.prepareStatement("INSERT INTO Produtos(nome,preco,quantidade) "
					+ "VALUE (?,?,?)");
			remover = con.prepareStatement("DELETE FROM Produtos WHERE nome = ?");
			buscar = con.prepareStatement("SELECT * FROM Produtos WHERE cod = ?");
			listar = con.prepareStatement("SELECT * FROM Produtos");
			buscarnome = con.prepareStatement("SELECT * FROM Produtos WHERE nome= ?");
			atualizar = con.prepareStatement("UPDATE Produtos SET quantidade = ? WHERE cod = ?");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("problemas com o drive de Banco de dados");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public boolean inserirProdBD(Produto produto){
		
		boolean inserido = false;
		try {
			inserir.setString(1, produto.getNome());
			inserir.setDouble(2, produto.getPreco());
			inserir.setInt(3,produto.getQuantidade());
		
			
			inserido = inserir.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ConexaoBD.closeConnection(con, inserir);
			e.printStackTrace();
			
		}
		finally{
			ConexaoBD.closeConnection(con);
		}
			
		return inserido;
	}
	public boolean existeBD(String nome){
		boolean existe = false;
			try {
				buscarnome.setString(1,nome);
				if((rs = buscarnome.executeQuery())!=null){
					existe = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				ConexaoBD.closeConnection(con);
			}
			
		 return existe;
		
	}
	
	public Produto buscarProdBD(int cod){
		Produto prod = null;
		try {
			buscar.setInt(1, cod);
			
			if((rs = buscar.executeQuery())!=null){;
				prod = new Produto();
				prod.setCodigo(rs.getInt("cod"));
				prod.setQuantidade(rs.getInt("quantidade"));
				prod.setPreco(rs.getDouble("preco"));
				prod.setNome(rs.getString("nome"));
				
					
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		finally
		{
			ConexaoBD.closeConnection(con,rs);
		}
		
		return prod;
	}
	public Produto buscarProdBD(String nome){
		Produto prod = null;
		try {
			buscarnome.setString(1, nome);
			
			if((rs = buscarnome.executeQuery())!=null){;
				prod = new Produto();
				prod.setCodigo(rs.getInt("cod"));
				prod.setQuantidade(rs.getInt("quantidade"));
				prod.setPreco(rs.getDouble("preco"));
				prod.setNome(rs.getString("nome"));
				
					
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		finally
		{
			ConexaoBD.closeConnection(con,rs);
		}
		
		return prod;
	}
	
	
	public boolean removerProdBD( String nome){
		boolean removido = false;
		Produto prod = new Produto();
		
		prod = this.buscarProdBD(nome);
		try {
			remover.setString(1, prod.getNome());
			removido = remover.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ConexaoBD.closeConnection(con, remover);
			e.printStackTrace();
			
		}
				
		return removido;
	}
	
	public boolean atualizarPedidoBD(Produto produto){
		boolean atualizado = false;
		
		if(this.removerProdBD(produto.getNome())){
			if(this.inserirProdBD(produto)){
				atualizado = true;
			}
		}
		
		
		return atualizado;
	}
	
	public int buscarQuantidadeBD(String nome){
		Produto auxproduto = null;
		int aux = -1;
		
		auxproduto = buscarProdBD(nome);
		aux = auxproduto.getQuantidade();
		return aux;
	}
	
	
	public boolean atualizarQuatidadeBD(int quantidade,int cod){
		boolean atualizado = false;
		try {
			atualizar.setInt(1, quantidade);
			atualizar.setInt(2, cod);
			if(atualizar.execute()){
				atualizado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return atualizado;
	}
	
	public List<Produto> listarProdBD(){
		List<Produto> produtos = null;
		
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return produtos;
	}
	
}
