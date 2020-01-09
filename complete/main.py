import eel
import mysqldbconnection as db
eel.init('web')
options = {
    'mode': "chrome", #or "chrome-app",
    'host': 'localhost',
    'port': 8080,
    'chromeFlags': ["--start-fullscreen", "--browser-startup-dialog"]
}

db.createtable();
@eel.expose
def add(companyname,value):
    
    
    message=db.insertvalues(companyname,value)
    eel.added(message)
    print(companyname+value)
    


@eel.expose
def modify(name, value):
    message=db.updateinfo(name,value)
    eel.modified(message)
    print(name + value)

@eel.expose
def display(name):
    result,message=db.display(name)
    
    eel.display(message,result)
    #print(id)

@eel.expose
def deletedetails(name):
    message=db.deleteinfo(name)
    print(message)
    eel.deleted(message)
    print(name)



@eel.expose
def characterprocess(char):
    values,flag=db.characterprocess(char)
    eel.characterprocessJS(values,flag)


eel.my_javascript_function('Hello ', 'world!') 

eel.start('main.html',options=options,suppress_error=True)

'''
try:
    eel.start('main.html',options=options)
except:
    print("er")
    pass
'''
