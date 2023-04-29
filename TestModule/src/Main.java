import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.SecureClassLoader;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {
    public static void main(String[] args) throws Exception {
       /* String divPath = "D:\\univer\\SISPRO\\TestModule\\div.jar";
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
*/

        String pathToJar = "D:\\univer\\SISPRO\\TestModule\\FilesF-0.0.1-SNAPSHOT.jar";
      /*  JarFile jarFile = new JarFile(pathToJar);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = {new URL("jar:file:" + pathToJar + "!/")};
        URLClassLoader cl = URLClassLoader.newInstance(urls);

        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if (je.isDirectory() || !je.getName().endsWith(".class")) {
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0, je.getName().length() - 6);
            className = className.replace('/', '.');
            System.out.println(className);
            Class c = cl.loadClass(className);
        }*/
        ClassLoader cl= ClassLoader.getSystemClassLoader();
        var classes =getClassesFromJarFile(new File(pathToJar));


    }

    public static Set<String> getClassNamesFromJarFile(File givenFile) throws IOException {
        Set<String> classNames = new HashSet<>();
        try (JarFile jarFile = new JarFile(givenFile)) {
            Enumeration<JarEntry> e = jarFile.entries();
            while (e.hasMoreElements()) {
                JarEntry jarEntry = e.nextElement();
                if (jarEntry.getName().endsWith(".class")) {
                    String className = jarEntry.getName()
                            .replace("/", ".")
                            .replace(".class", "");
                    classNames.add(className);
                }
            }
            return classNames;
        }
    }
    public static Set<Class> getClassesFromJarFile(File jarFile) throws IOException, ClassNotFoundException {
        Set<String> classNames = getClassNamesFromJarFile(jarFile);
        Set<Class> classes = new HashSet<>(classNames.size());

        try (URLClassLoader cl = URLClassLoader.newInstance(
                new URL[] { new URL("jar:file:" + jarFile + "!/") })) {
            for (String name : classNames) {
                System.out.println(name);
                Class clazz = cl.loadClass(name); // Load the class by its name
                classes.add(clazz);
            }
        }
        return classes;
    }
}