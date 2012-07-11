package com.businessdirecotory.server.authorization;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SessionExpireTimeProviderImpl implements SessionExpireTimeProvider {

  public Date getSessionExpireTime() {

    Calendar calendar = Calendar.getInstance();

    calendar.add(Calendar.DAY_OF_MONTH, 20);

    return calendar.getTime();

  }
}
