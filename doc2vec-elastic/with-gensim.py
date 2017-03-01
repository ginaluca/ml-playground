from gensim import models
from gensim.models import word2vec

input_file='es.txt'

sentences = word2vec.LineSentence(input_file)
model = models.Word2Vec(sentences, size=50, window=5, min_count=5, workers=4)

model.save_word2vec_format('model.txt', binary=False)


# model.similarity('String', 'import')