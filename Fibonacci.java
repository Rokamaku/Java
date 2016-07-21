public class Fibonacci {
	public static int fib(int n, String spaces) {
		System.out.println(spaces + "fib(" + n + ")");
		if (n <= 2) return 1;
		return fib(n-1, spaces + " ") + fib(n-2, spaces + " ");
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		fib(n, "");
	}
}