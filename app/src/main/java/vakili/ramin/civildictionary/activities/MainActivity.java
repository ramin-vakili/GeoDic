package vakili.ramin.civildictionary.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import vakili.ramin.civildictionary.R;
import vakili.ramin.civildictionary.adapters.SuggestionsAdapter;
import vakili.ramin.civildictionary.database.DataBaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher, SuggestionsAdapter.ClickListener {

    private DataBaseHelper helper;
    private RecyclerView recyclerView;
    private SuggestionsAdapter adapter;
    private TextView textViewResult;
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
        EditText editText = (EditText) findViewById(R.id.editText);
        ImageButton imageButtonSearch = (ImageButton) findViewById(R.id.imageButtonSearch);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        imageButtonSearch.setOnClickListener(this);
        editText.addTextChangedListener(this);
    }

    @Override
    public void onClick(View view) {

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
        suggestions.clear();
        adapter.setSuggestions(suggestions);
        adapter.notifyDataSetChanged();
        textViewResult.setText(word);
    }
}
