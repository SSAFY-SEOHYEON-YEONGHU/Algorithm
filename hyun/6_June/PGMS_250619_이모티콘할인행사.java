package dfs;

import java.util.*;

public class PGMS_250619_이모티콘할인행사 {
    int N,E;
    ArrayList<int[]> answers = new ArrayList<>();

    public void getUsers(int[][] discount, int[][] users){
        int[] result = new int[2];

        for(int i=0; i<N; i++){
            int dis = users[i][0];
            int value = users[i][1];
            int sum = 0;

            for(int j=0; j<E; j++){
                if(dis <= discount[j][0]) sum += discount[j][1];
            }

            if(sum >= value) result[0]++;
            else result[1] += sum;
        }

        //System.out.println(result[0] + " " + result[1]);
        // for(int i=0; i<E; i++) System.out.print(discount[i][1]);
        // System.out.println("----------" + result[1]);
        answers.add(result);
    }

    public void comb(int cnt, int[][] discount,int[][] users, int[] emoticons){
        if(cnt == E){
            getUsers(discount, users);
            return;
        }

        for(int i=10; i<=40; i+=10){
            discount[cnt][0] = i;
            discount[cnt][1] = (emoticons[cnt] * (100 - i)) / 100;

            comb(cnt+1, discount, users, emoticons);
        }
    }
    public int[] simulation(int[][] users, int[] emoticons){
        comb(0, new int[E][2], users, emoticons);

        Collections.sort(answers, new Comparator<int[]>(){
            @Override
            public int compare(int[] n1, int[] n2){
                if(n1[0] == n2[0]) return n2[1] - n1[1];
                return n2[0] - n1[0];
            }
        });

        return answers.get(0);
    }

    public int[] solution(int[][] users, int[] emoticons) {
        N = users.length;
        E = emoticons.length;

        return simulation(users, emoticons);

    }
}