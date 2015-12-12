package com.henu.smp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liyngu on 10/31/15.
 */
public class SmpForest<E> {
    private HashMap<E, TreeNode> dataMap = new HashMap<>();
    private E root;
    private E focus;

    public void addChild(E parent, E elem) {
        TreeNode parentNode = dataMap.get(parent);
        if (parentNode == null || parent == null) {
            return;
        }
        TreeNode childNode = parentNode.getLeftChild();
        if (childNode == null) {
            TreeNode node = new TreeNode(elem);
            parentNode.setLeftChild(node);
            node.setParent(parentNode);
            dataMap.put(elem, node);
        } else {
            addSibling(childNode.getElem(), elem);
        }
    }

    public void addSibling(E sibling, E elem) {
        TreeNode siblingNode = dataMap.get(sibling);
        if (siblingNode == null || sibling == null) {
            return;
        }
        TreeNode nextSiblingNode = siblingNode.getRightChild();
        while (nextSiblingNode != null) {
            siblingNode = nextSiblingNode;
            nextSiblingNode = siblingNode.getRightChild();
        }
        TreeNode node = new TreeNode(elem);
        siblingNode.setRightChild(node);
        node.setParent(siblingNode);
        dataMap.put(elem, node);
    }

    public List<TreeNode> getChilds(TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        List<TreeNode> nodes = new ArrayList<>();
        while (node != null) {
            nodes.addAll(getChilds(node.getRightChild()));
            nodes.add(node);
            node = node.getLeftChild();
        }
        return nodes;
    }

    public <T extends E> List<T> getChildsByClass(E elem, Class<T> cls) {
        List<T> items = new ArrayList<>();
        TreeNode root = dataMap.get(elem);
        List<TreeNode> nodes = getChilds(root);
        nodes.remove(root);
        for (TreeNode node : nodes) {
            E e = node.getElem();
            if (cls.isInstance(e)) {
                items.add((T) e);
            }
        }
        return items;
    }

    public List<TreeNode> getSiblings(TreeNode node) {
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode preNode = node.getParent();
        while (preNode != null && preNode.getRightChild() == node) {
            node = preNode;
            preNode = preNode.getParent();
        }
        while (node != null) {
            nodes.add(node);
            node = node.getRightChild();
        }
        return nodes;
    }

    public <T extends E> List<T> getSiblingsByClass(E elem, Class<T> cls) {
        List<T> items = new ArrayList<>();
        TreeNode root = dataMap.get(elem);
        List<TreeNode> nodes = getSiblings(root);
        nodes.remove(root);
        for (TreeNode node : nodes) {
            E e = node.getElem();
            if (cls.isInstance(e)) {
                items.add((T) e);
            }
        }
        return items;
    }


    public void rollbackFocus() {
        TreeNode node = dataMap.get(focus);
        focus = getParent(getParent(node.getElem()));
    }

    public E getParent(E elem) {
        if (elem == null) {
            return null;
        }
        TreeNode node = dataMap.get(elem);
        TreeNode parent = node.getParent();
        if (parent == null) {
            return null;
        }
        while (parent.getRightChild() == node) {
            node = parent;
            parent = parent.parent;
        }
        return parent.getElem();
    }

    public E getChild(E elem) {
        TreeNode node = dataMap.get(elem);
        return node.getLeftChild().getElem();
    }

    public void setRoot(E elem) {
        this.root = elem;
        TreeNode node = new TreeNode(elem);
        dataMap.put(elem, node);
    }

    public E getRoot() {
        return root;
    }

    public void setFocus(E focus) {
        this.focus = focus;
    }

    public E getFocus() {
        return focus;
    }

    /**
     * 二叉树树的数据结构，提供左右孩子和父节点的操作
     * 使用左孩子又兄弟的存储法存储需要存储的菜单森林
     */
    private class TreeNode {
        private E elem;
        private TreeNode leftChild;
        private TreeNode rightChild;
        private TreeNode parent;

        public TreeNode(E elem) {
            this.elem = elem;
        }

        public E getElem() {
            return elem;
        }

        public TreeNode getParent() {
            return parent;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }

        public TreeNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(TreeNode leftChild) {
            this.leftChild = leftChild;
        }

        public TreeNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(TreeNode rightChild) {
            this.rightChild = rightChild;
        }
    }
}
