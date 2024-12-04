# def is_safe(report: list, ignore_index: int) -> bool:
#     ascending = False
#     descending = False
#     previous_level = None
#
#     for current_index, current_level in enumerate(report):
#         if current_index == ignore_index:
#             continue
#
#         if previous_level is None:
#             previous_level = current_level
#             continue
#
#         if previous_level < current_level:
#             ascending = True
#         elif previous_level > current_level:
#             descending = True
#
#         if (previous_level == current_level) or abs(previous_level - current_level) > 3 or (ascending and descending):
#             return False
#
#         previous_level = current_level
#
#     return True
with open("resources/day2input.txt") as f:
    content = f.read().strip().split('\n')

ans = 0
for report in content:
    values = list(map(int, report.split()))
    safepos = {1, 2, 3}
    safeneg = {-1, -2, -3}
    for i in range(1, len(values)):
        safepos.add(values[i] - values[i - 1])
        safeneg.add(values[i] - values[i - 1])

    if len(safepos) == 3 or len(safeneg) == 3:
        ans += 1

print("Day2 p1:",ans)


   