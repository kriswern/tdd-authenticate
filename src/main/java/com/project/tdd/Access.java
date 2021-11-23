package com.project.tdd;

import java.util.HashMap;
import java.util.List;

public class Access {

    private HashMap<String, List<String>> access;

    public Access(String resource, List<String> accessRights) {
        access = new HashMap<>();
        access.put(resource, accessRights);
    }

    public List<String> getAccess(String resource) {
        if(access.containsKey(resource)){
            return access.get(resource);
        }
        return List.of("No access");
    }
}
