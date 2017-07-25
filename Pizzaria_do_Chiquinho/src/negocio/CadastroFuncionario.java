package negocio;

import java.sql.SQLException;
import java.util.List;

import dados.FuncionarioBD;
import dados.exception.AtualizarFuncionarioErro;
import dados.exception.BuscarFuncionarioErro;
import dados.exception.InserirFuncionarioErro;
import dados.exception.ListarFuncionarioErro;
import dados.exception.RemoverFuncionarioErro;
import principal.Funcionario;
import negocio.exception.*;

public class CadastroFuncionario {
	
	private FuncionarioBD funcBD;
	//private String invalido = "Funcionario Inválido";
	//private String naoexiste = "Funcionario não existe";
	
	
	
	public CadastroFuncionario() throws ClassNotFoundException, SQLException{
		funcBD = new FuncionarioBD();
	}
	
	public void inserirFunc(Funcionario func) throws SQLException, FuncionarioInvalido, FuncionarioExistente, InserirFuncionarioErro{
		
		if(func != null){
			if(!(funcBD.existeBD(func.getLogin()))){
				if(funcBD.inserirFuncBD(func)){
					System.out.println("Funcionario cadastrado com sucesso");
				}else{
					System.out.println("Não foi possível cadastrar o funcionario");
				}
			}else{
				throw new FuncionarioExistente();
			}
		}else{
			throw new FuncionarioInvalido();
		}
	}
	
	
	public Funcionario buscarFunc(String cpf) throws SQLException, FuncionarioInexistente, FuncionarioInvalido, BuscarFuncionarioErro{
		Funcionario aux = null;
		if(cpf!=null){
			if(funcBD.existeBD(cpf)){
				aux = funcBD.buscarFuncBD(cpf); 
			}else{
				throw new FuncionarioInexistente();
			}
		}else{
			throw new FuncionarioInvalido();
		}
		return aux;
	}
	public void removerFunc(String cpf) throws SQLException, FuncionarioInexistente, FuncionarioInvalido, RemoverFuncionarioErro, BuscarFuncionarioErro{
		if(cpf!=null){
			if(funcBD.existeBD(cpf)){	
				if(this.funcBD.removerFuncBD(cpf)){
					System.out.println("Funcionário deletado com sucesso");
				}else{
					System.out.println("Erro ao deletar funcionário");
				}
			}else{
				throw new FuncionarioInexistente();
			}	
		}else{
			throw new FuncionarioInvalido();
		}		
	}
	public void atualizarFunc(Funcionario func) throws SQLException, FuncionarioInexistente, FuncionarioInvalido, RemoverFuncionarioErro, InserirFuncionarioErro, BuscarFuncionarioErro, AtualizarFuncionarioErro{
			
			if(func!=null){
				if(funcBD.existeBD(func.getCpf())){
					if(funcBD.atualizarFuncBD(func)){
						System.out.println("Funcionário atualizado com sucesso");
					}else{
						System.out.println("Não foi possível atualizar o funcionário");
					}
				}else{
					throw new FuncionarioInexistente();
				}
			}else{
				throw new FuncionarioInvalido();
			}
	}
	
	public List<Funcionario> listarFunc() throws SQLException, ListarFuncionarioErro{
		
		return funcBD.listarFuncBD();
	
	}
	

}
