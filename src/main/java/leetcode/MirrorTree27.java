package leetcode;

import entity.TreeNode;

public class MirrorTree27 {


    public static void main(String[] args) {

        TreeNode treeNode = new TreeNode(10,
                new TreeNode(8,new TreeNode(7),new TreeNode(9)),
                new TreeNode(12,new TreeNode(11),new TreeNode(13))
        );

        TreeNode mirrorTree = mirrorTree(treeNode);
        System.out.println(mirrorTree);

    }


    public static TreeNode mirrorTree(TreeNode treeNode){
        if (treeNode == null) return null;
        //数组反转，交换位置即可
        TreeNode tamp = treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = tamp;
        //递归
        //       10
        //       /\
        //     8   12
        //    / \  /\
        //   7  9 11 13
        mirrorTree(treeNode.left);
        mirrorTree(treeNode.right);
        return treeNode;
    }




}
