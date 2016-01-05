/** Tests the methods in Table.
* @author Tim Hsu
*/
package db61b;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static db61b.Utils.*;
import static org.junit.Assert.*;

public class TableTest {

    @Test
    public void testRow() {
        String[] words = new String[]{"Tim", "is", "cool"};
        Row aRow = new Row(words);
        assertEquals(3, aRow.size());
        assertEquals("cool", aRow.get(2));
    }

    @Test
    public void tableIteratorTest() {
        String[] colTitles = new String[]{"Name", "Surname", "Grade"};
        String[] a = new String[]{"Tim", "Hsu", "A-"};
        String[] b = new String[]{"Amy", "Zou", "A"};
        String[] c = new String[]{"Andy", "Hsu", "B+"};
        String[] d = new String[]{"Felix", "Su", "A+"};
        Row aRow = new Row(a);
        Row bRow = new Row(b);
        Row cRow = new Row(c);
        Row dRow = new Row(d);

        Table t1 = new Table("students", colTitles);
        t1.add(aRow);
        t1.add(bRow);
        t1.add(cRow);
        t1.add(dRow);
        TableIterator tItr = new TableIterator(t1);
        assertEquals(dRow.get(0), tItr.next().get(0));
        assertEquals(aRow.get(0), tItr.next().get(0));
        assertEquals(cRow.get(0), tItr.next().get(0));
        assertEquals(bRow.get(0), tItr.next().get(0));
        tItr.reset();
        assertEquals(dRow.get(0), tItr.next().get(0));
    }

    @Test
    public void conditiontestTest() {
        String name = "Table";
        String[] colTitles = new String[]{"Name", "Year", "Major", "Age"};
        String[] a = new String[]{"Tim", "1", "CS", "19"};
        String[] b = new String[]{"Amy", "2", "Haas", "19"};
        String[] c = new String[]{"Andy", "3", "Econ", "21"};
        String[] d = new String[]{"Felix", "4", "EECS", "18"};
        Table table = new Table(name, colTitles);
        Row aRow = new Row(a);
        Row bRow = new Row(b);
        Row cRow = new Row(c);
        Row dRow = new Row(d);

        table.add(aRow);
        table.add(bRow);
        table.add(cRow);
        table.add(dRow);

        Column col1 = new Column(table, "Name");
        Column col2 = new Column(table, "Year");
        List<TableIterator> tblItr = new ArrayList<TableIterator>();
        tblItr.add(new TableIterator(table));
        col1.resolve(tblItr);
        col2.resolve(tblItr);
        Condition cond1 = new Condition(col1, "=", "Tim");
        assertTrue(cond1.test());
        Condition cond2 = new Condition(col2, "=", "1");
        assertTrue(cond2.test());
        Condition cond3 = new Condition(col2, ">", "1");
        assertFalse(cond3.test());
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(TableTest.class));
    }
}
