package com.mohsin.baseapp.Helpers.Entities;

import android.app.Application;

import com.sromku.simple.storage.SimpleStorage;
import com.sromku.simple.storage.Storage;

import java.util.Set;

public class App extends Application {
    private static App sInstance;

    public Set<String> privileges;
    public Storage storage = null;
    public String FileName;

    public String deviceType;
    @Override
    public void onCreate() {
        super.onCreate();

        try {

            if (SimpleStorage.isExternalStorageWritable()) {
                storage = SimpleStorage.getExternalStorage();
            }
            else {
                storage = SimpleStorage.getInternalStorage(getApplicationContext());
            }

            if(!storage.isDirectoryExists("Meezan")){
                storage.createDirectory("Meezan",false);
            }

            FileName = Utils.getCurrentDateTime(4);
            if(!storage.isFileExist("Meezan", FileName+".txt")){
                storage.createFile("Meezan", FileName+".txt", "");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            deviceType = android.os.Build.DEVICE;

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public App() {
        sInstance = this;
    }

    public static App get() {
        return sInstance;
    }


}
