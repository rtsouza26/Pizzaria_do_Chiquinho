package principal;

/**Classe para objetos do tipo Produtos, onde serão contidos, valores e métodos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */

public class Pedido {
	
	private Cliente cliente;
	private Funcionarios func;
	private String obs;
	private String status;
	private float total;
	private Item_pedido listadeprodutos;
	
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float valor) {
		this.total = valor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String situacao) {
		this.status = situacao;
	}
	public Cliente getCliente(){
		return cliente;
	}
	public void setCliente(Cliente cliente){
		this.cliente=cliente;
	}
	
}
