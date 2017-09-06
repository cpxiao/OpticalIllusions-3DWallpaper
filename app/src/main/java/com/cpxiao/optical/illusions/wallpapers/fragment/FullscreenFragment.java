package com.cpxiao.optical.illusions.wallpapers.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cpxiao.R;
import com.cpxiao.gamelib.fragment.BaseZAdsFragment;
import com.cpxiao.zads.core.ZAdPosition;


/**
 * @author cpxiao on 2017/09/01.
 */

public class FullscreenFragment extends BaseZAdsFragment {
    public static final String ID = "ID";
    private ImageView mImageView;

    public static FullscreenFragment newInstance(Bundle bundle) {
        FullscreenFragment fragment = new FullscreenFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        loadZAds(ZAdPosition.POSITION_GAME);

        mImageView = (ImageView) view.findViewById(R.id.gif_image_view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            int id = bundle.getInt(ID, -1);
            if (id != -1) {
                Glide.with(getHoldingActivity())
                        .load(id)
                        .into(mImageView);
            } else {
                onDestroy();
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fullscreen;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImageView != null) {
            mImageView.clearAnimation();
            mImageView.destroyDrawingCache();
        }
    }
}
