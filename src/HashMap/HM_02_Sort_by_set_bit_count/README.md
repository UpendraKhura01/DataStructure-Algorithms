# 🔢 Sort by Set Bit Count

## 📌 Problem Statement

Given an array of integers `arr[]`.

Sort the array in descending order according to the number of set bits in the binary representation of each number.

A set bit means:

    Bit with value 1

Example:

``` id="binary-example"
5  → 0101 → 2 set bits
7  → 0111 → 3 set bits
15 → 1111 → 4 set bits
```

If two numbers have the same number of set bits:

    Preserve their original relative order

This is called:

    Stable sorting

---

## 🎯 Goal

Sort numbers by decreasing set bit count while maintaining stable order for equal counts.

---

## 💡 Intuition

The entire problem depends on:

    Number of set bits in each number

So for every element:

    Count set bits
    Group numbers according to bit count
    Output groups from highest to lowest

Example:

``` id="group-example"
arr = [5, 2, 3, 9]
```

Set bits:

``` id="setbits"
5 → 2
2 → 1
3 → 2
9 → 2
```

Groups:

``` id="groups"
2 bits → [5, 3, 9]
1 bit  → [2]
```

Final answer:

``` id="group-answer"
[5, 3, 9, 2]
```

Notice:

``` id="stable-order"
5, 3, 9 remain in original order
```

This is stable sorting.

---

## 🔥 Key Idea

Two approaches are used:

---

### Approach 1: HashMap + Manual Bit Counting

Store numbers according to their set bit count.

Structure:

``` id="map-structure"
bitCount → list of numbers
```

Then traverse from highest bit count to lowest.

---

### Approach 2: Inbuilt Sorting + Integer.bitCount()

Use:

```java
Integer.bitCount(x)
```

which directly returns number of set bits.

Then sort in descending order.

Java's sort is stable for objects, so original order remains preserved.

---

## 🧠 Thought Process

Suppose:

``` id="input-example"
arr = [5, 2, 3, 9, 4]
```

Binary representations:

``` id="binary-values"
5 → 0101 → 2 bits
2 → 0010 → 1 bit
3 → 0011 → 2 bits
9 → 1001 → 2 bits
4 → 0100 → 1 bit
```

Group them:

``` id="grouped"
2 bits → [5, 3, 9]
1 bit  → [2, 4]
```

Output highest bit groups first:

``` id="final-output"
[5, 3, 9, 2, 4]
```

---

## 💻 Code

### Without Inbuilt Function

```java
static ArrayList<Integer> sortBySetBitCount(int[] arr) {

    HashMap<Integer, ArrayList<Integer>> mp = new HashMap<>();

    int maxbit = 0;

    for (int i : arr) {

        int bits = countbit(i);

        mp.putIfAbsent(bits, new ArrayList<>());

        mp.get(bits).add(i);

        maxbit = Math.max(maxbit, bits);
    }

    ArrayList<Integer> ans = new ArrayList<>();

    for (int i = maxbit; i > 0; i--) {

        if (mp.containsKey(i)) {

            for (int j : mp.get(i)) {
                ans.add(j);
            }
        }
    }

    return ans;
}

private static int countbit(int n) {

    int count = 0;

    while (n > 0) {

        if (n % 2 == 1) {
            count++;
        }

        n /= 2;
    }

    return count;
}
```

---

### With Inbuilt Function

```java
static ArrayList<Integer> sortBySetBitCount2(int[] arr) {

    int n = arr.length;

    ArrayList<Integer> ans = new ArrayList<>();

    for (int i = 0; i < n; i++) {
        ans.add(arr[i]);
    }

    ans.sort(
        Comparator.comparingInt(a -> -Integer.bitCount(a))
    );

    return ans;
}
```

---

## 🧪 Dry Run

Input:

``` id="dryrun-input"
arr = [5, 2, 3, 9, 4, 6]
```

---

Step 1: Count set bits

``` id="count-bits"
5 → 0101 → 2 bits
2 → 0010 → 1 bit
3 → 0011 → 2 bits
9 → 1001 → 2 bits
4 → 0100 → 1 bit
6 → 0110 → 2 bits
```

---

Step 2: Store in HashMap

``` id="hashmap"
2 → [5, 3, 9, 6]
1 → [2, 4]
```

---

Step 3: Traverse from highest bit count

Start from:

``` id="max-bit"
2 bits
```

Add:

``` id="add-high"
[5, 3, 9, 6]
```

Then:

``` id="add-low"
[2, 4]
```

---

Final answer:

``` id="final-dry-answer"
[5, 3, 9, 6, 2, 4]
```

---

## 🔁 Logic Flow

    Start
      |
      v
    Traverse array
      |
      v
    Count set bits for each number
      |
      v
    Group numbers by bit count
      |
      v
    Track maximum bit count
      |
      v
    Traverse groups from high to low
      |
      v
    Add numbers into answer
      |
      v
    Return sorted array

---

## 📊 Complexity

### Approach 1

Time Complexity:

``` id="tc1"
O(n * log(maxValue))
```

Reason:

- Every number is processed
- Bit counting takes logarithmic time

---

Space Complexity:

``` id="sc1"
O(n)
```

Reason:

    HashMap stores all elements.

---

### Approach 2

Time Complexity:

``` id="tc2"
O(n log n)
```

Reason:

    Sorting dominates complexity.

---

Space Complexity:

``` id="sc2"
O(n)
```

Reason:

    ArrayList stores all elements.

---

## 🎯 Key Takeaways

- Set bit means binary digit `1`.
- Stable sorting preserves original order.
- Numbers can be grouped by bit count.
- `Integer.bitCount()` is very useful.
- HashMap grouping also works effectively.

---

## 🔥 Most Important Insight

The problem is NOT normal sorting.

We sort using:

``` id="sorting-rule"
number of set bits
```

instead of actual numerical value.

And equal bit counts must preserve original order.

---

## 🏁 Summary

To solve this problem:

    1. Count set bits of every number.
    2. Group or sort numbers according to bit count.
    3. Place higher bit counts first.
    4. Preserve original order for equal counts.
    5. Return final sorted array.

Efficient solutions:

    HashMap Approach:
        Time  : O(n log(maxValue))
        Space : O(n)

    Inbuilt Sort Approach:
        Time  : O(n log n)
        Space : O(n)