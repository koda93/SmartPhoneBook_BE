package com.soda.phonebook.dto.res;

import java.util.List;
import java.util.Set;

import com.soda.phonebook.domain.Contact;
import com.soda.phonebook.domain.Digit;
import com.soda.phonebook.domain.Tag;
import com.soda.phonebook.domain.info.Info;

import lombok.Getter;

@Getter
public class ContactResponseDto {
	private String name;
	private String memo;
	private byte[] photo;
	private List<Digit> digits;
	private List<Info> infoes;
	private Set<Tag> tags;
	
	public ContactResponseDto(Contact contact) {
		this.name = contact.getName();
		this.memo = contact.getMemo();
		this.photo = contact.getPhoto();
		this.digits = contact.getDigits();
		this.infoes = contact.getInfoes();
		this.tags = contact.getTags();
	}
}