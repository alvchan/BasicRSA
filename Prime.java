public final class Prime {
	public static boolean isPrime(int x) {
		int m = x - 1;
		for (int k = 1; k < 10; k++) {
			if ((x - 1) % (1 << k) == 0)
				m = (x - 1) >> k;
			else
				break;
		}

		int a = 2;

		long a_m = 1;
		for (int i = 0; i < m; i++)
			a_m *= a;

		long b = a_m % x;
		if (b == 1 || b == x-1)
			return true;

		for (int i = 0; i < 10; i++) {
			if (b == 1)
				return false;
			else if (b == x-1)
				return true;

			b = (b*b) % x;
		}

		return false;
	}
}
