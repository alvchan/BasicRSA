import java.math.BigInteger;

public final class Prime {
	private Prime() {}

	public static boolean isPrime(BigInteger x) {
		BigInteger m = x.subtract(BigInteger.ONE);
		for (int k = 1; k < 10; k++) {
			if ((x.subtract(BigInteger.ONE)).mod(BigInteger.ONE.shiftLeft(k)).equals(BigInteger.ZERO))
				m = x.subtract(BigInteger.ONE).shiftRight(k);
			else
				break;
		}

		final BigInteger a = BigInteger.TWO;
		BigInteger a_m = BigInteger.ONE;
		for (BigInteger i = BigInteger.ZERO; i.compareTo(m) == -1; i = i.add(BigInteger.ONE))
			a_m = a_m.multiply(a.multiply(m));

		BigInteger b = a_m.mod(x);
		if (b.equals(BigInteger.ONE) || b.equals(x.subtract(BigInteger.ONE)))
			return true;

		// TODO: up the rounds
		for (int i = 0; i < 10; i++) {
			if (b.equals(BigInteger.ONE))
				return false;
			else if (b.equals(x.subtract(BigInteger.ONE)))
				return true;

			b = b.modPow(BigInteger.TWO, x);
		}

		return false;
	}
}
