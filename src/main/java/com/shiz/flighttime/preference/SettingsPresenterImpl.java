package com.shiz.flighttime.preference;

import android.content.Context;

import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.shiz.flighttime.R;

import java.io.File;

/**
 * Created by oldman on 31.08.16.
 */
public class SettingsPresenterImpl implements SettingsPresenter, SettingsIteractor.DataBaseListener {

    private SettingsIteractor iteractor;
    private SettingsActivityView view;
    private Context context;

    public SettingsPresenterImpl(SettingsActivityView view, Context context) {
        iteractor = new SettingsIteractorImpl(context);
        this.view = view;
        this.context = context;
    }

    @Override
    public String getBackupInformation() {
        return null;
    }

    @Override
    public void exportBackup() {
        iteractor.exportRealmDB(this);
    }

    @Override
    public void importBackup() {
        iteractor.importRealmDB(null, this);
    }

    @Override
    public void importBackupFromPath(String path) {
        iteractor.importRealmDB(path, this);
    }

    @Override
    public void onPropertiesDialog() {
        DialogProperties properties = new DialogProperties();
        properties.selection_mode = DialogConfigs.SINGLE_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT;
        properties.root = new File(DialogConfigs.DEFAULT_DIR);
        properties.extensions = null;
        if (view != null)
            view.onCreateDialogPicker(properties);
    }


    @Override
    public void onExportDataBase(String result) {
        if (view != null)
            view.onShowShackBar(result);
    }

    @Override
    public void onImportDataBase(String result) {
        if (view != null)
            view.onRestartApplication();
    }

    @Override
    public void onEmptyDataBase() {
        if (view != null)
            view.onShowShackBar(context.getResources().getString(R.string.empty_database));
    }
}
