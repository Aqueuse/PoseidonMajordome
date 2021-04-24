import cherrypy
from jinja2 import Environment, FileSystemLoader


def launcher(application_path, port):
    env = Environment(loader=FileSystemLoader('static'))
    conf = {
        'global': {
            'server.socket_port': port
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

    cherrypy.quickstart(Root(), config=conf)
