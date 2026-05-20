[README.md](https://github.com/user-attachments/files/28049479/README.md)
# Data-Structure

> 用 Java 从零手写数据结构与算法，持续学习中，后续会持续更新 🚀

本项目是我自学数据结构的代码记录，所有结构均用 Java 手动实现，不依赖标准库中现成的数据结构类。按照线性表 → 栈 → 队列 → 树 → 图 → 查找排序 → 动态规划的顺序递进学习。

---

## 目前已实现

### 线性表

| 类名 | 说明 |
|------|------|
| `MyArrayList` | 顺序表，数组实现，O(1) 随机访问 |
| `MyLinkedList` | 单链表，带头结点 |
| `MyCircularLinkedList` | 循环单链表 |
| `MyDoubleLinkedList` | 双向链表 |
| `MyDoubleCircularLinkedList` | 双向循环链表，O(1) 尾插 |

### 栈

| 类名 | 说明 |
|------|------|
| `MyArrayStack` | 顺序栈，数组 + top 指针 |
| `MyLinkedStack` | 链栈，栈顶在链表头部 |

### 队列

| 类名 | 说明 |
|------|------|
| `MyCircularQueue` | 循环队列，模运算实现 |
| `MyLinkQueue` | 链队列，front + rear 双指针 |
| `MyDeque` | 链式双端队列，双向链表实现 |
| `MyArrayDeque` | 顺序双端队列，循环数组实现 |

### 树

| 类名 | 说明 |
|------|------|
| `ParentTree` | 双亲表示法 |
| `ChildTree` | 孩子表示法 |
| `ChildSiblingTree` | 孩子兄弟表示法（树转二叉树） |
| `BinaryTree` | 二叉链表存储 |
| `SequentialBinaryTree` | 顺序存储二叉树 |
| `BinaryTreeTraversal` | 先序/中序/后序/层序遍历（递归） |
| `CompleteBinaryTree` | 迭代遍历（中序/后序/层序） |
| `ThreadedBinaryTree` | 中序线索二叉树 |
| `BinarySearchTree` | 二叉搜索树，含递归/非递归插入删除 |
| `AVLTree` | AVL 平衡二叉树，含插入/删除/四种旋转 |
| `HuffmanTree` | 哈夫曼树，WPL 最小构造 |
| `DSU` | 并查集，路径压缩 + 按秩合并 |

---

## 项目结构

```
Data-Structure/
└── src/
    ├── linearlist/
    │   ├── MyArrayList.java
    │   ├── MyCircularLinkedList.java
    │   ├── MyDoubleCircularLinkedList.java
    │   ├── MyDoubleLinkedList.java
    │   └── MyLinkedList.java
    ├── queue/
    │   ├── MyArrayDeque.java
    │   ├── MyCircularQueue.java
    │   ├── MyDeque.java
    │   └── MyLinkQueue.java
    ├── stack/
    │   ├── MyArrayStack.java
    │   └── MyLinkedStack.java
    └── tree/
        ├── AVLTree.java
        ├── BinarySearchTree.java
        ├── BinaryTree.java
        ├── BinaryTreeTraversal.java
        ├── ChildSiblingTree.java
        ├── ChildTree.java
        ├── CompleteBinaryTree.java
        ├── DSU.java
        ├── ParentTree.java
        ├── SequentialBinaryTree.java
        └── ThreadedBinaryTree.java
```

---

## 开发环境

- Java 17
- IntelliJ IDEA
- Arch Linux / Windows

---

## 持续更新中

目前仍在学习中，后续计划陆续补充：

**图**
- [ ] 图的存储（邻接矩阵 / 邻接表）
- [ ] 图的遍历（BFS / DFS）
- [ ] 最短路径（Dijkstra / Floyd）
- [ ] 最小生成树（Prim / Kruskal）
- [ ] 拓扑排序

**查找与排序**
- [ ] 查找算法（二分查找 / 插值查找 / 哈希查找）
- [ ] 排序算法（冒泡 / 插入 / 希尔 / 归并 / 快排 / 堆排序）
- [ ] 堆 / 优先队列

**高级树结构**
- [ ] B 树
- [ ] B+ 树
- [ ] 红黑树

**动态规划**
- [ ] 基础 DP（斐波那契 / 最长公共子序列）
- [ ] 0-1 背包问题
- [ ] 完全背包 / 多重背包

如果你也在自学数据结构，欢迎参考，也欢迎 Issue 和 PR。
