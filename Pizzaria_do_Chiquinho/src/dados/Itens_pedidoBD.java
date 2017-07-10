package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	
	
	public Itens_pedidoBD(){
		
	    
		con = ConexaoBD.getConnection();
		
		try {
			inserir = con.prepareStatement("INSERT INTO itens_pedido(cod_pedido,cod_produto,quantidade) "
					+ "VALUE (?,?,?,?)");
			remover = con.prepareStatement("DELETE FROM item_pedido WHERE cod_pedido = ?");
			buscar = con.prepareStatement("SELECT * FROM item_pedido WHERE cod_pedido = ?");
			listar = con.prepareStatement("SELECT * FROM item_pedido");
			buscarprod = con.prepareStatement("SELECT * FROM item_pedido WHERE cod_pedido = ? AND cod_produto = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("problemas com o drive de Banco de dados");
			e.printStackTrace();
			System.exit(1);
		}
	}
	public boolean inserirProdutos(String codigo, Itens_pedido listadeprodutos){
		boolean inserido = false;
		for(int i = 0;i< listadeprodutos.tamanho();i++){
			try {
				inserir.setString(1, codigo);
				inserir.setInt(2, listadeprodutos.getProduto(i).getCodigo());
				inserir.setInt(3, listadeprodutos.getProduto(i).getQuantidade());
				
				inserido = inserir.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
	
		return inserido;
	}
	public Itens_pedido buscarProdutos(String codigo){
		prod = null;
		itens = new Itens_pedido();
		try {
			buscar.setString(1, codigo);
			rs = buscar.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			prod = new ProdutoBD();
			while(rs.next()){
				itens.addProduto(new Produto(rs.getInt("cod_produto"),prod.buscarProdBD(rs.getInt("cod_produto")).getNome(),
						rs.getInt("quantidade"),prod.buscarProdBD(rs.getInt("cod_produto")).getPreco()));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return itens;
	
	}
	public void removerProdutos(String codigo ){
		
		
		
		
	}
	
	public boolean existeProduto(String codigo,int codproduto){
		boolean existe = false;
		
		try {
			buscarprod.setString(1, codigo);
			buscarprod.setInt(2,codproduto);
			if((rs = buscarprod.executeQuery())!=null){
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
	

}
