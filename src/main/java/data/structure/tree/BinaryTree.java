package data.structure.tree;


//创建二叉树

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;


public class BinaryTree<Data> {

    public TreeNode<Data> createBinaryTreeByArray(Data[] arr, int index) {
        TreeNode<Data> node = null;
        if (index < arr.length) {
            Data data = arr[index];
            if (data != null) {
                node = new TreeNode<>(data);
                //创建左右子树，利用父子节点的关系，进行递归运算
                node.leftChildTree = createBinaryTreeByArray(arr, 2 * index + 1);
                node.rightChildTree = createBinaryTreeByArray(arr, 2 * index + 2);
            }
        }
        return node;
    }

    @SuppressWarnings("unchecked")
    public void preOrderTree(TreeNode<Data> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderTree(node.leftChildTree);
            preOrderTree(node.rightChildTree);
        }
    }

    @SuppressWarnings("unchecked")
    public void inOrderTree(TreeNode<Data> node) {
        if (node != null) {
            inOrderTree(node.leftChildTree);
            System.out.print(node.data + " ");
            inOrderTree(node.rightChildTree);
        }
    }

    @SuppressWarnings("unchecked")
    public void postOrderTree(TreeNode<Data> node) {
        if (node != null) {
            postOrderTree(node.leftChildTree);
            postOrderTree(node.rightChildTree);
            System.out.print(node.data + " ");
        }
    }

    public void levelOrderTree(TreeNode<Data> root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode<Data>> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<Data> node = queue.poll();
            System.out.print(node.data + " ---> ");
            if (node.leftChildTree != null) {
                queue.add(node.leftChildTree);
            }
            if (node.rightChildTree != null) {
                queue.add(node.rightChildTree);
            }
        }
    }

    public TreeNode<Data> inverseTreee(TreeNode<Data> node) {
        if (node == null) {
            return null;
        }
        TreeNode<Data> tmp = null;
        tmp = node.rightChildTree;
        node.rightChildTree = node.leftChildTree;
        node.leftChildTree = tmp;
        inverseTreee(node.leftChildTree);
        inverseTreee(node.rightChildTree);
        return node;
    }



    public static boolean isBalanced(TreeNode root) {
        if (root==null) return true;
        int dep=dfs(root);
        if (dep==-1){
            return false;
        }else{
            return true;
        }
    }
    public static int dfs(TreeNode root){
        if (root==null) return 0;
        int left=dfs(root.leftChildTree);//分别遍历左右子树
        int right=dfs(root.rightChildTree);
        if (left==-1 || right==-1) return -1;//如果左右子树高度相差大于1,直接返回-1
        left++;//计算左右子树高度,走了一层因此加1
        right++;
        if (Math.abs(left-right)>1) return -1;
        return Math.max(right,left);//返回左右子树最大深度作为当前节点的深度
    }


    @Test
    public void binaryTree(){
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Integer[] arr2 = new Integer[]{1,2,2,3,3,null,null,4,4};
        TreeNode<Integer> binaryTreeByArray2 = binaryTree.createBinaryTreeByArray(arr2, 0);
        boolean balanced = isBalanced(binaryTreeByArray2);
        System.out.println(balanced);
//

//        Integer[] arr3 = new Integer[]{3,9,20,null,null,15,7};
//        TreeNode<Integer> binaryTreeByArray3 = binaryTree.createBinaryTreeByArray(arr3, 0);
//        boolean balanced1 = isBalanced(binaryTreeByArray3);
//        System.out.println(balanced1);
    }


    public static void main(String[] args) {

        Integer[] arr = new Integer[]{3, 9, 20, null, null, 15, 7};
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        TreeNode<Integer> binaryTreeByArray = binaryTree.createBinaryTreeByArray(arr, 0);
        binaryTree.preOrderTree(binaryTreeByArray);
        System.out.println();
        binaryTree.inOrderTree(binaryTreeByArray);
        System.out.println();
        binaryTree.postOrderTree(binaryTreeByArray);
        System.out.println();
        binaryTree.levelOrderTree(binaryTreeByArray);
        System.out.println();
        TreeNode<Integer> integerTreeNode = binaryTree.inverseTreee(binaryTreeByArray);
        binaryTree.preOrderTree(integerTreeNode);
        System.out.println();

        Integer[] arr2 = new Integer[]{1,2,2,3,3,null,null,4,4};
        Integer[] arr3 = new Integer[]{3,9,20,null,null,15,7};
        TreeNode<Integer> binaryTreeByArray2 = binaryTree.createBinaryTreeByArray(arr2, 0);
        TreeNode<Integer> binaryTreeByArray3 = binaryTree.createBinaryTreeByArray(arr3, 0);
        boolean balanced = isBalanced(binaryTreeByArray2);
        boolean balanced1 = isBalanced(binaryTreeByArray3);
        System.out.println(balanced);
        System.out.println(balanced1);


    }

}

