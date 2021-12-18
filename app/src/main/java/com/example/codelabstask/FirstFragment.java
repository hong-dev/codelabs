package com.example.codelabstask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.codelabstask.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

private FragmentFirstBinding binding;

    TextView showCountTextView;
    int plusCount = 0;
    int minusCount = 0;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View fragmentFirstLayout = inflater.inflate(R.layout.fragment_first, container, false);

        // Get the count text view
        showCountTextView = fragmentFirstLayout.findViewById(R.id.textview_first);

        return fragmentFirstLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.random_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentCount = Integer.parseInt(showCountTextView.getText().toString());

                if (currentCount == 0) {
                    Toast zeroToast = Toast.makeText(getActivity(), R.string.random_button_toast_message, Toast.LENGTH_SHORT);
                    zeroToast.show();
                } else {
                    FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount);
                    NavHostFragment.findNavController(FirstFragment.this).navigate(action);
                }
            }
        });

        view.findViewById(R.id.toast_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity(), String.format(getString(R.string.toast_message), plusCount, minusCount), Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        view.findViewById(R.id.plus_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plusMe(view);
            }
        });

        view.findViewById(R.id.minus_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minusMe(view);
            }
        });

        view.findViewById(R.id.init_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCountTextView.setText(String.valueOf(0));
            }
        });
    }

    private void plusMe(View view) {
        plusCount++;

        // Get the value of the text view
        String countString = showCountTextView.getText().toString();

        // Convert value to a number and increment it
        Integer count = Integer.parseInt(countString);
        count++;

        // Display the new value in the text view
        showCountTextView.setText(count.toString());
    }

    private void minusMe(View view) {
        minusCount++;

        // Get the value of the text view
        String countString = showCountTextView.getText().toString();

        // Convert value to a number and decrement it
        Integer count = Integer.parseInt(countString);
        if (count > 0) {
            count--;
            showCountTextView.setText(count.toString());
        }
    }


@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}