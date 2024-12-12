def load_input_from_file(file_path):
    with open(file_path, 'r') as file:
        input_data = file.read()
    return input_data

def parse_input(input_data):
    rules_section, updates_section = input_data.strip().split("\n\n")
    rules = parse_rules(rules_section)
    updates = parse_updates(updates_section)
    return rules, updates

def parse_rules(rules_section):
    rules = []
    for line in rules_section.strip().split("\n"):
        x, y = map(int, line.split("|"))
        rules.append((x, y))
    return rules

def parse_updates(updates_section):
    updates = []
    for line in updates_section.strip().split("\n"):
        update = list(map(int, line.split(",")))
        updates.append(update)
    return updates

def is_update_in_order(update, rules):
    position_map = {page: idx for idx, page in enumerate(update)}
    for x, y in rules:
        if x in position_map and y in position_map:
            if position_map[x] > position_map[y]:
                return False
    return True

def find_middle_page(update):
    middle_index = len(update) // 2
    return update[middle_index]

def main(file_path):
    input_data = load_input_from_file(file_path)
    rules, updates = parse_input(input_data)
    middle_sum = 0

    for update in updates:
        if is_update_in_order(update, rules):
            middle_page = find_middle_page(update)
            middle_sum += middle_page

    return middle_sum

file_path = "resources/day5input.txt"
output = main(file_path)
print("Day5 p1:", output)
