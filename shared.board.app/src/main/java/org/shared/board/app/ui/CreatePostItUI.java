package org.shared.board.app.ui;

import eapli.framework.io.util.Console;
import org.shared.board.app.ImgurUpload;
import org.shared.board.app.SharedBoardAppController;
import org.shared.board.app.Util;
import org.shared.board.common.Message;
import org.shared.board.common.MessageCodes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * The type CreatePostItUI ui.
 */
public class CreatePostItUI {
    private SharedBoardAppController theController;

    /**
     * Instantiates a new create postIt ui.
     * @param theControllerp the the controllerp
     */
    public CreatePostItUI(final SharedBoardAppController theControllerp) {
        this.theController = theControllerp;
    }

    /**
     * Ask user Row position.
     * Ask user Col position.
     * Ask user board id.
     * Ask user type of content.
     * Call method to Create post-it.
     */
    protected void doShow() {
        String data = Util.postItBody();

        Message result = theController.createPostIt(data);

        if (result.code() == MessageCodes.ACK) {
            System.out.println("Post-It created successfully!\n");
        } else {
            String errorData;

            if (result.data().length > 0) {
                errorData = new String(result.data(),
                        StandardCharsets.US_ASCII);
            } else {
                errorData = "Invalid data!";
            }

            System.out.println(errorData + "\n");
        }
    }
}
