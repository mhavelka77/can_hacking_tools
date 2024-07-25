#!/usr/bin/python3

import re
import sys


# read the input
inputString = ""
for line in sys.stdin:
    inputString += line

# get the variable values
values = re.findall("#\(([a-zA-Z0-9])\-([a-zA-Z0-9])\)", inputString)
# get the string without the variable values
inputString = re.split("#\([a-zA-Z0-9]\-[a-zA-Z0-9]\)", inputString)

replacementsNumber = len(values)


def printCombination(valueIndex, replacements):

    # for the first variable, loop over the values 
    for i in range(int(values[valueIndex][0], 16), int(values[valueIndex][1], 16) + 1):
        # if there is no more variables, end the loop and start printing the values 
        if(valueIndex == (replacementsNumber - 1)):
            printable = ""
            for n in range(replacementsNumber - 1):
                printable += inputString[n] 
                printable += format(replacements[n], 'x')
            printable += inputString[replacementsNumber - 1]
            printable += format(i, 'x') 
            printable += inputString[replacementsNumber]
            printable = printable.replace('\n', '') 
            print(printable)
        # else call the function again, one level deeper 
        else:
            replacementsUpdated = replacements.copy()
            replacementsUpdated.append(i)
            printCombination(valueIndex + 1, replacementsUpdated)

printCombination(0, [])

