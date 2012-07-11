package com.businessdirecotory.server.authorization;

import java.util.Date;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public interface SessionExpireTimeProvider {
   Date getSessionExpireTime();
}
