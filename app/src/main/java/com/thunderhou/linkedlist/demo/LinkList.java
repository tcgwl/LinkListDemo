package com.thunderhou.linkedlist.demo;

import com.thunderhou.linkedlist.demo.bean.Node;

/**
 * 1.单链表的创建和遍历
 */
public class LinkList {
    public Node head;//头节点
    public Node current;//当前节点的索引

    /**
     * 向链表中添加数据
     */
    public LinkList add(int data) {
        //判断链表为空的时候
        if (head == null) {//如果头节点为空，说明这个链表还没有创建，那就把新的节点赋值给头节点
            head = new Node(data);
            current = head;
        } else {
            //创建新节点，放在当前节点的后面（把新节点和链表进行关联）
            current.next = new Node(data);
            //把链表的当前索引向后移动一位
            current = current.next;//此次操作完成后，current节点指向新节点
        }

        return this;
    }

    /**
     * 方法重载：向链表中添加结点
     */
    public LinkList add(Node node) {
        if (node == null) return this;

        if (head == null) {
            head = node;
            current = head;
        } else {
            current.next = node;
            current = current.next;
        }

        return this;
    }

    /**
     * 打印输出链表
     * @param node 表示从head节点开始进行遍历
     */
    public void print(Node node) {
        if (node == null) {
            System.out.println("空链表");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        current = node;
        while (current != null) {
            stringBuilder.append(current.data+"->");
            current = current.next;
        }
        String result = stringBuilder.toString();
        System.out.println(result.substring(0, result.lastIndexOf("->")));
    }

}
