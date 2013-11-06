/**
 * Copyright (C) 2011, SNDA
 * JNI interface class
 * @author: tangyaguang@snda.com
 * @date: 2011-08-14
 */

package com.snda.Andro7z;
import android.util.Log;

/**
 * <code>Andro7za</code> provided the 7za JNI interface.
 *
 * @author <a href="mailto:tangyaguang@snda.com"></a>
 * @version 1.0
 */
public final class Andro7za {
    private static String JNI_TAG = "7zaJNI";
    
    public native int a7za_print_usage();
    
    /**
     * <code>printUsage</code> print 7za usage.
     *
     */
    public void printUsage()
    {
        Log.d(JNI_TAG, "Call a7za_print_usage()");
        int ret = a7za_print_usage();
        Log.d(JNI_TAG, "a7za_print_usage() return code " + ret);
    }
    
    static {
        // Dynamically load stl_port, see jni/Application.mk
        // System.loadLibrary("stlport_shared");
        System.loadLibrary("7za");
    }
 }
