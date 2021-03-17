package leetcode;

import entity.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class BinaryTreeDepth {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(10, new TreeNode(9, null, null), new TreeNode(12, new TreeNode(11, null, null), new TreeNode(13, null, null)));

        new TreeNode(0,null,null);
//        System.out.println(new BinaryTreeDepth().maxDepth(node));
        System.out.println(new BinaryTreeDepth().maxDepth(new TreeNode(0,null,null)));


    }

    //采用迭代的方式
    public int maxDepth(TreeNode treeNode){
        if (treeNode == null){
            return 0;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.push(treeNode);
        int depth = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            depth ++;
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }

            }
        }
        return depth;
    }

    //递归的方式

    public int maxDepthRecursive(TreeNode treeNode){
        if (treeNode == null){
            return 0;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.push(treeNode);
        int depth = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            depth ++;
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }

            }
        }
        return depth;
    }


    /**
     * 求最小的深度
     * @param treeNode
     * @return
     */
    public int minDepth(TreeNode treeNode){
        if (treeNode == null){
            return 0;
        }
        int l = minDepth(treeNode.left);
        int r = minDepth(treeNode.right);
        if (treeNode.left == null && treeNode.right != null){
            return 1 + r;
        }
        if (treeNode.left != null && treeNode.right == null){
            return 1 + l;
        }
        int res = 1 + Math.min(l,r);
        return res;
    };


}
