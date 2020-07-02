package com.makhalibagas.submission2.Ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.makhalibagas.submission2.Adapter.AdapterUserFavorite;
import com.makhalibagas.submission2.Database.UserHelper;
import com.makhalibagas.submission2.Model.UserGithub;
import com.makhalibagas.submission2.R;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    private UserHelper userHelper;
    private ArrayList<UserGithub> userGithubList =  new ArrayList<>();
    private AdapterUserFavorite adapterUserFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        userHelper = new UserHelper(getApplicationContext());
        userHelper.open();
        userGithubList = userHelper.getDataUser();
        setRecyclerView();
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

    }

    private void setRecyclerView(){
        RecyclerView rv = findViewById(R.id.rv_user);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        adapterUserFavorite = new AdapterUserFavorite(getApplicationContext());
        rv.setAdapter(adapterUserFavorite);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userGithubList = userHelper.getDataUser();
        adapterUserFavorite.setUserGithubList(userGithubList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userHelper.close();
    }
}
