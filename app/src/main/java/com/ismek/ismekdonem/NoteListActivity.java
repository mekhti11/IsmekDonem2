package com.ismek.ismekdonem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ismek.ismekdonem.adapter.NotListAdapter;
import com.ismek.ismekdonem.entity.Not;
import com.ismek.ismekdonem.listener.HaberListener;
import com.ismek.ismekdonem.util.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private List<Not> notList;
    private SharedPreferenceUtils sharedPreferenceUtils;

    private RecyclerView recyclerView;
    private NotListAdapter adapter;
    private int selectedIndex = -1;
    private Not selectedNot;

    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_list);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        sharedPreferenceUtils = new SharedPreferenceUtils(NoteListActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gson = new Gson();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(NoteListActivity.this,YeniNotActivity.class),1000);
            }
        });

        //Cache de not var ve listeyi doldur
        String cacheNotData = sharedPreferenceUtils.getStringValue("notlist",null);
        if (!TextUtils.isEmpty(cacheNotData)){
            notList = gson.fromJson(cacheNotData, new TypeToken<List<Not>>(){}.getType());
            setListAdapter();
        }else{
            notList = new ArrayList<>();
        }
    }

    public void setListAdapter(){
        adapter = new NotListAdapter(notList, new HaberListener() {
            @Override
            public void onDetail(View view, int position) {
                selectedIndex = position;
                selectedNot = notList.get(position);

                CharSequence colors[] = new CharSequence[] {"Düzenle", "Sil"};

                AlertDialog.Builder builder = new AlertDialog.Builder(NoteListActivity.this);
                builder.setTitle("Seçim");
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            Intent i = new Intent(NoteListActivity.this,YeniNotActivity.class);
                            i.putExtra("not",selectedNot);
                            startActivityForResult(i,1001);
                        }else if (which == 1){
                            notList.remove(selectedIndex);
                            sharedPreferenceUtils.setValue("notlist",gson.toJson(notList));
                            setListAdapter();
                        }
                    }
                });
                builder.show();



            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000){
            Not not = data.getParcelableExtra("not");
            notList.add(not);
            setListAdapter();
        }else if (resultCode == 1001){
            if (selectedIndex != -1){
                Not not = data.getParcelableExtra("not");
                notList.set(selectedIndex,not);
                setListAdapter();
            }
        }
        sharedPreferenceUtils.setValue("notlist",gson.toJson(notList));
    }
}
