package queue;

/**
 * 双端队列类：体现 Java 的引用管理特色
 */
public class MyDeque {

    /**
     * 节点内部类：对应 C 中的 Node
     */
    private class Node {
        int data;
        Node pre;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node l; // 左端指针
    private Node r; // 右端指针

    /**
     * 构造函数：对应 C 中的 InitDeque
     * 初始化时创建一个空节点，l 和 r 同时指向它
     */
    public MyDeque() {
        Node s = new Node(0);
        s.pre = null;
        s.next = null;
        this.l = s;
        this.r = s;
    }

    /**
     * 左端插入：对应 C 中的 Linsert
     * 逻辑：数据存入当前 l，然后在 l 前面新增一个哨兵
     */
    public void lInsert(int k) {
        // 1. 把数据放在当前 l 指向的结点中
        l.data = k;
        // 2. 在 l 前面插入一个不放数据的空节点
        Node s = new Node(0);
        s.next = l;
        s.pre = null;
        l.pre = s;
        // 3. 更新 l 指针
        l = s;
    }

    /**
     * 右端插入：对应 C 中的 Rinsert
     * 逻辑：在当前 r 后面新增节点，r 移向新节点并存入数据
     */
    public void rInsert(int k) {
        Node s = new Node(0);
        s.next = null;
        s.pre = r;
        r.next = s;
        // 更新 r 指针
        r = s;
        // 数据放到新的 r 中
        r.data = k;
    }

    /**
     * 左端出队：对应 C 中的 Ldelet
     */
    public void lDelete() {
        // 判空：l 和 r 指向同一个哨兵时说明没数据
        if (l == r) {
            System.out.println("队空，无法从左端出队");
            return;
        }
        // l.next 才是真正的左端第一个数据节点
        Node p = l.next;
        System.out.println(p.data + " 左端出队");

        l.next = p.next;
        if (p == r) {
            // 如果出队的是最后一个节点，重置 r 为 l
            r = l;
        }
        if (p.next != null) {
            p.next.pre = l;
        }
        // Java 自动回收 p
    }

    /**
     * 右端出队：对应 C 中的 Rdelet
     */
    public void rDelete() {
        if (l == r) {
            System.out.println("队空，无法从右端出队");
            return;
        }
        // r 是真正的右端
        Node p = r;
        System.out.println(p.data + " 右端出队");
        r = r.pre;
        r.next = null;
        // Java 自动回收 p
    }

    // --- 测试代码 ---
    public static void main(String[] args) {
        MyDeque deque = new MyDeque();

        // 左插入 1, 2, 3 -> 队列逻辑顺序应该是 [3, 2, 1]
        deque.lInsert(1);
        deque.lInsert(2);
        deque.lInsert(3);

        // 右插入 4, 5, 6 -> 队列逻辑顺序应该是 [3, 2, 1, 4, 5, 6]
        deque.rInsert(4);
        deque.rInsert(5);
        deque.rInsert(6);

        // 连续出队测试
        deque.lDelete(); // 3 出队
        deque.lDelete(); // 2 出队
        deque.lDelete(); // 1 出队
        deque.lDelete(); // 4 出队
        deque.lDelete(); // 5 出队
        deque.lDelete(); // 6 出队
        deque.lDelete(); // 队空提示
    }
}
