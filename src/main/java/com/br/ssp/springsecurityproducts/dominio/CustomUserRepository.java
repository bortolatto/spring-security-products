package com.br.ssp.springsecurityproducts.dominio;

import com.br.ssp.springsecurityproducts.controller.UserDTO;

public interface CustomUserRepository {

    UserDTO findUserDTOWithAuthorities(String username);
}
