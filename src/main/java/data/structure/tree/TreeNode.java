package data.structure.tree;

public class TreeNode<Data> {
    Data data;
    TreeNode leftChildTree;
    TreeNode rightChildTree;

    public TreeNode(Data data) {
        this.data = data;
    }
}
