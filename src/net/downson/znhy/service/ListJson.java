package net.downson.znhy.service;

import net.downson.znhy.bean.GPS;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ListJson {
    public static JSONArray ProLogList2Json(List<GPS> list) {
        JSONArray json = new JSONArray();
        for (GPS pLog : list) {
            JSONObject jo = new JSONObject();
            jo.put("x", pLog.getLatitude());
            jo.put("y", pLog.getLongitude());

            json.put(jo);
        }

        return json;
    }
     }




