package com.wallet.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	@NotNull
	@Length(min=6, message="A senha deve conter no minimo 6 caracteres")
	private String password;
	@Length(min=5, max=50, message="O nome deve estar entre 5 e 50 caracteres")
	private String name;
	@Email(message="Email invalido")
	private String email;
}
