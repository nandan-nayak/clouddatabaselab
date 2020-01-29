package swingsql;



import javax.swing.*;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.ResultSet; 

//import swingsql.MySQLDBConnection;


class Actions extends MSSQLDBConnection
{
	
public void insertData(InsertObject insertObject)
{
	InsertResult result=insert(insertObject);
	// System.out.println("inserted");
JOptionPane.showMessageDialog(null,result.message);	
}

public ResultSet displayData(DisplayObject displayObject)
{
	
	
	DisplayResult result=display(displayObject);
	JOptionPane.showMessageDialog(null,result.message);
	return result.result;
}


public void deleteData(DeleteObject deleteObject)
{
	
	
	DeleteResult result=delete(deleteObject);
	JOptionPane.showMessageDialog(null,result.message);
	
	
}

public void updateData(UpdateObject updateObject)
{
	
	
	UpdateResult result=update(updateObject);
	JOptionPane.showMessageDialog(null,result.message);
}


}





class Update extends JFrame implements ActionListener
{
	static JTextField t1;
	 static JTextField t2;
	Actions action=new Actions();
	
	 void generate()
	 {
		 	 Update u=new Update();
		JFrame f=new JFrame(); 
	JLabel l1=new JLabel("enter company Name");
	JLabel l2=new JLabel("enter new share value");
	JLabel l3=new JLabel("");
	JButton b=new JButton("Done");
	t1=new JTextField();
	t2=new JTextField();
	l1.setBounds(100,100,100, 60);
	t1.setBounds(300,100,200, 60);
	b.addActionListener(u);
	l2.setBounds(100,200,100, 60);
	t2.setBounds(300,200,200, 60);
	b.setBounds(100,400,100,40);
	 
	 
	f.add(l1);f.add(t1);
	f.add(l2);f.add(t2);
	f.add(b);
	f.add(l3);
	f.setVisible(true);
	f.setSize(500,500);//400 width and 500 height  
	f.setLayout(null);//using no layout managers  
	
	
	
	
	
	
	 }
	
	
	public void actionPerformed(ActionEvent ae)
	{
		String s=ae.getActionCommand();
		UpdateObject updateObject=new UpdateObject();
		if(s.equals("Done"))
		{
			if(t1.getText().length()==0 || t2.getText().length()==0)
			{
				
				JOptionPane.showMessageDialog(null, "values must not be empty");
			}
			else
			{
				updateObject.companyName=t1.getText();
				updateObject.shareValue=Double.parseDouble(t2.getText());
			action.updateData(updateObject);

			t1.setText("");
			t2.setText("");
			}
		}
		
	}
	
	
	
}

class Display extends JFrame implements ActionListener
{
Actions action=new Actions();
	
	static JTextField t1;
	static JTextArea ta;
	static JButton b;
	
	static JLabel l1;
	
	void generate()
	{
		
		Display d=new Display();
		t1=new JTextField();
		ta=new JTextArea();
		JLabel l2=new JLabel("");
		b=new JButton("done");
		l1=new JLabel("enter company Name");	
		
		JFrame jf=new JFrame();
		t1.setBounds(300,100,100, 40);
		ta.setBounds(100,200,500, 300);
		b.setBounds(100,600,100, 40);
		l1.setBounds(100, 100, 100,40);
		b.addActionListener(d);
		jf.add(l1);jf.add(t1);jf.add(ta);
		jf.add(b);jf.add(l2);
		
		

		
		jf.setVisible(true);
		jf.setSize(500,500);//400 width and 500 height  
		jf.setLayout(null);//using no layout managers  
		
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{
		String s = e.getActionCommand(); 
		ResultSet rs;
		DisplayObject displayObject=new DisplayObject();
		
		if(s.equals("done")) {
			
			
			if(t1.getText().length()==0)
			{
				
				JOptionPane.showMessageDialog(null, "values must not be empty");
			}
			
			else
			{
			
			displayObject.companyName=t1.getText();
		
			//System.out.println("DB is "+DB);
			rs=action.displayData(displayObject);
			try {
			
			
			//System.out.println("result is "+rs);
			
			if(rs.next()==false)
			{
				
				JOptionPane.showMessageDialog(null, "nothing to display ");
				
			}
			else
			{
				ta.setText("Company Name= "+rs.getString(1)+"\n");
				ta.append("Share value = "+rs.getString(2)+"\n");
				
				
			}
			/*
			 //rs.next();
		
		if(rs!=null)
			ta.setText(Integer.toString(rs.getInt(0)));
		
		*/
			
		}catch(Exception exp)
		{
			exp.printStackTrace();
		}	
			
			
		}
		
	}
	}
}


class Insert extends JFrame implements ActionListener
{
	 static JTextField t1;
	 static JTextField t2;
	 static JTextField t3;
	 static JTextField t4;
	 static JTextField t5;
	 Actions action=new Actions();
	public void generate()
	{
		 
		
		Insert i=new Insert();
		JFrame jf=new JFrame();
		JLabel l1=new JLabel("Company Name");
		JLabel l2=new JLabel("Share Value");
		JLabel l3=new JLabel("location");
		JLabel l4=new JLabel("joined");
		
		 t1=new JTextField();
		 t2=new JTextField();
		 t3=new JTextField();
		 t4=new JTextField();
		
		JButton b=new JButton("Done");
		b.addActionListener(i);
		JButton b1=new JButton("Done1");
		l1.setBounds(100,100,100, 40);
		t1.setBounds(300,100,200, 40);
		l2.setBounds(100,200,100, 40);
		t2.setBounds(300,200,200, 40);
		
		
		l3.setBounds(100,300,100, 40);
		t3.setBounds(300,300,200, 40);
		l4.setBounds(100,400,100, 40);
		t4.setBounds(300,400,200, 40);
		
		
		
		b.setBounds(100,700,100,40);
		b1.setBounds(100,700,100,40);
		jf.add(l1);jf.add(t1);
		jf.add(l2);jf.add(t2);
		jf.add(l3);jf.add(t3);
		jf.add(l4);jf.add(t4);
		
		
		
		jf.add(b);jf.add(b1);
		jf.setVisible(true);
		jf.setSize(500,500);//400 width and 500 height  
		jf.setLayout(null);//using no layout managers  
		
		
		
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		InsertObject insertObject=new InsertObject();
		String s=ae.getActionCommand();
		if(s.equals("Done"))
		 {

			if(t1.getText().length()==0 || t2.getText().length()==0 || t3.getText().length()==0 || t4.getText().length()==0)
			{
				
				JOptionPane.showMessageDialog(null, "values must not be empty");
			}
			else
			{
				
				
				insertObject.companyName=t1.getText();
				insertObject.shareValue=Double.parseDouble(t2.getText());
				insertObject.location=t3.getText();
				insertObject.joined=t4.getText();
			action.insertData(insertObject);
			//System.out.println(SwingSql.b);	
			 //SwingSql.b.insertData("name"," email", 9090909," city", 1);
			t1.setText("");t2.setText("");t3.setText("");t4.setText("");
			}	
			 
		 }
		
	}
}



class Delete extends JFrame implements ActionListener
{
	

	 JLabel l1,l2;
	static  JTextField t1;
	 JButton b;
	Actions action =new Actions();
	 void generate()
	 {
		
		 l1=new JLabel("enter companyName to delete");
		 JFrame jf=new JFrame();
		 Delete d=new Delete();
		 t1=new JTextField();
		 l2=new JLabel();
		 b=new JButton("done");
		 
		 l1.setBounds(100, 100, 200, 40);
		 t1.setBounds(300,100,200, 40);
		 b.setBounds(100,700,100,40);
		 
		 b.addActionListener(d);
		 jf.add(l1);
		 jf.add(t1);jf.add(b);jf.add(l2);
		 
		 jf.setVisible(true);
			jf.setSize(500,500);//400 width and 500 height  
			jf.setLayout(null);//using no layout managers  
			
		 
	 }
	 
	 public void actionPerformed(ActionEvent e)
	 {
		 String a=e.getActionCommand();
		// System.out.println(t1.getText());
			DeleteObject deleteObject=new DeleteObject();
		 if(a.equals("done"))
		 {
			 
			 if(t1.getText().length()==0)
				{
					
					JOptionPane.showMessageDialog(null, "values must not be empty");
				}
			 else
			 {
			action.deleteData(deleteObject);
			//System.out.println(SwingSql.b);	
			 //SwingSql.b.insertData("name"," email", 9090909," city", 1);
			t1.setText("");
			 }
			 
		 }
		 
		 
	 }
	
}

public class Main extends JFrame implements ActionListener{

	
	
static	Actions b = new Actions();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//initialise log4j properties
		Log4JProperties.initialize();
		
		
		Main m=new Main();
	
		
		
		
		
		
		

		JFrame f=new JFrame();
		
		JButton b1=new JButton("Insert");
		JButton b2=new JButton("Delete");
		JButton b3=new JButton("Update");
		JButton b4=new JButton("Display");
		JButton b5=new JButton("dummy");
		
		
		b1.addActionListener(m);
		b2.addActionListener(m);
		b3.addActionListener(m);
		b4.addActionListener(m);
		
		JTextArea t1=new JTextArea("");
		b1.setBounds(130,700,100, 40);
		b2.setBounds(330,700,100, 40);
		b3.setBounds(530,700,100, 40);
		b4.setBounds(730,700,100, 40);
		b5.setBounds(930,700,100,40);
		
		
		
		t1.setBounds(50,50,800,500);
		t1.setEditable(false);
		f.add(t1);
		f.add(b1);f.add(b2);
		f.add(b3);f.add(b4);f.add(b5);
		
		
		
		f.setVisible(true);
		f.setSize(900,900);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		
		
		
		
		
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand(); 
	
		if (s.equals("Insert")) { 
            // set the text of the label to the text of the field 
          //  System.out.println("Insert");
            
            Insert i=new Insert();
           
            i.generate();
            
            
        } 
		  else if (s.equals("Delete")) { 
	            // set the text of the label to the text of the field 
	        	 //System.out.println("Delete"); 
	        	 Delete d=new Delete();
	        	
	        	 d.generate();
	        } 
	        else if(s.equals("Display")) {
	       Display d=new Display();
	      
	       
	     //  System.out.println("the DB s"+DB);
	       d.generate();
	        }
	        
	        else if(s.contentEquals("Update"))
	        {
	        	Update u=new Update();
	        	
	        	u.generate();
	        	
	        }        
	        
	    } 
		
		
		
		
		
		
	}


