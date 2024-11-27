import java.math.BigInteger;

public class PrimeTester {
	public static void main(String[] args) {
		assert(Prime.isPrime(BigInteger.TWO));
		assert(Prime.isPrime(new BigInteger("7")));
		assert(Prime.isPrime(new BigInteger("15")));
		assert(Prime.isPrime(new BigInteger("19")));
		assert(Prime.isPrime(new BigInteger("2311")));
		System.out.println("OK");
	}
}
