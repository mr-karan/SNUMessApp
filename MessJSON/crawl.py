__author__ = 'Vutsuak'

import urllib2
import BeautifulSoup as bs4

DH = urllib2.urlopen("http://messmenu.snu.in/messMenu.php")
ct=0
soup = bs4.BeautifulSoup(DH)
rows = soup.findAll('tr')
for tr in rows:
    for head in tr.findAll('th',attrs={'class':'alert alert-info'}):
        print "\n"+ head.text
    for cols in tr.findAll('td'):
        try:
            x= cols['class']
        except:
            x= cols.text.split('&nbsp;')
            for i in x:
                print i
