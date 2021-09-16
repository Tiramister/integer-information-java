package net.tiramister.integer.logic;

/** 素数に関するメソッド群. */
public class PrimeUtil {
  /**
   * 2^64まで判定するのに必要な基底.
   *
   * @see https://miller-rabin.appspot.com/
   */
  private static final long[] bases = {2, 325, 9375, 28178, 450775, 9780504, 1795265022};

  /**
   * ナイーブな試し割りアルゴリズム. O^{1/2}.
   *
   * @param n
   * @return nが素数か否か
   */
  public static boolean naive(long n) {
    if (n == 1) return false;
    for (long p = 2; p * p <= n; ++p) {
      if (n % p == 0) return false;
    }
    return true;
  }

  /**
   * Miller-Rabin 法. O(log n).
   *
   * <p>確率的なアルゴリズムだが、 n<2^64 の範囲では基底を上手く選ぶと決定的に解ける.
   *
   * @param n
   * @return nが素数か否か
   */
  public static boolean millerRabin(long n) {
    if (n == 1) return false;
    if (n == 2) return true;
    if (n % 2 == 0) return false;

    long odd = n - 1;
    int two = 0;
    while (odd % 2 == 0) {
      odd /= 2;
      ++two;
    }

    for (long basis : bases) {
      {
        long g = MathUtil.gcd(basis, n);
        if (g != 1 && g != n) return false;
      }

      long x = MathUtil.modPow(basis, odd, n);
      if (x == 0 || x == 1) continue;

      boolean judge = false;
      for (int i = 0; i < two; ++i) {
        if (x == n - 1) {
          judge = true;
          break;
        }
        x = MathUtil.modPow(x, 2, n);
      }

      if (!judge) return false;
    }

    return true;
  }

  /**
   * nが素数か否か判定する.
   *
   * @param n
   * @return nが素数か否か
   */
  public static boolean isPrime(long n) {
    return millerRabin(n);
  }

  /**
   * 次の素数を返す.
   *
   * @param n 正の整数
   * @return nより大きい最小の整数
   */
  public static long nextPrime(long n) {
    while (!isPrime(++n)) {}
    return n;
  }
}
