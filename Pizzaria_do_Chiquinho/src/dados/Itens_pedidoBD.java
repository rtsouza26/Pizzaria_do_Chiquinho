package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import principal.Item_pedido;


public class Itens_pedidoBD{
	
	private PreparedStatement inserir = null;
	private PreparedStatement remover = null;
	private PreparedStatement listar = null;
	private PreparedStatement buscar = null;
	private PreparedStatement buscarprod = null;
	private ResultSet rs = null;
	private Connection con = null;
	
	
	public Itens_pedidoBD(){
		
		con = ConexaoBD.getConnection();
		
		try {
			inserir = con.prepareStatement("INSERT INTO item_pedido(id,nome,preco,quantidade) "
					+ "VALUE (?,?,?,?)");
			remover = con.prepareStatement("DELETE FROM item_pedido WHERE nome = ?");
			buscar = con.prepareStatement("SELECT * FROM item_pedido WHERE nome = ?");
			listar = con.prepareStatement("SELECT * FROM item_pedido");
			buscarprod = con.prepareStatement("SELECT * FROM item_pedido WHERE nome = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("problemas com o drive de Banco de dados");
			e.printStackTrace();
			System.exit(1);
		}
	}
	public void cadastrarItem(Produto prod){
		boolean inserido = false;
		try {
			inserir.setString(1, prod.getId);
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

}
