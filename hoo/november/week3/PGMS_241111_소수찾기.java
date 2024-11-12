import java.util.*;

class Solution {

    int answer;
    boolean[] isMade;   // 한 번 체크한 적 있는 숫자는 저장

    public int solution(String numbers) {
        answer = 0;
        isMade = new boolean[9999999+1];
        for (int i = 1; i <= numbers.length(); i++) {
            permutation(numbers, i, 0, new boolean[numbers.length()], new ArrayList<>());
        }

        return answer;
    }

    void permutation(String numbers, int primeSize, int count, boolean[] isSelected, List<Integer> selectedNumberIndexes) {    // primeSize: 만들고자 하는 소수의 길이, count: 현재까지 고른 숫자
        if (count == primeSize) {
            if (isPrime(numbers, selectedNumberIndexes)) answer++;
            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (isSelected[i]) continue;
            isSelected[i] = true;
            selectedNumberIndexes.add(i);
            permutation(numbers, primeSize, count+1, isSelected, selectedNumberIndexes);
            isSelected[i] = false;
            selectedNumberIndexes.remove(selectedNumberIndexes.size()-1);
        }
    }

    boolean isPrime(String numbers, List<Integer> selectedNumberIndexes) {
        int madeNumber = makeNumber(numbers, selectedNumberIndexes);
        if (madeNumber == 0 || madeNumber == 1) return false;  // 0이나 1이면 건너 뜀
        if (isMade[madeNumber]) return false;   // 이미 한 번 만들어봤던 숫자면 건너 뜀

        isMade[madeNumber] = true;
        for (int i = 2; i*i <= madeNumber; i++) {
            if (madeNumber % i == 0) return false;  // 하나의 숫자만으로라도 나눠지면 소수 아님
        }

        return true;
    }

    int makeNumber(String numbers, List<Integer> selectedNumberIndexes) {
        String madeNumberString = "";
        for (int i = 0; i < selectedNumberIndexes.size(); i++) {
            madeNumberString += String.valueOf(numbers.charAt(selectedNumberIndexes.get(i)));
        }
        int madeNumber = Integer.parseInt(madeNumberString);

        return madeNumber;
    }

}