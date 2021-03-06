package vakili.ramin.civildictionary.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.andexert.library.RippleView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import vakili.ramin.civildictionary.R;
import vakili.ramin.civildictionary.adapters.SuggestionsAdapter;
import vakili.ramin.civildictionary.database.DataBaseHelper;
import vakili.ramin.civildictionary.entities.Word;
import vakili.ramin.civildictionary.util.VoicePlayer;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, TextWatcher, SuggestionsAdapter.ClickListener {

    private DataBaseHelper helper;
    private RecyclerView recyclerView;
    private SuggestionsAdapter adapter;
    private EditText editText;
    private TextView textViewResult;
    private RippleView rippleButtonVoice;
    private TextView textViewPhonetic;
    private VoicePlayer voicePlayer;
    ArrayList<String> suggestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIDs();
        adapter = new SuggestionsAdapter(suggestions, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        helper = new DataBaseHelper(this);
        try {
            helper.createDataBase();
            helper.openDataBase();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void findViewByIDs() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        editText = (EditText) findViewById(R.id.editText);
        RippleView rippleButtonSearch = (RippleView) findViewById(R.id.rippleButtonSearch);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        rippleButtonVoice = (RippleView) findViewById(R.id.rippleButtonVoice);
        textViewPhonetic = (TextView) findViewById(R.id.textViewPhonetic);
        rippleButtonSearch.setOnClickListener(this);
        rippleButtonVoice.setOnClickListener(this);
        editText.addTextChangedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rippleButtonSearch:
                searchShowResult(editText.getText().toString());
                break;

            case R.id.rippleButtonVoice:
                playVoices();
                break;
        }
    }

    private void playVoices() {
        try {
            voicePlayer.playVoices();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() > 0) {
            suggestions = helper.searchSuggestions(charSequence.toString());
            adapter.setSuggestions(suggestions);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onItemClicked(String word) {
        editText.setText(word);
        searchShowResult(word);
    }

    private void searchShowResult(String word) {
        if (editText.getText().toString().equals("")){
            return;
        }
        suggestions.clear();
        adapter.setSuggestions(suggestions);
        adapter.notifyDataSetChanged();
        Word searchWord = helper.searchWord(word);

        if (searchWord != null) {
            if (searchWord.getPhonetic().equals("")) {
                textViewPhonetic.setVisibility(View.INVISIBLE);
            } else {
                textViewPhonetic.setVisibility(View.VISIBLE);
                textViewPhonetic.setText(searchWord.getPhonetic());
            }

            String[] voicesFilePath = searchWord.getVoicesFilePath();

            if (voicesFilePath.length>0){
                rippleButtonVoice.setVisibility(View.VISIBLE);
                try {
                    voicePlayer = new VoicePlayer(voicesFilePath, this);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }else {
                rippleButtonVoice.setVisibility(View.INVISIBLE);
            }

            textViewResult.setText(searchWord.getMeaning());
        } else {
            textViewResult.setText(getResources().getString(R.string.not_found));
        }
    }
}
