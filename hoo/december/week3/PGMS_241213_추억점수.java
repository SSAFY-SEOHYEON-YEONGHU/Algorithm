import java.util.*;

class Solution {

    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = calcScore(name, yearning, photo);

        return answer;
    }

    int[] calcScore(String[] name, int[] yearning, String[][] photo) {
        Map<String, Integer> yearningMap = makeYearningMap(name, yearning);
        int[] scores = new int[photo.length];
        for (int i = 0; i < photo.length; i++) {
            int temp = 0;
            for (int j = 0; j < photo[i].length; j++) {
                if (!yearningMap.containsKey(photo[i][j])) continue;    // 그리움 점수에 없는 사람은 건너 뜀
                temp += yearningMap.get(photo[i][j]);
            }
            scores[i] = temp;
        }

        return scores;
    }

    Map<String, Integer> makeYearningMap(String[] name, int[] yearning) {
        Map<String, Integer> yearningMap  = new HashMap<>();
        for (int i = 0; i < name.length; i++) yearningMap.put(name[i], yearning[i]);

        return yearningMap;
    }

}