package org.domain.model;

import org.usermanagement.domain.model.User;

public class BoardPermissionFactory {

    public BoardPermissionFactory(){

    }
    public BoardPermission create(final User userp,
                        final AccessLevel accessLevelp) {
        return new BoardPermission(
                userp,
                accessLevelp
        );
    }
}
