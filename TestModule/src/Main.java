import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public static void main(String[] args) throws Exception {
        String divPath = "D:\\univer\\SISPRO\\TestModule\\div.jar";
        URL[] urls = {new URL("jar:file:" + divPath + "!/")};
        URLClassLoader fuckingAsmClassLoaderYobEgoMat = URLClassLoader.newInstance(urls);
        Class c = fuckingAsmClassLoaderYobEgoMat.loadClass("Division");
        System.out.println(c.getMethod("getDiv", int.class, int.class).invoke(null, 10, 2));


        String analPath = "D:\\univer\\SISPRO\\TestModule\\Anal.jar";
        URL[] analUrls = {new URL("jar:file:" + analPath + "!/")};
        URLClassLoader anaalCl = URLClassLoader.newInstance(analUrls);
        Class cass = anaalCl.loadClass("CountAnalizer");
        System.out.println(cass.getMethod("getIterCount", String.class).invoke(null, """
                 do {
                              System.out.println("fuck asm");
                          }
                          while (true);
                """));
    }
}