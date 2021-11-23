package com.project.tdd.authorization;

import com.project.tdd.authentication.token.Token;

import java.util.HashMap;
import java.util.List;

public class AuthorizationService {
    enum Resource {ACCOUNT, PROVISION_CALC}

    enum Rights {READ, WRITE, EXECUTE}

    HashMap<String, Access> privileges;

    public AuthorizationService() {
        privileges = new HashMap<>();

        privileges.put("anna", new Access(Resource.ACCOUNT.toString(), List.of(Rights.READ.toString())));
        privileges.put("berit", new Access(Resource.ACCOUNT.toString(), List.of(Rights.READ.toString(), Rights.WRITE.toString())));
        privileges.put("kalle", new Access(Resource.PROVISION_CALC.toString(), List.of(Rights.EXECUTE.toString())));

    }

    public List<String> isAuthorized(Token token, String resource) {
        if (privileges.containsKey(token.getUsername())) {
            return privileges.get(token.getUsername()).getAccess(resource);
        }
        return List.of("Not authorized");
    }

}
