
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JOptionPane;

public class DBconnect {
    Connection conn;
   
  
    
    public static Connection connectDb(){
        
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
          Connection  conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/digysacco","david","mutune");
            
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Cant connnect to database");
            return null;
            
        }
    }  
    
}
