package security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dados.ConexaoBD;
import principal.Funcionario;
import view.TLogin;
import view.TelaAdm;
import view.TelaAtendente;
import view.TelaCozinha;

public class Supervisao {
	
	private PreparedStatement consulta= null;
	private  PreparedStatement tipo= null;
	private ResultSet rs = null;
	private ResultSet rs1 = null;
	private Connection con = null;
	private Funcionario check = null;
	private String tipofunc = null;
	
	public Supervisao() throws ClassNotFoundException, SQLException{
		con = ConexaoBD.getConnection();
		
		String sql = "SELECT * FROM funcionario WHERE login =? AND senha =?" ;
		String sqltipo = "SELECT * FROM tipo_funcionario WHERE idtipo_funcionario = ?";
		consulta = con.prepareStatement(sql);
		tipo = con.prepareStatement(sqltipo);
	}
	
	public void Consulta(String login,String senha ) throws SQLException, LoginSenhaIncorretos, LoginSemTipo{
		
		consulta.setString(1, login);
		consulta.setString(2, senha);
		rs = consulta.executeQuery();
		
		
		if(rs.next()){
			check = new Funcionario();
			check.setCodigo(rs.getInt("idfuncionario"));
			check.setNome(rs.getString("nome"));
			check.setCpf(rs.getString("cpf"));
			check.setTelefone(rs.getString("telefone"));
			check.setTipo(rs.getInt("idtipo_funcionario"));
			
			tipo.setInt(1,check.getTipo());
			rs1 = tipo.executeQuery();
		
			if(rs1.next()){
				tipofunc = rs1.getString("tipo");
			}
			switch(tipofunc){
				case "Administrador":
					new TelaAdm().setVisible(true);
					break;
					
				case "Recepção":
					new TelaAtendente().setVisible(true);
				    break;
				
				case "Cozinha":
					new TelaCozinha().setVisible(true);
					break;
				
				default:
					throw new LoginSemTipo();
			}
		}else{
			throw new LoginSenhaIncorretos();
		}
		
		
	}

}
