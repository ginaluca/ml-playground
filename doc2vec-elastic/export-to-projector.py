import re

def camel_case_split(identifier):
    matches = re.finditer('.+?(?:(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])|$)', identifier)
    return [m.group(0) for m in matches]

classes = map(lambda x: x[:-1], open('classes.txt').readlines())

with open('model.txt') as sourcef, open('values.txt', 'w') as valuesf, open('labels.txt', 'w') as labelsf:
    sourcef.readline()
    labelsf.write('Name\tType\n')
    for line in sourcef.readlines():
        line = line[:-1]
        firstspace = line.index(' ')
        label = line[:firstspace]
        values = line[firstspace + 1:].replace(' ', '\t')
        valuesf.write(values + '\n')
        labelsf.write(label + '\t')
        camel_components = camel_case_split(label)
        if len(camel_components) > 1:
            labelsf.write(camel_components[-1])
        else:
            labelsf.write('Other')
        labelsf.write('\n')

