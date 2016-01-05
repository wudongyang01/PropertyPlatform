package net.meluo.propertyplatform.bean;

import com.dadao.d5.utl.Checking;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


public class City implements Serializable {

    private static final String NAME = "name";
    private String name = "";

    public City(String name){
        this.name = name ;
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


}
