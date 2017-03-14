import java.sql.*;  
class OracleCon{  
public static void main(String args[]){  
try{  

Class.forName("oracle.jdbc.driver.OracleDriver");  
  

Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","9150");  
Statement stmt=con.createStatement();  
  
String sql="Create table MEmployee(id Integer primary key,name varchar(30))";
  stmt.executeUpdate(sql);



String sql1="Insert into MEmployee values(1,'Mahesh')";
stmt.executeUpdate(sql1);


ResultSet rs=stmt.executeQuery("Select * from MEmployee");  


while(rs.next())  
System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  ");  
  
System.out.println("Rajeev");
con.close();  
  
}catch(Exception e){ System.out.println(e);}  
  
}  
} 