package Binary_Tree.BT_04_Burning_tree;

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

public class solution {
    public static void main(String[] args) {
        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);


        System.out.println(minTime(root,2));

    }

    static int minTime(Node root, int target) {
        Map<Node, Node> mp = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int s = q.size();

            for (int i = 0; i < s; i++) {
                Node cur = q.poll();

                if (cur.left != null) {
                    q.add(cur.left);
                    mp.put(cur.left, cur);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                    mp.put(cur.right, cur);
                }
            }
        }
        Node node = find(root, target);
        return duration(node,mp);

    }

    static int duration(Node node, Map<Node, Node> mp) {
        Queue<Node> q = new LinkedList<>();
        Set<Node> vis = new HashSet<>();
        int size = 0;
        q.add(node);
        vis.add(node);
        while (!q.isEmpty()) {

            int s = q.size();
            boolean burned = false;

            for (int i = 0; i < s; i++) {
                Node cur = q.poll();

                if (mp.get(cur) != null && !vis.contains(mp.get(cur))){
                    q.add(mp.get(cur));
                    vis.add(mp.get(cur));
                    burned = true;
                }
                if (cur.left != null  && !vis.contains(cur.left)) {
                    q.add(cur.left);
                    vis.add(cur.left);
                    burned = true;
                }
                if (cur.right != null && !vis.contains(cur.right)) {
                    q.add(cur.right);
                    vis.add(cur.right);
                    burned = true;
                }
            }
            if (burned) size++;

        }
        return size;

    }

    static Node find(Node node, int target) {
        if (node == null || node.data == target) return node;

        Node left = find(node.left, target);
        if (left != null) return left;

        return find(node.right, target);
    }


}
