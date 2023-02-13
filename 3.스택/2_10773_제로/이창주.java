import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> money = new Stack<>();
        int result = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num == 0) money.pop();
            else {
                money.push(num);
            }
        }

        for (int i : money) {
            result += i;
        }

        System.out.println(result);

    }
}
