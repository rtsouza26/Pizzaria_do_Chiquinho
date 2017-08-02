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
				funcBD.inserirFuncBD(func);
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
			if(this.funcBD.existeBD(cpf)){	
				this.funcBD.removerFuncBD(cpf);
				
			}else{
				throw new FuncionarioInexistente();
			}	
		}else{
			throw new FuncionarioInvalido();
		}		
	}
	public void atualizarFunc(Funcionario func) throws SQLException, FuncionarioInexistente, FuncionarioInvalido, AtualizarFuncionarioErro, BuscarFuncionarioErro{
			
			if(func!=null){
				if(this.funcBD.existeBD(func.getCpf())){
					try {
						this.funcBD.atualizarFuncBD(func);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
				}else{
					throw new FuncionarioInexistente();
				}
			}else{
				throw new FuncionarioInvalido();
			}
	}
	
	public List<Funcionario> listarFunc() throws SQLException, ListarFuncionarioErro{
		
		return this.funcBD.listarFuncBD();
	
	}
	

}
