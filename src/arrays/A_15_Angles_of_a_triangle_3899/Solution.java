package arrays.A_15_Angles_of_a_triangle_3899;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] sides = {3, 4, 5};
        double[] ans = internalAngles(sides);
        for(double i : ans)
            System.out.print(i);

    }
    static double[] internalAngles(int[] sides) {
        int a = sides[0];
        int b = sides[1];
        int c = sides[2];
        double[] ans = new double[3];
        if (a + b > c && a + c > b  && b + c > a){
            double angleBC = Math.acos (((b * b) + (c * c) - (a * a)) / (2.0 * b * c));
            double angleAC = Math.acos (((a * a) + (c * c) - (b * b)) / (2.0 * a * c));
            double angleAB = Math.acos (((b * b) + (a * a) - (c * c)) / (2.0 * b * a));
            angleBC = Math.toDegrees(angleBC);
            angleAB = Math.toDegrees(angleAB);
            angleAC = Math.toDegrees(angleAC);
            ans[0] = angleAB;
            ans[1] = angleAC;
            ans[2] = angleBC;
        }
        else {

            return new double[0];
        }

        Arrays.sort(ans);

        return ans;
    }
}
