import jdk.jshell.JShell;

import java.io.*;


/**
 * Класс для подсчета количества итераций цикла
 */
public class CountAnalizer {

    /**
     * @param str цикл для анализа
     * @return количество итераций
     * Для подсчета используется рантайм комплиция(Jshell)
     */
    public static int getIterCount(String str) {
        try (JShell jShell = JShell.builder().out(new PrintStream(new FileOutputStream("Analizer/src/Files/count.txt"))).build()) {
            StringBuilder sb = new StringBuilder(str);
            int index = sb.indexOf("{");
            sb.insert(index + 1, "\ncount++;");
            sb.insert(0, "{\nint count=0;\n");
            int last = sb.lastIndexOf("}");
            sb.insert(last, "if(count==1000000) {break;}");
            sb.insert(sb.length(), "\nSystem.out.println(\"count=\"+count);\n}");
            jShell.eval(sb.toString());
            BufferedReader bf = new BufferedReader(new FileReader("Analizer/src/Files/count.txt"));
            var a = bf.lines().filter(st -> st.contains("count=")).findAny().orElse("Error");
            return Integer.parseInt(a.substring(6));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
