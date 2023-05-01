package org.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The type Acronym.
 */
@Embeddable
public class BoardCol implements ValueObject {
    /**
     * Board Column position.
     */
    @Column(name = "BOARD_COL")
    private final Integer value;

    /**
     * The constant MIN_COLUMNS.
     */
    private static final Integer MIN_COLUMNS = 1;

    /**
     * Instantiates a new BoardCol.
     */
    protected BoardCol() {
        this.value = null;
    }

    private BoardCol(final String valuep,
                     final BoardNCol boardNColp) {
        Preconditions.nonEmpty(
                valuep,
                "Column position should neither be null nor empty"
        );
        Preconditions.nonNull(
                valuep,
                "Column position should neither be null nor empty"
        );

        int nColPos = Integer.parseInt(valuep);
        int MAX_COLUMNS = boardNColp.value();

        Preconditions.ensure(
                nColPos >= MIN_COLUMNS && nColPos <= MAX_COLUMNS,
                "Column position should have between 1 and "
                        + MAX_COLUMNS
        );

        this.value = nColPos;
    }

    /**
     * Factory method to create a BoardCol instance.
     * @param valuep The value of the board column position.
     * @return BoardCol instance.
     */
    public static BoardCol of(final String valuep,
                              final BoardNCol boardNColp) {
        return new BoardCol(valuep, boardNColp);
    }

    /**
     * Value int.
     * @return the int
     */
    public int value() {
        return value;
    }
}
