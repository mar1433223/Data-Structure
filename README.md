# Data-Structure

这是一个用 Java 手写数据结构与基础算法的学习项目。当前代码以“一个知识点一个类”的方式组织，每个实现类都带有 `main` 方法，便于在学习过程中直接运行、观察插入、删除、遍历、查找等操作的输出。

项目重点不是封装成可发布的库，而是按数据结构课程的路线，把线性表、栈、队列、树、图等结构从底层存储方式开始实现出来。当前实现以 `int`、`char` 等基础类型为主，适合配合教材或复习笔记逐步阅读。

## 当前已实现的数据结构/算法目录

### 线性表 `linearlist`

| 文件 | 当前内容 |
| --- | --- |
| `MyArrayList.java` | 顺序表，数组存储，支持追加、按位置插入、查找、删除和遍历输出 |
| `MyLinkedList.java` | 单链表，带头结点，支持头插、尾插、按值插入、删除和遍历 |
| `MyCircularLinkedList.java` | 循环单链表，支持头插、尾插、按值插入、删除和遍历 |
| `MyDoubleLinkedList.java` | 双向链表，支持前驱/后继维护、头插、尾插、插入、删除 |
| `MyDoubleCircularLinkedList.java` | 双向循环链表，使用头结点维护首尾关系，支持插入、删除和遍历 |

### 栈 `stack`

| 文件 | 当前内容 |
| --- | --- |
| `MyArrayStack.java` | 顺序栈，数组 + `top` 指针，支持入栈、出栈、判空和取栈顶 |
| `MyLinkedStack.java` | 链栈，使用头结点后的第一个节点作为栈顶，支持入栈、出栈、判空和取栈顶 |

### 队列 `queue`

| 文件 | 当前内容 |
| --- | --- |
| `MyCircularQueue.java` | 循环队列，数组存储，使用取模维护 `front` / `rear` |
| `MyLinkQueue.java` | 链队列，使用 `front` / `rear` 双指针，支持入队、出队、判空和取队头 |
| `MyDeque.java` | 链式双端队列，双向链表结构，支持两端插入和两端删除 |
| `MyArrayDeque.java` | 顺序双端队列，循环数组结构，支持两端插入、两端删除和长度统计 |

### 树 `tree`

| 文件 | 当前内容 |
| --- | --- |
| `ParentTree.java` | 树的双亲表示法，支持设置根节点、插入节点和查询父节点 |
| `ChildTree.java` | 树的孩子表示法，数组保存顶点，链表保存孩子关系 |
| `ChildSiblingTree.java` | 孩子兄弟表示法，使用第一个孩子和下一个兄弟表示普通树 |
| `BinaryTree.java` | 二叉链表存储，支持按父节点插入左右孩子和查询孩子节点 |
| `SequentialBinaryTree.java` | 顺序存储二叉树，使用数组下标关系维护父子节点 |
| `BinaryTreeTraversal.java` | 二叉树递归先序、中序、后序遍历，以及层序遍历 |
| `CompleteBinaryTree.java` | 二叉树递归遍历、迭代中序/后序遍历和层序遍历示例 |
| `ThreadedBinaryTree.java` | 中序线索二叉树，支持线索化并查询指定节点的前驱/后继 |
| `BinarySearchTree.java` | 二叉搜索树，包含递归/非递归查找、插入和删除示例 |
| `AVLTree.java` | AVL 平衡二叉树，包含插入、删除和 LL/RR/LR/RL 旋转调整 |
| `Huffman.java` | 基于优先队列构造哈夫曼树，并生成字符编码 |
| `Huffman1.java` | 使用数组式节点表构造哈夫曼树和编码 |
| `DSU.java` | 并查集，包含初始化、查找、合并和连通性检查 |

### 图 `graph`

| 文件 | 当前内容 |
| --- | --- |
| `AdjacencyMatrix.java` | 邻接矩阵表示图，示例中构造带权无向图 |
| `AdjacencyList.java` | 邻接表表示图，使用头插法维护边表 |
| `BFS.java` | 广度优先搜索，使用队列遍历邻接矩阵图 |
| `DFS.java` | 深度优先搜索，递归遍历邻接矩阵图 |
| `OrthogonalList.java` | 十字链表，有向图的出边和入边链表表示 |
| `AdjacencyMultilist.java` | 邻接多重表，无向图中每条边只保存一份 |
| `Kruskal.java` | Kruskal 最小生成树算法，边排序 + 并查集思想 |
| `Prim.java` | Prim 最小生成树算法，基于邻接矩阵和距离数组逐步扩展生成树 |
| `Dijkstra.java` | Dijkstra 单源最短路径算法，基于邻接矩阵、`dist` 和 `pre` 数组输出路径 |

## 技术栈

- Java：核心实现语言，当前代码使用普通 Java 类和 `main` 方法组织示例。
- IntelliJ IDEA：仓库包含 `.iml` 模块配置，源码根目录为 `Data Structure/src`。
- JDK：模块配置使用 inherited JDK；建议使用 JDK 8+，本地如果使用 Java 17 也可以直接编译运行。
- 构建方式：当前没有 Maven / Gradle 配置，主要通过 IDE 或 `javac` 编译。

## 项目结构说明

```text
Data-Structure/
├── Data Structure/
│   └── src/
│       ├── graph/        # 图的存储结构、遍历、最小生成树和最短路径算法
│       ├── linearlist/   # 顺序表、单链表、循环链表、双向链表
│       ├── queue/        # 循环队列、链队列、双端队列
│       ├── stack/        # 顺序栈、链栈
│       └── tree/         # 普通树、二叉树、搜索树、AVL、哈夫曼树、并查集
├── 数据结构/
│   ├── README.md
│   └── Data-Structure-复习资料.md
├── README.md
└── README_EN.md
```

说明：

- `Data Structure/src` 是当前 Java 源码目录。
- `数据结构/` 目录保存中文学习笔记和复习资料。
- `out/`、`.idea/` 等目录属于本地 IDE 或编译产物，不作为源码维护重点。

## 如何运行或测试

### 使用 IntelliJ IDEA

1. 打开项目根目录 `Data-Structure`。
2. 将 `Data Structure/src` 识别为 Sources Root。
3. 打开任意类，例如 `linearlist.MyArrayList`、`tree.AVLTree`、`graph.Kruskal`。
4. 直接运行该类中的 `main` 方法。

### 使用命令行编译

在项目根目录执行：

```powershell
javac -encoding UTF-8 -d out/check (Get-ChildItem -Recurse -Filter *.java 'Data Structure\src').FullName
```

编译完成后，可以运行带 `main` 方法的类。部分线性表、栈、队列示例不需要标准输入，例如：

```powershell
java -cp out/check linearlist.MyArrayList
java -cp out/check stack.MyArrayStack
java -cp out/check queue.MyCircularQueue
```

树和图目录中有不少示例通过 `Scanner` 读取标准输入。运行这类示例时需要手动输入数据，或用管道传入数据，例如：

```powershell
Set-Content -Encoding ASCII out/check/avl-input.txt "3 10 20 30 10 20 30"
cmd /c "java -cp out\check tree.AVLTree < out\check\avl-input.txt"
```

`graph.Kruskal` 和 `graph.Prim` 的源码末尾都保留了一组样例输入，也可以保存为输入文件后验证：

```powershell
@"
9 15
0 1 3
0 5 4
1 6 6
6 5 7
1 2 8
1 8 5
2 8 2
2 3 12
8 3 11
6 3 14
6 7 9
5 4 18
3 7 6
7 4 1
3 4 10
"@ | Set-Content -Encoding ASCII out/check/kruskal-input.txt
cmd /c "java -cp out\check graph.Kruskal < out\check\kruskal-input.txt"
cmd /c "java -cp out\check graph.Prim < out\check\kruskal-input.txt"
```

`graph.Dijkstra` 读取无向带权图和起点，源码末尾同样保留了样例输入。可以直接把样例保存为输入文件后运行：

```powershell
@"
9 16
0 1 1
0 2 5
1 2 3
1 3 7
1 4 5
2 4 1
2 5 7
3 4 2
3 6 3
4 5 3
4 6 6
4 7 9
5 7 5
6 7 2
6 8 7
7 8 4
0
"@ | Set-Content -Encoding ASCII out/check/dijkstra-input.txt
cmd /c "java -cp out\check graph.Dijkstra < out\check\dijkstra-input.txt"
```

当前项目没有独立的单元测试框架或测试目录；现阶段检查方式主要是：

- 全量 `javac` 编译，确认所有 Java 源码能通过编译。
- 运行各个类中的 `main` 示例，观察控制台输出是否符合预期。

## 学习进度

| 模块 | 进度 | 说明 |
| --- | --- | --- |
| 线性表 | 已实现 | 覆盖顺序表、单链表、循环链表、双向链表和双向循环链表 |
| 栈 | 已实现 | 覆盖数组栈和链栈 |
| 队列 | 已实现 | 覆盖循环队列、链队列、链式双端队列和数组双端队列 |
| 树 | 已实现 / 持续完善 | 已覆盖普通树表示、二叉树遍历、线索二叉树、BST、AVL、哈夫曼树和并查集 |
| 图 | 已实现 / 持续完善 | 已覆盖邻接矩阵、邻接表、十字链表、邻接多重表、BFS、DFS、Kruskal、Prim 和 Dijkstra |
| 查找 | in progress | 目前主要体现在顺序表查找、BST 查找和并查集查找，独立查找算法模块尚未建立 |
| 排序 | in progress | Kruskal 中使用边排序；独立排序算法模块尚未建立 |
| 动态规划 | 未开始 | 当前仓库暂无 DP 代码 |

## 后续计划

- 图算法：继续补充 Floyd、拓扑排序等常见算法，并整理运行示例。
- 查找算法：单独增加二分查找、插值查找、哈希查找等内容。
- 排序算法：单独增加冒泡、选择、插入、希尔、归并、快速排序、堆排序等实现。
- 堆与优先队列：补充二叉堆实现，并和哈夫曼树构造进行对照。
- 高级树结构：继续学习 B 树、B+ 树、红黑树。
- 测试改进：后续可以引入 JUnit，把当前 `main` 示例沉淀为可重复运行的测试用例。
