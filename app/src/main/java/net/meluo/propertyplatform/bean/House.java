package net.meluo.propertyplatform.bean;

import com.dadao.d5.utl.Checking;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;


public class House implements Serializable {

    private static final String NAME = "name";
    private static final String IMG = "img";
    private static final String PRICE = "price";
    private static final String BUILD_TYPE = "build_type";
    private static final String HOUSE_TYPE = "house_type";
    private static final String SIZE = "size";
    private static final String BUILD_TIME = "build_time";

    private String name = "";
    private float price = 0l;
    private String build_type = "";
    private String house_type = "";
    private float size = 0l;
    private String build_time = "";

    public House(String name, String img, float price, String build_type, String house_type, float size, String build_time) {

        this.name = name;
        this.img = img;
        this.price = price;
        this.build_type = build_type;
        this.house_type = house_type;
        this.size = size;
        this.build_time = build_time;
    }

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

            if (o.has(NAME)) {
                this.name = o.getString(NAME);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJSONObject() {
        JSONObject o = new JSONObject();
        try {

            if (!Checking.isNullorBlank(this.name)) {
                o.put(NAME, this.name);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return o;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setBuild_type(String build_type) {
        this.build_type = build_type;
    }

    public void setHouse_type(String house_type) {
        this.house_type = house_type;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setBuild_time(String build_time) {
        this.build_time = build_time;
    }

    private String img = "";

    public String getImg() {
        return img;
    }

    public float getPrice() {
        return price;
    }

    public String getBuild_type() {
        return build_type;
    }

    public String getHouse_type() {
        return house_type;
    }

    public float getSize() {
        return size;
    }

    public String getBuild_time() {
        return build_time;
    }


}
