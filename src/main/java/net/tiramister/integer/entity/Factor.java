package net.tiramister.integer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Factor {
  private long prime;
  private int exp;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getPrime());
    if (this.getExp() != 1) {
      sb.append("^{");
      sb.append(this.getExp());
      sb.append("}");
    }
    return sb.toString();
  }
}
