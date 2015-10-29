#Script to extract data from excel file and write it to JSON
# coding: utf-8

import pandas as pd
dh1 = pd.read_excel('DH1.xlsx')
dh2 = pd.read_excel('DH2.xlsx')
dh1=dh1.dropna(how='any')
dh2=dh2.dropna(how='any')
dh1z=dh1.iloc[:,(1,2,3,4,5,6,7)]
dh2z=dh2.iloc[:,(1,2,3,4,5,6,7)]
dh1z.columns=['Mon','Tue','Wed','Thu','Fri','Sat','Sun']
dh2z.columns=['Mon','Tue','Wed','Thu','Fri','Sat','Sun']
dh1z.to_json("DH1.json")
dh2z.to_json("DH2.json")




