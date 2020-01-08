class IDNotFoundException(Exception):
    def errormessage():
        return "Company ID not found"


class CompanyNameNotFound(Exception):
    def errormessage():
        return "Company ID not found"

class NegativeShareValue(Exception):
    def errormessage():
        return "sharevalue cannot be negative"

    
