# Max Amount by Selling K Tickets

**Difficulty:** Medium

## Problem Statement

You are given an array `arr[]` where `arr[i]` represents the number of tickets available with the `i-th` seller.

The price of a ticket is equal to the number of tickets **currently remaining** with that seller.

After selling one ticket:

- The seller's ticket count decreases by **1**.
- Therefore, the next ticket from the same seller becomes **1 cheaper**.

You can sell **at most `k` tickets** in total.

Return the **maximum total amount** that can be earned modulo **10⁹ + 7**.

---

## Example 1

### Input

```text
arr = [4,3,6,2,4]

k = 3
```

### Output

```text
15
```

### Explanation

Initially

```
6 4 4 3 2
```

Sell

```
6
```

Seller becomes

```
5
```

Heap

```
5 4 4 3 2
```

Sell

```
5
```

Seller becomes

```
4
```

Heap

```
4 4 4 3 2
```

Sell

```
4
```

Total

```
6 + 5 + 4 = 15
```

---

## Example 2

### Input

```text
arr = [5,3,5,2,4,4]

k = 2
```

### Output

```text
10
```

### Explanation

Sell

```
5
```

and

```
5
```

Total

```
10
```

---

# Intuition

At every step,

we should sell the ticket with the **highest current price**.

Why?

Suppose the largest ticket value is

```
8
```

and another seller has

```
5
```

Selling `8` first gives

```
8
```

and the seller becomes

```
7
```

If we sell `5` first,

we only earn

```
5
```

while the `8` remains unchanged.

Selling the highest available ticket is always optimal.

This is exactly what a **Max Heap (Priority Queue)** provides.

---

# Algorithm

1. Insert every seller's ticket count into a max heap.
2. While
    - `k > 0`
    - heap is not empty
3. Remove the largest value.
4. Add it to the answer.
5. Decrease it by one.
6. Insert the decreased value back into the heap.
7. Repeat until `k` tickets are sold.

---

# Dry Run

## Input

```text
arr = [4,3,6,2,4]

k = 3
```

### Initial Heap

```
6 4 4 3 2
```

---

### Ticket 1

Remove

```
6
```

Profit

```
6
```

Push

```
5
```

Heap

```
5 4 4 3 2
```

---

### Ticket 2

Remove

```
5
```

Profit

```
6 + 5 = 11
```

Push

```
4
```

Heap

```
4 4 4 3 2
```

---

### Ticket 3

Remove

```
4
```

Profit

```
15
```

Done.

---

# Why Max Heap?

At every sale we need

```
Largest ticket count
```

Operations required

- Find maximum
- Remove maximum
- Insert updated value

A Max Heap performs all of them in

```
O(log n)
```

making it the ideal data structure.

---

# Correctness Proof

We prove that always selling the seller with the maximum remaining tickets is optimal.

Suppose at some step the highest ticket price is

```
x
```

and another available ticket has price

```
y

where

x > y
```

Selling `y` first earns less immediately while leaving `x` unchanged.

Selling `x` first earns more immediately, and the seller's next ticket becomes `x-1`, which is still at least as good as postponing the sale.

Thus, replacing any sale of `y` with `x` cannot decrease the total profit.

By repeatedly applying this argument, always choosing the current maximum ticket price yields the maximum possible revenue.

Therefore, the greedy algorithm is correct.

---

# Complexity Analysis

Let

```
n = number of sellers
```

### Building Heap

```
O(n log n)
```

### Each Sale

Pop

```
O(log n)
```

Push

```
O(log n)
```

For `k` tickets

```
O(k log n)
```

---

### Total Time Complexity

```text
O((n + k) log n)
```

---

### Space Complexity

```text
O(n)
```

for the priority queue.

---

# Java Solution

```java
class Solution {

    long MOD = 1_000_000_007;

    public int maxAmount(int[] arr, int k) {

        PriorityQueue<Integer> pq =
                new PriorityQueue<>((a, b) -> b - a);

        for (int x : arr)
            pq.offer(x);

        long ans = 0;

        while (k > 0 && !pq.isEmpty()) {

            int cur = pq.poll();

            if (cur == 0)
                break;

            ans = (ans + cur) % MOD;

            pq.offer(cur - 1);

            k--;
        }

        return (int) ans;
    }
}
```

---

# Key Takeaways

- The current ticket price equals the seller's remaining tickets.
- Selling from the seller with the maximum remaining tickets always gives the highest profit.
- A **Max Heap** efficiently maintains the seller with the highest current ticket price.
- After each sale:
    - Add the price to the answer.
    - Decrease the seller's ticket count by 1.
    - Push it back into the heap.

---

# Tags

- Heap
- Priority Queue
- Greedy
- Simulation