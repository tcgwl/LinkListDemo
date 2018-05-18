package com.thunderhou.linkedlist.demo;

import com.thunderhou.linkedlist.demo.bean.Node;

import java.util.Stack;

/**
 * Created by Archer on 2018/5/17.
 */

public class LinkListUtil {
    /**
     * 获取单链表的长度
     *
     * 时间复杂度为O(n)
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
     * 查找单链表中的倒数第index个节点(简单版)
     *
     * 时间复杂度为O(n)
     */
    public static Node findLastNodeSimple(Node head, int index) {
        if (head == null) return null;
        //第一次遍历，得到链表的长度
        int length = getLength(head);
        if (length < index) {
            System.out.println("链表的长度小于"+index);
            return null;
        }
        //第二次遍历，输出倒数第index个节点的数据
        Node current = head;
        for (int i = 0; i < length - index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * 查找单链表中的倒数第index个节点(进阶版)
     *
     * 时间复杂度为O(n)
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
     * 查找链表的中间节点
     *
     * 时间复杂度为O(n)
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

    /**
     * 合并两个有序的单链表，合并之后的链表依然有序
     *
     * 时间复杂度为O(max(len1,len2))
     */
    public static Node mergeLinkList(Node head1, Node head2) {
        if (head1 == null && head2 == null) return null;
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        Node head = null;//新链表的头节点
        Node current = null;//current节点指向新链表
        // 一开始，我们让current节点指向head1和head2中较小的数据，得到head节点
        if (head1.data < head2.data) {
            head = head1;
            current = head1;
            head1 = head1.next;
        } else {
            head = head2;
            current = head2;
            head2 = head2.next;
        }

        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                current.next = head1;//新链表中，current指针的下一个节点对应较小的那个数据
                current = current.next;//current指针下移
                head1 = head1.next;
            } else {
                current.next = head2;
                current = current.next;
                head2 = head2.next;
            }
        }
        //合并剩余的元素
        if (head1 != null) {//说明链表2遍历完了，是空的
            current.next = head1;
        }

        if (head2 != null) {//说明链表1遍历完了，是空的
            current.next = head2;
        }

        return head;
    }

    /**
     * 单链表的反转
     *
     * 时间复杂度为O(n)
     */
    public static Node reverseList(Node head) {
        //如果链表为空或者只有一个节点，无需反转，直接返回原链表的头节点
        if (head == null || head.next == null) return head;

        Node current = head;
        Node tmpNext = null;//定义当前节点的下一个节点
        Node reverseHead = null;//反转后新链表的表头

        while (current != null) {
            tmpNext = current.next;//暂时保存住当前节点的下一个节点，因为下一次要用

            //以下两行为核心代码
            current.next = reverseHead;//将current的下一个节点指向新链表的头节点
            reverseHead = current;

            current = tmpNext;//操作结束后，current节点后移
        }

        return reverseHead;
    }

    /**
     * 从尾到头打印单链表(使用自己创建的栈)
     * 考虑到"后进先出"，使用栈
     *
     * 时间复杂度为O(n)
     */
    public static void reversePrint(Node head) {
        if (head == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Node current = head;
        //将链表的所有节点压栈
        while (current != null) {
            stack.push(current);//将当前节点压栈
            current = current.next;
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (stack.size() > 0) {
            stringBuilder.append(stack.pop().data+"->");//出栈操作
        }
        String result = stringBuilder.toString();
        System.out.println(result.substring(0, result.lastIndexOf("->")));
    }

    /**
     * 从尾到头打印单链表(使用系统的栈，递归)
     */
    public static void reversePrintWithSystemStack(Node head) {
        if (head == null) {
            return;
        }

        reversePrintWithSystemStack(head.next);
        System.out.println(head.data);
    }

    /**
     * 判断单链表是否有环
     * 这里也是用到两个指针，如果一个链表有环，那么用一个指针去遍历，是永远走不到头的
     *
     * 时间复杂度为O(n)
     */
    public static boolean hasCycle(Node head) {
        if (head == null) return false;

        Node first = head;
        Node second = head;

        while (second != null) {
            first = first.next;//first指针走一步
            second =second.next.next;//second指针走两步
            if (first == second) {//一旦两个指针相遇，说明链表是有环的
                return true;
            }
        }

        return false;
    }

    /**
     * 判断单链表是否有环。返回的节点是相遇的那个节点
     */
    public static Node hasCycle2(Node head) {
        if (head == null) return null;

        Node first = head;
        Node second = head;

        while (second != null) {
            first = first.next;
            second = second.next.next;
            if (first == second) {
                return first;
            }
        }

        return null;
    }

    /**
     * 有环链表中，获取环的长度。参数node代表的是相遇的那个节点
     */
    public static int getCycleLength(Node head, Node node) {
        if (head == null) return 0;

        Node current = node;
        int length = 0;

        while (current != null) {
            current = current.next;
            length++;
            if (current == node) {//当current节点走到原点的时候
                return length;
            }
        }

        return length;
    }

    /**
     * 单链表中，取出环的起始点
     *
     * 拿到环的长度length之后，需要用到两个指针变量first和second，
     * 先让second指针走length步；
     * 然后让first指针和second指针同时各走一步，
     * 当两个指针相遇时，相遇时的节点就是环的起始点。
     */
    public static Node getCycleStart(Node head, int cycleLength) {
        if (head == null) {
            return null;
        }

        Node first = head;
        Node second = head;
        //先让second指针走length步
        for (int i = 0; i < cycleLength; i++) {
            second = second.next;
        }
        //然后让first指针和second指针同时各走一步
        while (first != null && second != null) {
            first = first.next;
            second = second.next;

            if (first == second) {//如果两个指针相遇了，说明这个节点就是环的起始点
                return first;
            }
        }

        return null;
    }

    /**
     * 判断两个单链表相交的第一个交点(利用"栈"的"后进先出"特点)
     *
     * 分别把两个链表的节点放入两个栈中，这样两个链表的尾节点就位于两个栈的栈顶，
     * 接下来比较下一个栈顶，直到找到最后一个相同的节点
     *
     * 利用两个辅助栈，空间复杂度是O(len1+len2)，时间复杂度是O(len1+len2)
     */
    public static Node getFirstCommonNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        //分别把两个链表的节点放入两个栈中，这样两个链表的尾节点就位于两个栈的栈顶
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        Node current = head1;
        while (current != null) {
            stack1.push(current);
            current = current.next;
        }
        current = head2;
        while (current != null) {
            stack2.push(current);
            current = current.next;
        }

        while (stack1.size() > 0 && stack2.size() > 0) {
            if (stack1.pop().data != stack2.pop().data) {
                return current;
            } else {
                current = stack1.pop();
            }
        }

        return null;
    }

    /**
     * 判断两个链表相交的第一个节点：用到快慢指针，推荐（更优解）
     *
     * 首先遍历两个链表得到它们的长度。
     * 在第二次遍历的之前，在较长的链表上走 |len1-len2| 步，
     * 接着再同时在两个链表上遍历，找到的第一个相同的节点就是它们的第一个交点。
     *
     * 这种思路的时间复杂度也是O(len1+len2)，但是我们不再需要辅助栈，因此提高了空间效率
     */
    public static Node getFirstCommonNode2(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node longHead = null;
        Node shortHead = null;
        int lengthDiff = 0;//两个链表长度的差值

        int length1 = getLength(head1);
        int length2 = getLength(head2);
        lengthDiff = Math.abs(length1 - length2);
        //找出较长的那个链表
        if (length1 > length2) {
            longHead = head1;
            shortHead = head2;
        } else {
            longHead = head2;
            shortHead = head1;
        }
        //将较长的那个链表的指针向前走length个距离
        for (int i = 0; i < lengthDiff; i++) {
            longHead = longHead.next;
        }
        //将两个链表的指针同时向前移动
        while (longHead != null && shortHead != null) {
            if (longHead.data == shortHead.data) {//第一个相同的节点就是相交的第一个节点
                return longHead;
            }

            longHead = longHead.next;
            shortHead = shortHead.next;
        }

        return null;
    }
}
