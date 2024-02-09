package com.br.ssp.springsecurityproducts.security;

import com.br.ssp.springsecurityproducts.dominio.UserRepository;
import com.br.ssp.springsecurityproducts.controller.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserSecurity loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTOWithAuthorities = userRepository.findUserDTOWithAuthorities(username);
        if (userDTOWithAuthorities == null) return null;
        return new UserSecurity(userDTOWithAuthorities);
    }
}
