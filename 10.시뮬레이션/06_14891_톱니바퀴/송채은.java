import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static ArrayList<Wheel> wheel;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		wheel = new ArrayList<>();
		for(int i = 0; i < 4; i++)
			wheel.add(new Wheel(br.readLine()));
		
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			boolean clockwise = false;
			if(dir == 1) clockwise = true;
			
			if(num == 1) {
				if(wheel.get(0).teeth.get(2) != wheel.get(1).teeth.get(6)) {
					if(wheel.get(1).teeth.get(2) != wheel.get(2).teeth.get(6)) {
						if(wheel.get(2).teeth.get(2) != wheel.get(3).teeth.get(6))
							rotate(3, !clockwise);
						rotate(2, clockwise);
					}
					rotate(1, !clockwise);
				}
				rotate(0, clockwise);
			} else if(num == 2) {
				if(wheel.get(1).teeth.get(6) != wheel.get(0).teeth.get(2))
					rotate(0, !clockwise);
				if(wheel.get(1).teeth.get(2) != wheel.get(2).teeth.get(6)) {
					if(wheel.get(2).teeth.get(2) != wheel.get(3).teeth.get(6))
						rotate(3, clockwise);
					rotate(2, !clockwise);
				}
				rotate(1, clockwise);
			} else if(num == 3) {
				if(wheel.get(2).teeth.get(6) != wheel.get(1).teeth.get(2)) {
					if(wheel.get(1).teeth.get(6) != wheel.get(0).teeth.get(2))
						rotate(0, clockwise);
					rotate(1, !clockwise);
				}
				if(wheel.get(2).teeth.get(2) != wheel.get(3).teeth.get(6))
					rotate(3, !clockwise);
				rotate(2, clockwise);
			} else {
				if(wheel.get(3).teeth.get(6) != wheel.get(2).teeth.get(2)) {
					if(wheel.get(2).teeth.get(6) != wheel.get(1).teeth.get(2)) {
						if(wheel.get(1).teeth.get(6) != wheel.get(0).teeth.get(2))
							rotate(0, !clockwise);
						rotate(1, clockwise);
					}
					rotate(2, !clockwise);
				}
				rotate(3, clockwise);
			}
		}
		
		int sum = 0;
		for(int i = 0; i < wheel.size(); i++) {
			int score = (int) Math.pow(2, i);
			if(wheel.get(i).teeth.get(0) == '1')
				sum += score;
		}
		System.out.print(sum);
	}
	
	public static void rotate(int num, boolean clockwise) {
		if(clockwise) {
			char move = wheel.get(num).teeth.remove(7);
			wheel.get(num).teeth.add(0, move);
		} else {
			char move = wheel.get(num).teeth.remove(0);
			wheel.get(num).teeth.add(move);
		}
	}
}

class Wheel{
	ArrayList<Character> teeth = new ArrayList<>();
	
	Wheel(String s){
		char[] input = s.toCharArray();
		for(int i = 0; i < s.length(); i++)
			this.teeth.add(input[i]);
	}
}