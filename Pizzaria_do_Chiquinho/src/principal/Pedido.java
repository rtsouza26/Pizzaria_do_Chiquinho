package principal;

/**Classe para objetos do tipo Produtos, onde serão contidos, valores e métodos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */

public class Pedido {
	
	private int codigo;
	private Cliente cliente;
	private Funcionario func;
	private String obs;
	private String status;
	private double total;
	private Itens_pedido listadeprodutos;
	
	public Pedido(){
	}
	public Pedido(int codigo, Cliente cliente, Funcionario func, String obs, 
			String status, double total, Itens_pedido listadeprodutos){
		this.codigo =codigo;
		this.cliente = cliente;
		this.func = func;
		this.obs = obs;
		this.status = status;
		this.total = total;
		this.listadeprodutos = listadeprodutos;
	}



	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Funcionario getFunc() {
		return func;
	}
	public void setFunc(Funcionario func) {
		this.func = func;
	}
	public Itens_pedido getListadeprodutos() {
		return listadeprodutos;
	}
	public void setListadeprodutos(Itens_pedido listadeprodutos) {
		this.listadeprodutos = listadeprodutos;
	}
	
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public double getTotal() {
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
