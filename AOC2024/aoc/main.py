import importlib
import os


def run_all_days():
    start_day = 1
    end_day = 3

    for day in range(start_day, end_day + 1):
        day_module = f"day{day}"
        input_file = f"resources/day{day}input.txt"

        if not os.path.exists(f"{day_module}.py"):
            break

        if not os.path.exists(input_file):
            print(f"Day {day}: Input file not found!")
            continue

        try:
            day_script = importlib.import_module(day_module)
            # print(f"Day {day}:")

            # if hasattr(day_script, "main"):
            #     day_script.main(input_file)
            # else:
            #     print(f"Day {day}: No main() function found. Skipping...")

        except Exception as e:
            print(f"Day {day}: Error running script - {e}")

    # if __name__ == "__main__":
    #     run_all_days()


run_all_days()
