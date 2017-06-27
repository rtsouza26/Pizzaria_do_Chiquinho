package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import principal.Funcionario;
import principal.Pedido;

public class PedidoBD {
	
	private PreparedStatement inserir = null;
	private PreparedStatement remover = null;
	private PreparedStatement listar = null;
	private PreparedStatement buscar = null;
	private PreparedStatement buscarcod = null;
	private ResultSet rs = null;
	private Connection con = null;
	private ClienteBD clienteBD = new ClienteBD();
	private FuncionariosBD funcBD = new FuncionariosBD();
	
	
	public PedidoBD(){
		
		con = ConexaoBD.getConnection();
		
		try {
			inserir = con.prepareStatement("INSERT INTO Pedido(cliente,funcionario,obs,status) "
					+ "VALUE (?,?,?,?,?,?,?)");
			remover = con.prepareStatement("DELETE FROM Pedido WHERE cod = ?");
			buscar = con.prepareStatement("SELECT * FROM Pedido WHERE cod = ?");
			buscarcod = con.prepareStatement("SELECT * FROM Pedido WHERE cod = ?");
			listar = con.prepareStatement("SELECT * FROM Pedido");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("problemas com o drive de Banco de dados");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public boolean inserirPedidoBD(Pedido pedido){
		
		return true;
		
	}
	public boolean existeBD(int cod){
		boolean existe = false;
		try {
			buscarcod.setInt(1, cod);
			if((rs = buscarcod.executeQuery())!=null){
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
	public Pedido BuscarPedidoBD(int cod){
		Pedido pedido = null;
		
		try {
			buscar.setInt(1, cod);
			
			if((rs = buscar.executeQuery())!=null){;
				pedido = new Pedido();
				pedido.setCodigo(rs.getInt("cod"));
				pedido.setCliente(clienteBD.buscarClienBD(rs.getInt("cpf_cliente")));;
				pedido.setFunc(funcBD.BuscarFuncBD(rs.getString("nome_func")));
				pedido.setStatus(rs.getString("status"));
				pedido.setObs(rs.getString("obs"));
				pedido.setTotal(rs.getFloat("total"));
				
					
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		finally
		{
			ConexaoBD.closeConnection(con,rs);
		}
		
		return pedido;
	}
	
	public boolean removerPedidoBD( int cod){
		boolean removido = false;
		Pedido pedido = new Pedido();
		
		pedido = this.BuscarPedidoBD(cod);
		try {
			remover.setInt(1, pedido.getCodigo());
			removido = remover.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ConexaoBD.closeConnection(con, remover);
			e.printStackTrace();
			
		}
				
		return removido;
	}
	
	public List<Pedido> listarPedidoBD(){
		List<Pedido> pedidos = null;
		
		try {
			if((rs = listar.executeQuery())!=null){
				pedidos = new ArrayList<Pedido>();
				while(rs.next()){
					pedidos.add(new Pedido(
							rs.getInt("cod"),
							rs.getString("nome"),
							rs.getString("endereco"),
							rs.getInt("cpf"),
							rs.getString("telefone"),
							rs.getString("tipo"),
							rs.getString("login")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return funcionarios;
	}
}
	


