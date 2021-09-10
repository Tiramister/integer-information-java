package net.tiramister.integer.logic;

import java.util.ArrayList;
import net.tiramister.integer.entity.Factor;

public class Factorizer {
  // O(n^{1/2})
  // TODO: Pollard's rho algorithm による O(n^{1/4}) の実装
  public static ArrayList<Factor> factorize(Long n) {
    ArrayList<Factor> factors = new ArrayList<Factor>();

    if (n == 1) {
      factors.add(new Factor(1L, 1));
      return factors;
    }

    for (Long p = 2L; p * p <= n; ++p) {
      if (n % p != 0) continue;
      int exp = 0;
      while (n % p == 0) {
        n /= p;
        ++exp;
      }
      factors.add(new Factor(p, exp));
    }

    if (n != 1) {
      factors.add(new Factor(n, 1));
    }

    return factors;
  }
}
