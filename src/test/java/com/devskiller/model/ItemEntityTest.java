package com.devskiller.model;

import org.junit.Test;

import javax.persistence.TypedQuery;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ItemEntityTest extends BaseEntityTest {

  @Test
  public void shouldCreateItem() {
    execute(entityManager -> {
      Item item = new Item("Test title", "Test description");
      entityManager.persist(item);
    });

    execute(entityManager -> {
      TypedQuery<Item> query = entityManager.createQuery("from Item", Item.class);
      List<Item> items = query.getResultList();
      assertThat("There should be one item created", items.size(), equalTo(1));
    });

  }

}
