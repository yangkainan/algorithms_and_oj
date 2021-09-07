#!/usr/bin/env python

import getpass
import sqlite3
import csv

USER_PATH = "/Users/" + getpass.getuser()
RKIT_PATH = USER_PATH + "/Library/Containers/com.reederapp.rkit2.mac/Data/Library/Application Support/Reeder/rkit/"

conn = sqlite3.connect(RKIT_PATH + "rkit.db")
DATA_DB_PATH = RKIT_PATH + "rkit-data.db"

cursor = conn.cursor()
cursor.execute("ATTACH DATABASE '%s' AS data" % DATA_DB_PATH)


cursor.execute('''
    SELECT data.rkitemdata.title, data.rkitemdata.link,  data.rkitemdata.content FROM data.rkitemdata
    INNER JOIN rkitem
    WHERE data.rkitemdata.id == rkitem.id and rkitem.starred == 1 
''')

data = cursor.fetchall()

with open("starred_items.csv", "w") as f:
    writer = csv.writer(f)
    for row in data:
        writer.writerow(row)
