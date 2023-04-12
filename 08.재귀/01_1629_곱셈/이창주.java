import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long recur(long A, long B, long C) {

        if (B == 1) return A % C;

        long val = recur(A, B / 2, C);

        val = val * val % C;
        if (B%2 == 0) return val;
        return val * A % C;
    }

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        // A를 B번 곱한 수를 C로 나눈 나머지
        System.out.println(recur (A,B,C));
        /*
       a^n * a^n  = a^2n;

       a^(k+1) % C


        */

    }
}