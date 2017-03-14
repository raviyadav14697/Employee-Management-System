import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mu.*;

public class AddFrame extends JFrame
{
	JPanel jp1,jp2;
	JButton add,back;
	JLabel l1,l2;
	JTextField t1,t2;

public AddFrame()
{
	super("Add Employee");
	setSize(500,150);
	setResizable(false);
    
	jp1=new JPanel();
	jp1.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));

	add=new JButton("Add");
	back=new JButton("Back");
	l1=new JLabel("Id");
	l2=new JLabel("Name");
	t1=new JTextField(5);
	t2=new JTextField(10);
	jp1.add(l1);
	jp1.add(t1);
	jp1.add(l2);
	jp1.add(t2);
	add(jp1);


	jp2=new JPanel();
	jp2.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
	jp2.add(add);
	jp2.add(back);
	add(jp2,BorderLayout.SOUTH);
	setLocationRelativeTo(null);
	setVisible(true);

	addWindowListener(new WindowAdapter()
	{
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
	
			add.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					try
					{
					String id=t1.getText();
					String name=t2.getText();
					
					//A.validateLetters(name);
					
					if(id.length()==0 || name.length()==0) 
					{
						Sound.failure();
						JOptionPane.showMessageDialog(new JDialog(),"All fields are mandatory");
						return;
					}
                     Integer.parseInt(id);
					if(!Validation.validateLetters(name))
					{   
						Sound.failure();
						JOptionPane.showMessageDialog(new JDialog(),"Name Should contain only alphabets");
						t2.setText("");
						return;
					}	
					
			//		System.out.println("rajeev");
											
						DatabaseHandler q=new DatabaseHandler();
						q.insert(Integer.parseInt(id),name);
						t1.setText("");
						t2.setText("");
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