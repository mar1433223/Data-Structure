package queue;

/**
 * 循环队列类：体现 Java 的封装与模运算逻辑
 */
public class MyCircularQueue {
    private int[] data;    // 用于存放数据的数组
    private int front;     // 队首指针 (f)
    private int rear;      // 队尾指针 (r)
    private int capacity;  // 队列最大容量

    /**
     * 构造函数：对应 C 中的 InitQueue
     * @param k 设定的数组长度
     */
    public MyCircularQueue(int k) {
        this.capacity = k;
        this.data = new int[k];
        this.front = 0;
        this.rear = 0;
    }

    /**
     * 入队操作
     * Java 特色：通常会返回布尔值告知操作是否成功，或者抛出异常
     */
    public void enQueue(int k) {
        // 判满逻辑：牺牲一个空间，(rear + 1) % maxx == front
        if ((rear + 1) % capacity == front) {
            System.out.println("队满，不能入队: " + k);
            return;
        }
        data[rear] = k;
        rear = (rear + 1) % capacity; // 逻辑上形成循环
    }

    /**
     * 判空操作
     * @return 队空返回 true，否则返回 false
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 出队操作
     */
    public void deQueue() {
        if (isEmpty()) {
            System.out.println("队空，不能出队");
            return;
        }
        int x = data[front];
        front = (front + 1) % capacity; // 逻辑上向前移动
        System.out.println(x + " 出队");
    }

    /**
     * 获取队首元素（不删除）
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法查看");
        }
        return data[front];
    }

    // --- 主测试函数 ---
    public static void main(String[] args) {
        // 创建一个长度为 10 的循环队列
        MyCircularQueue q = new MyCircularQueue(10);

        q.enQueue(1);
        q.enQueue(2);
        q.enQueue(3);

        q.deQueue(); // 1 出队
        q.deQueue(); // 2 出队
        q.deQueue(); // 3 出队
        q.deQueue(); // 队空提示
    }
}
