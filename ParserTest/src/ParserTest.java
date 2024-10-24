import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class ParserTest {

    @Test
    public void testValidExpressions() {

        List<String> expectedValid = new ArrayList<>();
        expectedValid.add("(2+1)*2");
        expectedValid.add("(2)");
        expectedValid.add("3+(4*5)-2");
        expectedValid.add("((10/(5+5))*(6-4))");
        expectedValid.add("(7+8*(9-3))/(2+1)");
        expectedValid.add("(2+(3*(4-2)))");
        expectedValid.add("(10/(5+(1*2)))");
        expectedValid.add("((2+3)*(4/2))");
        expectedValid.add("((6-2)*(8+3))/(4+1)");
        expectedValid.add("(10*(2+3))/5");
        expectedValid.add("(15/(3+2))*(4-2)");
        expectedValid.add("(9-(4+5))/2");
        expectedValid.add("(7*(8-3))+4");
        expectedValid.add("(6/(2+1))*(9-4)");
        expectedValid.add("((2*3)+(4-2))");
        expectedValid.add("(12+(5*2))/7");
        expectedValid.add("(10-(3+2))*(4+1)");
        expectedValid.add("((4+6)*(8/2))/(5-1)");
        expectedValid.add("(7-(2+3))*(9+1)");
        expectedValid.add("-2");
        expectedValid.add("-(2)");
        expectedValid.add("-(1+2)");
        expectedValid.add("-2+1");
        expectedValid.add("-2-(2)");
        expectedValid.add("122 + 1");
        expectedValid.add("122 + 1 ");
        expectedValid.add(" 122 + 1");
        expectedValid.add("122 + (1 ) ");
        expectedValid.add("122 + ( 1)");
        expectedValid.add("122 + ( 1 )");
        expectedValid.add("122 + (   1  )");
        expectedValid.add("122 + (   1)");
        expectedValid.add("   122 + ( 1)");
        expectedValid.add("122 +\n1");
        expectedValid.add("122 + (   \n 1  )");
        expectedValid.add("-2\n-(2\n)");
        expectedValid.add(" 122 + (//комментарий\n1)");
        expectedValid.add("122 + (1 ) ");
        expectedValid.add("(7-\n(2+3))*\n(9+1)");
        expectedValid.add("((2*3)+(4\n-2))");
        expectedValid.add("12+((5\n*2))/7");
        expectedValid.add("(10\n-(3+2))*(4+1)");
        expectedValid.add("-2\n+3");
        expectedValid.add("-2-(2\n+3)");
        expectedValid.add("122\n+1");
        expectedValid.add("122 + (1\n)");
        expectedValid.add("2 + 2 // simple addition");
        expectedValid.add("/* comment */ 2 + 2");
        expectedValid.add("2 + /* comment */ 2");
        expectedValid.add("2 + 2 /* comment */");
        expectedValid.add("(2 + 2) /* sum of two numbers */ * 3");
        expectedValid.add("/* multi-line \n comment */ 2 + 3");


        List<String> actualValid = new ArrayList<>();
        List<String> failedValid = new ArrayList<>();
        for (String expr : expectedValid) {
            Parser parser = new Parser(expr);
            if (parser.parseExpr()) {
                actualValid.add(expr);
            } else {
                failedValid.add(expr);
            }
        }


        if (!failedValid.isEmpty()) {
            System.out.println("Ошибочные валидные выражения: " + failedValid);
        }

        assertEquals("Expected valid expressions to match parsed results", expectedValid, actualValid);
    }

    @Test
    public void testInvalidExpressions() {
        List<String> expectedInvalid = new ArrayList<>();
        expectedInvalid.add("((");
        expectedInvalid.add("(2)+");
        expectedInvalid.add("2+)+3");
        expectedInvalid.add("(5*(6-3)");
        expectedInvalid.add("((4/2))*(3+)");
        expectedInvalid.add("(3+4)*5)-2");
        expectedInvalid.add("(2*(3+(4-2))");
        expectedInvalid.add("(10/(5+2)*(6-4");
        expectedInvalid.add("7+8*(9-3)/2+1");
        expectedInvalid.add("(3/0))");
        expectedInvalid.add("((6-2)*(8+))/5");
        expectedInvalid.add("((10*(2+3))/5");
        expectedInvalid.add("((15/(3+2)*(4-2)");
        expectedInvalid.add("(9-(4+5)/2");
        expectedInvalid.add("7*(8-3))+");
        expectedInvalid.add("(6/(2+1)*(9-");
        expectedInvalid.add("(2*3)+(4-)");
        expectedInvalid.add("12+((5*2))/7");
        expectedInvalid.add("10-(3+2)*(4+");
        expectedInvalid.add("(7-(2+3)*(9+");

        List<String> actualInvalid = new ArrayList<>();
        List<String> failedInvalid = new ArrayList<>();
        for (String expr : expectedInvalid) {
            Parser parser = new Parser(expr);
            if (!parser.parseExpr()) {
                actualInvalid.add(expr);
            } else {
                failedInvalid.add(expr);
            }
        }


        if (!failedInvalid.isEmpty()) {
            System.out.println("Ошибочные невалидные выражения: " + failedInvalid);
        }

        assertEquals("Expected invalid expressions to match parsing failures", expectedInvalid, actualInvalid);
    }
}