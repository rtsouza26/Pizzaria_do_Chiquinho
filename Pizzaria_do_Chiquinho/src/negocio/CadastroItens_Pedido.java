package negocio;

import java.sql.SQLException;
import java.util.List;

import dados.ProdutoBD;
import principal.Produto;

public class CadastroItens_Pedido {
	
	public void inserir(List<Produto> itens_pedido) throws ClassNotFoundException, SQLException{
		for(int i = 0;i<itens_pedido.size();i++){
			ProdutoBD prodaux = new ProdutoBD();
			
		}
	}

}
