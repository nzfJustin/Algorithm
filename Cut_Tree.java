import java.util.*;

import javax.print.event.PrintEvent;
  /*
   Cut Tree
给一个 tree 的数据结构，每一个 node 可以有多个子节点。
定义一个操作 break, 就是把某一个 edge 切断，这样一个树会被切成两个小树，并且要求每个小树里面要有偶数个node，否则不可以这样切。
现在给一个 root, 要求尽可能把这个树切最多的小树，然后返回所有小树的root。如果这个树不能被切小，throw an exception.

a method to calculate the # of node within a tree 
in order 
*/  
class TreeNode {
    int val;
    List<TreeNode>lists;
    public TreeNode(int v) {
        this.val = v;
        lists = new ArrayList<>();
    }
}
class Cut_Tree {
    public static int numOfNode(TreeNode root) {
        int[] num = {0};
        numOfNodes(root, num);
        return num[0];
    }
    public static void numOfNodes(TreeNode root, int[] sum) {
        if(root != null) {
            sum[0]+=1;
        }
        List<TreeNode>list = root.lists;
        if(list != null) {
            for(TreeNode n: list) {
                numOfNodes(n, sum);
            }
        }
    }
    // public static void throwError() {
    //     throw new NumberFormatException();
    // }
    public static List<TreeNode> cut(TreeNode root) {
        int total = numOfNode(root);
        int remainder = total % 2;
        if(remainder == 1) {
            //throw new IllegalArgumentException();
            throw new AssertionError();
        }
        List<TreeNode>result = new ArrayList<>();
        cutTree(root,result);
        return result;
    }
    public static void cutTree(TreeNode root, List<TreeNode>res) {
        if(root == null) {
            return;
        }
       for(TreeNode node: root.lists) {
           cutTree(node, res);
       }
       int val =numOfNode(root);
       if(val %2 == 0) {
           res.add(root);
       }
    }
    
    public static void main(String[]args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);  
        TreeNode n3 = new TreeNode(3);  
        TreeNode n4 = new TreeNode(4);  
        TreeNode n5 = new TreeNode(5);  
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);  
       n1.lists.add(n2);
       n2.lists.add(n3);
       n3.lists.add(n4);
       n2.lists.add(n5);
       n5.lists.add(n6);
       n6.lists.add(n7);
       n6.lists.add(n8);
       int num = numOfNode(n1);
       System.out.println(num);
       List<TreeNode>node = new ArrayList<>();
       node = cut(n1);
       for(TreeNode n: node){
           System.out.print(n.val+",");
       }
    //    System.out.println(num);
        System.out.println("hello");
    }
}