import java.math.BigInteger;
import java.util.Random;

public final class Rsa {
	private BigInteger p, q, n, k;
	private BigInteger e, d;
	private static final int[] primes = {3, 5, 17, 257, 65537};
	private static final int[] allowedKeySizes = {1024, 2048, 3072, 4096};

	public Rsa(int keySize) {
		if (!isKeySizeValid(keySize)) {
			System.err.println("key should be 1024/2048/3072/4096 bits long.");
			System.exit(0);
		}

		p = BigInteger.probablePrime(keySize, new Random());
		System.err.printf("p=%s\n", p);
		q = BigInteger.probablePrime(keySize, new Random());
		System.err.printf("q=%s\n", q);

		n = p.multiply(q);
		System.err.printf("n=%s\n", n);

		BigInteger p_1 = p.subtract(BigInteger.ONE);
		BigInteger q_1 = q.subtract(BigInteger.ONE);
		k = p_1.multiply(q_1).abs().divide(p_1.gcd(q_1));
		System.err.printf("k=%s\n", k);
	
		boolean found = false;
		for (int elem : primes) {
			e = new BigInteger(String.valueOf(elem));
			// works iff p,q prime
			if (e.gcd(k).equals(BigInteger.ONE)) {
				found = true;
				break;
			}
		}
		if (!found) {
			for (BigInteger i = new BigInteger("3"); i.compareTo(k) == -1; i = i.add(BigInteger.TWO)) {
				found = i.gcd(k).equals(BigInteger.ONE);
				if (found) {
					e = i;
					break;
				}
			}
		}
		
		if (!found) {
			System.err.println("e not found.");
			System.exit(0);
		}

		System.err.printf("e=%s\n", e);
		d = e.modInverse(k);
		System.err.printf("d=%s\n", d);
	}

	public BigInteger encrypt(BigInteger message) {
		System.err.printf("plaintext: %s\n", message);
		return message.modPow(e, n);
	}

	public BigInteger decrypt(BigInteger ciphertext) {
		System.err.printf("ciphertext: %s\n", ciphertext);
		BigInteger m_d = ciphertext.modPow(d, n);
		System.err.printf("deciphered: %s\n", m_d);
		return m_d;
	}

	private static boolean isKeySizeValid(int keySize) {
		for (int keysz : allowedKeySizes) {
			if (keySize == keysz)
				return true;
		}
		return false;
	}
}
