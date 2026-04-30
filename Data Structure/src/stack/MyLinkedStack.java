package stack;

/**
 * 链栈类：基于带头结点的单链表实现
 */
public class MyLinkedStack {

    /**
     * 节点内部类：对应 C 中的 SNode
     */
    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top; // 对应 C 中的头指针/栈顶指针

    /**
     * 构造函数：对应 C 中的 InitStack
     * 初始化一个带头结点的空链表
     */
    public MyLinkedStack() {
        this.top = new Node(0); // 创建头结点（哨兵位）
        this.top.next = null;
    }

    /**
     * 入栈 (Push)：采用头插法
     * @param k 入栈的数据
     */
    public void push(int k) {
        Node s = new Node(k); // 创建新节点
        s.next = top.next;    // 1. 新节点指向原来的首元节点
        top.next = s;         // 2. 头结点指向新节点
    }

    /**
     * 判空 (IsEmpty)
     * @return 栈空返回 true
     */
    public boolean isEmpty() {
        return top.next == null;
    }

    /**
     * 出栈 (Pop)：删除首元节点
     */
    public void pop() {
        if (isEmpty()) {
            System.out.println("栈空，不能出栈");
            return;
        }
        // 逻辑删除首元节点
        // 在 Java 中，只要没有引用指向原来的首元节点，它就会被 GC 回收
        top.next = top.next.next;
    }

    /**
     * 获取栈顶数据 (GetTop)
     */
    public void getTop() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        // 栈顶数据始终在头结点的下一个节点中
        System.out.println("栈顶是: " + top.next.data);
    }

    // --- 测试代码 ---
    public static void main(String[] args) {
        MyLinkedStack s = new MyLinkedStack();

        s.push(1);
        s.push(2);
        s.push(3);

        s.pop();      // 弹出 3
        s.getTop();   // 输出 2
    }
}
