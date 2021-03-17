import java.util.*;
/*
Given a directed Graph with each node having exactly one outgoing edge. Good Node:
      - A node which is marked as good
      - A node which has a path to a good node
  One node in the graph is marked good, 
  you can swap an edge(can both change its start node and end node), find the minimum number of swaps to make all nodes good nodes
   (Swap: n1 -> n2 => n1 -> n3)，
  example: 
        (1. bad) -> (2.bad) -> (3.bad) <- (4.good)
        need to swap: 3,4 
  explaination:
          if we swap 3 and 4, the original graph will be:
          (1. bad) -> (2.bad) -> (4.good) <- (3.bad)
          since 3 now point to a good node, 3 will be good, 
          and since 2 and 1 point to a good node, 1 and 2 will be good too   
  idea:
       find the number of bad nodes that points to no other nodes. once we swap this bad node with a good node, 
       then all nodes connecting to this node will be good nodes
  implementation:
      - generate a graph based on the input, for the graph: Map<Integer, List<Integer>>graph, key is a node, value is the node that the key node point to
      - find the # of node that are bad node and points to no one 
*/
// for a node, 1 is good, 0 is bad
class Node {
    int val;
    int good;
    public Node(int val, int good) {
        this.val = val;
        this.good = good;
    }
}
class swapForAllGoodNode {
   public static int minSwapV2(Node[][]connection) {
	// used to store all nodes in the graph
        Set<Node>set = new HashSet<>();
	// used to store all bad nodes in the graph
        Set<Node>badNode = new HashSet<>();
        // generating the graph
        Map<Node, List<Node>>graph = new HashMap<>();
        for(Node[] node: connection) {
            graph.put(node[0], new ArrayList<>());
        }
        for(Node[] node: connection) {
            graph.get(node[0]).add(node[1]);
            set.add(node[0]);
            set.add(node[1]);
            if(node[0].good == 0) {
                badNode.add(node[0]);
            }
            if(node[1].good == 0) {
                badNode.add(node[1]);
            }
        }
        // find the bad node that points to no one
        List<Node>endBad = new ArrayList<>();
        for(Node n: set) {
            if(n.good == 0 && !graph.containsKey(n)) {
                endBad.add(n);
            }
        }
        //check if all nodes are bad node, if so, throw error
        if(badNode.size() == set.size()) {
            throw new IllegalArgumentException("invalid graph!");
        }
        if(endBad.size()!= 0) {
            return endBad.size();
        }
        return 0;
    }

    public static void main(String[] arg) {
        /*
           (0,0)->(1,0)->(2,0)<-(3,1)
           1 swap for 3rd and 4th node
           (0,0)->(1,0)->(2,1)<-(3,0)
           then all bad nodes has a path to a goof node, then all will be good           
        */
        Node n1 = new Node(0, 0);
        Node n2 = new Node(1, 0);
        Node n3 = new Node(2, 0);
        Node n4 = new Node(3, 1);
        Node[][]graph = new Node[3][2];
        graph[0][0] = n1;
        graph[0][1] = n2;
        graph[1][0] = n2;
        graph[1][1] = n3;
        graph[2][0] = n4;
        graph[2][1] = n3;
        int swaps = minSwapV2(graph);
        System.out.println("swap for graph 1: "+swaps);
        /*
            
                   
         (0,0) ->(2,0) ->(3,0) <- (4,1)
                   |      
            (1,0)->
             swap (4,1) and (3,0), then all will be good node 
        */ 
        Node n6 = new Node(0,0);
        Node n7 = new Node(1,0);
        Node n8 = new Node(2,0);
        Node n9 = new Node(3,0);
        Node n10 = new Node(4,1);
        Node[][]graph_1 = new Node[4][2];
        graph_1[0][0] = n6;
        graph_1[0][1] = n8;
        graph_1[1][0] = n7;
        graph_1[1][1] = n8;
        graph_1[2][0] = n8;
        graph_1[2][1] = n9;
        graph_1[3][0] = n10;
        graph_1[3][1] = n9;
        int swaps_1 = minSwapV2(graph_1);
        System.out.println("swap for graph 2: "+swaps_1);
        /*
            (0,0)->(1,0)->(2,0)->(3,1)
            no need to swap, all nodes already point to a good node 
        */
        Node n_1 = new Node(0,0);
        Node n_2 = new Node(1,0);
        Node n_3 = new Node(2,0);
        Node n_4 = new Node(3,1);
        Node[][]graph_2 = new Node[3][2];
        graph_2[0][0] = n_1;
        graph_2[0][1] = n_2;
        graph_2[1][0] = n_2;
        graph_2[1][1] = n_3;
        graph_2[2][0] = n_3;
        graph_2[2][1] = n_4;
        int swaps_2 = minSwapV2(graph_2);
        System.out.println("swap for graph 3: "+swaps_2);
        /*
             (0,0) -> (1,0) <- (2,0)
	     invalid graph, all are bad nodes 
        */
        Node b_1 = new Node(0,0);
        Node b_2 = new Node(1,0);
        Node b_3 = new Node(2,0);
        Node[][]graph_3 = new Node[2][2];
        graph_3[0][0] = b_1;
        graph_3[0][1] = b_2;
        graph_3[1][0] = b_3;
        graph_3[1][1] = b_2;
        int swap_3 = minSwapV2(graph_3);
        System.out.println("swap for graph 4: "+swap_3);
    }
}
