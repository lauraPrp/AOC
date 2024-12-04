from collections import Counter

list1 = []
list2 = []

with open("resources/day1input.txt", 'r') as file:
    for line in file:
        numbers = line.split()
        numbers = [int(num) for num in numbers]
        list1.append(numbers[0])
        list2.append(numbers[1])

list1 = sorted(list1)
list2 = sorted(list2)

total = 0
total2 = 0
for i in range(len(list1)):
    difference = abs(list1[i] - list2[i])
    total += difference


def get_frequencies(lst, excluded):
    return Counter(item for item in lst if item not in excluded)


excluded_items = set()

combined_frequencies = get_frequencies(list1 + list2, excluded_items)

excluded_items.update(key for key, count in combined_frequencies.items() if count == 1)

freq_list1 = get_frequencies(list1, excluded_items)
freq_list2 = get_frequencies(list2, excluded_items)

total2 = sum(freq_list1[key] * freq_list2[key] * key for key in freq_list1 if key in freq_list2)

print('part1:', total)
print('part2:', total2)