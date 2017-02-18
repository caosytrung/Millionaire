package trungcs.com.millionaire.models;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by caotr on 11/09/2016.
 */
public class ManagerHC {
    private static final String PATH_ALTP =
            Environment.getDataDirectory() +
                    File.separator +
                    "data" +
                    File.separator +
                    "trungcs.com.millionaire" +
                    File.separator +
                    "database";
    private static final String NAME_DATA = "diemcao";
    private static final String TAG = ManagerHC.class.getSimpleName();
    private Context mContext;
    private SQLiteDatabase mSqlite;

    public ManagerHC(Context context) {
        mContext = context;
        coppyDataBase();
    }

    private void coppyDataBase() {
        try {
            new File(PATH_ALTP).mkdir();
            File fileName =
                    new File(PATH_ALTP +
                            File.separator +
                            NAME_DATA);
            if (fileName.exists()) {
                return;
            }
            fileName.createNewFile();
            AssetManager manager = mContext.getAssets();
            InputStream in = manager.open("diemcao");
            OutputStream out = new FileOutputStream(fileName);
            byte[] b = new byte[1024];
            int le = in.read(b);
            while (le > 0) {
                out.write(b, 0, le);
                le = in.read(b);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDataBase() {
        if (mSqlite == null || mSqlite.isOpen() == false) {
            mSqlite = SQLiteDatabase
                    .openDatabase(
                            PATH_ALTP + File.separator + NAME_DATA,
                            null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    public void closeDatabase() {
        if (mSqlite != null) {
            mSqlite.close();
            mSqlite = null;
        }
    }
    public ArrayList<ItemHC> getListLopHoc() {
        ArrayList<ItemHC> listLopHoc = new ArrayList<>();
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = mSqlite.rawQuery("SELECT * FROM diemcao", null
                    );
            ItemHC lopHoc;
            while (cs.moveToNext()) {
                lopHoc = new ItemHC(cs.getString(0), cs.getInt(1));
                listLopHoc.add(lopHoc);
            }
        } finally {
            mSqlite.close();
        }

        return listLopHoc;
    }



}
