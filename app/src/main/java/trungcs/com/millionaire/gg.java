package trungcs.com.millionaire;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by caotr on 30/08/2016.
 */
public class gg extends Dialog {

    public gg(Context context) {
        super(context);
    }

    public gg(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected gg(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
