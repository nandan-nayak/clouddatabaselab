/*
 * contains the code for MSSQL DB connection
 * 
 */

package swingsql;


//import swingsql.DBObject;
import java.sql.*;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;



class ConnectionClassMSSQL
{
	
	Connection conn = null;
	 Statement stmt = null;
	String classname="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	
}



class MSSQLDBConnection {
/*
 * contains the functions to insert , delete, update , display
 */
static	Logger logger = Logger.getLogger(MSSQLDBConnection.class.getName());
static String DB_NAME="nandan_sharemarket";
	static String tableName="nandan_company_value";
	static String infoTable="nandan_company_info";
static	ConnectionClassMSSQL connect(String user,String pass)
	{
	
	/*
	 * connects to DB for every Action (Insert,Update)
	 * return the connection object and statement object
	 * 
	 */
	
		//String connectionstring="jdbc:mysql://localhost:3307/"+DB_NAME;
		ConnectionClassMSSQL connectionObject=new ConnectionClassMSSQL();
		
		String connectionstring="jdbc:sqlserver://localhost;databaseName="+DB_NAME;
		
		
		
		
		
		
		try
		{
		Class.forName(connectionObject.classname);
		connectionObject.conn=  DriverManager.getConnection(connectionstring,user,pass);
		connectionObject.stmt=connectionObject.conn.createStatement();
		logger.info("connection to DB successfull");
		}
		catch(SQLException e)
		{
		
			logger.error("SQLException while connecting");
			logger.error(e);
			
		}
		catch(ClassNotFoundException e)
		{
			// for class.forname
			logger.error("ClassNotFoundException while connecting");
			logger.error(e);
		}
		
		return connectionObject;
		
	}
	
	
static	DeleteResult delete(DeleteObject deleteobject)
	{
	
	/*
	 * Takes the input to delete and returns the Delete Result 
	 * 
	 */
	
		DeleteResult result=new DeleteResult();
		
		ConnectionClassMSSQL connectionobject=connect("root","root");
		String 	sql="select * from "+tableName+" where companyname=?";
		PreparedStatement pstmt;
			try
			{
				pstmt=connectionobject.conn.prepareStatement(sql);
				pstmt.setString(1, deleteobject.companyName);
				result.resultset=pstmt.executeQuery();
				result.resultset.next();
				result.resultset.getString(1);
				
				
				
				
				sql="delete from "+tableName+" where companyname='"+deleteobject.companyName+"'";
				result.result=connectionobject.stmt.executeUpdate(sql);
				result.message="deleted successfully";
						
				logger.info("Deletion successfull for Input "+deleteobject.companyName );
			
			}catch(Exception e) {
				result.message="deletion failed...";
				//e.printStackTrace();
				logger.error("Exception while Deleting for input "+deleteobject.companyName);
				logger.error(e);
			
			
			}
			
		
		
		return result;
	}
	
/*
static boolean checkCompany(String companyName,ConnectionClassMSSQL connectionobject)
{
	// Connection connectionObject=connectionobject;
	PreparedStatement pstmt;
	 DisplayResult result=new DisplayResult();
	 String sql="select * from "+infoTable+" where companyname='"+companyName+"'";
	 try
	 {
		
		 result.result=connectionobject.stmt.executeQuery(sql);
		 System.out.println(result.result);
		 
	 }
	 catch(SQLException e)
	 {
		 e.printStackTrace();
		 
	 }
	 
	 return true;
}
*/


static	InsertResult insert(InsertObject insertObject)
	{
	/*
	 * 
	 * inserts the values to DB
	 * 
	 * 
	 */
		InsertResult result=new InsertResult();
		PreparedStatement pstmt;
		ConnectionClassMSSQL connectionobject=connect("root","root");
		String sql2="insert into "+infoTable+"(companyname,joined,location) values (?,?,?) ";
		String sql1="insert into "+tableName+"(companyname,sharevalue) values (?,?) ";
		
		try
		{
			pstmt=connectionobject.conn.prepareStatement(sql2);
			 pstmt.setString(1,insertObject.companyName);
			    pstmt.setString(2, insertObject.joined);
			    pstmt.setString(3, insertObject.location);
			    pstmt.executeUpdate();
			 pstmt=connectionobject.conn.prepareStatement(sql1);
			    //result=stmt.executeUpdate(sql);
			    pstmt.setString(1,insertObject.companyName);
			    pstmt.setDouble(2, insertObject.shareValue);
			    pstmt.executeUpdate();
			result.message="inserted successfull";
				logger.info("Insertion successfull for Input "+insertObject.companyName + " "+insertObject.shareValue );
			
		}catch(SQLException e)
		{
			result.message="insertion failed";
			
			logger.error("SQLException while insertion for input "+insertObject.companyName+" "+insertObject.shareValue);
			logger.error(e);
			//e.printStackTrace();
		}
		
		
		
		
		return result;
		
		
	}
	
	
static	UpdateResult update(UpdateObject updateObject)
	{	
		UpdateResult result=new UpdateResult();
		ConnectionClassMSSQL connectionobject=connect("root","root");
		PreparedStatement pstmt;
		//System.out.println(updateObject.shareValue);
		String sql="update "+tableName+" set sharevalue=? where id=?";
		
		
		try
		{
			pstmt=connectionobject.conn.prepareStatement(sql);
			pstmt.setDouble(1, updateObject.shareValue);
			pstmt.setString(2, updateObject.companyName);
			result.result=pstmt.executeUpdate();
			logger.info("Updation successfull for Input "+updateObject.companyName );
			result.message="updation successfull";
			if(result.result==0)
			{
				result.message="updation failed";
				logger.info("updation for empty for "+updateObject.companyName + " "+updateObject.shareValue);
				
					
			}
			else
			{
				result.message="updation success";
				logger.info("updation success "+updateObject.companyName + " "+updateObject.shareValue);
				
			}
		}
		
		
		
		catch(SQLException e)
		{
			
			result.message="updation failed";
			logger.error("SQLException while updation for input "+updateObject.companyName+" "+updateObject.shareValue);
			logger.error(e);
			
			//e.printStackTrace();
		}
		return result;
		
	}
	
static	DisplayResult display(DisplayObject displayObject)
	{
		DisplayResult result=new DisplayResult();
		ConnectionClassMSSQL connectionobject=connect("root","root");
		PreparedStatement pstmt;
		String sql="select * from "+tableName+" where companyname=?";
		try
		{
				
				 pstmt=connectionobject.conn.prepareStatement(sql);
				
				 pstmt.setString(1, displayObject.companyName);
				 
				 result.result=pstmt.executeQuery();
				result.message="display successfull";
				 /*if(result.result.first()==false)
					 {
					 	result.message="nothing to display";
					 }
					*/logger.info("Display successfull for Input "+displayObject.companyName );
		}
				 
		catch(SQLException e)
		{
			result.message="display failed";
			logger.error("SQLException while Display for input "+displayObject.companyName);
			logger.error(e);
			
		}
		
		return result;
	}
	
	
}
