package com.devskiller.model;

import org.junit.Test;

import javax.persistence.TypedQuery;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class UserEntityTest extends BaseEntityTest {

  @Test
  public void shouldCreateUser() {
    execute(entityManager -> {
      User user = new User("testUser");
      entityManager.persist(user);
    });

    execute(entityManager -> {
      TypedQuery<User> query = entityManager.createQuery("from User u where u.username=:username", User.class);
      query.setParameter("username", "testUser");
      User loadedUser = query.getSingleResult();

      assertThat("User must not be null", loadedUser, notNullValue());
    });

  }

}
