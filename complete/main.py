"""
main file which has the connection to front end as well as back end

"""

# import the eel for connecting FrontEnd with Python
#import file "sqlitedbconnection" for database transaction
import eel
import sqlitedbconnection as db


#initialize the eel
eel.init('web')
options = {
    'mode': "chrome", #or "chrome-app",
    'host': 'localhost',
    'port': 8080,
    'chromeFlags': ["--start-fullscreen", "--browser-startup-dialog"]
}


#create the database when executed 
db.createtable();


#exposes the python function "add(var1,var2)" in javascript with same function name   to take value from user
#in javascript call this function by passing the parameters
#message is sent from python to javascript, in javascript create function named "added"
@eel.expose
def add(companyname,value):
    """
    python function which adds the data into the database and returns the message to the front end
    """
    
    message=db.insertvalues(companyname,value) # inserting into database
    eel.added(message)                         #returning message to javascript to display in front end
    #print(companyname+value)
    


@eel.expose
def modify(name, value):
    """
    exposing the python function to take parameters to modify the values.
    
    """
    message=db.updateinfo(name,value)#modifying Table content
    eel.modified(message)            #returning message to frontend
    #print(name + value)

@eel.expose
def display(name):
    """
    This function takes company name from front end and send back result as well as message to front end
    
    """
    result,message=db.display(name) # call display function 
    
    eel.display(message,result)    #send result as well as message to front end  

    
    
@eel.expose
def deletedetails(name):
    """
    Used to delete the information from Database and send back the message
    """
    message=db.deleteinfo(name)
    #print(message)
    eel.deleted(message)
    #print(name)



@eel.expose
def characterprocess(char):
    """
    This function is used to  character by character processing during display
    which means if you type one character in the front end "display" part , the companies begining with that 
    character is sent back.
    
    """
    values,flag=db.characterprocess(char)
    eel.characterprocessJS(values,flag)


#eel.my_javascript_function('Hello ', 'world!') 


#start the application 

eel.start('main.html',options=options,suppress_error=True)

'''
try:
    eel.start('main.html',options=options)
except:
    print("er")
    pass
'''
