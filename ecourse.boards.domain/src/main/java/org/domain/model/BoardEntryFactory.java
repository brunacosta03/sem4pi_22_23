package org.domain.model;

public class BoardEntryFactory {

    public BoardEntryFactory(){

    }
    public BoardEntry create(final String entryNumberp,
                             final String boardRowp,
                             final String boardColp,
                             final String entryTitlep,
                             final String boardNRowp,
                             final String boardNColp) {
        BoardNRow boardNRow = BoardNRow.of(boardNRowp);
        BoardNCol boardNCol = BoardNCol.of(boardNColp);

        BoardRow boardRow = BoardRow.of(boardRowp, boardNRow);
        BoardCol boardCol = BoardCol.of(boardColp, boardNCol);


        return new BoardEntry(
                EntryNumber.of(entryNumberp, boardRow, boardCol, boardNRow, boardNCol),
                boardRow,
                boardCol,
                EntryTitle.of(entryTitlep)
        );
    }
}
