# 数据结构复习资料（Java 实现）

> 基于项目 `Data-Structure`，按学习顺序从线性表 → 栈 → 队列 → 树的递进式复习文档。

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
   - 4.11 哈夫曼树 HuffmanTree
   - 4.12 并查集 DSU

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
```
