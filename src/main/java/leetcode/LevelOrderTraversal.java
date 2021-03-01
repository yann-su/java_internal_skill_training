package leetcode;

import entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(10);
        TreeNode left = new TreeNode(7);
        TreeNode right = new TreeNode(11);
        treeNode.left = left;
        treeNode.right = right;
        System.out.println(treeNode);
        LevelOrderTraversal levelOrderTraversal = new LevelOrderTraversal();
        List<List<Integer>> list = levelOrderTraversal.levelOrder(treeNode);
        System.out.println(list);


    }


    //按层序
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> queueList = new ArrayList<>();
        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> queueListOne = new ArrayList<>();
            //提前使队列先出栈，同一批次的
            for (int i = 0; i < size;i ++){
                TreeNode poll = queue.poll();
                queueListOne.add(poll.val);
                //此处是
                if (poll.left != null){
                    queue.add(poll.left);
                }
                if (poll.right != null){
                    queue.add(poll.right);
                }
            }
            queueList.add(queueListOne);
            System.gc();
        }
        return queueList;
    }



}
