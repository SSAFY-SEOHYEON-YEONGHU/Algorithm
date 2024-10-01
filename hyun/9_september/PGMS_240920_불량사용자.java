package backtracking;

import java.util.*;

public class PGMS_240920_불량사용자 {
    String[] users;
    String[] bans;
    ArrayList<Node>[] banList;
    HashMap<Integer, Integer> ansList = new HashMap<>();

    int answer = 0;

    class Node{
        String ban;
        int idx;
        Node(String ban, int idx){
            this.ban = ban;
            this.idx = idx;
        }
    }

    public void makeBanList(){
        for(int a=0; a<bans.length; a++){
            String banned = bans[a];

            for(int b =0; b<users.length; b++){
                String user = users[b];
                if(banned.length() == user.length()){
                    boolean isPossible = true;
                    for(int i=0; i<banned.length(); i++){
                        if(banned.charAt(i) == '*') continue;
                        if(user.charAt(i) != banned.charAt(i)){
                            isPossible = false;
                            break;
                        }
                    }
                    if(isPossible) banList[a].add(new Node(user, b));
                }
            }
        }
    }

    public void dfs(int cnt, int made){
        if(cnt == bans.length){
            if(!ansList.containsKey(made)) ansList.put(made,1);
            return;
        }

        for(int i=0; i<banList[cnt].size(); i++){
            Node cur = banList[cnt].get(i);

            if((made & (1 << cur.idx)) != 0 ) continue;
            int tmp = made | (1<<cur.idx);
            dfs(cnt+1, tmp);
        }
    }

    public int simulation(){

        makeBanList();

        dfs(0,0);

        return ansList.size();

    }
    public int solution(String[] user_id, String[] banned_id) {
        users = user_id;
        bans = banned_id;
        banList = new ArrayList[banned_id.length];
        for(int i=0; i<banned_id.length; i++) banList[i] = new ArrayList<>();


        return simulation();
    }
}