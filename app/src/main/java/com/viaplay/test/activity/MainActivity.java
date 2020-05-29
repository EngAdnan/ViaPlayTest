package com.viaplay.test.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.viaplay.test.model.ViaplayResponse;
import com.viaplay.test.model.ViaplaySections;
import com.viaplay.test.R;
import com.viaplay.test.service.ApiCallService;
import com.viaplay.test.adapter.SectionsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    List<ViaplaySections> sections = new ArrayList<>();
    private SectionsAdapter sectionsAdapter;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        sectionsAdapter = new SectionsAdapter(sections, R.layout.list_item_sections, getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sectionsAdapter);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(config);

        sectionsAdapter.SetOnItemClickListener((view, position) -> new Handler().postDelayed(() -> {
                    Intent intent = new Intent(getApplicationContext(), SectionDetailsActivity.class);
                    intent.putExtra("sectionID", sections.get(position).getId());
                    intent.putExtra("sectionName", sections.get(position).getTitle());
                    intent.putExtra("sectionLink", sections.get(position).getHref());
                    startActivity(intent);
                }, 400)
        );

        RealmResults<ViaplaySections> sectionsList = realm.where(ViaplaySections.class).findAll();
        if (sectionsList != null) {
            sections = sectionsList;
            sectionsAdapter.UpdateSectionList(sectionsList);

        }


        ApiCallService.fetchData().sections().enqueue(new Callback<ViaplayResponse>() {
            @Override
            public void onResponse(@NonNull Call<ViaplayResponse> call, @NonNull Response<ViaplayResponse> response) {
                Log.e(TAG, call.request().toString());
                if (response.body() != null) {
                    sections = response.body().getSections();
                    sectionsAdapter.UpdateSectionList(sections);

                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(sections);
                    realm.commitTransaction();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ViaplayResponse> call, @NonNull Throwable t) {
                Log.e(TAG, t.toString());

            }
        });
    }
}
