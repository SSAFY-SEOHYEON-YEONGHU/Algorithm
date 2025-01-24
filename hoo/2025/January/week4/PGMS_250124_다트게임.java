import java.util.*;

class Solution {

    public int solution(String dartResult) {
        int answer = calcScore(dartResult);

        return answer;
    }

    public int calcScore(String dartResult) {
        int score = 0;
        List<Integer> scoreList = new ArrayList<>();    // 계산된 점수들을 저장해둘 리스트
        int tempScore = 0;
        for (int i = 0; i < dartResult.length(); i++) {
            if (dartResult.charAt(i) == '*' || dartResult.charAt(i) == '#') {   // 옵션을 계산해줄 차례인 경우
                if (dartResult.charAt(i) == '*') {
                    for (int j = 1; j <= Math.min(scoreList.size(), 2); j++) {  // 현재 점수와 이전 점수까지 두 배 처리
                        scoreList.set(scoreList.size()-j, scoreList.get(scoreList.size()-j)*2);
                    }
                } else if (dartResult.charAt(i) == '#') scoreList.set(scoreList.size()-1, scoreList.get(scoreList.size()-1)*-1);    // 현재 점수 마이너스 처리
            } else if (dartResult.charAt(i) == 'S' || dartResult.charAt(i) == 'D' || dartResult.charAt(i) == 'T') {
                if (dartResult.charAt(i) == 'S') tempScore = tempScore;
                else if (dartResult.charAt(i) == 'D') tempScore = (int) Math.pow(tempScore, 2);
                else if (dartResult.charAt(i) == 'T') tempScore = (int) Math.pow(tempScore, 3);

                scoreList.add(tempScore);
                tempScore = 0;
            } else tempScore = Integer.parseInt(String.valueOf(tempScore) + String.valueOf(dartResult.charAt(i)));
            // System.out.println(scoreList);
        }

        for (int i = 0; i < scoreList.size(); i++) score += scoreList.get(i);

        return score;
    }

}