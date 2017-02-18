package trungcs.com.millionaire;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by caotr on 31/08/2016.
 */
public class ManagerMedia {
    private MediaPlayer mPlayer;
    private Context mContext;

    public ManagerMedia(Context mContext) {
        this.mContext = mContext;
    }

    public void openMedia(int id,boolean loop){
       release();
        mPlayer = MediaPlayer.create(mContext,id);
        if(loop){
            mPlayer.setLooping(true);
        }
    }

    public void play(){
        if(mPlayer != null && !mPlayer.isPlaying() ){
            mPlayer.start();
        }
    }

    public void release(){
        if (mPlayer != null){
            mPlayer.release();
        }
    }

    public void stop(){
        if(mPlayer != null && mPlayer.isPlaying()){
            mPlayer.stop();
        }
    }
    public void pause(){
        if(mPlayer != null && mPlayer.isPlaying()){
            mPlayer.pause();
        }
    }
}
