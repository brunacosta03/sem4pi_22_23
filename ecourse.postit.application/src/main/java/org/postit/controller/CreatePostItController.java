package org.postit.controller;

import eapli.framework.application.UseCaseController;
import org.authz.application.AuthorizationService;
import org.authz.application.AuthzRegistry;
import org.domain.model.postit.PostIt;
import org.persistence.PersistenceContext;
import org.postit.service.PostItService;
import org.user.management.CourseRoles;

/**
 * Controller class for adding a new post-it to the board.
 */
@UseCaseController
public class CreatePostItController {
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
    public CreatePostItController() {
        authz = AuthzRegistry.authorizationService();
    }


    /**
     * Create post it.
     * @param postItContentp the post it contentp
     * @param postItRowp     the post it rowp
     * @param postItColumnp  the post it columnp
     * @param boardIdp       the board idp
     * @return the post it
     */
    public PostIt createPostIt(final String postItContentp,
                               final String postItRowp,
                               final String postItColumnp,
                               final String boardIdp) {
        authz.ensureAuthenticatedUserHasAnyOf(CourseRoles.allRoles());

        return postItSvc.createPostIt(postItContentp, postItRowp, postItColumnp,
                authz.session().get().authenticatedUser(), boardIdp);
    }
}
