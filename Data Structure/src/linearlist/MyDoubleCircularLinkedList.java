package linearlist;

/**
 * 双向循环链表类
 */
public class MyDoubleCircularLinkedList {

    /**
     * 节点类：包含前后两个方向的引用
     */
    private class Node {
        int data;
        Node next;
        Node prev;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head; // 头结点 (哨兵位)

    /**
     * 初始化：对应 C 中的 InitLink
     * Java 特色：让 head 的前后都指向自己，形成一个闭环
     */
    public MyDoubleCircularLinkedList() {
        head = new Node(0);
        head.next = head;
        head.prev = head;
    }

    /**
     * 头插法：在头结点后面立即插入新数据
     */
    public void headInsert(int k) {
        Node s = new Node(k);
        // 四步走逻辑（Java 引用版）
        s.next = head.next;
        s.prev = head;
        head.next.prev = s;
        head.next = s;
    }

    /**
     * 尾插法：在末尾添加数据 k
     * Java 特色：利用双向循环特性，尾结点就是 head.prev，无需遍历查找！
     */
    public void rearInsert(int k) {
        Node s = new Node(k);
        Node last = head.prev; // 直接拿到尾结点

        s.next = head;
        s.prev = last;
        last.next = s;
        head.prev = s;
    }

    /**
     * 查找数据 k 所在的节点
     */
    private Node find(int k) {
        Node p = head.next;
        while (p != head && p.data != k) {
            p = p.next;
        }
        return p;
    }

    /**
     * 指定位置插入：在数据 k 之后插入 k1
     */
    public void insert(int k, int k1) {
        Node p = find(k);
        if (p == head) {
            System.out.println(k + " 不存在，无法插入");
            return;
        }
        Node s = new Node(k1);
        s.next = p.next;
        s.prev = p;
        p.next.prev = s;
        p.next = s;
    }

    /**
     * 删除操作：双向循环链表的删除最简洁
     */
    public void delete(int k) {
        Node p = find(k);
        if (p == head) {
            System.out.println(k + " 不存在，无法删除");
            return;
        }
        // 核心逻辑：前后节点“跨过”自己直接握手
        p.prev.next = p.next;
        p.next.prev = p.prev;
        // p 失去引用后，JVM GC 会自动处理内存
    }

    /**
     * 遍历打印
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
        MyDoubleCircularLinkedList list = new MyDoubleCircularLinkedList();

        list.headInsert(1);
        list.headInsert(2);
        list.headInsert(3);
        System.out.print("头插结果 (3 2 1): ");
        list.show();

        list.rearInsert(4);
        list.rearInsert(5);
        System.out.print("尾插结果 (3 2 1 4 5): ");
        list.show();

        list.insert(1, 10);
        list.insert(4, 7);
        System.out.print("插入 10 和 7 后: ");
        list.show();

        list.delete(100); // 测试不存在
        list.delete(1);
        list.delete(5);
        list.delete(4);
        System.out.print("删除部分后: ");
        list.show();
    }
}
