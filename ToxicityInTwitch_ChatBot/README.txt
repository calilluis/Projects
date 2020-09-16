
Toxicity in Twitch.
---------------------
This project consists in a Twitch chat bot that analyzes the toxicity of the Twitch chat and is able to change the chat mode in order of it. We also included a briev study of the toxicity in different streams. We used Natural Lenguage Processing and Machine Learning in order to achieve the objective. We used Fasttext, as its a very powerful library of NLP + ML.

In the "Program" folder there are all the necessary files.
Inside there, there is the "bot" folder where you can find all the implemented files:

- dic_filter.py: main file of the app. It classifies, grabs the  data in real time, calcs the toxicity and moderates the chat.
- livegraph.py: is the code that will generate a real time graph.
- graph_data.txt: is the file that is opened inside the livegrpah.py in order to generate the graph.
- labeled_data.txt: contains all the messages found and classified.
- reader.py: Code that we use to grab the twitch chat in real time. 

How to execute:
- Ubuntu is recomended in order for the program to work correctly.
- You must install fasttext in the terminal inside of the project folder.
- You must execute in two different windows the livegraph.py and the dic-filter.py in order for the graph to be seen in real time.
- Commands: python3 dic_filter.py
	    python livegraph.py
