package net.tiramister.integer.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.tiramister.integer.entity.Result;

@Getter
@Setter
@NoArgsConstructor
public class ResultForm {
  private long n;
  private String isPrime;
  private String factors;

  public static ResultForm build(Long n) {
    if (n == null) return null;

    ResultForm resultForm = new ResultForm();
    resultForm.setN(n);

    Result result = new Result(n);
    resultForm.setIsPrime(result.isPrime() ? "Yes" : "No");

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < result.getFactors().size(); ++i) {
      sb.append(result.getFactors().get(i));
      if (i + 1 < result.getFactors().size()) {
        sb.append(" \\cdot ");
      }
    }
    resultForm.setFactors(sb.toString());

    return resultForm;
  }
}
