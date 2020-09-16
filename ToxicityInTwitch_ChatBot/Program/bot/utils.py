import logging
import traceback
import codecs
import os
import socket
import urllib2

PASS = "oauth:6d2zjallti1nu9h8aa48keffzrmhop"
NICK = "mraceituno"
IDENT = "mraceituno"
REALNAME = "mraceituno"

HOST = "irc.twitch.tv"
PORT = 6667

def get_connection_params(channelName):
	url = "http://api.twitch.tv/api/channels/"+channelName+"/chat_properties"
	response = urllib2.urlopen(url)
	data = json.load(response)

	if data["eventchat"]:
		pieces = data["chat_servers"][0].split(":")
		connection_params = (pieces[0],int(pieces[1]))
	else:
		connection_params = (HOST, PORT)

	return connection_params


def run(game, channel):

	CHANNEL = "#" + channel
	GAME = game

	logging.debug("Starting bot...")
	f=""
	try:
		filepath = "./dataset_new/" + codecs.decode(GAME,"utf-8") + "_" + CHANNEL
		if os.path.isfile(filepath):
			f = open(filepath,"a")
		else:
			f = open(filepath,"w")
	except BaseException as e:
		traceback.print_exc()
		logging.warning(e)
		logging.warning("GAME "+ str(GAME))
		logging.warning("CHANNEL " + str(CHANNEL))
		exit()

	s = socket.socket()
	host, port = get_connection_params(channel)
	try:
		s.connect((host,port))
	except:
		traceback.print_exc()
		logging.warning("cannot connect to " + CHANNEL)
		exit()

	s.send("PASS %s\r\n" % PASS)
	s.send("NICK %s\r\n" % NICK)
	s.send("USER %s %s bla :%s\r\n" % (IDENT, host, REALNAME))
	s.send("JOIN %s\r\n" % CHANNEL)

	logging.debug("Joined " + CHANNEL)
	
	readbuffer = ""
	try:
		s.settimeout(1)
		while not self.stop:
			try:
				readbuffer=readbuffer+s.recv(1024)
				temp=readbuffer.split("\n")
				readbuffer=temp.pop()
				
				for line in temp:					
					toks = line.strip().split()
					if(toks[0]=="PING"):
						s.send("PONG %s\r\n" % line[1])

					if "PRIVMSG" in line:
						ts = time.time()
						timestamp = datetime.datetime.fromtimestamp(ts).strftime(':%Y-%m-%d %H.%M.%S ')
						line = timestamp + line

						logging.verbose(line)

						f.write(line + "\n")
						f.flush()
						os.fsync(f)
	
			except socket.timeout as e:
				err = e.args[0]
				if err != 'timed out':
					raise e


	finally:
		logging.warning("Exiting bot for channel " + CHANNEL)
		s.close()
		if f:
			f.close()

run("csgo", "yointerneto")