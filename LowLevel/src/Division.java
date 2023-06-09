/**
 * Класс для выполнения низкоуровневого кода. Язык: asm
 */
public class Division {
    static
    {
        System.load("D:\\univer\\SISPRO\\LowLevel\\src\\c_asm_jni.dll");
    }

    /**
     * @param arg1 делимое
     * @param arg2 делитель
     * @return результат целочесленного деления
     */
    public static native int getDiv(int arg1, int arg2);


    /**
     * @param arg1 Первый аргумент
     * @param arg2 Второй аргумент
     * @return результат вычислений
     */
    public static native int ByteOr(int arg1, int arg2);

    /*
        To compile use x64 developer command prompt
        cd D:\\univer\\SISPRO\\LowLevel\\src
        D:
        ml64.exe /Fo./asm.obj /c Division.asm
        cl Division.cpp /c /I . /I "C:\Program Files\Java\jdk-18.0.2.1\include" /I "C:\Program Files\Java\jdk-18.0.2.1\include/win32" /Fo./c_jni.obj
        link /DLL /out:c_asm_jni.dll asm.obj c_jni.obj
     */
}
