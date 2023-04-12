import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long C;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		System.out.print(recur(A, B));
	}
	
	static long recur(long a, long b) {
		if(b == 1) return a % C;
		
		long temp = recur(a, b/2);
		temp = temp * temp % C;
		
		if(b % 2 != 0)
			return temp * a % C;
		
		return temp;
	}
}
