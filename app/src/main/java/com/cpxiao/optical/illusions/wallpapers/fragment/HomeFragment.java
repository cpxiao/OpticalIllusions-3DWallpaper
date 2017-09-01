package com.cpxiao.optical.illusions.wallpapers.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cpxiao.R;
import com.cpxiao.gamelib.fragment.BaseFragment;

/**
 * @author cpxiao on 2017/09/01.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Button buttonWarning = (Button) view.findViewById(R.id.how_to_play);
        Button buttonTips = (Button) view.findViewById(R.id.tips);
        Button buttonPlay = (Button) view.findViewById(R.id.play);
        Button buttonRating = (Button) view.findViewById(R.id.rating);
        Button buttonShare = (Button) view.findViewById(R.id.share);

        buttonWarning.setOnClickListener(this);
        buttonTips.setOnClickListener(this);
        buttonPlay.setOnClickListener(this);
        buttonRating.setOnClickListener(this);
        buttonShare.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Context context = getHoldingActivity();
        if (id == R.id.how_to_play) {
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle(R.string.how_to_play)
                    .setMessage(R.string.how_to_play_msg)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            dialog.show();
        } else if (id == R.id.tips) {
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle(R.string.tips)
                    .setMessage(R.string.tips_msg)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            dialog.show();
        } else if (id == R.id.play) {

            Bundle bundle = new Bundle();
            addFragment(ListFragment.newInstance(bundle));
        } else if (id == R.id.rating) {
        } else if (id == R.id.share) {
        }
    }
}
