package spreadsheet;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents a sparse spreadsheet. A sparse spreadsheet is a spreadsheet with a large
 * number of empty cells. It represents this efficiently using a hash map.
 */
public class SparseSpreadSheet implements SpreadSheet {

  private final Map<CellPosition, Double> sheet;
  private int width;
  private int height;

  /**
   * Create an empty spreadsheet.
   */
  public SparseSpreadSheet() {
    this.sheet = new HashMap<CellPosition, Double>();
    this.width = 0;
    this.height = 0;
  }

  /**
   * Returns the value of the cell at the specified row and column.
   *
   * @param row the row number of the cell, starting with 0
   * @param col the column number of the cell, starting with 0
   * @return the value of the cell at the specified row and column
   * @throws IllegalArgumentException if the row or column are negative
   */
  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    return this.sheet.getOrDefault(new CellPosition(row, col), Double.valueOf(0.0));
  }

  /**
   * Sets the value of the cell at the specified row and column to the specified value.
   *
   * @param row   the row number of the cell, starting with 0
   * @param col   the column number of the cell, starting at 0
   * @param value the value that this cell must be set to
   * @throws IllegalArgumentException if the row or column are negative
   */
  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    this.sheet.put(new CellPosition(row, col), Double.valueOf(value));
    if ((row + 1) > height) {
      height = row + 1;
    }

    if ((col + 1) > width) {
      width = col + 1;
    }
  }

  /**
   * Returns whether the specified cell is empty.
   *
   * @param row the row number of the cell, starting with 0
   * @param col the column number of the cell, starting with 0
   * @return true if the cell is empty, false otherwise
   * @throws IllegalArgumentException if the row or column are negative
   */
  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    return !this.sheet.containsKey(new CellPosition(row, col));
  }

  /**
   * Return the width of this spreadsheet. The width is defined by the cell with the highest column
   *
   * @return the width of this spreadsheet
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Return the height of this spreadsheet. The height is defined by the cell with the highest row
   *
   * @return the height of this spreadsheet
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * This class represents the position of a cell in a spreadsheet.
   */
  public static class CellPosition {

    private final int row;
    private final int column;

    /**
     * Create a new cell position.
     *
     * @param row    the row number of the cell
     * @param column the column number of the cell
     */
    private CellPosition(int row, int column) {
      this.row = row;
      this.column = column;
    }

    /**
     * Check if this cell position is equal to another object.
     *
     * @param o the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CellPosition)) {
        return false;
      }
      CellPosition other = (CellPosition) o;
      return this.row == other.row && this.column == other.column;
    }

    /**
     * Get the hash code of this cell position.
     *
     * @return the hash code of this cell position
     */
    @Override
    public int hashCode() {
      return Objects.hash(this.row, this.column);
    }
  }
}
