import logging
import logging.config
import os

filename="logs.txt"




#logging.config.fileConfig("logging.conf")

logger = logging.getLogger("simple_example")
logger.setLevel(logging.DEBUG)


#create handler

#ch = logging.StreamHandler()


ch=logging.FileHandler(filename,mode="a")
ch.setLevel(logging.DEBUG)




#format of the log
formatter = logging.Formatter("%(asctime)s - %(name)s - %(levelname)s - %(message)s")


ch.setFormatter(formatter)

logger.addHandler(ch)


#logger.debug("demodemo")


