package principal;



/**Classe para objetos do tipo Funcion�rios, onde ser�o contidos, valores e m�todos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */

public class Funcionario {
	
	private int codigo;
	private String nome;
	private String endereco;
	private String cpf;
	private String telefone;
	private int cod_tipo;
	private String senha;
	private String login;
	
	
	public Funcionario(){
		
	}
	public Funcionario( 
			
		int cod, String nome, String endereco, String cpf, String telefone, int cod_tipo, String login){
		
		this.codigo=cod;
		this.nome=nome;
		this.endereco=endereco;
		this.cpf=cpf;
		this.telefone=telefone;
		this.cod_tipo=cod_tipo;
		this.login=login;
	}
	
	public int getTipo() {
		return cod_tipo;
	}
	public void setTipo(int cod_tipo) {
		this.cod_tipo = cod_tipo;
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
