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
        int[][] visited = new int[enemy.length+1][k+1]; // 라운드, k사용횟수
        for(int i=0; i<enemy.length; i++)
            for(int j=0;j<=k;j++)
                visited[i][j] = -1;

        visited[0][k] = n;
        q.add(new Node(n,k));

        for(int i=0; i<enemy.length;i++){
            int size = q.size();
            for(int j=0; j<size; j++){
                Node cur = q.poll();
                if(cur.n <= 0) {
                    answer = Math.max(answer,i);
                    continue;
                }
                if(cur.k > 0){
                    if(visited[i+1][cur.k-1] < cur.n ){
                        visited[i+1][cur.k-1] = cur.n;
                        q.add(new Node(cur.n, cur.k-1));
                    }
                    if(visited[i+1][cur.k] < cur.n-enemy[i]){
                        visited[i+1][cur.k] = cur.n-enemy[i];
                        q.add(new Node(cur.n - enemy[i], cur.k));
                    }
                }
                else{
                    if(visited[i+1][0] < cur.n-enemy[i]){
                        visited[i+1][0] = cur.n-enemy[i];
                        q.add(new Node(cur.n - enemy[i], 0));
                    }
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