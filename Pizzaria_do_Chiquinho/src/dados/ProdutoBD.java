package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dados.exception.*;

import java.util.ArrayList;

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
	private ResultSet rs = null;
	private Connection con = null;
	
	
	public ProdutoBD() throws ClassNotFoundException, SQLException{
		
		con = ConexaoBD.getConnection();
		
		
			inserir = con.prepareStatement("INSERT INTO produtos(nome,quantidade,preco_unitario) "
					+ "VALUE (?,?,?)");
			remover = con.prepareStatement("DELETE FROM produtos WHERE nome = ?");
			buscar = con.prepareStatement("SELECT * FROM produtos WHERE nome = ?");
			listar = con.prepareStatement("SELECT * FROM produtos");
			buscarnome = con.prepareStatement("SELECT * FROM produtos WHERE nome= ?");
			atualizar = con.prepareStatement("UPDATE produtos SET quantidade = ?, preco_unitario = ? WHERE nome = ? ");
			
			
	}
	
	//Função testada e duncionando (Mas precisa ser otimizada)
	public void inserirProdutoBD(Produto produto) throws SQLException,InserirProdutoErro{
				
			if(produto != null){
				
				//inserir.setInt(1, produto.getCodigo());
				inserir.setString(1, produto.getNome());
				inserir.setInt(2, produto.getQuantidade());
				inserir.setDouble(3, produto.getPreco());
				inserir.executeUpdate();
			}else{				
				throw new InserirProdutoErro();
			}
	
	}

	//Função testada e funcionando !!!
	public boolean existeProdutoBD(String nome) throws SQLException{
		boolean existe = false;
				
				rs = null;
				buscarnome.setString(1,nome);
				rs = buscarnome.executeQuery();
				
				if(rs.next()){
					existe = true;
				}
			
		 return existe;	
	}
	
	//Função testada e funcionando !!!
	public Produto buscarProdutoBD(String nome) throws SQLException, ClassNotFoundException,BuscaProdutoErro{
		Produto prod = null;
			
			rs = null;
			buscar.setString(1, nome);
			rs = buscar.executeQuery();
			
			if(rs.next()){
				prod = new Produto();
				prod.setCodigo(rs.getInt("idproduto"));
				prod.setQuantidade(rs.getInt("quantidade"));
				prod.setPreco(rs.getDouble("preco_unitario"));
				prod.setNome(rs.getString("nome"));
				
				return prod;
			}else{
				throw new BuscaProdutoErro();
			}
	}
	
	//Função testada e funcionando (Mas precisa ser otimizada)
	public boolean removerProdutoBD(String nome) throws SQLException, ClassNotFoundException,RemoverProdutoErro, BuscaProdutoErro{
		boolean removido = false;

			if(nome != null){
				
				remover.setString(1, this.buscarProdutoBD(nome).getNome());
				removido = remover.execute();
				removido = true;
			}else{
				
				throw new RemoverProdutoErro();
				
			}
			
		return removido;
				
	}
	
	//Função testada e funcionando !!!
	public void atualizarProdutoBD(Produto produto) throws SQLException, ClassNotFoundException,AtualizarProdutoErro, BuscaProdutoErro{
		
		if(this.existeProdutoBD(produto.getNome())){
			
			atualizar.setInt(1, produto.getQuantidade());
			atualizar.setDouble(2, produto.getPreco());
			atualizar.setString(3, produto.getNome());
			atualizar.executeUpdate();

		}else{
			throw new AtualizarProdutoErro();
		}

	}
	
	//Função testada e funcionando !!!
	public int buscarQuantidade(String nome) throws SQLException, ClassNotFoundException, BuscaProdutoErro{
		int quantidade;
		
		if(this.buscarProdutoBD(nome) != null){
			
			quantidade = this.buscarProdutoBD(nome).getQuantidade();
			return quantidade;
			
		}else{		
			throw new BuscaProdutoErro();	
		}	
	}
	
	//Função testada e funcionando !!!
	public boolean atualizarQuatidade(String nome, int quantidade) throws SQLException, AtualizarProdutoErro, ClassNotFoundException, BuscaProdutoErro{
		boolean atualizado = false;
	
		if(this.existeProdutoBD(nome)){
			
			atualizar.setInt(1, quantidade);
			atualizar.setDouble(2, this.buscarProdutoBD(nome).getPreco());
			atualizar.setString(3, nome);
			atualizar.executeUpdate();
			atualizado = true;

		}else{
			throw new AtualizarProdutoErro();
		}

		return atualizado;
	}
	
	//Função testada e funcionando !!!
	public List<Produto> listarProdBD() throws SQLException,ListarProdutoErro, ClassNotFoundException{
		List<Produto> produtos = null;
			
			rs = null;
			rs = listar.executeQuery();
		
			if(rs != null){
				produtos = new ArrayList<Produto>();
				while(rs.next()){
					Produto produtoaux = new Produto();
					
					produtoaux.setCodigo(rs.getInt("idproduto"));
					produtoaux.setNome(rs.getString("nome"));
					produtoaux.setQuantidade(rs.getInt("quantidade"));
					produtoaux.setPreco(rs.getDouble("preco_unitario"));
					produtos.add(produtoaux);
			
				}
				
				return produtos;
				
			}else{
				throw new ListarProdutoErro();
			}
	}
}
