#include <string.h>
#include "7za.h"
#include "com_snda_Andro7z_Andro7za.h"

#define  ARGC 4
static const char *test_args[ARGC + 1] =
		{ "7za", "x", "-o/mnt/sdcard/extractarchiveandroid",
				"/mnt/sdcard/7za123456789.7z", 0 };

JNIEXPORT jint JNICALL Java_com_snda_Andro7z_Andro7za_a7za_1print_1usage(
		JNIEnv *env, jobject obj, jstring _pathArchive,
		jstring _pathToExtract) {
	const char *pathArchive = env->GetStringUTFChars(_pathArchive, 0);
	const char *pathToExtract = env->GetStringUTFChars(_pathToExtract, 0);

	test_args[2] = pathToExtract;
	test_args[3] = pathArchive;

	// process
	jint ret = andro7za_main(ARGC, test_args);

	// Release strings
    env->ReleaseStringUTFChars(_pathArchive, pathArchive);
    env->ReleaseStringUTFChars(_pathToExtract, pathToExtract);

	return ret;
}
