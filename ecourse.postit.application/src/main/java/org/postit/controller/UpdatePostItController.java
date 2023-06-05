package org.postit.controller;

import eapli.framework.validations.Preconditions;
import org.authz.application.AuthorizationService;
import org.authz.application.AuthzRegistry;
import org.domain.model.postit.PostIt;
import org.persistence.PersistenceContext;
import org.postit.service.PostItService;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.User;

/**
 * The type Update post it controller.
 */
public class UpdatePostItController {
    /**
     * Authorization service instance.
     */
    private final AuthorizationService authz;

    /**
     * Create a board service with repository injection.
     */
    private final PostItService postItSvc = new PostItService(
            PersistenceContext.repositories().postIt(),
            PersistenceContext.repositories().boards());

    /**
     * Instantiates CreatePostItController.
     */
    public UpdatePostItController() {
        authz = AuthzRegistry.authorizationService();
    }

    /**
     * Update post it content post-it.
     * @param postItContentp the post-it contentp
     * @param postItRowp     the post-it rowp
     * @param postItColumnp  the post-it columnp
     * @param boardIdp       the board idp
     * @return the post-it
     */
    public PostIt updatePostItContent(final String postItContentp,
                                      final String postItRowp,
                                      final String postItColumnp,
                                      final String boardIdp) {
        authz.ensureAuthenticatedUserHasAnyOf(CourseRoles.allRoles());

        return postItSvc.updateContent(postItContentp, postItRowp, postItColumnp,
                boardIdp, authz.session().get().authenticatedUser());
    }

    /**
     * Update post it content post-it.
     * @param postItContentp the post-it contentp
     * @param postItRowp     the post-it rowp
     * @param postItColumnp  the post-it columnp
     * @param boardIdp       the board idp
     * @param authUser       the auth user
     * @return the postit
     */
    public PostIt updatePostItContent(final String postItContentp,
                                      final String postItRowp,
                                      final String postItColumnp,
                                      final String boardIdp,
                                      final User authUser) {
        Preconditions.ensure(authUser != null,
                "You need to authenticate first");

        return postItSvc.updateContent(postItContentp, postItRowp, postItColumnp,
                boardIdp, authUser);
    }
}
