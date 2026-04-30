package queue;

/**
 * 链队列类：利用 Java 的引用机制实现
 */
public class MyLinkQueue {

    /**
     * 节点内部类：对应 C 中的 QNode
     */
    private class QNode {
        int data;
        QNode next;

        QNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private QNode front; // 队首指针，指向头结点
    private QNode rear;  // 队尾指针，指向最后一个有效节点

    /**
     * 构造函数：对应 C 中的 InitQueue
     * Java 特色：构造时直接创建哨兵头结点
     */
    public MyLinkQueue() {
        QNode sentinel = new QNode(0); // 创建头结点（不存数据）
        this.front = sentinel;
        this.rear = sentinel;
    }

    /**
     * 入队操作
     * @param k 入队的数据
     */
    public void enQueue(int k) {
        QNode s = new QNode(k); // 1. 创建新节点
        rear.next = s;          // 2. 将原尾节点指向新节点
        rear = s;               // 3. 更新尾指针
    }

    /**
     * 判空操作
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 出队操作
     */
    public void deQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，无法出队");
            return;
        }

        // 1. 找到首元节点（头结点的下一个）
        QNode p = front.next;
        System.out.println(p.data + " 出队");

        // 2. 头结点跳过 p，指向 p 的下一个
        front.next = p.next;

        // 3. 特殊情况处理：如果删掉的是最后一个节点
        // 在 Java 中，如果 front.next 变为了 null，说明队列空了
        if (front.next == null) {
            rear = front; // 让尾指针重新指回头结点
        }

        // p 失去引用后，JVM GC 会自动回收内存，无需 free(p)
    }

    /**
     * 查看队首元素
     */
    public Integer peek() {
        if (isEmpty()) return null;
        return front.next.data;
    }

    // --- 测试代码 ---
    public static void main(String[] args) {
        MyLinkQueue q = new MyLinkQueue();

        q.enQueue(1);
        q.enQueue(2);
        q.enQueue(3);

        q.deQueue(); // 1 出队
        q.deQueue(); // 2 出队

        System.out.println("当前队首: " + (q.isEmpty() ? "空" : q.peek()));

        q.deQueue(); // 3 出队
        q.deQueue(); // 提示队列为空
    }
}
