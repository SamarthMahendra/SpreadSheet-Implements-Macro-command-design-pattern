import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import spreadsheet.EnhancedSpreadSheet;
import spreadsheet.MacroSpreadSheet;
import spreadsheet.SpreadSheet;
import spreadsheet.SparseSpreadSheet;
import spreadsheet.SpreadSheetControllerEnhanced;
import spreadsheet.SpreadSheetMacro;
import spreadsheet.BulkAssignMacro;
import spreadsheet.AverageMacro;
import spreadsheet.RangeMacro;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This class is the tester for a sparse spreadsheet.
 */
public class SparseSpreadSheetTest {

  private SpreadSheet sheet;

  /**
   * Sets up the spreadsheet for testing.
   */
  @Before
  public void setup() {
    sheet = new SparseSpreadSheet();
  }

  /**
   * Tests the get and set methods of the spreadsheet.
   */
  @Test
  public void testGetSet() {
    Random r = new Random(100);
    double[][] expectedSet = new double[100][100];
    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        double num = r.nextDouble();
        expectedSet[i][j] = num;
        assertTrue(sheet.isEmpty(i, j));
        assertEquals(0.0, sheet.get(i, j), 0.001);
        sheet.set(i, j, num);
        assertFalse(sheet.isEmpty(i, j));
      }
    }

    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        assertEquals(expectedSet[i][j], sheet.get(i, j), 0.01);
      }
    }
  }

  /**
   * Tests the getHeight and getWidth methods of the spreadsheet.
   */
  @Test
  public void testGetWidthHeight() {
    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        sheet.set(i, j, 0);
        assertEquals((i + 1), sheet.getHeight());
        if (i == 0) {
          assertEquals((j + 1), sheet.getWidth());
        } else {
          assertEquals(100, sheet.getWidth());
        }
      }
    }

    sheet.set(1000, 1000, 0);
    assertEquals(1001, sheet.getWidth());
    assertEquals(1001, sheet.getHeight());
  }

  /**
   * Tests the set neg row.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetWithNegativeRow() {
    sheet.set(0, 0, 1);
    sheet.set(0, 1, 9);
    sheet.get(-1, 0);
  }

  /**
   * Tests the get neg col.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetWithNegativeColumn() {
    sheet.set(0, 0, 1);
    sheet.set(0, 1, 9);
    sheet.get(0, -1);
  }

  /**
   * Tests the BulkAssignMacro.
   */
  @Test
  public void testBulkAssignMacro() {
    SpreadSheetMacro macro = new BulkAssignMacro(0, 0, 1, 1, 5);
    EnhancedSpreadSheet enhancedSheet = new EnhancedSpreadSheet();
    enhancedSheet.executeMacro(macro);
    assertEquals(5, enhancedSheet.get(0, 0), 0.01);
    assertEquals(5, enhancedSheet.get(1, 1), 0.01);
    assertEquals(5, enhancedSheet.get(0, 0), 0.01);
    assertEquals(5, enhancedSheet.get(1, 1), 0.01);
  }

  /**
   * Tests the AverageMacro.
   */
  @Test
  public void testAverageMacro() {
    SpreadSheetMacro macro = new AverageMacro(0, 0, 1, 1, 2, 2);
    EnhancedSpreadSheet enhancedSheet = new EnhancedSpreadSheet();
    enhancedSheet.set(0, 0, 1);
    enhancedSheet.set(0, 1, 2);
    enhancedSheet.set(1, 0, 3);
    enhancedSheet.set(1, 1, 4);
    enhancedSheet.executeMacro(macro);
    assertEquals(2.5, enhancedSheet.get(2, 2), 0.01);
  }

  /**
   * Tests the RangeMacro.
   */
  @Test
  public void testRangeMacro() {
    SpreadSheetMacro macro = new RangeMacro(0, 0, 1, 1, 1, 1);
    EnhancedSpreadSheet enhancedSheet = new EnhancedSpreadSheet();
    enhancedSheet.executeMacro(macro);
    assertEquals(1, enhancedSheet.get(0, 0), 0.01);
    assertEquals(2, enhancedSheet.get(0, 1), 0.01);
    assertEquals(3, enhancedSheet.get(1, 0), 0.01);
    assertEquals(4, enhancedSheet.get(1, 1), 0.01);
  }

  /**
   * Tests the BulkAssignMacro.
   */
  @Test
  public void testBulkSet() {
    Appendable appendable = new StringBuilder();
    MacroSpreadSheet sheet = new EnhancedSpreadSheet();
    SpreadSheetControllerEnhanced controller = new SpreadSheetControllerEnhanced(sheet,
        new StringReader("bulk-assign-value \nA\n1\nB\n4\n100\nquit"), appendable);
    controller.processCommand(sheet);
    assertEquals(100.0, sheet.get(1, 1), 0.0);
  }

  /**
   * Invalid start row.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartRow() {
    Appendable appendable = new StringBuilder();
    MacroSpreadSheet sheet = new EnhancedSpreadSheet();
    SpreadSheetControllerEnhanced controller = new SpreadSheetControllerEnhanced(sheet,
        new StringReader("bulk-assign-value \nA\n-1\nB\n4\n100\nquit"), appendable);
    controller.processCommand(sheet);
  }

  /**
   * invalid end row.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEndRow() {
    Appendable appendable = new StringBuilder();
    MacroSpreadSheet sheet = new EnhancedSpreadSheet();
    SpreadSheetControllerEnhanced controller = new SpreadSheetControllerEnhanced(sheet,
        new StringReader("bulk-assign-value \nA\n1\nB\n-4\n100\nquit"), appendable);
    controller.processCommand(sheet);
  }

  /**
   * end row less than start row.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEndRowLessThanStartRow() {
    Appendable appendable = new StringBuilder();
    MacroSpreadSheet sheet = new EnhancedSpreadSheet();
    SpreadSheetControllerEnhanced controller = new SpreadSheetControllerEnhanced(sheet,
        new StringReader("bulk-assign-value \nB\n4\nB\n1\n100\nquit"), appendable);
    controller.processCommand(sheet);
  }

  /**
   * Tests the AverageMacro.
   */
  @Test
  public void testAverage() {
    Appendable appendable = new StringBuilder();
    MacroSpreadSheet enhancedSheet = new EnhancedSpreadSheet();
    enhancedSheet.set(0, 0, 1);
    enhancedSheet.set(0, 1, 2);
    enhancedSheet.set(1, 0, 3);
    enhancedSheet.set(1, 1, 4);
    SpreadSheetControllerEnhanced controller = new SpreadSheetControllerEnhanced(sheet,
        new StringReader("average \nA\n0\nB\n1\nC\n2\nquit"), appendable);
    controller.processCommand(enhancedSheet);
    assertEquals(2.5, enhancedSheet.get(2, 2), 0.01);
  }

  /**
   * Invalid start row.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartRowAverage() {
    Appendable appendable = new StringBuilder();
    MacroSpreadSheet sheet = new EnhancedSpreadSheet();
    SpreadSheetControllerEnhanced controller = new SpreadSheetControllerEnhanced(sheet,
        new StringReader("average \nA\n-1\nB\n1\nC\n2\nquit"), appendable);
    controller.processCommand(sheet);
  }

  /**
   * invalid end row.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEndRowAverage() {
    Appendable appendable = new StringBuilder();
    MacroSpreadSheet sheet = new EnhancedSpreadSheet();
    SpreadSheetControllerEnhanced controller = new SpreadSheetControllerEnhanced(sheet,
        new StringReader("average \nA\n1\nB\n-1\nC\n2\nquit"), appendable);
    controller.processCommand(sheet);
  }

  /**
   * end row less than start row.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEndRowLessThanStartRowAverage() {
    Appendable appendable = new StringBuilder();
    MacroSpreadSheet sheet = new EnhancedSpreadSheet();
    SpreadSheetControllerEnhanced controller = new SpreadSheetControllerEnhanced(sheet,
        new StringReader("average \nB\n4\nB\n1\nC\n2\nquit"), appendable);
    controller.processCommand(sheet);
  }

  /**
   * Tests the RangeMacro.
   */
  @Test
  public void testRangeAssign() {
    Appendable appendable = new StringBuilder();
    MacroSpreadSheet enhancedSheet = new EnhancedSpreadSheet();
    SpreadSheetControllerEnhanced controller = new SpreadSheetControllerEnhanced(sheet,
        new StringReader("range-assign \nA\n0\nA\n1\n1\n1\nquit"), appendable);
    controller.processCommand(enhancedSheet);
    assertEquals(1, enhancedSheet.get(0, 0), 0.01);
    assertEquals(2, enhancedSheet.get(0, 1), 0.01);
  }

  /**
   * Invalid start row.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartRowRangeAssign() {
    Appendable appendable = new StringBuilder();
    MacroSpreadSheet sheet = new EnhancedSpreadSheet();
    SpreadSheetControllerEnhanced controller = new SpreadSheetControllerEnhanced(sheet,
        new StringReader("range-assign \nA\n-1\nA\n1\n1\n1\nquit"), appendable);
    controller.processCommand(sheet);
  }

  /**
   * invalid end row.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEndRowRangeAssign() {
    Appendable appendable = new StringBuilder();
    MacroSpreadSheet sheet = new EnhancedSpreadSheet();
    SpreadSheetControllerEnhanced controller = new SpreadSheetControllerEnhanced(sheet,
        new StringReader("range-assign \nA\n1\nA\n-1\n1\n1\nquit"), appendable);
    controller.processCommand(sheet);
  }

  /**
   * end row less than start row.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEndRowLessThanStartRowRangeAssign() {
    Appendable appendable = new StringBuilder();
    MacroSpreadSheet sheet = new EnhancedSpreadSheet();
    SpreadSheetControllerEnhanced controller = new SpreadSheetControllerEnhanced(sheet,
        new StringReader("range-assign \nB\n4\nB\n1\n1\n1\nquit"), appendable);
    controller.processCommand(sheet);
  }


}