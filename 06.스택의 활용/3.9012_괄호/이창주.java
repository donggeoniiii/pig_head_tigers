import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // (나오면 + ) 나오면 - 0이면 YES else No

        StringBuffer sb = new StringBuffer();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {


            String s = br.readLine();
            int cnt = 0;
            for (int j = 0; j < s.length(); j++) {
                if(s.charAt(j) == '(') cnt++;
                else cnt--;
                if (cnt < 0) {
                    break;
                }
            }
            if(cnt == 0) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }
        System.out.println(sb);
    }
}
