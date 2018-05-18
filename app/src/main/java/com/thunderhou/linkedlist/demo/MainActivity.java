package com.thunderhou.linkedlist.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.thunderhou.linkedlist.demo.bean.Node;

/**
 本文包含链表的以下内容：
 　　1、单链表的创建和遍历
 　　2、求单链表中节点的个数
 　　3、查找单链表中的倒数第k个结点（剑指offer，题15）
 　　4、查找单链表中的中间结点
 　　5、合并两个有序的单链表，合并之后的链表依然有序【出现频率高】（剑指offer，题17）
 　　6、单链表的反转【出现频率最高】（剑指offer，题16）
 　　7、从尾到头打印单链表（剑指offer，题5）
 　　8、判断单链表是否有环
 　　9、取出有环链表中，环的长度
 　　10、单链表中，取出环的起始点（剑指offer，题56）。本题需利用上面的第8题和第9题。
 　　11、判断两个单链表相交的第一个交点（剑指offer，题37）
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int LINKLIST_LENGTH = 10;
    private LinkList linkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn01 = findView(R.id.btn01);
        Button btn02 = findView(R.id.btn02);
        Button btn03 = findView(R.id.btn03);
        Button btn04 = findView(R.id.btn04);
        Button btn05 = findView(R.id.btn05);
        Button btn06 = findView(R.id.btn06);
        Button btn07 = findView(R.id.btn07);
        Button btn08 = findView(R.id.btn08);
        Button btn09 = findView(R.id.btn09);
        Button btn10 = findView(R.id.btn10);
        Button btn11 = findView(R.id.btn11);
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
        btn04.setOnClickListener(this);
        btn05.setOnClickListener(this);
        btn06.setOnClickListener(this);
        btn07.setOnClickListener(this);
        btn08.setOnClickListener(this);
        btn09.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);

        getLinkList();
    }

    private <T extends View> T findView(int resId) {
        return (T) findViewById(resId);
    }

    private LinkList getLinkList() {
        if (linkList == null) {
            linkList = new LinkList();
            for (int i = 0; i < LINKLIST_LENGTH; i++) {
                linkList.add(i);
            }
        }
        return linkList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                //从head节点开始遍历输出
                linkList.print(linkList.head);
                break;
            case R.id.btn02:
                System.out.println("链表长度:"+LinkListUtil.getLength(linkList.head));
                break;
            case R.id.btn03:
                int index = 3;
                Node lastNode = LinkListUtil.findLastNodeSimple(linkList.head, index);
                lastNode = LinkListUtil.findLastNodeFinal(linkList.head, index);
                if (lastNode != null) {
                    System.out.println("查找单链表中的倒数第"+index+"个结点为:" + lastNode.data);
                }
                break;
            case R.id.btn04:
                Node midNode = LinkListUtil.findMidNode(linkList.head);
                if (midNode != null) {
                    System.out.println("单链表中的中间结点为:"+midNode.data);
                }
                break;
            case R.id.btn05:
                LinkList list05_1 = new LinkList();
                LinkList list05_2 = new LinkList();
                //向LinkList中添加数据
                for (int i = 0; i < 4; i++) {
                    list05_1.add(i);
                }
                for (int i = 3; i < 8; i++) {
                    list05_2.add(i);
                }
                list05_1.print(list05_1.head);
                list05_2.print(list05_2.head);

                LinkList list3 = new LinkList();
                list3.head = LinkListUtil.mergeLinkList(list05_1.head, list05_2.head);
                list3.print(list3.head);
                break;
            case R.id.btn06:
                LinkList reverseList = new LinkList();
                reverseList.head = LinkListUtil.reverseList(linkList.head);
                reverseList.print(reverseList.head);
                break;
            case R.id.btn07:
                LinkListUtil.reversePrintWithSystemStack(linkList.head);
                break;
            case R.id.btn08:
                linkList.add(linkList.head);//添加这行代码有环，否则无环
                System.out.println("判断单链表是否有环: " + LinkListUtil.hasCycle(linkList.head));
                break;
            case R.id.btn09:
                LinkList list09 = new LinkList();
                Node second = null;//把第二个结点记下来
                for (int i = 0; i < 4; i++) {
                    list09.add(i);
                    if (i == 1) {
                        second = list09.current;
                    }
                }
                list09.add(second);

//                list09.add(list09.head);
                Node cycleNode = LinkListUtil.hasCycle2(list09.head);
                if (cycleNode != null) {
                    int cycleLength = LinkListUtil.getCycleLength(list09.head, cycleNode);
                    System.out.println("环的长度为: " + cycleLength);
                }
                break;
            case R.id.btn10:
                LinkList list10 = new LinkList();
                Node third = null;
                for (int i = 1; i < 7; i++) {
                    list10.add(i);
                    if (i == 3) {
                        third = list10.current;
                    }
                }
                list10.add(third);
                Node cycleNode1 = LinkListUtil.hasCycle2(list10.head);
                if (cycleNode1 != null) {
                    int cycleLength1 = LinkListUtil.getCycleLength(list10.head, cycleNode1);
                    Node cycleStartNode = LinkListUtil.getCycleStart(list10.head, cycleLength1);
                    if (cycleStartNode != null) {
                        System.out.println("环的起始点是: " + cycleStartNode.data);
                    }
                }
                break;
            case R.id.btn11:
                LinkList list11_1 = new LinkList();
                LinkList list11_2 = new LinkList();
                list11_1.add(1).add(2).add(3).add(6).add(7);
                list11_2.add(4).add(5).add(6).add(7);
                Node firstCommonNode = LinkListUtil.getFirstCommonNode2(list11_1.head, list11_2.head);
                if (firstCommonNode != null) {
                    System.out.println("两个单链表相交的第一个交点为: " + firstCommonNode.data);
                }
                break;
            default:
                break;
        }
    }
}
