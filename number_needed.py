'''
Problem: Given two strings, a and b, that may or may not be of the same length,
determine the minimum number of character deletions required to make
a and b anagrams. Any characters can be deleted from either of the strings.

Solution: Use a Hashtable(dictionary) to keep track of the letter and count of
each letter in each string. Then loop through each letter in the alphabet
accumulating the counts of the different frequencies 
'''
def number_needed(a, b):
    #define different dictionaries for two strings
    a_dict = {}
    b_dict = {}
    
    #load the dictionaries with the frequencies of the letters
    for a_letter in a:
        if a_letter not in a_dict.keys():
            a_dict[a_letter] = 1
        else:
            a_dict[a_letter] = a_dict[a_letter] + 1
    
    for b_letter in b:
        if b_letter not in b_dict.keys():
            b_dict[b_letter] = 1
        else:
            b_dict[b_letter] = b_dict[b_letter] + 1
    
    alphabet = "abcdefghijklmnopqrstuvwxyz"
    
    count = 0
    #loop through each letter in the alphabet
    for letter in alphabet:
        
        #if letter not in both strings, skip the letter
        if letter not in a_dict.keys() and letter not in b_dict.keys():
            continue
        #if letter in a and not b, add the frequency of letter in a to count
        elif letter in a_dict.keys() and letter not in b_dict.keys():
            count = count + a_dict[letter]
        #if letter in b and not a, add frequency of letter in b to count
        elif letter in b_dict.keys() and letter not in a_dict.keys():
            count = count + b_dict[letter]
        #letter in both words, add the difference between the frequencies to count
        else:
            count = count + abs(a_dict[letter] - b_dict[letter])
    
    return count
# take in input from stdin
a = raw_input().strip()
b = raw_input().strip()

print number_needed(a, b)