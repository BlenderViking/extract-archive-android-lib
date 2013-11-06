#include <stdio.h>
#include <sstream>
#include "StdAfx.h"
#include "Common/CommandLineParser.h"
#include "myPrivate.h"
#include "Windows/System.h"


#include "Common/MyException.h"
#include "Common/StdOutStream.h"
#include "Windows/Error.h"
#include "Windows/NtCheck.h"

#include "../CPP/7zip/UI/Common/ArchiveCommandLine.h"
#include "../CPP/7zip/UI/Common/ExitCode.h"
#include "../CPP/7zip/UI/Console/ConsoleClose.h"

#include "7za.h"
#include "7za_log.h"
#include "com_snda_Andro7z_Andro7za.h"

#define  ARGC 1
static const char *test_args [ARGC+1] = {
    "7za",
    // "x",
    // "-o/mnt/sdcard",
    // "/mnt/sdcard/vista.7z",
    0
};

JNIEXPORT jint JNICALL Java_com_snda_Andro7z_Andro7za_a7za_1print_1usage
  (JNIEnv *, jobject)
{
    LOG_D("call Java_com_snda_Andro7z_Andro7za_a7za_1print_1usage.\n");
    jint ret = run_7za(ARGC, test_args);
    return ret;
}

static const char *kExceptionErrorMessage = "\n\nError:\n";
static const char *kUserBreak  = "\nBreak signaled\n";
static const char *kMemoryExceptionMessage = "\n\nERROR: Can't allocate required memory!\n";
static const char *kUnknownExceptionMessage = "\n\nUnknown Error\n";
static const char *kInternalExceptionMessage = "\n\nInternal Error #";

const char *HELP_STRING =
    "\nUsage: 7z"
#ifdef _NO_CRYPTO
    "r"
#else
#ifndef EXTERNAL_CODECS
    "a"
#endif
#endif
    " <command> [<switches>...] <archive_name> [<file_names>...]\n"
    "       [<@listfiles...>]\n"
    "\n"
    "<Commands>\n"
    "  a: Add files to archive\n"
    "  b: Benchmark\n"
    "  d: Delete files from archive\n"
    "  e: Extract files from archive (without using directory names)\n"
    "  l: List contents of archive\n"
    "  t: Test integrity of archive\n"
    "  u: Update files to archive\n"
    "  x: eXtract files with full paths\n"
    "<Switches>\n"
    "  -ai[r[-|0]]{@listfile|!wildcard}: Include archives\n"
    "  -ax[r[-|0]]{@listfile|!wildcard}: eXclude archives\n"
    "  -bd: Disable percentage indicator\n"
    "  -i[r[-|0]]{@listfile|!wildcard}: Include filenames\n"
    "  -m{Parameters}: set compression Method\n"
    "  -o{Directory}: set Output directory\n"
    #ifndef _NO_CRYPTO
    "  -p{Password}: set Password\n"
    #endif
    "  -r[-|0]: Recurse subdirectories\n"
    "  -scs{UTF-8 | WIN | DOS}: set charset for list files\n"
    "  -sfx[{name}]: Create SFX archive\n"
    "  -si[{name}]: read data from stdin\n"
    "  -slt: show technical information for l (List) command\n"
    "  -so: write data to stdout\n"
    "  -ssc[-]: set sensitive case mode\n"
    "  -t{Type}: Set type of archive\n"
    "  -u[-][p#][q#][r#][x#][y#][z#][!newArchiveName]: Update options\n"
    "  -v{Size}[b|k|m|g]: Create volumes\n"
    "  -w[{path}]: assign Work directory. Empty path means a temporary directory\n"
    "  -x[r[-|0]]]{@listfile|!wildcard}: eXclude filenames\n"
    "  -y: assume Yes on all queries\n";

static const char *COPYRIGHT_STRING = "\n7-Zip";

static void show_copyright_and_help()
{
    LOG_I("%s (locale=%s,Utf16=%s,%d CPU(s))",
          COPYRIGHT_STRING,
          my_getlocale(),
          (global_use_utf16_conversion ? "on" : "off"),
          NWindows::NSystem::GetNumberOfProcessors());
    LOG_I(HELP_STRING);
}

int run_7za(int argc, const char *argv[])
{
    CStdOutStream &stdStream = g_StdOut;
    CStdOutStream *g_StdStream = &stdStream;
    
    try {
        UStringVector commandStrings;
    
#ifdef _WIN32
        NCommandLineParser::SplitCommandLine(GetCommandLineW(), commandStrings);
#else
        extern void mySplitCommandLine(int numArgs,const char *args[],UStringVector &parts);
        mySplitCommandLine(argc, argv, commandStrings);
#endif

        show_copyright_and_help();
    
        if (commandStrings.Size() == 1)
        {
            // 如果没有任何参数，打印帮助信息
            show_copyright_and_help();
            return 0;
        }
    
        // commandStrings.Delete(0);

//     CArchiveCommandLineOptions options;

//     CArchiveCommandLineParser parser;

//     parser.Parse1(commandStrings, options);

//     if (options.HelpMode)
//     {
//         ShowCopyrightAndHelp(g_StdOut, true);
//         return 0;
//     }

// #if defined(_7ZIP_LARGE_PAGES)
//     if (options.LargePages)
//     {
//         SetLargePageSize();
// #ifdef _WIN32
//         NSecurity::EnableLockMemoryPrivilege();
// #endif
//     }
// #endif

//     CStdOutStream &stdStream = options.StdOutMode ? g_StdErr : g_StdOut;
//     g_StdStream = &stdStream;

//     if (options.EnableHeaders)
//         ShowCopyrightAndHelp(stdStream, false);

//     parser.Parse2(options);

//     CCodecs *codecs = new CCodecs;
//     CMyComPtr<
// #ifdef EXTERNAL_CODECS
//         ICompressCodecsInfo
// #else
//         IUnknown
// #endif
//         > compressCodecsInfo = codecs;
//     HRESULT result = codecs->Load();
//     if (result != S_OK)
//         throw CSystemException(result);

//     bool isExtractGroupCommand = options.Command.IsFromExtractGroup();

//     if (codecs->Formats.Size() == 0 &&
//         (isExtractGroupCommand ||
//          options.Command.CommandType == NCommandType::kList ||
//          options.Command.IsFromUpdateGroup()))
//         throw kNoFormats;

//     CIntVector formatIndices;
//     if (!codecs->FindFormatForArchiveType(options.ArcType, formatIndices))
//         throw kUnsupportedArcTypeMessage;

//     if (options.Command.CommandType == NCommandType::kInfo)
//     {
//         stdStream << endl << "Formats:" << endl;
//         int i;
//         for (i = 0; i < codecs->Formats.Size(); i++)
//         {
//             const CArcInfoEx &arc = codecs->Formats[i];
// #ifdef EXTERNAL_CODECS
//             if (arc.LibIndex >= 0)
//             {
//                 char s[16];
//                 ConvertUInt32ToString(arc.LibIndex, s);
//                 PrintString(stdStream, s, 2);
//             }
//             else
// #endif
//                 stdStream << "  ";
//             stdStream << ' ';
//             stdStream << (char)(arc.UpdateEnabled ? 'C' : ' ');
//             stdStream << (char)(arc.KeepName ? 'K' : ' ');
//             stdStream << "  ";
//             PrintString(stdStream, arc.Name, 6);
//             stdStream << "  ";
//             UString s;
//             for (int t = 0; t < arc.Exts.Size(); t++)
//             {
//                 const CArcExtInfo &ext = arc.Exts[t];
//                 s += ext.Ext;
//                 if (!ext.AddExt.IsEmpty())
//                 {
//                     s += L" (";
//                     s += ext.AddExt;
//                     s += L')';
//                 }
//                 s += L' ';
//             }
//             PrintString(stdStream, s, 14);
//             stdStream << "  ";
//             const CByteBuffer &sig = arc.StartSignature;
//             for (size_t j = 0; j < sig.GetCapacity(); j++)
//             {
//                 Byte b = sig[j];
//                 if (b > 0x20 && b < 0x80)
//                 {
//                     stdStream << (char)b;
//                 }
//                 else
//                 {
//                     stdStream << GetHex((Byte)((b >> 4) & 0xF));
//                     stdStream << GetHex((Byte)(b & 0xF));
//                 }
//                 stdStream << ' ';
//             }
//             stdStream << endl;
//         }
//         stdStream << endl << "Codecs:" << endl;

// #ifdef EXTERNAL_CODECS
//         UInt32 numMethods;
//         if (codecs->GetNumberOfMethods(&numMethods) == S_OK)
//             for (UInt32 j = 0; j < numMethods; j++)
//             {
//                 int libIndex = codecs->GetCodecLibIndex(j);
//                 if (libIndex >= 0)
//                 {
//                     char s[16];
//                     ConvertUInt32ToString(libIndex, s);
//                     PrintString(stdStream, s, 2);
//                 }
//                 else
//                     stdStream << "  ";
//                 stdStream << ' ';
//                 stdStream << (char)(codecs->GetCodecEncoderIsAssigned(j) ? 'C' : ' ');
//                 UInt64 id;
//                 stdStream << "  ";
//                 HRESULT res = codecs->GetCodecId(j, id);
//                 if (res != S_OK)
//                     id = (UInt64)(Int64)-1;
//                 char s[32];
//                 ConvertUInt64ToString(id, s, 16);
//                 PrintString(stdStream, s, 8);
//                 stdStream << "  ";
//                 PrintString(stdStream, codecs->GetCodecName(j), 11);
//                 stdStream << endl;
//                 /*
//                   if (res != S_OK)
//                   throw "incorrect Codec ID";
//                 */
//             }
// #endif
//         return S_OK;
//     }
//     else if (options.Command.CommandType == NCommandType::kBenchmark)
//     {
//         if (options.Method.CompareNoCase(L"CRC") == 0)
//         {
//             HRESULT res = CrcBenchCon((FILE *)stdStream, options.NumIterations, options.NumThreads, options.DictionarySize);
//             if (res != S_OK)
//             {
//                 if (res == S_FALSE)
//                 {
//                     stdStream << "\nCRC Error\n";
//                     return NExitCode::kFatalError;
//                 }
//                 throw CSystemException(res);
//             }
//         }
//         else
//         {
//             HRESULT res;
// #ifdef EXTERNAL_CODECS
//             CObjectVector<CCodecInfoEx> externalCodecs;
//             res = LoadExternalCodecs(compressCodecsInfo, externalCodecs);
//             if (res != S_OK)
//                 throw CSystemException(res);
// #endif
//             res = LzmaBenchCon(
// #ifdef EXTERNAL_CODECS
//                 compressCodecsInfo, &externalCodecs,
// #endif
//                 (FILE *)stdStream, options.NumIterations, options.NumThreads, options.DictionarySize);
//             if (res != S_OK)
//             {
//                 if (res == S_FALSE)
//                 {
//                     stdStream << "\nDecoding Error\n";
//                     return NExitCode::kFatalError;
//                 }
//                 throw CSystemException(res);
//             }
//         }
//     }
//     else if (isExtractGroupCommand || options.Command.CommandType == NCommandType::kList)
//     {
//         if (isExtractGroupCommand)
//         {
//             CExtractCallbackConsole *ecs = new CExtractCallbackConsole;
//             CMyComPtr<IFolderArchiveExtractCallback> extractCallback = ecs;

//             ecs->OutStream = &stdStream;

// #ifndef _NO_CRYPTO
//             ecs->PasswordIsDefined = options.PasswordEnabled;
//             ecs->Password = options.Password;
// #endif

//             ecs->Init();

//             COpenCallbackConsole openCallback;
//             openCallback.OutStream = &stdStream;

// #ifndef _NO_CRYPTO
//             openCallback.PasswordIsDefined = options.PasswordEnabled;
//             openCallback.Password = options.Password;
// #endif

//             CExtractOptions eo;
//             eo.StdInMode = options.StdInMode;
//             eo.StdOutMode = options.StdOutMode;
//             eo.PathMode = options.Command.GetPathMode();
//             eo.TestMode = options.Command.IsTestMode();
//             eo.OverwriteMode = options.OverwriteMode;
//             eo.OutputDir = options.OutputDir;
//             eo.YesToAll = options.YesToAll;
//             eo.CalcCrc = options.CalcCrc;
// #if !defined(_7ZIP_ST) && !defined(_SFX)
//             eo.Properties = options.ExtractProperties;
// #endif
//             UString errorMessage;
//             CDecompressStat stat;
//             HRESULT result = DecompressArchives(
//                 codecs,
//                 formatIndices,
//                 options.ArchivePathsSorted,
//                 options.ArchivePathsFullSorted,
//                 options.WildcardCensor.Pairs.Front().Head,
//                 eo, &openCallback, ecs, errorMessage, stat);
//             if (!errorMessage.IsEmpty())
//             {
//                 stdStream << endl << "Error: " << errorMessage;
//                 if (result == S_OK)
//                     result = E_FAIL;
//             }

//             stdStream << endl;
//             if (ecs->NumArchives > 1)
//                 stdStream << "Archives: " << ecs->NumArchives << endl;
//             if (ecs->NumArchiveErrors != 0 || ecs->NumFileErrors != 0)
//             {
//                 if (ecs->NumArchives > 1)
//                 {
//                     stdStream << endl;
//                     if (ecs->NumArchiveErrors != 0)
//                         stdStream << "Archive Errors: " << ecs->NumArchiveErrors << endl;
//                     if (ecs->NumFileErrors != 0)
//                         stdStream << "Sub items Errors: " << ecs->NumFileErrors << endl;
//                 }
//                 if (result != S_OK)
//                     throw CSystemException(result);
//                 return NExitCode::kFatalError;
//             }
//             if (result != S_OK)
//                 throw CSystemException(result);
//             if (stat.NumFolders != 0)
//                 stdStream << "Folders: " << stat.NumFolders << endl;
//             if (stat.NumFiles != 1 || stat.NumFolders != 0)
//                 stdStream << "Files: " << stat.NumFiles << endl;
//             stdStream
//                 << "Size:       " << stat.UnpackSize << endl
//                 << "Compressed: " << stat.PackSize << endl;
//             if (options.CalcCrc)
//             {
//                 char s[16];
//                 ConvertUInt32ToHexWithZeros(stat.CrcSum, s);
//                 stdStream << "CRC: " << s << endl;
//             }
//         }
//         else
//         {
//             UInt64 numErrors = 0;
//             HRESULT result = ListArchives(
//                 codecs,
//                 formatIndices,
//                 options.StdInMode,
//                 options.ArchivePathsSorted,
//                 options.ArchivePathsFullSorted,
//                 options.WildcardCensor.Pairs.Front().Head,
//                 options.EnableHeaders,
//                 options.TechMode,
// #ifndef _NO_CRYPTO
//                 options.PasswordEnabled,
//                 options.Password,
// #endif
//                 numErrors);
//             if (numErrors > 0)
//             {
//                 g_StdOut << endl << "Errors: " << numErrors;
//                 return NExitCode::kFatalError;
//             }
//             if (result != S_OK)
//                 throw CSystemException(result);
//         }
//     }
//     else if (options.Command.IsFromUpdateGroup())
//     {
//         CUpdateOptions &uo = options.UpdateOptions;
//         if (uo.SfxMode && uo.SfxModule.IsEmpty())
//             uo.SfxModule = kDefaultSfxModule;

//         COpenCallbackConsole openCallback;
//         openCallback.OutStream = &stdStream;

// #ifndef _NO_CRYPTO
//         bool passwordIsDefined =
//             options.PasswordEnabled && !options.Password.IsEmpty();
//         openCallback.PasswordIsDefined = passwordIsDefined;
//         openCallback.Password = options.Password;
// #endif

//         CUpdateCallbackConsole callback;
//         callback.EnablePercents = options.EnablePercents;

// #ifndef _NO_CRYPTO
//         callback.PasswordIsDefined = passwordIsDefined;
//         callback.AskPassword = options.PasswordEnabled && options.Password.IsEmpty();
//         callback.Password = options.Password;
// #endif
//         callback.StdOutMode = uo.StdOutMode;
//         callback.Init(&stdStream);

//         CUpdateErrorInfo errorInfo;

//         if (!uo.Init(codecs, formatIndices, options.ArchiveName))
//             throw kUnsupportedArcTypeMessage;
//         HRESULT result = UpdateArchive(codecs,
//                                        options.WildcardCensor, uo,
//                                        errorInfo, &openCallback, &callback);

// #ifdef ENV_UNIX
//         if (uo.SfxMode)
//         {
//             void myAddExeFlag(const UString &name);
//             for(int i = 0; i < uo.Commands.Size(); i++)
//             {
//                 CUpdateArchiveCommand &command = uo.Commands[i];
//                 if (!uo.StdOutMode)
//                 {
//                     myAddExeFlag(command.ArchivePath.GetFinalPath());
//                 }
//             }
//         }
// #endif

//         int exitCode = NExitCode::kSuccess;
//         if (callback.CantFindFiles.Size() > 0)
//         {
//             stdStream << endl;
//             stdStream << "WARNINGS for files:" << endl << endl;
//             int numErrors = callback.CantFindFiles.Size();
//             for (int i = 0; i < numErrors; i++)
//             {
//                 stdStream << callback.CantFindFiles[i] << " : ";
//                 stdStream << NError::MyFormatMessageW(callback.CantFindCodes[i]) << endl;
//             }
//             stdStream << "----------------" << endl;
//             stdStream << "WARNING: Cannot find " << numErrors << " file";
//             if (numErrors > 1)
//                 stdStream << "s";
//             stdStream << endl;
//             exitCode = NExitCode::kWarning;
//         }

//         if (result != S_OK)
//         {
//             UString message;
//             if (!errorInfo.Message.IsEmpty())
//             {
//                 message += errorInfo.Message;
//                 message += L"\n";
//             }
//             if (!errorInfo.FileName.IsEmpty())
//             {
//                 message += errorInfo.FileName;
//                 message += L"\n";
//             }
//             if (!errorInfo.FileName2.IsEmpty())
//             {
//                 message += errorInfo.FileName2;
//                 message += L"\n";
//             }
//             if (errorInfo.SystemError != 0)
//             {
//                 message += NError::MyFormatMessageW(errorInfo.SystemError);
//                 message += L"\n";
//             }
//             if (!message.IsEmpty())
//                 stdStream << L"\nError:\n" << message;
//             throw CSystemException(result);
//         }
//         int numErrors = callback.FailedFiles.Size();
//         if (numErrors == 0)
//         {
//             if (callback.CantFindFiles.Size() == 0)
//                 stdStream << kEverythingIsOk << endl;
//         }
//         else
//         {
//             stdStream << endl;
//             stdStream << "WARNINGS for files:" << endl << endl;
//             for (int i = 0; i < numErrors; i++)
//             {
//                 stdStream << callback.FailedFiles[i] << " : ";
//                 stdStream << NError::MyFormatMessageW(callback.FailedCodes[i]) << endl;
//             }
//             stdStream << "----------------" << endl;
//             stdStream << "WARNING: Cannot open " << numErrors << " file";
//             if (numErrors > 1)
//                 stdStream << "s";
//             stdStream << endl;
//             exitCode = NExitCode::kWarning;
//         }
//         return exitCode;
//     }
//     else
//         PrintHelpAndExit(stdStream);
    }
    catch(const NConsoleClose::CCtrlBreakException &)
    {
        LOG_E("\n%s", kUserBreak);
        return (NExitCode::kUserBreak);
    }
    catch(const CArchiveCommandLineException &e)
    {
        (*g_StdStream) << kExceptionErrorMessage << e << endl;
        return (NExitCode::kUserError);
    }
    catch(const CSystemException &systemError)
    {
        if (systemError.ErrorCode == E_OUTOFMEMORY)
        {
            (*g_StdStream) << kMemoryExceptionMessage;
            return (NExitCode::kMemoryError);
        }
        if (systemError.ErrorCode == E_ABORT)
        {
            (*g_StdStream) << endl << kUserBreak;
            return (NExitCode::kUserBreak);
        }
        UString message;
        (*g_StdStream) << endl << endl << "System error:" << endl << message << endl;
        return (NExitCode::kFatalError);
    }
    catch(NExitCode::EEnum &exitCode)
    {
        (*g_StdStream) << kInternalExceptionMessage << exitCode << endl;
        return (exitCode);
    }
    catch(const UString &s)
    {
        (*g_StdStream) << kExceptionErrorMessage << s << endl;
        return (NExitCode::kFatalError);
    }
    catch(const AString &s)
    {
        (*g_StdStream) << kExceptionErrorMessage << s << endl;
        return (NExitCode::kFatalError);
    }
    catch(const char *s)
    {
        (*g_StdStream) << kExceptionErrorMessage << s << endl;
        return (NExitCode::kFatalError);
    }
    catch(int t)
    {
        (*g_StdStream) << kInternalExceptionMessage << t << endl;
        return (NExitCode::kFatalError);
    }
    catch(...)
    {
        (*g_StdStream) << kUnknownExceptionMessage;
        return (NExitCode::kFatalError);
    }
}
