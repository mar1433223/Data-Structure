package queue;

/**
 * 循环数组双端队列
 * 特色：使用 sum 记录个数，简化判满判空逻辑
 */
public class MyArrayDeque {
    private int[] data;   // 数组
    private int l;        // 左端指针：指向真正的左端元素
    private int r;        // 右端指针：指向真正右端元素的后一个位置
    private int sum;      // 队列中数据的个数
    private int maxSize;  // 最大容量

    /**
     * 构造函数：对应 C 中的 InitDeque
     */
    public MyArrayDeque(int size) {
        this.maxSize = size;
        this.data = new int[size];
        this.l = 0;
        this.r = 0;
        this.sum = 0;
    }

    /**
     * 左端入队：先动指针，后入队
     */
    public void lInsert(int k) {
        if (sum == maxSize) {
            System.out.println("队满，无法入队: " + k);
            return;
        }
        // 关键逻辑：向左移动指针，利用 (l - 1 + maxSize) % maxSize 实现循环回绕
        l = (l - 1 + maxSize) % maxSize;
        data[l] = k;
        sum++;
    }

    /**
     * 左端出队
     */
    public void lDelete() {
        if (sum == 0) {
            System.out.println("队空，无法出队");
            return;
        }
        int k = data[l];
        System.out.println(k + " 左端出队");
        // 向右移动左指针
        l = (l + 1) % maxSize;
        sum--;
    }

    /**
     * 右端入队：先放数据，后动指针
     */
    public void rInsert(int k) {
        if (sum == maxSize) {
            System.out.println("队满，无法入队: " + k);
            return;
        }
        data[r] = k;
        // 向右移动右指针
        r = (r + 1) % maxSize;
        sum++;
    }

    /**
     * 右端出队：先动指针，后拿数据
     */
    public void rDelete() {
        if (sum == 0) {
            System.out.println("队空，无法出队");
            return;
        }
        // 向左移动右指针回到最后一个元素位置
        r = (r - 1 + maxSize) % maxSize;
        int k = data[r];
        System.out.println(k + " 右端出队");
        sum--;
    }

    /**
     * 获取当前队列大小
     */
    public int size() {
        return this.sum;
    }

    // --- 测试代码 ---
    public static void main(String[] args) {
        MyArrayDeque q = new MyArrayDeque(10);

        q.lInsert(1);
        q.lInsert(2);
        q.lInsert(3); // 此时顺序 [3, 2, 1]

        q.rInsert(4);
        q.rInsert(5);
        q.rInsert(6); // 此时顺序 [3, 2, 1, 4, 5, 6]

        // 连续右端出队测试
        for (int i = 0; i < 7; i++) {
            q.rDelete();
        }
    }
}
