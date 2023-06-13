package org.shared.board.app;

import eapli.framework.io.util.Console;
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
        String data = "";

        String content = "";
        final String rowPos = Console.readLine("Post-It Row position:");
        final String colPos = Console.readLine("Post-It Column position:");
        final String boardId = Console.readLine("Board Id:");

        String opt = "";

        do {
            System.out.println("1 - Text Content");
            System.out.println("2 - Image Content");
            opt = Console.readLine("Option - ");

            switch (opt) {
                case "1":
                    content = Console.readLine("Post-It Content:");
                    break;
                case "2":
                    String filePath = Console.readLine("Image path:");
                    content = ImgurUpload.uploadImage(filePath);
                    break;
                default:
                    opt = "-1";
                    break;
            }
        } while ("-1".compareTo(opt) == 0);

        data = content + "\0" + rowPos + "\0" + colPos + "\0" + boardId + "\0";

        System.out.println();

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
