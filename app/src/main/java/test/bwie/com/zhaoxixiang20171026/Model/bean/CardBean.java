package test.bwie.com.zhaoxixiang20171026.Model.bean;

/**
 * Created by admin on 2017/10/26/026.
 */

public class CardBean {
    private boolean checked;
    private String name;
    private int price;

    public CardBean() {
    }

    public CardBean(boolean checked, String name, int price) {
        this.checked = checked;
        this.name = name;
        this.price = price;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
