package yxd.test.design_pattern;

import android.util.Log;

/**
 * Created by asus on 2018/1/9.
 */

public class PrototypeTest {

    public static void main(){
        Card card = new Card("A");
        Card card1 = card.clone();
        card1.info();

        CompanyCard companyCard = new CompanyCard(new Company("A"));
        CompanyCard companyCard1 = companyCard.clone();
        companyCard1.info();

    }

}
/*
浅拷贝
 */
class Card implements Cloneable{

    private String name;

    public Card(String name) {
        this.name = name;
    }

    //浅拷贝
    @Override
    protected Card clone(){
        Card card = null;
        try {
            card = (Card) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return card;
    }

    public void info(){
        Log.d("Test", "这是卡片"+name);
    }
}

/*
 深拷贝
  */
class CompanyCard implements Cloneable{
    private Company company;

    public CompanyCard(Company company) {
        this.company = company;
    }

    @Override
    protected CompanyCard clone() {
        CompanyCard card = null;
        try {
            card = (CompanyCard) super.clone();
            card.company = this.company.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return card;
    }

    public void info(){
        Log.d("Test", "这是来自"+company.getName()+"公司的名片");
    }
}

class Company implements Cloneable{

    private String name;

    public String getName() {
        return name;
    }

    public Company(String name) {
        this.name = name;
    }

    @Override
    protected Company clone(){
        Company company = null;
        try {
            company = (Company) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return company;
    }
}

