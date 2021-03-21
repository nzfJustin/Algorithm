import java.util.*;

/*
    Given a multiset (set that allows for multiple instances of same value), 
    partition it into two multisets A and B 
    such that the sum of A is greater than that of B. Return A. 
    If more than one such As exists, return the one with minimal size.
    Input:
        nums = [4, 5, 2, 3, 1, 2]
    Output:
        [4, 5]
    explain:
        We can divide the numbers into two subsets A = [4, 5] and B = [1, 2, 2, 3]. 
        The sum of A is 9 which is greater than the sum of B which is 8. 
        There are other ways to divide but A = [4, 5] is of minimal size of 2.
    implementation:
        1. sort the input in descending order
        2. get the sum of the input multiset
        3. iterate from the beginning, calculate cur_sum, if cur_sum >= sum-cur_sum, return
    time complexity:
        O(nlogn) for sorting
*/

public class divideBox {
    public static List<Integer> divide_multi_box(List<Integer>boxes) {
        if (boxes == null || boxes.size() == 0) {
            throw new IllegalArgumentException("invalid input");
        }

        List<Integer>res = new ArrayList<>();
        Collections.sort(boxes, Collections.reverseOrder());
        
        int sum = 0;
        for(Integer box: boxes) {
            sum += box;
        }

        int curSum = 0;
        for (int i = 0; i < boxes.size(); i++) {
            if (curSum >= sum - curSum) {
                break;
            }
            curSum += boxes.get(i);
            res.add(boxes.get(i));
        }

        return res;
    }
    public static void main(String[]args) {
        int[]arr = {4,5,1,3,2,2};
        List<Integer>boxes = new ArrayList<>();
        for(int i: arr) {
            boxes.add(i);
        }
        List<Integer>res = divide_multi_box(boxes);
        System.out.println(res);
    }
}