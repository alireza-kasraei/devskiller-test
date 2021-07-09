package com.devskiller.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Max(10)
	@Min(0)
	@Column(nullable = true, length = 10)
	private Integer rating;

	@Size(min = 0, max = 10)
	@Column(length = 200)
	private String comment;

	@ManyToOne
	private Item item;

	@ManyToOne
	private User author;

	public Review() {
	}

	public Review(Integer rating, String comment, User author) {
		this.rating = rating;
		this.comment = comment;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public Integer getRating() {
		return rating;
	}

	public String getComment() {
		return comment;
	}

	public Item getItem() {
		return item;
	}

	public User getAuthor() {
		return author;
	}

	void setItem(Item item) {
		this.item = item;
	}
}
