def check_word(word: str) -> bool:
    freq_dict = {}
    for char in word:
        freq_dict[char] = freq_dict.get(char,0) + 1

    if len(freq_dict) > 2:
        return False
    
    if len(freq_dict) == 1:
        return True
    else:
        if len(word) % 2 == 0:
            #even length number so cannot have an integer in the middle
            return False
        else:
            #odd length word
            if len(list(filter(lambda x: x == 1, freq_dict.values()))) == 1:
                middle_idx = int(len(word) / 2)
                if word[middle_idx] != word[middle_idx + 1] and word != word[middle_idx - 1]:
                    return True
                else:
                    return False
            else:
                return False
    

def substring_count(full_string):
    count = 0
    count += len(full_string)

    special_strings = [char for char in full_string]

    for k in range(2, len(full_string) + 1):
        kmers = [full_string[i:i+k] for i in range(len(full_string) - k + 1)]
        for word in kmers:
            if check_word(word):
                special_strings.append(word)
                count += 1
    return count

if __name__ == "__main__":
    print(substring_count("abcbaba"))
