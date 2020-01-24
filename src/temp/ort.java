/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import jdk.jfr.events.FileReadEvent;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author bambl
 */
public class ort {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws MalformedURLException, IOException, JSONException, SQLException {
        Connection db;
        URL url = new URL("https://data.rtr.at/api/v1/tables/plz.json");

        db = DriverManager.getConnection("jdbc:derby://localhost:1527/Patient", "Pat", "Pat");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        System.out.println("File" + url.getFile());

        JSONObject json = new JSONObject(br.readLine());
        System.out.println(json.getJSONArray("data").get(1));

        //JSONObject obj = new JSONObject(json.getJSONArray("data").get(0));
        ArrayList<JSONObject> list = new ArrayList();
        //PreparedStatement pps = db.prepareStatement("INSERT INTO ort(plz,ort) VALUES (?,?)");

        System.out.println(json.getJSONArray("data").length());
        
        for (int i = 0; i < json.getJSONArray("data").length(); i++) {

            list.add((JSONObject) json.getJSONArray("data").get(i));
            //System.out.println(list.get(i));
            //System.out.println(list.get(i).getString("ort"));

            //pps.setInt(1, list.get(i).getInt("plz"));
            //pps.setString(2, list.get(i).getString("ort"));

            //pps.executeUpdate();

        }

    }

}
