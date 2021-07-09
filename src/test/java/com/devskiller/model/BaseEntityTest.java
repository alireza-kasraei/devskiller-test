package com.devskiller.model;

import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.function.Consumer;

public abstract class BaseEntityTest {

  protected EntityManagerFactory entityManagerFactory;

  @Before
  public void setup() {
    entityManagerFactory = Persistence.createEntityManagerFactory("devskiller");
  }

  public void cleanup() {
    entityManagerFactory.close();
  }

  protected void execute(Consumer<EntityManager> operation) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    try {
      operation.accept(entityManager);
      entityManager.flush();
      transaction.commit();
    } catch(Throwable t) {
      transaction.rollback();
      throw t;
    } finally {
      entityManager.close();
    }
  }
}
