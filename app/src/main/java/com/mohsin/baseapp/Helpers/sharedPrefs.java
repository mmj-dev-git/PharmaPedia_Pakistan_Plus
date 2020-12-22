package com.mohsin.baseapp.Helpers;

import android.content.Context;
import android.content.SharedPreferences;


import com.mohsin.baseapp.Helpers.Entities.App;
import com.mohsin.baseapp.R;

public class sharedPrefs {

    private static SharedPreferences Prefs(Context context) {
        return context.getSharedPreferences(String.valueOf(R.string.app_name), context.MODE_PRIVATE);
    }

    public static SharedPreferences getPref() {
        return Prefs(App.get().getApplicationContext());
    }

    public static SharedPreferences.Editor setPref() {
        // perform validation etc..
        return Prefs(App.get().getApplicationContext()).edit();
    }

    public static void putString(String key, String value)
    {
        try {
            setPref().putString(AESEncyption.encrypt(key,false),AESEncyption.encrypt(value,true)).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void putLong(String key, long value)
    {
        setPref().putLong(key,value).commit();
    }

    public static void putInt(String key, int value)
    {
        setPref().putInt(key,value).commit();
    }

    public static void putBool(String key, boolean value)
    {
        setPref().putBoolean(key,value).commit();
    }

    public static String getString(String key)
    {
        try {
            String keyTemp = AESEncyption.encrypt(key,false);
            String value = getPref().getString(keyTemp,"");

            if(!value.equals(""))
            {
                return  AESEncyption.decrypt(value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Long getLong(String key)
    {
        return getPref().getLong(key,0);
    }

    public static int getInt(String key)
    {
        return getPref().getInt(key,0);
    }

    public static Boolean getBool(String key)
    {
        return getPref().getBoolean(key,false);
    }

    public static void clearAll()
    {
        getPref().edit().clear().apply();
    }


}