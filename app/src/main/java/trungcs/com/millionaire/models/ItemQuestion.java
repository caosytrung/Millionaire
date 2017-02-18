package trungcs.com.millionaire.models;

import java.io.Serializable;

/**
 * Created by caotr on 28/08/2016.
 */
public class ItemQuestion implements Serializable {
    private int  level;
    private String caseA;
    private String caseB;
    private String caseC;
    private String caseD;
    private int trueCase;
    private String contentQuestion;

    public ItemQuestion(int level,
                        String caseA,
                        String caseB,
                        String caseC,
                        String caseD,
                        int trueCase,
                        String contentQuestion) {

        this.level = level;
        this.caseA = caseA;
        this.caseB = caseB;
        this.caseC = caseC;
        this.caseD = caseD;
        this.trueCase = trueCase;
        this.contentQuestion =
                contentQuestion;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCaseA() {
        return caseA;
    }

    public void setCaseA(String caseA) {
        this.caseA = caseA;
    }

    public String getCaseB() {
        return caseB;
    }

    public void setCaseB(String caseB) {
        this.caseB = caseB;
    }

    public String getCaseC() {
        return caseC;
    }

    public void setCaseC(String caseC) {
        this.caseC = caseC;
    }

    public String getCaseD() {
        return caseD;
    }

    public void setCaseD(String caseD) {
        this.caseD = caseD;
    }

    public int getTrueCase() {
        return trueCase;
    }

    public void setTrueCase(int trueCase) {
        this.trueCase = trueCase;
    }

    public String getContentQuestion() {
        return contentQuestion;
    }

    public void setContentQuestion(String contentQuestion) {
        this.contentQuestion =
                contentQuestion;
    }
}
