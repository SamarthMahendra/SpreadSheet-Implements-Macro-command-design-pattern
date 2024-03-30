package spreadsheet;
//average from-row-num from-col-num to-row-num to-col-num dest-row-num dest-col-num.
// This will compute the average of a range of cells and put it in the specified destination cell.
// For example average A 1 B 10 C 2 computes the average of the 20 values in A1:B10 and
// stores it in the cell C2.

/**
 * This class represents the AverageMacro class that implements the SpreadSheetMacro interface. This
 * class represents the average macro that computes the average of a range of cells and puts it in
 * the specified destination cell.
 */
public class AverageMacro implements SpreadSheetMacro {

  private final int fromRow;
  private final int fromCol;
  private final int toRow;
  private final int toCol;
  private final int destRow;
  private final int destCol;

  /**
   * Constructs a new AverageMacro object that takes spreadsheet and start and end cells.
   *
   * @param fromRow starting row
   * @param fromCol starting column
   * @param toRow   ending row
   * @param toCol   ending column
   * @param destRow destination row
   * @param destCol destination column
   */
  public AverageMacro(int fromRow, int fromCol, int toRow, int toCol, int destRow, int destCol) {
    // check for all valid cells
    if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0 || destRow < 0 || destCol < 0) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    // check for valid range
    if (fromRow > toRow || fromCol > toCol) {
      throw new IllegalArgumentException("Invalid range");
    }
    // check for valid destination cell
    if (destRow < fromRow || destRow > toRow || destCol < fromCol || destCol > toCol) {
      throw new IllegalArgumentException("Invalid destination cell");
    }
    this.fromRow = fromRow;
    this.fromCol = fromCol;
    this.toRow = toRow;
    this.toCol = toCol;
    this.destRow = destRow;
    this.destCol = destCol;
  }


  /**
   * Takes a object of SpreadSheetMacro and assigns the values of the cells of the current
   * spreadsheet to the destination cell.
   *
   * @param sheet the spreadsheet
   */
  @Override
  public void execute(SpreadSheet sheet) {
    // get the sum of the cells
    double sum = 0;
    int count = 0;
    for (int i = this.fromRow; i <= this.toRow; i++) {
      for (int j = this.fromCol; j <= this.toCol; j++) {
        sum += sheet.get(i, j);
        count++;
      }
    }
    // get the average
    double average = sum / count;
    // set the value of the destination cell to the average
    sheet.set(this.destRow, this.destCol, average);

  }
}
