package ex00;

public class Program {
	public static void main(String args[]) {

		int num = 479598;
		int a = num % 10;
		num /= 10;
		int b = num % 10;
		num /= 10;
		int c = num % 10;
		num /= 10;
		int d = num % 10;
		num /= 10;
		int e = num % 10;
		num /= 10;
		int f = num % 10;
		System.out.println(a + b + c + d + e + f);
	}
}
