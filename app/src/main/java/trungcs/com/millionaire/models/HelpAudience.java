package trungcs.com.millionaire.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by caotr on 31/08/2016.
 */
public class HelpAudience {
    private int level;
    private int case1,case2,case3,case4;
    private int trucase;
    private List<Integer> list;

    public HelpAudience(int level, int case1, int case2, int case3, int case4, int trucase) {
        this.level = level;
        this.case1 = case1;
        this.case2 = case2;
        this.case3 = case3;
        this.case4 = case4;
        this.trucase = trucase;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCase1() {
        return case1;
    }

    public void setCase1(int case1) {
        this.case1 = case1;
    }

    public int getCase2() {
        return case2;
    }

    public void setCase2(int case2) {
        this.case2 = case2;
    }

    public int getCase3() {
        return case3;
    }

    public void setCase3(int case3) {
        this.case3 = case3;
    }

    public int getCase4() {
        return case4;
    }

    public void setCase4(int case4) {
        this.case4 = case4;
    }

    public int getTrucase() {
        return trucase;
    }

    public void setTrucase(int trucase) {
        this.trucase = trucase;
    }

    public void result(int casetrue,int case1,int case2,int case3,int level){
        Random random = new Random();
        int khoang = level * 10;
        if(level >= 9){
            khoang =80;
        }
        casetrue = random.nextInt(101) - random.nextInt(khoang);
        case1 = random.nextInt(101 - casetrue);
        case2 = random.nextInt(101 - casetrue -case1);
        case3 = random.nextInt(101 - casetrue - case1 - case2);
        Log.d("aaa",case1 + "");
    }
    public List<Integer> computer(){
        list = new ArrayList<>();

        if(trucase == 1){
            result(case1,case2,case3,case4,level);
        }
        else {
            if(trucase == 2){
                result(case2,case1,case3,case4,level);
            }
            else {
                if(trucase == 3){
                    result(case3,case1,case2,case4,level);
                }
                else {
                    result(case3,case1,case2,case4,level);
                }
            }
        }
        Log.d("xxx",case1 + "");
        list.add(case1);
        list.add(case2);
        list.add(case3);
        list.add(case4);

        return list;
    }
}
