# Earliest Finish Time for Land and Water Rides II (LeetCode 3635)

**Difficulty:** Medium

## Problem Statement

A theme park has two categories of rides:

- **Land rides**
    - `landStartTime[i]` → earliest time the ride can start.
    - `landDuration[i]` → duration of the ride.

- **Water rides**
    - `waterStartTime[j]` → earliest time the ride can start.
    - `waterDuration[j]` → duration of the ride.

A tourist must ride:

- exactly **one land ride**
- exactly **one water ride**

The rides may be taken in **either order**.

Rules:

- A ride can only start at or after its opening time.
- Immediately after finishing one ride, the tourist may begin the other if it is already open, otherwise they must wait.

Return the **earliest possible finishing time**.

---

## Example 1

**Input**

```text
landStartTime = [2,8]
landDuration  = [4,1]

waterStartTime = [6]
waterDuration  = [3]
```

**Output**

```text
9
```

**Explanation**

Choose:

```text
Land Ride 0:
Start = 2
Finish = 6

Water Ride:
Starts immediately at 6
Finish = 9
```

No other order gives a smaller finish time.

---

## Example 2

**Input**

```text
landStartTime = [5]
landDuration  = [3]

waterStartTime = [1]
waterDuration  = [10]
```

**Output**

```text
14
```

**Explanation**

Take the water ride first:

```text
Water:
1 → 11

Land:
11 → 14
```

---

## Constraints

- `1 ≤ n, m ≤ 5 × 10^4`
- `1 ≤ startTime, duration ≤ 10^5`

---

# Observation

There are only **two possible orders**:

1. Land → Water
2. Water → Land

Instead of checking every pair (`O(nm)`), notice something important.

---

## Land → Water

Suppose the tourist finishes the land ride at time

```text
finishLand = landStart + landDuration
```

For a particular water ride,

```text
start = max(finishLand, waterStart)
finish = start + waterDuration
```

To minimize this value, we only need the **earliest finishing land ride**.

So first compute

```text
fastLand =
minimum(landStart + landDuration)
```

Then every water ride can be evaluated independently.

---

## Water → Land

Similarly,

First compute

```text
fastWater =
minimum(waterStart + waterDuration)
```

Then for every land ride,

```text
start = max(fastWater, landStart)
finish = start + landDuration
```

Take the minimum.

---

# Algorithm

### Step 1

Find the earliest land ride finish

```text
fastLand = min(start + duration)
```

---

### Step 2

For every water ride

```text
start = max(fastLand, waterStart)

finish = start + waterDuration
```

Store the minimum.

---

### Step 3

Find the earliest water ride finish

```text
fastWater = min(start + duration)
```

---

### Step 4

For every land ride

```text
start = max(fastWater, landStart)

finish = start + landDuration
```

Store the minimum.

---

### Step 5

Return

```text
min(Land→Water,
    Water→Land)
```

---

# Code

```java
static int earliestFinishTime(
        int[] landStartTime,
        int[] landDuration,
        int[] waterStartTime,
        int[] waterDuration) {

    int n = landStartTime.length;
    int m = waterStartTime.length;

    int fastLand = Integer.MAX_VALUE;
    int fastWater = Integer.MAX_VALUE;

    int landWater = Integer.MAX_VALUE;
    int waterLand = Integer.MAX_VALUE;

    // Earliest finishing land ride
    for (int i = 0; i < n; i++) {
        fastLand = Math.min(fastLand,
                landStartTime[i] + landDuration[i]);
    }

    // Land -> Water
    for (int i = 0; i < m; i++) {

        fastWater = Math.min(fastWater,
                waterStartTime[i] + waterDuration[i]);

        int start = Math.max(fastLand,
                waterStartTime[i]);

        landWater = Math.min(
                landWater,
                start + waterDuration[i]);
    }

    // Water -> Land
    for (int i = 0; i < n; i++) {

        int start = Math.max(
                fastWater,
                landStartTime[i]);

        waterLand = Math.min(
                waterLand,
                start + landDuration[i]);
    }

    return Math.min(landWater, waterLand);
}
```

---

# Dry Run

## Example

```text
Land

Ride A:
Start = 2
Duration = 4
Finish = 6

Ride B:
Start = 8
Duration = 1
Finish = 9

fastLand = 6
```

Water

```text
Start = 6
Duration = 3

start = max(6,6)=6

finish = 9
```

So

```text
Land → Water = 9
```

Now compute

```text
fastWater = 9
```

For Land rides

Ride A

```text
start = max(9,2)=9
finish =13
```

Ride B

```text
start=max(9,8)=9
finish=10
```

Minimum

```text
Water → Land = 10
```

Answer

```text
min(9,10)=9
```

---

# Why This Works

Suppose we are considering **Land → Water**.

Among all possible land rides, only the one that finishes **earliest** can ever help start the water ride sooner.

Choosing a slower land ride cannot reduce the finishing time because:

```text
finishLand ≥ fastLand
```

Therefore

```text
max(finishLand, waterStart)
≥
max(fastLand, waterStart)
```

The same reasoning applies to **Water → Land**.

Thus only the earliest-finishing ride of the first category needs to be considered.

---

# Complexity Analysis

Let

- `n` = number of land rides
- `m` = number of water rides

### Time Complexity

```text
O(n + m)
```

Three linear scans.

### Space Complexity

```text
O(1)
```

Only a few variables are used.

---

# Summary

- Compute the **earliest finishing land ride**.
- Evaluate every water ride using that finish time.
- Compute the **earliest finishing water ride**.
- Evaluate every land ride using that finish time.
- Return the smaller of the two possibilities.

This avoids checking every pair of rides and achieves an optimal **O(n + m)** solution.