import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            int split = line.indexOf(" ");
            sb.append(Integer.parseInt(line.substring(0, split))+Integer.parseInt(line.substring(split+1))).append('\n');
        }
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
    }
}