package net.tiramister.integer.logic;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Test;

public class PrimeUtilTests {
  private static final int SIEVE_SIZE = 1000000;
  private static final long MAX = (long) SIEVE_SIZE * SIEVE_SIZE;

  private static ThreadLocalRandom rng = ThreadLocalRandom.current();

  /** 1から10^6までを全部試す. */
  @Test
  public void 小さいケースでの素数判定() throws Exception {
    boolean[] isPrime = new boolean[SIEVE_SIZE + 1];
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;

    for (int p = 2; p * p <= SIEVE_SIZE; ++p) {
      if (!isPrime[p]) continue;

      for (int q = p * 2; q <= SIEVE_SIZE; q += p) isPrime[q] = false;
    }

    for (int p = 1; p <= SIEVE_SIZE; ++p) {
      assertEquals(PrimeUtil.isPrime(p), isPrime[p]);
    }
  }

  /**
   * [10^6, 10^12)からランダムに長さ10^6の区間を取り、全部試す.
   *
   * <p>愚直解には区間篩を使用.
   */
  @Test
  public void 中くらいのケースでの素数判定() throws Exception {
    boolean[] isPrime = new boolean[SIEVE_SIZE + 1];
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;

    long left = rng.nextLong(SIEVE_SIZE, MAX - SIEVE_SIZE);
    long right = left + SIEVE_SIZE;
    // [left, right)を判定

    boolean[] isPrimeLarger = new boolean[SIEVE_SIZE];
    Arrays.fill(isPrimeLarger, true);

    for (int p = 2; p <= SIEVE_SIZE; ++p) {
      if (!isPrime[p]) continue;

      for (int q = p * 2; q <= SIEVE_SIZE; q += p) isPrime[q] = false;

      long x = (left + p - 1) / p * p;
      while (x < right) {
        isPrimeLarger[(int) (x - left)] = false;
        x += p;
      }
    }

    for (long x = left; x < right; ++x) {
      assertThat(PrimeUtil.isPrime(x)).isEqualTo(isPrimeLarger[(int) (x - left)]);
    }
  }
}
