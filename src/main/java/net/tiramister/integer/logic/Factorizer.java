package net.tiramister.integer.logic;

import net.tiramister.integer.entity.Factors;

public class Factorizer {
  // O(n^{1/2})
  public static Factors factorize(Long n) {
    Factors factors = new Factors();

    if (n == 1) {
      factors.add(1L, 1);
      return factors;
    }

    for (Long p = 2L; p * p <= n; ++p) {
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
