package io.github.vejei.viewpagerindicator.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Arrays;
import java.util.List;

import io.github.vejei.viewpagerindicator.indicator.RectIndicator;

public class RectIndicatorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rect_indicator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) return;

        PageAdapter pageAdapter = new PageAdapter();
        ViewPager2 viewPager2 = view.findViewById(R.id.view_pager2);
        viewPager2.setAdapter(pageAdapter);
        pageAdapter.setData(Arrays.asList(activity.data));

        RectIndicator rectIndicator = view.findViewById(R.id.rect_indicator);
        rectIndicator.setWithViewPager2(viewPager2);

        RectIndicator squareIndicator = view.findViewById(R.id.square_indicator);
        squareIndicator.setWithViewPager2(viewPager2);

        RectIndicator stadiumIndicator = view.findViewById(R.id.stadium_indicator);
        stadiumIndicator.setWithViewPager2(viewPager2);

        List<String> newData = Arrays.asList("Seventh", "Eighth", "Ninth");
        pageAdapter.append(newData);
    }
}
