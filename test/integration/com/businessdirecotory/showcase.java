package com.businessdirecotory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */

public class Showcase {



  private LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());


  private DatastoreService service;


  @Before
  public void setUp() throws Exception {

    helper.setUp();
    service = DatastoreServiceFactory.getDatastoreService();

  }

  @After
  public void tearDown() throws Exception {
    helper.tearDown();
  }

//  static class Person {
//
//    @Id
//    private Long id;
//
//    @Index(true)
//    private String name;
//
//
//    private Integer age;
//
//
//  }

  @Test
  public void keyToString() {

    Entity entity = new Entity("Account");

    service.put(entity);

    String stringKey = KeyFactory.keyToString(entity.getKey());

    Key key = KeyFactory.stringToKey(stringKey);

    assertThat(key, is(equalTo(key)));


  }

//  @Test
//  public void twigSample() throws Exception {
//
//    Person person = new Person();
//
//    person.id = 10l;
//
//    person.name = "Stefan";
//
//    person.age = 20;
//
//    ObjectDatastore datastore = new AnnotationObjectDatastore(false);
//
//    datastore.store().instance(person).now();
//
//    datastore.disassociateAll();
//
//    Person existingPerson = datastore.load().type(Person.class).id(10l).now();
//
//    System.out.println(existingPerson.name);
//
//    List<Person> personList = datastore.find().type(Person.class).addFilter("name", Query.FilterOperator.EQUAL, "Stefan")
//            .returnAll().now();
//
//    for (Person p : personList) {
//      System.out.println(p.name);
//
//
//    }
//  }

  class Expense {

    public final String name;

    public final Double amount;

    Expense(String name, Double amount) {
      this.name = name;
      this.amount = amount;
    }

  }

  @Test
  public void sampleTest() throws Exception {

    Entity account = new Entity("Account", "mgenov@gmail.com");

    account.setProperty("name", "Miroslav Genov");

    service.put(account);

    registerExpense(account, "Paper", 20d);

    registerExpense(account, "Pens", 10d);

    registerExpense(account, "TV", 15d);

    Query query = new Query("Expense");

    query.addFilter("account", Query.FilterOperator.EQUAL, account.getKey());

    query.addFilter("date", Query.FilterOperator.EQUAL, new Date());

    List<Expense> expenses = new ArrayList<Expense>();

    for (Entity entity : service.prepare(query).asIterable()) {

      System.out.println("Expense");

      System.out.println("Name: " + entity.getProperty("name"));

      System.out.println("Amount: " + entity.getProperty("amount"));

      System.out.println();

      expenses.add(new Expense((String) entity.getProperty("name"), (Double) entity.getProperty("amount")));

      System.out.println();

    }

    // 1 request = 30 seconds -> 1000000000000 rows !!!!


    // task queue -> 1000 - 2000 rows -> blobstore

    // 50 mb file ?? 6mb -> < 1s


    // return to GWT !!!
  }

  // name, age, firstname, family
  // 1 Pesho, 20, Peter, Petrov
  // 2 Stefan, 15, Stefan, Stoqnov
  // 3 Pesho, 20, Peter, Stoqnov
  // 4 Pesho, 10, Peter, dimitrov


  // Pesho, 20 godini !


  // Pesho10  -> 4
  // Pesho20  -> 1, 3
  // Stefan15 -> 2

  @Test
  public void sampleSearch() throws Exception {
    Entity account = new Entity("Account", "mgenov@gmail.com");

    account.setProperty("name", "Miroslav Genov");
    Set<String> index = new HashSet<String>();
    index.add("m");
    index.add("mi");
    index.add("mir");
    index.add("miro");
    index.add("miros");

    index.add("ivan");
    index.add("pirot");
    index.add("gorna");


    account.setProperty("index", index);
    service.put(account);


    Query query = new Query("Account");
    query.addFilter("index", Query.FilterOperator.EQUAL, "ivan".toLowerCase());
    query.addFilter("index", Query.FilterOperator.EQUAL, "pirot".toLowerCase());

    // cursor ! -> forwarding cursors only
    for (Entity entity : service.prepare(query).asIterable()) {
      String name = (String) entity.getProperty("name");
      System.out.println("Name: " + name);
    }

  }


  @Test
  public void ancestorQuery() throws Exception {

    Entity account = new Entity("Account", "mgenov@gmail.com");

    account.setProperty("name", "Miroslav Genov");

    service.put(account);

    for (int i = 0; i < 10; i++) {

      Entity expense = new Entity("Expense", account.getKey());

      expense.setProperty("name", "Name" + i);

      expense.setProperty("amount", 20d);

      service.put(expense);

    }

    Query query = new Query("Expense");

    query.setAncestor(account.getKey());

    for (Entity entity : service.prepare(query).asIterable()) {
      System.out.println("Name: " + entity.getProperty("name"));
    }
  }

  private void registerExpense(Entity account, String name, double amount) {

    Entity expense = new Entity("Expense");

    expense.setProperty("name", name);
    expense.setProperty("amount", amount);
    expense.setProperty("account", account.getKey());
    expense.setProperty("date", new Date());
    service.put(expense);

  }

  @Test
  public void returnsAllYearsOfExpenses() {

    Calendar calendar = Calendar.getInstance();

    calendar.set(2012, Calendar.FEBRUARY, 1);

    int dayOfFeb = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

  }

  //TODO: try to save a list with 2500 entities.

  //TODO: crate a list editor for expenses .... !


}