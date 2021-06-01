import json
import cherrypy
from jinja2 import Environment, FileSystemLoader

import modules.filesystem
from modules.rosetta import router
from modules.processManagement import manager
from modules.projectManagement import openProject_ui


def launcher(application_path, port):
    env = Environment(loader=FileSystemLoader('static'))
    conf = {
        'global': {
            'server.socket_port': port,
            'server.thread_pool': 30
        },
        '/': {
            'tools.staticdir.root': application_path
        },
        '/static': {
            'tools.staticdir.on': True,
            'tools.staticdir.dir': 'static'
        }
    }

    class Root(object):
        @cherrypy.expose
        def index(self):
            tmpl = env.get_template('index.html')
            return tmpl.render(baseURL="http://127.0.0.1:" + str(port))

        @cherrypy.expose
        def rosetta(self):
            if cherrypy.request.method == "POST":
                cherrypy.request.process_request_body = True
                cherrypy.response.headers['Access-Control-Allow-Methods'] = 'POST'
                cherrypy.response.headers['Access-Control-Allow-Headers'] = 'content-type'
                cherrypy.response.headers['Access-Control-Allow-Origin'] = '*'
                content_length = cherrypy.request.headers['Content-Length']
                rawbody = cherrypy.request.body.read(int(content_length))
                langage = rawbody.decode('utf-8').split(' ')[0]
                script = rawbody.decode('utf-8').replace(langage + ' ', '')
                result = str(router.route(langage, script))
                return result

        @cherrypy.expose
        def open(self):
            if cherrypy.request.method == "GET":
                manager.monitor('pyqt_open')
                json_tree = json.dumps(modules.filesystem.make_tree(openProject_ui.file_name))
                return json_tree

        @cherrypy.expose
        def create(self):
            if cherrypy.request.method == "GET":
                manager.monitor('pyqt_create')
                return 'create'

    cherrypy.quickstart(Root(), config=conf)
