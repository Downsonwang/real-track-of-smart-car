package net.downson.znhy.servlet;

import com.alibaba.fastjson.JSON;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/SearchServlet")
public class SearchServlet  extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//           try {
        response.setContentType("text/html");

        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        InputStreamReader insr = new InputStreamReader(request.getInputStream(),"utf-8");
     String result = "";
     int respInt = insr.read();
     while(respInt!=-1) {
      result +=(char)respInt;
     respInt = insr.read();
}   
    


        Map<String, Object> obj = null;
        try {
            obj =  JSON.parseObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }       

       
        Map<String, Object> service = (Map)obj.get("service");
        String latitude  =  "";
        String longtitude =  "";
        String power = "";
        String qualt = "";
        Map<String, Object> data = null;

        String deviceId  =   (String)obj.get("deviceId");
        String notifyType  =   (String)obj.get("notifyType");
        String gatewayId  =   (String)obj.get("gatewayId");
        String requestId  =   (String)obj.get("requestId");

        if (service != null  && service.containsKey("data")) {
            data = (Map) service.get("data");
        }
        if (data != null  && data.containsKey("longtitude")) {
            longtitude = (String) data.get("longtitude");
        }
        if (data != null  && data.containsKey("latitude")) {
            latitude = (String) data.get("latitude");
        }
        if (data != null  && data.containsKey("power")) {
            power = (String) data.get("power");
        }
        if (data != null  && data.containsKey("qualt")) {
            qualt = (String) data.get("qualt");
        }

        try {
            ComboPooledDataSource dataSource=new ComboPooledDataSource();
            QueryRunner queryRunner=new QueryRunner(dataSource);
            String sql="insert into iot values(?,?,?,?,?)";
            int row = queryRunner.update(sql,deviceId,longtitude,latitude,power,qualt);
            //行数大于零说明注册成功
            if (row>0) {
                System.out.println("成功加入");
            }else {
                System.out.println("失败加入");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
 System.out.println("result:" + result);
        System.out.println("deviceId:" + deviceId);
        System.out.println("notifyType:" + notifyType);
        System.out.println("gatewayId:" + gatewayId);
        System.out.println("requestId:" + requestId);

        System.out.println("service:" + service);
        System.out.println("longtitude:" + longtitude);
        System.out.println("latitude:" + latitude);
        System.out.println("power:"+power);
        System.out.println("qualt:"+qualt);
    }
//        PrintWriter out = response.getWriter();
//        SearchService service = new SearchService();
////        List<GPS> list = null;
//        try {
//            list = service.findCategory();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//
//        }
//        ListJson ltj = new ListJson();
//
//        JSONArray a = ltj.ProLogList2Json(list);




    }






