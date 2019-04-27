import glob2
import datetime

def merge_file(filenames):
    with open(datetime.datetime.now().strftime("%a-%d")+".txt","w") as fileMega:
        for filename in filenames:
            with open(filename,"r") as file:
                fileMega.write(file.read()+'\n')




filenames = glob2.glob("*.txt")
merge_file(filenames)
