package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.exception.BuscaProdutoErro;
import dados.exception.BuscarClienteErro;
import dados.exception.BuscarFuncionarioErro;
import dados.exception.RemoverItem_pedidoErro;
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
	
//Eu precisei mexer em pedidoBD e cadastro pedido pois haviam métodos que chamavam métodos de funcionários, que é da minha parte, eu apenas coloquei os exceptions de Funcionários que era preciso	
	public PedidoBD() throws ClassNotFoundException, SQLException{
		
		con = ConexaoBD.getConnection();
		
	
			inserir = con.prepareStatement("INSERT INTO Pedido(cod,cpf_cliente,cpf_funcionario,obs,status,total) "
					+ "VALUE (?,?,?,?,?,?)");
			remover = con.prepareStatement("DELETE FROM Pedido WHERE cod = ?");
			buscar = con.prepareStatement("SELECT * FROM Pedido WHERE cod = ?");
			buscarcod = con.prepareStatement("SELECT * FROM Pedido WHERE cod = ?");
			listar = con.prepareStatement("SELECT * FROM Pedido");
		
	}
	
	public boolean inserirPedidoBD(Pedido pedido) throws ClassNotFoundException, SQLException{
		itens = new Itens_pedidoBD();
		
		boolean inserido = false;
		
			inserir.setString(1, pedido.getCodigo());
			inserir.setString(1,pedido.getCliente().getCpf());
			inserir.setString(2, pedido.getFunc().getCpf());
			inserir.setString(3,pedido.getObs());
			inserir.setString(4, pedido.getStatus());
			inserir.setDouble(5, pedido.getTotal());
			
			inserido = inserir.execute();
			if(!itens.inserirProdutos(pedido.getCodigo(), pedido.getListadeprodutos())){
				inserido = false;
			}
			
		return inserido;
		
		
	}
	public boolean existeBD(String codigo) throws SQLException{
		boolean existe = false;
		
			buscarcod.setString(1, codigo);
			if((rs = buscarcod.executeQuery())!=null){
				existe = true;
			}
		
	 return existe;
		
		
	}
	public Pedido buscarPedidoBD(String cod) throws ClassNotFoundException, SQLException, BuscaProdutoErro, BuscarFuncionarioErro, BuscarClienteErro{
		Pedido pedido = null;
		clienteBD = new ClienteBD();
		funcBD = new FuncionarioBD();
		itens = new Itens_pedidoBD();
		
				buscar.setString(1, cod);
			
			if((rs = buscar.executeQuery())!=null){;
				pedido = new Pedido();
				pedido.setCodigo(rs.getString("cod"));
				pedido.setCliente(clienteBD.buscarClienBD(rs.getString("cpf_cliente")));;
				pedido.setFunc(funcBD.buscarFuncBD(rs.getString("nome_func")));
				pedido.setStatus(rs.getString("status"));
				pedido.setObs(rs.getString("obs"));
				pedido.setTotal(rs.getFloat("total"));
				
				pedido.setListadeprodutos(itens.buscarProdutos(pedido.getCodigo()));
			}
		
		
		return pedido;
	}
	
	public boolean removerPedidoBD( String cod) throws ClassNotFoundException, SQLException, BuscaProdutoErro, BuscarFuncionarioErro, BuscarClienteErro, RemoverItem_pedidoErro{
		boolean removido = false;
		Pedido pedido = new Pedido();
		itens = new Itens_pedidoBD();
		
		pedido = this.buscarPedidoBD(cod);
	
			remover.setString(1, pedido.getCodigo());
			itens.removerProdutos(pedido.getCodigo());
			removido = remover.execute();
	
				
		return removido;
	}
	
	public boolean atualizarPedidoBD(Pedido pedido) throws ClassNotFoundException, SQLException, BuscaProdutoErro, BuscarFuncionarioErro, BuscarClienteErro, RemoverItem_pedidoErro{
		boolean atualizado = false;
		
		if(this.removerPedidoBD(pedido.getCodigo())){
			if(this.inserirPedidoBD(pedido)){
				atualizado = true;
			}
		}
		
		
		return atualizado;
	}
	
	public List<Pedido> listarPedidoBD() throws ClassNotFoundException, SQLException, BuscaProdutoErro, BuscarFuncionarioErro, BuscarClienteErro{
		List<Pedido> pedidos = null;
		clienteBD = new ClienteBD();
		funcBD = new FuncionarioBD();
		itens = new Itens_pedidoBD();
		
		
		
			if((rs = listar.executeQuery())!=null){
				pedidos = new ArrayList<Pedido>();
				while(rs.next()){
					pedidos.add(new Pedido(
							rs.getString("cod"),
							clienteBD.buscarClienBD(rs.getString("cpf_cliente")),
							funcBD.buscarFuncBD(rs.getString("cpf_funcionario")),
							rs.getString("obs"),
							rs.getString("status"),
							rs.getDouble("total_pedido"),
							itens.buscarProdutos(rs.getString("cod"))));
							
							
				}
			}
		
		
		return pedidos;
	}
}
	


