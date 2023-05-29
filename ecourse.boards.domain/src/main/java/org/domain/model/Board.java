package org.domain.model;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.time.util.CurrentTimeCalendars;
import org.usermanagement.domain.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "T_SHARED_BOARD")
public class Board
        implements AggregateRoot<Long>,
        Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Version of board.
     */
    @Version
    private Long version;

    /**
     * Board Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    /**
     * Board title.
     */
    private BoardTitle boardTitle;
    /**
     * Board number of columns.
     */
    private BoardNCol boardNCol;
    /**
     * Board number of rows.
     */
    private BoardNRow boardNRow;
    /**
     * Active or Archive board.
     */
    private boolean boardState;
    /**
     * Date when user created board.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdOn;

    /**
     * Board Owner.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_email")
    private User boardOwner;

    /**
     * List of board permissions.
     */
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<BoardPermission> boardPermissions;

    /**
     * List of board entry.
     */
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BoardEntry> boardEntrys;

    protected Board() {

    }

    Board(final BoardTitle boardTitlep,
          final BoardNRow boardNRowp,
          final BoardNCol boardNColp,
          final List<BoardEntry> allBoardEntrys,
          final User boardOwnerp) {
        this.boardTitle = boardTitlep;
        this.boardNRow = boardNRowp;
        this.boardNCol = boardNColp;
        this.boardEntrys = allBoardEntrys;
        this.boardOwner = boardOwnerp;
        this.createdOn = CurrentTimeCalendars.now();
        this.boardState = true;
        this.boardPermissions = new ArrayList<>();
    }

    /**
     * Get board title.
     * @return BoardTitle
     */
    public BoardTitle boardTitle() {
        return boardTitle;
    }

    /**
     * Get board number of columns.
     * @return BoardNCol
     */
    public BoardNCol boardNCol() {
        return boardNCol;
    }

    /**
     * Get board number of rows.
     * @return BoardNCol
     */
    public BoardNRow boardNRow() {
        return boardNRow;
    }

    /**
     * Get board owner of rows.
     * @return User
     */
    public User boardOwner() {
        return boardOwner;
    }

    /**
     * Add Permissions to board.
     * @param boardPermissionp board permission.
     */
    public void addPermission(final BoardPermission boardPermissionp) {
        this.boardPermissions.add(boardPermissionp);
    }

    /**
     * Check if user has specific permission in board
     * @param user user that should have permission
     * @param accessLevel typer of permission
     * @return true/false
     */
    public boolean userHasPermission(User user, AccessLevel accessLevel){
        for (BoardPermission boardPermission : this.boardPermissions) {
                if(boardPermission.userWithPermission().sameAs(user)
                    && boardPermission.accessLevel().equals(accessLevel)){
                    return true;
                }
        }

        return false;
    }

    /**
     * Find row of entry Title
     * @return String
     */
    public String findRowByEntryTitle(String rowTitle){
        EntryTitle findEntryTitle = EntryTitle.of(rowTitle);

        for (BoardEntry boardEntry : this.boardEntrys) {
            if(boardEntry.entryTitle().equals(findEntryTitle)){
                return boardEntry.boardRow().toString();
            }
        }

        return null;
    }

    /**
     * Find column of entry Title
     * @return String
     */
    public String findColumnByEntryTitle(String colTitle){
        EntryTitle findEntryTitle = EntryTitle.of(colTitle);

        for (BoardEntry boardEntry : this.boardEntrys) {
            if(boardEntry.entryTitle().equals(findEntryTitle)){
                return boardEntry.boardCol().toString();
            }
        }

        return null;
    }

    /**
     * Check if some Board is the same object then other.
     * @param other
     * @return true/false
     */
    @Override
    public boolean sameAs(final Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Board)) {
            return false;
        }

        Board that = (Board) other;

        return Objects.equals(boardId, that.boardId)
                && Objects.equals(boardTitle, that.boardTitle)
                && Objects.equals(boardNRow, that.boardNRow)
                && Objects.equals(boardNCol, that.boardNCol)
                && Objects.equals(boardState, that.boardState)
                && Objects.equals(boardOwner, that.boardOwner);
    }

    /**
     * Get boardId.
     * @return boardId
     */
    @Override
    public Long identity() {
        return boardId;
    }
}
