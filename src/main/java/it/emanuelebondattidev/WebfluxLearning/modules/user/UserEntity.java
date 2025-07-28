package it.emanuelebondattidev.WebfluxLearning.modules.user;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table(name="users")
@Data
public class UserEntity {
	
	@Id
	@Column
	private UUID id;
	
	@Column("first_name")
	private String firstName;
	
	@Column("last_name")
	private String lastName;
	
	@Column
	private String email;
	
	@Column
	private String password;

}
