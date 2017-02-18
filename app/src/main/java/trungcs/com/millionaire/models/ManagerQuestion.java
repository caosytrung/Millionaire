package trungcs.com.millionaire.models;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caotr on 28/08/2016.
 */
public class ManagerQuestion {
    private static final String PATH_ALTP =
            Environment.getDataDirectory() +
                    File.separator +
                    "data" +
                    File.separator +
                    "trungcs.com.millionaire" +
                    File.separator +
                    "database";
    private static final String NAME_DATA = "Question";
    private static final String TAG = ManagerQuestion.class.getSimpleName();
    private Context mContext;
    private SQLiteDatabase mSqlite;

    public ManagerQuestion(Context context) {
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
            InputStream in = manager.open("Question");
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

    public List<ItemQuestion> get15Question() {
        openDataBase();
        List<ItemQuestion> questions = new ArrayList<>();
        String sqlQuery15Question =
                "SELECT * FROM " +
                        "( SELECT * FROM Question ORDER BY random() ) " +
                        "GROUP BY level ORDER BY level ASC limit 15";
        Cursor c = mSqlite.rawQuery(sqlQuery15Question, null);
        if (c == null) {
            return questions;
        }
        c.moveToFirst();
        int indexQuestion = c.getColumnIndex("question");
        int indexLevel = c.getColumnIndex("level");
        int indexCaseA = c.getColumnIndex("casea");
        int indexCaseB = c.getColumnIndex("caseb");
        int indexCaseC = c.getColumnIndex("casec");
        int indexCaseD = c.getColumnIndex("cased");
        int indexTrueCase = c.getColumnIndex("truecase");

        while (!c.isAfterLast()) {
            String questionSrt = c.getString(indexQuestion);
            String caseA = c.getString(indexCaseA);
            String caseB = c.getString(indexCaseB);
            String caseC = c.getString(indexCaseC);
            String caseD = c.getString(indexCaseD);
            int trueCase = Integer
                    .parseInt(c.getString(indexTrueCase));
            int level = c.getInt(indexLevel);
            ItemQuestion question = new ItemQuestion(level, caseA, caseB,
                    caseC, caseD, trueCase, questionSrt);
            questions.add(question);
            c.moveToNext();
        }
        for ( ItemQuestion question : questions ) {
            Log.d(TAG, "get15Question level: "
                    + question.getLevel());
            Log.d(TAG, "get15Question question: "
                    + question.getContentQuestion());
            Log.d(TAG, "get15Question caseA: "
                    + question.getCaseA());
            Log.d(TAG, "get15Question caseB: "
                    + question.getCaseB());
            Log.d(TAG, "get15Question caseC: "
                    + question.getCaseC());
            Log.d(TAG, "get15Question caseD: "
                    + question.getCaseD());
            Log.d(TAG, "get15Question trueCase: "
                    + question.getTrueCase());
            Log.d(TAG, "------------------------------------------");
        }
        return questions;
    }

}
