import time
from datetime import datetime as dt
hosts_path = "C:\\Windows\\System32\\drivers\\etc\\hosts"
redirect = "127.0.0.1"
websites_list = ["www.9gag.com", "9gag.com","www.facebook.com", "facebook.com"]

while True:
    if dt(dt.now().year, dt.now().month, dt.now().day,8) < dt.now() < dt(dt.now().year, dt.now().month, dt.now().day,16):

        with open(hosts_path,'r+') as file:
            content = file.read()
            for website in websites_list:
                if website in content:
                    pass
                else:
                    file.write(redirect +" " + website + "\n")
        print("Working Hours")
    else:

        with open(hosts_path,'r+') as file:
            content_list = file.readlines()
            file.seek(0)
            for line in content_list:
                if not any(website in line for website in websites_list):
                    file.write(line)
            file.truncate()
        print("Fun hours")
    time.sleep(5)
