package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dados.exception.*;

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
		
		
			inserir = con.prepareStatement("INSERT INTO produto(id,nome,quantidade,preco_unitario) "
					+ "VALUE (?,?,?,?)");
			remover = con.prepareStatement("DELETE FROM produto WHERE nome = ?");
			buscar = con.prepareStatement("SELECT * FROM produto WHERE cod = ?");
			listar = con.prepareStatement("SELECT * FROM produto");
			buscarnome = con.prepareStatement("SELECT * FROM produto WHERE nome= ?");
			atualizar = con.prepareStatement("UPDATE produto SET nome = ?, quantidade = ?, preco_unitario = ? WHERE id = ? ");
			
			
	}
	
	
	public void inserir(Produto produto) throws SQLException,InserirProdutoErro{
		
	
			inserir.setInt(1, produto.getCodigo());
			inserir.setString(1, produto.getNome());
			inserir.setInt(2, produto.getQuantidade());
			inserir.setDouble(1, produto.getPreco());
		
			
			if(!inserir.execute()){
				throw new InserirProdutoErro();
			};
		
			
		
	}
	public boolean existe(String nome) throws SQLException{
		boolean existe = false;
		
				buscarnome.setString(1,nome);
				if((rs = buscarnome.executeQuery())!=null){
					existe = true;
				}
			
			
		 return existe;
		
	}
	
	public Produto buscar(int id) throws SQLException, ClassNotFoundException,BuscaProdutoErro{
		Produto prod = null;

			buscar.setInt(1, id);
			
			if((rs = buscar.executeQuery())!=null){;
				prod = new Produto();
				prod.setCodigo(rs.getInt("id"));
				prod.setQuantidade(rs.getInt("nome"));
				prod.setPreco(rs.getDouble("quantidade"));
				prod.setNome(rs.getString("preco_unitario"));
				
				return prod;
			}else{
				throw new BuscaProdutoErro();
			}
			
		
		
	}
	public Produto buscar(String nome) throws SQLException, ClassNotFoundException,BuscaProdutoErro{
		Produto prod = null;
	
			buscarnome.setString(1, nome);
			
			if((rs = buscarnome.executeQuery())!=null){;
				prod = new Produto();
				prod.setCodigo(rs.getInt("id"));
				prod.setQuantidade(rs.getInt("quantidade"));
				prod.setPreco(rs.getDouble("preco"));
				prod.setNome(rs.getString("nome"));
				return prod;
					
			}else{
				throw new BuscaProdutoErro();
			}
			
	}
	
	
	public void remover( int id) throws SQLException, ClassNotFoundException,RemoverProdutoErro, BuscaProdutoErro{
	

			remover.setString(1, this.buscar(id).getNome());
			if(!remover.execute()){
				throw new RemoverProdutoErro();
			}
		
	}
	
	public void atualizar(Produto produto) throws SQLException, ClassNotFoundException,AtualizarProdutoErro, BuscaProdutoErro{
		buscar(produto.getCodigo());
		atualizar.setString(1, produto.getNome());
		atualizar.setInt(2, produto.getQuantidade());
		atualizar.setDouble(3, produto.getPreco());
		atualizar.setInt(4, produto.getCodigo());
		
		if(!atualizar.execute()){
			throw new AtualizarProdutoErro();
		}
			
	}
	
	public int buscarQuantidade(String nome) throws SQLException, ClassNotFoundException, BuscaProdutoErro{
		Produto auxproduto = null;
		int aux = 0;
		
		auxproduto = buscar(nome);
		aux = auxproduto.getQuantidade();
		return aux;
	}
	
	
	public boolean atualizarQuatidade(int quantidade,int cod) throws SQLException{
		boolean atualizado = false;
	
			atualizar.setInt(1, quantidade);
			atualizar.setInt(2, cod);
			if(atualizar.execute()){
				atualizado = true;
			}
		
		return atualizado;
	}
	
	public List<Produto> listarProdBD() throws SQLException,ListarProdutoErro{
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
				return produtos;
			}else{
				throw new ListarProdutoErro();
			}
		
		
		
	}
	
}
