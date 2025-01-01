package twentytwentyfour.october.week3;

public class PGMS_241015_n진수게임 {

    public String solution(int n, int t, int m, int p) {
        String answer = calcNumber(n, t, m, p);

        return answer;
    }

    String calcNumber(int n, int t, int m, int p) {
        String nNaryNumbers = "";
        for (int i = 0; i <= t*m; i++) {    // 미리 구하는 숫자 개수*인원 수 만큼의 수를 미리 n진수로 변환하고 담아놓기
            nNaryNumbers += Integer.toString(i, n).toUpperCase();
        }

        String answer = "";
        for (int i = p-1; i < t*m; i += m) {    // 튜브의 순서부터 사람의 수를 주기로하여 말해야하는 숫자 정답에 더해줌
            answer += String.valueOf(nNaryNumbers.charAt(i));
        }
        return answer;
    }

}
