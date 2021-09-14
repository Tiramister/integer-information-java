package net.tiramister.integer.logic;

import net.tiramister.integer.entity.Factors;

/** 約数の個数を計算するメソッド群. */
public class DivisorCounter {
  /**
   * nの約数の個数を計算する.
   *
   * @param n
   * @return nの約数の個数
   */
  public static int countDivisors(long n) {
    return countDivisors(Factorizer.factorize(n));
  }

  /**
   * 素因数分解から約数の個数を計算する.
   *
   * @param factors nの素因数分解
   * @return nの約数の個数
   */
  public static int countDivisors(Factors factors) {
    if (factors.isOne()) return 1;
    return factors.getFactors().stream()
        .reduce(1, (prod, factor) -> prod * (factor.getExp() + 1), (prod1, prod2) -> prod1 * prod2);
  }
}
