package net.tiramister.integer.logic;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.LongUnaryOperator;
import net.tiramister.integer.entity.Factors;

public class Factorizer {
  private static ThreadLocalRandom rng = ThreadLocalRandom.current();

  // naive algorithm
  // O(n^{1/2})
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

  // Pollard's rho algorithm
  // O(n^{1/4}) expected?
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

  public static Factors factorize(long n) {
    return pollard(n);
  }
}
