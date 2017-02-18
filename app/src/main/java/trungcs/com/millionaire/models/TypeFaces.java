package trungcs.com.millionaire.models;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by caotr on 26/08/2016.
 */
public class TypeFaces {
    private Context mContext;
    public TypeFaces(Context context){
        mContext = context;
    }

    public static Typeface getTF1(Context context){

        return (Typeface.createFromAsset(context.getAssets(),"font2.ttf"));
    }
}
