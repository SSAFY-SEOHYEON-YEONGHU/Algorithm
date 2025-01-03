class Solution {
    public int solution(String s) {
        int answer = 0;

        char first = s.charAt(0);
        int[] cnt = new int[2];

        for(int i=0; i < s.length(); i++){
            if(s.charAt(i) == first) cnt[0]++;
            else cnt[1]++;

            if(cnt[0] == cnt[1]){
                answer++;
                cnt[0] = 0;
                cnt[1] = 0;

                if(i+1 < s.length()) first = s.charAt(i+1);
            }
        }

        if(cnt[0] != cnt[1]) answer++;

        return answer;
    }
}