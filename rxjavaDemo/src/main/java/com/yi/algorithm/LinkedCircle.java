package com.yi.algorithm;

public class LinkedCircle {


    public static void main(String[] args) {

        // 初始化有环的链表
        Node root = initNode();
        boolean result = isCircle(root);
        System.out.println("---  result= " + result);
    }

    /**
     * 初始化有环的链表
     */
    private static Node initNode() {

        new Node(new Node(null, 1),0);
        Node root = new Node(null, 0);
        Node n4 = new Node(null, 4);
        Node n3 = new Node(null, 3);
        Node n2 = new Node(null, 2);
        Node n1 = new Node(null, 1);

        root.next = n1;
        n1.next = n2;
        n2.next = root;
        return root;
    }

    /**
     * 验证是否成环
     */
    private static boolean isCircle(Node node) {
        Node slow = node;
        Node fast = node;
        while (slow.next != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow.value == fast.value) {
                return true;
            }
        }
        return false;
    }

    /**
     * 链表实体类
     */
    static class Node {
        Node next;
        int value;

        Node(Node next, int value) {
            this.next = next;
            this.value = value;
        }
    }
}
