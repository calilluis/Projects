import argparse
import fileinput
import numpy as np
import fasttext
from sklearn.metrics import confusion_matrix
import string

k = 10
filenames = ['fold0.txt','fold1.txt','fold2.txt','fold3.txt','fold4.txt','fold5.txt','fold6.txt','fold7.txt','fold8.txt','fold9.txt']

for i in range(k):
	model=None #reset model
	filenames.remove('fold'+str(i)+'.txt');
	for j in range(k-1):
		with open('kfold/train/train'+str(i)+'.train', 'a', encoding='utf-8-sig') as fout, open("kfold/"+filenames[j], 'r', encoding='utf-8-sig') as fin:
			for line in fin:
				l = line.lower()
				l = l.translate((str.maketrans("",""), string.punctuation))
				fout.write(l)
	filenames.insert(i, 'fold'+str(i)+'.txt')
	model = fasttext.train_supervised(input="kfold/train/train"+str(i)+".train", lr=1.0, epoch=25, wordNgrams=2)
	test_file= open('kfold/fold'+str(i)+'.txt', "r", encoding='utf-8-sig')
	with open("kfold/predictions/prediction"+str(i)+".txt",'a', encoding='utf-8-sig') as pred:
		for row in test_file:
			result=None
			if line[9:13] == "toxi":
				r1 = row[15:-1].lower()
				r1 = r1.translate((str.maketrans("",""), string.punctuation)) #remove [!”#$%&’()*+,-./:;<=>?@[\]^_`{|}~]:
				r1 = r1.strip()
				result=model.predict(r1)
			elif line[9:13] == "noto":
				r2 = row[17:-1].lower()
				r2 = r2.translate((str.maketrans("",""), string.punctuation)) #remove [!”#$%&’()*+,-./:;<=>?@[\]^_`{|}~]:
				r2 = r2.strip()
				result=model.predict(r2)
			pred.write(str(result)+ " " + str(row))



predictionFiles = ['prediction0.txt','prediction1.txt','prediction2.txt','prediction3.txt','prediction4.txt','prediction5.txt','prediction6.txt','prediction7.txt','prediction8.txt','prediction9.txt']
test_labels = []
pred_labels = []

for i in range(k):
	with open('kfold/predictions/prediction'+str(i)+'.txt','r', encoding='utf-8-sig') as preds:
		for line in preds:
			if(line.split()[0] != '((\'\\ufeff__label__notoxic\',),'):
				pred_labels.append(line.split()[0])
				test_labels.append('((\''+line.split()[2]+'\',),')


eq = 0
for i in range(len(test_labels)):
	if(test_labels[i] == pred_labels[i]):
		eq += 1
		
print("Accuracy: " + str(eq / len(test_labels)))
print(confusion_matrix(test_labels, pred_labels))

output = set()
for x in test_labels:
    output.add(x)
print(output)

