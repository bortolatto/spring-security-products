package com.br.ssp.springsecurityproducts.controller;

import org.hibernate.query.TupleTransformer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserDTOTupleTransformer implements TupleTransformer<UserDTO> {

    private Map<String, UserDTO> userDTOMap = new LinkedHashMap<>();

    @Override
    public UserDTO transformTuple(Object[] tuple, String[] aliases) {
        Map<String, Integer> mapAliases = mapAliases(aliases);
        String user = tuple[mapAliases.get(UserDTO.USERNAME_ALIAS)].toString();

        UserDTO userDTO = userDTOMap.computeIfAbsent(user, id -> {
            String _algorithm = tuple[mapAliases.get(UserDTO.ALGORITHM_ALIAS)].toString();
            String _password = tuple[mapAliases.get(UserDTO.PASSWORD_ALIAS)].toString();
            return new UserDTO(user, _password, _algorithm);
        });

        userDTO.getAuthorities().add(new AuthorityDTO(tuple[mapAliases.get(UserDTO.AUTH_NAME_ALIAS)].toString()));

        return userDTOMap.get(user);
    }

    private Map<String, Integer> mapAliases(String[] aliases) {
        Map<String, Integer> aliasesMap = new HashMap<>();
        for (int i = 0; i < aliases.length; i++) {
            aliasesMap.put(aliases[i].toUpperCase(), i);
        }
        return aliasesMap;
    }

}
