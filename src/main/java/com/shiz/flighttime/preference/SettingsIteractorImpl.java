package com.shiz.flighttime.preference;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.shiz.flighttime.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import io.realm.Realm;
import io.realm.internal.IOException;

/**
 * Created by oldman on 31.08.16.
 */
public class SettingsIteractorImpl implements SettingsIteractor {
    private static final String TAG = SettingsIteractorImpl.class.getSimpleName();
    private Context context;
    private Realm realm = Realm.getDefaultInstance();

    public SettingsIteractorImpl(Context context) {
        this.context = context;
    }


    @Override
    public void exportRealmDB(DataBaseListener listener) {
        File exportRealmFile = getExportFile();
        if (realm.isClosed())
            this.realm = Realm.getDefaultInstance();
        try {
            // if backup file already exists, delete it
            exportRealmFile.delete();
            // copy current realm to backup file
            try {
                realm.writeCopyTo(exportRealmFile);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String msg = context.getResources().getString(R.string.export_file) + exportRealmFile.getAbsolutePath();
        Log.d(TAG, msg);
        realm.close();
        listener.onExportDataBase(msg);
    }

    @Override
    public void importRealmDB(String path, DataBaseListener listener) {
        String pathFile = copyBundledRealmFile(path);
        if (pathFile != null)
            listener.onImportDataBase(pathFile);
        else
            listener.onEmptyDataBase();
    }

    private String copyBundledRealmFile(final String oldFilePath) {
        try {
            File oldFile;
            File file = new File(context.getFilesDir() + "/default.realm");
            FileOutputStream outputStream = new FileOutputStream(file);
            if (oldFilePath == null) {
                oldFile = getExportFile();
                if (oldFile.length() == 0)
                    return null;
            } else
                oldFile = new File(oldFilePath);
            FileInputStream inputStream = new FileInputStream(oldFile);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, bytesRead);
            }
            outputStream.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File getExportFile() {
        File file;
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            file = new File(Environment.getExternalStorageDirectory() + getAppName() + getAppName() + ".realm");
        } else {
            file = new File(Environment.getDataDirectory() + getAppName() + getAppName() + ".realm");
        }
        if (!file.getParentFile().exists())
            file.getParentFile().mkdir();
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        return file;
    }

    private String getRealmPath() {
        if (realm.isClosed())
            this.realm = Realm.getDefaultInstance();
        return realm.getPath();

    }

    private String getAppName() {
        return File.separator + context.getResources().getString(R.string.app_name);
    }
}
