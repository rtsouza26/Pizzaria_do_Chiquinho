package principal;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**Classe para objetos do tipo Produtos, onde serão contidos, valores e métodos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */

public class Pedido {
	
	private String codigo;
	private Cliente cliente;
	private Funcionario func;
	private String obs;
	private String status;
	private double total;
	private Itens_pedido listadeprodutos;
	
	public Pedido(){
		
		this.codigo = this.getDateTime();
	}
	public Pedido(String codigo, Cliente cliente, Funcionario func, String obs, 
			String status, double total, Itens_pedido listadeprodutos){
		this.codigo =codigo;
		this.cliente = cliente;
		this.func = func;
		this.obs = obs;
		this.status = status;
		this.total = total;
		this.listadeprodutos = listadeprodutos;
	}


	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
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
	public void setTotal(double valor) {
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
	private String getDateTime() { 
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss"); 
		Date date = new Date(); 
		return dateFormat.format(date); 
	}
	
	
}
