package vakili.ramin.civildictionary.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vakili.ramin.civildictionary.R;
import vakili.ramin.civildictionary.database.DatabaseHelper;
import vakili.ramin.civildictionary.entities.Word;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper helper = new DatabaseHelper(this);
        helper.insertEntry(new Word("cat", "smallest feline"));
    }
}
