package negocio;

import dados.FuncionarioBD;
import principal.Funcionario;

public class CadastroFuncionario {
	
	private FuncionarioBD funcBD;
	private String invalido = "Funcionario Inválido";
	private String naoexiste = "Funcionario não existe";
	
	
	
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
		}else{
			System.out.println(invalido);
		}
	}
	
	
	//Daqui pra baixo foi eu que fiz, qualquer erro vcs me avisem, blz?
	public Funcionario buscarFunc(String login){
		Funcionario aux = null;
		if(login!=null){
			if(funcBD.existeBD(login)){
				aux = funcBD.buscarFuncBD(login);
			}else{
				System.out.println(naoexiste);
			}
		}else{
			System.out.println(invalido);
		}
		return aux;
	}
	public void removerFunc(String login){
		if(login!=null){
			if(funcBD.existeBD(login)){	
				if(this.funcBD.removerFuncBD(login)){
					System.out.println("Funcionário deletado com sucesso");
				}else{
					System.out.println("Erro ao deletar funcionário");
				}
			}else{
				System.out.prinln(naoexiste);
			}	
		}else{
			System.out.println(invalido);
		}		
	}
	public List<Funcionario> listarFunc(){
		
		return FuncBD.listarFuncBD();
	
	}
	

}
