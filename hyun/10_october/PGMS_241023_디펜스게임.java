import java.util.*;

class Solution {
    int answer = 0;
    class Node{
        int n,k;
        Node(int n, int k){
            this.n = n;
            this.k = k;
        }
    }
    public void simulation(int n, int k, int[] enemy){
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(n,k));

        for(int i=0; i<enemy.length;i++){
            int size = q.size();
            for(int j=0; j<size; j++){
                Node cur = q.poll();
                if(cur.n <= 0) {
                    answer = Math.max(answer,i-1);
                    continue;
                }
                if(cur.k > 0){
                    q.add(new Node(cur.n, cur.k-1));
                    q.add(new Node(cur.n - enemy[i], cur.k));
                }
                else{
                    q.add(new Node(cur.n - enemy[i], 0));
                }
            }
        }
        if(!q.isEmpty()) answer = enemy.length;
    }
    public int solution(int n, int k, int[] enemy) {
        // int answer = 0;
        simulation(n,k,enemy);
        return answer;
    }
}