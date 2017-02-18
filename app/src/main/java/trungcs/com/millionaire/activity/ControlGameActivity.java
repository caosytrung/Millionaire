package trungcs.com.millionaire.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trungcs.com.millionaire.ManagerMedia;
import trungcs.com.millionaire.R;
import trungcs.com.millionaire.fragment.LevelFragment;
import trungcs.com.millionaire.fragment.QuestionFragment;
import trungcs.com.millionaire.interfacecallback.IHelp;
import trungcs.com.millionaire.interfacecallback.ISentLevel;
import trungcs.com.millionaire.interfacecallback.ISentResult;
import trungcs.com.millionaire.interfacecallback.IStopThread;
import trungcs.com.millionaire.models.ItemQuestion;
import trungcs.com.millionaire.models.ManagerQuestion;

/**
 * Created by caotr on 26/08/2016.
 */
public class ControlGameActivity extends AppCompatActivity
        implements View.OnClickListener,
        ISentLevel,ISentResult,IStopThread,Runnable {
    private static final int SENT_TIME =  1 ;
    private static final String TAG = "ControlGameActivity";
    private QuestionFragment mQuestionFragment;
    private LevelFragment mLevleQuestion;
    private ManagerQuestion mManagerQuestion;
    private static final String LEVEL = "level";
    public int level = 1;
    private List<ItemQuestion> itemQuestions;
    public static final String SENT_OBJECT = "put";
    private TextView mTvTime;
    private TextView mTvScore;
    private Handler mHandler;
    private Message mMessage;
    private boolean isStop;
    private Map<String,String> mapScore;
    private ManagerMedia mManagerMedia;


    private IHelp mIHelp;
    private TextView tvCaseA,tvCaseB;
    private LevelFragment mLevleQuestion1;

    public void setmIHelp(IHelp mIHelp) {
        this.mIHelp = mIHelp;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mManagerQuestion.closeDatabase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controler);
        mManagerMedia = new ManagerMedia(this);
        mManagerMedia .openMedia(R.raw.background_music, true);

        initViews();
        initCommons();
        initFragment();

    }
    private void startThread(){
        Thread thread = new Thread(this);
        thread.start();
    }

    private void initCommons() {
        mManagerQuestion = new ManagerQuestion(this);
        itemQuestions= mManagerQuestion.get15Question();
        isStop = false;
        mapScore = new HashMap<>();
        mapScore.put("1","500,000");
        mapScore.put("2","1,000,000");
        mapScore.put("3","2,000,000");
        mapScore.put("4","3,000,000");
        mapScore.put("5","5,000,000");
        mapScore.put("6","10,000,000");
        mapScore.put("7","18,000,000");
        mapScore.put("8","27,000,000");
        mapScore.put("9","35,000,000");
        mapScore.put("10","50,000,000");
        mapScore.put("11","62,000,000");
        mapScore.put("12","75,000,000");
        mapScore.put("13","90,00,000");
        mapScore.put("14","120,000,000");
        mapScore.put("15","150,000,000");



        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case SENT_TIME:
                        int time = msg.arg1;
                        mTvTime.setText(time + "");
                        if(time== 0){

                            AlertDialog.Builder dialog = new AlertDialog.Builder(ControlGameActivity.this).setTitle("Thông báo").
                                    setMessage("Hết time ,Bạn có muốn chơi lại không").
                                    setPositiveButton("Có ", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    }).
                                    setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            System.exit(0);
                                        }
                                    });
                            dialog.setCancelable(false);
                            dialog.show();
                        }

                        break;
                }
            }
        };
    }

    private void initViews() {
        mTvScore = (TextView) findViewById(R.id.tv_score);
        mTvTime = (TextView) findViewById(R.id.tv_time_play);




        tvCaseA = (TextView) findViewById(R.id.btn_case_a);
        tvCaseB = (TextView) findViewById(R.id.btn_case_b);

    }

    private void initFragment() {
        mQuestionFragment = new QuestionFragment(this);
        mQuestionFragment.setiSentResult(this);
        mQuestionFragment.setiStopThread(this);
        mLevleQuestion = new LevelFragment();
        mLevleQuestion.setmISentLevel(this);
        getSupportFragmentManager().
                beginTransaction().
                setCustomAnimations(
                        R.anim.open,
                        R.anim.exit
                ).
                add(R.id.content_fragment,
                        mLevleQuestion)
                .commit();
        Bundle bundle = new Bundle();
        bundle.putInt(LEVEL,level);
        mLevleQuestion.setArguments(bundle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_stop:
                break;
            case R.id.iv_help_50 :
                mIHelp.helpDeleteTwoWrongResult();
                break;
            case R.id.iv_help_restart:
                break;
            case R.id.iv_help_ask_kg:
                break;
            case R.id.iv_help_call:
                break;
            default:
                break;
        }
    }

    private void showFragmentLevel(){
        getSupportFragmentManager().
                beginTransaction().
                setCustomAnimations(
                        R.anim.open,
                        R.anim.exit
                ).
                replace(R.id.content_fragment,
                        mLevleQuestion1,
                        LevelFragment.
                                class.getName()).
                commit();
    }
    private void showFragmentQuestion(){

        getSupportFragmentManager().
                beginTransaction().
                setCustomAnimations(
                        R.anim.open,
                        R.anim.exit
                ).
                replace(R.id.content_fragment,
                        mQuestionFragment,
                        QuestionFragment.
                                class.getName()).
                commitAllowingStateLoss();
    }


    @Override
    public void sendAndFinis(int level) {
        mManagerMedia.play();
        isStop= false;
        startThread();
        ItemQuestion itemQuestion = itemQuestions.get(level);
        getIntent().putExtra(SENT_OBJECT, itemQuestion);
        showFragmentQuestion();

    }

    @Override
    public void sentResult(boolean result) {
        if(result == true){
            mTvScore.setText(mapScore.get(level+""));
            level ++;
            mTvScore.setText(mapScore.get(level+""));
            Bundle bundle = new Bundle();
            bundle.putInt(LEVEL,level);
            mLevleQuestion.setArguments(bundle);
            showFragmentLevel();
        }
        else {
            mManagerMedia.stop();
            finish();
        }
    }

    @Override
    public void stop() {
        isStop = true;
    }

    @Override
    public void run() {
        for(int i = 20 ; i >= 0 ; i --){
            Log.d(TAG,"zzzzzzzzzzzz" + i);
            mMessage = new Message();
            mMessage.what = SENT_TIME;
            mMessage.arg1 = i;
            mMessage.setTarget(mHandler);
            mMessage.sendToTarget();
            if (isStop) {
                break;
            }

            SystemClock.sleep(1000);

        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }

}
