import java.util.*;


class numPath{
    /*
    给一个 M*N 矩阵，每一点只能往右上、右、右下走，问从左下角(A)走到右下角(B)共 有几种走法。
    加强版:
    现在有另一个参数 H，如果从 A 到 B 之间 path 的高度不小于 H 才算是 valid path，问 共有几种走法。
    比如 M = 3，N = 4，H = 1 就有如下三种走法 (1 代表走的路): 
    0000 0000 0000
    0010 0100 0110
    1101 1011 1001
    */
    public static int numOfWaysHard(int[][] matrix, int h) {
        int m = matrix.length;
        int n = matrix[0].length;
        return helper(m, n) - helper(h,n);
    }
    
    private static int helper(int m, int n) {
        if(m == 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[m-1][0] = 1;
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                dp[i][j] +=  dp[i][j-1];
                if (i > 0) {
                    dp[i][j] += dp[i-1][j-1];
                }
                if (i < m-1) {
                    dp[i][j] += dp[i+1][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
    /*M*N的矩阵，从左下走到右下有多少种（可以向右，右下，右上走）*/
    public static int[][]dir = {{0,1},{1,1},{-1,1}};
    public static int numOfWays(int[][]matrix, int height) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<List<Integer>>res = new ArrayList<>();
        List<Integer>list = new ArrayList<>();
        dfs(matrix, m-1, 0, list, res, m, n, Integer.MAX_VALUE, Integer.MIN_VALUE, height);
        for(List<Integer>l: res) {
            System.out.println(l);
        }
        return res.size();
    }
    // each cell's value represent the # of ways to reach this cell from bottom left corner 
    // ways here is used to store the previous cell's value
    // so that we can update current cell's value based on previous cell's value (just increment by 1)
    public static void dfs(int[][]matrix, int i, int j, List<Integer>list, List<List<Integer>>res, int m, int n, int small, int large, int height) {
        if(i==m && j==n && large-small >= height*m) {
            //System.out.println(small+","+large);
            res.add(new ArrayList<Integer>(list));
            small = Integer.MAX_VALUE;
            large = Integer.MIN_VALUE;
            return;
        }
        if(i<0 || j<0 || i>=matrix.length || j>=matrix[0].length) {return;}
        list.add(m*i+j);
        small = Math.min(small, m*i+j);
        large = Math.max(large, m*i+j);
        for(int[]d: dir) {
            dfs(matrix, i+d[0], j+d[1], list, res, m, n, small, large, height);
        }
        list.remove(list.size()-1);
    }
    public static void main(String[] arg) {
        //System.out.println("local_test now:"+local_test);
        int[][]matrix = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        int ways = numOfWays(matrix, 1);
        System.out.println(ways);
        int anotherway = numOfWaysHard(matrix, 0);
        System.out.println("the answer is: "+anotherway);
    }
}