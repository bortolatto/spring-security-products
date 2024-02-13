package com.br.ssp.springsecurityproducts.dominio;

import com.br.ssp.springsecurityproducts.controller.UserDTOTupleTransformer;
import com.br.ssp.springsecurityproducts.controller.UserDTO;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final EntityManager entityManager;

    public CustomUserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    @Override
    public UserDTO findUserDTOWithAuthorities(String username) {
        return (UserDTO) entityManager.createNativeQuery("""
                        SELECT u.username
                             , u.algorithm
                             , u.password
                             , a.name as auth_name                
                          from domain_user u
                          join authority a on a.user_id = u.id
                          where u.username = :username
                        """, UserDTO.class).setParameter("username", username)
                .unwrap(org.hibernate.query.Query.class)
                .<UserDTO>setTupleTransformer(new UserDTOTupleTransformer()).getSingleResultOrNull();
    }
}
