package vakili.ramin.civildictionary.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import vakili.ramin.civildictionary.R;
import vakili.ramin.civildictionary.database.DataBaseHelper;
import vakili.ramin.civildictionary.entities.Word;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHelper helper = new DataBaseHelper(this);
        try {
            helper.createDataBase();
            helper.openDataBase();
            ArrayList<Word> words = helper.getWords();
            for (Word w: words){
                Log.i("TAG", w.getWord() + "-" + w.getMeaning());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
