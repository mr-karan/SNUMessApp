#Script to extract data from excel file and write it to JSON
# coding: utf-8

import pandas as pd
df = pd.read_excel('DH2.xlsx')
df=df.dropna(how='any')
z=df.iloc[:,(1,2,3,4,5,6,7)]
z.columns=['Mon','Tue','Wed','Thu','Fri','Sat','Sun']
z.to_json("week.json")



