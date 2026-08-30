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
| `ToPo.java` | 拓扑排序，基于邻接表、入度数组和队列判断有向无环图 |
| `CriticalPath.java` | 关键路径，基于邻接表、拓扑序、事件最早/最晚发生时间输出关键活动 |

### 查找 `search`

| 文件 | 当前内容 |
| --- | --- |
| `BinarySearch.java` | 二分查找，在已排序的 1-based 整数数组中查找目标值并输出下标或 `-1` |
| `HashSearch.java` | 开放定址哈希查找，使用除留余数法和线性探测处理冲突 |

### 排序 `sort`

| 文件 | 当前内容 |
| --- | --- |
| `BubbleSort.java` | 冒泡排序，相邻元素交换，并用 `flag` 在已有序时提前结束 |
| `SelectionSort.java` | 简单选择排序，每轮从未排序区间选出最小值放到前端 |
| `InsertionSort.java` | 直接插入排序，将当前元素插入前方已有序区间 |
| `ShellSort.java` | 希尔排序，按 `n / 2` 递减增量执行分组插入，并输出每趟结果 |
| `QuickSort.java` | 快速排序，以区间首元素为基准，使用左右指针完成划分和递归 |
| `HeapSort.java` | 堆排序，使用 1-based 数组建大根堆，再交换堆顶并向下调整 |
| `MergeSort.java` | 归并排序，递归划分区间后用临时数组合并两个有序子区间 |
| `CountingSort.java` | 计数排序，统计非负整数出现次数并用前缀和稳定回填 |

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
│       ├── graph/        # 图的存储结构、遍历、最小生成树、最短路径、拓扑排序和关键路径
│       ├── linearlist/   # 顺序表、单链表、循环链表、双向链表
│       ├── queue/        # 循环队列、链队列、双端队列
│       ├── search/       # 二分查找、开放定址哈希查找
│       ├── sort/         # 冒泡、选择、插入、希尔、快速、堆、归并、计数排序
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
3. 打开任意类，例如 `linearlist.MyArrayList`、`tree.AVLTree`、`graph.Kruskal`、`graph.ToPo`、`graph.CriticalPath`。
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

排序示例先读取元素个数，再读取待排序整数。例如：

```powershell
"8 49 38 65 97 76 13 27 49" | java -cp out/check sort.QuickSort
"8 49 38 65 97 76 13 27 49" | java -cp out/check sort.HeapSort
"8 49 38 65 97 76 13 27 49" | java -cp out/check sort.MergeSort
"8 49 38 65 97 76 13 27 49" | java -cp out/check sort.CountingSort
```

其余排序类可将类名替换为 `BubbleSort`、`SelectionSort`、`InsertionSort` 或 `ShellSort`。`CountingSort` 的计数数组长度为 `10005`，因此输入值应为 `0..10004` 的非负整数；其余排序实现使用长度为 `105` 的数组和 1-based 下标，输入规模应控制在 104 个整数以内。

查找示例也通过标准输入读取数据：`BinarySearch` 依次读取 `n`、目标值和已升序排列的 `n` 个整数；`HashSearch` 依次读取 `n`、待插入整数和查询值。哈希表容量固定为 `15`，哈希地址按 `key % 13` 计算并线性探测，因此示例输入应使用非负整数，且元素数量不要超过表容量。

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

`graph.ToPo` 读取有向图顶点序列和边，使用入度为 0 的顶点队列生成拓扑序。源码末尾保留了样例输入：

```powershell
@"
6 8
ABCDEF
A B
A C
A D
C B
C E
F D
F E
D E
"@ | Set-Content -Encoding ASCII out/check/topo-input.txt
cmd /c "java -cp out\check graph.ToPo < out\check\topo-input.txt"
```

`graph.CriticalPath` 读取 AOE 网的顶点序列和带权有向边，先做拓扑排序计算事件最早发生时间，再反向计算最晚发生时间，最后输出关键活动边。源码末尾保留了样例输入：

```powershell
@"
9 11
ABCDEFGHY
A B 6
A C 4
A D 5
B E 1
C E 1
D F 2
E G 9
E H 7
F H 4
G Y 2
H Y 4
"@ | Set-Content -Encoding ASCII out/check/critical-path-input.txt
cmd /c "java -cp out\check graph.CriticalPath < out\check\critical-path-input.txt"
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
| 图 | 已实现 / 持续完善 | 已覆盖邻接矩阵、邻接表、十字链表、邻接多重表、BFS、DFS、Kruskal、Prim、Dijkstra、拓扑排序和关键路径 |
| 查找 | 已建立 / 持续完善 | 已建立独立 `search` 模块，包含二分查找和开放定址哈希查找；BST、并查集中的查找实现仍可对照学习 |
| 排序 | 已实现 / 持续完善 | 已建立独立 `sort` 模块，覆盖冒泡、选择、直接插入、希尔、快速、堆、归并和计数排序 |
| 动态规划 | 未开始 | 当前仓库暂无 DP 代码 |

## 后续计划

- 图算法：继续补充 Floyd 等常见算法，并继续整理拓扑排序、关键路径等有向图算法的运行示例。
- 查找算法：补充插值查找、折半查找的边界用例，以及哈希表的查找失败、冲突链和满表处理。
- 排序算法：补充基数排序，并对归并、计数排序和现有实现增加边界输入、负数处理和重复元素测试。
- 堆与优先队列：补充二叉堆实现，并和哈夫曼树构造进行对照。
- 高级树结构：继续学习 B 树、B+ 树、红黑树。
- 测试改进：后续可以引入 JUnit，把当前 `main` 示例沉淀为可重复运行的测试用例。
