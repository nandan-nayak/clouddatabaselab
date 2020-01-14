"""
This class Provides Custom Exception for the Database

import this class and "raise the Exception" to throw the Exception 
while catching the Exception call the particular function 

"""


class IDNotFoundException(Exception):
    def errormessage():
        return "Company ID not found"


class CompanyNameNotFound(Exception):
    def errormessage():
        return "Company ID not found"

class NegativeShareValue(Exception):
    def errormessage():
        return "sharevalue cannot be negative"

    
