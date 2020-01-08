import eel
import mysqldbconnection
eel.init('web')
options = {
    'mode': "chrome", #or "chrome-app",
    'host': 'localhost',
    'port': 8080,
    'chromeFlags': ["--start-fullscreen", "--browser-startup-dialog"]
}

@eel.expose
def add(companyname,value):
    print(companyname+value)


@eel.expose
def modify(id, value):
    print(id + value)

@eel.expose
def display(id):
    print(id)

@eel.expose
def deletedetails(id):
    print(id)






eel.my_javascript_function('Hello ', 'world!') 

try:
    eel.start('main.html',options=options)
except:
    pass
