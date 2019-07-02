package com.code.alpha.alphamade.exercise.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.code.alpha.alphamade.R;
import com.code.alpha.alphamade.exercise.activity.MainActivity;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements View.OnClickListener {


    public CategoryFragment() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnDetailCategory = view.findViewById(R.id.btn_detail_category);
        btnDetailCategory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_detail_category){
            DetailCategoryFragment fragment = new DetailCategoryFragment();
            Bundle bundle = new Bundle();
            bundle.putString(DetailCategoryFragment.EXTRA_NAME, "Lifestyle");
            String desc = "Kategori ini akan berisi produk-produk lifestyle";
            fragment.setArguments(bundle);
            fragment.setDescription(desc);

            ((MainActivity) Objects.requireNonNull(getActivity())).replaceFragment(fragment);
        }
    }
}
