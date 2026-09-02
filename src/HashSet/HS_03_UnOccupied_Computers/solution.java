package HashSet.HS_03_UnOccupied_Computers;

import java.util.HashSet;
/*
        Unoccupied Computers

        Difficulty: Easy
        A cafe has n computers. The customer events are represented by a string s of uppercase English letters,
        where each distinct letter appears exactly twice:

        The first occurrence denotes the customer's arrival.
        The second occurrence denotes the customer's departure.
        A customer is assigned a computer only if one is available at the time of arrival, otherwise the customer is
        rejected and does not use a computer.

        Return the number of customers who could not be assigned a computer upon arrival.

        Examples:

        Input: n = 3, s = "GACCBDDBAGEE"
        Output: 1
        Explanation: Only D will not be able to get any computer. So the answer is 1.
        Input: n = 1, s = "ABCBAC"
        Output: 2
        Explanation: B and C will not be able to get any computers. So the answer is 2.
        Constraints:

        1 ≤ n ≤ 26
        1 ≤ s.size() ≤ 52
        s consists of uppercase English letters and each letter occurs exactly 2 times.

 */
public class solution {
    public static void main(String[] args) {

    }
    int solve(int n, String s) {
        // code here
        int notAssigned = 0;
        HashSet<Character> accepted = new HashSet<>();
        HashSet<Character> rejected = new HashSet<>();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if(accepted.contains(c)){
                n++;
                accepted.remove(c);
            }
            else if(rejected.contains(c)){
                rejected.remove(c);
            }
            else{
                if(n > 0){
                    n--;
                    accepted.add(c);
                }
                else{
                    notAssigned++;
                    rejected.add(c);
                }
            }
        }
        return notAssigned;
    }
}
