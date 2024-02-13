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
        String user = tuple[mapAliases.get("username")].toString();

        UserDTO userDTO = userDTOMap.computeIfAbsent(user, id -> {
            String _algorithm = tuple[mapAliases.get("algorithm")].toString();
            String _password = tuple[mapAliases.get("password")].toString();
            return new UserDTO(user, _password, _algorithm);
        });

        userDTO.getAuthorities().add(new AuthorityDTO(tuple[mapAliases.get("auth_name")].toString()));

        return userDTOMap.get(user);
    }

    private Map<String, Integer> mapAliases(String[] aliases) {
        Map<String, Integer> aliasesMap = new HashMap<>();
        for (int i = 0; i < aliases.length; i++) {
            aliasesMap.put(aliases[i], i);
        }
        return aliasesMap;
    }

}
