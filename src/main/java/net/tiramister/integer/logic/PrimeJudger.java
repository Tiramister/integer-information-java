package net.tiramister.integer.logic;

public class PrimeJudger {
  // O(sqrt(n))
  public static boolean isPrime(long n) {
    if (n == 1) return false;
    for (long p = 2; p * p <= n; ++p) {
      if (n % p == 0) return false;
    }
    return true;
  }
}
