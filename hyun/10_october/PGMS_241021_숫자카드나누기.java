import java.util.*;

class Solution {
    ArrayList<Integer> A = new ArrayList<>();
    ArrayList<Integer> B = new ArrayList<>();

    public void getSu(int[] arr, ArrayList<Integer> result){
        Arrays.sort(arr);
        int num = arr[0];

        // num 약수 구하기
        ArrayList<Integer> yaksu = new ArrayList<>();
        for(int i=2; i<= num; i++){
            if(num % i == 0) yaksu.add(i);
        }

        for(int y : yaksu){
            boolean isP = true;
            for(int i=1 ; i<arr.length; i++){
                if(arr[i] % y != 0){
                    isP = false;
                    break;
                }
            }

            if(isP) result.add(y);
        }

    }

    public boolean examine(int[] arr, int su){
        for(int n : arr){
            if(su > n) continue;
            if(n % su == 0) return false;
        }
        return true;
    }


    public int solution(int[] arrayA, int[] arrayB) {
        // 1. 공통 약수 구해주기 (제일 작은 원소기준)
        getSu(arrayA, A);
        getSu(arrayB, B);

        Collections.sort(A, Collections.reverseOrder());
        Collections.sort(B, Collections.reverseOrder());

        if(A.size() == 0 && B.size() == 0) return 0;

        // 2. 2가지 조건 중 일치하는 조건이 있는지 확인
        int answer = 0;
        for(int su : A){
            if(examine(arrayB, su)){
                answer = Math.max(answer, su);
                break;
            }
        }

        for(int su : B){
            if(examine(arrayA, su)){
                answer = Math.max(answer, su);
                break;
            }
        }

        return answer;
    }
}