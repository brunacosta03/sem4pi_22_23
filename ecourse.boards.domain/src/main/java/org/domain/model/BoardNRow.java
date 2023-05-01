package org.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import org.ecourse.Application;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The type Acronym.
 */
@Embeddable
public class BoardNRow implements ValueObject {
    /**
     * Board Title of Entity.
     */
    @Column(name = "BOARD_N_ROW")
    private final Integer value;

    /**
     * The constant MAX_LENGTH.
     */
    private static final Integer MAX_ROWS = Integer.valueOf(
            Application.settings().maxRows());


    /**
     * The constant MIN_LENGTH.
     */
    private static final Integer MIN_ROWS = 1;

    /**
     * Instantiates a new BoardNRow.
     */
    protected BoardNRow() {
        this.value = null;
    }

    private BoardNRow(final String valuep) {
        Preconditions.nonEmpty(
                valuep,
                "The number of Rows should neither be null nor empty"
        );
        Preconditions.nonNull(
                valuep,
                "The number of Rows should neither be null nor empty"
        );

        int nRows = Integer.parseInt(valuep);

        Preconditions.ensure(
                nRows >= MIN_ROWS && nRows <= MAX_ROWS,
                "The number of Rows should have between 1 and "
                        + MAX_ROWS
        );

        this.value = nRows;
    }

    /**
     * Factory method to create a BoardNRow instance.
     * @param valuep The value of the board number of rows.
     * @return BoardNRow instance.
     */
    public static BoardNRow of(final String valuep) {
        return new BoardNRow(valuep);
    }

    /**
     * Value int.
     * @return the int
     */
    public int value() {
        return value;
    }
}
