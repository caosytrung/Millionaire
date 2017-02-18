package trungcs.com.millionaire.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import trungcs.com.millionaire.ManagerMedia;
import trungcs.com.millionaire.R;
import trungcs.com.millionaire.interfacecallback.ISentResult;
import trungcs.com.millionaire.interfacecallback.IStopThread;
import trungcs.com.millionaire.models.ItemQuestion;
import trungcs.com.millionaire.models.ManagerQuestion;

/**
 * Created by caotr on 27/08/2016.
 */
public class QuestionFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "QuestionFragment";
    private View.OnClickListener mOnClickListener;
    private static final String LEVEL = "level";
    private TextView mTvLevel;
    private TextView mTvQuestion;
    private TextView tvCaseA;
    private TextView tvCaseB;
    private TextView tvCaseC;
    private TextView tvCaseD;
    private ImageView mTvStop;
    private ImageView mTvCall;
    private ImageView mTvDeleteCall;
    private ImageView mTv5050;
    private ImageView mTvDelete5050;
    private ImageView mTvAskKhanGia;
    private ImageView mTvDeleteAsk;
    private ImageView mTvSwapQuestion;
    private ImageView mTvDeleteSwap;
    private ItemQuestion mItemQuestion;
    public static final String SENT_OBJECT = "put";
    private int mResult;
    private ISentResult iSentResult;
    private IStopThread iStopThread;
    private LinearLayout mLnFragQuestion;
    private List<View> views;
    private List<View> helps;
    private List<Integer> listAnserTrue;
    private List<Integer> listAnserFalse;



    public void setiStopThread(IStopThread iStopThread){
        this.iStopThread = iStopThread;
    }

    public ISentResult getiSentResult() {
        return iSentResult;
    }

    public void setiSentResult(ISentResult iSentResult) {
        this.iSentResult = iSentResult;
    }

    public QuestionFragment(View.OnClickListener onClickListener){
        mOnClickListener = onClickListener;

    }
    private HashMap<String,TextView> hashMap;

    private TextView mBtnCaseA;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.
                inflate(R.layout.fragment_question, container, false);


        getActivity().findViewById(R.id.iv_help_50).setOnClickListener(this);

        initView(view);
        Intent intent = getActivity().getIntent();
        mItemQuestion = (ItemQuestion)
                intent.getSerializableExtra(SENT_OBJECT);

        tvCaseA.setText(mItemQuestion.getCaseA());
        tvCaseB.setText(mItemQuestion.getCaseB());
        tvCaseC.setText(mItemQuestion.getCaseC());
        tvCaseD.setText(mItemQuestion.getCaseD());
        mTvLevel.setText("Câu số " + mItemQuestion.getLevel());
        mTvQuestion.setText(mItemQuestion.getContentQuestion());
        hashMap = new HashMap<>();
        hashMap.put("1", tvCaseA);
        hashMap.put("2", tvCaseB);
        hashMap.put("3", tvCaseC);
        hashMap.put("4", tvCaseD);
        listAnserTrue = new ArrayList<>();
        listAnserTrue.add(R.raw.true_a);
        listAnserTrue.add(R.raw.true_b);
        listAnserTrue.add(R.raw.true_c);
        listAnserTrue.add(R.raw.true_d2);
        listAnserFalse = new ArrayList<>();
        listAnserFalse.add(R.raw.lose_a);
        listAnserFalse.add(R.raw.lose_b);
        listAnserFalse.add(R.raw.lose_c);
        listAnserFalse.add(R.raw.lose_d);



        return view;
    }

    private void initView(View v){
        mTvLevel = (TextView) v.findViewById(R.id.tv_so_thu_tu);
        mTvQuestion = (TextView) v.findViewById(R.id.tv_quetion);
        tvCaseA = (TextView) v.findViewById(R.id.btn_case_a);
        tvCaseB = (TextView) v.findViewById(R.id.btn_case_b);
        tvCaseC = (TextView) v.findViewById(R.id.btn_case_c);
        tvCaseD = (TextView) v.findViewById(R.id.btn_case_d);

        mTvStop = (ImageView) getActivity().findViewById(R.id.tv_stop);

        mTvCall = (ImageView) getActivity().findViewById(R.id.iv_help_call);
        mTvDeleteCall = (ImageView) getActivity().findViewById(R.id.tv_turn_off_call);

        mTv5050 = (ImageView) getActivity().findViewById(R.id.iv_help_50);
        mTvDelete5050 = (ImageView) getActivity().findViewById(R.id.iv_turn_off_50);

        mTvAskKhanGia = (ImageView) getActivity().findViewById(R.id.iv_help_ask_kg);
        mTvDeleteAsk = (ImageView) getActivity().findViewById(R.id.iv_turn_off_ask);

        mTvSwapQuestion = (ImageView) getActivity().findViewById(R.id.iv_help_restart);
        mTvDeleteSwap = (ImageView) getActivity().findViewById(R.id.iv_turn_off_restart);

        mTvStop.setOnClickListener(this);
        mTvCall.setOnClickListener(this);
        mTvAskKhanGia.setOnClickListener(this);
        mTv5050.setOnClickListener(this);
        mTvSwapQuestion.setOnClickListener(this);


        mLnFragQuestion = (LinearLayout)
                v.findViewById(R.id.ln_frag_question);


        tvCaseA.setOnClickListener(this);
        tvCaseB.setOnClickListener(this);
        tvCaseC.setOnClickListener(this);
        tvCaseD.setOnClickListener(this);

        helps = new ArrayList<>();

        views = new ArrayList<>();
        views.add(tvCaseA);
        views.add(tvCaseB);
        views.add(tvCaseC);
        views.add(tvCaseD);

    }
    private void enableView(){
        for(int i= 0 ; i < views.size(); i ++){
            views.get(i).setEnabled(false);
        }
    }


    HashMap<String,Integer> mapsss = new HashMap<>();


    public void result(int casetrue,int level){
        Log.d(TAG,casetrue + " " + level);

        Random random = new Random();
        int ct;
       if(level >= 0 && level <= 5){
           ct = random.nextInt(31) + 60;
       }
        else if(level >= 6 && level < 10 ){
           ct = random.nextInt(30) + 40;
       }
        else {
           ct = random.nextInt(30) +30;
       }

        int cas1,cas2,cas3;
        cas1 = random.nextInt(101 - ct);
        cas2 = random.nextInt(101 - ct -cas1);
        cas3 = 100 - ct - cas1 - cas2;
        if(casetrue == 1){
            mapsss.put("1",ct);
            mapsss.put("2",cas1);
            mapsss.put("3",cas2);
            mapsss.put("4",cas3);
        }
        else {
            if(casetrue == 2){
                mapsss.put("2",ct);
                mapsss.put("1",cas1);
                mapsss.put("3",cas2);
                mapsss.put("4",cas3);
            }
            else {
                if(casetrue == 3){
                    mapsss.put("3",ct);
                    mapsss.put("1",cas1);
                    mapsss.put("2",cas2);
                    mapsss.put("4",cas3);
                }
                else {
                    mapsss.put("4",ct);
                    mapsss.put("1",cas1);
                    mapsss.put("2",cas2);
                    mapsss.put("3",cas3);
                }
            }
        }
        Log.d(TAG,mapsss.get("1") + " " + mapsss.get("2") + " " + mapsss.get(3) + " " + mapsss.get("4") );

    }

    @Override
    public void onClick(View v) {
        mResult = mItemQuestion.getTrueCase();

        switch (v.getId()) {

            case R.id.iv_help_call:
                mTvCall.setEnabled(false);
                mTvDeleteCall.setVisibility(View.VISIBLE);
                Dialog dialog1 = new Dialog(getContext());
                dialog1.setContentView(R.layout.custom_dialog_call);
                dialog1.setTitle("zzzzzzzzzzzzzzzzzzzz");
                final TextView textView = (TextView) dialog1.findViewById(R.id.tv_answer_call);
                dialog1.findViewById(R.id.cc1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setVisibility(View.VISIBLE);
                        textView.setText("Toi nghi la A");
                    }
                });
                dialog1.findViewById(R.id.cc2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setVisibility(View.VISIBLE);
                        textView.setText("Toi nghi la B");
                    }
                });
                dialog1.findViewById(R.id.cc3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {textView.setVisibility(View.VISIBLE);

                        textView.setText("Toi nghi la D");
                    }
                });
                dialog1.show();
                break;

            case R.id.iv_help_ask_kg:
                mTvAskKhanGia.setEnabled(false);
                mTvDeleteAsk.setVisibility(View.VISIBLE);
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.custom_answer);
                dialog.setTitle("zzzzzzzzzzzzzzzzzzzz");
                final TextView tv1 = (TextView) dialog.findViewById(R.id.iv_persen0);
                final TextView tv2 = (TextView) dialog.findViewById(R.id.iv_persen1);
                final TextView tv3 = (TextView) dialog.findViewById(R.id.iv_persen2);
                final TextView tv4 = (TextView) dialog.findViewById(R.id.iv_persen3);
                TextView tvPer1 = (TextView) dialog.findViewById(R.id.per1);
                TextView tvPer2 = (TextView) dialog.findViewById(R.id.per2);
                TextView tvPer3 = (TextView) dialog.findViewById(R.id.per3);
                TextView tvPer4 = (TextView) dialog.findViewById(R.id.per4);

                final LinearLayout layout0 =
                        (LinearLayout) dialog.findViewById(R.id.ln_persen0);
                final LinearLayout layout1 =
                        (LinearLayout) dialog.findViewById(R.id.ln_persen0);
                final LinearLayout layout2 =
                        (LinearLayout) dialog.findViewById(R.id.ln_persen0);
                final LinearLayout layout3 =
                        (LinearLayout) dialog.findViewById(R.id.ln_persen0);

                int tc = mItemQuestion.getTrueCase();
                int lev = mItemQuestion.getLevel();

                result(tc, lev);

                layout0.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int wid = layout0.getWidth();
                        int hei = layout0.getHeight();
                        tv1.setWidth(wid);
                        tv1.setHeight((mapsss.get("1") * hei / 100));
                    }
                });
                layout1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int wid = layout1.getWidth();
                        int hei = layout1.getHeight();
                        tv2.setWidth(wid);
                        tv2.setHeight((mapsss.get("2") * hei / 100));
                    }
                });
                layout2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int wid = layout2.getWidth();
                        int hei = layout2.getHeight();
                        tv3.setWidth(wid);
                        tv3.setHeight((mapsss.get("3") * hei / 100));
                    }
                });
                layout3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int wid = layout3.getWidth();
                        int hei = layout3.getHeight();
                        tv4.setWidth(wid);
                        tv4.setHeight((mapsss.get("4") * hei / 100));
                    }
                });


                tvPer1.setText(mapsss.get("1") + "%");
                tvPer2.setText(mapsss.get("2") + "%");
                tvPer3.setText(mapsss.get("3") + "%");
                tvPer4.setText(mapsss.get("4") + "%");

                dialog.show();
                break;

            case R.id.iv_help_50:
                mTvDelete5050.setVisibility(View.VISIBLE);
                mTv5050.setEnabled(false);

                int count = 0;
                int save = -1;
                while (count < 2) {
                    Random random = new Random();
                    int ran = random.nextInt(4) + 1;
                    if (ran != mResult && save != ran) {
                        views.get(ran - 1).setVisibility(View.INVISIBLE);
                        count++;
                        save = ran;
                    }
                }

                break;

            case R.id.iv_help_restart:
                mTvSwapQuestion.setEnabled(false);
                mTvDeleteSwap.setVisibility(View.VISIBLE);

                ManagerQuestion managerQuestion = new ManagerQuestion(getActivity());
                ItemQuestion itemQuestion = managerQuestion.get15Question().get(mItemQuestion.getLevel() - 1);
                tvCaseA.setText(itemQuestion.getCaseA());
                tvCaseB.setText(itemQuestion.getCaseB());
                tvCaseC.setText(itemQuestion.getCaseC());
                tvCaseD.setText(itemQuestion.getCaseD());
                mTvLevel.setText("Câu " + itemQuestion.getLevel());
                mTvQuestion.setText(itemQuestion.getContentQuestion());
                break;
            case R.id.btn_case_a:

                ManagerMedia managerMedia = new ManagerMedia(getContext());
                managerMedia.openMedia(R.raw.ans_a2,false);
                managerMedia.play();
                iStopThread.stop();
                enableView();
                tvCaseA.setBackgroundResource(R.drawable.press_ques);

                new CountDownTimer(5000, 5000) {
                    @Override
                    public void onFinish() {
                        if (mResult == 1) {
                            ManagerMedia managerMedia = new ManagerMedia(getContext());
                            managerMedia.openMedia(listAnserTrue.get(mResult - 1),false);
                            managerMedia.play();

                            new CountDownTimer(5000, 100) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    int tim = (int) (millisUntilFinished / 100);
                                    if (tim % 2 == 0) {
                                        tvCaseA.setBackgroundResource(R.drawable.true_question);
                                    } else {
                                        tvCaseA.setBackgroundResource(R.drawable.press_ques);
                                    }
                                }

                                @Override
                                public void onFinish() {

                                    iSentResult.sentResult(true);

                                }
                            }.start();

                        } else {
                            ManagerMedia managerMedia = new ManagerMedia(getContext());
                            managerMedia.openMedia(listAnserFalse.get(mResult- 1),false);
                            managerMedia.play();
                            new CountDownTimer(5000, 100) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    int time = (int) (millisUntilFinished / 100);
                                    if (time % 2 == 0) {
                                        tvCaseA.setBackgroundResource(R.drawable.bg_faile1);
                                        hashMap.get(mResult + "").setBackgroundResource(R.drawable.true_question);
                                    } else {
                                        tvCaseA.setBackgroundResource(R.drawable.press_ques);
                                        hashMap.get(mResult + "").setBackgroundResource(R.drawable.press_ques);

                                    }
                                }

                                @Override
                                public void onFinish() {

                                    iSentResult.sentResult(false);

                                }
                            }.start();


                        }
                    }

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }
                }.start();

                break;
            case R.id.btn_case_b:
                ManagerMedia managerMedia1 = new ManagerMedia(getContext());
                managerMedia1.openMedia(R.raw.ans_b2,false);
                managerMedia1.play();
                iStopThread.stop();
                enableView();
                tvCaseB.setBackgroundResource(R.drawable.press_ques);
                new CountDownTimer(5000, 5000) {
                    @Override
                    public void onFinish() {
                        if (mResult == 2) {
                            ManagerMedia managerMedia1 = new ManagerMedia(getContext());
                            managerMedia1.openMedia(listAnserTrue.get(mResult- 1),false);
                            managerMedia1.play();

                            new CountDownTimer(5000, 100) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    int tim = (int) (millisUntilFinished / 100);
                                    if (tim % 2 == 0) {
                                        tvCaseB.setBackgroundResource(R.drawable.true_question);
                                    } else {
                                        tvCaseB.setBackgroundResource(R.drawable.press_ques);
                                    }
                                }

                                @Override
                                public void onFinish() {

                                    iSentResult.sentResult(true);

                                }
                            }.start();

                        } else {
                            ManagerMedia managerMedia1 = new ManagerMedia(getContext());
                            managerMedia1.openMedia(listAnserFalse.get(mResult- 1),false);
                            managerMedia1.play();
                            new CountDownTimer(5000, 100) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    int time = (int) (millisUntilFinished / 100);
                                    if (time % 2 == 0) {
                                        tvCaseB.setBackgroundResource(R.drawable.bg_faile1);
                                        hashMap.get(mResult + "").setBackgroundResource(R.drawable.true_question);
                                    } else {
                                        tvCaseB.setBackgroundResource(R.drawable.press_ques);
                                        hashMap.get(mResult + "").setBackgroundResource(R.drawable.press_ques);

                                    }
                                }

                                @Override
                                public void onFinish() {

                                    iSentResult.sentResult(false);

                                }
                            }.start();


                        }
                    }

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }
                }.start();
                break;
            case R.id.btn_case_c:
                ManagerMedia managerMedia2 = new ManagerMedia(getContext());
                managerMedia2.openMedia(R.raw.ans_c2,false);
                managerMedia2.play();

                iStopThread.stop();
                enableView();
                tvCaseC.setBackgroundResource(R.drawable.press_ques);
                new CountDownTimer(5000, 5000) {
                    @Override
                    public void onFinish() {
                        if (mResult == 3) {
                            ManagerMedia managerMedia2 = new ManagerMedia(getContext());
                            managerMedia2.openMedia(listAnserTrue.get(mResult- 1),false);
                            managerMedia2.play();
                            new CountDownTimer(5000, 100) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    int tim = (int) (millisUntilFinished / 100);
                                    if (tim % 2 == 0) {
                                        tvCaseC.setBackgroundResource(R.drawable.true_question);
                                    } else {
                                        tvCaseC.setBackgroundResource(R.drawable.press_ques);
                                    }
                                }

                                @Override
                                public void onFinish() {

                                    iSentResult.sentResult(true);

                                }
                            }.start();

                        } else {
                            ManagerMedia managerMedia2 = new ManagerMedia(getContext());
                            managerMedia2.openMedia(listAnserFalse.get(mResult- 1),false);
                            managerMedia2.play();
                            new CountDownTimer(5000, 100) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    int time = (int) (millisUntilFinished / 100);
                                    if (time % 2 == 0) {
                                        tvCaseC.setBackgroundResource(R.drawable.bg_faile1);
                                        hashMap.get(mResult + "").setBackgroundResource(R.drawable.true_question);
                                    } else {
                                        tvCaseC.setBackgroundResource(R.drawable.press_ques);
                                        hashMap.get(mResult + "").setBackgroundResource(R.drawable.press_ques);

                                    }
                                }

                                @Override
                                public void onFinish() {

                                    iSentResult.sentResult(false);

                                }
                            }.start();
                        }
                    }

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }
                }.start();
                break;
            case R.id.btn_case_d:
                ManagerMedia managerMedia3 = new ManagerMedia(getContext());
                managerMedia3.openMedia(R.raw.ans_d2,false);
                managerMedia3.play();
                iStopThread.stop();
                enableView();
                tvCaseD.setBackgroundResource(R.drawable.press_ques);
                new CountDownTimer(5000, 5000) {
                    @Override
                    public void onFinish() {
                        if (mResult == 4) {
                            ManagerMedia managerMedia3 = new ManagerMedia(getContext());
                            managerMedia3.openMedia(listAnserTrue.get(mResult- 1),false);
                            managerMedia3.play();
                            new CountDownTimer(5000, 100) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    int tim = (int) (millisUntilFinished / 100);
                                    if (tim % 2 == 0) {
                                        tvCaseD.setBackgroundResource(R.drawable.true_question);
                                    } else {
                                        tvCaseD.setBackgroundResource(R.drawable.press_ques);
                                    }
                                }

                                @Override
                                public void onFinish() {

                                    iSentResult.sentResult(true);

                                }
                            }.start();

                        } else {
                            ManagerMedia managerMedia3 = new ManagerMedia(getContext());
                            managerMedia3.openMedia(listAnserFalse.get(mResult- 1),false);
                            managerMedia3.play();
                            new CountDownTimer(5000, 100) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    int time = (int) (millisUntilFinished / 100);
                                    if (time % 2 == 0) {
                                        tvCaseD.setBackgroundResource(R.drawable.bg_faile1);
                                        hashMap.get(mResult + "").setBackgroundResource(R.drawable.true_question);
                                    } else {
                                        tvCaseD.setBackgroundResource(R.drawable.press_ques);
                                        hashMap.get(mResult + "").setBackgroundResource(R.drawable.press_ques);

                                    }
                                }
                                @Override
                                public void onFinish() {
                                    iSentResult.sentResult(false);
                                }
                            }.start();

                        }
                    }
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }
                }.start();
                break;
            default:

                break;
        }

    }
}

