package com.soda.phonebook.domain.info;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.soda.phonebook.domain.Category;
import com.soda.phonebook.domain.Contact;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("A")
public class Address extends Info{
	
	@Builder
	public Address(Long id, Contact contact, String contents, Category category) {
		super(id, contact, contents, category);
	}
}
