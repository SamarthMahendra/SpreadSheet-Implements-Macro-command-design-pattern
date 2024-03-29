package spreadsheet;

/**
 * Represents a spreadsheet that can execute a macro.
 */
public interface MacroSpreadSheet extends SpreadSheet {

  /**
   * Executes the given macro on the current spreadsheet.
   *
   * @param macro the macro to be executed
   */
  void executeMacro(SpreadSheetMacro macro);

}
