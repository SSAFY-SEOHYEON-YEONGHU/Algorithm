import java.util.*;

class Solution {
    public boolean func(int n, long su, int[] times){
        long sum = 0;
        for(int t : times){
            sum += (su / t);
            if(sum >= n) break;

        }
        return sum>= n ? true : false;
    }

    public long solution(int n, int[] times) {
        long low = -1;
        long high = Long.MAX_VALUE;

        while(low+1 < high){
            long mid = (low + high) / 2;
            if(func(n,mid,times) == false) low = mid;
            else high = mid;
        }

        return high;
    }
}