package it.emanuelebondattidev.WebfluxLearning.modules.user;

import org.springframework.beans.BeanUtils;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository{

	private final R2dbcEntityTemplate template;

	@Override
	public Mono<User> createUser(Mono<User> dto) {

		return dto.mapNotNull(this::toEntity)
		.log()
		.flatMap( x -> {
			return template.insert( UserEntity.class ).using( x );
		})
		.mapNotNull(this::toDTO);
	}


	private UserEntity toEntity( User from ) {

		UserEntity to = new UserEntity();

		if( from != null ) {
			BeanUtils.copyProperties( from, to );
		}

		return to;

	}

	private User toDTO( UserEntity from ) {

		User to = new User();

		if( from != null ) {
			BeanUtils.copyProperties( from, to );
		}

		return to;

	}

}
