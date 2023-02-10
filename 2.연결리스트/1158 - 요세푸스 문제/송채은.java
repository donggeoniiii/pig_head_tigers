import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		LinkedList<Integer> list = new LinkedList<>();
		
		for(int i = 1; i <= N; i++)
			list.add(i);
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		int index = 0;
		while(N > 1) {
			index = (index + (K - 1)) % N;

			sb.append(list.remove(index));
			sb.append(", ");
			N--;
		}
		
		sb.append(list.remove());
		sb.append(">");
		
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
