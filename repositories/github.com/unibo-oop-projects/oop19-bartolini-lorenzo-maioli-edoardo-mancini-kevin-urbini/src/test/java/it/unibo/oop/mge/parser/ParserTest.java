package it.unibo.oop.mge.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ParserTest {
    private String str1 = "ln(pow(x,2)*4+1)";
    private String str2 = "x-1";
    private String str3 = "( 2 +ex p(x))+1";
    private String str4 = "(x+1)*4";
    private String str5 = "(1 +1)* 5";
    private String str6 = "( 3*(x+4) + 1*(2+3))";
    private String str7 = "3/(x+2)*((pow(2,x))))))))";
    private String str8 = "ln(pow(x,2)*4)+2)))))";
    private String str9 = "ln(2+pow(x,2)*4)))))";
    private String str10 = "-1+x";
    private String str11 = "(0.75)/exp(pow((x*5),2)*pow((y*5),2))";
    @Test
    public void blankDeleterTest() {
        assertEquals(new BlankDeleter(str3).toString(), "(2+exp(x))+1");
        assertEquals(new BlankDeleter(str5).toString(), "(1+1)*5");
        assertEquals(new BlankDeleter(str6).toString(), "(3*(x+4)+1*(2+3))");
    }

    @Test
    public void checkBracketsTest() {
        assertThrows(IllegalArgumentException.class, () -> new CheckBrackets(new BlankDeleter(str7)).toString());
        assertThrows(IllegalArgumentException.class, () -> new CheckBrackets(new BlankDeleter(str8)).toString());
        assertThrows(IllegalArgumentException.class, () -> new CheckBrackets(new BlankDeleter(str9)).toString());
    }

    private String testerComposer(final String str) {
        return new BracketsResolver(new CheckBrackets(new BlankDeleter(str))).toString();
    }

    @Test
    public void decoratorTest() {
        assertEquals(testerComposer(str1), "ln(sum(mul(pow(x,2),4),1))");
        assertEquals(testerComposer(str2), "sot(x,1)");
        assertEquals(testerComposer(str4), "mul(sum(x,1),4)");
        assertEquals(testerComposer(str10), "sum(sot(0,1),x)");
        assertEquals(testerComposer(str11), "div(0.75,exp(mul(pow(mul(x,5),2),pow(mul(y,5),2))))");
    }

}
