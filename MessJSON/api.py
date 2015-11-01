
from flask import Flask, jsonify, abort, request, make_response, url_for

app = Flask(__name__, static_url_path = "")


@auth.error_handler
def unauthorized():
    return make_response(jsonify( { 'error': 'Unauthorized access' } ), 403)
    # return 403 instead of 401 to prevent browsers from displaying the default auth dialog
    
@app.errorhandler(400)
def not_found(error):
    return make_response(jsonify( { 'error': 'Bad request' } ), 400)

@app.errorhandler(404)
def not_found(error):
    return make_response(jsonify( { 'error': 'Not found' } ), 404)

tasks = [
    {
        'id': 1,
        'title': u'Buy groceries',
        'description': u'Milk, Cheese, Pizza, Fruit, Tylenol', 
        'done': False
    },
    {
        'id': 2,
        'title': u'Learn Python',
        'description': u'Need to find a good Python tutorial on the web', 
        'done': False
    }
]
@app.route('/todo/api/dh1/breakfast', methods = ['GET'])
def get_tasks():
    return jsonify( { 'tasks': map(make_public_task, tasks) } )

@app.route('/todo/api/dh1/lunch', methods = ['GET'])
def get_tasks():
    return jsonify( { 'tasks': map(make_public_task, tasks) } )

@app.route('/todo/api/dh1/dinner', methods = ['GET'])
def get_tasks():
    return jsonify( { 'tasks': map(make_public_task, tasks) } )

@app.route('/todo/api/dh2/breakfast', methods = ['GET'])
def get_tasks():
    return jsonify( { 'tasks': map(make_public_task, tasks) } )

@app.route('/todo/api/dh2/lunch', methods = ['GET'])
def get_tasks():
    return jsonify( { 'tasks': map(make_public_task, tasks) } )

@app.route('/todo/api/dh2/dinner', methods = ['GET'])
def get_tasks():
    return jsonify( { 'tasks': map(make_public_task, tasks) } )

@app.route('/todo/api/v1.0/tasks/<int:task_id>', methods = ['GET'])
def get_task(task_id):
    task = filter(lambda t: t['id'] == task_id, tasks)
    if len(task) == 0:
        abort(404)
    return jsonify( { 'task': make_public_task(task[0]) } )

if __name__ == '__main__':
    app.run(debug = True)
