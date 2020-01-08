import sqlite3
from sqlite3 import Error

DBNAME="sharemarket.db"
createtablesql="create table if not exists companyinfo(id integer primary key autoincrement,companyname text,sharevalue real)"   


#conn=None
#cursor=None

def connect():
    con=None
    cursor=None
    try:
        con = sqlite3.connect(DBNAME)
        cursor= con.cursor()
        #print("successfully connected to DB");
    except Error:
        print(Error)
    return con,cursor


def createtable():
    con,cursor=connect()
    try:
        cursor.execute(createtablesql)
        con.commit()
    except Error:
        print(Error)
        con.rollback()
    finally:
        con.close();


def insertvalues(companyname,sharevalue):
    sql="insert into companyinfo(id,companyname,sharevalue) values(null,'"+companyname+"',"+str(sharevalue)+")";
    con,cursor=connect()
    print(sql)
    try:
        #print("inserted")
        cursor.execute(sql)
        con.commit()
    except Error as e:
        print(e)
        con.rollback()
    finally:
        con.close();
    #print(sql)

def deleteinfo(id):
    sql="delete from companyinfo where id="+str(id)
    con,cursor=connect()
    try:
        #print("deleted")
        cursor.execute(sql)
        con.commit();
    except Error as e:
        print(e)
        conn.rollback();
    finally:
        con.close();


def updateinfo(id,sharevalue):
    sql="update companyinfo set sharevalue="+str(sharevalue)+" where id="+str(id);
    con,cursor=connect();
    try:
        #print("updated")
        cursor.execute(sql)
        con.commit();
    except Error as e:
        print(e)
        conn.rollback();
    finally:
        con.close();

def display(id):
    sql="select * from companyinfo where id="+str(id)
    con,cursor=connect();
    result=None
    try:
        #print("displayed")
        cursor.execute(sql)
        result=cursor.fetchall()
        #con.commit()
    except Error as e:
        print(e);con.rollback();
    finally:
        con.close();
    
    return result
    
        




#connect();
createtable()
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





