// Copyright (C) 2011, SNDA
// 
// Android log interface header
// 
// @author: tangyaguang@snda.com
// 
// @date: 2011-08-14
// 

#ifndef _7ZA_LOG_H_
#define _7ZA_LOG_H_

#include <android/log.h>
#include <stdio.h>

#define LOG_V(...) __android_log_print(ANDROID_LOG_VERBOSE, "lib7za", __VA_ARGS__)
#define LOG_D(...)                                                      \
    do {                                                                \
        char buf[1024];                                                 \
        snprintf(buf, 1024, __VA_ARGS__);                               \
        __android_log_print(ANDROID_LOG_DEBUG  , "lib7za", "[%s:%d] %s", __FILE__, __LINE__, buf); \
    } while (0)

#define LOG_I(...) __android_log_print(ANDROID_LOG_INFO   , "lib7za", __VA_ARGS__)
#define LOG_W(...) __android_log_print(ANDROID_LOG_WARN   , "lib7za", __VA_ARGS__)
#define LOG_E(...)                                                      \
    do {                                                                \
        char buf[1024];                                                 \
        snprintf(buf, 1024, __VA_ARGS__);                               \
        __android_log_print(ANDROID_LOG_ERROR, "lib7za", "[%s:%d] %s", __FILE__, __LINE__, buf); \
    } while (0)

#endif // _7ZA_LOG_H_
