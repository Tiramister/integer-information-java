package net.tiramister.integer.logic;

import net.tiramister.integer.entity.Factors;

/** トーシェント関数を計算するメソッド群. */
public class Totient {
  /**
   * nのトーシェント関数を計算する.
   *
   * @param n
   * @return nのトーシェント関数
   */
  public static long totient(long n) {
    return totient(Factorizer.factorize(n));
  }

  /**
   * 素因数分解の結果からトーシェント関数を計算する.
   *
   * @param factors nの素因数分解
   * @return nのトーシェント関数
   */
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
