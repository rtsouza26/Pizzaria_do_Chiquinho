package principal;



/**Classe para objetos do tipo Funcionários, onde serão contidos, valores e métodos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */

public class Funcionario {
	private int codigo;
	private String nome;
	private int endereco;
	private String cpf;
	private String telefone;
	private int tipo;
	
	private String senha;
	private String login;
	
	
	public Funcionario(){
		
	}
	public Funcionario( 
			int cod,String nome,int endereco,String cpf,String telefone,int tipo,String login){
		this.codigo=cod;
		this.nome=nome;
		this.endereco=endereco;
		this.cpf=cpf;
		this.telefone=telefone;
		this.tipo=tipo;
		this.login=login;
	}
	
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
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
	public int getEndereco() {
		return endereco;
	}
	public void setEndereco(int endereco) {
		this.endereco = endereco;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	

}
