package Binary_Tree.BT_05_Distribute_candies;

class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}
public class solution {
    public static void main(String[] args) {

        Node root = new Node(5);

        root.left = new Node(0);
        root.right = new Node(0);

        root.left.left = null;
        root.left.right = null;

        root.right.left = new Node(0);
        root.right.right = new Node(0);

        System.out.println(distCandy(root));
    }
    static int distCandy(Node root) {
        // code here
        int[] count = {0};
        helper(root,count);

        return count[0];
    }
    static int helper(Node node,int[] count){
        if(node == null) return 0;

        int l = helper(node.left,count);
        int r = helper(node.right,count);

        count[0] += Math.abs(l)+Math.abs(r);

        return node.data+l+r-1;
    }

}
