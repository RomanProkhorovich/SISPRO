public class Division {
    static
    {
        System.load("D:\\univer\\SISPRO\\LowLevel\\src\\c_asm_jni.dll");
    }
    public static native int getDiv(int arg1, int arg2);
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
