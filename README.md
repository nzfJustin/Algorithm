# number-of-path-from-bottom-left-to-bottom-right
/*
    given a MxN matrix, at each point, one can either go right, right up, right bottom, 
    what would be the # of path starting from the bottom left and end at bottom right
    
    harder version:
       now we have a parameter h, a path is considered a valid path if the min height difference within the path from A to B is no less than h,
       what would be the # of valid path from the bottom left to bottom right
       ex: 
          given M = 3，N = 4，H = 1
          return 3. (below are the 3 valid path, 1 represent path)  
              0000 0000 0000
              0010 0100 0110
              1101 1011 1001
    */
    
    I solved it in 2 ways, 1 in dp, 1 in dfs. 
    both have time complexity of O(m*n). space complexity of O(m*n)
    
    if you could think of any optimization, please let me know. Thank you!
    
    
    
