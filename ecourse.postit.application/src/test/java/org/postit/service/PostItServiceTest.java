package org.postit.service;

import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.domain.model.*;
import org.domain.model.postit.*;
import org.domain.repositories.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.ECoursePasswordPolicy;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.model.UserBuilder;
import repositories.PostItRepository;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PostItServiceTest {
    ECoursePasswordPolicy passwordPolicy = new ECoursePasswordPolicy();
    private PostItService postItService;
    @Mock
    private PostItRepository postItRepository;

    @Mock
    private BoardRepository boardRepository;

    private final String BOARD_TITLE = "Test Board";
    private final String BOARD_N_ROW = "3";
    private final String BOARD_N_COL = "4";

    private static final String STRING_SHORTNAME = "shortName";
    private static final String STRING_FULLNAME = "fullName";
    private static final String STRING_PASSWORD = "Correct5";
    private static final String STRING_EMAIL = "email@email.com";

    private static final String POST_IT_CONTENT = "Test Post-it";
    private static final String POST_IT_ROW_COL = "2";
    private static final String BOARD_ID = "123";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postItService = new PostItService(postItRepository, boardRepository);
    }

    @Test
    void testCreatePostItSuccessful() {
        User postItOwner = managerUser();
        Board board = createBoard();

        board.addPermission(createBoardPermission(postItOwner));
        when(boardRepository.ofIdentity(123L)).thenReturn(Optional.of(board));
        when(postItRepository.positByPosition(POST_IT_ROW_COL, POST_IT_ROW_COL, board)).thenReturn(null);
        when(postItRepository.save(any(PostIt.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PostIt createdPostIt = postItService.createPostIt(POST_IT_CONTENT, POST_IT_ROW_COL, POST_IT_ROW_COL, postItOwner, BOARD_ID);

        assertNotNull(createdPostIt);
        assertEquals(POST_IT_CONTENT, createdPostIt.content().value());
        assertEquals(PostItRow.of(POST_IT_ROW_COL, board.boardNRow()), createdPostIt.rowPos());
        assertEquals(PostItColumn.of(POST_IT_ROW_COL, board.boardNCol()), createdPostIt.columnPos());
        assertEquals(postItOwner, createdPostIt.owner());
        assertEquals(board, createdPostIt.board());
        assertEquals(PostItStateType.CREATED, createdPostIt.state());
        verify(postItRepository, times(1)).save(any(PostIt.class));
    }

    @Test
    void testCreatePostWithRowPosOutsideShouldThrowError() {
        User postItOwner = managerUser();
        Board board = createBoard();

        board.addPermission(createBoardPermission(postItOwner));
        when(boardRepository.ofIdentity(123L)).thenReturn(Optional.of(board));
        when(postItRepository.positByPosition(POST_IT_ROW_COL, POST_IT_ROW_COL, board)).thenReturn(null);
        when(postItRepository.save(any(PostIt.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertThrows(IllegalArgumentException.class, () -> {
            postItService.createPostIt(
                    POST_IT_CONTENT,
                    "10",
                    POST_IT_ROW_COL,
                    postItOwner,
                    BOARD_ID);
        });
    }

    @Test
    void testCreatePostWithColumnPosOutsideShouldThrowError() {
        User postItOwner = managerUser();
        Board board = createBoard();

        board.addPermission(createBoardPermission(postItOwner));
        when(boardRepository.ofIdentity(123L)).thenReturn(Optional.of(board));
        when(postItRepository.positByPosition(POST_IT_ROW_COL, POST_IT_ROW_COL, board)).thenReturn(null);
        when(postItRepository.save(any(PostIt.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertThrows(IllegalArgumentException.class, () -> {
            postItService.createPostIt(
                    POST_IT_CONTENT,
                    POST_IT_ROW_COL,
                    "10",
                    postItOwner,
                    BOARD_ID);
        });
    }

    @Test
    void testCreatePostWithInvalidOwnerShouldThrowError() {
        Board board = createBoard();

        board.addPermission(createBoardPermission(managerUser()));
        when(boardRepository.ofIdentity(123L)).thenReturn(Optional.of(board));

        assertThrows(IllegalArgumentException.class, () -> {
            postItService.createPostIt(
                    POST_IT_CONTENT,
                    POST_IT_ROW_COL,
                    POST_IT_ROW_COL,
                    null,
                    BOARD_ID);
        });
    }

    @Test
    void testCreatePostWithInvalidBoardShouldThrowError() {
        User postItOwner = managerUser();

        when(boardRepository.ofIdentity(123L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            postItService.createPostIt(
                    POST_IT_CONTENT,
                    POST_IT_ROW_COL,
                    POST_IT_ROW_COL,
                    postItOwner,
                    BOARD_ID);
        });
    }

    @Test
    void testCheckIfIsRowEntryTitleWithInvalidEntryTitle() {
        User postItOwner = managerUser();
        Board board = createBoard();

        board.addPermission(createBoardPermission(postItOwner));
        when(boardRepository.ofIdentity(123L)).thenReturn(Optional.of(board));
        when(postItRepository.save(any(PostIt.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertThrows(IllegalArgumentException.class, () -> {
            postItService.createPostIt(
                    POST_IT_CONTENT,
                    "This Board Row doesn't exist",
                    POST_IT_ROW_COL,
                    postItOwner,
                    BOARD_ID);
        });
    }

    @Test
    void testCheckIfIsColumnEntryTitleWithInvalidEntryTitle() {
        User postItOwner = managerUser();
        Board board = createBoard();

        board.addPermission(createBoardPermission(postItOwner));
        when(boardRepository.ofIdentity(123L)).thenReturn(Optional.of(board));
        when(postItRepository.save(any(PostIt.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertThrows(IllegalArgumentException.class, () -> {
            postItService.createPostIt(
                    POST_IT_CONTENT,
                    POST_IT_ROW_COL,
                    "This Board Column doesn't exist",
                    postItOwner,
                    BOARD_ID);
        });
    }

    @Test
    void testChangePostItContentSuccessful() {
        User postItOwner = managerUser();
        Board board = createBoard();
        PostItFactory postItFactory = new PostItFactory();

        board.addPermission(createBoardPermission(postItOwner));
        when(boardRepository.ofIdentity(123L)).thenReturn(Optional.of(board));

        PostIt existingPostIt = postItFactory.create(
                POST_IT_CONTENT,
                POST_IT_ROW_COL,
                POST_IT_ROW_COL,
                postItOwner,
                board,
                PostItStateType.CREATED
        );

        when(postItRepository.positByPosition(POST_IT_ROW_COL, POST_IT_ROW_COL, board)).thenReturn(existingPostIt);
        when(postItRepository.save(any(PostIt.class))).thenAnswer(invocation -> invocation.getArgument(0));

        String updatedContent = "Updated Post-it Content";
        PostIt updatedPostIt = postItService.changePostIt(updatedContent,
                POST_IT_ROW_COL, POST_IT_ROW_COL, BOARD_ID, postItOwner, PostItStateType.UPDATED);

        assertNotNull(updatedPostIt);
        assertEquals(updatedContent, updatedPostIt.content().value());
        assertEquals(PostItRow.of(POST_IT_ROW_COL, board.boardNRow()), updatedPostIt.rowPos());
        assertEquals(PostItColumn.of(POST_IT_ROW_COL, board.boardNCol()), updatedPostIt.columnPos());
        assertEquals(postItOwner, updatedPostIt.owner());
        assertEquals(board, updatedPostIt.board());
        assertEquals(PostItStateType.UPDATED, updatedPostIt.state());
        verify(postItRepository, times(1)).save(any(PostIt.class));
    }

    @Test
    void testDeletePostItSuccessful() {
        User postItOwner = managerUser();
        Board board = createBoard();
        PostItFactory postItFactory = new PostItFactory();

        board.addPermission(createBoardPermission(postItOwner));
        when(boardRepository.ofIdentity(123L)).thenReturn(Optional.of(board));

        PostIt existingPostIt = postItFactory.create(
                POST_IT_CONTENT,
                POST_IT_ROW_COL,
                POST_IT_ROW_COL,
                postItOwner,
                board,
                PostItStateType.CREATED
        );

        when(postItRepository.positByPosition(POST_IT_ROW_COL, POST_IT_ROW_COL, board)).thenReturn(existingPostIt);
        when(postItRepository.save(any(PostIt.class))).thenAnswer(invocation -> invocation.getArgument(0));

        String updatedContent = PostItStateType.DELETED.toString();
        PostIt updatedPostIt = postItService.changePostIt(updatedContent,
                POST_IT_ROW_COL, POST_IT_ROW_COL, BOARD_ID, postItOwner, PostItStateType.DELETED);

        assertNotNull(updatedPostIt);
        assertEquals(updatedContent, updatedPostIt.content().value());
        assertEquals(PostItRow.of(POST_IT_ROW_COL, board.boardNRow()), updatedPostIt.rowPos());
        assertEquals(PostItColumn.of(POST_IT_ROW_COL, board.boardNCol()), updatedPostIt.columnPos());
        assertEquals(postItOwner, updatedPostIt.owner());
        assertEquals(board, updatedPostIt.board());
        assertEquals(PostItStateType.DELETED, updatedPostIt.state());
        verify(postItRepository, times(1)).save(any(PostIt.class));
    }

    @Test
    void testchangePostItThrowsExceptionWhenPostItNotFound() {
        User postItOwner = managerUser();
        Board board = createBoard();

        board.addPermission(createBoardPermission(postItOwner));
        when(boardRepository.ofIdentity(123L)).thenReturn(Optional.of(board));
        when(postItRepository.positByPosition(POST_IT_ROW_COL, POST_IT_ROW_COL, board)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () ->
                postItService.changePostIt("Updated Post-it Content",
                        POST_IT_ROW_COL, POST_IT_ROW_COL, BOARD_ID, postItOwner, PostItStateType.UPDATED));

        verify(postItRepository, never()).save(any(PostIt.class));
    }

    @Test
    void testchangePostItThrowsExceptionWhenUserDoesNotHavePermission() {
        User postItOwner = managerUser();
        Board board = createBoard();
        PostItFactory postItFactory = new PostItFactory();

        when(boardRepository.ofIdentity(123L)).thenReturn(Optional.of(board));

        PostIt existingPostIt = postItFactory.create(
                POST_IT_CONTENT,
                POST_IT_ROW_COL,
                POST_IT_ROW_COL,
                postItOwner,
                board,
                PostItStateType.CREATED
        );

        when(postItRepository.positByPosition(POST_IT_ROW_COL, POST_IT_ROW_COL, board)).thenReturn(existingPostIt);

        assertThrows(IllegalArgumentException.class, () ->
                postItService.changePostIt("Updated Post-it Content",
                        POST_IT_ROW_COL, POST_IT_ROW_COL, BOARD_ID, postItOwner, PostItStateType.UPDATED));

        verify(postItRepository, never()).save(any(PostIt.class));
    }

    @Test
    void testchangePostItThrowsExceptionWhenUserDoesIsNotTheSame() {
        User postItOwner = managerUser();
        User updateOwner = anotherManagerUser();
        Board board = createBoard();
        PostItFactory postItFactory = new PostItFactory();

        when(boardRepository.ofIdentity(123L)).thenReturn(Optional.of(board));

        PostIt existingPostIt = postItFactory.create(
                POST_IT_CONTENT,
                POST_IT_ROW_COL,
                POST_IT_ROW_COL,
                postItOwner,
                board,
                PostItStateType.CREATED
        );

        when(postItRepository.positByPosition(POST_IT_ROW_COL, POST_IT_ROW_COL, board)).thenReturn(existingPostIt);

        assertThrows(IllegalArgumentException.class, () ->
                postItService.changePostIt("Updated Post-it Content",
                        POST_IT_ROW_COL, POST_IT_ROW_COL, BOARD_ID, updateOwner, PostItStateType.UPDATED));

        verify(postItRepository, never()).save(any(PostIt.class));
    }

    @Test
    public void testIsNumeric_NullInput_ReturnsFalse() {
        assertFalse(postItService.isNumeric(null));
    }

    @Test
    public void testIsNumeric_EmptyString_ReturnsFalse() {
        assertFalse(postItService.isNumeric(""));
    }

    @Test
    public void testIsNumeric_WhitespaceString_ReturnsFalse() {
        assertFalse(postItService.isNumeric("   "));
    }

    @Test
    public void testIsNumeric_NonNumericString_ReturnsFalse() {
        assertFalse(postItService.isNumeric("Hello"));
    }

    @Test
    public void testIsNumeric_NumericString_ReturnsTrue() {
        assertTrue(postItService.isNumeric("123"));
    }

    @Test
    public void testIsNumeric_DecimalString_ReturnsTrue() {
        assertTrue(postItService.isNumeric("3.14"));
    }

    @Test
    public void testIsNumeric_NegativeInteger_ReturnsTrue() {
        assertTrue(postItService.isNumeric("-42"));
    }

    @Test
    public void testIsNumeric_NegativeDecimal_ReturnsTrue() {
        assertTrue(postItService.isNumeric("-0.5"));
    }

    @Test
    public void testIsNumeric_NumericWithLeadingZeros_ReturnsTrue() {
        assertTrue(postItService.isNumeric("000123"));
    }

    @Test
    public void testIsNumeric_NumericWithTrailingZeros_ReturnsTrue() {
        assertTrue(postItService.isNumeric("123000"));
    }

    private User managerUser(){
        UserBuilder userBuilder = new UserBuilder(passwordPolicy, new PlainTextEncoder());

        return userBuilder.with(
                        STRING_SHORTNAME,
                        STRING_PASSWORD,
                        STRING_FULLNAME,
                        STRING_EMAIL,
                        CourseRoles.MANAGER)
                .build();
    }

    private User anotherManagerUser(){
        UserBuilder userBuilder = new UserBuilder(passwordPolicy, new PlainTextEncoder());

        return userBuilder.with(
                        STRING_SHORTNAME + " ANOTHER",
                        STRING_PASSWORD,
                        STRING_FULLNAME,
                        STRING_EMAIL,
                        CourseRoles.MANAGER)
                .build();
    }

    public Board createBoard(){
        BoardFactory factory = new BoardFactory();
        Board board = factory.create(
                BOARD_TITLE,
                BOARD_N_ROW,
                BOARD_N_COL,
                new ArrayList<>(),
                managerUser());

        return board;
    }

    public BoardPermission createBoardPermission(User user){
        BoardPermissionFactory factory = new BoardPermissionFactory();
        BoardPermission boardPermission = factory.create(user, AccessLevelType.WRITE);

        return boardPermission;
    }
}