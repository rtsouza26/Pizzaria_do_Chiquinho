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
	private String telefone;
	private int cod_forma_pagamento;
	
	public Cliente(){
		
	}
	
	public Cliente( 
			
		int cod,String nome,String endereco,String telefone, int cod_forma_pagamento){
		
		this.codigo = cod;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.cod_forma_pagamento = cod_forma_pagamento;

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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getCod_forma_pagamento() {
		return cod_forma_pagamento;
	}

	public void setCod_forma_pagamento(int cod_forma_pagamento) {
		this.cod_forma_pagamento = cod_forma_pagamento;
	}
	
	
	

}
