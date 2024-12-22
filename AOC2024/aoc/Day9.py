def load_input_from_file(file_path):
    """
    Load the disk map input from a file.
    """
    with open(file_path, 'r') as file:
        return file.read().strip()

def parse_disk_map(disk_map):
    """
    Parse the disk map into a list of (file/free_space, length).
    Handles cases where the string length is odd.
    """
    parsed = []
    for i in range(0, len(disk_map) - 1, 2):
        length_file = int(disk_map[i])
        length_space = int(disk_map[i + 1])
        parsed.append((length_file, length_space))
    # If the string length is odd, add the last file length with zero free space
    if len(disk_map) % 2 != 0:
        parsed.append((int(disk_map[-1]), 0))
    return parsed

def expand_disk(parsed_map):
    """
    Expand the parsed disk map into a list representing the disk.
    Each block is either a file ID or free space ('.').
    """
    disk = []
    file_id = 0
    for file_length, space_length in parsed_map:
        disk.extend([file_id] * file_length)
        disk.extend(['.'] * space_length)
        if file_length > 0:
            file_id += 1
    return disk

def compact_disk(disk):
    """
    Compact the disk by moving file blocks to the leftmost free space.
    """
    left = 0  # Track the next available position
    for right in range(len(disk)):
        if disk[right] != '.':  # If it's a file block
            disk[left] = disk[right]
            left += 1
    # Fill the remaining part of the disk with '.'
    for i in range(left, len(disk)):
        disk[i] = '.'
    return disk

def calculate_checksum(disk):
    """
    Calculate the filesystem checksum.
    Sum of position * file ID for non-free-space blocks.
    """
    checksum = 0
    for position, block in enumerate(disk):
        if block != '.':  # Skip free space
            checksum += position * block
    return checksum

def debug_parsed_map(parsed_map):
    """
    Print debug information about the parsed map.
    """
    print("Parsed Map:")
    for idx, (file_len, space_len) in enumerate(parsed_map):
        print(f"File ID {idx}: Length {file_len}, Free Space {space_len}")
    print()

def debug_disk(disk, message="Disk State"):
    """
    Print debug information about the current disk state.
    """
    print(f"{message}:")
    print("".join(str(block) if block != '.' else '.' for block in disk))
    print()

def main(file_path):
    """
    Main function to compute the filesystem checksum.
    """
    # Load disk map from file
    disk_map = load_input_from_file(file_path)

    # Step 1: Parse the input disk map
    parsed_map = parse_disk_map(disk_map)
    debug_parsed_map(parsed_map)

    # Step 2: Expand the parsed map into a full disk layout
    disk = expand_disk(parsed_map)
    debug_disk(disk, "Initial Disk Layout")

    # Step 3: Compact the disk
    compacted_disk = compact_disk(disk)
    debug_disk(compacted_disk, "Compacted Disk Layout")

    # Step 4: Calculate the checksum
    checksum = calculate_checksum(compacted_disk)
    print(f"Checksum: {checksum}")
    return checksum

# Example usage:
if __name__ == "__main__":
    input_file = "resources/day9input.txt"  # Replace with your input file path
    result = main(input_file)
