import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static char[] result = new char[531441];
    public static void cantor(char[] result,int start,int end){

        int x = (end - start) / 3;

        for (int i = start + x; i < start + x + x; i++) {
            result[i] = ' ';
        }

        if(x<=1) return;
        cantor(result, start,start+x);
        cantor(result, start+x+x,end);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        String s;

        while( (s = br.readLine()) != null){

            int n = Integer.parseInt(s);
            int size = (int) Math.pow(3, n);
            Arrays.fill(result, '-');
            cantor(result,0,size);
            for(int i =0; i< size; i++){
                sb.append(result[i]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}