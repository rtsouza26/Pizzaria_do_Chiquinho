package dados;

/**Classe para a conex�o de todas as outras classes com o banco de dados, onde ser�o contidos, valores e m�todos para o mesmo.
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
	static final String url = "jdbc:mysql://localhost:3306/pizzaria?useSSL=false";
	
	static final String nao  = "N�o conectou";
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
