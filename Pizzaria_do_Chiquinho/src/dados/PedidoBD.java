package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import principal.Cliente;
import principal.Funcionario;
import principal.Itens_pedido;
import principal.Pedido;

public class PedidoBD {
	
	private PreparedStatement inserir = null;
	private PreparedStatement remover = null;
	private PreparedStatement listar = null;
	private PreparedStatement buscar = null;
	private PreparedStatement buscarcod = null;
	private ResultSet rs = null;
	private Connection con = null;
	private ClienteBD clienteBD;
	private FuncionarioBD funcBD;
	private Itens_pedidoBD itens ;
	
	
	public PedidoBD(){
		
		con = ConexaoBD.getConnection();
		
		try {
			inserir = con.prepareStatement("INSERT INTO Pedido(cod,cpf_cliente,cpf_funcionario,obs,status,total) "
					+ "VALUE (?,?,?,?,?,?)");
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
		itens = new Itens_pedidoBD();
		
		boolean inserido = false;
		try {
			inserir.setInt(1, pedido.getCodigo());
			inserir.setString(1,pedido.getCliente().getCpf());
			inserir.setString(2, pedido.getFunc().getCpf());
			inserir.setString(3,pedido.getObs());
			inserir.setString(4, pedido.getStatus());
			inserir.setDouble(5, pedido.getTotal());
			
			inserido = inserir.execute();
			if(!itens.inserirProdutos(pedido.getCodigo(), pedido.getListadeprodutos())){
				inserido = false;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return inserido;
		
		
	}
	public boolean existeBD(int codigo){
		boolean existe = false;
		try {
			buscarcod.setInt(1, codigo);
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
	public Pedido buscarPedidoBD(int cod){
		Pedido pedido = null;
		clienteBD = new ClienteBD();
		funcBD = new FuncionarioBD();
		itens = new Itens_pedidoBD();
		
		try {
			buscar.setInt(1, cod);
			
			if((rs = buscar.executeQuery())!=null){;
				pedido = new Pedido();
				pedido.setCodigo(rs.getInt("cod"));
				pedido.setCliente(clienteBD.buscarClienBD(rs.getString("cpf_cliente")));;
				pedido.setFunc(funcBD.buscarFuncBD(rs.getString("nome_func")));
				pedido.setStatus(rs.getString("status"));
				pedido.setObs(rs.getString("obs"));
				pedido.setTotal(rs.getFloat("total"));
				
				pedido.setListadeprodutos(itens.buscarProdutos(pedido.getCodigo()));
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
		itens = new Itens_pedidoBD();
		
		pedido = this.buscarPedidoBD(cod);
		try {
			remover.setInt(1, pedido.getCodigo());
			itens.removerProdutos(pedido.getCodigo());
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
		clienteBD = new ClienteBD();
		funcBD = new FuncionarioBD();
		itens = new Itens_pedidoBD();
		
		
		try {
			if((rs = listar.executeQuery())!=null){
				pedidos = new ArrayList<Pedido>();
				while(rs.next()){
					pedidos.add(new Pedido(
							rs.getInt("cod"),
							clienteBD.buscarClienBD(rs.getString("cpf_cliente")),
							funcBD.buscarFuncBD(rs.getString("cpf_funcionario")),
							rs.getString("obs"),
							rs.getString("status"),
							rs.getDouble("total_pedido"),
							itens.buscarProdutos(rs.getInt("cod"))));
							
							
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pedidos;
	}
}
	


