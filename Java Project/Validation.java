
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 public class Validation{
    
    public static boolean validateLetters(String txt) {

    String regx = "[a-zA-Z]";
    Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(txt);
    return matcher.find();


	}
}
