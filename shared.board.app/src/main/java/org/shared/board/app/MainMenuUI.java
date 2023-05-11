package org.shared.board.app;

import org.shared.board.common.MessageCodes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainMenuUI {
    private Socket sock;
    private SharedBoardAppController theController;

    public MainMenuUI(Socket sockp) {
        this.sock = sockp;
        this.theController = new SharedBoardAppController(sockp);
    }

    public void handleACK(int codeResult,
                          String messageOK,
                          String messageBAD){
        if(codeResult == MessageCodes.ACK){
            System.out.println(messageOK);
        } else {
            System.out.println(messageBAD);
        }
    }

    public void doShow() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        int choice = 0, codeResult;

        do {
            System.out.println("1 - Communication test");
            System.out.println("2 - Authenticate");
            System.out.println("0 - End of session request");
            System.out.println("\nOption - ");
            try {
                input = in.readLine();
                choice = Integer.parseInt(input);
            } catch(NumberFormatException ex) {
                choice = -1;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            switch (choice) {
                case 1:
                    codeResult = theController.sendCommunicationTest();

                    handleACK(codeResult,
                            "Communication test with success!\n",
                            "Problem with communication test!\n");

                    break;
                case 2:
                    LoginUI loginUI = new LoginUI(theController);
                    loginUI.doShow();
                    break;
                case 0:
                    codeResult = theController.sendEndOfSession();

                    if(codeResult == MessageCodes.ACK){
                        System.out.println("End of session request with success!\n");
                        choice = -1;
                    } else {
                        System.out.println("Problem with end of session request!\n");
                    }

                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

        } while(choice != -1);

        sock.close();
    }
}