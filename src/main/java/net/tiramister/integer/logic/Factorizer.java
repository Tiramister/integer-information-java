package net.tiramister.integer.logic;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.LongUnaryOperator;
import net.tiramister.integer.entity.Factors;

/** 素因数分解をするメソッド群. */
public class Factorizer {
  private static ThreadLocalRandom rng = ThreadLocalRandom.current();

  /**
   * 試し割りによるナイーブなアルゴリズム. O(n^{1/2}).
   *
   * @param n
   * @return nの素因数分解
   */
  public static Factors naive(long n) {
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

  /**
   * Pollard's rho 法でnの非自明な約数を見つける.
   *
   * <p>ヒューリスティックな乱択アルゴリズムなので、停止しない可能性がある.
   *
   * @param n 合成数
   * @return nの非自明な約数
   */
  private static long findDivisor(long n) {
    if (n % 2 == 0) return 2;

    while (true) {
      long s = rng.nextLong(n);
      long z = rng.nextLong(n);
      // 乱数列生成器
      LongUnaryOperator f =
          x -> {
            long sq = MathUtil.modPow(x, 2, n) + z;
            if (sq >= n) sq -= n;
            return sq;
          };
      long x = s;
      long y = s;
      do {
        x = f.applyAsLong(x);
        y = f.applyAsLong(f.applyAsLong(y));
      } while (MathUtil.gcd(Math.abs(x - y), n) == 1);

      long g = MathUtil.gcd(Math.abs(x - y), n);
      if (g != n) return g;
    }
  }

  /**
   * Pollard's rho 法による素因数分解. O(n^{1/4}) と予想されているが未証明.
   *
   * @param n
   * @return nの素因数分解
   */
  public static Factors pollard(long n) {
    ArrayList<Long> ps = new ArrayList<>();
    ps.add(n);

    int i = 0;
    while (i < ps.size()) {
      long factor = ps.get(i);
      if (factor == 1 || PrimeJudger.isPrime(factor)) {
        ++i;
      } else {
        long d = findDivisor(factor);
        ps.set(i, factor / d);
        ps.add(d);
      }
    }
    ps.sort(null);

    // ランレングス圧縮
    Factors factors = new Factors();
    long prev = -1;
    int e = 0;
    for (long p : ps) {
      if (p != prev) {
        if (e != 0) factors.add(prev, e);
        prev = p;
        e = 0;
      }
      ++e;
    }
    factors.add(prev, e);

    return factors;
  }

  /**
   * nの素因数分解を行う.
   *
   * @param n
   * @return nの素因数分解.
   */
  public static Factors factorize(long n) {
    return pollard(n);
  }
}
