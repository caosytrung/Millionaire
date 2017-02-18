package trungcs.com.millionaire.models;

/**
 * Created by caotr on 11/09/2016.
 */
public class ItemHC {
    private String ten;
    private int diem;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public ItemHC(String ten, int diem) {

        this.ten = ten;
        this.diem = diem;
    }
}
