package com.yztc.lx.utils;

import android.content.Context;

import com.yztc.lx.com.yztc.lx.entity.Goods;
import com.yztc.lx.com.yztc.lx.entity.GoodsTypes;
import com.yztc.lx.com.yztc.lx.entity.Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lx on 2016/8/5.
 */

public class AssetsUtils {
    public static String getJsonString(Context context, String fileName) {
        try {
            InputStream is = context.getResources().getAssets().open(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte b[] = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                baos.write(b, 0, len);
                baos.flush();
            }
            is.close();
            return baos.toString("utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //解析第一级，得到总的Goods的集合
    public static List<Goods> fromStringToGoodsList(String jsonString) {
        List<Goods> list = new ArrayList<>();
        try {
            JSONObject obj1=new JSONObject(jsonString);
            JSONObject obj2=obj1.getJSONObject("data");
            JSONArray arr = obj2.getJSONArray("tags");

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                JSONArray arr2 = obj.getJSONArray("tags");
                List<GoodsTypes> goodsTypes = fromStringToGoodsTypesList(arr2);
                Goods goods = new Goods(goodsTypes, obj.getString("category"));
                list.add(goods);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //解析第二级，得到一个GoodsTypes的集合
    public static List<GoodsTypes> fromStringToGoodsTypesList(JSONArray array) {
        List<GoodsTypes> list = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                JSONArray arr = obj.getJSONArray("tags");
                List<Types> typesList = fromStringToTypesList(arr);
                GoodsTypes goodsTypes = new GoodsTypes(typesList, obj.getString("title"));
                list.add(goodsTypes);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //解析第三级，得到Types集合
    public static List<Types> fromStringToTypesList(JSONArray array) {
        List<Types> list = new ArrayList<>();

        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Types types=new Types(obj.getInt("tid"),obj.getString("name"));
                list.add(types);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }
}
