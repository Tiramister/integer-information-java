package net.tiramister.integer.logic;

public class PrimeJudger {
  // 2^64まで判定するのに必要な基底
  // https://miller-rabin.appspot.com/
  private static final long[] bases = {2, 325, 9375, 28178, 450775, 9780504, 1795265022};

  // naive algorithm
  // O(sqrt(n))
  public static boolean naive(long n) {
    if (n == 1) return false;
    for (long p = 2; p * p <= n; ++p) {
      if (n % p == 0) return false;
    }
    return true;
  }

  // Miller-Rabin algorithm
  // O(log n)
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

  public static boolean isPrime(long n) {
    return millerRabin(n);
  }
}
