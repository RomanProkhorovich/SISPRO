import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println(ForAnalizer.getForIterCount("""
                do {
                             System.out.println("fuck asm");
                         }
                         while (true);
                """));


        System.out.println(ForAnalizer.getForIterCount("""
                for (int i = 0; i < 10; i--) {
                            System.out.println("fuck c++");
                        }
                """));
    }
}