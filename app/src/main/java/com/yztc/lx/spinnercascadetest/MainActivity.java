package com.yztc.lx.spinnercascadetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.yztc.lx.com.yztc.lx.entity.Goods;
import com.yztc.lx.com.yztc.lx.entity.GoodsTypes;
import com.yztc.lx.com.yztc.lx.entity.Types;
import com.yztc.lx.utils.AssetsUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner sp_category1, sp_title, sp_category2;
    private ArrayAdapter<String> adp_category1;
    private ArrayAdapter<String> adp_title;
    private ArrayAdapter<String> adp_category2;
    private List<Goods> goods = new ArrayList<>();
    private List<String> category1Name_list;
    private List<String> titleName_list;
    private List<String> category2Name_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String jsonString = AssetsUtils.getJsonString(this, "chaobaida.txt");
        //Log.d("Tag", jsonString);
        initView();
        initDate(jsonString);
        initAdapter();
        initEvent();
    }

    //完成事件的监听的定义
    private void initEvent() {
        sp_category1.setOnItemSelectedListener(this);
        sp_title.setOnItemSelectedListener(this);
    }

    private void initDate(String jsonString) {
        goods = AssetsUtils.fromStringToGoodsList(jsonString);
        for (Goods good : goods) {
            category1Name_list.add(good.getCategory());
        }
        Log.d("tag", category1Name_list.toString());
    }

    //初始化适配器
    private void initAdapter() {
        adp_category1 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, category1Name_list);
        adp_title = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);
        adp_category2 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);

        adp_category1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adp_title.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adp_category2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_category1.setAdapter(adp_category1);
        sp_title.setAdapter(adp_title);
        sp_category2.setAdapter(adp_category2);
    }

    //初始化控件
    private void initView() {
        sp_category1 = (Spinner) findViewById(R.id.sp_category1);
        sp_title = (Spinner) findViewById(R.id.sp_title);
        sp_category2 = (Spinner) findViewById(R.id.sp_category2);
        category1Name_list = new ArrayList<>();
    }

    /**
     * spinner的下拉列表项选择事件，当选中spinner中的某一项item的时候会触发
     * @param parent  指点击事件发生的容器，即某个特定的spinner
     * @param view  spinner中的item项view
     * @param position  当前选择的item的itemID
     * @param id  被点击的item的行号（跟position一个意思，不过多用position）
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.sp_category1:
                titleName_list = new ArrayList<>();
                List<GoodsTypes> goodsTypes = goods.get(position).getTags();
                for (GoodsTypes g : goodsTypes) {
                    titleName_list.add(g.getTitle());
                }
                //调用ArrayAdapter的clear()方法和addAll()方法,而不是spinner的方法
                adp_title.clear();
                adp_title.addAll(titleName_list);

                category2Name_list = new ArrayList<>();
                /**
                 *  在这里要得到第二个下拉列表框的itemID，根据第二个下拉列表框的itemID来填充第三个下拉列表框
                 *  否则当第一个spinner选择第二项以上的item时，会出现数组越界异常，因为types数组的size为2
                 */
                List<Types> types = goodsTypes.get((int) sp_title.getSelectedItemId()).getTags();
                for (Types type : types) {
                    category2Name_list.add(type.getName());
                }
                adp_category2.clear();
                adp_category2.addAll(category2Name_list);

                //设置第二个和第三个下拉列表框的默认选择项
                sp_title.setSelection(0);
                sp_category2.setSelection(0);
                break;
            case R.id.sp_title:
                /**
                 * 每次选择的时候重新new一下第三个spinner填充list，相当于将list清空，
                 * 不然的话在第二个spinner进行选择项操作的时候，第三个spinner的列表项会累加在一起
                 */
                category2Name_list = new ArrayList<>();
                /**
                 * 在这里要先获取第一个spinnerItem的itemID，根据其itemID来得到相应的GoodTypes
                 * 然后再根据得到的gooTypes和当前选择的第二个spinner的itemID来获得第三个spinner的下拉列表项
                 */
                List<Types> types2 = goods.get((int) sp_category1.getSelectedItemId()).getTags().get(position).getTags();
                for (Types type : types2) {
                    category2Name_list.add(type.getName());
                }
                adp_category2.clear();
                adp_category2.addAll(category2Name_list);
                break;
        }

    }

    //此方法无用
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
