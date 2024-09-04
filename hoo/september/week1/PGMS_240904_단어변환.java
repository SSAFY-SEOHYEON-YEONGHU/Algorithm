package september.week1;

public class PGMS_240904_단어변환 {

    int minCount;

    public int solution(String begin, String target, String[] words) {
        minCount = Integer.MAX_VALUE;
        dfs(begin, target, words, 0, new boolean[words.length]);

        return (minCount != Integer.MAX_VALUE)? minCount:0;
    }

    void dfs(String now, String target, String[] words, int count, boolean[] isChecked) {
        if (now.equals(target)) {
            minCount = Math.min(minCount, count);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (isChecked[i]) continue;

            if (!isPossible(now, words[i])) continue;
            isChecked[i] = true;
            dfs(words[i], target, words, count+1, isChecked);
            isChecked[i] = false;
        }
    }

    boolean isPossible(String now, String next) {
        int diffCount = 0;
        for (int i = 0; i < now.length(); i++) {
            if (diffCount >= 2) break;
            if (now.charAt(i) != next.charAt(i)) diffCount++;
        }

        return (diffCount == 1)? true:false;    // 한 자리가 다른 것만 가능함
    }

}
