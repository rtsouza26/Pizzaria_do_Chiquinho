package negocio;

import java.sql.SQLException;
import java.util.List;

import dados.PedidoBD;
import dados.exception.BuscaProdutoErro;
import dados.exception.BuscarClienteErro;
import dados.exception.BuscarFuncionarioErro;
import dados.exception.RemoverItem_pedidoErro;
import principal.Pedido;

public class CadastroPedido {
	
	private PedidoBD pedidoBD;
	private String invalido = "Pedido Inv�lido";
	private String n�oexiste = "Pedido n�o existe";
	
	
	public CadastroPedido() throws ClassNotFoundException, SQLException{
		pedidoBD = new PedidoBD();
	}
	
	
	public void inserirPedido(Pedido pedido) throws ClassNotFoundException, SQLException, BuscarFuncionarioErro, BuscarClienteErro{
		if(pedido!= null){
			
			if(!(pedidoBD.inserirPedidoBD(pedido))){
				System.out.println("Pedido cadastrado com sucesso");
			}else{
				System.out.println("N�o foi poss�vel cadastrar o pedido" );
			}
			
		}else{
			System.out.println(invalido);
		}
	}
	
	public Pedido buscarPedido(String cod) throws ClassNotFoundException, SQLException, BuscaProdutoErro, BuscarFuncionarioErro, BuscarClienteErro{
		Pedido aux = null;
		if(Integer.parseInt(cod)>0){
			if(pedidoBD.existeBD(cod)){
				aux = new Pedido();
				aux = pedidoBD.buscarPedidoBD(cod);
			}else{
				System.out.println(n�oexiste);
			}
		}else{
			System.out.println(invalido);
		}
		return aux;
	}
	
	public void removerPedido(String cod) throws ClassNotFoundException, SQLException, BuscaProdutoErro, BuscarFuncionarioErro, BuscarClienteErro, RemoverItem_pedidoErro{
		
		if (Integer.parseInt(cod)>0){
			if(pedidoBD.existeBD(cod)){
				if(pedidoBD.removerPedidoBD(cod)){
					System.out.println("Pedido removido com sucesso");
				}else{
					System.out.println("N�o foi poss�vel excluir o pedido");
				}
			}else{
				System.out.println(n�oexiste);
			}
		}else{
			System.out.println(invalido);
		}
	}
	
	public void atualizarPedido(Pedido pedido) throws ClassNotFoundException, SQLException, BuscaProdutoErro, BuscarFuncionarioErro, BuscarClienteErro, RemoverItem_pedidoErro{
		
		if((Integer.parseInt(pedido.getCodigo()))>0){
			if(pedidoBD.existeBD(pedido.getCodigo())){
				if(pedidoBD.atualizarPedidoBD(pedido)){
					System.out.println("Pedido atualizao com sucesso");
				}else{
					System.out.println("N�o foi poss�vel atualizar o pedido");
				}
			}else{
				System.out.println(n�oexiste);
			}
		}else{
			System.out.println(invalido);
		}
	}
	public List<Pedido> listarPedido() throws ClassNotFoundException, SQLException, BuscaProdutoErro, BuscarFuncionarioErro, BuscarClienteErro{
		
		return pedidoBD.listarPedidoBD();
		
		
	}
}
