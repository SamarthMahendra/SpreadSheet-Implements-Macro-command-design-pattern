package spreadsheet;

import java.util.Scanner;

/**
 * Represents a controller for a spreadsheet program.
 */
public class SpreadSheetControllerEnhanced extends SpreadSheetController {

  /**
   * Create a controller to work with the specified sheet (model), readable (to take inputs) and
   * appendable (to transmit output).
   *
   * @param sheet      the sheet to work with (the model)
   * @param readable   the Readable object for inputs
   * @param appendable the Appendable objects to transmit any output
   */
  public SpreadSheetControllerEnhanced(SpreadSheet sheet, Readable readable,
      Appendable appendable) {
    super(sheet, readable, appendable);
  }

  /**
   * Override the processCommand method. This method should check if the command given to it is
   * "bulk-assign-value". If so, then it should get the additional arguments, and use the macro
   * object to execute this operation (what would you have to do to make sure this works?).
   * Otherwise, it should delegate to the inherited processCommand method.
   */
  @Override
  public void processCommand(Object sheet) {
    // Cast the sheet to a MacroSpreadSheet object
    MacroSpreadSheet macroSheet = (MacroSpreadSheet) sheet;
    Scanner sc = new Scanner(this.readable);
    String userInstruction = sc.next();

    // Common variables
    int startrow = getRowNum(sc.next()); //get the row string
    int startcol = sc.nextInt(); //get the column number, starting with 1
    int endrow = getRowNum(sc.next()); //get the row string
    int endcol = sc.nextInt(); //get the column number, starting with 1

    // Declare macro variable outside the switch statement
    SpreadSheetMacro macro = null;

    switch (userInstruction) {
      case "bulk-assign-value":
        double value = sc.nextDouble(); //get the value to be assigned

        // Instantiate a BulkAssignMacro object with the provided parameters
        macro = new BulkAssignMacro(startrow, startcol, endrow, endcol, value);
        break;

      case "average":
        int destrow = getRowNum(sc.next()); //get the row string
        int destcol = sc.nextInt(); //get the column number, starting with 1

        // Instantiate an AverageMacro object with the provided parameters
        macro = new AverageMacro(startrow, startcol, endrow, endcol, destrow, destcol);
        break;

      case "range-assign":
        double startvalue = sc.nextDouble(); //get the value to be assigned
        double increment = sc.nextDouble(); //get the value to be assigned

        // Instantiate a RangeMacro object with the provided parameters
        macro = new RangeMacro(startrow, startcol, endrow, endcol, startvalue, increment);
        break;

      default:
        // Call the superclass method processCommand with the provided parameters
        super.processCommand(sheet);
        break;
    }

    // Execute the macro if it is not null
    if (macro != null) {
      macroSheet.executeMacro(macro);
    }
  }

  /**
   * Override the printMenu method so that it includes this new command.
   */
  @Override
  protected void printMenu() {
    super.printMenu();
    writeMessage("bulk-assign-value: Assigns a value to a range of cells\n");
    writeMessage("q or quit (quit the program) " + System.lineSeparator());
  }
}
