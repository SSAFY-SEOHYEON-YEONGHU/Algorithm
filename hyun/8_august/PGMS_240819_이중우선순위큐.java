package implementation;


import java.io.*;
import java.util.*;

public class PGMS_240819_이중우선순위큐 {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < operations.length; i++){
            String[] op = operations[i].split(" ");
            if(op[0].equals("I")){
                // System.out.println("I");
                arr.add(Integer.parseInt(op[1]));
            }else{
                if(arr.size() == 0) continue;

                int su = Integer.parseInt(op[1]);

                if(su == 1){
                    Collections.sort(arr);
                    arr.remove(arr.size() - 1);

                }else{
                    Collections.sort(arr, Collections.reverseOrder());
                    arr.remove(arr.size() - 1);
                }
            }
        }

        if(arr.size() == 0){
            answer = new int[]{0,0};
        }
        else{
            Collections.sort(arr);
            answer = new int[]{arr.get(arr.size() - 1), arr.get(0)};
        }


        return answer;
    }
}