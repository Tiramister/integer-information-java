package net.tiramister.integer.logic;

import net.tiramister.integer.entity.Factors;

/** 原始根を計算するメソッド群. */
public class PrimitiveRoot {
  /**
   * nが原始根を持つか判定する.
   *
   * <p>以下のいずれかの形で表せることと同値.
   *
   * <ul>
   *   <li>n = 1, 2, 4
   *   <li>n = p^k (pは奇素数)
   *   <li>n = 2 p^k (pは奇素数)
   * </ul>
   *
   * @param n 正の整数
   * @return nが原始根を持つか
   */
  public static boolean hasPrimitiveRoot(long n) {
    if (n <= 4) return true;

    if (n % 2 == 0) n /= 2;
    if (n % 2 == 0) return false;

    Factors factors = Factorizer.factorize(n);
    return factors.getFactors().size() == 1;
  }

  /**
   * nの最小原始根を計算する.
   *
   * <p>ある仮定のもとで、最小原始根は O(log^6(n)) になることが証明されている.
   *
   * @see https://en.wikipedia.org/wiki/Primitive_root_modulo_n#Upper_bounds
   * @param n 正の整数
   * @return nの最小原始根 nが原始根を持たなければ-1
   */
  public static long minimum(long n) {
    // 乗法群の位数が1のものは例外処理
    if (n == 1) return 0;
    if (n == 2) return 1;

    if (!hasPrimitiveRoot(n)) return -1;

    long t = Totient.totient(n);
    Factors factors = Factorizer.factorize(t);
    long[] exps = factors.getFactors().stream().mapToLong(f -> t / f.getPrime()).toArray();
    // 試すべき指数

    for (long p = 2; ; ++p) {
      if (MathUtil.gcd(n, p) != 1) continue;

      boolean judge = true;
      for (long e : exps) {
        if (MathUtil.modPow(p, e, n) == 1) judge = false;
      }
      if (judge) return p;
    }
  }
}
