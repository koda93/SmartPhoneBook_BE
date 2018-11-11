package com.soda.phonebook.domain.info;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.soda.phonebook.domain.Category;
import com.soda.phonebook.domain.Contact;
import com.soda.phonebook.domain.Info;

import lombok.Builder;

@Entity
@DiscriminatorValue("D")
public class Date extends Info{
	@Builder
	public Date(Contact contact, String contents, Category category) {
		super(contact, contents, category);
	}
}
