
Toxicity in Twitch.
---------------------

En la carpeta Program hi ha tots els fitxers necessaris.
Dins d'aquesta, hi ha la carpeta bot on es situen els fitxers que hem implementat nosaltres:
- dic_filter.py: es el fitxer main de l'aplicació. Fem la classificació, la recollida en live de dades, 
				 el càlcul de toxicitat i la moderació del chat.

- livegraph.py: es el codi que ens genera el graf en temps real.

- graph_data.txt: es el fitxer que s'obre dins de livegrpah.py per a generar el graf.

- labeled_data.txt: conté tots els missatges recollits i classificats.

- reader.py: Codi que utilitzem per recollir el chat de twitch en directe. Tot i ser utilitzat també en dic_filter.py, 
			 ens va servir a l'hora de etiquetar missatges


Com executar l'aplicació:
- Es recomana utilitzar el sistema operatiu d'Ubuntu per tal de que tot funcioni correctament.
- S'ha d'instalar fasttext en el terminal entrant dins la carpeta del projecte.
- Executar en dos scripts diferents el livegraph.py i el dic_filter.py per tal de que el graf es vegi en tempos real.
- Comandes: python3 dic_filter.py  
			python livegraph.py