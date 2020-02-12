package com.contactfilter.constants;

import javax.naming.OperationNotSupportedException;

public final class EndpointPath {

    public static final String CONTACTS = "/hello/contacts";

    private EndpointPath() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Do not instantiate the " + getClass().getName() + " class, this is util class.");
    }
}
