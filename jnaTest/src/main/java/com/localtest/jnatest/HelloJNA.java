package com.localtest.jnatest;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public class HelloJNA {  // Save as com.localtest.jnatest.HelloJNA.java
    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary)
                Native.load((Platform.isWindows() ? "msvcrtDuplicate" : "c"),
                        CLibrary.class);

        void printf(String format, Object... args);
    }
}