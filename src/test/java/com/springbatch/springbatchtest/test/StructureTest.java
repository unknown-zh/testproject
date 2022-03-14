package com.springbatch.springbatchtest.test;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class StructureTest {

    //单链表
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

    //双链表
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

    //遍历链表
    public void getListNodeLoop(ListNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    //树基础结构
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

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class DeptTree extends TreeNode {
        private String name;
    }

    public List<DeptTree> getDeptTree(List<DeptTree> depts) {
        List<DeptTree> treeList = depts.stream()
                .filter(dept -> !dept.getId().equals(dept.getParentId()))
                .map(dept -> {
                    DeptTree node = new DeptTree();
                    node.setId(dept.getId());
                    node.setParentId(dept.getParentId());
                    node.setName(dept.getName());
                    return node;
                }).collect(Collectors.toList());
        return TreeUtil.bulid(treeList, 0);
    }


    @UtilityClass
    public class TreeUtil {
        /**
         * 两层循环实现建树
         *
         * @param treeNodes 传入的树节点列表
         * @return
         */
        public <T extends TreeNode> List<T> bulid(List<T> treeNodes, Object root) {

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
        public <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Object root) {
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
    }


    /**
     * @Author Dr. zhang
     * @CreateTime 2022/3/14
     * @Description 跳表
     */
    //一级索引
    public static List indexOne;
    //二级索引
    public static List indexTwo;
    //三级索引
    public static List indexThree;

    public class SkipList {

    }


    /**
     * @Author Dr. zhang
     * @CreateTime 2022/3/14
     * @Description 根据长度，获得指定长度的随机数数组
     */
    public int[] getRandomArrays(int arrayLength) {
        int[] arrays = new int[arrayLength];
        Random a = new Random();
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = Math.abs(a.nextInt());
        }
        return arrays;
    }

    /**
     * @Author Dr. zhang
     * @CreateTime 2022/3/14
     * @Description 根据长度和头节点，追加指定长度的节点
     */
    public ListNode getRandomSingleLinked(ListNode listNode, int linkedLength) {
        if (listNode == null) {
            listNode = new ListNode();
        }
        Random a = new Random();
        listNode.val = Math.abs(a.nextInt());
        if (linkedLength != 1) {
            listNode.next = new ListNode();
            getRandomSingleLinked(listNode.next, linkedLength - 1);
        }
        return listNode;
    }

    //打印单链表
    public void printList(ListNode head) {
        String str = "";
        for (ListNode n = head; n != null; ) {
            str = str + " " + n.val;
            n = n.next;
        }
        System.out.println(str);
    }


    public DeptTree getRandomTree(DeptTree deptTree, int treehigh, String isFull) {
        if (deptTree == null) {
            deptTree = new DeptTree();
        }
        Random a = new Random();
        deptTree.val = Math.abs(a.nextInt());

        return deptTree;
    }

}
