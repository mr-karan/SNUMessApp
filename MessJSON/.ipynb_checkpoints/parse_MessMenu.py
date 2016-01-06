__author__ = 'Vutsuak'

import urllib2
import BeautifulSoup as bs4


DH = urllib2.urlopen("http://snulinks.snu.edu.in/snuMess/messMenu.php")

soup = bs4.BeautifulSoup(DH)
ct = 0
print "DH1 ----- MENU------\n\n"
for i in soup.findAll("p"):
     if i.string == "Monday":
                ct += 1
                if ct > 1:
                    print "\n\nDH2 ----- MENU------\n\n"
     print i.text



