package linearlist;

/**
 * 顺序表类：体现了 Java 的封装特性
 */
public class MyArrayList {
    private int[] data;    // 存放数据的数组
    private int size;      // 当前存放在表中的元素个数（对应 C 中的 a.s）
    private int capacity;  // 数组的最大容量

    /**
     * 构造函数：对应 C 中的 InitArray
     * Java 特色：通过 new 对象时自动调用，初始化内存
     */
    public MyArrayList(int initialCapacity) {
        this.capacity = initialCapacity;
        this.data = new int[initialCapacity];
        this.size = 0;
    }

    /**
     * 在顺序表末尾添加数据
     * @param k 要添加的数据
     */
    public void add(int k) {
        if (size == capacity) {
            System.out.println("数组已满");
            return;
        }
        data[size] = k;
        size++;
    }

    /**
     * 在指定下标位置插入数据
     * @param index 下标位置
     * @param k 要插入的数据
     */
    public void insert(int index, int k) {
        // 边界检查：Java 中通常会抛出异常，这里沿用你的逻辑进行提示
        if (size == capacity) {
            System.out.println("数组已满");
            return;
        }
        if (index < 0 || index > size) {
            System.out.println("插入位置不合法");
            return;
        }

        // 1) 数据后移，为新元素腾出空间
        for (int j = size - 1; j >= index; j--) {
            data[j + 1] = data[j];
        }
        // 2) 插入新数据
        data[index] = k;
        size++;
    }

    /**
     * 查找数据 k 的下标
     * @return 如果存在返回下标，不存在返回 -1
     */
    public int find(int k) {
        for (int i = 0; i < size; i++) {
            if (data[i] == k) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除指定数据 k
     * @param k 要删除的值
     */
    public void delete(int k) {
        int index = find(k);
        if (index == -1) {
            System.out.println("该数据不存在，无法删除");
            return;
        }

        // 元素前移覆盖
        for (int j = index + 1; j < size; j++) {
            data[j - 1] = data[j];
        }
        size--;
    }

    /**
     * 打印表中的所有数据
     * Java 特色：覆盖 toString() 或者定义专门的 show 方法
     */
    public void show() {
        if (size == 0) {
            System.out.println("空表");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    // --- 主函数测试 ---
    public static void main(String[] args) {
        // 创建一个容量为 105 的顺序表对象
        MyArrayList list = new MyArrayList(105);

        list.add(3);
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.print("初始数据：");
        list.show();

        list.insert(2, 10);
        list.insert(7, 11);
        System.out.print("插入后：");
        list.show();

        list.delete(5);
        list.delete(1);
        System.out.print("删除后：");
        list.show();
    }
}
