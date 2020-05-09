package io.github.vejei.viewpagerindicator.sample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) return;

        Button circleIndicatorButton = view.findViewById(R.id.button_main_circle_indicator);
        circleIndicatorButton.setOnClickListener(v -> {
            activity.addFragment(new CircleIndicatorFragment());
        });

        Button rectIndicatorButton = view.findViewById(R.id.button_main_rect_indicator);
        rectIndicatorButton.setOnClickListener(v -> {
            activity.addFragment(new RectIndicatorFragment());
        });
    }
}
