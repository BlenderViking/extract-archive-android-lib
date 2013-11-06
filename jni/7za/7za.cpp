#include "7za.h"
#include "com_snda_Andro7z_Andro7za.h"

#define  ARGC 4
static const char *test_args[ARGC + 1] = {
		"7za",
		"x",
		"-o/mnt/sdcard/extractarchiveandroid",
		"/mnt/sdcard/abcdefghijqlmnopqrstuvwxyz123456789.7z",
		0 };

JNIEXPORT jint JNICALL Java_com_snda_Andro7z_Andro7za_a7za_1print_1usage(
		JNIEnv *, jobject) {
	jint ret = andro7za_main(ARGC, test_args);
	return ret;
}
