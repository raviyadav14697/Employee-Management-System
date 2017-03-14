import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import mu.*;




public class HomeFrame extends JFrame{
	
JPanel jp;
JButton add , modify , delete,  view;

public HomeFrame()
{

super("Student Record Management");
setSize(500,200);
setResizable(false);
setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

jp = new JPanel();
jp.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));

add = new JButton("Add Record");
modify = new JButton("modify Record");
delete = new JButton("Delete Record");
view = new JButton("View Records");

jp.add(add);
jp.add(modify);
jp.add(delete);
jp.add(view);


add(jp);



setLocationRelativeTo(null);
setVisible(true);

addWindowListener(new WindowAdapter(){

	public void windowClosing(WindowEvent e){

		int op = JOptionPane.showConfirmDialog(new JDialog(),"Do you Want to exit");
		if(op == JOptionPane.YES_OPTION){
			System.exit(1);
		}
		
	}
	
});


add.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent ae)
	{
	
    	AddFrame a =new AddFrame();
		dispose();
	
	}
	
});




modify.addActionListener(new ActionListener(){
	
	public void actionPerformed(ActionEvent ae){
		
		ModifyFrame m = new ModifyFrame();
		dispose();
		
	}
	
});






delete.addActionListener(new ActionListener(){
	
	public void actionPerformed(ActionEvent ae){
		
		DeleteFrame d = new DeleteFrame();
		dispose();
		
	}
	
});






view.addActionListener(new ActionListener()
{
	
	public void actionPerformed(ActionEvent ae){
		
		ViewFrame d = new ViewFrame();
		dispose();
		
	}
	
});


}// End of HomeFrame() Function



public static void main(String args[]){
	
	HomeFrame hm = new HomeFrame();
	
}//End of main function 

}//End of Class HomeFrame




 class DatabaseHandler
{

static Connection con;

public static void getConnection(){

try{

Class.forName("oracle.jdbc.driver.OracleDriver");
con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","9150");  

}
//end of try

catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog(), "" + e);
}//end of catch


}


public void insert(int id,String name)
{
     try
	 {
	       getConnection();


	       String q="insert into MEmployee values(?,?) ";
	 
	      PreparedStatement pst =con.prepareStatement(q);

          
	      pst.setInt(1,id);
	      pst.setString(2,name);
		  
		  int i=pst.executeUpdate();
	  
	     Sound.success();
		 
		 JOptionPane.showMessageDialog(new JDialog(),"1 Record Added");
		 
	  
	 }
	 catch(Exception e)
	 {
	   Sound.failure();
     JOptionPane.showMessageDialog(new  JDialog(),"Record Already Exists");		  
	 }
	 
}






public void modify(int id,String name)
{
     try
	 {
	       getConnection();
		   String q="Update MEmployee set name=? where id=?";
		   
		   
		   PreparedStatement pst =con.prepareStatement(q);
		   
		   pst.setString(1,name);
		   pst.setInt(2,id);
	       
	       int i=pst.executeUpdate();
	 
	    Sound.success();
		
	 JOptionPane.showMessageDialog(new JDialog(),"1 Record Modified"); 
	 }
	 catch(Exception e)
	 {
	       Sound.failure();
           JOptionPane.showMessageDialog(new  JDialog(),"ID= "+id+" doesn't exist");	
    	   
	 }
	 
}



public void delete(int id)
{
     try
	 {
	       getConnection();
		   String q="Delete from MEmployee where id=?";
		   
		   PreparedStatement pst =con.prepareStatement(q);
		   pst.setInt(1,id);
	       
	       int i=pst.executeUpdate();
	 
	    Sound.success();
		
	 JOptionPane.showMessageDialog(new JDialog(),"1 Record Deleted"); 
	 
	 }
	 catch(Exception e)
	 {
	      Sound.failure();
          JOptionPane.showMessageDialog(new  JDialog(),"Invalid ID");		  
	 }
	 
}


public String query(){


StringBuffer sb = new StringBuffer();

try
{

getConnection();

String q = "select * from MEmployee order by id" ;
Statement st = con.createStatement();
ResultSet rs = st.executeQuery(q);

sb.append("ID:"+"\t"+"Name: " + "\n");
while(rs.next())
{

sb.append(rs.getString(1)+"\t" + rs.getString(2)+ "\n");

}//end of while

rs.close();

}//end of try
catch(Exception e){

JOptionPane.showMessageDialog(new JDialog()," "+e);

}//end of catch 

return sb.toString();

}//end of query

public boolean query1(int id){

boolean check = true;

try
{

getConnection();

String q = "select * from MEmployee where id=?" ;
PreparedStatement pst = con.prepareStatement(q);
pst.setInt(1,id);

//Statement st = con.createStatement();

ResultSet rs = pst.executeQuery();

//sb.append("ID:"+"\t"+"Name: " + "\n");
check = rs.next();
//rs.close();

}//end of try
catch(Exception e){

JOptionPane.showMessageDialog(new JDialog()," "+e);

}//end of catch 

return check;

}


}