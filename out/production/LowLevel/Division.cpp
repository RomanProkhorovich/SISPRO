#include "Division.h"
//#include "pch.h"
#include <jni.h>

extern "C" int __fastcall asm_div(int, int);
extern "C" int __fastcall asm_mask(int, int);

JNIEXPORT jint JNICALL Java_Division_getDiv
(JNIEnv* env, jobject obj, jint arg1 , jint arg2) {
    return asm_div(arg1,arg2);
}

JNIEXPORT jint JNICALL Java_Division_ByteOr
(JNIEnv* env, jobject obj, jint arg1, jint arg2) {
	return asm_mask(arg1,arg2);
}
