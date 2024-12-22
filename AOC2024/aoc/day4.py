def count_x_mas(grid):
    rows = len(grid)
    cols = len(grid[0])

    # X-MAS pattern is defined by the relative coordinates:
    # Two "MAS" in the shape of an "X"
    # Possible directions:
    # M.S
    # .A.
    # M.S
    x_mas_offsets = [
        [(0, 0), (1, 1), (2, 0)],  # Left diagonal (M.A.S)
        [(0, 2), (1, 1), (2, 2)]   # Right diagonal (M.A.S)
    ]

    # Variations of "MAS" (can be reversed)
    valid_mas = {"MAS", "SAM"}

    def is_x_mas(x, y):
        # Check both diagonals forming the X
        for offsets in x_mas_offsets:
            try:
                diagonal = "".join(
                    grid[x + dx][y + dy] for dx, dy in offsets
                )
                if diagonal not in valid_mas:
                    return False
            except IndexError:
                return False
        return True

    count = 0
    # Iterate through all grid positions
    for x in range(rows - 2):  # Ensure there is space for the pattern
        for y in range(cols - 2):  # Ensure space in both directions
            if is_x_mas(x, y):
                count += 1

    return count


def load_grid_from_file(filename):
    with open(filename, 'r') as file:
        grid = [line.strip() for line in file if line.strip()]
    return grid


# Example usage
if __name__ == "__main__":
    input_file = "resources/day4input.txt"  # Update with your input file path
    grid = load_grid_from_file(input_file)
    result = count_x_mas(grid)
    print("Day4 p1:", result)
