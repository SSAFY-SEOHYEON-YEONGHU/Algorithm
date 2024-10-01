package implementation;

import java.io.*;
import java.util.*;

public class PGMS_176962_과제진행하기 {
    ArrayList<Node> arr = new ArrayList<>();
    Deque<Node> stack = new ArrayDeque<>();

    ArrayList<String> result = new ArrayList<>();

    class Node{
        String name;
        int starttime;
        int playtime;
        Node(String name, int playtime){
            this.name = name;
            this.playtime = playtime;
        }
        Node(String name, int starttime, int playtime){
            this.name = name;
            this.starttime = starttime;
            this.playtime = playtime;
        }
    }

    public void makeArr(String[][] plans){
        for(int i=0; i<plans.length; i++){
            String[] tmp = plans[i];
            String[] time = tmp[1].split(":");
            arr.add(new Node(tmp[0],60*Integer.parseInt(time[0]) + Integer.parseInt(time[1]) ,Integer.parseInt(tmp[2])));
        }

        Collections.sort(arr, new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                return n1.starttime - n2.starttime;
            }
        });
    }

    public String[] solution(String[][] plans){
        makeArr(plans);

        for(int i=1; i<arr.size(); i++){
            Node front = arr.get(i-1);
            Node back = arr.get(i);
            System.out.println(front.name);
            int diff = back.starttime - front.starttime;
            System.out.println(back.starttime);
            System.out.println(diff);
            if(diff < front.playtime){
                stack.push(new Node(front.name, front.playtime - diff));
            }
            else if((diff == front.playtime)){
                result.add(front.name);
            }
            else{
                result.add(front.name);
                int remain = diff - front.playtime;
                while(!stack.isEmpty()){
                    Node cur = stack.peek();
                    if(cur.playtime <= remain){
                        stack.pop();
                        result.add(cur.name);
                        remain -= cur.playtime;
                    }else{
                        stack.pop();
                        stack.push(new Node(cur.name, cur.playtime - remain));
                        remain = 0;
                    }
                    if(remain == 0) break;
                }
            }
        }
        // 마지막 1개 바로 정답 처리
        result.add(arr.get(arr.size()-1).name);
        // 멈춰두 과제들 순서대로 정답 처리
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            result.add(cur.name);
        }
        // String 배열에 옮겨담기
        String[] answer = new String[result.size()];
        for(int i=0; i<result.size(); i++) answer[i] = result.get(i);

        return answer;
    }
}