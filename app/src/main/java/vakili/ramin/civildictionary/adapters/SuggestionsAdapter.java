package vakili.ramin.civildictionary.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vakili.ramin.civildictionary.R;

/**
 * Created by RaminV on 12/3/2018.
 */
public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.WordViewHolder> {

    private ArrayList<String> suggestions;
    private Context mContext;
    private LayoutInflater inflater;
    private ClickListener clickListener;

    public SuggestionsAdapter(ArrayList<String> suggestions, Context context, ClickListener clickListener) {
        this.suggestions = suggestions;
        this.mContext = context;
        this.clickListener = clickListener;
        inflater = LayoutInflater.from(context);
    }

    public void setSuggestions(ArrayList<String> suggestions) {
        this.suggestions = suggestions;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_word, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        holder.textViewWord.setText(suggestions.get(position));
    }

    @Override
    public int getItemCount() {
        return suggestions.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textViewWord;

        public WordViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewWord = (TextView) itemView.findViewById(R.id.textViewWord);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClicked(suggestions.get(getAdapterPosition()));
        }
    }

    public interface ClickListener{
        void onItemClicked(String word);
    }
}
