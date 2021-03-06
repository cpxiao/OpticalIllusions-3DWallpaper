package com.cpxiao.optical.illusions.wallpapers.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cpxiao.R;
import com.cpxiao.gamelib.fragment.BaseZAdsFragment;
import com.cpxiao.optical.illusions.wallpapers.mode.Data;
import com.cpxiao.zads.core.ZAdPosition;

import java.util.ArrayList;

/**
 * @author cpxiao on 2017/09/01.
 */

public class ListFragment extends BaseZAdsFragment {
    //每行数量
    private static final int ROW_COUNT = 5;

    //宽高比
    private static final float ASPECT_RATIO = 0.618f;

    public static ListFragment newInstance(Bundle bundle) {
        ListFragment fragment = new ListFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        loadZAds(ZAdPosition.POSITION_LEVEL_LIST);

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(context, ROW_COUNT));
        ArrayList<Integer> arrayList = Data.getOIGifData();
        NormalAdapter adapter = new NormalAdapter(context, arrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.NormalViewHolder> {
        private final LayoutInflater mLayoutInflater;
        private final Context mContext;
        private ArrayList<Integer> mDataList;

        NormalAdapter(Context context, ArrayList<Integer> dataList) {
            mDataList = dataList;
            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) view.getLayoutParams();
            params.height = (int) (parent.getMeasuredWidth() / ROW_COUNT * ASPECT_RATIO);
            view.setLayoutParams(params);
            return new NormalViewHolder(view);
        }


        @Override
        public void onBindViewHolder(NormalViewHolder holder, int position) {
            if (mDataList == null) {
                return;
            }
            if (position < 0 || position >= mDataList.size()) {
                return;
            }

            final int index = holder.getAdapterPosition();
            final Integer data = mDataList.get(index);
            //bind wallpaper
            //            holder.mImageView.setImageResource(data);
            Glide.with(mContext).load(data).into(holder.mImageView);
            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(FullscreenFragment.RESOURCE_ID, data);
                    addFragment(FullscreenFragment.newInstance(bundle));

                }
            });

            String title = getResources().getString(R.string.level) + ": " + (index + 1);
            holder.mTitle.setText(title);

        }

        @Override
        public int getItemCount() {
            return mDataList == null ? 0 : mDataList.size();
        }

        class NormalViewHolder extends RecyclerView.ViewHolder {
            ImageView mImageView;
            TextView mTitle;

            NormalViewHolder(View view) {
                super(view);
                mImageView = (ImageView) view.findViewById(R.id.image_view);
                mTitle = (TextView) view.findViewById(R.id.title);
            }
        }

    }
}
