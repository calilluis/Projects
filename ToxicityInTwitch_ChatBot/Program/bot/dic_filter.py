import socket
import fasttext
import datetime
import math
import string

HOST = 'irc.twitch.tv'
PORT = 6667
NICK = 'mraceituno'
PASS = 'oauth:m3rwlmfdy32jldor3z29y0b2eezugb'
CHAN = '#ml7support'

#Load our fasttext model
model = fasttext.load_model("data/model1.bin")

#Funció que calcula la toxicitat. La llista d'entrada està plena de 1 i 0 
#depenent de les etiquetes dels missatges
def calculate_toxicity(lst):
	total_tweets = 0
	toxit_tweets = 0
	for i in range(len(lst)):
		if lst[i]==1:
			toxit_tweets+=1

		total_tweets+=1

	t = toxit_tweets/total_tweets
	return t

#Funció que aplica el filtre de diccionari i fasttext.
def filter(parameter):
	aux = 0
	words = parameter.split(" ")
	for i in range (len(words)):
		dic = open("dictionary.txt", "r")
		if (len(words)>1 and i < len(words)-1):
			combination = words[i]+" "+words[i+1]
		for line in dic:
			line = line.rstrip('\n')
			if (len(words)-i > 1):
				#Com que el diccionari té paraules compostes, tenim creada la variable combination 
				#que ens agafa la paraula llegida i la següent a aquesta. Només fa aquesta comparació si la frase té més d'una paraula
				if (words[i].lower()==line or combination.lower()==line.strip()):
					aux = 1
					f = open("labeled_data.txt", "a")
					f.write("__label__toxic "+parameter+"\n")
					f.close()
					print("__label__toxic "+parameter+"\n")
					return 1
			else:
				if (words[i].lower() == line):
					aux = 1
					f = open("labeled_data.txt", "a")
					f.write("__label__toxic "+parameter+"\n")
					f.close()
					print("__label__toxic "+parameter+"\n")
					return 1

		dic.close()
	# si aux=0 vol dir que el diccionari no ha detectat toxic la frase i per tant la passem al fasttext
	if (aux == 0):

		f = open("labeled_data.txt", "a")
		#utilitzem el model entrenat per predir la classificació
		result=model.predict(message)
		if(str(result[0]) == "('__label__notoxic',)"):
			f.write("__label__notoxic "+parameter+"\n")
			print("__label__notoxic "+parameter+"\n")
			return 0
		else:
			f.write("__label__toxic "+parameter+"\n")
			print("__label__toxic "+parameter+"\n")
			return 1
		f.close()


def send_message(message):
	s.send(bytes('PRIVMSG #' + NICK + ' :' + message + '\r\n', 'UTF-8'))

#funció que ens serveix per canviar el mode del chat
def bot_action(toxicity):
	if(toxicity > 20):
		return 1;
	if(toxicity <= 20):
		return -1;

s = socket.socket()
s.connect((HOST, PORT))
s.send(bytes('PASS ' + PASS + '\r\n', 'UTF-8'))
s.send(bytes('NICK ' + NICK + '\r\n', 'UTF-8'))
s.send(bytes('JOIN ' + CHAN + ' \r\n', 'UTF-8'))


init_datetime = datetime.datetime.now()
final_datetime = datetime.datetime.now()
percentage = []
interval = 1
timeout = 0

while True:
	line = str(s.recv(1024))
	if 'End of /NAMES list' in line:
		break

while True:
	for line in str(s.recv(1024)).split('\\r\\n'):
		if "PING :tmi.twitch.tv" in line:
			print("PONG :tmi.twitch.tv")
			s.send(bytes("PONG :tmi.twitch.tv\r\n", "UTF-8"))

		parts = line.split(':')
		if len(parts) < 3:
			continue

		if 'QUIT' not in parts[1] and 'JOIN' not in parts[1] and 'PART' not in parts[1]:
			message = (parts[2])[:len(parts[2])]
			message = message.lower()
			message = message.translate((str.maketrans("",""), string.punctuation)) #remove [!”#$%&’()*+,-./:;<=>?@[\]^_`{|}~]:
			message = message.strip()
		final_datetime = datetime.datetime.now()
		difference = final_datetime - init_datetime
		result = filter(message)
		percentage.append(result)
		#anem mirant si l'interval de temps es superior o igual al determinat 
		if(difference.seconds >= 10):
			#en cas de ser-ho, calculem la toxicitat de l'array de missatges
			toxicity = calculate_toxicity(percentage)
			toxicity = math.trunc(toxicity*100)
			#depenent de la toxicitat canviem el mode de chat
			timeout = timeout + bot_action(toxicity)
			if(timeout <= 0):
				timeout = 0
			if(timeout >= 3):
				timeout = 3
			if(timeout == 0):
				send_message("/followersoff")
				send_message("/subscribersoff")
				send_message("/emoteonlyoff")
				print("Everybody")
			if(timeout == 1):
				send_message("/followers")
				send_message("/subscribersoff")
				send_message("/emoteonlyoff")
				print("Followers")
			if(timeout == 2):
				send_message("/followersoff")
				send_message("/subscribers")
				send_message("/emoteonlyoff")
				print("Subs")
			if(timeout == 3):
				send_message("/followersoff")
				send_message("/subscribersoff")
				send_message("/emoteonly")
				print("Emojis")
			
			print(toxicity)
			#borrem totes les dades anteriors i tornem a començar
			percentage = []
			init_datetime = final_datetime
			f = open("graph_data.txt", "a")
			f.write(str(interval)+", "+str(toxicity)+"\n")
			f.close()
			interval +=1
