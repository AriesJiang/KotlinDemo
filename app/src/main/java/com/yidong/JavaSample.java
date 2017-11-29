package com.yidong;

import android.util.Log;

/**
 * Created by Administrator on 2017/11/23.
 */

public class JavaSample {

    public static void dome() {
        Object[] objects;
        String[] strings2 = {"1", "2", "3", "4"};
        objects = strings2;
        if (strings2 != null) {
            for (String str : strings2
                    ) {
                Log.d("JavaSample", "str = " + str);
            }
        }

        if (objects != null) {
            for (Object obj : objects
                 ) {
                Log.d("JavaSample", "obj = " + ((String) obj));
            }
        }
    }
}
