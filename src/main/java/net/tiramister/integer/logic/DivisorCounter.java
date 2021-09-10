package net.tiramister.integer.logic;

public class DivisorCounter {
  public static int countDivisors(long n) {
    if (n == 1L) return 1;
    return Factorizer.factorize(n).getFactors().stream()
        .reduce(1, (prod, factor) -> prod * (factor.getExp() + 1), (prod1, prod2) -> prod1 * prod2);
  }
}
