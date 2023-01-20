import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int abs = a%10;
            a = a % 10;
            if (abs == 1) bw. write(abs+"\n");
            else{
                for (int j = 1; j < b; j++) {
                    abs = (abs * a) % 10;
                }
                if(abs == 0) bw.write("10\n");
                else bw.write(abs+"\n");
            }
        }
        bw.flush();
        bw.close();
    }
}