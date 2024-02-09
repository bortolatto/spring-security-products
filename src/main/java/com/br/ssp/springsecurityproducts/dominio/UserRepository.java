package com.br.ssp.springsecurityproducts.dominio;

import com.br.ssp.springsecurityproducts.dominio.CustomUserRepository;
import com.br.ssp.springsecurityproducts.dominio.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
}
