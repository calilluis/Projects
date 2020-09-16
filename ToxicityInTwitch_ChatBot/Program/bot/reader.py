import socket

HOST = "irc.twitch.tv"
PORT = 6667
NICK = "mraceituno_r"
PASS = "oauth:byam9pzdc2124gr37ki4xvpoqsy5fq"
CHAN = "#alexelcapo"
def send_message(message):
    s.send(bytes("PRIVMSG #" + NICK + " :" + message + "\r\n", "UTF-8"))

s = socket.socket()
s.connect((HOST, PORT))
s.send(bytes("PASS " + PASS + "\r\n", "UTF-8"))
s.send(bytes("NICK " + NICK + "\r\n", "UTF-8"))
s.send(bytes("JOIN "+ CHAN + " \r\n", "UTF-8"))


while True:
    line = str(s.recv(1024))
    if "End of /NAMES list" in line:
        break

while True:
    for line in str(s.recv(1024)).split('\\r\\n'):
        parts = line.split(':')
        if len(parts) < 3:
            continue

        if "QUIT" not in parts[1] and "JOIN" not in parts[1] and "PART" not in parts[1]:
            message = parts[2][:len(parts[2])]
	
        f = open("labeledData.txt", "a")
        f.write("__label__toxic "+message+"\n")
        f.close()
        print(message)
