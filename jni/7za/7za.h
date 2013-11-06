// Copyright (C) 2011, SNDA
// 
// JNI interface header
// 
// @author: tangyaguang@snda.com
// 
// @date: 2011-08-14

#ifndef _7ZA_H_
#define _7ZA_H_

extern int andro7za_main(int numArgs, const char *argv[]);

#ifdef __cplusplus
extern "C"{
#endif

    // Port 7zip main entry.
    int run_7za(int argc , const char *argv[]);
    
#ifdef __cplusplus
}
#endif

#endif  // _7ZA_H_
