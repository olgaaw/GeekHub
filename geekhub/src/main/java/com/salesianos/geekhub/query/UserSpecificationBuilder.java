package com.salesianos.geekhub.query;

import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.util.SearchCriteria;

import java.util.List;

public class UserSpecificationBuilder
        extends GenericSpecificationBuilder<User>{
    public UserSpecificationBuilder(List<SearchCriteria> params) {
        super(params);
    }
}