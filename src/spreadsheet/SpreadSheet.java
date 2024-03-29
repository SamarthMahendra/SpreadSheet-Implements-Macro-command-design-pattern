package spreadsheet;

/**
 * This interface represents all the operations to be offered by a spreadsheet. These operations are
 * supposed to be a barebones set upon which other operations may be developed.
 */
public interface SpreadSheet {

  /**
   * Get the number at the specified cell.
   *
   * @param row the row number of the cell, starting with 0
   * @param col the column number of the cell, starting with 0
   * @return the number at the specified cell, as a double. If the cell is empty, it returns a 0
   * @throws IllegalArgumentException if the row or column are negative
   */
  double get(int row, int col) throws IllegalArgumentException;

  /**
   * Set the value of the specified cell to the specified value.
   *
   * @param row   the row number of the cell, starting with 0
   * @param col   the column number of the cell, starting at 0
   * @param value the value that this cell must be set to
   * @throws IllegalArgumentException if the row or column are negative
   */
  void set(int row, int col, double value) throws IllegalArgumentException;


  /**
   * Returns whether the specified cell is empty.
   *
   * @param row the row number of the cell, starting with 0
   * @param col the column number of the cell, starting with 0
   * @return true if the cell is empty, false otherwise
   * @throws IllegalArgumentException if the row or column are negative
   */
  boolean isEmpty(int row, int col) throws IllegalArgumentException;

  /**
   * Return the width of this spreadsheet. The width is defined by the cell with the highest column
   * number that is not empty.
   *
   * @return the width of this spreadsheet
   */
  int getWidth();

  /**
   * Return the height of this spreadsheet. The height is defined by the cell with the highest row
   * number that is not empty.
   *
   * @return the height of this spreadsheet
   */
  int getHeight();
}
