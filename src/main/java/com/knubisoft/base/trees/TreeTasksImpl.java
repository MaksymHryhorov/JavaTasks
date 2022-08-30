package com.knubisoft.base.trees;

import java.util.ArrayList;
import java.util.List;


public class TreeTasksImpl implements TreeTasks {

    @Override
    public boolean isSameTree(TreeNode node1, TreeNode node2) {
        /*if (node1.equals(node2)) {
            return true;
        }*/

        if (node1 == null & node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        return node1.val == node2.val && isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right);

    }

    @Override
    public List<Integer> inorderTraversal(TreeNode node) {
        List<Integer> integerList = new ArrayList<>();

        if (node == null) {
            return integerList;
        }

        integerList.addAll(inorderTraversal(node.left));
        integerList.add(node.val);
        integerList.addAll(inorderTraversal(node.right));

        return integerList;
    }

    @Override
    public boolean isSymmetric(TreeNode node) {
        if (node == null) {
            return true;
        }

        return isSymmetric(node.left, node.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    @Override
    public int maxDepth(TreeNode node) {

        return node == null ? 0 : 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
    }

    @Override
    public boolean hasPathSum(TreeNode node, int targetSum) {
        if (node == null) {
            return false;
        }

        if (node.left == null && node.right == null && targetSum - node.val == 0) {
            return true;
        }

        return hasPathSum(node.right, targetSum - node.val) ||
                hasPathSum(node.left, targetSum - node.val);
    }

    @Override
    public TreeNode invertTree(TreeNode node) {
        if (node == null) {
            return null;
        }

        TreeNode leftNode = invertTree(node.left);

        node.left = invertTree(node.right);
        node.right = leftNode;

        return node;
    }

    @Override
    public int sumOfLeftLeaves(TreeNode node) {
/*
        return node.left.val + node.right.val != 0 ? node.val : 0;
*/
        int left = 0;
        int right = 0;

        if (node.left != null) {
            left = node.left.left == null && node.left.right == null ? node.left.val : sumOfLeftLeaves(node.left);
        }

        if (node.right != null) {
            right = sumOfLeftLeaves(node.right);
        }

        return left + right;
    }

    @Override
    public TreeNode mergeTrees(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return node1 == null ? node2 : node1;
        }

        /*TreeNode treeNode = new TreeNode(node1.val += node2.val);

        treeNode.left = mergeTrees(node1.left, node2.left);
        treeNode.right = mergeTrees(node2.right, node2.right);
        return treeNode;*/

        node1.val += node2.val;
        node1.left = mergeTrees(node1.left, node2.left);
        node1.right = mergeTrees(node1.right, node2.right);

        return node1;
    }
}
