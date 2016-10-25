package fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bwie.guowuyuan.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import Bean.CommunityBean;
import adapter.MyAdapter2;
import util.HttpGet;

/**
 * Created by 郭亚伟 on 2016/10/24.
 */
public class CaiFragment extends Fragment{
    private boolean isViewCreated;
    private boolean isLoadDataCompleted;

    private ListView listView;
    private View v;
    private String path="http://open.qyer.com/qyer/bbs/entry?client_id=qyer_android&client_secret=9fcaae8aefc4f9ac4915";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= View.inflate(getActivity(), R.layout.fragment_cai,null);


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化控件
        initView();
        //初始化数据
       // initData();
    }

    private void initView() {
        listView = (ListView) v.findViewById(R.id.listview);
        isViewCreated = true;
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
                listView.setAdapter(new MyAdapter2(getActivity(), list));
            }
        }.execute();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated)//只有在用户可见以及初始化之后才加载数据
        {
            lazyLoad();
        }
    }

    private void lazyLoad() {
        if (!isLoadDataCompleted) {
            initView();
            initData();
            isLoadDataCompleted = true;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        isLoadDataCompleted = false;//这里需要重置状态，不然加载了之后就没办法再重新加载了
    }
}
