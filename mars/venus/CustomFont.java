package mars.venus;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class CustomFont {
	
    private static Font ttfBase = null;
    private static Font telegraficoFont = null;
    private static InputStream myStream = null;
    private static final String FONT_PATH_MONACO = "monaco/monaco.TTF";

    public static Font CustomF() {


            try {
                myStream = new BufferedInputStream(
                        new FileInputStream(FONT_PATH_MONACO));
                ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
                telegraficoFont = ttfBase.deriveFont(Font.PLAIN, 12);               
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("Font not loaded.");
            }
            return telegraficoFont;
    }

}
