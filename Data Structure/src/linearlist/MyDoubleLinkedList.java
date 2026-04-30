package linearlist;

/**
 * 双向链表类
 */
public class MyDoubleLinkedList {

    /**
     * 节点类：增加了一个 prev 引用
     */
    private class Node {
        int data;
        Node next; // 指向后一个节点
        Node prev; // 指向前一个节点（对应 C 中的 pre）

        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head; // 头结点

    /**
     * 初始化：对应 C 中的 InitLink
     */
    public MyDoubleLinkedList() {
        head = new Node(0); // 哨兵头结点，不存实际数据
        head.next = null;
        head.prev = null;
    }

    /**
     * 头插法：在头结点后插入新数据 k
     */
    public void headInsert(int k) {
        Node s = new Node(k);

        s.next = head.next;
        if (head.next != null) {
            head.next.prev = s; // 原首元节点的前驱指向新节点
        }
        head.next = s;
        s.prev = head; // 新节点的前驱指向头结点
    }

    /**
     * 尾插法：在链表末尾添加数据 k
     */
    public void rearInsert(int k) {
        Node s = new Node(k);
        Node p = head;
        // 寻找尾节点
        while (p.next != null) {
            p = p.next;
        }
        p.next = s;
        s.prev = p;
        s.next = null;
    }

    /**
     * 查找指定数据的节点
     */
    private Node find(int k) {
        Node p = head.next;
        while (p != null && p.data != k) {
            p = p.next;
        }
        return p;
    }

    /**
     * 指定位置插入：在数据 k 之后插入 k1
     */
    public void insert(int k, int k1) {
        Node p = find(k);
        if (p == null) {
            System.out.println(k + " 不存在，无法插入");
            return;
        }

        Node s = new Node(k1);
        s.next = p.next;
        if (p.next != null) {
            p.next.prev = s;
        }
        p.next = s;
        s.prev = p;
    }

    /**
     * 删除操作：双向链表删除非常丝滑，不需要像单链表那样去找 pre 节点
     */
    public void delete(int k) {
        Node p = find(k);
        if (p == null) {
            System.out.println(k + " 不存在，无法删除");
            return;
        }

        // 修改前驱节点的 next 指向
        p.prev.next = p.next;

        // 如果不是最后一个节点，修改后继节点的 prev 指向
        if (p.next != null) {
            p.next.prev = p.prev;
        }

        // Java 自动回收 p 的内存，无需 free(p)
    }

    /**
     * 遍历打印
     */
    public void show() {
        Node p = head.next;
        if (p == null) {
            System.out.println("空链表");
            return;
        }
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    // --- 主测试函数 ---
    public static void main(String[] args) {
        MyDoubleLinkedList list = new MyDoubleLinkedList();

        list.headInsert(1);
        list.headInsert(2);
        list.headInsert(3);
        System.out.print("头插后: ");
        list.show();

        list.rearInsert(4);
        list.rearInsert(5);
        System.out.print("尾插后: ");
        list.show();

        list.insert(1, 10);
        list.insert(4, 7);
        System.out.print("指定插入后: ");
        list.show();

        list.delete(100);
        list.delete(1);
        list.delete(5);
        list.delete(4);
        System.out.print("删除操作后: ");
        list.show();
    }
}
