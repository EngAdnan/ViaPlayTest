package com.viaplay.test.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.viaplay.test.model.ViaplayData;
import com.viaplay.test.R;
import com.viaplay.test.model.ViaplaySections;
import com.viaplay.test.service.ApiCallService;

public class SectionDetailsActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ViaplayData selectedSectionDetails = new ViaplayData();
    Realm realm;
    String sectionId, sectionLink, sectionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_details);
        TextView sectionTitle = findViewById(R.id.section_title);
        TextView sectionDescription = findViewById(R.id.section_description_tv);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(config);
        sectionId = getIntent().getStringExtra("sectionID");
        sectionName = getIntent().getStringExtra("sectionName");
        sectionLink = getIntent().getStringExtra("sectionLink");

        setTitle(sectionName);

        ViaplayData sectionDetails = realm.where(ViaplayData.class).equalTo("sectionId", sectionId).findFirst();
        if (sectionDetails != null) {
            sectionTitle.setText(sectionDetails.getTitle());
            sectionDescription.setText(sectionDetails.getDescription());

        }


        ApiCallService.fetchData().sectionItems(sectionLink).enqueue(new Callback<ViaplayData>() {
            @Override
            public void onResponse(@NonNull Call<ViaplayData> call, @NonNull Response<ViaplayData> response) {
                Log.e(TAG, call.request().toString());
                if (response.body() != null) {
                    selectedSectionDetails = response.body();
                    new Handler().post(() -> {
                        sectionTitle.setText(selectedSectionDetails.getTitle());
                        sectionDescription.setText(selectedSectionDetails.getDescription());
                        selectedSectionDetails.setSectionId(sectionId);

                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(selectedSectionDetails);
                        realm.commitTransaction();
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call<ViaplayData> call, @NonNull Throwable t) {
                Log.e(TAG, t.toString());

            }
        });
    }
}



