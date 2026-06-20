# 📦 Block Placement Queries

## 📌 Problem Statement

You are given an infinite number line starting from:

    0 → +∞

You will process two types of queries:

### Type 1
    [1, x]

Build an obstacle at position `x`.

### Type 2
    [2, x, sz]

Check whether a block of size `sz` can be placed completely inside:

    [0, x]

such that it does not intersect any obstacle (touching is allowed).

---

## 🎯 Goal

For each query of type 2, return:

    true / false

based on whether a block of size `sz` can be placed.

---

## 💡 Intuition

The problem is about maintaining **segments between obstacles**.

Key idea:

    Obstacles split the number line into valid free intervals.

We only need to track:

- positions of obstacles
- maximum free segment sizes between them

Then answer queries by checking:

    Is there any free segment ≥ sz in range [0, x]?

---

## 🔥 Key Idea

We maintain:

### 1. Ordered obstacle positions
Using:

    TreeSet

So we can find:

    previous obstacle (lower)
    next obstacle (higher)

---

### 2. Segment lengths between obstacles
We use a:

    Segment Tree

to store:

    max free gap ending at each position

Each time we add a new obstacle:

We update gaps:

    left gap
    right gap

---

## 🧠 Thought Process

### When inserting obstacle at `block`:

We split an interval:

    prev ----- block ----- next

So we update:

- gap from prev → block
- gap from block → next

This maintains correct segment sizes.

---

### When answering query [2, idx, size]:

We check:

1. Maximum free segment in range [0, idx]
2. Gap from last obstacle before idx → idx

Then:

    max_gap = max(left_side_gap, right_side_gap)

If:

    max_gap ≥ size → TRUE
    else → FALSE

---

## 💻 Code

```java
class answer {

    int N = 50000;
    int[] segtree = new int[4 * N];
    TreeSet<Integer> st = new TreeSet<>();

    public List<Boolean> getResults(int[][] queries) {

        List<Boolean> ans = new ArrayList<>();
        st.add(0);

        for (int[] q : queries) {

            if (q[0] == 1) {

                int block = q[1];

                Integer next = st.higher(block);
                if (next != null) {
                    updatesegtree(next, next - block, 0, 0, N - 1);
                }

                int prev = st.lower(block);
                updatesegtree(block, block - prev, 0, 0, N - 1);

                st.add(block);

            } else {

                int idx = q[1];
                int size = q[2];

                int max_left = query(0, idx, 0, 0, N - 1);

                int prev = st.lower(idx);
                int max_right = idx - prev;

                int max = Math.max(max_left, max_right);

                ans.add(max >= size);
            }
        }

        return ans;
    }

    private void updatesegtree(int idx, int val, int cur, int lr, int rr) {

        if (lr == rr) {
            segtree[cur] = val;
            return;
        }

        int mid = lr + (rr - lr) / 2;

        if (idx <= mid) {
            updatesegtree(idx, val, 2 * cur + 1, lr, mid);
        } else {
            updatesegtree(idx, val, 2 * cur + 2, mid + 1, rr);
        }

        segtree[cur] =
            Math.max(segtree[2 * cur + 1], segtree[2 * cur + 2]);
    }

    private int query(int q_start, int q_end, int cur, int lr, int rr) {

        if (lr >= q_start && rr <= q_end) {
            return segtree[cur];
        }

        if (rr < q_start || lr > q_end) {
            return 0;
        }

        int mid = lr + (rr - lr) / 2;

        int left = query(q_start, q_end, 2 * cur + 1, lr, mid);
        int right = query(q_start, q_end, 2 * cur + 2, mid + 1, rr);

        return Math.max(left, right);
    }
}
```

---

## 🧪 Dry Run

### Input:

    queries = [[1,2],[2,3,3],[2,3,1],[2,2,2]]

---

### Step 1: [1,2]

Insert obstacle at:

    2

TreeSet:

    {0, 2}

Update gaps:

    0 → 2 = 2

---

### Step 2: [2,3,3]

Check interval [0,3]:

Free segments:

    0 → 2 = 2
    2 → 3 = 1

Max gap:

    2 < 3 → false

---

### Step 3: [2,3,1]

Max gap in [0,3]:

    2 ≥ 1 → true

---

### Step 4: [2,2,2]

Max gap in [0,2]:

    2 ≥ 2 → true

---

## 🔁 Logic Flow

    Start
      |
      v
    Maintain TreeSet of obstacles
      |
      v
    For each query:
        |
        +-- Type 1:
        |      Insert obstacle
        |      Update segment tree gaps
        |
        +-- Type 2:
               Check max free segment
               Compare with required size
               Store result
      |
      v
    Return results

---

## 📊 Complexity

### Time Complexity

Each query:

    O(log N)

Total:

    O(Q log N)

Where:

    Q = number of queries

---

### Space Complexity

    O(N)

For:

    Segment Tree + TreeSet

---

## 🎯 Key Takeaways

- Obstacles split number line into segments
- TreeSet helps find neighbors quickly
- Segment Tree maintains maximum segment size
- Query is just a max-check problem
- No need to simulate block placement

---

## 🔥 Most Important Insight

Instead of checking every placement:

    Track only gaps between obstacles

Then:

    Answer becomes a simple max segment query

This transforms a complex geometry problem into:

    Segment Tree + Ordered Set problem

---

## 🏁 Summary

To solve:

    1. Maintain obstacle positions in TreeSet.
    2. Maintain segment sizes using Segment Tree.
    3. Update gaps on insertion.
    4. For queries, check max gap in range.
    5. Return whether block fits.

Efficient solution:

    Time  : O(Q log N)
    Space : O(N)