package implementation;

public class PGMS_241009_행렬테두리회전하기 {
    int[][] map;

    int[] dx = {0,1,0,-1};
    int[] dy = {1,0,-1,0};

    int idx = 0;
    int[] answer;

    public void rotate(int row, int col, int x, int y){
        int ans = Integer.MAX_VALUE;

        int end = col;
        boolean change = false;

        int dir = 0;
        int before = map[x][y];
        for(int k=0; k<4; k++){
            for(int i=0; i<end; i++){
                x += dx[dir];
                y += dy[dir];
                int cur = map[x][y];
                map[x][y] = before;
                before = cur;

                ans = Math.min(ans, map[x][y]);
            }
            dir++;
            change = !change;
            if(!change) end = col;
            else end = row;

        }

        answer[idx++] = ans;

    }

    public void simulation(int[][] queries){
        for(int[] q : queries){
            rotate(q[2]-q[0], q[3]-q[1], q[0]-1, q[1]-1);
        }
    }


    public int[] solution(int rows, int columns, int[][] queries) {

        map = new int[rows][columns];
        int num = 1;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                map[i][j] = num++;
            }
        }

        answer = new int[queries.length];

        simulation(queries);

        return answer;
    }
}