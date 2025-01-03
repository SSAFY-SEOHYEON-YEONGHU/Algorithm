package implementation;

import java.util.*;

public class PGMS_241022_튜플 {
    String[] arr;

    public void makeArr(String s){
        s = s.substring(1,s.length()-1);
        s = "," + s;
        arr = s.split("\\}");
        Arrays.sort(arr, Comparator.comparingInt(String::length));
    }

    public int[] simulation(){
        HashMap<Integer, Integer> hmap = new HashMap<>(); // 수 , 인덱스

        int idx = 0;
        for(int i=0; i<arr.length; i++){

            String a = arr[i].substring(2,arr[i].length());
            String[] cur = a.split(",");

            for(String su : cur){
                int num = Integer.parseInt(su);
                if(hmap.containsKey(num)) continue;
                else {
                    hmap.put(num, idx++);
                    break;
                }
            }
        }

        int[] answer = new int[idx];
        for(int su : hmap.keySet()){
            answer[hmap.get(su)] = su;
        }

        return answer;
    }

    public int[] solution(String s) {
        makeArr(s);
        return simulation();
    }
}