package stack;

/**
 * 顺序栈类：体现 Java 的封装与状态维护
 */
public class MyArrayStack {
    private int[] data;    // 存储数据的数组
    private int top;       // 栈顶指针，指向真正栈顶数据的位置
    private int maxSize;   // 栈的最大容量

    /**
     * 构造函数：对应 C 中的 InitStack
     * Java 特色：通过 new 对象时自动初始化
     */
    public MyArrayStack(int capacity) {
        this.maxSize = capacity;
        this.data = new int[capacity];
        this.top = -1; // 初始为 -1，表示空栈
    }

    /**
     * 入栈操作 (Push)
     */
    public void push(int k) {
        // 判满逻辑：上溢出检查
        if (top == maxSize - 1) {
            System.out.println("栈满，不能入栈: " + k);
            return;
        }
        // 先移动指针，再放入数据
        data[++top] = k;
    }

    /**
     * 判空操作
     * @return 为空返回 true，否则返回 false
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 出栈操作 (Pop)
     */
    public void pop() {
        // 判空逻辑：下溢出检查
        if (isEmpty()) {
            System.out.println("栈空，不能出栈");
            return;
        }
        // 在 Java 中，逻辑上移动 top 即可，数组中的旧数据会被后续 Push 覆盖
        top--;
    }

    /**
     * 获取栈顶数据 (Peek/GetTop)
     */
    public void getTop() {
        if (isEmpty()) {
            System.out.println("栈空，没有栈顶数据");
            return;
        }
        System.out.println("栈顶是: " + data[top]);
    }

    // --- 主测试函数 ---
    public static void main(String[] args) {
        // 创建容量为 10 的栈
        MyArrayStack s = new MyArrayStack(10);

        s.push(1);
        s.push(2);
        s.push(3);

        s.pop();      // 3 出栈
        s.getTop();   // 应该是 2
    }
}