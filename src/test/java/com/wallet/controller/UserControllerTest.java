package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.UserDTO;
import com.wallet.entity.User;
import com.wallet.service.impl.UserServiceImpl;

//@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@MockBean
	UserServiceImpl service;

	@Autowired
	MockMvc mvc;

	private static final String NAME = "Teste";
	private static final String PASSWORD = "senha123";
	private static final String EMAIL = "email@teste.com";
	private static final String URL = "/user";

	@Test
	public void testSave() throws Exception {
		BDDMockito.given(service.save(Mockito.any(User.class)))
        .willReturn(getMockUser());
		
		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}

	public User getMockUser() {
		User u = new User();
		u.setName(NAME);
		u.setPassword(PASSWORD);
		u.setEmail(EMAIL);

		return u;
	}

	public String getJsonPayload() throws JsonProcessingException {
		UserDTO dto = new UserDTO();
		dto.setName(NAME);
		dto.setPassword(PASSWORD);
		dto.setEmail(EMAIL);

		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(dto);

	}

}
