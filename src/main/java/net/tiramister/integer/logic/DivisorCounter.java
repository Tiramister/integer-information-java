package net.tiramister.integer.logic;

import net.tiramister.integer.entity.Factors;

public class DivisorCounter {
  public static int countDivisors(long n) {
    return countDivisors(Factorizer.factorize(n));
  }

  public static int countDivisors(Factors factors) {
    if (factors.isOne()) return 1;
    return factors.getFactors().stream()
        .reduce(1, (prod, factor) -> prod * (factor.getExp() + 1), (prod1, prod2) -> prod1 * prod2);
  }
}
