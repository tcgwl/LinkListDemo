package com.thunderhou.linkedlist.demo;

import com.thunderhou.linkedlist.demo.bean.Node;

/**
 * Created by Archer on 2018/5/17.
 */

public class LinkListUtil {
    /**
     * 获取单链表的长度
     */
    public static int getLength(Node head) {
        if (head == null) {
            return 0;
        }
        int length = 0;
        Node current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第index个结点(简单版)
     */
    public static Node findLastNodeSimple(Node head, int index) {
        if (head == null) return null;
        //第一次遍历，得到链表的长度
        int length = getLength(head);
        if (length < index) {
            System.out.println("链表的长度小于"+index);
            return null;
        }
        //第二次遍历，输出倒数第index个结点的数据
        Node current = head;
        for (int i = 0; i < length - index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * 查找单链表中的倒数第index个结点(进阶版)
     */
    public static Node findLastNodeFinal(Node head, int index) {
        if (index == 0 || head == null) return null;

        Node first = head;
        Node second = head;
        //让 second 节点往后移 index-1 个位置
        for (int i = 0; i < index - 1; i++) {
            System.out.println("i="+i);
            second = second.next;
            if (second == null) {//说明index的值大于链表长度了
                System.out.println("链表的长度小于"+index);
                //throw new RuntimeException("链表的长度小于"+index);
                return null;
            }
        }
        //让 first 和 second 节点整体向后移动，直到 second 走到最后一个节点
        while (second.next != null) {
            first = first.next;
            second = second.next;
        }
        //当 second 节点走到最后一个节点的时候，此时 first 指向的节点就是我们要找的节点
        return first;
    }

    /**
     * 查找链表的中间结点
     */
    public static Node findMidNode(Node head) {
        if (head == null) return null;

        Node first = head;
        Node second = head;
        //每次移动时，让 second 节点移动两位，first 节点移动一位
        while (second != null && second.next != null) {
            first = first.next;
            second = second.next.next;
        }

        return first;
    }
}
