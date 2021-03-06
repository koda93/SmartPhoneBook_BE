package com.soda.phonebook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soda.phonebook.domain.Contact;
import com.soda.phonebook.domain.User;
import com.soda.phonebook.dto.res.ContactListReadResponseDto;
import com.soda.phonebook.repository.UserRepository;
import com.soda.phonebook.security.GoogleUser;
import com.soda.phonebook.security.SessionConstants;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;

	/*
	// test
	public User testUser() {
		User findUser = userRepository.findByName("테스트유저");
		if( findUser != null) {
			return findUser;
		}else {
			return save(User.builder().name("테스트유저").build());
		}
	}
	
	public User getCurrentUser() {
		// test
		User savedUser = testUser();
		return savedUser;
	}
	*/
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Transactional(readOnly = true)
	public List<ContactListReadResponseDto> getFavorites(User user) {
		User findUser = findByEmail(user.getEmail());
		
		List<ContactListReadResponseDto> favorites = new ArrayList<>();
		for(Contact contact : findUser.getFavorites())
			favorites.add(ContactListReadResponseDto.builder()
					.contact(contact).build());
		return favorites;
	}
	
//	private User findById(Long id) {
//		return userRepository.findById(id)
//				.orElseThrow(()->new IllegalArgumentException("findById error : wrong id")); 
//	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	/*
	@Transactional
    public User getOrSave(GoogleUser google){
        User savedUser = userRepository.findByEmail(google.getEmail());

        if(savedUser == null){
            User newUser = google.toEntity();
            newUser.updateRole(SessionConstants.LOGIN_USER);;
            savedUser = userRepository.save(newUser);
        }

        return savedUser;
    }*/
}
