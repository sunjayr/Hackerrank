def max_subset_sum(arr):
    max_val = [0] * len(arr)
    max_val[0] = arr[0] if arr[0] > 0 else 0
    max_val[1] = arr[1] if arr[1] > 0 else 0
    max_val[2] = max([
        0,
        arr[2],
        arr[2] + max_val[0],
        max_val[0]
    ])
    
    for i in range(3, len(arr)):
        max_val[i] = max([
            0,
            arr[i],
            max_val[i-2],
            max_val[i-3],
            arr[i] + max_val[i-2],
            arr[i] + max_val[i-3]
        ])
    
    return max(max_val[-1], max_val[-2])

if __name__ == '__main__':
    input_string = "1 -5 -10 -20 7 2"
    input_arr = list(map(int, input_string.split()))
    print(max_subset_sum(input_arr))

