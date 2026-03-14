import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}
class pair {
    Node first;//stores the node
    int second;//stores the vertical distance

    pair(Node first, int second) {
        this.first = first;
        this.second = second;
    }
}
public class B_01_Top_view_of_BinaryTree {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Node root = new Node(10);

        root.left = new Node(20);
        root.right = new Node(30);

        root.left.left = new Node(40);
        root.left.right = new Node(60);

        root.right.left = new Node(90);
        root.right.right = new Node(100);
        ArrayList<Integer> list=topView(root);
        System.out.println("ans is :");
        for (int val : list){
            System.out.print(val+" ");
        }

    }
    static ArrayList<Integer> topView(Node root) {
        // code here
        Queue<pair> q=new LinkedList<>();
       int vertical=0;
       q.add(new pair(root, vertical));
       /*
       we are storing the Vertical Distance-> VD in the key section of the map because we want to have only one value per VD.
       and we are storing the Node data in the value section.
        */
        Map<Integer,Integer> mp=new TreeMap<>();
        /*
        we created tree map because we want the vertical distance to be sorted so that we can access the data from left to right.
         */
        while(!q.isEmpty()){
            int s=q.size();
            for(int i=0;i<s;i++){
                pair pair=q.poll();

                mp.putIfAbsent(pair.second,pair.first.data);
                /*pair stores node in first and vertical distance in second
                so when we go to left we should decrease the VD and when we go to right we should increase it.

                 */

                if(pair.first.left!=null) q.add(new pair(pair.first.left,pair.second-1));
                if(pair.first.right!=null) q.add(new pair(pair.first.right, pair.second+1));
                /*
                if we have left and right node,then only we push it into the tree map;
                 */

            }
        }

        return new ArrayList<>(mp.values());
    }
}
