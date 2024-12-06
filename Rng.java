import java.util.Random;

/**
 * A wrapper class for generating a random integer.
 */
public final class Rng {
	private static final int N = 624;
	private static final int M = 397;
	private static final int XOR_TEMPER = 0x9908b0df;
	private static final int UPPER_MASK = 0x80000000;
	private static final int LOWER_MASK = 0x7fffffff;
	private static final int[] MAG = {0x0, XOR_TEMPER};

	// don't let anyone initialize - it's a static wrapper class
	private Rng() {}
		
	/**
	 * A pseudorandom number generator.
	 */
	private static final class MersenneTwister {
		private int[] state;
		private int idx;

		/**
		 * Seeds this PRNG object.
		 */
		private MersenneTwister() {
			Random rand = new Random(System.currentTimeMillis());
			int seed = rand.nextInt();
			state = new int[N];
			state[0] = seed & 0xffffffff;
			for (this.idx = 1; idx < N; idx++)
				state[idx] = (69069 * state[idx-1]) & 0xffffffff;
		}

		/**
		 * Create a new random 32-bit integer.
		 *
		 * @return random i32
		 */
		private int rand() {
			int y, i;
			if (idx >= N || idx < 0) {
				for (i = 0; i < N-M; i++) {
					y = (state[i] & UPPER_MASK) | (state[i+1] & LOWER_MASK);
					state[i] = state[i+M] ^ (y >> 1) ^ MAG[y & 0x1];
				}
				for (; i < N-1; i++) {
					y = (state[i] & UPPER_MASK) | (state[i+1] & LOWER_MASK);
					state[i] = state[i+(M-N)] ^ (y >> 1) ^ MAG[y & 0x1];
				}

				y = (state[N-1] & UPPER_MASK) | (state[0] & LOWER_MASK);
				state[N-1] = state[M-1] ^ (y >> 1) ^ MAG[y & 0x1];
				idx = 0;
			}

			y = state[idx++];
			y ^= y >> 11;
			y ^= (y << 7) & 0x9d2c5680;
			y ^= (y << 15) & 0xefc60000;
			y ^= y >> 18;

			return y;
		}
	}

	/**
	 * Create a new random 32-bit integer.
	 *
	 * @return random i32
	 */
	public static int rand() {
		MersenneTwister mt = new MersenneTwister();
		return mt.rand();
	}
}
