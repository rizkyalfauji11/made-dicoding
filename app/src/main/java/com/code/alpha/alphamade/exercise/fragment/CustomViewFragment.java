package com.code.alpha.alphamade.exercise.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.code.alpha.alphamade.R;
import com.code.alpha.alphamade.exercise.customview.CustomButton;
import com.code.alpha.alphamade.exercise.customview.CustomEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomViewFragment extends Fragment {


    public CustomViewFragment() {
    }

    private CustomEditText customEditText;
    private CustomButton customButton;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_costum_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customButton = view.findViewById(R.id.my_button);
        customEditText = view.findViewById(R.id.my_edit_text);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setMyButtonEnable();
        customEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setMyButtonEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), customEditText.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setMyButtonEnable() {
        Editable result = customEditText.getText();
        if(result != null && !result.toString().isEmpty()){
            customButton.setEnabled(true);
        }else {
            customButton.setEnabled(false);
        }
    }
}
