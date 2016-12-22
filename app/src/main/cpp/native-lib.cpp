#include <jni.h>
#include <string>

extern "C"
jstring
Java_com_wangliang161220_ant_activity_MainActivity_stringFromJNI(
        JNIEnv *env, jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
