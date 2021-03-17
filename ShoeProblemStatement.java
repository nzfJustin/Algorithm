import java.util.*; 
/**
* Envision you are working in a shoe store. The manager comes to you with a
* problem. One of the manufacturer's had a problem with their factory and will
* be sending two containers of shoes. One for left shoes and one with right
* shoes.
*
* The shoes are all the same style but of different sizes.
*
* After receiving the shoes the store needs to report the number of shoes that
* are mismatched.
*
* Write a program that will take in two arrays one for left shoe sizes and one
* for right shoe sizes that will then return the number of unmatched shoe's.
*
*
*/
/*
There are a number of rooms with unique numbers in 0, 1, 2, ..., N-1, where N is the total number of the rooms.
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
*/

public class ShoeProblemStatement {
public static int getMismatchedShoes(int[] leftShoeSizes, int[] rightShoeSizes) {
    Map<Integer, Integer>map_1 = new HashMap<>();
    for(int i = 0; i < leftShoeSizes.length; i++) {
        map_1.put(leftShoeSizes[i], map_1.getOrDefault(leftShoeSizes[i], 0)+1);
    }
    int res = 0;
    for(int i=0; i<rightShoeSizes.length; i++) {
        int shoe = rightShoeSizes[i];
        map_1.put(shoe, map_1.getOrDefault(shoe, 0)-1);
    }
    // 7 -> -1, 8 -> 0, 9 -> -1, 10->0, 5->-2
    for(Integer key: map_1.keySet()) {
        res += Math.abs(map_1.get(key));
    }
    return res;
}
public static void main(String[]args) {
   int[] leftShoeSizes = {7,7,8,8,9,10,10};
   int[] rightShoeSizes = {5,5,7,7,7,8,8,9,9,10,10};
   // 2, 1,1 -> 4
   int missing_shoes = getMismatchedShoes(leftShoeSizes, rightShoeSizes);
   System.out.println(missing_shoes);
}
}