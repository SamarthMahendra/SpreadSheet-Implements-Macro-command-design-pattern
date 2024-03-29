package spreadsheet;

import java.io.InputStreamReader;

/**
 * The driver of this application.
 */
public class SpreadSheetProgram {

  /**
   * main method of the program.
   *
   * @param args any command line arguments
   */
  public static void main(String[] args) {
    SpreadSheet model = new SparseSpreadSheet();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    SpreadSheetController controller = new SpreadSheetController(model, rd, ap);
    controller.control();
  }
}
