package net.tiramister.integer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class YesNo {
  boolean yes;

  @Override
  public String toString() {
    return this.isYes() ? "Yes" : "No";
  }
}
