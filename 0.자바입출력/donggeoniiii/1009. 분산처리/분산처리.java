import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
	    Scanner input = new Scanner(System.in);
	    int T = input.nextInt();
	    // a = 밑, b = 제곱수, rem = 나머지(remainder)
	    int a, b, rem, answer;
	    
	    for (int i = 0; i < T; i++) {
	        a = input.nextInt();
	        b = input.nextInt();
		rem = 0;
		answer = 1;
			// 아무리 제곱해도 일의 자리가 똑같은 수들: 0, 1, 5, 6
	        if (a % 10 == 0 || a % 10 == 1 || a % 10 == 5 || a % 10 == 6) {
			answer = a % 10;
		}
	        // 사이클이 2인 수들: 4, 9
	        else if (a % 10 == 4 || a % 10 == 9) {
	        	rem = b % 2;
	        	if (rem == 0) rem = 2;
		}
	        // 사이클이 4인 수들: 2, 3, 7, 8
	        else {
			rem = b % 4;
			if (rem == 0) rem = 4;
		}

	        // 일의 자리 값만 알면 되기 때문에 제곱한 값에 일의 자리만 가져와서 연산
	        for (int j = 0; j < rem; j++) answer = (answer * a) % 10;
	        if (answer == 0) answer = 10;
	        System.out.println(answer);
            }
    }
}