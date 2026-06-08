# Data-Structure

> Handcrafting data structures and algorithms in Java from scratch — continuous learning, continuous updates 🚀

This project is my code record for self-studying data structures. All structures are manually implemented in Java without relying on built-in data structure classes from the standard library. The learning path follows: Linear List → Stack → Queue → Tree → Graph → Searching & Sorting → Dynamic Programming.

---

## Currently Implemented

### Linear List

| Class | Description |
|------|------|
| `MyArrayList` | Sequential list (array-based), O(1) random access |
| `MyLinkedList` | Singly linked list, with dummy head node |
| `MyCircularLinkedList` | Circular singly linked list |
| `MyDoubleLinkedList` | Doubly linked list |
| `MyDoubleCircularLinkedList` | Circular doubly linked list, O(1) tail insertion |

### Stack

| Class | Description |
|------|------|
| `MyArrayStack` | Sequential stack, array + top pointer |
| `MyLinkedStack` | Linked stack, stack top at list head |

### Queue

| Class | Description |
|------|------|
| `MyCircularQueue` | Circular queue, modulo-based implementation |
| `MyLinkQueue` | Linked queue, front + rear dual pointers |
| `MyDeque` | Linked deque, doubly linked list implementation |
| `MyArrayDeque` | Sequential deque, circular array implementation |

### Tree

| Class | Description |
|------|------|
| `ParentTree` | Parent representation |
| `ChildTree` | Child representation |
| `ChildSiblingTree` | Child-sibling representation (tree → binary tree conversion) |
| `BinaryTree` | Binary linked list storage |
| `SequentialBinaryTree` | Sequential binary tree storage |
| `BinaryTreeTraversal` | Pre/in/post/level-order traversal (recursive) |
| `CompleteBinaryTree` | Iterative traversal (in/post/level-order) |
| `ThreadedBinaryTree` | In-order threaded binary tree |
| `BinarySearchTree` | Binary search tree, with recursive & non-recursive insert/delete |
| `AVLTree` | AVL balanced binary tree, with insert/delete/four rotation types |
| `HuffmanTree` | Huffman tree, minimum WPL construction |
| `DSU` | Disjoint Set Union, path compression + union by rank |

### Graph

| Class | Description |
|------|------|
| `AdjacencyMatrix` | Adjacency matrix, supports weighted undirected graphs |
| `AdjacencyList` | Adjacency list, head-insertion linked list construction |
| `BFS` | Breadth-first search, queue-based |
| `DFS` | Depth-first search, recursion-based |
| `OrthogonalList` | Orthogonal list, combines out-degree and in-degree for digraphs |
| `AdjacencyMultilist` | Adjacency multilist, each undirected edge stored only once |

---

## Project Structure

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
    ├── tree/
    │   ├── AVLTree.java
    │   ├── BinarySearchTree.java
    │   ├── BinaryTree.java
    │   ├── BinaryTreeTraversal.java
    │   ├── ChildSiblingTree.java
    │   ├── ChildTree.java
    │   ├── CompleteBinaryTree.java
    │   ├── DSU.java
    │   ├── ParentTree.java
    │   ├── Huffman.java
    │   ├── Huffman1.java
    │   ├── SequentialBinaryTree.java
    │   └── ThreadedBinaryTree.java
    └── graph/
        ├── AdjacencyMatrix.java
        ├── AdjacencyList.java
        ├── BFS.java
        ├── DFS.java
        ├── OrthogonalList.java
        └── AdjacencyMultilist.java
```

---

## Development Environment

- Java 17
- IntelliJ IDEA
- Arch Linux / Windows

---

## Work in Progress

Still actively learning — planned additions:

**Graph**
- [x] Graph storage (adjacency matrix / adjacency list)
- [x] Graph traversal (BFS / DFS)
- [x] Orthogonal list / Adjacency multilist
- [ ] Shortest path (Dijkstra / Floyd)
- [ ] Minimum spanning tree (Prim / Kruskal)
- [ ] Topological sorting

**Searching & Sorting**
- [ ] Search algorithms (binary search / interpolation search / hash search)
- [ ] Sorting algorithms (bubble / insertion / shell / merge / quicksort / heapsort)
- [ ] Heap / priority queue

**Advanced Tree Structures**
- [ ] B-tree
- [ ] B+ tree
- [ ] Red-black tree

**Dynamic Programming**
- [ ] Basic DP (Fibonacci / longest common subsequence)
- [ ] 0-1 knapsack problem
- [ ] Unbounded knapsack / bounded knapsack

If you're also self-studying data structures, feel free to reference this project. Issues and PRs are welcome!
