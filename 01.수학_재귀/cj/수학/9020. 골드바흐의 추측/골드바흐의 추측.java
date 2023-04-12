import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static boolean[] isPrime(int size) {
		
		boolean[] isPrime = new boolean[size+1];
	
		for(int i = 2; i < Math.sqrt(size); i++) {
			
			if(isPrime[i] == false) {
				for(int j =i+i; j <= size; j+= i) {
					isPrime[j] = true;
				}
			}
		}
		
		return isPrime;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		
		boolean[] is_Prime = isPrime(10000);

		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			
			for(int j = n/2; j<n; j++) {
				if(is_Prime[n-j] == false && is_Prime[j] == false) {
					sb.append(n-j).append(" ").append(j).append("\n");
					break;
				}
			}
		}
		System.out.println(sb);
	}
}