package com.businessdirecotory.server.authorization;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class SessionExpireTimeProviderImpl implements SessionExpireTimeProvider {

  public Date getSessionExpireTime() {

    Calendar calendar = Calendar.getInstance();

    calendar.add(Calendar.MONTH, 1);

    return calendar.getTime();

  }
}
