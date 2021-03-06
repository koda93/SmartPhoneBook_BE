package com.soda.phonebook.domain;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.soda.phonebook.config.JpaAuditConfiguration;
import com.soda.phonebook.domain.VO.ContactType;
import com.soda.phonebook.repository.ContactRepository;
import com.soda.phonebook.repository.UserRepository;

@Import(value = JpaAuditConfiguration.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class JoinTableTest {
	
	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	private Contact contact;
	private User user;
	private Set<Tag> tags;
	private Contact c;
	
	@Before
	public void setUp() {
		// tag-contact
		user = User.builder()
				.name("테스트유저")
				.build();
		
		User savedUser = userRepository.save(user);
		
		tags = new HashSet<Tag>();
		
		tags.add(Tag.builder()
				.name("학교친구")
				.user(savedUser)
				.build());
		
		tags.add(Tag.builder()
				.name("동네친구")
				.user(savedUser)
				.build());
		
		contact = Contact.builder()
				.name("연락처1")
				.type(ContactType.DEFAULT)
				.user(savedUser)
				.tags(new HashSet<Tag>())
				.build();
		
		// user-contact
		c = Contact.builder()
				.user(savedUser)
				.name("A")
				.type(ContactType.DEFAULT)
				.build();
		em.persist(c);
		em.flush();
		em.clear();
	}
	
	@Test
	public void test_tag_contact_MtoM_tagContact_조인테이블() throws Exception{
		
		assertEquals(0, contact.getTags().size());
		for(Tag tag : tags)
			contact.getTags().add(tag);
		Contact savedContact = contactRepository.save(contact);
		
		assertNotNull(savedContact.getTags());
	}
	
	@Test
	public void test_user_contact_1toM_favorite_조인테이블() throws Exception{
		
		user.getFavorites().add(c);
		User savedUser = userRepository.save(user); // cascade
		
		Set<Contact> findFavorite = savedUser.getFavorites();
		assertThat(findFavorite.stream().findFirst().get().getName(),is("A"));
	}
}
