import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int cnt = 0;
        for (int i = 0; i < T; i++) {
            String s = br.readLine();
            Stack<Character> gw = new Stack<>();

            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (gw.isEmpty()) {
                    gw.push(c);
                } else {

                    if (gw.peek() == c) {
                        gw.pop();
                    } else {
                        gw.push(c);
                    }
                }
            }
            if (gw.isEmpty()) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
