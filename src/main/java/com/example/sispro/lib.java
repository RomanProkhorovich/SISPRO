package com.example.sispro;


import com.example.sispro.Exceptions.ChocolateException;
import com.example.sispro.Exceptions.TooMuchIterations;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class lib {

    public  void main1(String[] args) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String pathToJar = "D:\\FilesF\\target\\FilesF-0.0.1.jar";
        JarFile jarFile = new JarFile(pathToJar);
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
            Class c = cl.loadClass(className);
            if (c.getName().equals("com.example.main")){
                var n=c.newInstance();
                var b=c.getMethod("main", String[].class);
                var a = b.invoke(n, (Object) new String[0]);
            }
            System.out.println(c.getName());
        }

    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String analPath = "D:\\univer\\SISPRO\\TestModule\\Anal.jar";


            URL[] analUrls = {new URL("jar:file:" + analPath + "!/")};
            URLClassLoader anaalCl = URLClassLoader.newInstance(analUrls);
            Class cass = anaalCl.loadClass("CountAnalizer");
            var iterCount =(Integer) (cass.getMethod("getIterCount", String.class).invoke(null,
                 """
                 do {
                              System.out.println("fuck asm");
                          }
                          while (true);
                """));

            if (iterCount==1_000_000)
                throw new TooMuchIterations();

    }

    public static long analyze(String str)  throws TooMuchIterations {
        String analPath = "D:\\univer\\SISPRO\\TestModule\\Anal.jar";
        int iterCount;
        try {
            URL[] analUrls = {new URL("jar:file:" + analPath + "!/")};
            URLClassLoader anaalCl = URLClassLoader.newInstance(analUrls);
            Class cass = anaalCl.loadClass("CountAnalizer");
            iterCount =(Integer) (cass.getMethod("getIterCount", String.class).invoke(null, str));
                 /*"""
                 do {
                              System.out.println("fuck asm");
                          }
                          while (true);
                """);*/

            if (iterCount==1_000_000)
                throw new TooMuchIterations();
            return iterCount;
        } catch (MalformedURLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
    public static int div(int a,int b){
        String divPath = "D:\\univer\\SISPRO\\TestModule\\div.jar";
        if (b==0){
            throw new ArithmeticException();
        }
        try {
            URL[] urls = {new URL("jar:file:" + divPath + "!/")};
            URLClassLoader fuckingAsmClassLoaderYobEgoMat = URLClassLoader.newInstance(urls);
            Class c = fuckingAsmClassLoaderYobEgoMat.loadClass("Division");
            return (int)c.getMethod("getDiv", int.class, int.class).invoke(null, a, b);
        }
        catch (Exception e){
            throw new ChocolateException();
        }
    }
    public static int byteOr(int a,int b){
        String divPath = "D:\\univer\\SISPRO\\TestModule\\div.jar";
        try {
            URL[] urls = {new URL("jar:file:" + divPath + "!/")};
            URLClassLoader fuckingAsmClassLoaderYobEgoMat = URLClassLoader.newInstance(urls);
            Class c = fuckingAsmClassLoaderYobEgoMat.loadClass("Division");
            return (int)c.getMethod("byteOr", int.class, int.class).invoke(null, a, b);
        }
        catch (Exception e){
            throw new ChocolateException();
        }
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

