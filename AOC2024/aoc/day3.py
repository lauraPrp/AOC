import re


def sum_valid_multiplications_with_conditionals(file_path):
    with open(file_path, 'r') as file:
        input_file = file.read()

    mul_pattern = r"mul\((\d{1,3}),(\d{1,3})\)"
    do_pattern = r"do\(\)"
    dont_pattern = r"don't\(\)"

    total = 0
    matches = re.findall(mul_pattern, input_file)
    for match in matches:
        x, y = map(int, match)
        total += x * y
    print("PArt1: " + str(total))
    mul_enabled = True
    total = 0

    position = 0
    while position < len(input_file):
        dont_match = re.match(dont_pattern, input_file[position:])
        if dont_match:
            mul_enabled = False
            position += dont_match.end()
            continue


        do_match = re.match(do_pattern, input_file[position:])
        if do_match:
            mul_enabled = True
            position += do_match.end()
            continue

        mul_match = re.match(mul_pattern, input_file[position:])
        if mul_match:
            if mul_enabled:
                x, y = map(int, mul_match.groups())
                total += x * y
            position += mul_match.end()
            continue

        # If no valid instruction is found, move next
        position += 1


    return total

result = sum_valid_multiplications_with_conditionals("resources/day3input.txt")
print(":part2:", result)
