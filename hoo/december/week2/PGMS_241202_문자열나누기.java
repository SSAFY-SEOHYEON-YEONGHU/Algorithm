import java.util.*;

class Solution {

    public int solution(String s) {
        int answer = doSplit(s);

        return answer;
    }

    public int doSplit(String s) {
        if (s.length() == 1) return 1;  // s가 문자 하나짜리면 1 반환
        int splitCount = 0;

        char firstLetter = s.charAt(0);   // 문자열의 첫 글자
        int firstLetterCount = 1;
        int otherLetterCount = 0;
        int index = 1;
        while (index < s.length()) {
            if (s.charAt(index) == firstLetter) firstLetterCount++;
            else otherLetterCount++;

            if (firstLetterCount == otherLetterCount) { // 두 숫자가 같아지면
                splitCount++;
                if (index < s.length()-2) { // 다음 문자가 있는 경우
                    firstLetter = s.charAt(index + 1);
                    firstLetterCount = 1;
                    otherLetterCount = 0;
                    index += 2;
                }
            } else index++;

            if (index >= s.length()-1) {
                splitCount++;
                break;
            }
        }

        return splitCount;
    }

}