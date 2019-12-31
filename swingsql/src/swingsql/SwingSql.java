package swingsql;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*; 

import java.sql.*;
import java.util.Scanner;




abstract class SqlDemo
{

	Connection conn = null;
	 Statement stmt = null;
	String sql;
	ResultSet resultSet;
	int result;
		void connect(String JDBC_DRIVER , String DB_URL,String USER,String PASS,String DB_NAME) {}
	void createTable(String name){}
	
	void insertData(String name,float share_value) 
	{
	  
	}
abstract ResultSet display(int id) ;
void updateData(int id,float share_value) {}
	void deleteData(int id) {}
	
}


class Brew extends  SqlDemo
{
	
	
	Brew()
	{
		
		
	}
	
	
	
	
	@Override
	 void connect(String JDBC_DRIVER , String DB_URL,String USER,String PASS,String DB_NAME) {
	
		ResultSet rs1;int flag=0;String db;
		 Logger logger = Logger.getLogger(Brew.class.getName());
		logger.info("database  connection successfull");
		try {
			
			
			
			
			   Class.forName(JDBC_DRIVER);
			   conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql",USER,PASS);
			    stmt = conn.createStatement();
			   rs1 = stmt.executeQuery("Show Databases");
		      System.out.println("List of databases: ");
		      while(rs1.next()) {
		    	  System.out.println(rs1.getString(1));
		         if(rs1.getString(1).compareTo("sharemarket")==0)
		         {
		        	
		        	 flag=1;
		        	// System.out.println("loop is "+flag);
		        	 break; 
		         }
		        // System.out.println();
		      }
			//  System.out.println("flag is "+flag);


			  if(flag==1)
			  {
				  
			    System.out.println(" database is already present .... Connecting to database...");
			    conn = DriverManager.getConnection(DB_URL,USER,PASS);
			    stmt = conn.createStatement();
			

			  }
			  else
			  {
				  int res;
				  
				  res=stmt.executeUpdate("create database sharemarket");
				  rs1=stmt.executeQuery("use sharemarket");
				  System.out.println("database not found and created");
				  conn = DriverManager.getConnection(DB_URL,USER,PASS);
				    stmt = conn.createStatement();
			  }
			
		}catch(Exception e) {e.printStackTrace();}
		
		
		System.out.println("successfully connected to database");
		
	
	
	}
	
	
	@Override
	void createTable(String name)
	{
		
		ResultSet rs1;int flag=0;
		try
		{
		   DatabaseMetaData meta = (DatabaseMetaData) conn.getMetaData();
		   
		     rs1 = meta.getTables(null, null, null, new String[] {
		         "TABLE"
		      });
		     
		
		     
		     
		     
		     
		     while (rs1.next()) {
		         String tblName = rs1.getString("TABLE_NAME");
		         //System.out.println(tblName);
		         if(tblName.compareTo(name)==0)
		         {
		        	//String  sql="drop table "+name;
		        	// stmt.executeUpdate(sql);
		        	 flag=1;
		        	 System.out.println(name+" table already found .... using same table ");
		         }
		         
		      }
		     if(flag==0)
		     {
		     String  sql = "create table company(id int not null auto_increment,name varchar(20),sharevalue float, primary key(id))";
			    //sql="show tables";
		     System.out.println("Table "+name+ " not found .... creating a new table");
		     //System.out.println("staement is "+stmt);
			    result = stmt.executeUpdate(sql);
			    System.out.println("new "+name+" table created");
		     
		     } 
		     
		}catch(SQLException e) {
			
			
			//e.printStackTrace();
		System.out.println("========= table creation failed =========");	
		
		}
		
		
		
		  
		
		
		
		
	}
	
	
	@Override
	void insertData(String name,float share_value) 
	{
	    sql="insert into company(name,sharevalue) values('"+name+"',"+share_value+") ";
	    
	    try {
	    
	    result=stmt.executeUpdate(sql);

		//System.out.println("inserted successfully ");
		JOptionPane.showMessageDialog(null, "inserted successfully");
		
	    }catch(SQLException e) {
	    	//e.printStackTrace();
	    	JOptionPane.showMessageDialog(null, "insertion failed");
	    
	    }
	}
	
	
	
	@Override
	void updateData(int id,float share_value) 
	{
		try {
			sql="update company set sharevalue="+share_value+" where id= "+id;
		result=stmt.executeUpdate(sql);
		if(result==0)
		{
			JOptionPane.showMessageDialog(null, "updation failed");
				
		}
		else
		JOptionPane.showMessageDialog(null, "updated successfully");
		}catch(SQLException e) {
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "updation failed");
		
		}
	}
	
	
	@Override
	ResultSet display(int id) 
	{
		
		
		ResultSet result=null;
		String sql="select * from company where id="+id;
try
{
		
		 result=stmt.executeQuery(sql);
	}catch(Exception e) {e.printStackTrace();}		

return result;
	
	
	
	
	}
	
	
	
	
	@Override
	void deleteData(int id)
	{
		
		sql="select * from company where id="+id;
		try
		{
		stmt=conn.createStatement();
		resultSet=stmt.executeQuery(sql);
		//resultSet.next();
		//Brew b=new Brew();
		resultSet.next();
		
		System.out.println("company found and deleted");
			sql="delete from company where id="+id;
			result=stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "deleted successfully");
		
		
		
		
		}catch(SQLException e) {
			
			//e.printStackTrace();
			
		
			JOptionPane.showMessageDialog(null, "deletion failed ");
		
		}
		
	}
	
	
	
	
}


class Update extends JFrame implements ActionListener
{
	static JTextField t1;
	 static JTextField t2;
	
	 
	 void generate()
	 {
		 Update u=new Update();
		JFrame f=new JFrame(); 
	JLabel l1=new JLabel("enter id");
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
		
		if(s.equals("Done"))
		{
		
			SwingSql.b.updateData(Integer.parseInt(t1.getText()),Float.parseFloat(t2.getText()));

			t1.setText("");
			t2.setText("");
			
		}
		
	}
	
}

class Display extends JFrame implements ActionListener
{
	Brew brew=new Brew();
	
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
		l1=new JLabel("enter id");	
		
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
		
		if(s.equals("done")) {
			
			int id=Integer.parseInt(t1.getText());
			rs=SwingSql.b.display(id);
		try {
			
			
			//System.out.println("result is "+rs);
			
			if(rs.next()==false)
			{
				JOptionPane.showMessageDialog(null, "nothing to display ");
				
			}
			else
			{
				ta.setText("id = "+rs.getString(1)+"\n");
				ta.append("Company Name = "+rs.getString(2)+"\n");
				ta.append("Share Value = "+rs.getString(3)+"\n");
				
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


 class Insert extends JFrame implements ActionListener
{
	 static JTextField t1;
	 static JTextField t2;
	 static JTextField t3;
	 static JTextField t4;
	 static JTextField t5;
	
	 
	public void generate()
	{
		Insert i=new Insert();
		JFrame jf=new JFrame();
		JLabel l1=new JLabel("Company Name");
		JLabel l2=new JLabel("Share Value");
		
		 t1=new JTextField();
		 t2=new JTextField();
		
		JButton b=new JButton("Done");
		b.addActionListener(i);
		JButton b1=new JButton("Done1");
		l1.setBounds(100,100,100, 40);
		t1.setBounds(300,100,200, 40);
		l2.setBounds(100,200,100, 40);
		t2.setBounds(300,200,200, 40);
		
		b.setBounds(100,700,100,40);
		b1.setBounds(100,700,100,40);
		jf.add(l1);jf.add(t1);
		jf.add(l2);jf.add(t2);
		
		
		
		jf.add(b);jf.add(b1);
		jf.setVisible(true);
		jf.setSize(500,500);//400 width and 500 height  
		jf.setLayout(null);//using no layout managers  
		
		
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand(); 
		if(s.equals("Done"))
		{
			
			//System.out.println(t1.getText()+t2.getText()+t3.getText()+t4.getText()+t5.getText());
			SwingSql.b.insertData(t1.getText(),Float.parseFloat(t2.getText()));
		//SwingSql.b.insertData("name"," email", 9090909," city", 1);
		t1.setText("");t2.setText("");
		
		
		}
	}
}

 
 class Delete extends JFrame implements ActionListener
 {
	 
	 JLabel l1,l2;
	static  JTextField t1;
	 JButton b;
	 
	 void generate()
	 {
		 l1=new JLabel("enter id to delete");
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
		 if(a.equals("done"))
		 {
			SwingSql.b.deleteData(Integer.parseInt(t1.getText()));
			//System.out.println(SwingSql.b);	
			 //SwingSql.b.insertData("name"," email", 9090909," city", 1);
			t1.setText("");
				
			 
		 }
		 
		 
	 }
	 
 }



public class SwingSql extends JFrame implements ActionListener {

	
	static Brew b=new Brew();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 //System.out.println(System.getProperty("user.dir"));
		String log4jConfPath = "E://nandan/swingsql/src/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
		SwingSql s=new SwingSql(); 
		 String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		 String DB_URL = "jdbc:mysql://localhost:3307/sharemarket?characterEncoding=latin1";

		 String DB_NAME = "sharemarket";
		 String USER = "root";
		 String PASS = "root";
		 String name,email,city;
		 int status,phone;
		 int choice,id;
		
		//Scanner in=new Scanner(System.in);
		b.connect(JDBC_DRIVER, DB_URL, USER, PASS,DB_NAME);
		b.createTable("company");
		ResultSet re;
		//re=b.display(1);
		//try{while(re.next()) {}}catch(Exception e) {e.printStackTrace();}
		//b.insertData("name"," email", 9090909," city", 1);
		

		JFrame f=new JFrame();
		
		JButton b1=new JButton("Insert");
		JButton b2=new JButton("Delete");
		JButton b3=new JButton("Update");
		JButton b4=new JButton("Display");
		JButton b5=new JButton("dummy");
		
		
		b1.addActionListener(s);
		b2.addActionListener(s);
		b3.addActionListener(s);
		b4.addActionListener(s);
		
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
		Brew b=new Brew();
		
		
		f.setVisible(true);
		f.setSize(900,900);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		
		
		
		
	}
	public void actionPerformed(ActionEvent e) 
{
		String s = e.getActionCommand(); 
        if (s.equals("Insert")) { 
            // set the text of the label to the text of the field 
            System.out.println("Insert");
            
            Insert i=new Insert();
            i.generate();
            
            
            
        } 
        
        else if (s.equals("Delete")) { 
            // set the text of the label to the text of the field 
        	 System.out.println("Delete"); 
        	 Delete d=new Delete();
        	 d.generate();
        } 
        else if(s.equals("Display")) {
       Display d=new Display();
       d.generate();
        }
        
        else if(s.contentEquals("Update"))
        {
        	Update u=new Update();
        	u.generate();
        	
        }        
        
    } 
	
	
}