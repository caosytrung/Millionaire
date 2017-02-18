package trungcs.com.millionaire.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import trungcs.com.millionaire.ManagerMedia;
import trungcs.com.millionaire.dialogandtoas.DialogInformation;
import trungcs.com.millionaire.R;
import trungcs.com.millionaire.models.TypeFaces;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener, View.OnClickListener {
    private Button mBtnStart;
    private Button mBtnHightScore;
    private Button mBtnGuideGame;
    private Button mBtnInformation;
    private Animation mAniRightToLeft;
    private Animation mAniRightToLeft1;
    private Animation mAnoLeftToRight;
    private Animation mAnoLeftToRight1;
    private ImageView mImgVolume;
    private boolean mIsTurnOnVolume;
    private DialogInformation mDialogInformation;
    private ManagerMedia managerMedia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAni();
        initViews();
        initComons();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        managerMedia.openMedia(R.raw.bgmusic,false);
        managerMedia.play();
    }

    private void initComons() {
        mIsTurnOnVolume = false;
        mDialogInformation = new DialogInformation(this);
        managerMedia = new ManagerMedia(this);
        managerMedia.openMedia(R.raw.bgmusic,false);
        managerMedia.play();
    }

    private void initViews() {
        mBtnStart = (Button)
                findViewById(R.id.btn_start);
        mBtnHightScore = (Button)
                findViewById(R.id.btn_hightscore);
        mBtnGuideGame = (Button)
                findViewById(R.id.btn_guide_game);
        mBtnInformation = (Button)
                findViewById(R.id.btn_information);
        mImgVolume = (ImageView)
                findViewById(R.id.img_volume);

        Typeface typeface = TypeFaces.getTF1(this);
        mBtnStart.setTypeface(typeface);
        mBtnHightScore.setTypeface(typeface);
        mBtnGuideGame.setTypeface(typeface);
        mBtnInformation.setTypeface(typeface);


        TableRow tbr1= (TableRow)
                findViewById(R.id.table_1);
        TableRow tbr2= (TableRow)
                findViewById(R.id.table_2);
        TableRow tbr3= (TableRow)
                findViewById(R.id.table_3);
        TableRow tbr4= (TableRow)
                findViewById(R.id.table_4);


        tbr1.
                setAnimation(mAniRightToLeft);
        tbr2.
                setAnimation(mAnoLeftToRight);
        tbr3.
                setAnimation(mAniRightToLeft1);
        tbr4.
                setAnimation(mAnoLeftToRight1);
        mAniRightToLeft1.setAnimationListener(this);
        mAniRightToLeft.setAnimationListener(this);
        mAnoLeftToRight1.setAnimationListener(this);
        mAnoLeftToRight.setAnimationListener(this);

        mAniRightToLeft.start();
        mAnoLeftToRight.start();
        mAniRightToLeft1.start();
        mAnoLeftToRight1.start();
        mBtnStart.setOnClickListener(this);
        mBtnGuideGame.setOnClickListener(this);
        mBtnHightScore.setOnClickListener(this);
        mBtnInformation.setOnClickListener(this);
        mImgVolume.setOnClickListener(this);



    }

    private void initAni() {
        mAniRightToLeft = AnimationUtils.
                loadAnimation(this,
                        R.anim.ani_menu_right_to_left);
        mAnoLeftToRight = AnimationUtils.
                loadAnimation(this,
                        R.anim.ani_menu_left_to_right);
        mAnoLeftToRight1 = AnimationUtils.
                loadAnimation(this,
                        R.anim.ani_menu_ltr1);
        mAniRightToLeft1 = AnimationUtils.
                loadAnimation(this,
                        R.anim.ani_menu_rtl1);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                managerMedia.stop();
                startActivity(new Intent(this, ControlGameActivity.class));
                break;
            case R.id.btn_hightscore:
                managerMedia.stop();
                startActivity(new Intent(this,HightScoreActivity.class));
                break;
            case R.id.btn_guide_game:
                managerMedia.stop();
                break;
            case R.id.btn_information :
                mDialogInformation.show();
                break;
            case R.id.img_volume:
                if(false == mIsTurnOnVolume){
                    managerMedia.pause();
                    mImgVolume.setImageLevel(1);
                }
                else {
                    managerMedia.play();
                    mImgVolume.setImageLevel(0);
                }
                TextView textView = new TextView(this);
                mIsTurnOnVolume = !mIsTurnOnVolume;
                break;
            default:
                break;
        }
    }


}
