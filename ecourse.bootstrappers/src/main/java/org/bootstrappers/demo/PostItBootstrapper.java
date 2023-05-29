package org.bootstrappers.demo;

import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.domain.model.postit.PostIt;
import org.postit.controller.CreatePostItController;

import java.util.NoSuchElementException;

public class PostItBootstrapper implements Action {
    CreatePostItController theController = new CreatePostItController();
    @Override
    public boolean execute() {
        registerPostIt("Content Post-It 1", "Title Row 2", "Title Column 3", "1");
        registerPostIt("Content Post-It 2", "Title Row 2", "Title Column 2", "1");
        registerPostIt("Content Post-It 3", "Title Row 3", "Title Column 3", "1");
        registerPostIt("Content Post-It 4", "2", "2", "4");
        registerPostIt("Content Post-It 5", "2", "3", "4");
        registerPostIt("Content Post-It 6", "3", "3", "4");

        return true;
    }


    private void registerPostIt(final String postItContentp,
                                final String postItRowp,
                                final String postItColumnp,
                                final String boardIdp) {
        try{
            PostIt postIt = theController.createPostIt(postItContentp,
                  postItRowp, postItColumnp, boardIdp);

            System.out.println("[+] Post-It Id - " + postIt.identity());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IntegrityViolationException e){
            System.out.println("Already exist --> Post-It Row " + postItRowp
                    + " Post-It Column --> " + postItColumnp
                    + " Board --> " + boardIdp);
        } catch (NoSuchElementException e){
            System.out.println("Board with that id doesn't exist");
        }
    }
}
