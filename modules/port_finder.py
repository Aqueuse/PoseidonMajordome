import socket
import errno


# loop for a port not in use and write it to the config file
def find_socket():
    my_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    for i in range(3000, 6000):
        try:
            my_socket.bind(("127.0.0.1", i))
            return i
        except socket.error as error:
            if error.errno == errno.EADDRINUSE:
                print("Port "+str(i)+" is already in use")
            else:
                # something else raised the socket.error exception
                print(error)
    my_socket.close()
