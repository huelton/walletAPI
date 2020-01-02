package com.wallet.service;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.User;
import com.wallet.repository.UserRepository;

//@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@MockBean
	UserRepository repository;
	
	@Autowired
	UserService service;
	
	private static final String EMAIL = "email@teste.com";
	
	@Before
	public void setUp() {
		BDDMockito.given(repository.findByEmailEquals(Mockito.anyString()))
		                           .willReturn(Optional.of(new User()));
	}
	
	@Test
	public void testFindByEmail() {
		Optional<User> user = service.findByEmail(EMAIL);
		assertTrue(user.isPresent());
	}
}
