import jdk.jshell.JShell;
import jdk.jshell.SourceCodeAnalysis;

import javax.tools.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ForAnalizer {
    public static int getForIterCount(String str)  {
        try(JShell jShell=JShell.builder().out(new PrintStream(new FileOutputStream("Analizer/src/Files/count.txt"))).build()) {
            StringBuilder sb = new StringBuilder(str);
            int index = sb.indexOf("{");
            sb.insert(index + 1, "\ncount++;");
            sb.insert(0, "{\nint count=0;\n");
            int last = sb.lastIndexOf("}");
            sb.insert(last, "if(count==1000000) {break;}");
            sb.insert(sb.length(), "\nSystem.out.println(\"count=\"+count);\n}");
            System.out.println(sb);
            jShell.eval(sb.toString());
            BufferedReader bf=new BufferedReader(new FileReader("Analizer/src/Files/count.txt"));
            var a=bf.lines().filter(st->st.contains("count=")).findAny().orElse("Error");
            return Integer.parseInt(a.substring(6));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
