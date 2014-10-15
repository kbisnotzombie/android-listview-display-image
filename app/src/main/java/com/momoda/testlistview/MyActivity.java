package com.momoda.testlistview;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class MyActivity extends ListActivity implements AbsListView.OnScrollListener {
    private ListView listView;
    private int visibleLastIndex = 0;   //最后的可视项索引
    private int visibleItemCount;       // 当前窗口可见项总数
    private ListViewAdapter adapter;
    private View loadMoreView;
    private Button loadMoreButton;
    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        loadMoreView = getLayoutInflater().inflate(R.layout.load_more, null);
        loadMoreButton = (Button) loadMoreView.findViewById(R.id.loadMoreButton);

        listView = getListView();               //获取id是list的ListView

        listView.addFooterView(loadMoreView);   //设置列表底部视图

        initAdapter();

        setListAdapter(adapter);                //自动为id是list的ListView设置适配器

        listView.setOnScrollListener(this);     //添加滑动监听
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        ArrayList<String> items = new ArrayList<String>();
//        for (int i = 0; i < 10; i++) {
//            items.add(String.valueOf(i + 1));
//        }
        items.add("http://pic3.nipic.com/20090619/1242397_084625008_2.jpg");
        items.add("http://pic.dooland.com/MagzineArticlePic/303017_64_1380219176_99.jpg");
        items.add("http://pic3.nipic.com/20090619/1242397_084625008_2.jpg");
        items.add("http://pic.dooland.com/MagzineArticlePic/303017_64_1380219176_99.jpg");

        adapter = new ListViewAdapter(this, items);
    }

    /**
     * 滑动时被调用
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.visibleItemCount = visibleItemCount;
        visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
    }

    /**
     * 滑动状态改变时被调用
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        int itemsLastIndex = adapter.getCount() - 1;    //数据集最后一项的索引
        int lastIndex = itemsLastIndex + 1;             //加上底部的loadMoreView项
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex) {
            //如果是自动加载,可以在这里放置异步加载数据的代码
            Log.i("LOADMORE", "loading...");
        }
    }

    /**
     * 点击按钮事件
     * @param view
     */
    public void loadMore(View view) {
        loadMoreButton.setText("loading...");   //设置按钮文字loading
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                loadData();

                adapter.notifyDataSetChanged(); //数据集变化后,通知adapter
                listView.setSelection(visibleLastIndex - visibleItemCount + 1); //设置选中项

                loadMoreButton.setText("load more");    //恢复按钮文字
            }
        }, 2000);
    }

    /**
     * 模拟加载数据
     */
    private void loadData() {
        int count = adapter.getCount();
//        for (int i = count; i < count + 10; i++) {
//            adapter.addItem(String.valueOf(i + 1));
//        }
        adapter.addItem("http://e.hiphotos.baidu.com/image/pic/item/962bd40735fae6cdff8dc3660db30f2442a70fff.jpg");
        adapter.addItem("http://pic.dooland.com/MagzineArticlePic/303017_64_1380219176_99.jpg");
    }
}
