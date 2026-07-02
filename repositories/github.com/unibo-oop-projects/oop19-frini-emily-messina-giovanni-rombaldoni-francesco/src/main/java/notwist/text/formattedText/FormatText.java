package notwist.text.formattedText;

import java.io.IOException;
import java.io.Reader;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public class FormatText extends  HTMLEditorKit.ParserCallback {

	StringBuffer s;

	 public void parse(Reader in) throws IOException {
	   s = new StringBuffer();
	   ParserDelegator delegator = new ParserDelegator();
	   // the third parameter is TRUE to ignore charset directive
	   delegator.parse(in, this, Boolean.TRUE);
	 }

	 public String getText() {
		   return s.toString();
		 }
}
