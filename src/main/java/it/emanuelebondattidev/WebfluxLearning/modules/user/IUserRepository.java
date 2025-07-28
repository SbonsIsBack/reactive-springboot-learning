package it.emanuelebondattidev.WebfluxLearning.modules.user;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IUserRepository extends ReactiveCrudRepository<UserEntity, UUID>, CustomUserRepository{
	

}
