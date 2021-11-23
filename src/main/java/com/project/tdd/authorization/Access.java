package com.project.tdd.authorization;

import java.util.HashMap;
import java.util.List;

public class Access {

    private final HashMap<String, List<String>> access;

    public Access(String resource, List<String> accessRights) {
        access = new HashMap<>();
        access.put(resource, accessRights);
    }

    public List<String> getAccess(String resource) {
        if (access.containsKey(resource)) {
            return access.get(resource);
        }
        return List.of("No access");
    }
}
