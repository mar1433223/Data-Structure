package linearlist;

/**
 * 循环单链表类
 */
public class MyCircularLinkedList {

    /**
     * 节点类
     */
    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head; // 头结点

    /**
     * 初始化循环链表：对应 C 中的 InitLink
     * Java 特色：构造时直接让 head.next 指向自己，形成闭环
     */
    public MyCircularLinkedList() {
        head = new Node(0); // 哨兵头结点
        head.next = head;    // 循环的关键：指向自己
    }

    /**
     * 头插法
     */
    public void headInsert(int k) {
        Node s = new Node(k);
        s.next = head.next; // s 指向原首元节点
        head.next = s;      // 头结点指向 s
    }

    /**
     * 尾插法
     */
    public void rearInsert(int k) {
        Node s = new Node(k);
        Node p = head;
        // 循环找尾：条件是 p.next 不等于头结点
        while (p.next != head) {
            p = p.next;
        }
        p.next = s;
        s.next = head; // 尾节点指向头结点
    }

    /**
     * 查找数据为 k 的节点
     */
    private Node find(int k) {
        Node p = head.next;
        // 注意循环终止条件：回到 head 说明找了一圈没找到
        while (p != head && p.data != k) {
            p = p.next;
        }
        return p;
    }

    /**
     * 指定插入：在 k 之后插入 k1
     */
    public void insert(int k, int k1) {
        Node p = find(k);
        if (p == head) {
            System.out.println(k + "不存在，无法插入");
            return;
        }
        Node s = new Node(k1);
        s.next = p.next;
        p.next = s;
    }

    /**
     * 删除操作
     */
    public void delete(int k) {
        Node pre = head;
        // 查找待删节点的前驱
        while (pre.next != head && pre.next.data != k) {
            pre = pre.next;
        }

        if (pre.next == head) {
            System.out.println(k + "不存在，无法删除");
        } else {
            // 逻辑删除，JVM 会处理内存
            pre.next = pre.next.next;
        }
    }

    /**
     * 遍历显示
     */
    public void show() {
        Node p = head.next;
        if (p == head) {
            System.out.println("空链表");
            return;
        }
        while (p != head) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    // --- 主函数测试 ---
    public static void main(String[] args) {
        MyCircularLinkedList list = new MyCircularLinkedList();

        list.headInsert(1);
        list.headInsert(2);
        list.headInsert(3);
        System.out.print("循环链表头插: ");
        list.show();

        list.rearInsert(4);
        list.rearInsert(5);
        System.out.print("循环链表尾插: ");
        list.show();

        list.insert(1, 10);
        System.out.print("插入 10 后: ");
        list.show();

        list.delete(1);
        list.delete(5);
        System.out.print("删除 1 和 5 后: ");
        list.show();
    }
}