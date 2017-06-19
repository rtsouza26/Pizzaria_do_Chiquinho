package principal;


import java.util.Scanner;

import conexaoBD.FuncionariosBD;
import conexaoBD.LoginBD;
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*String login;
		String senha;
		Scanner entrada = new Scanner(System.in);
		
		
		System.out.println("Login :");
		login = entrada.next();
		System.out.println("");
		System.out.println("Senha :");
		senha = entrada.next();
		
		LoginBD funcBD = new LoginBD();
		Funcionarios func = null;
		if((func = funcBD.checkLogin(login, senha))!=null){
			
			System.out.println("Usuário correto");
		}else{
			System.out.println("Login ou Senha errados");
		}*/
		
		new TLogin().setVisible(true);	

	}

}
