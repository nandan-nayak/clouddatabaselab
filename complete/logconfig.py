"""
Configuration For Logs with the help of python package "logging"  

"""

import logging
import logging.config
import os

filename="logs.txt"




#logging.config.fileConfig("logging.conf")



#specify a name for log().. this is not used anywhere and it is not the filename also
logger = logging.getLogger("simple_example")
logger.setLevel(logging.DEBUG)


#create handler

#ch = logging.StreamHandler()

#specify the filename ( *.txt ) where you need to store the logs 
ch=logging.FileHandler(filename,mode="a")
ch.setLevel(logging.DEBUG)




#format of the log
formatter = logging.Formatter("%(asctime)s - %(name)s - %(levelname)s - %(message)s")


ch.setFormatter(formatter)

logger.addHandler(ch)


#logger.debug("demodemo")


