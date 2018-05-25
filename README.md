## 链表算法练习

### 本文包含链表的以下内容：
1. 单链表的创建和遍历
2. 求单链表中节点的个数
3. 查找单链表中的倒数第k个结点（剑指offer，题15）
4. 查找单链表中的中间结点
5. <font color=#0099ff>合并两个有序的单链表</font>，合并之后的链表依然有序【出现频率高】（剑指offer，题17）
6. <font color=#0099ff>单链表的反转</font>【出现频率最高】（剑指offer，题16）
7. 从尾到头打印单链表（剑指offer，题5）
8. 判断单链表是否有环
9. 取出有环链表中，环的长度
10. 单链表中，取出环的起始点（剑指offer，题56）。本题需利用上面的第8题和第9题。
11. 判断两个单链表相交的第一个交点（剑指offer，题37）

### 代码部分
- 5、合并两个有序的单链表，合并之后的链表依然有序：(这道题经常被各公司考察)
```
例如：
链表1：1->2->3->4
链表2：2->3->4->5

合并后：1->2->2->3->3->4->4->5

解题思路：
　　挨着比较链表1和链表2。
　　这个类似于归并排序。尤其要注意两个链表都为空、和其中一个为空的情况。只需要O(1)的空间。时间复杂度为O(max(len1,len2))

代码实现：
	//两个参数代表的是两个链表的头结点
    public Node mergeLinkList(Node head1, Node head2) {
        if (head1 == null && head2 == null) {  //如果两个链表都为空
            return null;
        }
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        Node head; //新链表的头结点
        Node current;  //current结点指向新链表

        // 一开始，我们让current结点指向head1和head2中较小的数据，得到head结点
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
                current.next = head1;  //新链表中，current指针的下一个结点对应较小的那个数据
                current = current.next; //current指针下移
                head1 = head1.next;
            } else {
                current.next = head2;
                current = current.next;
                head2 = head2.next;
            }
        }

        //合并剩余的元素
        if (head1 != null) { //说明链表2遍历完了，是空的
            current.next = head1;
        }

        if (head2 != null) { //说明链表1遍历完了，是空的
            current.next = head2;
        }

        return head;
    }
```

- 6、单链表的反转：【出现频率最高】
```
例如链表：1->2->3->4

反转之后：4->3->2->1

思路：从头到尾遍历原链表，每遍历一个结点，将其摘下放在新链表的最前端。注意链表为空和只有一个结点的情况。时间复杂度为O(n) 

代码实现：
    public Node reverseList(Node head) {
        //如果链表为空或者只有一个节点，无需反转，直接返回原链表的头结点
        if (head == null || head.next == null) {
            return head;
        }

        Node current = head;
        Node next = null; //定义当前结点的下一个结点
        Node reverseHead = null;  //反转后新链表的表头

        while (current != null) {
            next = current.next;  //暂时保存住当前结点的下一个结点，因为下一次要用

            current.next = reverseHead; //将current的下一个结点指向新链表的头结点
            reverseHead = current;  

            current = next;   // 操作结束后，current节点后移
        }

        return reverseHead;
    }
```

### 鸣谢
- [链表面试题Java实现](https://www.cnblogs.com/smyhvae/p/4782595.html)
