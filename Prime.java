import java.math.BigInteger;

public final class Prime {
	final static BigInteger ZERO = new BigInteger("0");
	final static BigInteger ONE = new BigInteger("1");
	final static BigInteger TWO = new BigInteger("2");

	private Prime() {}

	public static boolean isPrime(BigInteger x) {
		if (x.equals(TWO))
			return true;

		BigInteger m = x.subtract(ONE);
		for (int k = 1; k < 10; k++) {
			if ((x.subtract(ONE)).mod(ONE.shiftLeft(k)).equals(ZERO))
				m = x.subtract(ONE).shiftRight(k);
			else
				break;
		}

		final BigInteger a = TWO;
		BigInteger a_m = ONE;
		for (BigInteger i = ZERO; i.compareTo(m) == -1; i = i.add(ONE))
			a_m = a_m.multiply(a.multiply(m));

		BigInteger b = a_m.mod(x);
		if (b.equals(ONE) || b.equals(x.subtract(ONE)))
			return true;

		// TODO: up the rounds
		for (int i = 0; i < 10; i++) {
			if (b.equals(ONE))
				return false;
			else if (b.equals(x.subtract(ONE)))
				return true;

			b = b.modPow(TWO, x);
		}

		return false;
	}
}
