#import sqlite3
#from sqlite3 import Error
#import inputobjects as obj
import logconfig as log
import pyodbc
from pyodbc import Error


DBNAME="student"

def dbConnection():
    con=None
    cursor=None
    
    try:
        #con = sqlite3.connect(DBNAME)
        con = pyodbc.connect('Driver={SQL Server};'
                              'Server=DESKTOP-0MFRJ4E;'
                              'Database=school;')
        cursor= con.cursor()
        log.logger.info("connected to database")
        
        #print("successfully connected to DB");
    except Error:
        print(Error)
        log.logger.error("database connection failed")
    return con,cursor


def insertStudent(details,std,year):
    con,cursor=dbConnection();  
    message=""
    
    try:
        
        #sql=details.sql+"",int(details.rollno),details.name,details.parentemail
        print("sql is ",details.sql1,int(details.rollno),details.name,details.parentemail)
        cursor.execute(details.sql1,int(details.rollno),details.name,details.parentemail)
        
        con.commit()
        #sql1=details.sql2+""(details.rollno,std,year)
        
        cursor.execute(details.sql2,int(details.rollno),int(std),int(year))
        con.commit()
        message="inserted to DB successfully with values "+details.name
        log.logger.info(message)
    except:
        message="student insertion failed"
        log.logger.error(message)
        con.rollback()
    return message

def insertTeacher(details,subject):
    con,cursor=dbConnection();  
    message=""
    try:
        
        sql=details.sql+""
        cursor.execute(sql)
        con.commit()
        if searchforteacher(details.rollno)<=0:
            sql1=details.sql1+""
            cursor.execute(sql1)
            con.commit()
        message="inserted to DB successfully with values "
        log.logger.info(message)
    except:
        message="teacher insertion failed"
        log.logger.error(message)
    return message




def searchforteacher(rollno):
    con,cursor=dbConnection();  
    result=None
    try:
        
        sql="select * from  teacher where rollno =?"
        cursor.execute(sql)
        con.commit()
        
        result=cursor.fetchone()
        log.logger.info("search for teacher successfull ")
    except:
        log.logger.error("teacher search failed")
    return result


def insertSubject(details):
    con,cursor=dbConnection();  
    
    message=""
    try:
        
        sql=details.sql+""
        cursor.execute(sql)
        con.commit()
        if searchforsubject(details.name)<=0:
            sql1=details.sql1+""
            cursor.execute(sql1)
            con.commit()
        message="inserted to DB successfully with values "+details.name
        log.logger.info(message)
    except:
        message="subject insertion failed"
        log.logger.error(message)
    message


def searchforsubject(subcode):
    con,cursor=dbConnection();  
    result=None
    try:
        
        sql="select * from  subject where code =?"
        cursor.execute(sql)
        con.commit()
        result=cursor.fetchone()
        log.logger.info("search for teacher successfull ")
    except:
        log.logger.error("teacher search failed")
    return result


"""
def linkSubjectToClass(details):
    con,cursor=dbConnection();  
    try:
        
        sql=details.sql+""
        log.logger.info("inserted to DB successfully with values "+details)
    except:
        log.logger.error("Failed to link subject to class")
        
def linkTeacherToSubject(details):
    con,cursor=dbConnection();  
    try:
        
        sql=details.sql+""
        log.logger.info("inserted to DB successfully with values "+details)
    except:
        log.logger.error("Failed to link teacher and subject")

"""

def display(details):
    con,cursor=dbConnection();  
    try:
        
        sql=details.sql+""
        log.logger.info("inserted to DB successfully with values "+details)
    except:
        log.logger.error("Display failed")
    
    
        
        
    
    
