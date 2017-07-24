package dados;

/**Classe para a conexão de todas as outras classes com o banco de dados, onde serão contidos, valores e métodos para o mesmo.
 * @author 
 * @version 1.0
 * @since Release 0
 */

import java.sql.*;
import java.util.logging.*;

public class ConexaoBD {
	
	static final String driver = "com.mysql.jdbc.Driver";
	static final String user = "admin";
	static final String pass = "12345";
	static final String url = "jdbc:mysql://localhost:3306/pizzaria";
	
	static final String nao  = "Não conectou";
	static final String sim = "Conectou";
	static final String fim = "Desconectou";
	
	public static  java.sql.Connection getConnection() throws ClassNotFoundException, SQLException{
		
		Connection con = null;
		
		
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			System.out.println(sim);
			
		
		return con;
		
	}
	public static void closeConnection(Connection con) throws SQLException{
        
        if(con!=null){
            
                con.close();
                System.out.println(fim);
           
            
        }

    }
	public static void closeConnection(Connection con,ResultSet rs) throws SQLException{
		closeConnection(con);
		
		if(rs!=null){
	           
	               rs.close();   
		}
	    
		
	}
	public static void closeConnection(Connection con, PreparedStatement stmp) throws SQLException{
        
        closeConnection(con);
       if(stmp!=null){
           try {
               stmp.close();
           } catch (SQLException ex) {
               Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
           }
       }

   }
	public static void closeConnection(Connection con, PreparedStatement stmp, ResultSet rs ) throws SQLException{
        
        closeConnection(con,stmp);
       if(rs!=null){
           try {
               rs.close();
               
           } catch (SQLException ex) {
               Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
           }
       }

   }
	

}
