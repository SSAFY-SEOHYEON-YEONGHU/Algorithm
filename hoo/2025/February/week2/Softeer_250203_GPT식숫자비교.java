import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        printInGPTOrder(br, N);
    }

    static void printInGPTOrder(BufferedReader br, int N) throws Exception {
        PriorityQueue<String> numbers = new PriorityQueue<>(new Comparator<String>() {  // 문자열로 삽입 후 그 안에서 GPT 순서로 비교하게
            @Override
            public int compare(String f1, String f2) {
                int floorF1 = (int) Math.floor(Float.parseFloat(f1));
                int floorF2 = (int) Math.floor(Float.parseFloat(f2));
                if (floorF1 < floorF2) return -1;  // 정수부가 더 작은 숫자는 앞에 오게
                else if (floorF1 == floorF2) {  // 정수부가 같은 경우 소수부 판별 시작
                    String[] splittedF1 = f1.split("\\.");  // .은 정규식에서 임의의 한 문자를 의미, 따라서 .을 split()의 정규식으로 사용하고 싶으면 \\ 나 []를 사용해야 함
                    String[] splittedF2 = f2.split("\\.");
                    if (splittedF1.length < splittedF2.length) return -1;  // f1의 길이가 짧으면, 정수만 있는 경우이고 더 작은 수이므로 앞에 오게
                    else if (splittedF1.length == splittedF2.length) {  // 두 숫자의 길이가 같은 경우
                        if (splittedF1.length == 1) return 1;  // 만약 두 수 다 정수부만 있는 수였을 경우 그냥 아무렇게나 정렬되게 1 반환
                        return Integer.parseInt(splittedF1[1]) - Integer.parseInt(splittedF2[1]);  // 소수부 숫자 기준 오름차순 정렬
                    } else return 1;  // f2 길이가 더 짧으면 f2가 앞에 오게
                } else return 1;  // 정수부가 더 큰 숫자는 뒤에 가게
            }
        });

        for(int i = 0; i < N; i++) numbers.offer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(numbers.poll()).append("\n");
        System.out.println(sb);
    }

}
