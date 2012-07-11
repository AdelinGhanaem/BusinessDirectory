package com.businessdirecotory.server;

import org.junit.Test;

import java.util.Calendar;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class Spike {


  @Test
  public void calendarSpike() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MONTH, 1);
    System.out.print(calendar.getTime());
  }
}
