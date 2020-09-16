import random
import fileinput
import numpy as np
# import fasttext
# from sklearn.metrics import confusion_matrix

k = 10
filenames = ['fold0.txt','fold1.txt','fold2.txt','fold3.txt','fold4.txt','fold5.txt','fold6.txt','fold7.txt','fold8.txt','fold9.txt']


lines_notox = open("kfold/data/notoxic.txt").readlines()
nlinesnotox=len(lines_notox)
random.shuffle(lines_notox)


lines_tox = open("kfold/data/toxic.txt").readlines()
nlinestox=len(lines_tox)
random.shuffle(lines_tox)

separacioi=nlinesnotox/10
separacioj=nlinestox/10
print(separacioi)
print(separacioj)
i=0
j=0
for file in filenames:
	with open("kfold/folds/"+file, 'a') as fold:
		fold.writelines(lines_notox[i:i+separacioi])
		fold.writelines(lines_tox[j:j+separacioj])
		i=i+separacioi
		j=j+separacioj
