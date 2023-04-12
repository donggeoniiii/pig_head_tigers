import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int r, c;
	static int cnt = 0;
	/*
	 * 사분면 확인
	 * size / 2 하면 딱 절반으로 나누어짐 (짝수)x(짝수) 평면이기 때문
	 * 이를 이용해서 4등분한 영역 중 어디에 (r,c)가 속하는지 확인
	 * 계산의 편의를 위해 결과값은 사분면 방향과는 다르게 설정
	 * size가 1이 되면 메소드 종료
	 * 아니면 size를 반으로 나누어 다시 진행
	 * 
	 */
	
	public static int quad(int size) {
		
		int map = (int) Math.pow(2, size);
		
		int result = 0;
		if (r < map/2 && c < map/2) {
			result = 0;
		} else if (r < map/2 && c >= map/2) {
			result = 1;
			c -= map/2;
		}else if (r >= map/2 && c < map/2) {
			result = 2;
			r -= map/2;
		} else {
			result = 3;
			r -= map/2;
			c -= map/2;
		}
		
		return result;
	}
	
	public static void recursion(int size) {
		
		if (size == 0) return;
		
		else {
			int prev = quad(size);
			size--;
			int newMap = (int) Math.pow(2, size);
			cnt += prev * newMap * newMap;
			recursion(size);
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		recursion(N);
		
		// 결과 출력
		System.out.println(cnt);
	}
}