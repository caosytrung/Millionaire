            package trungcs.com.millionaire.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import trungcs.com.millionaire.ManagerMedia;
import trungcs.com.millionaire.R;
import trungcs.com.millionaire.interfacecallback.ISentLevel;

/**
 * Created by caotr on 28/08/2016.
 */
public class LevelFragment extends Fragment  implements  Runnable{
    private static final String TAG = "LevelFragment";
    private List<TextView> textViewQuestions;
    private static final String LEVEL = "level";
    private int mLevel;
    private Message mMessage;
    private int mColorDefault;
    private boolean isStart;
    private Handler mHandler;
    private ISentLevel mISentLevel;
    public void setmISentLevel(ISentLevel iSentLevel){
        mISentLevel = iSentLevel;
    }
    private List<Integer> listSong;

    private void initCommons(){
        isStart = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_level,
                container,false
        );
        listSong = new ArrayList<>();
        listSong.add(R.raw.ques1);
        listSong.add(R.raw.ques2_b);
        listSong.add(R.raw.ques3_b);
        listSong.add(R.raw.ques4_b);
        listSong.add(R.raw.ques5_b);
        listSong.add(R.raw.ques6);
        listSong.add(R.raw.ques7_b);
        listSong.add(R.raw.ques8_b);
        listSong.add(R.raw.ques9_b);
        listSong.add(R.raw.ques10);

        textViewQuestions = new ArrayList<>();
        textViewQuestions.add((TextView) view.findViewById(R.id.item_question_1));
        textViewQuestions.add((TextView) view.findViewById(R.id.item_question_2));
        textViewQuestions.add((TextView) view.findViewById(R.id.item_question_3));
        textViewQuestions.add((TextView) view.findViewById(R.id.item_question_4));
        textViewQuestions.add((TextView) view.findViewById(R.id.item_question_5));
        textViewQuestions.add((TextView) view.findViewById(R.id.item_question_6));
        textViewQuestions.add((TextView) view.findViewById(R.id.item_question_7));
        textViewQuestions.add((TextView) view.findViewById(R.id.item_question_8));
        textViewQuestions.add((TextView) view.findViewById(R.id.item_question_9));
        textViewQuestions.add((TextView) view.findViewById(R.id.item_question_10));
        textViewQuestions.add((TextView) view.findViewById(R.id.item_question_11));
        textViewQuestions.add((TextView) view.findViewById(R.id.item_question_12));
        textViewQuestions.add((TextView) view.findViewById(R.id.item_question_13));
        textViewQuestions.add((TextView) view.findViewById(R.id.item_question_14));
        textViewQuestions.add((TextView) view.findViewById(R.id.item_question_15));
        Drawable drawable = textViewQuestions.get(0).getBackground();
        Bundle bundle = this.getArguments();

        mLevel = getArguments().getInt(LEVEL,-1);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"zzzzzzzzzzzzzzzzzzz" + mLevel);
        if(mLevel == 1){
            isStart = true;

        }
        else {
            isStart = false;
        }
        startThread();
        initHandler();

    }

    private void initHandler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case START:
                        int level = msg.arg1;
                        switch (level){
                            case 0 :
                               ManagerMedia m = new ManagerMedia(getContext());
                                m.openMedia(R.raw.luatchoi_c,false);
                                m.play();
                                break;
                            case 2:
                                textViewQuestions.get(4).
                                        setBackgroundColor(Color.parseColor("#76FF03"));
                               // textViewQuestions.get(0).invalidate();
                                break;
                            case 3:
                                textViewQuestions.get(4).
                                        setBackgroundColor(Color.parseColor("#00000000"));
                               textViewQuestions.get(9).
                                       setBackgroundColor(Color.parseColor("#76FF03"));
                                break;
                            case 4:
                                textViewQuestions.get(9).
                                        setBackgroundColor(Color.parseColor("#00000000"));
                                textViewQuestions.get(14).
                                        setBackgroundColor(Color.parseColor("#76FF03"));
                                break;
                            case 5:
                                textViewQuestions.get(14).
                                        setBackgroundColor(Color.parseColor("#00000000"));
                                textViewQuestions.get(mLevel - 1).
                                        setBackgroundResource
                                                (R.drawable.bg_green);
                                ManagerMedia managerMedia = new ManagerMedia(getContext());
                                managerMedia.openMedia(listSong.get(0),false);
                                managerMedia.play();
                                break;
                            case 6:
                                mISentLevel.sendAndFinis(mLevel -1);
                                break;
                            default:
                                break;
                        }
                        break;
                    case PLAY:
                        int pos = msg.arg1;
                        switch (pos){
                            case 0 :
                                textViewQuestions.get(mLevel -2).
                                        setBackgroundColor(Color.parseColor("#76FF03"));

                                break;
                            case 2:
                                ManagerMedia managerMedia = new ManagerMedia(getContext());
                                managerMedia.openMedia(listSong.get(mLevel - 1),false);
                                managerMedia.play();
                                textViewQuestions.get(mLevel -2).
                                        setBackgroundColor(Color.parseColor("#00000000"));
                                textViewQuestions.get(mLevel -1).
                                        setBackgroundColor(Color.parseColor("#76FF03"));
                                break;

                            case 3:
                                mISentLevel.sendAndFinis(mLevel -1);
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
        };
    }


    private void startThread(){
        Thread thread = new Thread(this);
        thread.start();
    }

    public static final int START = 1;
    public static final int PLAY = 2;
    @Override
    public void run() {
        if(isStart){
            for (int i = 0 ; i < 7 ; i ++){
                mMessage = new Message();
                mMessage.what = START;
                mMessage.arg1 = i;
                mMessage.setTarget(mHandler);
                mMessage.sendToTarget();
                SystemClock.sleep(1000);
            }
        }
        else {
            for (int i = 0 ; i < 4; i ++){
                mMessage = new Message();
                mMessage.what = PLAY;
                mMessage.arg1 = i;
                mMessage.setTarget(mHandler);
                mMessage.sendToTarget();
                SystemClock.sleep(2000);
            }
        }

    }

}
