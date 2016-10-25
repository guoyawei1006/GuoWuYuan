package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.guowuyuan.R;

import java.util.List;

import Bean.CommunityBean;

/**
 * Created by 郭亚伟 on 2016/10/25.
 */
public class MyAdapter2 extends BaseAdapter{
    Context context;
    List<CommunityBean.DataEntity.ForumListEntity> list;
    public MyAdapter2(Context context, List<CommunityBean.DataEntity.ForumListEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.listview_item, null);

        TextView text = (TextView) v.findViewById(R.id.textView);

        //text.setText(list.get(position).name);
        //text.setText(list.get(position).name);
        text.setText(list.get(position).name);
        return v;
    }
}
