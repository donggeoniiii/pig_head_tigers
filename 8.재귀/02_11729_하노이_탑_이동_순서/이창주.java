import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static void hanoi(int a, int b, int n) {
        // 원판 n개를 a에서 b로 옮기는 함수
        if (n == 1) {
            sb.append(a).append(" ").append(b).append("\n");
            return;
        }

        hanoi(a, 6 - a - b, n - 1);
        sb.append(a).append(" ").append(b).append("\n");
        hanoi(6 -a -b, b, n - 1);

    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        System.out.println((int) Math.pow(2,n)-1);
        hanoi(1, 3, n);
        System.out.println(sb);
    }
}

// 1 3 7 15
// 2 4 8 16