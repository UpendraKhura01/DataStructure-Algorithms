# 📊 Marks from Ranks

## 📌 Problem Statement

You are given several non-overlapping intervals of valid marks.

The intervals are represented using two arrays:

    l[i] = starting mark of the i-th interval
    r[i] = ending mark of the i-th interval

Every integer between `l[i]` and `r[i]` is a valid mark.

The intervals are:

- Sorted in increasing order
- Non-overlapping
- Inclusive of both endpoints

The rank of a mark is its position among all valid marks when they are arranged in increasing order.

The smallest valid mark has:

    rank = 1

The next valid mark has:

    rank = 2

and so on.

Given an array `rank[]`, find the mark corresponding to every requested rank.

### Example

    Input:
    l    = [1, 6, 14]
    r    = [3, 9, 15]
    rank = [2, 5, 8]

The valid marks are:

    [1, 2, 3, 6, 7, 8, 9, 14, 15]

Their ranks are:

    Mark:  1  2  3  6  7  8  9  14 15
    Rank:  1  2  3  4  5  6  7   8  9

Therefore:

    rank 2 -> mark 2
    rank 5 -> mark 7
    rank 8 -> mark 14

Output:

    [2, 7, 14]

---

## 🎯 Goal

For every requested rank, determine the corresponding valid mark efficiently.

Instead of generating every valid mark, we:

    1. Convert interval boundaries into useful rank information.
    2. Store those boundaries in a sorted list.
    3. Use binary search to find the interval containing the requested rank.
    4. Calculate the exact mark using an offset.

---

## 💡 Intuition

Consider:

    l = [1, 6, 14]
    r = [3, 9, 15]

The intervals are:

    [1, 3]
    [6, 9]
    [14, 15]

The valid marks are:

    1, 2, 3
    6, 7, 8, 9
    14, 15

Instead of storing every mark, we only store the beginning and ending mark of each interval together with their ranks.

For the first interval:

    [1, 3]

The marks are:

    1 -> rank 1
    2 -> rank 2
    3 -> rank 3

So we store:

    (1, 1)
    (3, 3)

For the second interval:

    [6, 9]

The marks are:

    6 -> rank 4
    7 -> rank 5
    8 -> rank 6
    9 -> rank 7

So we store:

    (6, 4)
    (9, 7)

For the third interval:

    [14, 15]

The marks are:

    14 -> rank 8
    15 -> rank 9

So we store:

    (14, 8)
    (15, 9)

The important observation is:

    Within an interval,
    rank increases by exactly 1
    whenever mark increases by 1.

Therefore, once we know a starting point `(baseMark, baseRank)`, we can calculate:

    mark = baseMark + (targetRank - baseRank)

---

## 🔥 Key Idea

For every interval `[left, right]`, calculate its starting and ending ranks.

The number of marks inside the interval is:

    right - left + 1

If the current interval starts at:

    cur_rank

then its ending rank is:

    cur_rank + (right - left)

because the starting mark already occupies one rank.

For example:

    interval = [6, 9]
    cur_rank = 4

Number of jumps:

    9 - 6 = 3

Therefore:

    ending rank = 4 + 3
                = 7

After the interval, the next rank becomes:

    8

This is exactly what the code calculates:

    int diff = right - left;

    list.add(new ranks(left, cur_rank));

    int new_rank = cur_rank + diff;

    list.add(new ranks(right, new_rank));

    cur_rank = new_rank + 1;

---

## 🧠 Thought Process

### Step 1: Assign Ranks to Interval Boundaries

Start with:

    cur_rank = 1

For each interval:

    [left, right]

store:

    (left, cur_rank)

Then calculate the rank of `right`:

    new_rank = cur_rank + (right - left)

Store:

    (right, new_rank)

Then move to the next rank:

    cur_rank = new_rank + 1

---

### Step 2: Build the Boundary List

For:

    l = [1, 6, 14]
    r = [3, 9, 15]

The list becomes:

    (1,1)
    (3,3)
    (6,4)
    (9,7)
    (14,8)
    (15,9)

Each record contains:

    (mark, rank)

Notice that the ranks are sorted:

    1, 3, 4, 7, 8, 9

This is important because it allows binary search.

---

### Step 3: Search for a Requested Rank

Suppose:

    target rank = 5

We need to find where rank `5` belongs.

The stored boundary ranks are:

    1, 3, 4, 7, 8, 9

The largest boundary rank that is:

    <= 5

is:

    rank = 4

That boundary is:

    (6, 4)

This means:

    mark 6 corresponds to rank 4

To reach rank 5:

    rank difference = 5 - 4
                   = 1

Therefore:

    mark = 6 + 1
         = 7

So:

    rank 5 -> mark 7

---

### Step 4: Binary Search Finds the Best Boundary

The `find()` method searches for:

    largest boundary rank <= target rank

It keeps this index in:

    best

Whenever:

    cur.rank() <= rank

we know this boundary could be the starting point for the answer.

So:

    best = mid

and continue searching to the right:

    left = mid + 1

If:

    cur.rank() > rank

we move left:

    right = mid - 1

At the end:

    best

contains the closest valid boundary before or at the requested rank.

---

### Step 5: Calculate the Exact Mark

Once we have:

    base mark
    base rank

the answer is:

    base mark + (target rank - base rank)

This works because marks and ranks increase together by `1` inside an interval.

---

## 💻 Code

```java
ArrayList<ranks> list;
int n;

public ArrayList<Integer> getMarks(int[] l, int[] r, int[] rank) {
    n = l.length;

    list = new ArrayList<>();

    int cur_rank = 1;

    for (int i = 0; i < n; i++) {

        int left = l[i];
        int right = r[i];

        int diff = right - left;

        list.add(new ranks(left, cur_rank));

        int new_rank = cur_rank + diff;

        list.add(new ranks(right, new_rank));

        cur_rank = new_rank + 1;
    }

    ArrayList<Integer> ans = new ArrayList<>();

    for (int i = 0; i < rank.length; i++) {
        int num = find(rank[i]);
        ans.add(num);
    }

    return ans;
}

private int find(int rank) {
    int left = 0;
    int right = 2 * n - 1;
    int best = -1;

    while (left <= right) {

        int mid = left + (right - left) / 2;

        ranks cur = list.get(mid);

        if (cur.rank() <= rank) {
            left = mid + 1;
            best = mid;
        }
        else {
            right = mid - 1;
        }
    }

    int base = list.get(best).num();

    return base + (rank - list.get(best).rank());
}

private record ranks(int num, int rank) {}
```
🧪 Dry Run
Input
l    = [1, 6, 14]
r    = [3, 9, 15]
rank = [2, 5, 8]

The intervals are:

[1, 3]
[6, 9]
[14, 15]
Step 1: Process Interval [1, 3]

Initially:

cur_rank = 1

We have:

left = 1
right = 3

Calculate:

diff = right - left
     = 3 - 1
     = 2

Store the starting boundary:

(1, 1)

Calculate ending rank:

new_rank = cur_rank + diff
         = 1 + 2
         = 3

Store:

(3, 3)

Move to the next rank:

cur_rank = new_rank + 1
         = 4

Current list:

[(1,1), (3,3)]
Step 2: Process Interval [6, 9]

Current rank:

cur_rank = 4

We have:

left = 6
right = 9

Calculate:

diff = 9 - 6
     = 3

Store starting boundary:

(6, 4)

Calculate ending rank:

new_rank = 4 + 3
         = 7

Store:

(9, 7)

Move to next rank:

cur_rank = 7 + 1
         = 8

Current list:

[(1,1), (3,3), (6,4), (9,7)]
Step 3: Process Interval [14, 15]

Current rank:

cur_rank = 8

We have:

left = 14
right = 15

Calculate:

diff = 15 - 14
     = 1

Store:

(14, 8)

Calculate:

new_rank = 8 + 1
         = 9

Store:

(15, 9)

Move to next rank:

cur_rank = 9 + 1
         = 10

Final boundary list:

(1,1)
(3,3)
(6,4)
(9,7)
(14,8)
(15,9)
Step 4: Query Rank 2

We need:

rank = 2

Boundary ranks:

1, 3, 4, 7, 8, 9

We need the largest rank <= 2.

That is:

rank = 1

Corresponding boundary:

(1,1)

Therefore:

base mark = 1
base rank = 1

Calculate:

mark = base + (targetRank - baseRank)

mark = 1 + (2 - 1)
     = 2

Therefore:

rank 2 -> mark 2
Step 5: Query Rank 5

We need:

rank = 5

Boundary ranks:

1, 3, 4, 7, 8, 9

The largest boundary rank <= 5 is:

4

Corresponding boundary:

(6,4)

Therefore:

base mark = 6
base rank = 4

Calculate:

mark = 6 + (5 - 4)
     = 7

Therefore:

rank 5 -> mark 7
Step 6: Query Rank 8

We need:

rank = 8

Boundary ranks:

1, 3, 4, 7, 8, 9

The largest boundary rank <= 8 is:

8

Corresponding boundary:

(14,8)

Therefore:

base mark = 14
base rank = 8

Calculate:

mark = 14 + (8 - 8)
     = 14

Therefore:

rank 8 -> mark 14
Final Answer

The queries were:

rank = [2, 5, 8]

We found:

rank 2 -> mark 2
rank 5 -> mark 7
rank 8 -> mark 14

Therefore:

Output = [2, 7, 14]
🔁 Logic Flow
Start
  |
  v
Read intervals
  |
  v
cur_rank = 1
  |
  v
Process each [left, right]
  |
  v
Store:
    (left, cur_rank)
  |
  v
Calculate:
    new_rank = cur_rank + right - left
  |
  v
Store:
    (right, new_rank)
  |
  v
Update:
    cur_rank = new_rank + 1
  |
  v
Boundary list created
  |
  v
For every requested rank
  |
  v
Binary search:
Find largest boundary rank <= target
  |
  v
Get base mark and base rank
  |
  v
Calculate:
    mark = baseMark + targetRank - baseRank
  |
  v
Add mark to answer
  |
  v
Return answer
📊 Complexity

Let:

N = number of intervals
Q = number of requested ranks
Building the Boundary List

For every interval, two records are inserted.

Therefore:

Time = O(N)

The list contains:

2N

elements.

Processing Queries

For every requested rank, binary search is performed over 2N boundaries.

Binary search takes:

O(log N)

Therefore, for Q queries:

O(Q log N)
Total Time Complexity
O(N + Q log N)
Space Complexity

The boundary list stores two records per interval:

2N records

Therefore:

O(N)

The answer list requires:

O(Q)

So total auxiliary/result space is:

O(N + Q)
🎯 Key Takeaways
Do not generate every valid mark.
Store only important interval boundaries.

Every boundary is represented as:

(mark, rank)

For an interval [left, right], if its first mark has rank cur_rank, then:

endingRank = cur_rank + (right - left)

After processing an interval:

cur_rank = endingRank + 1
The boundary ranks remain sorted because the intervals are sorted and non-overlapping.
This allows binary search.
For a requested rank, find the largest stored rank that is <= the target.

Then calculate the answer using:

mark = baseMark + (targetRank - baseRank)
No physical expansion of the intervals is required.
🔥 Most Important Insight

The most important idea is that we only need interval boundaries, not every individual mark.

Suppose we know:

base mark  = 6
base rank  = 4

Inside the same interval:

rank 4 -> mark 6
rank 5 -> mark 7
rank 6 -> mark 8
rank 7 -> mark 9

Notice the relationship:

mark - baseMark = rank - baseRank

Therefore:

mark = baseMark + (rank - baseRank)

The binary search simply finds the correct interval by locating the largest boundary rank that is not greater than the requested rank.

So the complete strategy is:

Interval boundaries
      ↓
Assign boundary ranks
      ↓
Binary search nearest boundary
      ↓
Calculate offset
      ↓
Return mark

This is why the solution avoids iterating through potentially huge ranges.

🏁 Summary

The solution converts each interval into two boundary records:

(startingMark, startingRank)
(endingMark, endingRank)

For:

l = [1, 6, 14]
r = [3, 9, 15]

the boundary list becomes:

(1,1)
(3,3)
(6,4)
(9,7)
(14,8)
(15,9)

For every requested rank:

1. Binary search for the largest boundary rank <= target rank.
2. Use that boundary as the base.
3. Calculate:

       mark = baseMark + (targetRank - baseRank)

For:

rank = [2, 5, 8]

we get:

2 -> 2
5 -> 7
8 -> 14

Therefore:

Output = [2, 7, 14]

Efficient solution:

Time  : O(N + Q log N)
Space : O(N + Q)