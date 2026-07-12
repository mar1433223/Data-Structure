# 数据结构复习资料（Java 实现）

> 基于项目 `Data-Structure`，按学习顺序从线性表 → 栈 → 队列 → 树 → 图的递进式复习文档。

---

## 目录

1. [线性表 (linearlist)](#1-线性表-linearlist)
   - 1.1 顺序表 MyArrayList
   - 1.2 单链表 MyLinkedList
   - 1.3 循环单链表 MyCircularLinkedList
   - 1.4 双向链表 MyDoubleLinkedList
   - 1.5 双向循环链表 MyDoubleCircularLinkedList
2. [栈 (stack)](#2-栈-stack)
   - 2.1 顺序栈 MyArrayStack
   - 2.2 链栈 MyLinkedStack
3. [队列 (queue)](#3-队列-queue)
   - 3.1 循环队列 MyCircularQueue
   - 3.2 链队列 MyLinkQueue
   - 3.3 链式双端队列 MyDeque
   - 3.4 顺序双端队列 MyArrayDeque
4. [树 (tree)](#4-树-tree)
   - 4.1 双亲表示法 ParentTree
   - 4.2 孩子表示法 ChildTree
   - 4.3 孩子兄弟表示法 ChildSiblingTree
   - 4.4 二叉链表存储 BinaryTree
   - 4.5 顺序存储二叉树 SequentialBinaryTree
   - 4.6 二叉树遍历 BinaryTreeTraversal
   - 4.7 完全二叉树与迭代遍历 CompleteBinaryTree
   - 4.8 线索二叉树 ThreadedBinaryTree
   - 4.9 二叉搜索树 BinarySearchTree
   - 4.10 AVL 平衡二叉树 AVLTree
   - 4.11 哈夫曼树 Huffman / Huffman1
   - 4.12 并查集 DSU
5. [图 (graph)](#5-图-graph)
   - 5.1 图的基本概念与术语
   - 5.2 邻接矩阵 AdjacencyMatrix
   - 5.3 邻接表 AdjacencyList
   - 5.4 广度优先搜索 BFS
   - 5.5 深度优先搜索 DFS
   - 5.6 十字链表 OrthogonalList
   - 5.7 多重邻接表 AdjacencyMultilist
   - 5.8 Kruskal 最小生成树
   - 5.9 Prim 最小生成树
   - 5.10 Dijkstra 单源最短路径
   - 5.11 拓扑排序 ToPo
   - 5.12 关键路径 CriticalPath
   - 5.13 Kruskal vs Prim 对比

---

## 1. 线性表 (linearlist)

线性表是最基础的数据结构，元素之间是"一对一"的线性关系。实现方式分为**顺序存储**（数组）和**链式存储**（链表）两大类。

---

### 1.1 顺序表 `MyArrayList`

**核心思想**：用一块**连续的数组**存储数据，通过下标 `O(1)` 随机访问。

**字段**：

| 字段 | 含义 |
|------|------|
| `int[] data` | 存放元素的数组 |
| `int size` | 当前实际元素个数 |
| `int capacity` | 数组最大容量 |

**关键操作**：

| 操作 | 时间复杂度 | 原理 |
|------|-----------|------|
| `add(k)` | O(1) | 直接追加到 `data[size]`，`size++` |
| `insert(index, k)` | O(n) | 从 `size-1` 到 `index` 的元素逐个后移一位，腾出空位放入新元素 |
| `find(k)` | O(n) | 遍历数组逐个比较 |
| `delete(k)` | O(n) | 先 `find` 找到下标，再从该下标后一个元素开始逐个前移覆盖 |

**优缺点**：
- 优：随机访问 O(1)，内存连续，缓存友好
- 缺：插入/删除需移动大量元素 O(n)，容量固定需预分配

---

### 1.2 单链表 `MyLinkedList`

**核心思想**：每个**节点**存储数据和指向下一个节点的**引用**，节点在内存中不必连续。使用**带头结点**（dummy head）简化边界处理。

**节点结构**：
```
[ data | next ] → [ data | next ] → [ data | next ] → null
```

**带头结点的好处**：空链表和非空链表的插入/删除操作代码统一，不需要特判"链表为空"的情况。

**关键操作**：

| 操作 | 时间复杂度 | 原理 |
|------|-----------|------|
| `headInsert(k)` | O(1) | 新节点插在 head 之后：`s.next = head.next; head.next = s` |
| `rearInsert(k)` | O(n) | 遍历到尾节点（`p.next == null`），让尾节点指向新节点 |
| `find(k)` | O(n) | 从头结点下一个开始遍历，逐一比较 data |
| `insert(k, k1)` | O(n) | 先 find(k) 定位，然后 `s.next = p.next; p.next = s` |
| `delete(k)` | O(n) | 找前驱节点 `pre`（满足 `pre.next.data == k`），执行 `pre.next = pre.next.next` |

**删除依赖前驱节点**是单链表的特征——因为只有 `next` 指针，无法从当前节点找到前一个节点。

**优缺点**：
- 优：插入/删除不需要移动元素，动态扩容无容量限制
- 缺：无法随机访问，查找 O(n)，额外存储指针

---

### 1.3 循环单链表 `MyCircularLinkedList`

**核心思想**：尾节点的 `next` 指向**头结点**（而非 `null`），形成闭环。

```
head → [ data | next ] → [ data | next ] → [ data | next ]
        ↑                                         ↓
        ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←
```

**与单链表的关键区别**：

| | 单链表 | 循环单链表 |
|--|--------|-----------|
| 尾节点 next | `null` | 指向 `head` |
| 遍历终止条件 | `p != null` | `p != head` |
| 空链表判断 | `head.next == null` | `head.next == head` |

**初始化**：`head.next = head`，自己指向自己。

---

### 1.4 双向链表 `MyDoubleLinkedList`

**核心思想**：每个节点有两个指针 `prev` 和 `next`，分别指向前驱和后继。

**删除操作不再需要找前驱**——这是双向链表最大的优势：

```java
// 单链表删除：需要遍历找 pre
pre.next = pre.next.next;

// 双向链表删除：直接通过 p.prev 拿到前驱
p.prev.next = p.next;
if (p.next != null) p.next.prev = p.prev;
```

**优缺点**：
- 优：双向遍历，删除/插入操作更简洁
- 缺：每个节点多存一个引用，维护两个指针的代码更复杂

---

### 1.5 双向循环链表 `MyDoubleCircularLinkedList`

**核心思想**：双向链表 + 循环。头结点的 `prev` 指向尾节点，尾节点的 `next` 指向头结点。

**初始状态**：`head.next = head; head.prev = head`

**尾插法 O(1)**——最大的性能优势：

```java
Node last = head.prev;  // 不需要遍历！直接拿到尾节点
s.next = head;
s.prev = last;
last.next = s;
head.prev = s;
```

**删除最简洁**——两行代码：

```java
p.prev.next = p.next;
p.next.prev = p.prev;
```

**五种链表的演变关系**：

```
单链表 → 加循环 → 循环单链表
   ↓                ↓
 加prev           加prev
   ↓                ↓
双向链表 → 加循环 → 双向循环链表
```

---

## 2. 栈 (stack)

栈是**后进先出**（LIFO，Last In First Out）的线性结构。只能在一端（栈顶）进行插入和删除。

---

### 2.1 顺序栈 `MyArrayStack`

**核心思想**：用数组 + 栈顶指针 `top` 实现。

**字段**：

| 字段 | 含义 |
|------|------|
| `int[] data` | 存储元素的数组 |
| `int top` | 栈顶指针，初始 `-1` 表示空栈 |
| `int maxSize` | 最大容量 |

**关键操作**：

| 操作 | 代码 | 说明 |
|------|------|------|
| 判空 | `top == -1` | 指针回到初始值 |
| 判满 | `top == maxSize - 1` | 指针到达数组末尾 |
| 入栈 push | `data[++top] = k` | 先移动指针，再放入数据 |
| 出栈 pop | `top--` | 逻辑删除，`top` 下移即可 |

**入栈一定是先 `++top` 再赋值**，因为初始 `top = -1`。

---

### 2.2 链栈 `MyLinkedStack`

**核心思想**：用带头结点的单链表实现，**栈顶在链表头部**（头插法入栈）。

```
head → [栈顶] → [  ] → [栈底] → null
```

**为什么栈顶在头部？** 因为单链表的头插/头删都是 O(1)，而尾插/尾删是 O(n)。

| 操作 | 对应链表操作 |
|------|-------------|
| push | 头插法 |
| pop | 删除首元节点 |
| getTop | 读取 `head.next.data` |

---

## 3. 队列 (queue)

队列是**先进先出**（FIFO，First In First Out）的线性结构。在队尾插入，在队首删除。

---

### 3.1 循环队列 `MyCircularQueue`

**核心思想**：用数组 + 两个指针 `front`（队首）和 `rear`（队尾），通过**模运算**实现循环利用数组空间。

**判空/判满策略**——**牺牲一个存储空间**来区分：

| 条件 | 含义 |
|------|------|
| `rear == front` | 队空 |
| `(rear + 1) % capacity == front` | 队满 |

**关键操作**：

| 操作 | 代码 | 说明 |
|------|------|------|
| 入队 | `data[rear] = k; rear = (rear + 1) % capacity` | 先放数据，rear 再前移 |
| 出队 | `front = (front + 1) % capacity` | front 前移即删除 |
| 队首 | `data[front]` | front 始终指向队首元素 |

**模运算 `% capacity` 是实现循环的关键**——指针走到数组末尾后回到开头。

---

### 3.2 链队列 `MyLinkQueue`

**核心思想**：用带头结点的单链表 + `front`（指向头结点）和 `rear`（指向尾节点）两个指针。

**关键操作**：

| 操作 | 原理 |
|------|------|
| 入队 | `rear.next = s; rear = s` — 尾插法 O(1) |
| 出队 | `front.next = front.next.next` — 删除首元节点 O(1) |
| 判空 | `front == rear`（都指向头结点） |

**出队的特殊情况**：当删掉队列中唯一元素时，需要同时重置 `rear = front`。

---

### 3.3 链式双端队列 `MyDeque`

**核心思想**：左右两端都可以入队和出队，用**双向链表**实现，每个操作都是 O(1)。

**判空**：`l == r`（两个指针指向同一个哨兵节点说明没有数据）。

---

### 3.4 顺序双端队列 `MyArrayDeque`

**核心思想**：用**循环数组** + `sum` 字段（元素个数）简化判空判满。

**核心操作**（均 O(1)）：

| 操作 | 说明 |
|------|------|
| `lInsert(k)` | `l = (l - 1 + maxSize) % maxSize; data[l] = k` |
| `rInsert(k)` | `data[r] = k; r = (r + 1) % maxSize` |
| `lDelete()` | `l = (l + 1) % maxSize` |
| `rDelete()` | `r = (r - 1 + maxSize) % maxSize` |

**注意 `(x ± 1 + maxSize) % maxSize` 的写法**——处理负数的模运算，确保指针在 `[0, maxSize-1]` 范围内循环。

---

## 4. 树 (tree)

树是**一对多**的层次结构，是数据结构的核心难点。

学习路线：**树的存储** → **二叉树的遍历** → **二叉搜索树** → **AVL 平衡树** → **哈夫曼树** → **并查集**

**重要性质速查**：

| 性质 | 结论 |
|------|------|
| $n_0 = n_2 + 1$ | 叶子数 = 度为2的节点数 + 1（从边的角度推导） |
| 完全二叉树高度 | $\lfloor \log_2 n \rfloor + 1$ |
| n 个节点的二叉树空指针数 | $n + 1$ |
| AVL 插入最多旋转次数 | 1次（单旋或双旋） |
| AVL 删除最多旋转次数 | $O(\log n)$ 次 |

---

### 4.1 双亲表示法 `ParentTree`

**核心思想**：用数组存储节点，每个节点记录其**父节点的下标**。

```
节点:  [A -1]  [B 0]  [C 0]  [D 1]  [E 2]
下标:   0       1      2      3      4
parent: -1(根)  父=A   父=A   父=B   父=C
```

**优点**：找父亲 O(1)。**缺点**：找孩子需要全表扫描 O(n)。

**适用场景**：并查集、需要频繁查找根/父亲的场景。

---

### 4.2 孩子表示法 `ChildTree`

**核心思想**：数组存节点 + 每个节点挂一个**孩子链表**（存储所有孩子的下标）。

**节点结构**：
- `TreeNode`：`data` + `firstChild`（孩子链表头指针）
- `SonNode`：`sonIndex`（孩子在数组中的下标）+ `next`

**优点**：找孩子方便。**缺点**：找父亲需要遍历所有节点的孩子链表 O(n)。

---

### 4.3 孩子兄弟表示法 `ChildSiblingTree`

**核心思想**：用**二叉链表**存储普通树——左指针指向**第一个孩子**，右指针指向**右侧兄弟**。

```
        A                     A
     /  |  \       →         /
    B   C   D               B → C → D
   / \                     /
  E   F                   E → F
```

**节点结构**：`data` + `firstChild`（长子）+ `nextSibling`（右兄弟）

**这是将任意树转换为二叉树的标准方法**，也是最重要的一种树的存储方式。

**查找节点的递归逻辑**（三个方向搜索）：先查当前节点 → 递归查 `firstChild` → 递归查 `nextSibling`

---

### 4.4 二叉链表存储 `BinaryTree`

**核心思想**：二叉树的标准存储——每个节点有 `data`、左孩子引用 `l`、右孩子引用 `r`。

**查找**通过**先序递归**实现（根 → 左 → 右）：

```java
if (node == null || node.data == target) return node;
BTNode ans = find(node.l, target);
if (ans != null) return ans;
return find(node.r, target);
```

**插入**：先 `find` 找父节点，再根据 `flag`（0 左 / 1 右）挂载新节点。

---

### 4.5 顺序存储二叉树 `SequentialBinaryTree`

**核心思想**：用一维数组存储二叉树，利用下标关系代替指针（根在下标 1）。

| 关系 | 公式 |
|------|------|
| 左孩子 | `2 * i` |
| 右孩子 | `2 * i + 1` |
| 父亲 | `i / 2`（整除） |

**优点**：不需要存储指针，适合**完全二叉树**。**缺点**：非完全二叉树浪费大量空间。

---

### 4.6 二叉树遍历 `BinaryTreeTraversal`

**四种遍历方式**：

| 遍历方式 | 访问顺序 | 递归代码模式 |
|----------|----------|-------------|
| 先序 | **根** → 左 → 右 | visit → recurse(L) → recurse(R) |
| 中序 | 左 → **根** → 右 | recurse(L) → visit → recurse(R) |
| 后序 | 左 → 右 → **根** | recurse(L) → recurse(R) → visit |
| 层序 | 逐层从左到右 | 用队列 BFS |

**记忆口诀**：先/中/后说的是**根节点**在第几个被访问。

**层序遍历（BFS）**用 Java 内置队列：

```java
Queue<BTNode> queue = new LinkedList<>();
queue.offer(root);
while (!queue.isEmpty()) {
    BTNode curr = queue.poll();
    // 访问 curr
    if (curr.l != null) queue.offer(curr.l);
    if (curr.r != null) queue.offer(curr.r);
}
```

---

### 4.7 完全二叉树与迭代遍历 `CompleteBinaryTree`

**完全二叉树定义**：除最后一层外，每层都是满的，且最后一层节点从左到右连续排列。

**迭代中序（左-根-右）**：

```java
BTNode p = root;
while (p != null || !stack.isEmpty()) {
    if (p != null) {
        stack.push(p);
        p = p.l;           // 一路向左
    } else {
        p = stack.pop();   // 弹出访问
        visit(p);
        p = p.r;           // 转向右子树
    }
}
```

**迭代后序（左-右-根）**——需要 `pre` 指针记录上次访问的节点，判断右子树是否已访问过：

```java
if (p.r != null && p.r != pre) {
    p = p.r;       // 右子树还没访问，转向右子树
} else {
    p = stack.pop();
    visit(p);
    pre = p;
    p = null;      // 关键：置 null 防止重复压栈
}
```

---

### 4.8 线索二叉树 `ThreadedBinaryTree`

**要解决的问题**：二叉链表中有 `n+1` 个空指针域，线索二叉树**利用这些空指针**存储前驱/后继信息。

**核心字段**：

| 字段 | 含义 |
|------|------|
| `lflag = 0` | 左指针指向左孩子 |
| `lflag = 1` | 左指针是**前驱线索** |
| `rflag = 0` | 右指针指向右孩子 |
| `rflag = 1` | 右指针是**后继线索** |

**中序线索化**核心逻辑：

```java
if (node.l == null) { node.l = pre; node.lflag = 1; }
if (pre != null && pre.r == null) { pre.r = node; pre.rflag = 1; }
pre = node;
```

**找前驱/后继**：

| 条件 | 结论 |
|------|------|
| `lflag == 1` | 前驱就是 `node.l` |
| `lflag == 0` | 前驱是**左子树最右边的节点** |
| `rflag == 1` | 后继就是 `node.r` |
| `rflag == 0` | 后继是**右子树最左边的节点** |

---

### 4.9 二叉搜索树 `BinarySearchTree`

**定义**：对任意节点，**左子树所有值 < 根 < 右子树所有值**。中序遍历结果一定是**升序**的。

**查找**：等于找到，小于去左子树，大于去右子树。

**插入**（递归版）：
```java
if (node == null) return new BSTNode(x);
if (x < node.data) node.l = insertRecursive(node.l, x);
else if (x > node.data) node.r = insertRecursive(node.r, x);
return node;
```

**删除**——三种情况：

| 情况 | 做法 |
|------|------|
| 叶子节点 | 直接删除，返回 null |
| 单孩子 | 让孩子顶替自己 |
| 两个孩子 | 找**后继**（右子树最小值）替换自己，然后递归删除后继 |

---

### 4.10 AVL 平衡二叉树 `AVLTree`

**定义**：对任意节点，**左右子树高度差不超过 1**。通过**旋转**操作在插入/删除后恢复平衡。

**平衡因子 bf**：`bf = height(左子树) - height(右子树)`，AVL 树要求每个节点 bf ∈ {-1, 0, +1}。

---

#### 四种旋转判定

| 类型 | bf(A) | bf(B) | 操作 |
|------|-------|-------|------|
| LL | +2 | +1 | 右旋 A |
| RR | −2 | −1 | 左旋 A |
| LR | +2 | −1 | 先左旋 B，再右旋 A |
| RL | −2 | +1 | 先右旋 B，再左旋 A |

**口诀**：同侧（LL/RR）单旋，异侧（LR/RL）双旋。

---

#### LL 右旋图解

```
     A (bf=+2)              B
    / \                   /   \
   B   T4      →         C     A
  / \                         / \
 C   T2                      T2  T4
```

**两步指针变化**（顺序不能反）：
```java
a.left = b.right;   // 第一步：B 的右子树移交给 A（先救住引用）
b.right = a;        // 第二步：A 降为 B 的右子
```

顺序不能反：若先执行 `b.right = a`，`b.right` 被覆盖，原来 B 的右子树引用永久丢失。

---

#### 旋转代码

```java
private Node rotateRight(Node a) {
    Node b = a.left;
    a.left = b.right;
    b.right = a;
    // 先更新 a（b 的高度依赖 a）
    a.height = Math.max(height(a.left), height(a.right)) + 1;
    b.height = Math.max(height(b.left), height(b.right)) + 1;
    return b;
}

private Node rotateLeft(Node a) {
    Node b = a.right;
    a.right = b.left;
    b.left = a;
    a.height = Math.max(height(a.left), height(a.right)) + 1;
    b.height = Math.max(height(b.left), height(b.right)) + 1;
    return b;
}
```

LR 和 RL 直接组合单旋，无需单独实现：

```java
// LR：先左旋 B，再右旋 A
private Node balance(Node a) {
    if (bf(a) == 2) {
        if (bf(a.left) == -1) a.left = rotateLeft(a.left);
        return rotateRight(a);
    }
    if (bf(a) == -2) {
        if (bf(a.right) == 1) a.right = rotateRight(a.right);
        return rotateLeft(a);
    }
    return a;
}
```

---

#### 插入 vs 删除的关键区别

| | 插入 | 删除 |
|--|------|------|
| 子树高度变化 | 变高 | 变矮 |
| 旋转后高度 | 恢复到插入前（不变） | 比删除前还低 1 |
| 向上传播 | 旋转一次即停止 | 可能一路传播到根 |
| 最多旋转次数 | 1次 | O(log n) 次 |
| 判断旋转类型 | 用插入值 x 与子节点比较 | 用子树高度比较 |

**删除时判断旋转类型**：

```java
// 不能用 x 来判断，改用高度
if (get_H(right.right) >= get_H(right.left)) → RR
else → RL
```

**删除独有的情况：bf(B) = 0**

插入时 bf(B) 不会为 0，删除时会出现。此时单旋后整体高度**不变**，停止向上传播。

---

#### 删除完整递归结构

```java
private Node delete(Node node, int val) {
    if (node == null) return null;
    if (val < node.val) {
        node.left = delete(node.left, val);
    } else if (val > node.val) {
        node.right = delete(node.right, val);
    } else {
        if (node.left == null) return node.right;
        if (node.right == null) return node.left;
        // 找中序后继替代
        Node successor = getMin(node.right);
        node.val = successor.val;
        node.right = delete(node.right, successor.val);
    }
    node.height = Math.max(height(node.left), height(node.right)) + 1;
    return balance(node);  // 递归回溯时每层都检查平衡
}
```

**递归天然实现回溯**：找到节点删完后，沿递归调用链一层层往上返回，每层都执行最后两行——更新高度、检查平衡。

---

### 4.11 哈夫曼树 HuffmanTree

**定义**：给定一组带权叶子节点，构造一棵**带权路径长度（WPL）最小**的二叉树。

$$WPL = \sum w_i \times d_i$$

$w_i$ 是叶子权值，$d_i$ 是叶子到根的深度。权值越大的叶子离根越近。

---

#### 构造过程（贪心）

1. 把所有节点放入**最小优先队列**
2. 每次取出两个最小权值的节点，合并为一个新节点（新节点权值 = 两者之和）
3. 把新节点放回队列
4. 重复直到队列只剩一个节点，即为根

**示例**：权值 {1, 2, 3, 4, 5}

```
初始: {1, 2, 3, 4, 5}
第一步: 取 1+2=3 → {3, 3, 4, 5}
第二步: 取 3+3=6 → {4, 5, 6}
第三步: 取 4+5=9 → {6, 9}
第四步: 取 6+9=15 → 完成
```

最终 WPL = 1×3 + 2×3 + 3×2 + 4×2 + 5×2 = 33

---

#### 哈夫曼编码

**核心思想**：出现频率高的字符用短编码，频率低的用长编码，整体编码总长度最短。

**编码规则**：从根到叶子，向左走记 0，向右走记 1，路径即为编码。

**两个关键性质**：
- **前缀码**：任何字符的编码都不是另一个的前缀，解码无歧义
- **最优性**：哈夫曼编码产生的总编码长度在所有前缀码方案里最短

**实际应用**：ZIP/GZIP 压缩、JPEG 图片压缩、MP3 音频压缩。

---

#### 哈夫曼树 — 数组实现（Huffman1）

**核心思想**：不建二叉链表，而是用**数组**存放所有节点，通过 `father` / `left` / `right` **下标**代替指针引用。

**节点结构**：

```java
class Node {
    int weight;   // 权值
    int father;   // 父节点下标，-1 表示无父
    int left;     // 左孩子下标，-1 表示无左孩子
    int right;    // 右孩子下标，-1 表示无右孩子
}
```

**构造过程**（`create` 方法）：

1. 前 n 个位置存放原始叶子节点（`father = -1`）
2. 从第 n 个位置开始，每次找两个 `father == -1` 中**最小和次小**的节点
3. 合并为新节点放在当前位置，设置左右孩子和父指针
4. 重复直到得到根节点

```java
// 总共 2n-1 个节点：n 个叶子 + (n-1) 次合并产生的新节点
int m = 2 * n - 1;
Node[] tree = new Node[m];
// 初始化前 n 个叶子节点...
for (int i = n; i < m; i++) {
    int[] min = find(tree, i - 1);  // 在 [0, i-1] 范围找最小两个
    tree[i] = new Node(tree[s1].weight + tree[s2].weight, s1, s2);
    tree[s1].father = tree[s2].father = i;
}
```

**编码生成**（`createCode` 方法）——从叶子往上走到根：

```java
for (int i = 0; i < n; i++) {
    int p = i;
    while (tree[p].father != -1) {
        if (tree[tree[p].father].left == p) code[i] = "0" + code[i];
        else code[i] = "1" + code[i];
        p = tree[p].father;
    }
}
```

**与链表版本（Huffman）的对比**：

| | Huffman（链表版） | Huffman1（数组版） |
|--|------------------|-------------------|
| 节点存储 | 对象引用（`Node left, right`） | 下标索引（`int left, right`） |
| 合并 | `PriorityQueue` 自动排序 | 手动遍 历找最小两个 |
| 编码方向 | 递归从根到叶子 | 循环从叶子回溯到根 |
| 父节点 | 不存 | 存 `father` 下标，方便回溯 |
| 适用 | 通用场景，代码优雅 | 竞赛/应试，无需标准库优先队列 |

---

### 4.12 并查集 DSU

**英文名**：Union-Find / Disjoint Set Union（DSU）

**解决的问题**：维护元素分组关系，支持两个操作：查询两个元素是否同组、合并两个组。

**核心思想**：每个组用一棵树表示，树根是这个组的代表。每个节点只存一个信息：父节点是谁。

**为什么用树**：树天然能表达"谁是老大"这个关系。find 只需沿父指针向上走，union 只需把一个根挂到另一个根下，改一个指针，加上路径压缩后接近 O(1)。

---

#### 核心操作

```java
int[] parent = new int[n];
int[] rank = new int[n];

// 初始化：每个人自己是自己的根
void init(int n) {
    for (int i = 0; i < n; i++) { parent[i] = i; rank[i] = 0; }
}

// find：路径压缩，沿途所有节点直接挂到根上
int find(int x) {
    if (parent[x] != x) parent[x] = find(parent[x]);
    return parent[x];
}

// union：按秩合并，矮树挂到高树下
void union(int x, int y) {
    int px = find(x), py = find(y);
    if (px == py) return;
    if (rank[px] < rank[py]) parent[px] = py;
    else if (rank[px] > rank[py]) parent[py] = px;
    else { parent[py] = px; rank[px]++; }
}
```

---

#### 路径压缩原理

`return parent[x] = find(parent[x])` 这一行，递归返回时每层都执行赋值，路径上所有节点的父指针直接指向根，一次压缩全部完成。

---

#### 按秩合并

**秩（rank）**：树的高度上界，用于决定谁挂谁，避免树退化成链。

| 情况 | 操作 | rank 变化 |
|------|------|-----------|
| `rank[px] < rank[py]` | px 挂到 py 下 | 不变 |
| `rank[px] > rank[py]` | py 挂到 px 下 | 不变 |
| `rank[px] == rank[py]` | py 挂到 px 下 | `rank[px]++` |

**只有等高合并时 rank 才加 1**，因为只有这种情况整体高度真正增加了 1。

---

#### 适用场景

- 判断图是否有环（加边时检查两端是否已连通）
- 最小生成树 Kruskal 算法
- 网络连通性判断
- 朋友圈/社交网络分组
- 岛屿数量问题（相邻陆地合并）

**规律**：只要题目涉及"动态合并分组 + 查询是否同组"，优先考虑并查集。

---

## 5. 图 (graph)

图是**多对多**的网状结构，由**顶点**集合和**边**集合组成。图是数据结构中最复杂的非线性结构之一，也是后续学习图算法（遍历、最短路径、最小生成树等）的基础。

学习路线：**图的概念与术语** → **图的存储（邻接矩阵 / 邻接表）** → **图的遍历（BFS / DFS）** → **最小生成树（Kruskal / Prim）** → **最短路径**

---

### 5.1 图的基本概念与术语

| 术语 | 含义 |
|------|------|
| 顶点（Vertex） | 图中的数据元素/节点 |
| 边（Edge） | 两个顶点之间的连线 |
| 无向图（Undirected Graph） | 边没有方向，$(u, v)$ 与 $(v, u)$ 是同一条边 |
| 有向图（Directed Graph / Digraph） | 边有方向，$<u, v>$ 与 $<v, u>$ 是两条不同的弧 |
| 弧（Arc） | 有向图中的边，$<u, v>$ 表示从 u 指向 v |
| 度（Degree） | 与该顶点相连的边的数量 |
| 出度（Out-degree） | 有向图中以该顶点为**起点**的弧的条数 |
| 入度（In-degree） | 有向图中以该顶点为**终点**的弧的条数 |
| 权值（Weight） | 边上的数值，表示距离/成本/时间等 |
| 带权图（Weighted Graph） | 边有权值的图，也称"网" |
| 路径（Path） | 从一个顶点到另一个顶点经过的边序列 |
| 连通图 | 无向图中任意两个顶点都连通 |
| 强连通图 | 有向图中任意两个顶点互相可达 |
| 完全图 | 任意两个顶点之间都有边（无向完全图边数 = $\frac{n(n-1)}{2}$） |

---

### 5.2 邻接矩阵 `AdjacencyMatrix`

**核心思想**：用一个 $n \times n$ 的二维数组 `e[i][j]` 存储顶点之间的关系。若有边则为权值（无权图用 1），无边则用一个**极大值**表示"不可达"。

#### 数据结构

```java
char[] v = new char[105];          // 顶点数组，v[i] 存下标 i 对应的顶点名称
int[][] e = new int[105][105];     // 邻接矩阵，e[i][j] 存边 (i, j) 的权值
int n, m;  // n = 顶点数，m = 边数
```

#### 初始化

```java
// 所有边初始化为"无穷大"（不可达）
for (int[] row : e) Arrays.fill(row, Integer.MAX_VALUE / 2);
// 自己到自己的距离为 0
for (int i = 1; i <= n; i++) e[i][i] = 0;
```

#### 建边（无向带权图）

```java
e[xi][yi] = e[yi][xi] = w;  // 对称赋值
```

**为什么无向图是对称矩阵？** 因为无向图的边 $(u, v)$ 表示 u 和 v 之间互达，所以 `e[u][v]` 和 `e[v][u]` 必须相同，矩阵关于主对角线对称。

---

#### 为什么用 `Integer.MAX_VALUE / 2` 而不是 `Integer.MAX_VALUE`？

这是**带权图邻接矩阵的头号细节**——防止加法溢出：

```java
// 假设 e[u][k] = Integer.MAX_VALUE 且 e[k][v] = 3
// 在最短路径算法中计算：
int viaK = e[u][k] + e[k][v];  // Integer.MAX_VALUE + 3 → 溢出变成负数！

// 使用 Integer.MAX_VALUE / 2：
int viaK = e[u][k] + e[k][v];  // (MAX/2) + 3 → 仍在 int 范围内，不会溢出
```

`Integer.MAX_VALUE / 2` ≈ 10 亿，仍然远大于任何可能的实际路径长度（题目数据范围通常 ≤ 10000），能可靠地表示"不可达"，同时两个这样的值相加也不会溢出。

---

#### 度的计算

| | 无向图 | 有向图 |
|--|--------|--------|
| 度 | 第 i 行非零（非 ∞）元素个数 | — |
| 出度 | — | **第 i 行**非零（非 ∞）元素个数（行和） |
| 入度 | — | **第 i 列**非零（非 ∞）元素个数（列和） |

**原理**：行表示"从这个顶点出发的边"（出度），列表示"到达这个顶点的边"（入度）。无向图中出入相同，所以行和 = 列和 = 度。

---

#### 优缺点

| | 优点 | 缺点 |
|--|------|------|
| 邻接矩阵 | ① 判断任意两点是否相邻 O(1) ② 方便取边权 ③ 矩阵运算方便（用于 Floyd 等算法） | ① 空间 O(n²)，稀疏图浪费严重 ② 遍历某顶点的所有邻接点需扫描整行 O(n) |
| 适用 | **稠密图**（边数接近顶点数的平方）、需要频繁判断两点是否相邻 | |

---

### 5.3 邻接表 `AdjacencyList`

**核心思想**：每个顶点挂一个**链表**，存储它的所有**邻接顶点**。本质是"数组 + 链表"的组合——数组存顶点，链表存边。

#### 数据结构

```java
class Node {          // 边节点
    int adj;          // 邻接顶点下标
    int w;            // 边权
    Node next;        // 链表中下一个邻接点
}

class vv {            // 顶点
    char data;        // 顶点名称
    Node next;        // 邻接链表头指针
}

vv[] v = new vv[105]; // 顶点数组
```

#### 建边方式：头插法

```java
// 将 yi 插入到 xi 的邻接链表中（头插法）
Node node = new Node(yi, w);    // 创建新边节点
Node first = v[xi].next;        // 保存原链表头
v[xi].next = node;              // 新节点成为新头
node.next = first;              // 新节点指向原头
```

**为什么用头插法？** 头插是 O(1)，尾插需要遍历到尾节点 O(n)。建图过程效率高。

---

#### 有向图求入度——需要逆邻接表

邻接表天然支持查出度（遍历顶点 i 的链表即可），但**查入度需要遍历所有顶点的链表**，找哪些顶点的链表中包含 i，代价是 O(n + m)。

```
问题：顶点 i 的入度 = 有多少条弧指向 i？
       邻接表存的是 i → 别人，不是 别人 → i
```

**解决方案**：建立**逆邻接表**——额外维护一个链表数组，专门存"指向当前顶点的弧"。

```java
// 建邻接表的同时维护逆邻接表
// 正邻接表：v[xi] 的链表中加 yi（xi → yi）
// 逆邻接表：inv[yi] 的链表中加 xi（谁 → yi）

// 正邻接表
Node node = new Node(yi, w);
node.next = v[xi].next;
v[xi].next = node;

// 逆邻接表
Node invNode = new Node(xi, w);
invNode.next = inv[yi].next;
inv[yi].next = invNode;
```

有了逆邻接表后，查入度只需遍历 `inv[i]` 的链表，O(d⁻(i))。

---

#### 优缺点

| | 优点 | 缺点 |
|--|------|------|
| 邻接表 | ① 空间 O(n + m)，稀疏图高效 ② 遍历某顶点所有邻接点 O(邻接点数) | ① 判断两点是否相邻需要遍历链表 O(n) ② 边节点额外存储指针 |
| 适用 | **稀疏图**（边数远小于 n²）、需要频繁遍历邻接点 | |

---

#### 邻接矩阵 vs 邻接表 对比总结

| 维度 | 邻接矩阵 | 邻接表 |
|------|----------|--------|
| 空间复杂度 | O(n²) | O(n + m) |
| 判断 (u, v) 是否相邻 | O(1) | O(deg(u)) |
| 遍历 u 的所有邻接点 | O(n) | O(deg(u)) |
| 取边 (u, v) 权值 | O(1) | 需遍历链表 |
| 适用图类型 | 稠密图 | 稀疏图 |
| 实现复杂度 | 简单 | 稍复杂（需维护链表） |
| 矩阵运算（如 Floyd） | 支持 | 不支持 |
| 查有向图入度 | O(n)（扫列） | O(n + m)（需逆邻接表） |

---

### 5.4 广度优先搜索 BFS

**核心思想**：从起点出发，先访问所有距离为 1 的邻接点，再访问距离为 2 的，以此类推。用**队列**逐层扩展。

**关键词**：队列、分层遍历、最短路径（无权图）

---

#### BFS 通用模板

```java
static void bfs(int start) {
    if (flag[start]) return;           // 已访问，跳过
    queue.add(start);
    flag[start] = true;
    while (!queue.isEmpty()) {
        int x = queue.remove();        // 出队，访问
        System.out.print(x + " ");
        // 遍历 x 的所有邻接点
        ENode q = e[x].first;
        while (q != null) {
            if (!flag[q.s]) {
                queue.add(q.s);
                flag[q.s] = true;      // 入队时立即标记，防止重复入队
            }
            q = q.next;
        }
    }
}
```

---

#### BFS vs DFS 关键区别

| | BFS | DFS |
|--|-----|-----|
| 数据结构 | 队列（Queue） | 栈（递归/显式栈） |
| 遍历方式 | 一层一层扩散 | 一条路走到底再回溯 |
| 空间复杂度 | O(n)（队列可能存整层） | O(h)（递归栈深度） |
| 适用场景 | 无权最短路径、层序遍历 | 连通性判断、拓扑排序、强连通分量 |
| 标记时机 | **入队时**立即标记 | 访问时标记 |
| 根节点处理 | 在 for 循环中 bfs(i) 确保遍历所有连通分量 | 同左 |

---

#### 关键细节

1. **`flag` 标记在入队时，不是出队时**
   
   ```java
   // ✅ 正确：入队时标记
   queue.add(q.s);
   flag[q.s] = true;
   
   // ❌ 错误：出队时标记 → 同一节点可能重复入队！
   if (!flag[q.s]) queue.add(q.s);  // 入队后不标记，后面还会重新判断
   ```

2. **连通分量处理**：图可能不连通，需要在 main 中用 for 循环对所有顶点调用 bfs(i)，确保每个连通分量都被访问。

3. **项目中的实现**使用了邻接表存储，BFS 遍历时通过 `e[x].first` 链表获取 x 的所有邻接点。

---

### 5.5 深度优先搜索 DFS

**核心思想**：从起点出发，沿着一条路径一直走到无路可走，再**回溯**到上一个分叉点，选择另一条路继续。

**关键词**：递归、回溯、深度优先

---

#### DFS 通用模板（递归）

```java
static void dfs(int i) {
    if (flag[i]) return;               // 已访问，直接返回
    flag[i] = true;                    // 标记访问
    System.out.print(value[i] + " ");   // 处理当前节点
    for (int j = 1; j <= n; j++) {
        if (e[i][j] == 1 && !flag[j]) {
            dfs(j);                    // 递归访问邻接点
        }
    }
}
```

---

#### 递归执行过程（以具体图为例）

```
    A ── B ── C
    │    │    │
    F ── G ── D ── E
         │    │
         I ── H

DFS(A): A → B → C → I → D → G → F → E → H
```

**回溯发生在**：当前节点的所有邻接点都已访问完毕时，递归自动返回上一层。

---

#### DFS 的实现方式对比

| 实现方式 | 数据结构 | 特点 |
|----------|----------|------|
| 递归 | 系统栈 | 代码简洁，邻接矩阵天然适合 |
| 显式栈 | 手动 Stack | 与 BFS 代码结构对称（队列换栈） |

**项目中的实现**使用邻接矩阵存储，通过遍历 `e[i][j] == 1` 找邻接点。

---

### 5.6 十字链表 OrthogonalList

**要解决的问题**：邻接表查有向图的入度需要遍历所有顶点链表，效率低；逆邻接表需要额外空间。十字链表**同时存出度和入度**，一套结构解决。

**核心思想**：每条弧有**两个指针链**——`tnext`（同尾指针链，即出边链）和 `hnext`（同头指针链，即入边链）。每个顶点有 `firstout`（第一条出弧）和 `firstin`（第一条入弧）。

---

#### 数据结构

```java
class ENode {             // 弧节点（边）
    int w;                // 权值
    int tail;             // 弧尾（起点）下标
    ENode tnext;          // 下一个同尾弧（同一出发点）
    int head;             // 弧头（终点）下标
    ENode hnext;          // 下一个同头弧（同一到达点）
}

class e {                 // 顶点节点
    char data;            // 顶点名称
    ENode firstout;       // 第一条以该点为起点的弧
    ENode firstin;        // 第一条以该点为终点的弧
}
```

---

#### 与邻接表 / 逆邻接表的关系

```
十字链表 = 邻接表（firstout + tnext）+ 逆邻接表（firstin + hnext）→ 合并在同一组节点中
```

| 查询 | 方法 | 复杂度 |
|------|------|--------|
| 顶点 i 的出度 | 遍历 `graph[i].firstout` 的 `tnext` 链 | O(出度) |
| 顶点 i 的入度 | 遍历 `graph[i].firstin` 的 `hnext` 链 | O(入度) |

---

#### 建弧代码

```java
ENode eNode = new ENode(w, xi, yi);
// 头插法：tnext 链（出边方向）
eNode.tnext = graph[xi].firstout;
graph[xi].firstout = eNode;
// 头插法：hnext 链（入边方向）
eNode.hnext = graph[yi].firstin;
graph[yi].firstin = eNode;
```

---

### 5.7 多重邻接表 AdjacencyMultilist

**要解决的问题**：无向图的普通邻接表中，每条边被存了两次（u→v 和 v→u），删除边或修改边时需要维护两条链表。

**核心思想**：每条边只存**一份**，通过**两个指针链**分别挂在两个端点上。

---

#### 数据结构

```java
class ENode {
    int w;                // 权值
    int x;                // 端点一
    ENode xnext;          // 从端点 x 出发的同边链
    int y;                // 端点二
    ENode ynext;          // 从端点 y 出发的同边链
}
```

---

#### 与邻接表的本质区别

| | 邻接表 | 多重邻接表 |
|--|--------|-----------|
| 边上几个节点 | **两个**（u→v 和 v→u 各一个 `Node`） | **一个**（边 (u,v) 只有一个 `ENode`） |
| 删除边 | 需分别在 u 和 v 的链表中删除 | 一个节点，可同时从两个链中移除 |
| 空间 | 2m 个边节点 | m 个边节点 |

---

#### 十字链表 vs 多重邻接表

| | 十字链表 | 多重邻接表 |
|--|----------|-----------|
| 适用图 | **有向图** | **无向图** |
| 指针含义 | `tnext`（同尾/出）+ `hnext`（同头/入） | `xnext`（从端点 x）+ `ynext`（从端点 y） |
| 核心思想 | 合并邻接表 + 逆邻接表 | 合并无向图的两条弧为一个边节点 |

---

### 5.8 Kruskal 最小生成树

**适用对象**：带权无向连通图。目标是从所有边中选出 `n - 1` 条边，使所有顶点连通，并且边权和最小。

**项目对应代码**：`graph.Kruskal`

**核心思想**：按边权从小到大选边。每次选择一条不会形成环的最小边，直到选满 `n - 1` 条边。

这本质上是“从边出发”的贪心算法：

1. 把所有边放入 `List<Edge>`。
2. 调用 `Collections.sort(list)` 按权值升序排序。
3. 用并查集维护当前已经连通的顶点集合。
4. 遍历排序后的边 `(x, y, w)`：
   - 如果 `find(x) != find(y)`，说明加入这条边不会成环，可以选择。
   - 合并两个集合，累计边权 `sum += w`。
   - 当已选边数 `cnt == n - 1` 时停止。

#### 边结构

```java
class Edge implements Comparable<Edge> {
    int x, y;
    int w;

    @Override
    public int compareTo(Edge other) {
        return this.w - other.w;
    }
}
```

`Comparable` 的作用是让 `Collections.sort(list)` 知道按 `w` 从小到大排序。

#### 并查集判环

```java
static int find(int x) {
    if (flag[x] == x) return x;
    else return flag[x] = find(flag[x]);
}
```

如果一条边的两个端点已经属于同一个集合，说明它们之间已经有路径连通，再加入这条边就会形成环，所以必须跳过。

```java
xi = find(x);
yi = find(y);
if (xi != yi) {
    flag[xi] = yi;
    cnt++;
    sum += edge.w;
}
```

#### 复杂度

| 步骤 | 复杂度 |
|------|--------|
| 边排序 | O(m log m) |
| 并查集查询/合并 | 近似 O(1) |
| 总体 | O(m log m) |

**适合场景**：边比较少的稀疏图。因为 Kruskal 主要处理“边集”，边越少越有优势。

**记忆口诀**：Kruskal 先看边，边小就试；不成环就收下。

---

### 5.9 Prim 最小生成树

**适用对象**：带权无向连通图。目标同样是得到权值和最小的生成树。

**项目对应代码**：`graph.Prim`

**核心思想**：从一个起点开始，每次把“距离当前生成树最近的未加入顶点”加入生成树。

这本质上是“从点出发”的贪心算法：

1. 用邻接矩阵 `e[i][j]` 保存边权。
2. `flag[i]` 表示顶点 `i` 是否已经加入生成树。
3. `dist[i]` 表示顶点 `i` 到当前生成树的最小边权。
4. 每轮选择未加入顶点中 `dist` 最小的点 `min`。
5. 将 `min` 加入生成树，并用 `min` 更新其他未加入顶点的 `dist`。

#### 核心字段

```java
static int[][] e = new int[105][105];   // 邻接矩阵
static boolean[] flag = new boolean[105]; // 是否已加入生成树
static int[] dist = new int[105];       // 到当前生成树的最小边权
```

#### 初始化

```java
for (int i = 0; i < n; i++) {
    dist[i] = Integer.MAX_VALUE / 2;
    for (int j = 0; j < n; j++) {
        e[i][j] = Integer.MAX_VALUE / 2;
    }
}
```

因为当前实现使用邻接矩阵，无边的位置用极大值表示。这样后续执行 `Math.min(dist[j], e[min][j])` 时，无边不会错误地被选中。

#### 每轮选点

```java
int min = -1;
int t = Integer.MAX_VALUE / 2;
for (int j = 0; j < n; j++) {
    if (dist[j] < t && !flag[j]) {
        min = j;
        t = dist[min];
    }
}
```

这段代码在线性扫描所有顶点，找当前距离生成树最近的未加入点。当前源码是朴素 Prim，没有使用优先队列优化。

#### 更新距离

```java
flag[min] = true;
sum += dist[min];
for (int j = 0; j < n; j++) {
    if (!flag[j]) {
        dist[j] = Math.min(dist[j], e[min][j]);
    }
}
```

新顶点加入生成树后，其他顶点可能通过它以更小代价连接到生成树，所以要更新 `dist`。

#### 复杂度

| 实现方式 | 复杂度 | 说明 |
|----------|--------|------|
| 当前邻接矩阵朴素实现 | O(n²) | 每轮扫描所有顶点找最小 `dist` |
| 邻接表 + 优先队列 | O(m log n) | 当前项目暂未实现 |

**适合场景**：当前这种邻接矩阵版本更适合稠密图，或者顶点数不大、想重点理解算法过程的学习场景。

**记忆口诀**：Prim 先建树，点离树近就加入。

---

### 5.10 Dijkstra 单源最短路径

**适用对象**：带权图中从一个起点到其他所有顶点的最短路径。当前项目的 `graph.Dijkstra` 按无向带权图读取边，使用邻接矩阵保存权值。

**前提条件**：边权不能为负数。Dijkstra 的贪心依据是“当前未确定顶点中 `dist` 最小的点，其最短距离已经确定”，如果存在负权边，这个结论会失效。

#### 当前源码结构

```java
static int[][] g = new int[105][105];      // 邻接矩阵，g[i][j] 为边权
static boolean[] flag = new boolean[105];  // 顶点最短路是否已确定
static int[] pre = new int[105];           // 最短路径上的前驱顶点
static int[] dist = new int[105];          // 起点到各点的当前最短距离
```

`dist[s] = 0` 表示起点到自身距离为 0；无边位置初始化为 `Integer.MAX_VALUE / 2`，避免执行 `dist[min] + g[min][j]` 时整数溢出。

#### 核心过程

1. 从所有未确定顶点中选出 `dist` 最小的顶点 `min`。
2. 将 `flag[min] = true`，表示起点到 `min` 的最短路已经确定。
3. 用 `min` 尝试更新其他未确定顶点：

```java
if (!flag[j] && dist[min] + g[min][j] < dist[j]) {
    dist[j] = dist[min] + g[min][j];
    pre[j] = min;
}
```

`pre[j] = min` 的含义是：当前找到的最短路径中，走到 `j` 之前的上一个顶点是 `min`。最终可以从终点沿 `pre` 反向回溯到起点。

#### 复杂度

| 实现方式 | 时间复杂度 | 空间复杂度 | 说明 |
|----------|------------|------------|------|
| 当前邻接矩阵朴素实现 | O(n²) | O(n²) | 每轮线性扫描最小 `dist`，适合顶点数不大或稠密图 |
| 邻接表 + 优先队列 | O(m log n) | O(n + m) | 当前项目暂未实现，适合稀疏图 |

#### 易错点

- `dist` 必须先全部初始化为极大值，再把起点设为 0。
- 无边不要用 `Integer.MAX_VALUE` 直接参与加法，容易溢出。
- `flag[min] = true` 后再用 `min` 更新其他点，已经确定的顶点不再回退。
- 当前输出路径是从终点沿 `pre` 反向打印到起点，顺序是“终点 → 起点”；如果题目要求“起点 → 终点”，需要先压栈或递归后序输出。
- 若图不连通，某些点的 `dist` 会保持极大值；更严谨的实现应在 `min == -1` 时提前结束，避免访问非法下标。

#### 与 BFS 最短路的区别

| 场景 | 适合算法 |
|------|----------|
| 无权图或所有边权相同 | BFS |
| 非负权带权图 | Dijkstra |
| 存在负权边 | Bellman-Ford 或 SPFA（当前项目暂未实现） |

**记忆口诀**：Dijkstra 每轮定一个最近点，再用这个点松弛其他边。

---

### 5.11 拓扑排序 ToPo

**适用对象**：有向无环图（DAG）。拓扑排序把图中顶点排成一个线性序列，使每条有向边 `u -> v` 都满足 `u` 出现在 `v` 前面。当前项目的 `graph.ToPo` 用邻接表保存有向边，用入度数组和队列实现 Kahn 算法。

#### 当前源码结构

```java
static int[] degree = new int[105];       // 入度数组
static e[] graph = new e[105];            // 顶点数组，每个顶点挂一条出边链表
static int[] topo = new int[105];         // 保存拓扑序中的顶点下标
static Queue<Integer> queue = new LinkedList<>();
```

边节点 `Node.data` 保存邻接顶点下标，顶点节点 `e.data` 保存顶点字符，`e.first` 指向该顶点的第一条出边。建边时把终点插入起点的邻接链表，并让终点入度加一：

```java
Node node = new Node(yi);
degree[yi]++;
node.next = graph[xi].first;
graph[xi].first = node;
```

#### Kahn 算法过程

1. 先把所有入度为 0 的顶点入队。
2. 每次出队一个顶点 `j`，把它加入 `topo`。
3. 遍历 `j` 的所有出边，把相邻顶点入度减一。
4. 某个相邻顶点入度变成 0 时，说明它的所有前驱都已处理，可以入队。
5. 最后若 `cnt == n`，说明所有顶点都被处理，拓扑排序成功；否则图中存在环。

核心代码：

```java
while (node != null) {
    degree[node.data]--;
    if (degree[node.data] == 0) {
        queue.add(node.data);
    }
    node = node.next;
}
```

#### 复杂度

| 项目 | 复杂度 | 说明 |
|------|--------|------|
| 时间复杂度 | O(n + m) | 每个顶点最多入队一次，每条边最多处理一次 |
| 空间复杂度 | O(n + m) | 邻接表、入度数组、队列和拓扑序数组 |

#### 易错点

- 拓扑排序只适用于有向图，且只有 DAG 才能输出包含全部顶点的拓扑序。
- 入度数组必须在建边时同步维护；漏掉 `degree[yi]++` 会导致排序结果错误。
- 入度为 0 的顶点可能不止一个，因此拓扑序通常不唯一，和入队顺序、邻接表插入顺序有关。
- 当前实现使用头插法建邻接表，同一个顶点的出边遍历顺序会和输入边顺序相反。
- `cnt < n` 表示存在环或仍有顶点入度无法清零，不能把已经输出的部分序列当成完整拓扑序。
- 当前 `find(char x)` 找不到顶点时返回 `-1`，更严谨的版本应在建边前校验输入顶点是否合法，避免数组越界。

#### 例题思路

遇到“课程先修关系”“工程任务依赖”“判断是否能完成所有任务”这类题，可以把任务看成顶点，依赖关系 `A 必须在 B 前完成` 看成有向边 `A -> B`。若拓扑排序能处理完全部顶点，说明依赖关系无环；若不能处理完，说明存在循环依赖。

---

### 5.12 关键路径 CriticalPath

**适用对象**：AOE 网（Activity On Edge Network），也就是用有向边表示活动、用顶点表示事件的带权有向无环图。当前项目的 `graph.CriticalPath` 用邻接表保存带权有向边，先通过拓扑排序计算事件最早发生时间 `etv`，再按拓扑序逆序计算事件最晚发生时间 `ltv`，最后输出关键活动。

#### 当前源码结构

```java
static int[] etv = new int[105];       // 事件最早发生时间
static int[] ltv = new int[105];       // 事件最晚发生时间
static int[] topo = new int[105];      // 拓扑序，下标从 1 开始保存
static int[] degree = new int[105];    // 入度数组
static e[] graph = new e[105];         // 顶点数组
static Queue<Integer> queue = new LinkedList<>();
```

边节点 `Node` 中的 `data` 保存终点下标，`w` 保存活动持续时间，`next` 指向同一起点的下一条出边。建边时使用头插法：

```java
Node node = new Node(yi, w);
degree[yi]++;
node.next = graph[xi].first;
graph[xi].first = node;
```

#### 计算过程

1. **拓扑排序**：所有入度为 0 的顶点入队，出队后写入 `topo[++k]`。
2. **正向更新 `etv`**：处理边 `u -> v` 时，执行 `etv[v] = max(etv[v], etv[u] + w)`，表示事件 `v` 最早必须等所有前驱活动完成。
3. **初始化 `ltv`**：取拓扑序最后一个顶点 `end = topo[k]`，用 `etv[end]` 初始化所有事件的最晚发生时间。
4. **逆拓扑更新 `ltv`**：从后往前处理边 `i -> j`，执行 `ltv[i] = min(ltv[i], ltv[j] - w)`。
5. **判断关键活动**：边 `i -> j` 的最早开始时间 `ete = etv[i]`，最晚开始时间 `lte = ltv[j] - w`。若 `ete == lte`，这条活动没有机动时间，是关键活动。

核心判断：

```java
ete = etv[i];
lte = ltv[j] - node.w;
if (ete == lte) {
    System.out.println("" + graph[i].adj + graph[j].adj);
}
```

#### 复杂度

| 项目 | 复杂度 | 说明 |
|------|--------|------|
| 时间复杂度 | O(n + m) | 拓扑排序和两次边遍历都只处理顶点、边有限次 |
| 空间复杂度 | O(n + m) | 邻接表、入度数组、队列、拓扑序和时间数组 |

#### 易错点

- 关键路径必须建立在有向无环图上；如果图中有环，拓扑序不完整，`etv` / `ltv` 的结果没有意义。
- `etv` 用 `max`，因为一个事件要等所有前驱活动完成后才能发生。
- `ltv` 用 `min`，因为一个事件不能晚到影响任何后继活动的最晚开始。
- 当前实现会在拓扑排序中直接修改 `degree`，如果后续还需要原始入度，应该额外复制一份。
- `topo` 使用 1-based 保存，逆序循环是 `for (int q = k; q >= 1; q--)`，不要和 0-based 顶点下标混淆。
- 当前 `find(char x)` 找不到顶点时返回 `-1`，更严谨的版本应校验输入，避免非法下标访问。

#### 例题思路

遇到“工程最短工期”“哪些活动延误会影响总工期”这类题，可以把事件作为顶点，活动作为带权有向边。先算所有事件的最早/最晚发生时间，再找 `ete == lte` 的边；这些边组成的路径就是关键路径，路径长度等于整个工程的最短完成时间。

---

### 5.13 Kruskal vs Prim 对比

| 维度 | Kruskal | Prim |
|------|---------|------|
| 贪心对象 | 边 | 点 |
| 核心数据结构 | 边集 + 并查集 | 邻接矩阵 + `dist` 数组 |
| 判断重点 | 加边是否成环 | 哪个点离当前生成树最近 |
| 当前项目复杂度 | O(m log m) | O(n²) |
| 更适合 | 稀疏图 | 稠密图 |
| 项目文件 | `Kruskal.java` | `Prim.java` |

两者求出的最小生成树权值相同，但具体选边顺序可能不同。项目中的样例输入下，两种算法最终输出的最小生成树权值都是 `36`。

---

## 附录：数据结构学习路线总览

```
线性表
├── 顺序表（数组）────────────── 连续存储，O(1) 随机访问
├── 单链表 ──────────────────── 链式存储，O(1) 头插头删
├── 循环单链表 ──────────────── next 指向头结点形成环
├── 双向链表 ────────────────── 增加 prev 指针，删除更简洁
└── 双向循环链表 ────────────── 终极形态，O(1) 尾插

栈（LIFO）
├── 顺序栈 ──────────────────── 数组 + top 指针
└── 链栈 ────────────────────── 单链表实现，栈顶在头部

队列（FIFO）
├── 循环队列 ────────────────── 数组 + 模运算，牺牲一个空间判满
├── 链队列 ──────────────────── front + rear 双指针
├── 链式双端队列 ────────────── 双向链表，两端均可操作
└── 顺序双端队列 ────────────── 循环数组 + sum 简化判空判满

树
├── 树的存储 ─────────────────── 双亲表示法 / 孩子表示法 / 孩子兄弟表示法
├── 二叉树存储 ───────────────── 二叉链表 / 顺序存储
├── 遍历 ─────────────────────── 先序/中序/后序/层序（递归+迭代）
├── 线索二叉树 ───────────────── 利用空指针存储前驱后继
├── 二叉搜索树 ───────────────── 左小右大，中序有序，O(log n) 查找
├── AVL 平衡树 ───────────────── 高度差 ≤1，LL/RR/LR/RL 四种旋转
├── 哈夫曼树 ─────────────────── WPL 最小，贪心构造，用于数据压缩
└── 并查集（DSU） ────────────── 路径压缩+按秩合并，近似 O(1)

图
├── 基本概念 ─────────────────── 顶点/边/度/入度/出度/权值/连通图
├── 邻接矩阵 ─────────────────── 二维数组，O(1) 判相邻，稠密图适用
├── 邻接表 ───────────────────── 数组+链表，O(n+m) 空间，稀疏图适用
├── BFS ──────────────────────── 队列，逐层扩展，入队时标记
├── DFS ──────────────────────── 递归/栈，一条路走到底再回溯
├── 十字链表 ─────────────────── 有向图，合并邻接表+逆邻接表
├── 多重邻接表 ───────────────── 无向图，每条边只存一份
├── Kruskal ──────────────────── 边排序+并查集，适合稀疏图
├── Prim ─────────────────────── 从点扩展生成树，邻接矩阵版适合稠密图
├── Dijkstra ─────────────────── 单源最短路径，非负权图，dist+pre 记录距离和路径
├── 拓扑排序 ─────────────────── 有向无环图，入度数组+队列判断依赖顺序
└── 关键路径 ─────────────────── AOE 网，etv/ltv 判断没有机动时间的关键活动
```
