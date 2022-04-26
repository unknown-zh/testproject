package com.springbatch.springbatchtest.test;

import lombok.Data;

import java.util.*;


public class StructureTest {

    /**
     * @Description: 单链表
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/21
     */
    @Data
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    /**
     * @Description: 双链表
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/21
     */
    @Data
    public class LinkedListNode {

        LinkedListNode pre;
        int val;
        LinkedListNode next;

        LinkedListNode() {
        }

        LinkedListNode(int val) {
            this.val = val;
        }

        LinkedListNode(int val, LinkedListNode next, LinkedListNode pre) {
            this.val = val;
            this.next = next;
            this.pre = pre;
        }
    }

    /**
     * @Author Dr. zhang
     * @CreateTime 2022/3/14
     * @Description 跳表
     */
    public static class SkipList {
        //一级索引
        public static List indexOne;
        //二级索引
        public static List indexTwo;
        //三级索引
        public static List indexThree;
    }

    /**
     * @Description: 散列表，最难定义数据结构
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/21
     */
    public static class CustomHashMap<K, V> {
        HashMap hashMap = new HashMap<String, String>();
        static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
        static final int MAXIMUM_CAPACITY = 1 << 30;
        static final float DEFAULT_LOAD_FACTOR = 0.75f;

        //散列表的数组
        Node<K, V>[] tab;
        //散列表的链表的节点
        Node<K, V> p;
        int n, i;

        //计算数组数据的hash值
        static final int hash(Object key) {
            int h;
            return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        }

        //计算数组下标记
        static final int tableIndex(int hash) {
            return hash & (DEFAULT_INITIAL_CAPACITY - 1);
        }

        //链表节点定义
        static class Node<K, V> implements Map.Entry<K, V> {
            final int hash;
            final K key;
            V value;
            Node<K, V> next;

            Node(int hash, K key, V value, Node<K, V> next) {
                this.hash = hash;
                this.key = key;
                this.value = value;
                this.next = next;
            }

            public final K getKey() {
                return key;
            }

            public final V getValue() {
                return value;
            }

            public final String toString() {
                return key + "=" + value;
            }

            public final int hashCode() {
                return Objects.hashCode(key) ^ Objects.hashCode(value);
            }

            public final V setValue(V newValue) {
                V oldValue = value;
                value = newValue;
                return oldValue;
            }

            public final boolean equals(Object o) {
                if (o == this)
                    return true;
                if (o instanceof Map.Entry) {
                    Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                    if (Objects.equals(key, e.getKey()) &&
                            Objects.equals(value, e.getValue()))
                        return true;
                }
                return false;
            }
        }

        //这个是双链表的结构
        static class Entry<K, V> extends Node<K, V> {
            Entry<K, V> before, after;

            Entry(int hash, K key, V value, Node<K, V> next) {
                super(hash, key, value, next);
            }
        }

        //集成双链表的树节点，这是个红黑树的实现
        static final class TreeNode<K, V> extends Entry<K, V> {
            TreeNode<K, V> parent;  // red-black tree links
            TreeNode<K, V> left;
            TreeNode<K, V> right;
            TreeNode<K, V> prev;    // needed to unlink next upon deletion
            boolean red;

            TreeNode(int hash, K key, V val, Node<K, V> next) {
                super(hash, key, val, next);
            }

            /**
             * Returns root of tree containing this node.
             */
            final TreeNode<K, V> root() {
                for (TreeNode<K, V> r = this, p; ; ) {
                    if ((p = r.parent) == null)
                        return r;
                    r = p;
                }
            }

            /**
             * Ensures that the given root is the first node of its bin.
             */
//            static <K, V> void moveRootToFront(Node<K, V>[] tab, TreeNode<K, V> root) {
//                int n;
//                if (root != null && tab != null && (n = tab.length) > 0) {
//                    int index = (n - 1) & root.hash;
//                    TreeNode<K, V> first = (TreeNode<K, V>) tab[index];
//                    if (root != first) {
//                        Node<K, V> rn;
//                        tab[index] = root;
//                        TreeNode<K, V> rp = root.prev;
//                        if ((rn = root.next) != null)
//                            ((TreeNode<K, V>) rn).prev = rp;
//                        if (rp != null)
//                            rp.next = rn;
//                        if (first != null)
//                            first.prev = root;
//                        root.next = first;
//                        root.prev = null;
//                    }
//                    assert checkInvariants(root);
//                }
//            }

            /**
             * Finds the node starting at root p with the given hash and key.
             * The kc argument caches comparableClassFor(key) upon first use
             * comparing keys.
             */
//            final TreeNode<K, V> find(int h, Object k, Class<?> kc) {
//                TreeNode<K, V> p = this;
//                do {
//                    int ph, dir;
//                    K pk;
//                    TreeNode<K, V> pl = p.left, pr = p.right, q;
//                    if ((ph = p.hash) > h)
//                        p = pl;
//                    else if (ph < h)
//                        p = pr;
//                    else if ((pk = p.key) == k || (k != null && k.equals(pk)))
//                        return p;
//                    else if (pl == null)
//                        p = pr;
//                    else if (pr == null)
//                        p = pl;
//                    else if ((kc != null ||
//                            (kc = comparableClassFor(k)) != null) &&
//                            (dir = compareComparables(kc, k, pk)) != 0)
//                        p = (dir < 0) ? pl : pr;
//                    else if ((q = pr.find(h, k, kc)) != null)
//                        return q;
//                    else
//                        p = pl;
//                } while (p != null);
//                return null;
//            }

            /**
             * Calls find for root node.
             */
//            final TreeNode<K, V> getTreeNode(int h, Object k) {
//                return ((parent != null) ? root() : this).find(h, k, null);
//            }

            /**
             * Tie-breaking utility for ordering insertions when equal
             * hashCodes and non-comparable. We don't require a total
             * order, just a consistent insertion rule to maintain
             * equivalence across rebalancings. Tie-breaking further than
             * necessary simplifies testing a bit.
             */
            static int tieBreakOrder(Object a, Object b) {
                int d;
                if (a == null || b == null ||
                        (d = a.getClass().getName().
                                compareTo(b.getClass().getName())) == 0)
                    d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
                            -1 : 1);
                return d;
            }

            /**
             * Forms tree of the nodes linked from this node.
             *
             * @return root of tree
             */
//            final void treeify(Node<K, V>[] tab) {
//                TreeNode<K, V> root = null;
//                for (TreeNode<K, V> x = this, next; x != null; x = next) {
//                    next = (TreeNode<K, V>) x.next;
//                    x.left = x.right = null;
//                    if (root == null) {
//                        x.parent = null;
//                        x.red = false;
//                        root = x;
//                    } else {
//                        K k = x.key;
//                        int h = x.hash;
//                        Class<?> kc = null;
//                        for (TreeNode<K, V> p = root; ; ) {
//                            int dir, ph;
//                            K pk = p.key;
//                            if ((ph = p.hash) > h)
//                                dir = -1;
//                            else if (ph < h)
//                                dir = 1;
//                            else if ((kc == null &&
//                                    (kc = comparableClassFor(k)) == null) ||
//                                    (dir = compareComparables(kc, k, pk)) == 0)
//                                dir = tieBreakOrder(k, pk);
//
//                            TreeNode<K, V> xp = p;
//                            if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                                x.parent = xp;
//                                if (dir <= 0)
//                                    xp.left = x;
//                                else
//                                    xp.right = x;
//                                root = balanceInsertion(root, x);
//                                break;
//                            }
//                        }
//                    }
//                }
//                moveRootToFront(tab, root);
//            }

            /**
             * Returns a list of non-TreeNodes replacing those linked from
             * this node.
             */
//            final Node<K, V> untreeify(HashMap<K, V> map) {
//                Node<K, V> hd = null, tl = null;
//                for (Node<K, V> q = this; q != null; q = q.next) {
//                    Node<K, V> p = map.replacementNode(q, null);
//                    if (tl == null)
//                        hd = p;
//                    else
//                        tl.next = p;
//                    tl = p;
//                }
//                return hd;
//            }

            /**
             * Tree version of putVal.
             */
//            final TreeNode<K, V> putTreeVal(HashMap<K, V> map, Node<K, V>[] tab,
//                                            int h, K k, V v) {
//                Class<?> kc = null;
//                boolean searched = false;
//                TreeNode<K, V> root = (parent != null) ? root() : this;
//                for (TreeNode<K, V> p = root; ; ) {
//                    int dir, ph;
//                    K pk;
//                    if ((ph = p.hash) > h)
//                        dir = -1;
//                    else if (ph < h)
//                        dir = 1;
//                    else if ((pk = p.key) == k || (k != null && k.equals(pk)))
//                        return p;
//                    else if ((kc == null &&
//                            (kc = comparableClassFor(k)) == null) ||
//                            (dir = compareComparables(kc, k, pk)) == 0) {
//                        if (!searched) {
//                            TreeNode<K, V> q, ch;
//                            searched = true;
//                            if (((ch = p.left) != null &&
//                                    (q = ch.find(h, k, kc)) != null) ||
//                                    ((ch = p.right) != null &&
//                                            (q = ch.find(h, k, kc)) != null))
//                                return q;
//                        }
//                        dir = tieBreakOrder(k, pk);
//                    }
//
//                    TreeNode<K, V> xp = p;
//                    if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                        Node<K, V> xpn = xp.next;
//                        TreeNode<K, V> x = map.newTreeNode(h, k, v, xpn);
//                        if (dir <= 0)
//                            xp.left = x;
//                        else
//                            xp.right = x;
//                        xp.next = x;
//                        x.parent = x.prev = xp;
//                        if (xpn != null)
//                            ((TreeNode<K, V>) xpn).prev = x;
//                        moveRootToFront(tab, balanceInsertion(root, x));
//                        return null;
//                    }
//                }
//            }

            /**
             * Removes the given node, that must be present before this call.
             * This is messier than typical red-black deletion code because we
             * cannot swap the contents of an interior node with a leaf
             * successor that is pinned by "next" pointers that are accessible
             * independently during traversal. So instead we swap the tree
             * linkages. If the current tree appears to have too few nodes,
             * the bin is converted back to a plain bin. (The test triggers
             * somewhere between 2 and 6 nodes, depending on tree structure).
             */
//            final void removeTreeNode(HashMap<K, V> map, Node<K, V>[] tab,
//                                      boolean movable) {
//                int n;
//                if (tab == null || (n = tab.length) == 0)
//                    return;
//                int index = (n - 1) & hash;
//                TreeNode<K, V> first = (TreeNode<K, V>) tab[index], root = first, rl;
//                TreeNode<K, V> succ = (TreeNode<K, V>) next, pred = prev;
//                if (pred == null)
//                    tab[index] = first = succ;
//                else
//                    pred.next = succ;
//                if (succ != null)
//                    succ.prev = pred;
//                if (first == null)
//                    return;
//                if (root.parent != null)
//                    root = root.root();
//                if (root == null || root.right == null ||
//                        (rl = root.left) == null || rl.left == null) {
//                    tab[index] = first.untreeify(map);  // too small
//                    return;
//                }
//                TreeNode<K, V> p = this, pl = left, pr = right, replacement;
//                if (pl != null && pr != null) {
//                    TreeNode<K, V> s = pr, sl;
//                    while ((sl = s.left) != null) // find successor
//                        s = sl;
//                    boolean c = s.red;
//                    s.red = p.red;
//                    p.red = c; // swap colors
//                    TreeNode<K, V> sr = s.right;
//                    TreeNode<K, V> pp = p.parent;
//                    if (s == pr) { // p was s's direct parent
//                        p.parent = s;
//                        s.right = p;
//                    } else {
//                        TreeNode<K, V> sp = s.parent;
//                        if ((p.parent = sp) != null) {
//                            if (s == sp.left)
//                                sp.left = p;
//                            else
//                                sp.right = p;
//                        }
//                        if ((s.right = pr) != null)
//                            pr.parent = s;
//                    }
//                    p.left = null;
//                    if ((p.right = sr) != null)
//                        sr.parent = p;
//                    if ((s.left = pl) != null)
//                        pl.parent = s;
//                    if ((s.parent = pp) == null)
//                        root = s;
//                    else if (p == pp.left)
//                        pp.left = s;
//                    else
//                        pp.right = s;
//                    if (sr != null)
//                        replacement = sr;
//                    else
//                        replacement = p;
//                } else if (pl != null)
//                    replacement = pl;
//                else if (pr != null)
//                    replacement = pr;
//                else
//                    replacement = p;
//                if (replacement != p) {
//                    TreeNode<K, V> pp = replacement.parent = p.parent;
//                    if (pp == null)
//                        root = replacement;
//                    else if (p == pp.left)
//                        pp.left = replacement;
//                    else
//                        pp.right = replacement;
//                    p.left = p.right = p.parent = null;
//                }
//
//                TreeNode<K, V> r = p.red ? root : balanceDeletion(root, replacement);
//
//                if (replacement == p) {  // detach
//                    TreeNode<K, V> pp = p.parent;
//                    p.parent = null;
//                    if (pp != null) {
//                        if (p == pp.left)
//                            pp.left = null;
//                        else if (p == pp.right)
//                            pp.right = null;
//                    }
//                }
//                if (movable)
//                    moveRootToFront(tab, r);
//            }

            /**
             * Splits nodes in a tree bin into lower and upper tree bins,
             * or untreeifies if now too small. Called only from resize;
             * see above discussion about split bits and indices.
             */
//            final void split(HashMap<K, V> map, Node<K, V>[] tab, int index, int bit) {
//                TreeNode<K, V> b = this;
//                // Relink into lo and hi lists, preserving order
//                TreeNode<K, V> loHead = null, loTail = null;
//                TreeNode<K, V> hiHead = null, hiTail = null;
//                int lc = 0, hc = 0;
//                for (TreeNode<K, V> e = b, next; e != null; e = next) {
//                    next = (TreeNode<K, V>) e.next;
//                    e.next = null;
//                    if ((e.hash & bit) == 0) {
//                        if ((e.prev = loTail) == null)
//                            loHead = e;
//                        else
//                            loTail.next = e;
//                        loTail = e;
//                        ++lc;
//                    } else {
//                        if ((e.prev = hiTail) == null)
//                            hiHead = e;
//                        else
//                            hiTail.next = e;
//                        hiTail = e;
//                        ++hc;
//                    }
//                }
//
//                if (loHead != null) {
//                    if (lc <= UNTREEIFY_THRESHOLD)
//                        tab[index] = loHead.untreeify(map);
//                    else {
//                        tab[index] = loHead;
//                        if (hiHead != null) // (else is already treeified)
//                            loHead.treeify(tab);
//                    }
//                }
//                if (hiHead != null) {
//                    if (hc <= UNTREEIFY_THRESHOLD)
//                        tab[index + bit] = hiHead.untreeify(map);
//                    else {
//                        tab[index + bit] = hiHead;
//                        if (loHead != null)
//                            hiHead.treeify(tab);
//                    }
//                }
//            }

            /* ------------------------------------------------------------ */
            // Red-black tree methods, all adapted from CLR

            static <K, V> TreeNode<K, V> rotateLeft(TreeNode<K, V> root,
                                                    TreeNode<K, V> p) {
                TreeNode<K, V> r, pp, rl;
                if (p != null && (r = p.right) != null) {
                    if ((rl = p.right = r.left) != null)
                        rl.parent = p;
                    if ((pp = r.parent = p.parent) == null)
                        (root = r).red = false;
                    else if (pp.left == p)
                        pp.left = r;
                    else
                        pp.right = r;
                    r.left = p;
                    p.parent = r;
                }
                return root;
            }

            static <K, V> TreeNode<K, V> rotateRight(TreeNode<K, V> root,
                                                     TreeNode<K, V> p) {
                TreeNode<K, V> l, pp, lr;
                if (p != null && (l = p.left) != null) {
                    if ((lr = p.left = l.right) != null)
                        lr.parent = p;
                    if ((pp = l.parent = p.parent) == null)
                        (root = l).red = false;
                    else if (pp.right == p)
                        pp.right = l;
                    else
                        pp.left = l;
                    l.right = p;
                    p.parent = l;
                }
                return root;
            }

            static <K, V> TreeNode<K, V> balanceInsertion(TreeNode<K, V> root,
                                                          TreeNode<K, V> x) {
                x.red = true;
                for (TreeNode<K, V> xp, xpp, xppl, xppr; ; ) {
                    if ((xp = x.parent) == null) {
                        x.red = false;
                        return x;
                    } else if (!xp.red || (xpp = xp.parent) == null)
                        return root;
                    if (xp == (xppl = xpp.left)) {
                        if ((xppr = xpp.right) != null && xppr.red) {
                            xppr.red = false;
                            xp.red = false;
                            xpp.red = true;
                            x = xpp;
                        } else {
                            if (x == xp.right) {
                                root = rotateLeft(root, x = xp);
                                xpp = (xp = x.parent) == null ? null : xp.parent;
                            }
                            if (xp != null) {
                                xp.red = false;
                                if (xpp != null) {
                                    xpp.red = true;
                                    root = rotateRight(root, xpp);
                                }
                            }
                        }
                    } else {
                        if (xppl != null && xppl.red) {
                            xppl.red = false;
                            xp.red = false;
                            xpp.red = true;
                            x = xpp;
                        } else {
                            if (x == xp.left) {
                                root = rotateRight(root, x = xp);
                                xpp = (xp = x.parent) == null ? null : xp.parent;
                            }
                            if (xp != null) {
                                xp.red = false;
                                if (xpp != null) {
                                    xpp.red = true;
                                    root = rotateLeft(root, xpp);
                                }
                            }
                        }
                    }
                }
            }

            static <K, V> TreeNode<K, V> balanceDeletion(TreeNode<K, V> root,
                                                         TreeNode<K, V> x) {
                for (TreeNode<K, V> xp, xpl, xpr; ; ) {
                    if (x == null || x == root)
                        return root;
                    else if ((xp = x.parent) == null) {
                        x.red = false;
                        return x;
                    } else if (x.red) {
                        x.red = false;
                        return root;
                    } else if ((xpl = xp.left) == x) {
                        if ((xpr = xp.right) != null && xpr.red) {
                            xpr.red = false;
                            xp.red = true;
                            root = rotateLeft(root, xp);
                            xpr = (xp = x.parent) == null ? null : xp.right;
                        }
                        if (xpr == null)
                            x = xp;
                        else {
                            TreeNode<K, V> sl = xpr.left, sr = xpr.right;
                            if ((sr == null || !sr.red) &&
                                    (sl == null || !sl.red)) {
                                xpr.red = true;
                                x = xp;
                            } else {
                                if (sr == null || !sr.red) {
                                    if (sl != null)
                                        sl.red = false;
                                    xpr.red = true;
                                    root = rotateRight(root, xpr);
                                    xpr = (xp = x.parent) == null ?
                                            null : xp.right;
                                }
                                if (xpr != null) {
                                    xpr.red = (xp == null) ? false : xp.red;
                                    if ((sr = xpr.right) != null)
                                        sr.red = false;
                                }
                                if (xp != null) {
                                    xp.red = false;
                                    root = rotateLeft(root, xp);
                                }
                                x = root;
                            }
                        }
                    } else { // symmetric
                        if (xpl != null && xpl.red) {
                            xpl.red = false;
                            xp.red = true;
                            root = rotateRight(root, xp);
                            xpl = (xp = x.parent) == null ? null : xp.left;
                        }
                        if (xpl == null)
                            x = xp;
                        else {
                            TreeNode<K, V> sl = xpl.left, sr = xpl.right;
                            if ((sl == null || !sl.red) &&
                                    (sr == null || !sr.red)) {
                                xpl.red = true;
                                x = xp;
                            } else {
                                if (sl == null || !sl.red) {
                                    if (sr != null)
                                        sr.red = false;
                                    xpl.red = true;
                                    root = rotateLeft(root, xpl);
                                    xpl = (xp = x.parent) == null ?
                                            null : xp.left;
                                }
                                if (xpl != null) {
                                    xpl.red = (xp == null) ? false : xp.red;
                                    if ((sl = xpl.left) != null)
                                        sl.red = false;
                                }
                                if (xp != null) {
                                    xp.red = false;
                                    root = rotateRight(root, xp);
                                }
                                x = root;
                            }
                        }
                    }
                }
            }

            /**
             * Recursive invariant check
             */
//            static <K, V> boolean checkInvariants(TreeNode<K, V> t) {
//                TreeNode<K, V> tp = t.parent, tl = t.left, tr = t.right,
//                        tb = t.prev, tn = (TreeNode<K, V>) t.next;
//                if (tb != null && tb.next != t)
//                    return false;
//                if (tn != null && tn.prev != t)
//                    return false;
//                if (tp != null && t != tp.left && t != tp.right)
//                    return false;
//                if (tl != null && (tl.parent != t || tl.hash > t.hash))
//                    return false;
//                if (tr != null && (tr.parent != t || tr.hash < t.hash))
//                    return false;
//                if (t.red && tl != null && tl.red && tr != null && tr.red)
//                    return false;
//                if (tl != null && !checkInvariants(tl))
//                    return false;
//                if (tr != null && !checkInvariants(tr))
//                    return false;
//                return true;
//            }
        }

        //扩容
        final Node<K, V>[] resize() {
            return null;
        }


    }


    /**
     * @Description: 树基础结构
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/21
     */
    @Data
    public static class TreeNode {
        String id;
        String parentId;
        String name;
        Object val;
        ArrayList<TreeNode> children = new ArrayList<>();

        public void add(TreeNode node) {
            children.add(node);
        }
    }

    /**
     * @Description: 图的基本定义
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/21
     */
    @Data
    public static class Graph {
        private int size; // 顶点数量
        private int[] vertexs; // 顶点数组
        private int[][] matrix; // 邻接矩阵
        private static final int MAX_WEIGHT = 10000;

        public Graph(int size) {
            super();
            this.size = size;
            vertexs = new int[size];
            for (int i = 0; i < size; i++) {
                vertexs[i] = i;
            }
            matrix = new int[size][size];
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int[] getVertexs() {
            return vertexs;
        }

        public void setVertexs(int[] vertexs) {
            this.vertexs = vertexs;
        }

        // 出度
        public int outDegree(int index) {
            int degree = 0;
            for (int i = 0; i < matrix[index].length; i++) {
                int weight = matrix[index][i];
                if (weight != 0 && weight != MAX_WEIGHT) {
                    degree++;
                }
            }
            return degree;
        }

        // 入度
        public int inDegree(int index) {
            int degree = 0;
            for (int i = 0; i < matrix.length; i++) {
                int weight = matrix[i][index];
                if (weight != 0 && weight != MAX_WEIGHT) {
                    degree++;
                }
            }
            return degree;
        }

        // 获取权值
        public int getWeight(int v1, int v2) {
            int weight = matrix[v1][v2];
            return weight == 0 ? 0 : (weight == MAX_WEIGHT ? -1 : weight);
        }

        // 获取最大出度
        public int maxOutDegree() {
            int maxDegree = 0;
            for (int i = 0; i < matrix.length; i++) {
                int degree = outDegree(i);
                if (maxDegree < degree) {
                    maxDegree = degree;
                }
            }
            return maxDegree;
        }

        // 获取最大入度
        public int maxInDegree() {
            int maxDegree = 0;
            for (int i = 0; i < matrix[0].length; i++) {
                int degree = inDegree(i);
                if (maxDegree < degree) {
                    maxDegree = degree;
                }
            }
            return maxDegree;
        }

        public static void main(String[] args) {
            Graph graph = new Graph(5);
            int[] a0 = new int[]{0, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 6};
            int[] a1 = new int[]{9, 0, 3, MAX_WEIGHT, MAX_WEIGHT};
            int[] a2 = new int[]{2, MAX_WEIGHT, 0, 5, MAX_WEIGHT};
            int[] a3 = new int[]{0, MAX_WEIGHT, MAX_WEIGHT, 0, 1};
            int[] a4 = new int[]{0, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 0};
            graph.matrix[0] = a0;
            graph.matrix[1] = a1;
            graph.matrix[2] = a2;
            graph.matrix[3] = a3;
            graph.matrix[4] = a4;

            System.out.println("v0的出度：" + graph.outDegree(0));
            System.out.println("v0的入度：" + graph.inDegree(0));
            System.out.println("v0到v1的权值：" + graph.getWeight(0, 1));
            System.out.println("v1到v0的权值：" + graph.getWeight(1, 0));
            System.out.println("最大出度：" + graph.maxOutDegree());
            System.out.println("最大入度：" + graph.maxInDegree());
        }
    }

//    @Data
//    @EqualsAndHashCode(callSuper = true)
//    public static class DeptTree extends TreeNode {
//        private String name;
//    }
//
//    public List<DeptTree> getDeptTree(List<DeptTree> depts) {
//        List<DeptTree> treeList = depts.stream()
//                .filter(dept -> !dept.getId().equals(dept.getParentId()))
//                .map(dept -> {
//                    DeptTree node = new DeptTree();
//                    node.setId(dept.getId());
//                    node.setParentId(dept.getParentId());
//                    node.setName(dept.getName());
//                    return node;
//                }).collect(Collectors.toList());
//        return TreeUtil.bulid(treeList, 0);
//    }


    /**
     * @Description: 遍历链表
     * @Param: ListNode node
     * @return: node.val
     * @Author: zhangmt
     * @Date: 2022/3/21
     */
    public void getListNodeLoop(ListNode node) {
        while (node != null) {
            System.out.print(node.val+" ");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * 两层循环实现建树
     *
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public <T extends TreeNode> List<T> bulidTree(List<T> treeNodes, Object root) {

        List<T> trees = new ArrayList<>();

        for (T treeNode : treeNodes) {

            if (root.equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }

            for (T it : treeNodes) {
                if (it.getParentId() == treeNode.getId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.add(it);
                }
            }
        }
        return trees;
    }

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    public <T extends TreeNode> List<T> buildTreeByRecursive(List<T> treeNodes, Object root) {
        List<T> trees = new ArrayList<T>();
        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getParentId())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    public <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
        for (T it : treeNodes) {
            if (treeNode.getId() == it.getParentId()) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }


    /**
     * @Author Dr. zhang
     * @CreateTime 2022/3/14
     * @Description 根据长度，获得指定长度的随机数数组
     */
    public int[] getRandomArrays(int arrayLength, int maxNum) {
        maxNum = maxNum <= 0 ? 100 : maxNum;
        int[] arrays = new int[arrayLength];
        Random a = new Random();
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = Math.abs(a.nextInt(maxNum));
        }
        return arrays;
    }

    /**
     * @Author Dr. zhang
     * @CreateTime 2022/3/14
     * @Description 根据长度和头节点，追加指定长度的节点
     */
    public ListNode getRandomSingleLinked(ListNode listNode, int linkedLength, int maxVal) {
        maxVal = maxVal <= 0 ? 100 : maxVal;
        if (listNode == null) {
            listNode = new ListNode();
        }
        Random a = new Random();
        listNode.val = Math.abs(a.nextInt(maxVal));
        if (linkedLength != 1) {
            listNode.next = new ListNode();
            getRandomSingleLinked(listNode.next, linkedLength - 1, maxVal);
        }
        return listNode;
    }

    /**
     * @Description: 根据树节点，书该，是否为满随机生成树的值
     * @Param:
     * @return:
     * @Author: zhangmt
     * @Date: 2022/3/21
     */
    public TreeNode getRandomTree(TreeNode treeNode, int treehigh, String isFull) {
        if (treeNode == null) {
            treeNode = new TreeNode();
        }
        Random a = new Random();
        treeNode.val = Math.abs(a.nextInt());

        return treeNode;
    }

}
