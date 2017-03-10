from gensim import models
from gensim.models import word2vec

input_file='es.txt'

sentences = word2vec.LineSentence(input_file)
model = models.Word2Vec(sentences, sg=1, size=50, window=5, min_count=5, workers=2, alpha=0.025, iter=5)

model.save_word2vec_format('model.txt', binary=False)


# model.similarity('String', 'import')

model.wv.most_similar(positive=['GatewayAllocator', 'UnitTestCase'], negative=['NoopGatewayAllocator'])
model.wv.most_similar(positive=['%CLOSE_PAREN%', '%OPEN_SQUARE%'], negative=['%OPEN_PAREN%'])
model.wv.most_similar(positive=['%CLOSE_PAREN%', '%OPEN_BRACE%'], negative=['%OPEN_PAREN%'])
model.wv.most_similar(positive=['interface', 'RestClient'], negative=['class'])


