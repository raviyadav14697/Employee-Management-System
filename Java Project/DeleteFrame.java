import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mu.*;

public class DeleteFrame extends JFrame
{
     JPanel jp1,jp2;
	JButton delete,back;
	JLabel l1;
	JTextField t1;
	 
	 
	 public DeleteFrame()
	 {
	  super("Delete Record");
	 
	   setSize(500,150);
	   setResizable(false);
	   

	   
	jp1=new JPanel();
	jp1.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));

	delete = new JButton("Delete");
	back = new JButton("Back");
	l1=new JLabel("Id");
	//l2=new JLabel("Name");
	t1=new JTextField(5);
	//t2=new JTextField(10);
	jp1.add(l1);
	jp1.add(t1);
	//jp1.add(l2);
	//jp1.add(t2);
	add(jp1);

	   
	jp2=new JPanel();
	jp2.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
	jp2.add(delete);
	jp2.add(back);
	add(jp2,BorderLayout.SOUTH);
	setLocationRelativeTo(null);
	setVisible(true);
	   
	   
	   addWindowListener(new WindowAdapter(){
	   
	      public void windowClosing(WindowEvent we)
		  {
		       HomeFrame h=new HomeFrame();
		       dispose();
		  }
	   
	   });
    

  back.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			HomeFrame a=new HomeFrame();
			dispose();
		
		}

	});
	
   			delete.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					try 
					{
					String id=t1.getText();
					Integer.parseInt(id);
					
					
			//		System.out.println("rajeev");
					if(id.length()==0) 
					{
						Sound.failure();
						JOptionPane.showMessageDialog(new JDialog(),"All fields are mandatory");
						return;
					}
					
						
						DatabaseHandler q = new DatabaseHandler();
						
						DatabaseHandler q1 = new DatabaseHandler();
						if(!q1.query1(Integer.parseInt(id)))
						{
						Sound.failure();
						JOptionPane.showMessageDialog(new JDialog(),"This ID is not there in Employee Table");
					    t1.setText("");
						return;
							
						}
						q.delete(Integer.parseInt(id));
						t1.setText("");
						//t2.setText("");
					}
					catch(Exception e)
					{
						Sound.failure();
						JOptionPane.showMessageDialog(new JDialog(),"ID of the Employee should be Numeric");
					    t1.setText("");
						return;
						
					}
				}
						});





   }
	 
}