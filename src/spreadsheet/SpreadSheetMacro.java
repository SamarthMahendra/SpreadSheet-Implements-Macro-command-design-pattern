package spreadsheet;

/**
 * Represents a macro that can be executed on a spreadsheet.
 */
public interface SpreadSheetMacro {

  /**
   * Executes the macro on the given spreadsheet.
   *
   * @param sheet the spreadsheet
   */
  void execute(SpreadSheet sheet);

}
