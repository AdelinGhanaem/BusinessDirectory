package com.businessdirecotory.shared.entites;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Adelin Ghanayem <adelin.ghanaem@clouway.com>
 */
public class User implements Serializable {

  private long id;

  @Pattern(regexp = "^[a-zA-Z0-9]{6,}$", message = "Парола с по малко от 6 символа е не валидна!")
  private String password;

  @NotEmpty(message = "Празен e-mail !")
  @Email(message = "Не валидна форма на e-mail: ")
  private String username;



  public User(long id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User() {
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public long getCompanyId() {
    return id;
  }

  public void setIdCompanyId(long id) {
    this.id = id;
  }
}
