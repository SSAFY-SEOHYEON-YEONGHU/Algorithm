
class Solution {
    int N;
    int total;
    int[] answer;

    int[][] index;

    int[] dx = {0,-1,1};
    int[] dy = {1,-1,1};
    boolean[] dir = {true,false,true};

    public void makeIndex(){
        int period = 0;
        for(int i=0; i<N; i++){
            index[i][0] = period;
            index[i][1] = period + i;

            answer[period] = i+1;
            index[i][0]++;

            period+= i+1;
        }
    }

    public void simulation(){
        makeIndex();

        int size = N-1;
        int su = N+1;

        int x = N-1;
        int y = index[x][0] - 1;

        while(true){
            for(int k=0; k<3; k++){ // 방향대로
                for(int s=0; s<size; s++){ // size 만큼 채우기
                    x += dx[k];
                    y += dy[k];
                    if(dir[k]) {
                        answer[index[x][0]] = su++;
                        index[x][0]++;
                    }
                    else{
                        answer[index[x][1]] = su++;
                        index[x][1]--;
                    }
                }
                size--;
                if(size == 0) return;
            }
        }

    }


    public int[] solution(int n) {
        if(n==1) return new int[]{1};
        N = n;
        index = new int[N][2];

        total = (1 + n) * (n / 2);
        if(n % 2 != 0) total += ((n / 2) + 1);

        System.out.println(total);

        answer = new int[total];

        simulation();

        return answer;
    }
}