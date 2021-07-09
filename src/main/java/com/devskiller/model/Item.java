package com.devskiller.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(max = 100)
	@NotEmpty
	@Column(nullable = false,length = 100)
	private String title;

	@Size(max = 200)
	@Column(length = 200)
	private String description;

	@OneToMany(orphanRemoval = true,mappedBy = "item",cascade = CascadeType.ALL)
	Set<Review> reviews = new HashSet<>();

	public Item() {
	}

	public Item(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		reviews.add(review);
		review.setItem(this);
	}
}
