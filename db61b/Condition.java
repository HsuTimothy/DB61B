package db61b;

import java.util.List;

/** Represents a single 'where' condition in a 'select' command.
 *  @author Tim Hsu */
class Condition {

    /** Internally, we represent our relation as a 3-bit value whose
     *  bits denote whether the relation allows the left value to be
     *  greater than the right (GT), equal to it (EQ),
     *  or less than it (LT). */
    private static final int GT = 1, EQ = 2, LT = 4;

    /** A Condition representing COL1 RELATION COL2, where COL1 and COL2
     *  are column designators. and RELATION is one of the
     *  strings "<", ">", "<=", ">=", "=", or "!=". */
    Condition(Column col1, String relation, Column col2) {
        _relation = relation;
        column1 = col1;
        column2 = col2;
    }

    /** A Condition representing COL1 RELATION 'VAL2', where COL1 is
     *  a column designator, VAL2 is a literal value (without the
     *  quotes), and RELATION is one of the strings "<", ">", "<=",
     *  ">=", "=", or "!=".
     */
    Condition(Column col1, String relation, String val2) {
        this(col1, relation, new Literal(val2));
    }

    /** Assuming that ROWS are rows from the respective tables from which
     *  my columns are selected, returns the result of performing the test I
     *  denote. */
    boolean test() {
        int compare = column1.value().compareTo(column2.value());
        switch (_relation) {
        case "<":
            return compare < 0;
        case ">":
            return compare > 0;
        case "=":
            return compare == 0;
        case ">=":
            return compare >= 0;
        case "<=":
            return compare <= 0;
        case "!=":
            return !(compare == 0);
        default:
            return false;
        }
    }

    /** Return true iff all CONDITIONS are satified. */
    static boolean test(List<Condition> conditions) {
        if (conditions == null) {
            return true;
        }
        for (Condition condition : conditions) {
            if (!condition.test()) {
                return false;
            }
        }
        return true;
    }

    /** My column 1. */
    private Column column1;
    /** My column 2. */
    private Column column2;
    /** My relation. */
    private String _relation;
}
