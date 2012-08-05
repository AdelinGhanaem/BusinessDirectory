package com.businessdirecotory.server.authorization;

import java.util.UUID;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class IdGeneratorImpl implements IdGenerator {
  public Long generateId() {
    return UUID.randomUUID().getMostSignificantBits();
  }
}
