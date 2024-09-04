package bfs;

import java.io.*;
import java.util.*;

public class PGMS_43163_단어변환 {
    class Node{
        String word;
        int cnt;
        Node(String word, int cnt){
            this.word = word;
            this.cnt = cnt;
        }
    }
    public int changeCnt(String a, String b){
        int sum = 0;
        for(int i=0; i<a.length();i++){
            if(a.charAt(i) != b.charAt(i)) sum++;
        }
        return sum;
    }

    public int solution(String begin, String target, String[] words) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];

        q.add(new Node(begin,0));
        for(int i=0; i<words.length;i++) {
            if(words[i].equals(begin)){
                visited[i] = true;
                break;
            }
        }

        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.word.equals(target)) return cur.cnt;

            for(int i=0; i<words.length;i++) {
                if(changeCnt(cur.word, words[i]) > 1 || visited[i]) continue;
                q.add(new Node(words[i], cur.cnt + 1));
                visited[i] = true;
            }
        }
        return 0;
    }
}