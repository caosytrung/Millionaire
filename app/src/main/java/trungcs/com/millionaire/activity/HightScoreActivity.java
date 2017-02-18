package trungcs.com.millionaire.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import trungcs.com.millionaire.R;
import trungcs.com.millionaire.models.ItemHC;
import trungcs.com.millionaire.models.ManagerHC;

/**
 * Created by caotr on 06/09/2016.
 */
public class HightScoreActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hight_score);
        ListView listView = (ListView) findViewById(R.id.lv_hight_score);
        ArrayList<String> arrayList = new ArrayList<>();
        ManagerHC managerHC = new ManagerHC(this);
        List<ItemHC> itemHCs = managerHC.getListLopHoc();
        for (int i = 0 ; i < itemHCs.size() ; i++){
            arrayList.add(itemHCs.get(i).getTen() + " : " + itemHCs.get(i).getDiem());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arrayList
        );
        listView.setAdapter(adapter);
    }



}
