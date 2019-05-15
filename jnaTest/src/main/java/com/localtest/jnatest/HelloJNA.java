package com.localtest.jnatest;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.ptr.NativeLongByReference;

public class HelloJNA {
    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary)
                Native.load((Platform.isWindows() ? "gsdll64" : "c"),
                        CLibrary.class);

        int gsdll_revision(String[] product, String[] copyright,
                           NativeLongByReference gs_revision, NativeLongByReference gs_revisiondate);
        //origin c api
        //int GSDLLAPI gsdll_revision(char **product, char **copyright, long *gs_revision, long *gs_revisiondate)
        //https://www.ghostscript.com/doc/current/DLL.htm
    }
    public interface CLibrary32 extends Library {
        CLibrary32 INSTANCE = (CLibrary32)
                Native.load((Platform.isWindows() ? "gsdll32" : "c"),
                        CLibrary32.class);

        int gsdll_revision(String[] product, String[] copyright,
                           NativeLongByReference gs_revision, NativeLongByReference gs_revisiondate);
    }
    public void call_gsdll_revision(){
        if (Platform.is64Bit()){
            call_gsdll_revision_64();
        } else {
            call_gsdll_revision_32();
        }
    }
    public void call_gsdll_revision_64(){
        NativeLongByReference gsVersion = new NativeLongByReference();
        NativeLongByReference gsRevisiondate = new NativeLongByReference();
        String[] product = new String[100];
        String[] copyright = new String[100];
        HelloJNA.CLibrary.INSTANCE.gsdll_revision(product, copyright,
                gsVersion, gsRevisiondate);
        System.out.println("running on 64 bit program");
        System.out.print("product:");
        for(int i = 0; i < product.length ;i++){
            if (product[i] != null){
                System.out.println(product[i]);
            }
        }
        System.out.print("copyright:");
        for(int i = 0; i < copyright.length ;i++){
            if (copyright[i] != null){
                System.out.println(copyright[i]);
            }
        }
        System.out.println("version:" + gsVersion.getValue().longValue());
        System.out.println("revision date:" + gsRevisiondate.getValue().longValue());
    }
    public void call_gsdll_revision_32(){
        NativeLongByReference gsVersion = new NativeLongByReference();
        NativeLongByReference gsRevisiondate = new NativeLongByReference();
        String[] product = new String[100];
        String[] copyright = new String[100];
        HelloJNA.CLibrary32.INSTANCE.gsdll_revision(product, copyright,
                gsVersion, gsRevisiondate);
        System.out.println("running on 32 bit program");
        System.out.print("product:");
        for(int i = 0; i < product.length ;i++){
            if (product[i] != null){
                System.out.println(product[i]);
            }
        }
        System.out.print("copyright:");
        for(int i = 0; i < copyright.length ;i++){
            if (copyright[i] != null){
                System.out.println(copyright[i]);
            }
        }
        System.out.println("version:" + gsVersion.getValue().longValue());
        System.out.println("revision date:" + gsRevisiondate.getValue().longValue());
    }
}