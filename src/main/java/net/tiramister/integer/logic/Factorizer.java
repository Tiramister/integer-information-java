package net.tiramister.integer.logic;

import net.tiramister.integer.entity.Factors;

public class Factorizer {
  // O(n^{1/2})
  public static Factors factorize(long n) {
    if (n == 1) return Factors.one();

    Factors factors = new Factors();
    for (long p = 2L; p * p <= n; ++p) {
      if (n % p != 0) continue;
      int exp = 0;
      while (n % p == 0) {
        n /= p;
        ++exp;
      }
      factors.add(p, exp);
    }

    if (n != 1) factors.add(n, 1);

    return factors;
  }
}
