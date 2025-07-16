package implementation;

import java.io.*;
import java.util.*;

public class BOJ_250716_거짓말 {
    static int N,M;
    static Queue<Integer> space = new ArrayDeque<>();

    static ArrayList<Integer>[] person;
    static ArrayList<Integer>[] party;

    static void simulation(){
        boolean[] personB = new boolean[N+1];
        boolean[] partyB = new boolean[M+1];

        boolean isFlag = true;

        while(!space.isEmpty()){
            int size = space.size();

            if(isFlag){ // 사람이 참여한 파티장 번호 담기
                for (int i = 0; i < size; i++) {
                    int personNum = space.poll();
                    for(int pty : person[personNum]){
                        if(partyB[pty]) continue;
                        space.add(pty);
                        partyB[pty] = true;
                    }
                }
                isFlag = !isFlag;
            }
            else{
                for (int i = 0; i < size; i++) {
                    int partyNum = space.poll();
                    for(int per : party[partyNum]){
                        if(personB[per]) continue;
                        space.add(per);
                        personB[per] = true;
                    }
                }
                isFlag = !isFlag;
            }
        }

        int answer = 0;
        for (int i = 1; i <= M; i++) {
            if(!partyB[i]) answer++;
        }

        System.out.println(answer);

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        person = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            person[i] = new ArrayList<>();
        }
        party = new ArrayList[M+1];
        for (int i = 0; i <= M; i++) {
            party[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        for (int i = 0; i < a; i++) {
            space.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            for (int j = 0; j < a; j++) {
                int su = Integer.parseInt(st.nextToken());
                party[i].add(su);
                person[su].add(i);
            }
        }

        //
        simulation();
    }
}
