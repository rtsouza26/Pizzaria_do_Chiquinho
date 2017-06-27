package negocio;

import dados.FuncionariosBD;
import principal.Funcionario;

public class CadastroFuncionario {
	
	private FuncionariosBD funcBD;
	
	
	public CadastroFuncionario(){
		funcBD = new FuncionariosBD();
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
	}

}
