package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import principal.Produto;

/**Classe para a conexão do classe funcionários com o banco de dados, onde serão contidos, valores e métodos para o mesmo.
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
	private ResultSet rs = null;
	private Connection con = null;
	
	
	public ProdutoBD(){
		
		con = ConexaoBD.getConnection();
		
		try {
			inserir = con.prepareStatement("INSERT INTO produto(nome,preco,quantidade) "
					+ "VALUE (?,?,?)");
			remover = con.prepareStatement("DELETE FROM produto WHERE nome = ?");
			buscar = con.prepareStatement("SELECT * FROM produto WHERE nome = ?");
			listar = con.prepareStatement("SELECT * FROM produto");
			buscarnome = con.prepareStatement("SELECT * FROM produto WHERE nome = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("problemas com o drive de Bnco de dados");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public boolean inserirprodBD(String nome, double preco, int quantidade){
		
		boolean inserido = false;
		try {
			inserir.setString(1, nome);
			inserir.setDouble(2, preco);
			inserir.setInt(3,quantidade);
		
			
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
	public boolean ExisteBD(String nome){
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
	
	public Produto BuscarProdBD(String nome){
		Produto prod = null;
		try {
			buscar.setString(1, nome);
			
			if((rs = buscar.executeQuery())!=null){;
				prod = new Produto();
				prod.setCodigo(rs.getInt("cod"));
				prod.setQuantidade(rs.getInt("quantidade"));
				prod.setPreco(rs.getDouble("preco"));
				prod.setString(rs.getString("nome"));
				
					
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
		
		prod = this.BuscarProdBD(nome);
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
