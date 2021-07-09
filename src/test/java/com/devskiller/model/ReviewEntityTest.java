package com.devskiller.model;

import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ReviewEntityTest extends BaseEntityTest {

  @Test
  public void shouldCreateReview() {
    execute(entityManager -> {
      Item item = new Item("Test title", "Test description");
      User user = new User("reviewAuthor");
      entityManager.persist(user);
      entityManager.persist(item);
      item.addReview(new Review(7, "comment", user));
      entityManager.persist(item);
    });

    execute(entityManager -> {
      TypedQuery<Item> query = entityManager.createQuery("from Item", Item.class);
      List<Item> items = query.getResultList();
      assertThat("There should be one item created", items.size(), equalTo(1));

      TypedQuery<Review> reviewQuery = entityManager.createQuery("from Review", Review.class);
      List<Review> reviews = reviewQuery.getResultList();
      assertThat("There should be one review created", reviews.size(), equalTo(1));
    });

  }

}
