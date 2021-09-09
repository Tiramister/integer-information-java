package net.tiramister.integer.logic;

public class PrimeJudge {
  // Θ(sqrt(n))の判定アルゴリズム
  // TODO: Miller-Rabinで実装
  public static Boolean isPrime(Long n) {
    if (n == 1L) return false;
    for (Long p = 2L; p * p <= n; ++p) {
      if (n % p == 0) return false;
    }
    return true;
  }
}
