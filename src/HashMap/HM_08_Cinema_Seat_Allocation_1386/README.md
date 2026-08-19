
# 1386. Cinema Seat Allocation

---

# Problem Statement

A cinema has `n` rows, numbered from `1` to `n`.

Each row contains **10 seats**, numbered from `1` to `10`.

Some seats are already reserved. A four-person family can sit together in one of these three seat blocks:

```text
[2, 3, 4, 5]
[4, 5, 6, 7]
[6, 7, 8, 9]
```

A block can be used only if **none of its seats are reserved**.

Return the **maximum number of four-person families** that can be seated.

### Example 1

```text
Input:
n = 3
reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]

Output:
4
```

### Example 2

```text
Input:
n = 2
reservedSeats = [[2,1],[1,8],[2,6]]

Output:
2
```

### Example 3

```text
Input:
n = 4
reservedSeats = [[4,3],[1,4],[4,6],[1,7]]

Output:
4
```

---

# Goal

Find the maximum number of four-person families that can be assigned seats without using any reserved seat.

The main challenge is that:

```text
1 <= n <= 10^9
```

So we cannot iterate through every row.

---

# Intuition

A completely empty row can always accommodate **2 families**:

```text
2 3 4 5  → Family 1

6 7 8 9  → Family 2
```

Therefore, we can initially assume:

```text
Answer = 2 * n
```

The only rows that can reduce this answer are the rows containing reserved seats.

So instead of checking all `n` rows, we store only the rows that actually contain reservations.

For every affected row, we check whether the three possible blocks are available:

```text
Left    → 2 3 4 5

Middle  → 4 5 6 7

Right   → 6 7 8 9
```

---

# Key Idea

There are three possible four-seat blocks:

```text
Left   = {2,3,4,5}

Middle = {4,5,6,7}

Right  = {6,7,8,9}
```

For each row containing reserved seats:

### Case 1: Left and Right are both available

We can place:

```text
2 families
```

So no change is required.

---

### Case 2: At least one block is available

We can place:

```text
1 family
```

Therefore:

```text
answer -= 1
```

---

### Case 3: No block is available

The row cannot accommodate any family.

Therefore:

```text
answer -= 2
```

---

# Thought Process

### Step 1: Store Reserved Seats

Use:

```java
HashMap<Integer, Set<Integer>>
```

The map represents:

```text
row → reserved seats
```

For example:

```text
1 → {2,3,8}
2 → {6}
3 → {1,10}
```

This lets us quickly check whether a particular seat is reserved.

---

### Step 2: Assume Every Row Is Empty

Every empty row can accommodate two families.

Therefore:

```text
ans = n * 2
```

Because `n` can be as large as `10^9`, use `long` for this calculation.

---

### Step 3: Process Only Affected Rows

For every row present in the HashMap, check:

```text
left
middle
right
```

Each check requires only a constant number of `contains()` operations.

---

### Step 4: Adjust the Answer

If:

```text
left && right
```

then the row still supports two families.

Otherwise:

```text
left || middle || right
```

means one family is possible.

If none are possible, the row loses both families.

---

# Code (Functions Only)

```java
int maxNumberOfFamilies(int n, int[][] reservedSeats) {

    HashMap<Integer, Set<Integer>> mp = new HashMap<>();

    for (int[] r : reservedSeats) {
        int row = r[0];
        int seat = r[1];

        mp.computeIfAbsent(row, k -> new HashSet<>()).add(seat);
    }

    long ans = n * 2L;

    for (int row : mp.keySet()) {

        Set<Integer> seats = mp.get(row);

        boolean left = !seats.contains(2) &&
                !seats.contains(3) &&
                !seats.contains(4) &&
                !seats.contains(5);

        boolean middle = !seats.contains(4) &&
                !seats.contains(5) &&
                !seats.contains(6) &&
                !seats.contains(7);

        boolean right = !seats.contains(6) &&
                !seats.contains(7) &&
                !seats.contains(8) &&
                !seats.contains(9);

        if (left && right) {
            continue;
        }
        else if (left || middle || right) {
            ans -= 1;
        }
        else {
            ans -= 2;
        }
    }

    return (int) ans;
}
```

---

# Dry Run (Step-by-Step)

### Input

```text
n = 3

reservedSeats =
[
    [1,2],
    [1,3],
    [1,8],
    [2,6],
    [3,1],
    [3,10]
]
```

---

### Step 1: Initial Answer

There are `3` rows.

Every empty row can hold `2` families.

```text
ans = 3 * 2
    = 6
```

---

### Step 2: Process Row 1

Reserved seats:

```text
{2,3,8}
```

Check left block:

```text
2 3 4 5
```

Seats `2` and `3` are reserved.

```text
left = false
```

Check middle block:

```text
4 5 6 7
```

None are reserved.

```text
middle = true
```

Check right block:

```text
6 7 8 9
```

Seat `8` is reserved.

```text
right = false
```

At least one block is available, so row 1 can hold:

```text
1 family
```

Initially it could hold 2.

Therefore:

```text
ans = 6 - 1
    = 5
```

---

### Step 3: Process Row 2

Reserved seats:

```text
{6}
```

Left block:

```text
2 3 4 5
```

All seats are free.

```text
left = true
```

Middle block:

```text
4 5 6 7
```

Seat `6` is reserved.

```text
middle = false
```

Right block:

```text
6 7 8 9
```

Seat `6` is reserved.

```text
right = false
```

Only one family can be placed.

Therefore:

```text
ans = 5 - 1
    = 4
```

---

### Step 4: Process Row 3

Reserved seats:

```text
{1,10}
```

Neither seat affects the usable family blocks.

Therefore:

```text
left = true
right = true
```

Two families can be placed.

No subtraction is required.

```text
ans = 4
```

---

### Final Answer

```text
4
```

---

# Logic Flow

```text
Start
   |
   v
Store reserved seats
row -> Set of seats
   |
   v
Assume every row has 2 families
ans = 2 * n
   |
   v
Process every reserved row
   |
   v
Check Left Block
Check Middle Block
Check Right Block
   |
   v
Left && Right ?
   |
   +---- Yes ----> 2 families possible
   |                No change
   |
   +---- No -----> Is any block available?
                       |
                       +---- Yes ----> ans -= 1
                       |
                       +---- No -----> ans -= 2
   |
   v
Return ans
```

---

# Complexity

Let:

```text
R = number of reserved seats
```

### Time Complexity

Building the HashMap:

```text
O(R)
```

Processing affected rows:

```text
O(R)
```

Each seat lookup is expected `O(1)` using `HashSet`.

Therefore:

```text
Overall = O(R)
```

---

### Space Complexity

The HashMap stores the reserved seats:

```text
O(R)
```

Therefore:

```text
Overall = O(R)
```

---

# Key Takeaways

- An empty row can accommodate **2 families**.
- We should start with:
  ```text
  2 * n
  ```
- `n` can be as large as `10^9`, so iterating over every row is impossible.
- Only rows containing reserved seats need to be examined.
- The three relevant blocks are:
  ```text
  2-5
  4-7
  6-9
  ```
- If both left and right blocks are free, two families can be seated.
- If at least one valid block exists, one family can be seated.
- If no valid block exists, zero families can be seated.
- `HashMap + HashSet` provides efficient storage and lookup of reserved seats.

---

# Most Important Insight

The biggest optimization is to **start with the maximum possible answer** instead of constructing the seating arrangement row by row.

```text
Every empty row → 2 families
```

So:

```text
answer = 2 * n
```

Then only the rows containing reservations need correction.

This changes the problem from potentially processing:

```text
10^9 rows
```

to processing only:

```text
number of reserved seats
```

which is at most `10^4`.

---

# Summary

The solution uses a greedy observation: every completely empty row can accommodate two families. Therefore, initialize the answer as `2 * n` and process only rows containing reserved seats.

For each affected row, check the left, middle, and right four-seat blocks. If both left and right are available, the row still supports two families. If at least one block is available, it supports one family. Otherwise, it supports none.

The HashMap stores only affected rows, making the algorithm efficient even when `n` is extremely large.

### Final Complexity

```text
Time  : O(R)
Space : O(R)

where R = number of reserved seats.
```
