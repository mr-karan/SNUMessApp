from flask import Flask, request, jsonify, redirect, url_for
import pyexcel.ext.xlsx
import pandas as pd
from werkzeug import secure_filename

UPLOAD_FOLDER = 'upload'
ALLOWED_EXTENSIONS = set(['xlsx'])


app=Flask(__name__)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER


def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1] in ALLOWED_EXTENSIONS

@app.route("/upload", methods=['GET', 'POST'])
def upload_file():
    if request.method == 'POST': 
        file1 = request.files['file1']
        file2 = request.files['file2']

        if file1 and allowed_file(file1.filename) and file2 and allowed_file(file2.filename):

            dh1 = pd.read_excel(file1)
            dh1=dh1.dropna(how='any')
            dh1z=dh1.iloc[:,(1,2,3,4,5,6,7)]
            dh1z.columns=['Mon','Tue','Wed','Thu','Fri','Sat','Sun']
            dh1z.to_json("DH1.json")

            dh2 = pd.read_excel(file2)
            dh2=dh2.dropna(how='any')
            dh2z=dh2.iloc[:,(1,2,3,4,5,6,7)]
            dh2z.columns=['Mon','Tue','Wed','Thu','Fri','Sat','Sun']
            dh2z.to_json("DH2.json")
                        
            return 'Done! :)'
    

    return '''
    <!doctype html>
    <title>Upload an excel file</title>
    <h1>Excel file upload (xlsx only)</h1>
    <form action="" method=post enctype=multipart/form-data><p>
    DH1:<input type=file name=file1><br>
    DH2:<input type=file name=file2><br>
    <input type=submit value=Upload>
    </form>
    '''

if __name__ == "__main__":
    app.run()