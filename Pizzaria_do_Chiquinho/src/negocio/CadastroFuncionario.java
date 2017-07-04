package negocio;

import dados.FuncionarioBD;
import principal.Funcionario;

public class CadastroFuncionario {
	
	private FuncionarioBD funcBD;
	
	
	public CadastroFuncionario(){
		funcBD = new FuncionarioBD();
	}
	
	public void inserirFunc(Funcionario func){
		
		if(func != null){
			if(!(funcBD.existeBD(func.getLogin()))){
				if(funcBD.inserirFuncBD(func.getNome(), func.getEndereco(), func.getCpf(), func.getTelefone(), func.getTipo(), func.getLogin()
						, func.getSenha())){
					System.out.println("Funcionario cadastrado com sucesso");
				}else{
					System.out.println("Não foi possível cadastrar o funcionario");
				}
			}else{
				System.out.println("Funcionario já existe");
			}
		}
	}//Daqui pra baixo foi eu que fiz, qualquer erro vcs me avisem, blz?
	public Funcionario buscarFunc(String nome){
		return this.funcBD.buscarFuncBD(nome);
	}
	public void removerFunc(String nome){
		if(this.funcBD.removerFuncBD(nome)){
			System.out.println("Funcionário deletado com sucesso");
		}else{
			System.out.println("Não foi possível deletar funcionário");
		}
	}
	

}
