package implementation;

// 각 조각에  동일한 가짓수의 토핑 = 공평
import java.io.*;
        import java.util.*;

public class PGMS_132265_롤케이크자르기 {
    public int solution(int[] topping) {
        int answer = 0;
        HashMap<Integer, Integer> right = new HashMap<>();
        HashMap<Integer, Integer> left = new HashMap<>();

        for(int su : topping){
            if(right.containsKey(su)) right.put(su, right.get(su) + 1);
            else right.put(su, 1);
        }

        for(int su : topping){
            if(left.containsKey(su)) left.put(su, left.get(su) + 1);
            else left.put(su, 1);

            right.put(su, right.get(su) - 1);
            if(right.get(su) == 0) right.remove(su);

            if(left.size() == right.size()) answer++;

        }

        return answer;
    }
}