package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoBD {
	
	private PreparedStatement inserir = null;
	private PreparedStatement remover = null;
	private PreparedStatement buscar = null;
	private PreparedStatement buscarcpf = null;
	private ResultSet rs = null;
	private Connection con = null;

	
	public EnderecoBD(){
		
		con = ConexaoBD.getConnection();
	
		try {
			inserir = con.prepareStatement("INSERT INTO Endereco(cod_cliente,cod_funcionario,logradouro,numero,bairro,cidade,cep,complemento) "
					+ "VALUE (?,?,?,?,?,?,?,?)");
			remover = con.prepareStatement("DELETE FROM Endereco WHERE cod = ?");
			buscar = con.prepareStatement("SELECT * FROM Endereco WHERE nome = ?");
			buscarcpf = con.prepareStatement("SELECT * FROM Endereco WHERE cpf = ?");
		
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("problemas com o drive de Bnco de dados");
		e.printStackTrace();
		System.exit(1);
		}
	}
}


