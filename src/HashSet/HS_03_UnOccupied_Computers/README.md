# 🖥️ Unoccupied Computers

## 📌 Problem Statement

A cafe has:

    n

computers.

Customers are represented by uppercase English letters in a string `s`.

Each customer appears exactly twice:

    First occurrence  → Arrival
    Second occurrence → Departure

When a customer arrives:

    If a computer is available:
        Customer gets a computer.

    If no computer is available:
        Customer is rejected.

A rejected customer does not use a computer, so their second occurrence simply represents their departure from the event sequence.

Return the number of customers who were rejected.

---

## 🎯 Goal

Simulate the customer events and determine how many customers could not get a computer when they arrived.

The important part is to distinguish between:

    Accepted customers
    Rejected customers

because their second occurrences must be handled differently.

---

## 💡 Intuition

We need to track two groups of customers.

### Accepted

These customers successfully received a computer.

They are currently using one.

When they appear for the second time:

    Their computer becomes free.

So:

    n++

and the customer is removed from the accepted set.

---

### Rejected

These customers arrived when no computer was available.

They never received a computer.

When they appear for the second time:

    Nothing happens to n.

We simply remove them from the rejected set.

---

## 🔥 Key Idea

Use two `HashSet`s:

    accepted
        → Customers currently occupying computers.

    rejected
        → Customers who arrived when no computer was available.

For every character `c`:

    If c is in accepted:
        Customer leaves.
        Free one computer.

    Else if c is in rejected:
        Customer's second occurrence.
        Remove from rejected.

    Else:
        This is a new arrival.

        If computer available:
            Assign computer.
            Add customer to accepted.

        Otherwise:
            Reject customer.
            Increase answer.
            Add customer to rejected.

---

## 🧠 Thought Process

Suppose:

    n = 2

and a customer arrives.

If:

    n > 0

then a computer is available.

So:

    n--
    accepted.add(customer)

When that customer leaves:

    n++
    accepted.remove(customer)

Now consider a rejected customer.

If:

    n = 0

when they arrive, they cannot use a computer.

So:

    notAssigned++

and they are placed into:

    rejected

Their second occurrence does **not** increase the number of computers because they never occupied one.

This distinction is the core of the problem.

---

## 💻 Code

```java
int solve(int n, String s) {
    int notAssigned = 0;

    HashSet<Character> accepted = new HashSet<>();
    HashSet<Character> rejected = new HashSet<>();

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        if (accepted.contains(c)) {
            n++;
            accepted.remove(c);
        }
        else if (rejected.contains(c)) {
            rejected.remove(c);
        }
        else {
            if (n > 0) {
                n--;
                accepted.add(c);
            }
            else {
                notAssigned++;
                rejected.add(c);
            }
        }
    }

    return notAssigned;
}
```

---

## 🧪 Dry Run

Input:

    n = 3
    s = "GACCBDDBAGEE"

Track the important events:

    G → computer available → accepted
    A → computer available → accepted
    C → computer available → accepted

Now:

    n = 0

Next:

    B → no computer → rejected

Then:

    D → no computer → rejected

When:

    D → second occurrence → remove from rejected

Then:

    B → second occurrence → remove from rejected

Later:

    G → leaves → computer becomes available

Then:

    A → leaves → computer becomes available

Then:

    E → computer available → accepted

    E → leaves → computer becomes available

Only:

    B

was rejected.

Therefore:

    Answer = 1

---

## 🔁 Logic Flow

    Start
      |
      v
    Read customer c
      |
      v
    Is c in accepted?
      |
      +---- Yes ----> Customer leaves
      |                  |
      |                  v
      |              n++
      |                  |
      |              Remove c
      |
      +---- No
             |
             v
       Is c in rejected?
             |
             +---- Yes ----> Remove c from rejected
             |
             +---- No
                    |
                    v
              New customer arrives
                    |
                    v
                Is n > 0?
                 /     \
               Yes      No
                |        |
                v        v
             n--       notAssigned++
             |          |
          accepted     rejected
                \        /
                 \      /
                    v
              Next customer
                    |
                    v
                  Done
                    |
                    v
             Return notAssigned

---

## 📊 Complexity

Let:

    m = s.length()

Since every customer appears exactly twice:

    m <= 52

### Time Complexity

Each character is processed once.

`HashSet` operations such as:

    contains()
    add()
    remove()

take average:

    O(1)

Therefore:

    Time = O(m)

---

### Space Complexity

At most 26 distinct customers exist.

Therefore:

    Space = O(26)

which can be considered:

    O(1)

because the alphabet size is fixed.

---

## 🎯 Key Takeaways

- Use a `HashSet` to track customers currently using computers.
- Use another `HashSet` to track customers who were rejected.
- An accepted customer's second occurrence frees a computer.
- A rejected customer's second occurrence does not affect the number of computers.
- Decrease `n` when a customer gets a computer.
- Increase `n` when an accepted customer leaves.
- Increase `notAssigned` only when a new customer arrives and `n == 0`.

---

## 🔥 Most Important Insight

The tricky part is that **not every second occurrence represents a computer becoming free**.

There are two cases:

    Accepted customer:
        First occurrence → occupies computer
        Second occurrence → frees computer

    Rejected customer:
        First occurrence → no computer
        Second occurrence → still no computer involved

Therefore, we must maintain two separate sets:

    accepted
    rejected

This makes it possible to correctly decide what the second occurrence means.

---

## 🏁 Summary

The problem can be solved using simulation and two `HashSet`s.

The process is:

    1. Start with `n` available computers.
    2. Process every customer event.
    3. If an accepted customer appears again, free a computer.
    4. If a rejected customer appears again, simply remove them from the rejected set.
    5. For a new customer, assign a computer if one is available.
    6. Otherwise, count the customer as rejected.
    7. Return `notAssigned`.

Core logic:

    New customer + computer available
        → accepted

    New customer + no computer
        → rejected

    Accepted customer appears again
        → computer becomes free

    Rejected customer appears again
        → no computer change

Complexity:

    Time  : O(s.length())
    Space : O(1)