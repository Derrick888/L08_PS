package sg.edu.rp.c346.id22011505.ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends MainActivity {
    ListView lvSong;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lvSong = findViewById(R.id.lvSongs);
        btnBack = findViewById(R.id.btnBack);

        ArrayList<String> songList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songList);
        lvSong.setAdapter(adapter);

        // Retrieve the data from the Intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String singer = intent.getStringExtra("singer");
        int year = intent.getIntExtra("year", 0);
        int stars = intent.getIntExtra("stars", 0);

        // Display the data in the ListView
        String songInfo = title + "\n" + singer + "\n" + year + "\n" + stars;
        songList.add(songInfo);
        adapter.notifyDataSetChanged();


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}