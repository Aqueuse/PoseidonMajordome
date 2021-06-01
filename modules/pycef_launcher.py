from cefpython3 import cefpython as cef
import sys


def launcher(url):
    sys.excepthook = cef.ExceptHook  # To shutdown all CEF processes on error
    cef.Initialize()
    cef.CreateBrowserSync(url=url, window_title="Poseidon Majordome")
    cef.MessageLoop()
    cef.Shutdown()
