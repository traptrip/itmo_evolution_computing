"""
Knut-Moris-Pratt algorithm
"""
from time import time
from pathlib import Path
import matplotlib.pyplot as plt
from numba import njit


def get_substr_idx_1(substring: str, string: str) -> int:
    s = substring + "#" + string
    p = [0] * len(s)
    for i in range(1, len(s)):
        k = p[i - 1]
        while k > 0 and s[i] != s[k]:
            k = p[k - 1]
        if s[i] == s[k]:
            k += 1
        p[i] = k
        if k == len(substring):
            return i - len(substring) * 2
    return -1


def get_substr_idx_2(substring: str, string: str) -> int:
    substring_size = len(substring)
    string_size = len(string)
    if string_size < substring_size:
        return -1
    s = substring + "#" + string
    p = [0] * string_size
    for i, char in enumerate(s):
        k = p[i - 1]
        while k > 0 and char != s[k]:
            k = p[k - 1]
        if char == s[k]:
            k += 1
        p[i] = k
        if k == substring_size:
            return i - substring_size * 2
    return -1


@njit
def get_substr_idx_3(substring: str, string: str) -> int:
    substring_size = len(substring)
    string_size = len(string)
    if string_size < substring_size:
        return -1
    s = substring + "#" + string
    p = [0] * string_size
    for i, char in enumerate(s):
        k = p[i - 1]
        while k > 0 and char != s[k]:
            k = p[k - 1]
        if char == s[k]:
            k += 1
        p[i] = k
        if k == substring_size:
            return i - substring_size * 2
    return -1


_ = get_substr_idx_3("start", "warmupstart")


class Test:
    def test(self, tests_filepath: Path = Path(__file__).parent / "tests.txt"):
        # read tests file
        with open(tests_filepath, "r") as f:
            lines = f.readlines()[1:]

        time1 = []
        time2 = []
        time3 = []
        for line in lines:
            substring, string = line.split("; ")

            t_mean = 0
            for i in range(10):
                t = time()
                _ = get_substr_idx_1(substring, string)
                t_mean += (time() - t) / 10
            time1.append(t_mean)

            t_mean = 0
            for i in range(10):
                t = time()
                _ = get_substr_idx_2(substring, string)
                t_mean += (time() - t) / 10
            time2.append(t_mean)

            t_mean = 0
            for i in range(10):
                t = time()
                _ = get_substr_idx_3(substring, string)
                t_mean += (time() - t) / 10
            time3.append(t_mean)

        plt.figure(figsize=(12, 8))
        plt.title("Time comparison")
        plt.plot(range(len(lines)), time1, label="naive KMP")
        plt.plot(range(len(lines)), time2, label="KMP with some improvments")
        plt.plot(range(len(lines)), time3, label="KMP with some improvments + numba")
        plt.legend()
        plt.ylabel("Time (sec)")
        plt.xlabel("Test number")
        plt.savefig("time_comparison.png")


if __name__ == "__main__":
    Test().test()
