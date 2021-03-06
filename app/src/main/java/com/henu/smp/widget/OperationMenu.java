package com.henu.smp.widget;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.henu.smp.Constants;
import com.henu.smp.R;
import com.henu.smp.base.BaseButton;
import com.henu.smp.base.BaseMenu;
import com.henu.smp.widget.RectButton;

/**
 * Created by liyngu on 12/12/15.
 */
public class OperationMenu extends BaseMenu {
    private BaseButton mClickedBtn;
    private ViewGroup mParentPanel;
    private BaseButton addBtn;
    private BaseButton delBtn;

    public OperationMenu(Context context, AttributeSet attrs) {
        super(context, attrs, R.layout.menu_operation);
        addBtn = (BaseButton) findViewById(R.id.add_btn);
        delBtn = (BaseButton) findViewById(R.id.del_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().showDialog(addBtn.getDialogClassName(), addBtn.getDialogParams());
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = delBtn.getDialogParams();
                bundle.putInt(Constants.SHOW_SONGS_MENU_ID, mClickedBtn.getMenuId());
                getActivity().showDialog(delBtn.getDialogClassName(), bundle);
            }
        });
    }

    @Override
    public void show() {
        mParentPanel.setVisibility(View.VISIBLE);
        super.show();
    }

    @Override
    public void hidden() {
        mParentPanel.setVisibility(View.GONE);
        super.hidden();
    }

    public void resetLocation() {
        if (mClickedBtn != null) {
            setLocationByView(mClickedBtn);
        }
    }

    public void setClickedView(BaseButton clickedBtn) {
        this.mClickedBtn = clickedBtn;
    }

    public BaseButton getClickedBtn() {
        return mClickedBtn;
    }

    public void setParentPanel(ViewGroup ParentPanel) {
        mParentPanel = ParentPanel;
    }
}
