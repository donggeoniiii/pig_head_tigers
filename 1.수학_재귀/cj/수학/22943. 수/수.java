import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static boolean[] isPrime(int size) {
		
		// 소수판별
		boolean[] is_Prime = new boolean[size];
		is_Prime[0] = true;
		is_Prime[1] = true;
		
		for(int i= 2; i< Math.sqrt(size); i++) {
			
			if(is_Prime[i] == false) {
				for(int j = i+i; j<size; j+=i) {
					is_Prime[j] = true;
				}
			}
		}
		
		return is_Prime;
	}
	
	// 정수 파싱
	public static boolean isDiff(int n, int k) {

		int[] parse_Num = new int[k];
		for(int i = 0; i<k; i++) {
			parse_Num[i] = n%10;
			n /= 10;
		}
		
		for(int i = 0; i < k-1; i++) {
			for(int j=i+1; j<k; j++) {
				if(parse_Num[i] == parse_Num[j]) return false;
			}
		}

		return true;
	}
	
	// 정수 파싱 2
	public static boolean isDiff2(int n, int k) {
		
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i <k; i++) {
			set.add(n%10);
			n /= 10;
		}
		if(set.size() == k) return true;
		return false;
	}
	
	public static boolean isPlus(int n, boolean[] is_Prime) {
		
		for(int i = 2; i <= n/2; i++) {
			if(is_Prime[n-i] == false && is_Prime[i] == false && n-i != i) return true;
		}
			
		return false;
	}
	
	public static boolean isMultiply(int n, boolean[] is_Prime) {
		
		for(int i=2; i< n; i++) {
			if(n%i == 0 && is_Prime[n/i] == false && is_Prime[i] == false) return true;
		}
		
		return false;
	}
	
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int k = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int size = (int)Math.pow(10, k);
		boolean[] is_Prime = isPrime(size);
		
		int count = 0;
		for(int i = size/10; i<size; i++) {
		
			if(isDiff(i, k)) {
				if(isPlus(i,is_Prime)) {
					
					int a = i;
					while (a % m == 0) {
						a /= m;
					}
					
					if(isMultiply(a, is_Prime)) {
						count++;
					}
				}
			}
		}
		System.out.println(count);
		
	}
}