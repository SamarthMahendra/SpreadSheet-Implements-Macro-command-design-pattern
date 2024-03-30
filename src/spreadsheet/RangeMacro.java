package spreadsheet;
// range-assign from-row-num from-col-num to-row-num to-col-num start-value increment.
// This will set a row or column of cells to a range of values starting at the given value and
// advancing by the given increment. For example range-assign A 1 A 10 1 1
// will assign A1:A10 to values 1, 2, 3, ..., 10 respectively.

/**
 * This class represents the RangeMacro class that implements the SpreadSheetMacro interface. This
 * class represents the range macro that sets a row or column of cells to a range of values starting
 * at the given value and advancing by the given increment.
 */
public class RangeMacro implements SpreadSheetMacro {

  private int fromRow;
  private int fromCol;
  private int toRow;
  private int toCol;
  private double startValue;
  private double increment;

  /**
   * Constructs a new RangeMacro object that takes spreadsheet and start and end cells.
   *
   * @param fromRow    starting row
   * @param fromCol    starting column
   * @param toRow      ending row
   * @param toCol      ending column
   * @param startValue starting value
   * @param increment  increment value
   */
  public RangeMacro(int fromRow, int fromCol, int toRow, int toCol, double startValue,
      double increment) {
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
    this.startValue = startValue;
    this.increment = increment;
  }

  /**
   * Takes a object of SpreadSheetMacro and assigns the values of the cells of the current
   * spreadsheet to the destination cell.
   *
   * @param sheet the spreadsheet
   */
  public void execute(SpreadSheet sheet) {
    double value = this.startValue;
    for (int i = this.fromRow; i <= this.toRow; i++) {
      for (int j = this.fromCol; j <= this.toCol; j++) {
        sheet.set(i, j, value);
        value += this.increment;
      }
    }
  }

}
