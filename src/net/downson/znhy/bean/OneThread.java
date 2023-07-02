package net.downson.znhy.bean;

import net.downson.znhy.dao.SearchDao;
import net.downson.znhy.service.ListJson;
import org.json.JSONArray;

import javax.websocket.Session;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OneThread extends Thread {
    private Session session;
    private List<GPS> currentMessage;
    private SearchDao dbUtil;
    private int currentIndex;
    public OneThread(Session session) {
        this.session = session;
        currentMessage = new ArrayList<GPS>();
        dbUtil = new SearchDao();
        currentIndex = 0;//此时是0条消息
    }

    public OneThread() {

    }


    @Override
    public void run() {
        while (true) {
            List<GPS> list = null;
            try {
                list = dbUtil.querySearch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ListJson ltj = new ListJson();
            JSONArray a = ltj.ProLogList2Json(list);
            if (a != null&& currentIndex < a.length() ) {
//                for (int i = currentIndex; i < a.length(); i++) {
//                    JSONObject jo = a.getJSONObject(i);
//                    String latitude = jo.getString("latitude");
//                    String longitude = jo.getString("longitude");
//                    System.out.println(latitude);
//                    System.out.println(longitude);
                try {
                    session.getBasicRemote().sendText(a.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                            session.getBasicRemote().sendObject(list.get(i)); //No encoder specified for object of class [class AlarmMessage]
//                }
                currentIndex = a.length();
            }
            try {
                //一秒刷新一次
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}