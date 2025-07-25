package com.example.MovieInABox.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.MovieInABox.R;
import com.example.MovieInABox.activity.admin.AddMoviesActivity;
import com.example.MovieInABox.activity.admin.ManagerMovies;
import com.example.MovieInABox.model.ApiResponse;
import com.example.MovieInABox.model.Status;
import com.example.MovieInABox.model.User;
import com.example.MovieInABox.service.ApiService;
import com.example.MovieInABox.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LinearLayout linearLayout_add_movies, linearLayout_manager_movies;
    private Button btn_add_movies, btn_manger_movies;
    private String mParam1;
    private String mParam2;

    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        linearLayout_add_movies = view.findViewById(R.id.linearLayout_add_movies);
        linearLayout_manager_movies = view.findViewById(R.id.linearLayout_manager_movies);
        btn_add_movies = view.findViewById(R.id.btn_add_movies);
        btn_manger_movies = view.findViewById(R.id.btn_manager_movies);

        // hide ui
        linearLayout_add_movies.findViewById(R.id.linearLayout_add_movies).setVisibility(View.INVISIBLE);
        linearLayout_manager_movies.findViewById(R.id.linearLayout_manager_movies).setVisibility(View.INVISIBLE);

        // ...
        checkRole();
        addMoviesOnClick();
        btnMangerMoviesOnClick();

        return view;
    }

    public void addMoviesOnClick() {
        btn_add_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddMoviesActivity.class);
                startActivity(intent);
            }
        });
    }

    public void btnMangerMoviesOnClick() {
        btn_manger_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ManagerMovies.class);
                startActivity(intent);
            }
        });
    }

    private void checkRole() {
        UserService userService = ApiService.createService(UserService.class);
        Call<ApiResponse<User>> call = userService.getUser();
        call.enqueue(new Callback<ApiResponse<User>>() {
            @Override
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<User> res = response.body();
                    if (res.getStatus() == Status.SUCCESS) {
                        User user = res.getData();
                        if (user.getAdmin() == true) {
                            // show ui
                            linearLayout_add_movies.findViewById(R.id.linearLayout_add_movies).setVisibility(View.VISIBLE);
                            linearLayout_manager_movies.findViewById(R.id.linearLayout_manager_movies).setVisibility(View.VISIBLE);
                        } else {
                            // hide ui
                            linearLayout_add_movies.findViewById(R.id.linearLayout_add_movies).setVisibility(View.INVISIBLE);
                            linearLayout_manager_movies.findViewById(R.id.linearLayout_manager_movies).setVisibility(View.INVISIBLE);
                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<ApiResponse<User>> call, Throwable t) {

            }
        });
    }

}