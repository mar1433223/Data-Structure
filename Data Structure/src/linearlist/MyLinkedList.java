package linearlist;

/**
 * 链表类
 */
public class MyLinkedList {

    /**
     * 节点内部类：对应 C 中的 struct Node
     * Java 特色：内部类可以方便地访问外部类的属性
     */
    private class Node {
        int data;     // 数据域
        Node next;    // 指针域（在 Java 中是对象的引用）

        // 构造一个新节点
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head; // 头结点 (Dummy Head)

    /**
     * 初始化链表：对应 C 中的 InitLink
     * Java 特色：构造函数直接完成初始化，头结点不存储有效数据
     */
    public MyLinkedList() {
        head = new Node(0); // 创建头结点
        head.next = null;
    }

    /**
     * 头插法：在头结点之后插入数据 k
     * @param k 插入的数据
     */
    public void headInsert(int k) {
        Node s = new Node(k); // 1. 创建新节点
        s.next = head.next;   // 2. 新节点指向原来的首元节点
        head.next = s;        // 3. 头结点指向新节点
    }

    /**
     * 尾插法：在链表末尾添加数据 k
     * @param k 插入的数据
     */
    public void rearInsert(int k) {
        Node s = new Node(k); // 1. 创建新节点
        Node p = head;
        // 2. 找到尾节点
        while (p.next != null) {
            p = p.next;
        }
        // 3. 尾节点指向新节点
        p.next = s;
    }

    /**
     * 查找数据 k 所在的节点
     * @return 找到返回节点引用，否则返回 null
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
        p.next = s;
    }

    /**
     * 删除操作：删除链表中第一个值为 k 的节点
     * Java 特色：不需要手动 free(p)，GC 会自动回收不可达的对象
     */
    public void delete(int k) {
        Node pre = head;
        // 查找待删除节点的前驱节点
        while (pre.next != null && pre.next.data != k) {
            pre = pre.next;
        }

        if (pre.next == null) {
            System.out.println(k + " 不存在，无法删除");
        } else {
            // 将前驱节点的 next 指向待删除节点的 next，实现逻辑删除
            pre.next = pre.next.next;
            // 原节点在 Java 中如果没有被引用，会被垃圾回收器处理
        }
    }

    /**
     * 遍历并打印链表内容
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

    // --- 测试代码 ---
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        // 测试头插
        list.headInsert(1);
        list.headInsert(2);
        list.headInsert(3);
        System.out.print("头插法(3 2 1): ");
        list.show();

        // 测试尾插
        list.rearInsert(4);
        list.rearInsert(5);
        System.out.print("追加尾插后: ");
        list.show();

        // 测试指定位置插入
        list.insert(1, 10);
        list.insert(4, 7);
        System.out.print("指定位置插入后: ");
        list.show();

        // 测试删除
        list.delete(100); // 不存在
        list.delete(1);
        list.delete(5);
        list.delete(4);
        System.out.print("删除部分节点后: ");
        list.show();
    }
}
