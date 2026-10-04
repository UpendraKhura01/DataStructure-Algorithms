package Grid.GD_17_Peremeter_of_Shapes;

/*
        Perimeter of Shapes in Binary Matrix
        Solved
        Difficulty: Easy
        Given a binary matrix mat[][] of size n × m, where each cell contains either 0 or 1,
        find the total perimeter of all figures formed by cells containing 1s.
        Two cells are considered adjacent if they share a common side.

        A single cell containing 1 has a perimeter of 4, whereas two adjacent cells containing 1 (
        i.e., 11) together have a perimeter of 6.



        Examples :

        Input: mat[][] = [[0,1,0,0,0], [1,1,1,0,0], [1,0,0,0,0]]
        Output: 12
        Explanation: The five cells form a single figure. Hence, the perimeter of the figure is 12.

        Input: mat[][] = [[1,0], [1,1]]
        Output: 8
        Explanation: The two adjacent cells share one common side. Hence, the perimeter of the figure is 6.

        Constraints:

        1 ≤ n, m ≤ 1000
 */
public class solution {
    public static void main(String[] args) {

    }

        static int findPerimeter(int[][] mat) {
            // code here

            int n = mat.length;
            int m = mat[0].length;

            int cnt = 0;
            int adj = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {

                    if (mat[i][j] == 1) {
                        cnt++;
                        if (i + 1 < n && mat[i + 1][j] == 1) {
                            adj++;
                        }
                        if (j + 1 < m && mat[i][j + 1] == 1) {
                            adj++;
                        }

                    }
                }
            }

            return 4 * cnt - 2 * adj;
        }
    }


