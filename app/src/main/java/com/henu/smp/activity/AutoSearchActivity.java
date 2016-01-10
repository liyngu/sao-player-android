package com.henu.smp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.henu.smp.R;
import com.henu.smp.base.BaseAsyncResult;
import com.henu.smp.base.BaseButton;
import com.henu.smp.base.BaseDialog;
import com.henu.smp.base.BaseMenu;
import com.henu.smp.entity.Menu;
import com.henu.smp.entity.Song;
import com.henu.smp.widget.RectButton;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyngu on 12/23/15.
 */
public class AutoSearchActivity extends BaseDialog {
    private ListView mListView;
    private List<Song> mSongList;
    private Song mSelectedSong;

    @ViewInject(R.id.choose_list_menu)
    private BaseMenu mChooseListMenu;

    private View.OnClickListener mChooseMenuListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BaseButton btn = (BaseButton) v;
            List<Song> songs = new ArrayList<>();
            mSelectedSong.setMenuId(btn.getMenuId());
            songs.add(mSelectedSong);
            mMusicService.saveSongs(songs, AutoSearchActivity.this);
            AutoSearchActivity.this.finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_search);
        List<Menu> menuList = mUserService.getMusicMenus(this);
        for (Menu menu : menuList) {
            RectButton rb = new RectButton(this);
            rb.setData(menu);
            mChooseListMenu.addView(rb);
            rb.setOnClickListener(mChooseMenuListener);
        }

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSelectedSong = mSongList.get(position);
                mChooseListMenu.show();
            }
        });
        this.setAdapterData();

    }

    public void setAdapterData(){
        if (mSongList != null) {
            return;
        }
        mMusicService.getAllLocalSongs(new BaseAsyncResult<List<Song>>() {
            @Override
            public void onSuccess(List<Song> result) {
                if (result == null || result.size() == 0 ){
                    return;
                }
                mSongList = result;
                ArrayAdapter<Song> adapter = new ArrayAdapter<>(AutoSearchActivity.this,
                        android.R.layout.simple_list_item_1, result);
                mListView.setAdapter(adapter);
            }
        }, this);
    }
}
