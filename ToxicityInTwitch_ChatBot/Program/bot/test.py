import fasttext


model = fasttext.load_model("data/model1.bin")
result=model.predict("i love you")
print(str(result))
# test_file= open("test_file.txt", "r")
# for row in test_file:
#     result=model.predict(row)
#     print(str(result)+ " " + str(row))
