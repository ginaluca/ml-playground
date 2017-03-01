import os
import re

def tokenize_class(f):
    tokens = set()
    for line in f:
        tokens |= set(re.split('\W+', line))
    return tokens

rootDir = '/Users/gianluca/sw/elasticsearch'
tokens = set()
for dirName, subdirList, fileList in os.walk(rootDir):
    for fname in fileList:
        if fname.endswith('.java'):
            print 'Processing %s (set: %d)' % (fname, len(tokens))
            with open('%s/%s' % (dirName, fname)) as f:
                tokens |= tokenize_class(f)
tokens.remove('')
with open('vocabulary', 'w') as v:
    for t in tokens:
        v.write(t + '\n')

