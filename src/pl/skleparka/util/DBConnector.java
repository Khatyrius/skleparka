package pl.skleparka.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBConnector {

    public static Connection getConnection() throws Exception {
    	Connection connect = null;
    	String url = "jdbc:mysql://localhost/druk?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow";
    	String user = "root";
    	String password = "root";
    	
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager
                    .getConnection(url, user, password);
        }
        catch(SQLException e){ 
            e.printStackTrace();
        }
        return connect;		
    }
    
    public static void close(Connection con, ResultSet rs, Statement st){
    	try {
    			if(con!= null) con.close();
			if(rs != null) rs.close();
			if(st != null) st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}