import fasttext
import string

with open("data/labeledData.train", 'r') as lines:
	for line in lines:
		l = line.lower()
		l = l.translate((str.maketrans("",""), string.punctuation)) 
		output = open("data/labeledDataNormalized.train",'a')
		output.write(str(l))

model = fasttext.train_supervised(input="data/labeledDataNormalized.train", lr=1.0, epoch=25, wordNgrams=2)
#bucket=200000, dim=50, loss='hs' to make it faster
model.save_model("data/model1.bin")