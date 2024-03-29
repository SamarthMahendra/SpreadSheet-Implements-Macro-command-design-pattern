package spreadsheet;

/**
 * Represents a spreadsheet that can execute a macro.
 */
public class EnhancedSpreadSheet extends SparseSpreadSheet implements MacroSpreadSheet {

  /**
   * Constructs a new EnhancedSpreadSheet object.
   */
  public EnhancedSpreadSheet() {
    super();
  }

  /**
   * Executes the given macro on the current spreadsheet.
   *
   * @param macro the macro to be executed
   */
  @Override
  public void executeMacro(SpreadSheetMacro macro) {
    macro.execute(this);

  }
}
