package net.tiramister.integer.logic;

import java.math.BigInteger;

public class MathUtil {
  public static long gcd(long x, long y) {
    if (x < y) return gcd(y, x);

    while (y != 0) {
      long r = x % y;
      x = y;
      y = r;
    }
    return x;
  }

  public static long modPow(long x, long e, long m) {
    return BigInteger.valueOf(x).modPow(BigInteger.valueOf(e), BigInteger.valueOf(m)).longValue();
  }
}
