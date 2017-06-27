package principal;

/**Classe para objetos do tipo Cliente, onde serão contidos, valores e métodos para o mesmo.
 * @author
 * @version 1.0
 * @since Release 0
 */

public class Cliente {
	
	private int codigo;
	private String nome;
	private String endereco;
	private String cep;
	private int cpf;
	private String telefone;
	
	public Cliente(){
		
	}
	
	public Cliente( 
			
			int cod,String nome,String endereco,int cpf,String telefone){
		this.codigo=cod;
		this.nome=nome;
		this.endereco=endereco;
		this.cpf=cpf;
		this.telefone=telefone;

	}
	
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	

}
