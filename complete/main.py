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
def modify(id, value):
    message=db.updateinfo(id,value)
    eel.modified(message)
    print(id + value)

@eel.expose
def display(name):
    result,message=db.display(name)
    eel.display(message)
    #print(id)

@eel.expose
def deletedetails(id):
    message=db.delete(id)
    print(message)
    eel.delete(message)
    print(id)






eel.my_javascript_function('Hello ', 'world!') 

eel.start('main.html',options=options,suppress_error=True)

'''
try:
    eel.start('main.html',options=options)
except:
    print("er")
    pass
'''
