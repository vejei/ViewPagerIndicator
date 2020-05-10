package io.github.vejei.viewpagerindicator.sample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import io.github.vejei.viewpagerindicator.indicator.CircleIndicator;

public class CircleIndicatorFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_circle_indicator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) return;

        InfinitePageAdapter pageAdapter = new InfinitePageAdapter();
        pageAdapter.setData(Arrays.asList(activity.data));
        ViewPager2 viewPager2 = view.findViewById(R.id.view_pager2);
        viewPager2.setAdapter(pageAdapter);

        CircleIndicator slideAnimationIndicator = view.findViewById(R.id.circle_indicator_slide);
        slideAnimationIndicator.setWithViewPager2(viewPager2, false);
        slideAnimationIndicator.setItemCount(activity.data.length);
        slideAnimationIndicator.setAnimationMode(CircleIndicator.AnimationMode.SLIDE);

        CircleIndicator scaleAnimationIndicator = view.findViewById(R.id.circle_indicator_scale);
        scaleAnimationIndicator.setWithViewPager2(viewPager2, false);
        scaleAnimationIndicator.setItemCount(activity.data.length);
        scaleAnimationIndicator.setAnimationMode(CircleIndicator.AnimationMode.SCALE);

        CircleIndicator colorAnimationIndicator = view.findViewById(R.id.circle_indicator_color);
        colorAnimationIndicator.setWithViewPager2(viewPager2, false);
        colorAnimationIndicator.setItemCount(activity.data.length);
        colorAnimationIndicator.setAnimationMode(CircleIndicator.AnimationMode.COLOR);

        CircleIndicator noAnimationIndicator = view.findViewById(R.id.circle_indicator_none);
        noAnimationIndicator.setWithViewPager2(viewPager2, false);
        noAnimationIndicator.setItemCount(activity.data.length);
        noAnimationIndicator.setAnimationMode(CircleIndicator.AnimationMode.NONE);
    }
}
