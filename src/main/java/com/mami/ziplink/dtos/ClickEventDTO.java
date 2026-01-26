package com.mami.ziplink.dtos;

import java.time.LocalDate;

public class ClickEventDTO {
  private LocalDate clickDate;
  private Long count;

  public void setClickDate(LocalDate clickDate) {
    this.clickDate = clickDate;
  }

  public void setCount(Long count) {
    this.count = count;
  }
}
