package net.tiramister.integer.logic;

import net.tiramister.integer.entity.Factors;

public class Totient {
  public static long totient(long n) {
    return totient(Factorizer.factorize(n));
  }

  public static long totient(Factors factors) {
    if (factors.isOne()) return 1;

    long result = 1;

    for (Factors.Factor factor : factors.getFactors()) {
      long prod = 1;
      for (int i = 0; i < factor.getExp(); ++i) {
        prod *= factor.getPrime();
      }
      result *= prod - prod / factor.getPrime();
    }

    return result;
  }
}
