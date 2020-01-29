package swingsql;


//import swingsql.DBObject;
import java.sql.*;

import org.apache.log4j.Logger;

import swingsql.Log4JProperties.*;

class ConnectionClassMySQL 
{
	
	Connection conn = null;
	 Statement stmt = null;
	String classname="com.mysql.jdbc.Driver";
	
		
	
}


 class MySQLDBConnection {
	 
	
	 
static	Logger logger = Logger.getLogger(MySQLDBConnection.class.getName());
static String DB_NAME="nandan_sharemarket";
static String tableName="nandan_company_value";
static String infoTable="nandan_company_info";
 static	ConnectionClassMySQL connect(String user,String pass)
	{
		String connectionstring="jdbc:mysql://localhost:3307/"+DB_NAME;
		ConnectionClassMySQL connectionObject=new ConnectionClassMySQL();
		
		
		
		try
		{
		Class.forName(connectionObject.classname);
		connectionObject.conn=  DriverManager.getConnection(connectionstring,user,pass);
		connectionObject.stmt=connectionObject.conn.createStatement();
		logger.info("connection to DB successfull");
		}
		catch(SQLException e)
		{
			logger.error("SQLException while connection ");
			logger.error(e);
			
			
		}
		catch(ClassNotFoundException e)
		{
			// for class.forname
			logger.error("ClassNotFoundException while connectionut ");
			logger.error(e);
		}
		
		return connectionObject;
		
	}
	
	 static	DeleteResult delete(DeleteObject deleteobject)
	{
		DeleteResult result=new DeleteResult();
		
		ConnectionClassMySQL connectionobject=connect("root","root");
		String sql="select * from "+tableName+" where companyname='"+deleteobject.companyName+"'";
		
			try
			{
				result.resultset=connectionobject.stmt.executeQuery(sql);
				result.resultset.next();
				result.resultset.getString(1);
				sql="delete from "+tableName+" where companyname='"+deleteobject.companyName+"'";
				result.result=connectionobject.stmt.executeUpdate(sql);
				result.message="deleted successfully";
						
			
			
			}catch(Exception e) {
				result.message="deletion failed...";
				//e.printStackTrace();
				logger.error("SQLException while Delete for input "+deleteobject.companyName);
				logger.error(e);
			
			
			}
			
		
		
		return result;
	}
	 
	 static boolean checkCompany(String companyName,ConnectionClassMySQL connectionobject)
	 {
		// Connection connectionObject=connectionobject;
		 DisplayResult result=new DisplayResult();
		 String sql="select * from "+infoTable+" where companyname='"+companyName+"'";
		 try
		 {
			
			 result.result=connectionobject.stmt.executeQuery(sql);
			 
			 
		 }
		 catch(SQLException e)
		 {
			 
			 
		 }
		 
		 return true;
	 }
	
static	InsertResult insert(InsertObject insertObject)
	{
	
		InsertResult result=new InsertResult();
		
		ConnectionClassMySQL connectionobject=connect("root","root");

		//boolean companyfound=checkCompany(insertObject.companyName,connectionobject);
		String sql="insert into "+tableName+"(companyname,sharevalue) values('"+insertObject.companyName+"',"+insertObject.shareValue+") ";
	    
		try
		{
			
			 result.result=connectionobject.stmt.executeUpdate(sql);
			result.message="Inserted successfully";
			logger.info("insertion to DB successfull "+insertObject.companyName+" "+insertObject.shareValue);
		}catch(SQLException e)
		{
			result.message="insertion failed";
			logger.error("SQLException while insert "+insertObject.companyName+" "+insertObject.shareValue);
			logger.error(e);
			//e.printStackTrace();
		}
		
		
		
		
		return result;
		
		
	}
	
	
static	UpdateResult update(UpdateObject updateObject)
	{	
		UpdateResult result=new UpdateResult();
		ConnectionClassMySQL connectionobject=connect("root","root");
		//System.out.println(updateObject.shareValue);
		String sql="update "+tableName+" set sharevalue="+updateObject.shareValue+" where companyname= '"+updateObject.companyName+"'";
		
		logger.info("updation to DB successfull "+updateObject.companyName+" "+updateObject.shareValue);
		try
		{
			result.result=connectionobject.stmt.executeUpdate(sql);
			result.message="updated successfully";logger.info("update to DB successfull "+updateObject.companyName+" "+updateObject.shareValue);
			if(result.result==0)
				result.message="updation failed";
			
		}
		catch(SQLException e)
		{
			
			result.message="updation failed";
			
			logger.error("SQLException while update  "+updateObject.companyName);
			logger.error(e);
			//e.printStackTrace();
		}
		return result;
		
	}
	
static	DisplayResult display(DisplayObject displayObject)
	{
		DisplayResult result=new DisplayResult();
		ConnectionClassMySQL connectionobject=connect("root","root");
		String sql="select * from "+tableName+" where companyname='"+displayObject.companyName+"'";
		try
		{
			 result.result=connectionobject.stmt.executeQuery(sql);
			 result.message="display successfull";
			 logger.info("display  successfull "+displayObject.companyName);
		}
		catch(SQLException e)
		{
			result.message="display failed";
			logger.error("SQLException while Display  "+displayObject.companyName );
			logger.error(e);
		}
		
		return result;
	}
	
	
}
