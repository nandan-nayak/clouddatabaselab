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
	 PreparedStatement pstmt=null;
	String sql;
	ResultSet resultSet;
	int result;
		void connect(String USER,String PASS,String DB_NAME,String DB) {}
	void createTable(String name,String DB){}
	
	void insertData(String name,float share_value,String DB) 
	{
	  
	}
abstract ResultSet display(int id,String DB) ;
void updateData(int id,float share_value,String DB) {}
	void deleteData(int id,String DB) {}
	
}


class Brew extends  SqlDemo
{
	 Logger logger = Logger.getLogger(Brew.class.getName());
	
	Brew()
	{
		
		
	}
	
	
	
	
	@Override
	 void connect(String USER,String PASS,String DB_NAME,String DB) {
	
		ResultSet rs1;int flag=0;String connectionstring,classname, createdatabasequery,showdatabasequery;
		if(DB=="mysql")
		{
			connectionstring="jdbc:mysql://localhost:3307/test";
			classname="com.mysql.jdbc.Driver";
			showdatabasequery="show databases";
		}
		else
		{
			connectionstring="jdbc:sqlserver://localhost;";
			classname="com.microsoft.sqlserver.jdbc.SQLServerDriver";
			showdatabasequery="select * from sys.databases  ";
			//showdatabasequery="show databases"
		}
		createdatabasequery="create database "+DB_NAME;
		
		try {
			
			
			
			
			 //  Class.forName(JDBC_DRIVER);
			  Class.forName(classname);
			   
			   
			
			   //conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql",USER,PASS);
			  
			  
			  //this connection works for both database
			  conn = DriverManager.getConnection(connectionstring,USER,PASS);
			   
			   
			   logger.info("Default Database  connection successfull");
			    stmt = conn.createStatement();
			   rs1 = stmt.executeQuery(showdatabasequery);
		      
		      //logger.info("List of databases:");
		      while(rs1.next()) {
		    	  //logger.info(rs1.getString(1));
		         if(rs1.getString(1).compareTo(DB_NAME)==0)
		         {
		        	
		        	 flag=1;
		        	 break; 
		         }
		        }
		
		      
				
			   
			  if(flag==1)
			  {
				  
				  
				  
				  if(DB=="mysql")
					{
						connectionstring="jdbc:mysql://localhost:3307/"+DB_NAME;
						//System.out.println(connectionstring);
						//classname="com.mysql.jdbc.Driver";
						 conn = DriverManager.getConnection(connectionstring,USER,PASS);
						 
						stmt=conn.createStatement();
						
					}
					else
					{
						connectionstring="jdbc:sqlserver://localhost;databaseName="+DB_NAME;
						stmt=conn.createStatement();
						
					//	classname="com.microsoft.sqlserver.jdbc.SQLServerDriver";
					}
				  
				  
				  
				  
				  
			   // System.out.println(" database is already present .... Connecting to database...");
				  
			    logger.info("database is already present .... Connecting to database...");
			    
			    conn = DriverManager.getConnection(connectionstring,USER,PASS);
			    stmt = conn.createStatement();
			

			  }
			  else
			  {
				  int res;
				  logger.info("database Not Found ... creating new database");
				  
				
				  res=stmt.executeUpdate(createdatabasequery);
				  rs1=stmt.executeQuery("use "+DB_NAME);
				 // System.out.println("database not found and created");
				  
				  conn = DriverManager.getConnection(connectionstring,USER,PASS);
				    stmt = conn.createStatement();
				    rs1.close();
			    
					//  pstmt=conn.prepareStatement(createdatabasequery);
					 // pstmt.executeQuery();
				  
				  
				  }
			
		}catch(Exception e) {
		logger.warn("ERROR DURING CREATING DATABASE...");
		logger.warn(e.getMessage());
			//e.printStackTrace();
			
		}
	
		
		
		//System.out.println("successfully connected to database");
		logger.info("successfully connected to database");
	
	
	}
	
	
	@Override
	void createTable(String name,String DB)
	{
		
		ResultSet rs1;int flag=0;String  sql;
		try
		{
			if(DB.compareTo("mysql")==0)
			{
		  sql="show tables";
		  rs1=stmt.executeQuery(sql);
			}
			else
			{
				sql="select * from sys.tables";
				  rs1=stmt.executeQuery(sql);
			}
		     
		     
		     
		     
		     while (rs1.next()) {
		    	 System.out.println(rs1.getString(1));
		         if(rs1.getString(1).compareTo(name)==0)
		         {
		        	//String  sql="drop table "+name;
		        	// stmt.executeUpdate(sql);
		        	 flag=1;
		        	// System.out.println(name+" table already found .... using same table ");
		        	 
		        	 logger.info(name+" table already found .... using same table ");
		        		
		         }
		         
		      }
		     if(flag==0)
		     {
		    	 if(DB.compareTo("mysql")==0)
		    		 sql = "create table company(id int not null auto_increment,name varchar(20),sharevalue float, primary key(id))";
		    	 else
		    		 sql = "create table company(id int not null identity,name varchar(20),sharevalue float, primary key(id))";
		    	 
		    	 //sql="show tables";
		   //  System.out.println("Table "+name+ " not found .... creating a new table");
		     
		     logger.info("Table "+name+ " not found .... creating a new table");
	        	
		     
		     
		     //System.out.println("staement is "+stmt);
			    result = stmt.executeUpdate(sql);
			    //System.out.println("new "+name+" table created");
			    
			    logger.info("new "+name+" table created");
		        
			    
		     
		     } 
		     
		}catch(SQLException e) {
			
			
			//e.printStackTrace();
	logger.warn("ERROR in Creating Table");
	logger.warn(e.getMessage());
		
		}
		
		
		
		  
		
		
		
		
	}
	
	
	@Override
	void insertData(String name,float share_value,String DB) 
	{
		
		if(DB=="mysql")
		{
			
	    sql="insert into company(name,sharevalue) values('"+name+"',"+share_value+") ";
	    
	    
	    try {
		    
		    result=stmt.executeUpdate(sql);

			//System.out.println("inserted successfully ");
		    logger.info("Inserted Data Successfully");
			JOptionPane.showMessageDialog(null, "inserted successfully");
			
		    }catch(SQLException e) {
		    	//e.printStackTrace();
		    	logger.warn("Data Insertion Failed");
		    	logger.warn(e.getMessage());
		    	JOptionPane.showMessageDialog(null, "insertion failed");
		    
		    }
	    
	    
	    
		}
		
		else
		{
			sql="insert into company(name,sharevalue) values (?,?) ";
			
			
			
			 try {
				    pstmt=conn.prepareStatement(sql);
				    //result=stmt.executeUpdate(sql);
				    pstmt.setString(1,name);
				    pstmt.setFloat(2, share_value);
				    pstmt.executeUpdate();
				    
					//System.out.println("inserted successfully ");
				    logger.info("Inserted Data Successfully");
					JOptionPane.showMessageDialog(null, "inserted successfully");
					
				    }catch(SQLException e) {
				    	//e.printStackTrace();
				    	logger.warn("Data Insertion Failed");
				    	logger.warn(e.getMessage());
				    	JOptionPane.showMessageDialog(null, "insertion failed");
				    
				    }
			
			
			
			
		}
	   
	}
	
	
	
	@Override
	void updateData(int id,float share_value,String DB) 
	{
		
		if(DB=="mysql")
		{
		try {
			sql="update company set sharevalue="+share_value+" where id= "+id;
		result=stmt.executeUpdate(sql);
		if(result==0)
		{
			logger.warn("Failed to update the information...... company not found ");
			JOptionPane.showMessageDialog(null, "updation failed.. company not found");
				
		}
		else
		{
			logger.info("Updated Successfull");
		JOptionPane.showMessageDialog(null, "updated successfully");
		}	}catch(SQLException e) {
			
			//e.printStackTrace();
			logger.warn("ERROR IN UPDATION....Updated failed");
			logger.warn(e.getMessage());
			JOptionPane.showMessageDialog(null, "updation failed");
		
		}
		
		}
		
		else
		{
			
			try {
				sql="update company set sharevalue=? where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setFloat(1, share_value);
			pstmt.setInt(2, id);
			result=pstmt.executeUpdate();
			if(result==0)
			{
				logger.warn("Failed to update the information...... company not found ");
				JOptionPane.showMessageDialog(null, "updation failed.. company not found");
					
			}
			else
			{
				logger.info("Updated Successfull");
			JOptionPane.showMessageDialog(null, "updated successfully");
			}	}catch(SQLException e) {
				
				//e.printStackTrace();
				logger.warn("ERROR IN UPDATION....Updated failed");
				logger.warn(e.getMessage());
				JOptionPane.showMessageDialog(null, "updation failed");
			
			}
			
			}
			
			
		
		}
	
	
	
	@Override
	ResultSet display(int id,String DB) 
	{
		
		
		ResultSet result=null;
		
		//System.out.println(DB);
		if(DB.compareTo("mysql")==0)
		{
		
		
		sql="select * from company where id="+id;
try
{
		
		 result=stmt.executeQuery(sql);
		 //if(result.next()==false)logger.info("nothing to display");
		 //result.previous();
	}catch(Exception e) {
	logger.warn("ERROR IN DISPLAY ..... ");
	logger.warn(e.getMessage());
		//e.printStackTrace();
		
	}	

		}
		
		
		
		else
		{
			
			

			sql="select * from company where id=?";
	try
	{
			
			 pstmt=conn.prepareStatement(sql);
			
			 pstmt.setInt(1, id);
			 
			 result=pstmt.executeQuery();
			 System.out.println("executed");
			 if(result.first()==false)logger.info("nothing to display");
			 //if(result.next()==false)logger.info("nothing to display");
			 //result.first();
		}catch(Exception e) {
		logger.warn("ERROR IN DISPLAY ..... ");
		logger.warn(e.getMessage());
			//e.printStackTrace();
			
		}	

			
		}

return result;
	
	
	
	
	}
	
	
	
	
	@Override
	void deleteData(int id,String DB)
	{
		int result;
		
		if(DB=="mysql")
		{
			
		sql="select * from company where id="+id;
		try
		{
		stmt=conn.createStatement();
		resultSet=stmt.executeQuery(sql);
		//resultSet.next();
		//Brew b=new Brew();
		resultSet.next();
		resultSet.getString(1);
		//System.out.println("company found and deleted");
		logger.info("company found and deleted");
			sql="delete from company where id="+id;
			result=stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "deleted successfully");
		
		
		
		
		}catch(SQLException e) {
			
			//e.printStackTrace();
			
		logger.warn("ERROR IN DELETING...");
		logger.warn(e.getMessage());
			JOptionPane.showMessageDialog(null, "deletion failed ");
		
		}
		
	}
		
		else
		{
			
		sql="select * from company where id=?";
		try
		{
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, id);
	resultSet=pstmt.executeQuery();
		//resultSet.next();
		//Brew b=new Brew();
		resultSet.next();
		resultSet.getString(1);
		//System.out.println("company found and deleted");
		logger.info("company found and deleted");
			sql="delete from company where id="+id;
			result=stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "deleted successfully");
		
		
		
		
		}catch(SQLException e) {
			
			//e.printStackTrace();
			
		logger.warn("ERROR IN DELETING...");
		logger.warn(e.getMessage());
			JOptionPane.showMessageDialog(null, "deletion failed ");
		
		}
		
	}
	}
	
	
	
	
}


class Update extends JFrame implements ActionListener
{
	static JTextField t1;
	 static JTextField t2;
	
	 static String DB;
	 void generate(String DB)
	 {
		 this.DB=DB;
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
		
			SwingSql.b.updateData(Integer.parseInt(t1.getText()),Float.parseFloat(t2.getText()),DB);

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
	static String DB;
	void generate(String DB)
	{
		this.DB=DB;
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
			//System.out.println("DB is "+DB);
			rs=SwingSql.b.display(id,this.DB);
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
	
	 static String DB;
	public void generate(String DB)
	{
		this.DB=DB;
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
			SwingSql.b.insertData(t1.getText(),Float.parseFloat(t2.getText()),DB);
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
	 static String DB;
	 void generate(String DB)
	 {
		 this.DB=DB;
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
			SwingSql.b.deleteData(Integer.parseInt(t1.getText()),DB);
			//System.out.println(SwingSql.b);	
			 //SwingSql.b.insertData("name"," email", 9090909," city", 1);
			t1.setText("");
				
			 
		 }
		 
		 
	 }
	 
 }



public class SwingSql extends JFrame implements ActionListener {

	
	static Brew b=new Brew();
	static String DB="mssql";	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 //System.out.println(System.getProperty("user.dir"));
		String log4jConfPath = "E://nandan/swingsql/src/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
		SwingSql s=new SwingSql(); 
		 //String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		 //String DB_URL = "jdbc:mysql://localhost:3307/sharemarket?characterEncoding=latin1";

		 String DB_NAME = "sharemarket";
		 String USER = "root";
		 String PASS = "root";
		 String name,email,city;
		 int status,phone;
		 int choice,id;
		
		//Scanner in=new Scanner(System.in);
		b.connect( USER, PASS,DB_NAME,DB);
		b.createTable("company",DB);
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
          //  System.out.println("Insert");
            
            Insert i=new Insert();
            i.generate(DB);
            
            
            
        } 
        
        else if (s.equals("Delete")) { 
            // set the text of the label to the text of the field 
        	 //System.out.println("Delete"); 
        	 Delete d=new Delete();
        	 d.generate(DB);
        } 
        else if(s.equals("Display")) {
       Display d=new Display();
     //  System.out.println("the DB s"+DB);
       d.generate(DB);
        }
        
        else if(s.contentEquals("Update"))
        {
        	Update u=new Update();
        	u.generate(DB);
        	
        }        
        
    } 
	
	
}
