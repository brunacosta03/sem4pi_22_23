package org.shared.board.server;

import org.authz.application.AuthenticationService;
import org.authz.application.AuthzRegistry;
import org.boards.controller.CreateBoardController;
import org.domain.model.BoardEntry;
import org.postit.controller.CreatePostItController;
import org.postit.controller.DeletePostItController;
import org.postit.controller.UndoPostItController;
import org.postit.controller.UpdatePostItController;
import org.shared.board.common.MessageCodes;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.model.UserSession;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * The type Shared board server service.
 */
public class SharedBoardServerService {
    /**
     * Get AuthenticationService.
     */
    private AuthenticationService authService = AuthzRegistry
            .authenticationService();

    /**
     * The Synchronizer.
     */
    Synchronizer synchronizer;

    /**
     * The constant MIN_ROWS_COLUMNS.
     */
    private static final String MIN_ROWS_COLS = "1";

    /**
     * Instantiates a new SharedBoardServerService.
     */
    public SharedBoardServerService() {
        this.synchronizer = Synchronizer.getInstance();
    }

    /**
     * Authenticate user.
     * @param userData the user data
     * @return the int
     * @throws IllegalArgumentException the illegal argument exception
     */
    public int authenticateUser(final String userData)
            throws IllegalArgumentException {
        String email = userData.substring(0, userData.indexOf("\0"));
        String password = userData.substring(
                userData.indexOf("\0") + 1, userData.length() - 1);

        Optional<UserSession> session = authService
                .authenticate(email, password,
                        CourseRoles.allRoles());

        if (session.isPresent()) {
            return MessageCodes.ACK;
        }

        return MessageCodes.ERR;
    }

    /**
     * Create board.
     * @param boardData the board data
     * @param authUser  the auth user
     * @return the int
     */
    public int createBoard(final String boardData,
                           final User authUser) {
        CreateBoardController boardController = new CreateBoardController();
        final String boardTitle = getStringByIndex(0, boardData);
        final String boardNCol = getStringByIndex(1, boardData);
        final String boardNRow = getStringByIndex(2, boardData);
        List<BoardEntry> allBoardEntrys = new ArrayList<>();

        int j = 3;

        for(int i = 1; i <= Integer.parseInt(boardNCol); i++){
            final String entryTitle = getStringByIndex(j, boardData);

            BoardEntry boardEntry = boardController.createBoardEntry(
                    String.valueOf(i),
                    MIN_ROWS_COLS,
                    String.valueOf(i),
                    entryTitle,
                    boardNRow,
                    boardNCol,
                    authUser
            );

            allBoardEntrys.add(boardEntry);
            j++;
        }

        for(int i = 2; i <= Integer.parseInt(boardNRow); i++){
            final String entryTitle = getStringByIndex(j, boardData);

            BoardEntry boardEntry = boardController.createBoardEntry(
                    String.valueOf(i),
                    String.valueOf(i),
                    MIN_ROWS_COLS,
                    entryTitle,
                    boardNRow,
                    boardNCol,
                    authUser
            );

            allBoardEntrys.add(boardEntry);
            j++;
        }

        boardController.createBoard(
                boardTitle,
                boardNRow,
                boardNCol,
                allBoardEntrys,
                authUser);

        return MessageCodes.ACK;
    }

    /**
     * Create post-it.
     * @param postItData the post-it data
     * @param authUser   the auth user
     * @return the int
     */
    public int createPostIt(final String postItData,
                            final User authUser) {
        CreatePostItController createPostItController = new CreatePostItController();
        final String content = getStringByIndex(0, postItData);
        final String rowPos = getStringByIndex(1, postItData);
        final String colPos = getStringByIndex(2, postItData);
        final String boardId = getStringByIndex(3, postItData);

        String lockKey = synchronizer.generateLockKey(rowPos, colPos, boardId);
        Object lock = synchronizer.getOrCreateLockObject(lockKey);

        synchronized (lock){
            try{
                createPostItController.createPostIt(
                        content,
                        rowPos,
                        colPos,
                        boardId,
                        authUser);
            } catch (NoSuchElementException e){
                throw new IllegalArgumentException(
                        "There is no board with that id!");
            }
        }

        return MessageCodes.ACK;
    }

    /**
     * Update post-it content.
     * @param postItData the post-it data
     * @param authUser   the auth user
     * @return the int
     */
    public int updatePostItContent(final String postItData,
                            final User authUser) {
        UpdatePostItController updatePostItController = new UpdatePostItController();
        final String content = getStringByIndex(0, postItData);
        final String rowPos = getStringByIndex(1, postItData);
        final String colPos = getStringByIndex(2, postItData);
        final String boardId = getStringByIndex(3, postItData);

        String lockKey = synchronizer.generateLockKey(rowPos, colPos, boardId);
        Object lock = synchronizer.getOrCreateLockObject(lockKey);

        synchronized (lock){
            try{
                updatePostItController.updatePostItContent(
                        content,
                        rowPos,
                        colPos,
                        boardId,
                        authUser);
            } catch (NoSuchElementException e){
                throw new IllegalArgumentException(
                        "There is no board with that id!");
            }
        }

        return MessageCodes.ACK;
    }

    /**
     * Update post-it position.
     * @param postItData the post-it data
     * @param authUser   the auth user
     * @return the int
     */
    public int updatePostItPosition(final String postItData,
                                    final User authUser) {
        UpdatePostItController updatePostItController = new UpdatePostItController();
        final String previousRowPos = getStringByIndex(0, postItData);
        final String previousColPos = getStringByIndex(1, postItData);
        final String newRowPos = getStringByIndex(2, postItData);
        final String newColPos = getStringByIndex(3, postItData);
        final String boardId = getStringByIndex(4, postItData);

        String lockKeyPrevious = synchronizer.generateLockKey(
                previousRowPos, previousColPos, boardId);
        Object previousLock = synchronizer.getOrCreateLockObject(lockKeyPrevious);

        String lockKeyNew = synchronizer.generateLockKey(
                newRowPos, newColPos, boardId);
        Object newLock = synchronizer.getOrCreateLockObject(lockKeyNew);

        synchronized (previousLock){
            synchronized (newLock){
                try{
                    updatePostItController.updatePostItPosition(
                            previousRowPos,
                            previousColPos,
                            newRowPos,
                            newColPos,
                            boardId,
                            authUser);
                } catch (NoSuchElementException e){
                    throw new IllegalArgumentException(
                            "There is no board with that id!");
                }
            }
        }

        return MessageCodes.ACK;
    }

    /**
     * Delete post-it.
     * @param postItData the post-it data
     * @param authUser   the auth user
     * @return the int
     */
    public int deletePostIt(final String postItData,
                                   final User authUser) {
        DeletePostItController deletePostItController = new DeletePostItController();
        final String rowPos = getStringByIndex(0, postItData);
        final String colPos = getStringByIndex(1, postItData);
        final String boardId = getStringByIndex(2, postItData);

        String lockKey = synchronizer.generateLockKey(rowPos, colPos, boardId);
        Object lock = synchronizer.getOrCreateLockObject(lockKey);

        synchronized (lock){
            try{
                deletePostItController.deletePostIt(
                        rowPos,
                        colPos,
                        boardId,
                        authUser);
            } catch (NoSuchElementException e){
                throw new IllegalArgumentException(
                        "There is no board with that id!");
            }
        }

        return MessageCodes.ACK;
    }

    /**
     * Undo post-it.
     * @param postItData the post-it data
     * @param user the auth user
     * @return the int
     */
    public int undoPostIt(String postItData, User user) {
        UndoPostItController ctrl = new UndoPostItController();

        final String boardId = getStringByIndex(0, postItData);
        final String rowPos = getStringByIndex(1, postItData);
        final String colPos = getStringByIndex(2, postItData);

        String lockKey = synchronizer.generateLockKey(rowPos, colPos, boardId);
        Object lock = synchronizer.getOrCreateLockObject(lockKey);

        synchronized (lock) {
            try{
                ctrl.undoPostIt(
                        rowPos,
                        colPos,
                        boardId,
                        user
                );
            } catch (NoSuchElementException e){
                throw new IllegalArgumentException(
                        "There is no board with that id!");
            }
        }

        return MessageCodes.ACK;
    }

    /**
     * All data is separated by \0.
     * So we want to split data to specific position.
     * @param index
     * @param input
     * @return
     */
    private static String getStringByIndex(int index, String input) {
        String[] substrings = input.split("\0");

        if (index < 0 || index >= substrings.length) {
            return "";
        }

        return substrings[index];
    }


}
