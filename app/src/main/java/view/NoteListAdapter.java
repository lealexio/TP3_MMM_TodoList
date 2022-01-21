package view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tp3.mmm.todolist.R;
import model.Note;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for RecyclerView
 */
public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteHolder> {
    // List of current notes in recycler
    private List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.title.setText(currentNote.getTitle());
        holder.content.setText(currentNote.getContent());
        holder.priority.setText(currentNote.getPriority());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    /**
     * Set list of notes to display in RecyclerView
     * @param notes to display
     */
    public void setNotes(List<Note> notes) {
        this.notes = notes;
        // Refresh view
        notifyDataSetChanged();
    }

    /**
     * Note view holder that describes an item view and metadata about its place within the RecyclerView
     */
    static class NoteHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView content;
        private final TextView priority;

        public NoteHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            priority = itemView.findViewById(R.id.priority);
        }
    }
}