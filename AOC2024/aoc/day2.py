def is_safe(report):
    increasing = all(1 <= report[i + 1] - report[i] <= 3 for i in range(len(report) - 1))
    decreasing = all(1 <= report[i] - report[i + 1] <= 3 for i in range(len(report) - 1))
    return increasing or decreasing


def is_safe_with_dampener(report):
    if is_safe(report):
        return True

    for i in range(len(report)):
        if is_safe([report[j] for j in range(len(report)) if j != i]):
            return True

    return False


def count_safe_reports(data):
    safe_count = 0
    for report in data:
        if is_safe(report):
            safe_count += 1
    return safe_count


def count_safe_reports_with_dampener(data):
    safe_count = 0
    for report in data:
        if is_safe_with_dampener(report):
            safe_count += 1
    return safe_count


def load_data_from_file(filename):
    with open(filename, 'r') as file:
        data = [
            list(map(int, line.strip().split()))
            for line in file if line.strip()
        ]
    return data


# if __name__ == "__main__":
data = load_data_from_file("resources/day2input.txt")
print("Day2 p1:", count_safe_reports(data))
print("Day2 p2:", count_safe_reports_with_dampener(data))
