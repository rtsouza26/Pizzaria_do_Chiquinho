package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dados.exception.BuscaProdutoErro;
import principal.Itens_pedido;
import principal.Produto;


public class Itens_pedidoBD{
	
	private PreparedStatement inserir = null;
	private PreparedStatement remover = null;
	private PreparedStatement listar = null;
	private PreparedStatement buscar = null;
	private PreparedStatement buscarprod = null;
	private ResultSet rs = null;
	private Connection con = null;
	private ProdutoBD prod = null;
	private Itens_pedido itens = new Itens_pedido();
	
	
	public Itens_pedidoBD() throws ClassNotFoundException, SQLException{
		
	    
		con = ConexaoBD.getConnection();
		
			inserir = con.prepareStatement("INSERT INTO itens_pedido(cod_pedido,cod_produto,quantidade) "
					+ "VALUE (?,?,?,?)");
			remover = con.prepareStatement("DELETE FROM item_pedido WHERE cod_pedido = ?");
			buscar = con.prepareStatement("SELECT * FROM item_pedido WHERE cod_pedido = ?");
			listar = con.prepareStatement("SELECT * FROM item_pedido");
			buscarprod = con.prepareStatement("SELECT * FROM item_pedido WHERE cod_pedido = ? AND cod_produto = ?");
		
	}
	public boolean inserirProdutos(String codigo, Itens_pedido listadeprodutos) throws SQLException{
		boolean inserido = false;
		for(int i = 0;i< listadeprodutos.tamanho();i++){
			
				inserir.setString(1, codigo);
				inserir.setInt(2, listadeprodutos.getProduto(i).getCodigo());
				inserir.setInt(3, listadeprodutos.getProduto(i).getQuantidade());
				
				inserido = inserir.execute();
				
			
		}
	
		return inserido;
	}
	public Itens_pedido buscarProdutos(String codigo) throws SQLException, ClassNotFoundException, BuscaProdutoErro{
		prod = null;
		itens = new Itens_pedido();
		
			buscar.setString(1, codigo);
			rs = buscar.executeQuery();
		
		
		
			prod = new ProdutoBD();
			while(rs.next()){
				itens.addProduto(new Produto(rs.getInt("cod_produto"),prod.buscar(rs.getInt("cod_produto")).getNome(),
						rs.getInt("quantidade"),prod.buscar(rs.getInt("cod_produto")).getPreco()));
				
			}
		
		
		
		return itens;
	
	}
	public void removerProdutos(String codigo ){
		
		
		
		
	}
	
	public boolean existeProduto(String codigo,int codproduto) throws SQLException{
		boolean existe = false;
		
		
			buscarprod.setString(1, codigo);
			buscarprod.setInt(2,codproduto);
			if((rs = buscarprod.executeQuery())!=null){
				existe = true;
			}
		
		
		
		return existe;
	}
	

}
