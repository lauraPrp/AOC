import sys


def solve_p1(input_data):
    position = 50
    count = 0
    dial_size = 100

    for instruction in input_data:
        if not instruction.strip():
            continue
        direction = instruction[0].upper()
        clicks = int(instruction[1:].strip())

        # Simulate the FULL rotation at once
        if direction == 'R':
            position = (position + clicks) % dial_size
        else:  # 'L'
            position = (position - clicks) % dial_size

        # Count ONLY if we END on 0 after this rotation
        if position == 0:
            count += 1

    return count

def solve_p2(data):
    position = 50
    count = 0
    dial_size = 100
    for instruction in data:
        direction = instruction[0]
        clicks = int(instruction[1:])
        for _ in range(clicks):
            if direction == 'R':
                position = (position + 1) % dial_size
            else:
                position = (position - 1) % dial_size
            if position == 0:
                count += 1
    return count

if __name__ == "__main__":
    try:
        with open('d1.txt', 'r') as f:
            data = [line.strip() for line in f.readlines()]
    except FileNotFoundError:
        data = [line.strip() for line in sys.stdin.readlines()]

    result1 = solve_p1(data)
    result2 = solve_p2(data)
    print(f"Number of times dial points at 0: {result1}")
    print(f"Number of times dial pass over 0: {result2}")