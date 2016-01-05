package net.meluo.propertyplatform.bean;

import com.dadao.d5.utl.Checking;

import org.json.JSONException;
import org.json.JSONObject;


public class User {

    private static final String NICK = "nick";
    private static final String NAME = "name";
    private static final String HEAD_URL = "head_url";
    private static final String SEX = "sex";
    private static final String MOBILE = "mobile";

    private String nick = "";
    private String name = "";
    private String head_url = "";
    private int sex = 0;
    private String mobile = "";

    public void fromJSONString(String json) {

        JSONObject o;
        try {
            o = new JSONObject(json);
            fromJSONObject(o);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void fromJSONObject(JSONObject o) {
        try {
            if (o.has(NICK)) {
                this.nick = o.getString(NICK);
            }
            if (o.has(NAME)) {
                this.name = o.getString(NAME);
            }
            if (o.has(HEAD_URL)) {
                this.head_url = o.getString(HEAD_URL);
            }
            if (o.has(SEX)) {
                this.sex = o.getInt(SEX);
            }
            if (o.has(MOBILE)) {
                this.mobile = o.getString(MOBILE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJSONObject() {
        JSONObject o = new JSONObject();
        try {
            if (!Checking.isNullorBlank(this.nick)) {
                o.put(NICK, this.nick);
            }
            if (!Checking.isNullorBlank(this.name)) {
                o.put(NAME, this.name);
            }
            if (!Checking.isNullorBlank(this.head_url)) {
                o.put(HEAD_URL, this.head_url);
            }
            o.put(SEX, this.sex);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return o;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
