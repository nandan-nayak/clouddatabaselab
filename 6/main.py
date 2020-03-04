# -*- coding: utf-8 -*-
"""
Created on Sun Mar  1 12:33:19 2020

@author: hp
"""

import eel
import mssqldb as db
import inputobjects as obj


eel.init('web')
options = {
    'mode': "chrome", #or "chrome-app",
    'host': 'localhost',
    'port': 8080,
    'chromeFlags': ["--start-fullscreen", "--browser-startup-dialog"]
}


@eel.expose
def addStudentDetails(name,rollno,std,parentemail,year):
    
    student=obj.InsertStudentDetails();
    student.name=name
    student.rollno=rollno;
    
    student.parentemail=parentemail
    print(name)
    message=db.insertStudent(student,std,year)
    eel.added(message)
    
@eel.expose
def addTeacherDetails(name,rollno,subject,teachertype):
    
   teacher=obj.InsertTeacherDetails();
   teacher.name=name
   teacher.rollno=rollno;
   teacher.teachertype=teachertype
   print(name)
   #message=db.insertTeacher(teacher,subject)
   #eel.added(message)
   

    
@eel.expose
def addSubjectDetails(name,std,code):
    
   subject=obj.InsertSubjectDetails();
   subject.name=name
   subject.std=std;
   subject.code=code;
   message=db.insertSubject(subject)
   eel.added(message)



   
    


@eel.expose
def modify(name, value):
    message=db.updateinfo(name,value)
    eel.modified(message)
    print(name + value)

@eel.expose
def displayStudent(rollno):
    result,message=db.displayStudent(rollno)
    
    eel.display(message,result)
    #print(id)


@eel.expose
def displayAverage():
    result,message=db.displayAverage()
    
    eel.display(message,result)
    #print(id)
    

@eel.expose
def displayHighest(std):
    result,message=db.displayHighest(std)
    
    eel.display(message,result)
    #print(id)



@eel.expose
def displayAverageTeacher():
    result,message=db.displayAverageTeacher()
    
    eel.display(message,result)
    #print(id)



#eel.my_javascript_function('Hello ', 'world!') 

eel.start('student.html',options=options,suppress_error=True)

'''
try:
    eel.start('main.html',options=options)
except:
    print("er")
    pass
'''
