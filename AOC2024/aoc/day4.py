def count_xmas(grid, word="XMAS"):
    rows = len(grid)
    cols = len(grid[0])
    word_len = len(word)
    directions = [
        (0, 1),  # Horizontal right
        (0, -1),  # Horizontal left
        (1, 0),  # Vertical down
        (-1, 0),  # Vertical up
        (1, 1),  # Diagonal down-right
        (1, -1),  # Diagonal down-left
        (-1, 1),  # Diagonal up-right
        (-1, -1)  # Diagonal up-left
    ]

    def is_valid(x, y, dx, dy):
        for i in range(word_len):
            nx, ny = x + i * dx, y + i * dy
            if not (0 <= nx < rows and 0 <= ny < cols) or grid[nx][ny] != word[i]:
                return False
        return True

    count = 0
    for x in range(rows):
        for y in range(cols):
            for dx, dy in directions:
                if is_valid(x, y, dx, dy):
                    count += 1
    return count


def load_grid_from_file(filename):
    with open(filename, 'r') as file:
        grid = [line.strip() for line in file if line.strip()]
    return grid


# if __name__ == "__main__":
input_file = "resources/day4input.txt"
grid = load_grid_from_file(input_file)
result = count_xmas(grid)
print("Day4 p1:", result)
