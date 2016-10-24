package com.bwie.guowuyuan;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import Bean.CommunityBean;
import Bean.DataBean;
import adapter.MAdapter;
import util.HttpGet;

public class MainActivity extends AppCompatActivity {
    private String path="http://open.qyer.com/qyer/bbs/entry?client_id=qyer_android&client_secret=9fcaae8aefc4f9ac4915";

    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化界面
        initView();
        //初始化数据
        initData();

    }
    //初始化数据
    private void initData() {
        //异步加载数据
        new AsyncTask<Void,Void,String>(){

            @Override
            protected String doInBackground(Void... voids) {
                String str=HttpGet.Getstr(path);
                return str;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson=new Gson();
                CommunityBean communityBean=gson.fromJson(s, CommunityBean.class);
                //设置适配器
                recycler.setAdapter(new MAdapter(MainActivity.this,communityBean));
            }
        }.execute();

    }

    //初始化界面
    private void initView() {
        //找到控件id
        recycler = (RecyclerView) findViewById(R.id.recyclerview);
        //设置布局管理器
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }
}
