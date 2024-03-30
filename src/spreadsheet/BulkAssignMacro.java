package spreadsheet;

/**
 * Represents a macro that assigns a value to a region of cells in a spreadsheet.
 */
public class BulkAssignMacro implements SpreadSheetMacro {


  private double value;

  private int fromRow;
  private int fromCol;
  private int toRow;
  private int toCol;


  /**
   * Constructs a new BulkAssignMacro object that takes spreadsheet and start and end cells.
   *
   * @param fromRow starting row
   * @param fromCol starting column
   * @param toRow   ending row
   * @param toCol   ending column
   */
  public BulkAssignMacro(int fromRow, int fromCol, int toRow, int toCol, double value)
      throws IllegalArgumentException {
    // check for all valid cells
    if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    // check for valid range
    if (fromRow > toRow || fromCol > toCol) {
      throw new IllegalArgumentException("Invalid range");
    }
    this.fromRow = fromRow;
    this.fromCol = fromCol;
    this.toRow = toRow;
    this.toCol = toCol;

    this.value = value;

  }


  /**
   * Takes a object of SpreadSheetMacro and assigns the values of the cells of the current
   * spreadsheet to the destination cell.
   *
   * @param sheet the spreadsheet
   */
  @Override
  public void execute(SpreadSheet sheet) {
    for (int i = fromRow; i <= toRow; i++) {
      for (int j = fromCol; j <= toCol; j++) {
        sheet.set(i, j, value);
      }
    }
  }
}
