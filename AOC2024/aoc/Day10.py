from collections import deque


def find_trailhead_scores(topographic_map):
    rows = len(topographic_map)
    cols = len(topographic_map[0])

    def is_valid(x, y, current_height):
        return 0 <= x < rows and 0 <= y < cols and topographic_map[x][y] == current_height + 1

    #  breadth-first search to find all reachable nines
    def bfs(x, y):
        queue = deque([(x, y, 0)])  # (x, y, current height)
        visited = set()
        visited.add((x, y))
        reachable_nines = set()

        while queue:
            cx, cy, ch = queue.popleft()
            if topographic_map[cx][cy] == 9:
                reachable_nines.add((cx, cy))
            for dx, dy in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                nx, ny = cx + dx, cy + dy
                if is_valid(nx, ny, ch) and (nx, ny) not in visited:
                    visited.add((nx, ny))
                    queue.append((nx, ny, ch + 1))

        return len(reachable_nines)

    total_score = 0

    # Iterate the map to find trailheads
    for i in range(rows):
        for j in range(cols):
            if topographic_map[i][j] == 0:  # Found a trailhead
                total_score += bfs(i, j)

    return total_score


def load_topographic_map(file_path):
    with open(file_path, 'r') as file:
        return [list(map(int, line.strip())) for line in file.readlines()]


test_input = [
    [8, 9, 0, 1, 0, 1, 2, 3],
    [7, 8, 1, 2, 1, 8, 7, 4],
    [8, 7, 4, 3, 0, 9, 6, 5],
    [9, 6, 5, 4, 9, 8, 7, 4],
    [4, 5, 6, 7, 8, 9, 0, 3],
    [3, 2, 0, 1, 9, 0, 1, 2],
    [0, 1, 3, 2, 9, 8, 0, 1],
    [1, 0, 4, 5, 6, 7, 3, 2],
]

print("Test:", find_trailhead_scores(test_input))

file_path = "resources/day10input.txt"
topographic_map_from_file = load_topographic_map(file_path)
print("P1:", find_trailhead_scores(topographic_map_from_file))
