package com.zsy.sum.utils.depend;

import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * Created by 24275 on 2016/9/29.
 */

public class Lg {

    public static final String LgHttpReq="LgHttpReq";
    public static final String LgHttpRep="LgHttpRep";


    public static final int LogType = 0; //android util
    public static final int LoggerType = 1; //logger
    private static final boolean Debug = true;

    private static final String Tag = "LgTag";

    private static int type = LogType;
    private static String tag = Tag;

    private static final int MethodCount = 1;
    private static final int MethodOffset = 2;


    public static void init(int type, String tag) {
        Lg.tag = tag;
        Lg.type = type;
    }

    public static void init(int type) {
        init(type, Tag);
    }

    public static void i(int type, String tag, String msg) {
        if (!Debug) {
            return;
        }
        if (msg == null) {
            msg = "null";
        }
        if (tag == null) {
            tag = Tag;
        }
        if (type == LogType) {
            Log.i(tag, msg);
        } else {
            loggerI(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (type == LogType) {
            i(type, tag, msg);
        } else {
            loggerI(tag, msg);
        }
    }

    public static void i(String msg) {
        if (type == LogType) {
            i(tag, msg);
        } else {
            loggerI(tag, msg);
        }
    }

    private static void loggerI(String tag, String msg) {
        Logger.init(tag).methodOffset(MethodOffset).methodCount(MethodCount);
        Logger.i(msg);
    }


    public static void v(int type, String tag, String msg) {
        if (!Debug) {
            return;
        }
        if (msg == null) {
            msg = "null";
        }
        if (tag == null) {
            tag = Tag;
        }
        if (type == LogType) {
            Log.v(tag, msg);
        } else {
            loggerV(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (type == LogType) {
            v(type, tag, msg);
        } else {
            loggerV(tag, msg);
        }
    }

    public static void v(String msg) {
        if (type == LogType) {
            v(tag, msg);
        } else {
            loggerV(tag, msg);
        }
    }

    private static void loggerV(String tag, String msg) {
        Logger.init(tag).methodOffset(MethodOffset).methodCount(MethodCount);
        Logger.v(msg);
    }





    public static void d(int type, String tag, String msg) {
        if (!Debug) {
            return;
        }
        if (msg == null) {
            msg = "null";
        }
        if (tag == null) {
            tag = Tag;
        }
        if (type == LogType) {
            Log.d(tag, msg);
        } else {
            loggerD(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (type == LogType) {
            d(type, tag, msg);
        } else {
            loggerD(tag, msg);
        }
    }

    public static void d(String msg) {
        if (type == LogType) {
            d(tag, msg);
        } else {
            loggerD(tag, msg);
        }
    }

    private static void loggerD(String tag, String msg) {
        Logger.init(tag).methodOffset(MethodOffset).methodCount(MethodCount);
        Logger.d(msg);
    }



    public static void e(int type, String tag, String msg) {
        if (!Debug) {
            return;
        }
        if (msg == null) {
            msg = "null";
        }
        if (tag == null) {
            tag = Tag;
        }
        if (type == LogType) {
            Log.e(tag, msg);
        } else {
            loggerE(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (type == LogType) {
            e(type, tag, msg);
        } else {
            loggerE(tag, msg);
        }
    }

    public static void e(String msg) {
        if (type == LogType) {
            e(tag, msg);
        } else {
            loggerE(tag, msg);
        }
    }

    private static void loggerE(String tag, String msg) {
        Logger.init(tag).methodOffset(MethodOffset).methodCount(MethodCount);
        Logger.e(msg);
    }


}
