import sqlite3
from sqlite3 import Error
import customexception as excep
import logconfig as log


DBNAME="sharemarket.db"
createtablesql="create table if not exists companyinfo(id integer primary key autoincrement,companyname text,sharevalue real)"   

log.logger.debug("debugged")
#conn=None
#cursor=None

def connect():
    con=None
    cursor=None
    
    try:
        con = sqlite3.connect(DBNAME)
        cursor= con.cursor()
        
        log.logger.info("connected to database")
        #print("successfully connected to DB");
    except Error:
        #print(Error)
        log.logger.error("database connection failed")
    return con,cursor


def createtable():
    con,cursor=connect()
    try:
        cursor.execute(createtablesql)
        con.commit()
        log.logger.info("created table")
    except Error:
        #print(Error)
        log.logger.error("database connection failed")
        con.rollback()
    finally:
        con.close();


def insertvalues(companyname,sharevalue):
    sql="insert into companyinfo(id,companyname,sharevalue) values(null,'"+companyname+"',"+str(sharevalue)+")";
    con,cursor=connect()
    #print(sql)
    message=""
    try:
        #print("inserted")
        if float(sharevalue)<=0:
            raise excep.NegativeShareValue
        cursor.execute(sql)
        con.commit()
        
        message="values  inserted successfully"
        log.logger.info("inserted to DB successfully with values "+companyname+"  "+sharevalue)
    except excep.NegativeShareValue:
        message="share value should be positive"
        log.logger.error("negative share value inserted for "+companyname)
    except Error as e:
        #print(e)
        message="insertion failed"
        log.logger.error("insertion failed for "+companyname)
        con.rollback()
    finally:
        con.close();
    
    return message

def deleteinfo(name):
    sql="delete from companyinfo where companyname='"+name+"'";
    con,cursor=connect()
    message=""
    
    displayresult=display(name)
    
        
    try:
        #print("deleted")
        if len(displayresult[0])<=0:
            raise excep.CompanyNameNotFound
            
        cursor.execute(sql)
        #print("result of delete is ",dir(result))
        con.commit();
        message="deleted successfully"
        log.logger.info("Deletion of "+name+" successfull ")
        
    except excep.CompanyNameNotFound:
        log.logger.error("company "+name+" not found for deletion")
        message="company not found"
    except Error as e:
        #print(e)
        log.logger.error("deletion  failed")
        con.rollback();
        message="failed to delete ... "
    
        
    finally:
        con.close();
    
    return message


def updateinfo(name,sharevalue):
    sql="update companyinfo set sharevalue="+str(sharevalue)+" where companyname='"+name+"'";
    con,cursor=connect();
    message=""
    displayresult=display(name)
    #print("display ",displayresult[0])
   
    try:
        if len(displayresult[0])<=0:
            #print("not found")
            raise excep.CompanyNameNotFound
        if float(sharevalue)<=0:
            raise excep.NegativeShareValue
        #print("updated")
        cursor.execute(sql)
        con.commit();
        message="updated successfully"
        log.logger.info("updation successfull "+name+" "+sharevalue)
    except excep.CompanyNameNotFound:
        log.logger.error("company not found "+name+" for updation failed")
        message="company name not found"
    except excep.NegativeShareValue:
        message="share value can't be negative"
        log.logger.error("negative share value for "+name)
    except Error as e:
        #print(e)
        con.rollback();
        message="updation Failed"
    finally:
        con.close();
    return message

def display(name):
    sql="select * from  companyinfo where companyname='"+str(name)+"'";
    con,cursor=connect();
    result=0
    message=""
    try:
        #print("displayed")
        cursor.execute(sql)
        result=cursor.fetchall()
        if len(result)<=0:
            message="company not found"
        else:
                #con.commit()
            message="company found"
    except Error as e:
        #print("display error")
        #print(e);
        con.rollback();
        log.logger.error("problem in display")
        message="something went wrong..."
    finally:
        con.close();
    
    return result,message
    
        


def characterprocess(char):
    sql="SELECT companyname FROM companyinfo WHERE companyname LIKE '"+char+"%';"
    con,cursor=connect();
    result=0
    flag=0
    try:
        cursor.execute(sql)
        result=cursor.fetchall()
        if len(result)<=0:
            flag=0
        else:
                #con.commit()
            flag=1
    except Error as e:
        #print(e)
        log.logger.error("problem in characterprocess")
    finally:
        con.close();
        
    return result,flag

#connect();
#createtable()
'''
#insertvalues('adani',120.5)
result=display(1)
for row in result:
    print(row)
updateinfo(1,10)
result=display(1)
for row in result:
    print(row)
deleteinfo(1)
result=display(1)
for row in result:
    print(row)

deleteinfo(3)
'''
