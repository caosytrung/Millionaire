package trungcs.com.millionaire.dialogandtoas;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;

import trungcs.com.millionaire.R;

/**
 * Created by caotr on 26/08/2016.
 */
public class DialogInformation extends Dialog implements View.OnClickListener {
    public DialogInformation(Context context) {
        super(context);
        initViews();

    }

    public DialogInformation(Context context, int themeResId) {
        super(context, themeResId);
        initViews();
    }
    public void initViews(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_inf);
        findViewById(R.id.btn_close_inf).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
