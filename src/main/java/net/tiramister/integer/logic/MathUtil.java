package net.tiramister.integer.logic;

import java.math.BigInteger;

/** 汎用的な数学的関数群. */
public class MathUtil {
  /**
   * 最大公約数を求める.
   *
   * @param x 非負整数
   * @param y 非負整数
   * @return xとyの最大公約数
   */
  public static long gcd(long x, long y) {
    if (x < y) return gcd(y, x);

    while (y != 0) {
      long r = x % y;
      x = y;
      y = r;
    }
    return x;
  }

  /**
   * x^e を m で割った余りを求める.
   *
   * @param x 整数
   * @param e 非負整数
   * @param m 正の整数
   * @return x^e mod m
   */
  public static long modPow(long x, long e, long m) {
    return BigInteger.valueOf(x).modPow(BigInteger.valueOf(e), BigInteger.valueOf(m)).longValue();
  }
}
