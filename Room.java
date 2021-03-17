/*There are a number of rooms with unique numbers in 0, 1, 2, ..., N-1, where N is the total number of the rooms.
Eech room may have some keys that you can use to unlock other rooms. The keys are clearly labeled the room numbers
that they can open.

Initially, all the rooms are locked (except for room 0). You can walk back and forth between rooms freely.

You are asked to develop a program to return true if and only if you can enter every room.

Example 1:
4 rooms:
Room #: keys in the rooms
------------------------------
Room 0: 1
Room 1: 2
Room 2: 3
Room 3:
------------------------------
Return: true
Explanation:
We start in room 0, and pick up key 1.
We then go to room 1, and pick up key 2.
We then go to room 2, and pick up key 3.
We then go to room 3.
Since we are able to go to every room, we return true.

Example 2:
Input: [[1,3],[3,0,1],[2],[0]]
          0,     1,    2,  3
*/

import java.util.*;

public class Room {
    public static boolean Room(int[][]arr) {
        if(arr == null || arr.length == 0) {
            return false;
        }
        boolean[]valid = new boolean[arr.length];
        boolean[]visited = new boolean[arr.length];
        // valid[0] = true;
        // visited[0] = true;
        dfs(0, arr, valid, visited);
        for(int i=0; i<valid.length; i++) {
            if(!valid[i]) {
                return false;
            }
        }
        return true;
    }
    public static void dfs(int cur, int[][]arr, boolean[]valid, boolean[]visited) {
        if(visited[cur]) {
            return;
        }
        visited[cur] = true;
        valid[cur] = true;
        int[]rooms = arr[cur];
        for(int room: rooms) {
            dfs(room, arr, valid, visited);
        }
    }
    public static boolean bfs(int[][]arr) {
        Queue<Integer>q = new LinkedList<>();
        boolean[]valid = new boolean[arr.length];
        boolean[]visited = new boolean[arr.length];
        q.offer(0);
        while(!q.isEmpty()) {
            int cur = q.poll();
            valid[cur] = true;
            visited[cur] = true;
            int[]rooms = arr[cur];
            for(int r: rooms) {
                if(!visited[r]) {
                    q.offer(r);
                }
            }
        }
        for(int i=0; i<valid.length; i++) {
            if(!valid[i]) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[]args) {
        int[][]arr = {{1,3},{3,0,1},{2},{2}};
        boolean canVisit = Room(arr);
        System.out.println(canVisit);
        boolean canVisit_2 = bfs(arr);
        System.out.println("second approach: "+canVisit_2);
    }
}
