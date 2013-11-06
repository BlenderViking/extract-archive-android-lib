# Copyright (C) 2011, SNDA
# ndk make configuration for 7za
# Author: tangyaguang@snda.com

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

# Module name
LOCAL_MODULE    := 7za

# Local defines copy from '/Alone'
MY_LOCAL_DEFINES := -DBREAK_HANDLER -DUNICODE -D_UNICODE -DENV_NDK

# Local defines copy from 'makefile.linux_cross_arm
MY_MACHINE_DEFINES := -D_FILE_OFFSET_BITS=64 -D_LARGEFILE_SOURCE\
					 -DNDEBUG -D_REENTRANT -DENV_UNIX

MY_CPP_FLAGS := -fexceptions

# Compose CXXFLAGS
MY_CFLAGS := $(MY_LOCAL_DEFINES) $(MY_MACHINE_DEFINES)

# C src dir
MY_C_DIR := C
# CPP src dir
MY_CPP_DIR := CPP

# 7zip src dir
MY_7ZIP_DIR := $(MY_CPP_DIR)/7zip

# 7zip ui src dir
MY_7ZIP_UI_DIR := $(MY_7ZIP_DIR)/UI

MY_7ZIP_UI_CONSOLE_DIR = $(MY_7ZIP_UI_DIR)/Console
# CONSOLE_OBJS
MY_CONSOLE_SRC := $(MY_7ZIP_UI_CONSOLE_DIR)/BenchCon.cpp \
                 $(MY_7ZIP_UI_CONSOLE_DIR)/ConsoleClose.cpp \
                 $(MY_7ZIP_UI_CONSOLE_DIR)/ExtractCallbackConsole.cpp \
                 $(MY_7ZIP_UI_CONSOLE_DIR)/List.cpp \
                 $(MY_7ZIP_UI_CONSOLE_DIR)/Main.cpp \
                 $(MY_7ZIP_UI_CONSOLE_DIR)/MainAr.cpp \
                 $(MY_7ZIP_UI_CONSOLE_DIR)/OpenCallbackConsole.cpp \
                 $(MY_7ZIP_UI_CONSOLE_DIR)/PercentPrinter.cpp \
                 $(MY_7ZIP_UI_CONSOLE_DIR)/UpdateCallbackConsole.cpp \
                 $(MY_7ZIP_UI_CONSOLE_DIR)/UserInputUtils.cpp

# common src dir
MY_COMMON_SRC_DIR := $(MY_CPP_DIR)/Common
# COMMON_OBJS
MY_COMMON_SRC := $(MY_COMMON_SRC_DIR)/CommandLineParser.cpp \
                $(MY_COMMON_SRC_DIR)/CRC.cpp \
                $(MY_COMMON_SRC_DIR)/IntToString.cpp \
                $(MY_COMMON_SRC_DIR)/ListFileUtils.cpp \
                $(MY_COMMON_SRC_DIR)/StdInStream.cpp \
                $(MY_COMMON_SRC_DIR)/StdOutStream.cpp \
                $(MY_COMMON_SRC_DIR)/MyString.cpp \
                $(MY_COMMON_SRC_DIR)/StringConvert.cpp \
                $(MY_COMMON_SRC_DIR)/StringToInt.cpp \
                $(MY_COMMON_SRC_DIR)/UTFConvert.cpp \
                $(MY_COMMON_SRC_DIR)/MyWindows.cpp \
                $(MY_COMMON_SRC_DIR)/MyVector.cpp \
                $(MY_COMMON_SRC_DIR)/Wildcard.cpp

MY_WIN_SRC_DIR := $(MY_CPP_DIR)/Windows
# WIN_OBJS
MY_WIN_SRC := $(MY_WIN_SRC_DIR)/Error.cpp \
             $(MY_WIN_SRC_DIR)/FileDir.cpp \
             $(MY_WIN_SRC_DIR)/FileFind.cpp \
             $(MY_WIN_SRC_DIR)/FileIO.cpp \
             $(MY_WIN_SRC_DIR)/FileName.cpp \
             $(MY_WIN_SRC_DIR)/PropVariant.cpp \
             $(MY_WIN_SRC_DIR)/PropVariantConversions.cpp \
             $(MY_WIN_SRC_DIR)/Synchronization.cpp \
             $(MY_WIN_SRC_DIR)/System.cpp \
             $(MY_WIN_SRC_DIR)/Time.cpp

MY_7ZIP_COMMON_SRC_DIR := $(MY_7ZIP_DIR)/Common
# 7ZIP_COMMON_OBJS
MY_7ZIP_COMMON_SRC := $(MY_7ZIP_COMMON_SRC_DIR)/CreateCoder.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/CWrappers.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/FilePathAutoRename.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/FileStreams.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/FilterCoder.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/InBuffer.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/InOutTempBuffer.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/LimitedStreams.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/LockedStream.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/MemBlocks.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/MethodId.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/MethodProps.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/OffsetStream.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/OutBuffer.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/OutMemStream.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/ProgressMt.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/ProgressUtils.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/StreamBinder.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/StreamObjects.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/StreamUtils.cpp \
                     $(MY_7ZIP_COMMON_SRC_DIR)/VirtThread.cpp

# 7ZIP_UI_COMMON_OBJS
MY_7ZIP_UI_COMMON_SRC_DIR := $(MY_7ZIP_UI_DIR)/Common
MY_7ZIP_UI_COMMON_SRC :=  $(MY_7ZIP_UI_COMMON_SRC_DIR)/ArchiveCommandLine.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/ArchiveExtractCallback.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/ArchiveOpenCallback.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/Bench.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/DefaultName.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/EnumDirItems.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/Extract.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/ExtractingFilePath.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/LoadCodecs.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/OpenArchive.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/PropIDUtils.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/SetProperties.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/SortUtils.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/TempFiles.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/Update.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/UpdateAction.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/UpdateCallback.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/UpdatePair.cpp \
                         $(MY_7ZIP_UI_COMMON_SRC_DIR)/UpdateProduce.cpp

# AR_OBJS
MY_AR_SRC_DIR := $(MY_7ZIP_DIR)/Archive
MY_AR_SRC  := $(MY_AR_SRC_DIR)/Bz2Handler.cpp \
             $(MY_AR_SRC_DIR)/DeflateProps.cpp \
             $(MY_AR_SRC_DIR)/GzHandler.cpp \
             $(MY_AR_SRC_DIR)/LzmaHandler.cpp \
             $(MY_AR_SRC_DIR)/PpmdHandler.cpp \
             $(MY_AR_SRC_DIR)/SplitHandler.cpp \
             $(MY_AR_SRC_DIR)/XzHandler.cpp \
             $(MY_AR_SRC_DIR)/ZHandler.cpp

# AR_COMMON_OBJS
MY_AR_COMMON_DIR := $(MY_AR_SRC_DIR)/Common
MY_AR_COMMON_SRC := $(MY_AR_COMMON_DIR)/CoderMixer2.cpp \
                   $(MY_AR_COMMON_DIR)/CoderMixer2MT.cpp \
                   $(MY_AR_COMMON_DIR)/CrossThreadProgress.cpp \
                   $(MY_AR_COMMON_DIR)/DummyOutStream.cpp \
                   $(MY_AR_COMMON_DIR)/FindSignature.cpp \
                   $(MY_AR_COMMON_DIR)/HandlerOut.cpp \
                   $(MY_AR_COMMON_DIR)/InStreamWithCRC.cpp \
                   $(MY_AR_COMMON_DIR)/ItemNameUtils.cpp \
                   $(MY_AR_COMMON_DIR)/MultiStream.cpp \
                   $(MY_AR_COMMON_DIR)/OutStreamWithCRC.cpp \
                   $(MY_AR_COMMON_DIR)/ParseProperties.cpp

# 7Z_OBJS
MY_7Z_SRC_DIR := $(MY_AR_SRC_DIR)/7z
MY_7Z_SRC := $(MY_7Z_SRC_DIR)/7zCompressionMode.cpp \
            $(MY_7Z_SRC_DIR)/7zDecode.cpp \
            $(MY_7Z_SRC_DIR)/7zEncode.cpp \
            $(MY_7Z_SRC_DIR)/7zExtract.cpp \
            $(MY_7Z_SRC_DIR)/7zFolderInStream.cpp \
            $(MY_7Z_SRC_DIR)/7zFolderOutStream.cpp \
            $(MY_7Z_SRC_DIR)/7zHandler.cpp \
            $(MY_7Z_SRC_DIR)/7zHandlerOut.cpp \
            $(MY_7Z_SRC_DIR)/7zHeader.cpp \
            $(MY_7Z_SRC_DIR)/7zIn.cpp \
            $(MY_7Z_SRC_DIR)/7zOut.cpp \
            $(MY_7Z_SRC_DIR)/7zProperties.cpp \
            $(MY_7Z_SRC_DIR)/7zSpecStream.cpp \
            $(MY_7Z_SRC_DIR)/7zUpdate.cpp \
            $(MY_7Z_SRC_DIR)/7zRegister.cpp

MY_CAB_SRC_DIR := $(MY_AR_SRC_DIR)/Cab
MY_CAB_SRC := $(MY_CAB_SRC_DIR)/CabBlockInStream.cpp \
             $(MY_CAB_SRC_DIR)/CabHandler.cpp \
             $(MY_CAB_SRC_DIR)/CabHeader.cpp \
             $(MY_CAB_SRC_DIR)/CabIn.cpp \
             $(MY_CAB_SRC_DIR)/CabRegister.cpp

MY_TAR_SRC_DIR := $(MY_AR_SRC_DIR)/Tar
MY_TAR_SRC := $(MY_TAR_SRC_DIR)/TarHandler.cpp \
             $(MY_TAR_SRC_DIR)/TarHandlerOut.cpp \
             $(MY_TAR_SRC_DIR)/TarHeader.cpp \
             $(MY_TAR_SRC_DIR)/TarIn.cpp \
             $(MY_TAR_SRC_DIR)/TarOut.cpp \
             $(MY_TAR_SRC_DIR)/TarUpdate.cpp \
             $(MY_TAR_SRC_DIR)/TarRegister.cpp

MY_ZIP_SRC_DIR  := $(MY_AR_SRC_DIR)/Zip
MY_ZIP_SRC := $(MY_ZIP_SRC_DIR)/ZipAddCommon.cpp \
             $(MY_ZIP_SRC_DIR)/ZipHandler.cpp \
             $(MY_ZIP_SRC_DIR)/ZipHandlerOut.cpp \
             $(MY_ZIP_SRC_DIR)/ZipHeader.cpp \
             $(MY_ZIP_SRC_DIR)/ZipIn.cpp \
             $(MY_ZIP_SRC_DIR)/ZipItem.cpp \
             $(MY_ZIP_SRC_DIR)/ZipOut.cpp \
             $(MY_ZIP_SRC_DIR)/ZipUpdate.cpp \
             $(MY_ZIP_SRC_DIR)/ZipRegister.cpp

MY_COMPRESS_SRC_DIR := $(MY_7ZIP_DIR)/Compress
MY_COMPRESS_SRC := $(MY_COMPRESS_SRC_DIR)/Bcj2Coder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/Bcj2Register.cpp \
                  $(MY_COMPRESS_SRC_DIR)/BcjCoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/BcjRegister.cpp \
                  $(MY_COMPRESS_SRC_DIR)/BitlDecoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/BranchCoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/BranchMisc.cpp \
                  $(MY_COMPRESS_SRC_DIR)/BranchRegister.cpp \
                  $(MY_COMPRESS_SRC_DIR)/ByteSwap.cpp \
                  $(MY_COMPRESS_SRC_DIR)/BZip2Crc.cpp \
                  $(MY_COMPRESS_SRC_DIR)/BZip2Decoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/BZip2Encoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/BZip2Register.cpp \
                  $(MY_COMPRESS_SRC_DIR)/CopyCoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/CopyRegister.cpp \
                  $(MY_COMPRESS_SRC_DIR)/Deflate64Register.cpp \
                  $(MY_COMPRESS_SRC_DIR)/DeflateDecoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/DeflateEncoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/DeflateRegister.cpp \
                  $(MY_COMPRESS_SRC_DIR)/DeltaFilter.cpp \
                  $(MY_COMPRESS_SRC_DIR)/ImplodeDecoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/ImplodeHuffmanDecoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/Lzma2Decoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/Lzma2Encoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/Lzma2Register.cpp \
                  $(MY_COMPRESS_SRC_DIR)/LzmaDecoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/LzmaEncoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/LzmaRegister.cpp \
                  $(MY_COMPRESS_SRC_DIR)/LzOutWindow.cpp \
                  $(MY_COMPRESS_SRC_DIR)/Lzx86Converter.cpp \
                  $(MY_COMPRESS_SRC_DIR)/LzxDecoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/PpmdDecoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/PpmdEncoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/PpmdRegister.cpp \
                  $(MY_COMPRESS_SRC_DIR)/PpmdZip.cpp \
                  $(MY_COMPRESS_SRC_DIR)/QuantumDecoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/ShrinkDecoder.cpp \
                  $(MY_COMPRESS_SRC_DIR)/ZDecoder.cpp

MY_CRYPTO_SRC_DIR := $(MY_7ZIP_DIR)/Crypto
MY_CRYPTO_SRC := $(MY_CRYPTO_SRC_DIR)/7zAes.cpp \
                $(MY_CRYPTO_SRC_DIR)/7zAesRegister.cpp \
                $(MY_CRYPTO_SRC_DIR)/HmacSha1.cpp \
                $(MY_CRYPTO_SRC_DIR)/MyAes.cpp \
                $(MY_CRYPTO_SRC_DIR)/Pbkdf2HmacSha1.cpp \
                $(MY_CRYPTO_SRC_DIR)/RandGen.cpp \
                $(MY_CRYPTO_SRC_DIR)/Sha1.cpp \
                $(MY_CRYPTO_SRC_DIR)/WzAes.cpp \
                $(MY_CRYPTO_SRC_DIR)/ZipCrypto.cpp \
                $(MY_CRYPTO_SRC_DIR)/ZipStrong.cpp

MY_C_SRC := $(MY_C_DIR)/7zStream.c \
           $(MY_C_DIR)/Aes.c \
           $(MY_C_DIR)/Alloc.c \
           $(MY_C_DIR)/Bra.c \
           $(MY_C_DIR)/Bra86.c \
           $(MY_C_DIR)/BraIA64.c \
           $(MY_C_DIR)/BwtSort.c \
           $(MY_C_DIR)/Delta.c \
           $(MY_C_DIR)/HuffEnc.c \
           $(MY_C_DIR)/LzFind.c \
           $(MY_C_DIR)/LzFindMt.c \
           $(MY_C_DIR)/Lzma2Dec.c \
           $(MY_C_DIR)/Lzma2Enc.c \
           $(MY_C_DIR)/LzmaDec.c \
           $(MY_C_DIR)/LzmaEnc.c \
           $(MY_C_DIR)/MtCoder.c \
           $(MY_C_DIR)/Ppmd7.c \
           $(MY_C_DIR)/Ppmd7Dec.c \
           $(MY_C_DIR)/Ppmd7Enc.c \
           $(MY_C_DIR)/Ppmd8.c \
           $(MY_C_DIR)/Ppmd8Dec.c \
           $(MY_C_DIR)/Ppmd8Enc.c \
           $(MY_C_DIR)/Sha256.c \
           $(MY_C_DIR)/Sort.c \
           $(MY_C_DIR)/Threads.c \
           $(MY_C_DIR)/Xz.c \
           $(MY_C_DIR)/XzCrc64.c \
           $(MY_C_DIR)/XzDec.c \
           $(MY_C_DIR)/XzEnc.c \
           $(MY_C_DIR)/XzIn.c

MY_CRC32_C_SRC := $(MY_C_DIR)/7zCrc.c $(MY_C_DIR)/7zCrcOpt.c

MY_UTILITY_SRC_DIR := $(MY_CPP_DIR)/myWindows
MY_UTILITY_SRC := $(MY_UTILITY_SRC_DIR)/myGetTickCount.cpp \
                 $(MY_UTILITY_SRC_DIR)/wine_date_and_time.cpp \
                 $(MY_UTILITY_SRC_DIR)/myAddExeFlag.cpp \
                 $(MY_UTILITY_SRC_DIR)/mySplitCommandLine.cpp

MY_7ZA_SRC_DIR := 7za
MY_7ZA_SRC := $(MY_7ZA_SRC_DIR)/7za.cpp

# Source file list
LOCAL_SRC_FILES := $(MY_CONSOLE_SRC) $(MY_COMMON_SRC) $(MY_WIN_SRC) \
                   $(MY_7ZIP_COMMON_SRC) $(MY_7ZIP_UI_COMMON_SRC) \
                   $(MY_AR_SRC) $(MY_AR_COMMON_SRC) $(MY_7Z_SRC) \
                   $(MY_CAB_SRC) $(MY_TAR_SRC) $(MY_ZIP_SRC) $(MY_COMPRESS_SRC) \
                   $(MY_CRYPTO_SRC) $(MY_C_SRC) $(MY_CRC32_C_SRC) $(MY_UTILITY_SRC) $(MY_7ZA_SRC)

LOCAL_C_INCLUDES := $(LOCAL_PATH)/$(MY_CPP_DIR)/myWindows $(LOCAL_PATH)/$(MY_CPP_DIR) \
                    $(LOCAL_PATH)/$(MY_CPP_DIR)/include_windows

LOCAL_CFLAGS := $(MY_CFLAGS)
LOCAL_CPPFLAGS := $(MY_CFLAGS) $(MY_CPP_FLAGS)

# link android log lib
LOCAL_LDLIBS    := -llog

include $(BUILD_SHARED_LIBRARY)
