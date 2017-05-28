package principal;

import java.util.Scanner;
import conexaoBD.FuncionariosBD;

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
		
		String login;
		String senha;
		Scanner entrada = new Scanner(System.in);
		
		
		System.out.println("Login :");
		login = entrada.next();
		System.out.println("");
		System.out.println("Senha :");
		senha = entrada.next();
		
		FuncionariosBD funcBD = new FuncionariosBD();
		if(funcBD.checkLogin(login, senha)){
			
			System.out.println("Usuário correto");
		}else{
			System.out.println("Login ou Senha errados");
		}
		
		
		
		

	}

}
