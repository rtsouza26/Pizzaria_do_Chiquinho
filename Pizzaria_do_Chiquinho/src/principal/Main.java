package principal;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dados.ClienteBD;
import dados.FuncionarioBD;
import dados.LoginBD;
import dados.exception.AtualizarFuncionarioErro;
import dados.exception.BuscarFuncionarioErro;
import dados.exception.InserirClienteErro;
import dados.exception.InserirFuncionarioErro;
import dados.exception.ListarFuncionarioErro;
import dados.exception.RemoverFuncionarioErro;
import view.TLogin;

/**Pizzaria do Chiquinho
 * 
 * Projeto que visa criar um sistema de controle de processos de uma loja voltada para a venda de pizzas 
 * e serviços relacionados.
 * 
 * 
 * @author 
 * @version 1.0
 * @since Release 0
 */

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InserirClienteErro, InserirFuncionarioErro, BuscarFuncionarioErro, ListarFuncionarioErro, RemoverFuncionarioErro, AtualizarFuncionarioErro {
		// TODO Auto-generated method stub
		
		
		
		new TLogin().setVisible(true);	
		
	}

}
