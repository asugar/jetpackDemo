package com.yi.algorithm;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class TreeWidth {

    public static void main(String[] args) {
//        System.out.println("a= " + getMaxWidth(initNode()));
//        printTreeByHo(initNode());
        Node root = initNode();
        printTreeRootLeftRight(root);
        System.out.println("");
        printTreeRootLeftRight2(root);
    }

    public static void printTreeRootLeftRight2(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (stack.size() > 0) {
            Node node = stack.pop();
            System.out.print(node.value + " - ");
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
    }

    /**
     * 前序：根，左，右
     * 中序：左，根，右
     * 后序：左，右，根
     */
    public static void printTreeRootLeftRight(Node node) {
        if (node != null) {
            printTreeRootLeftRight(node.left);
            printTreeRootLeftRight(node.right);
            System.out.print(node.value + " - ");
        }
    }

    public static int getMaxWidth(Node treeNode) {
        if (treeNode == null)
            return 0;

        Queue<Node> queue = new ArrayDeque<Node>();
        int maxWitdth = 1; // 最大宽度
        queue.add(treeNode); // 入队

        while (true) {
            int len = queue.size(); // 当前层的节点个数
            if (len == 0)
                break;
            while (len > 0) {// 如果当前层，还有节点
                Node node = queue.poll();
                len--;
                if (node.left != null)
                    queue.add(node.left); // 下一层节点入队
                if (node.right != null)
                    queue.add(node.right);// 下一层节点入队
            }
            maxWitdth = Math.max(maxWitdth, queue.size());
        }
        return maxWitdth;
    }

    private static Node initNode() {
        Node treeRoot = new Node("a",
                new Node("b",
                        new Node("d",
                                new Node("h", null, null), null),
                        new Node("e",
                                new Node("i", null, null), null)),
                new Node("c",
                        new Node("f", null, null),
                        new Node("g", null, null)));
        return treeRoot;
    }

    public static class Node {

        public Node(String value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node left;
        public Node right;
        public String value;
    }

}
