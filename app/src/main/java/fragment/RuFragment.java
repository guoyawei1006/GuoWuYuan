package fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.bwie.guowuyuan.BuMenActivity;
import com.bwie.guowuyuan.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import Bean.CommunityBean;
import adapter.MyAdapter1;
import util.HttpGet;

/**
 * Created by 郭亚伟 on 2016/10/24.
 */
public class RuFragment extends Fragment {

    private GridView gridview;
    private View v;
    private String path="http://open.qyer.com/qyer/bbs/entry?client_id=qyer_android&client_secret=9fcaae8aefc4f9ac4915";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v =  View.inflate(getActivity(), R.layout.fragment_ru, null);


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridview = (GridView) v.findViewById(R.id.gridview);
        //初始化数据
        initData();
    }

    private void initData() {
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... params) {
                String str = HttpGet.Getstr(path);
                return str;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                Gson gson = new Gson();
                CommunityBean bean = gson.fromJson(s, CommunityBean.class);
                List<CommunityBean.DataEntity.ForumListEntity> list = bean.data.forum_list;

                //适配器
                gridview.setAdapter(new MyAdapter1(getActivity(), list));
            }
        }.execute();

    }
}
