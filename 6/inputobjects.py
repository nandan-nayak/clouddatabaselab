# -*- coding: utf-8 -*-
"""
Created on Sun Mar  1 11:53:40 2020

@author: hp
"""

class InsertStudentDetails:
    name=""
    rollno=0
    
    parentemail=""
    sql1="insert into student values(?,?,?)";
    sql2="insert into student_class values(?,?,?)"
    


class InsertTeacherDetails:
    name=""
    rollno=0
    teachertype=""
    sql="insert into teacher values(?,?,?)";
    sql1="insert into subject_teacher values(?,?,?)";
class InsertSubjectDetails:
    name=""
    std=0
    code=""
    sql="insert into student values(?,?,?,?,?)";
    

class ClassDetails:
    std=0
    sql="insert into student values(?,?,?,?,?)";


class LinkSubjectToClass:
    subjectname=""
    std=0
    sql="insert into student values(?,?,?,?,?)";
    
class LinkTeacherToSubject:
    teachername=""
    subjectname=""
    sql="insert into student values(?,?,?,?,?)";
    


