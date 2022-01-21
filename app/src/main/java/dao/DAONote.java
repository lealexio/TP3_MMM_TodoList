package dao;

import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import model.Note;

public class DAONote implements IRepository{
    private final DatabaseReference databaseReference;

    /**
     * Dao implementation for Firebase Realtime
     */
    public DAONote(){
        databaseReference = FirebaseDatabase.getInstance().getReference(Note.class.getSimpleName());
    }

    @Override
    public void insertNote(Note note) {
        Log.d("FIREBASE", "Add new note");
        databaseReference.push().setValue(note);
    }

    @Override
    public void deleteNote(String id) {
        databaseReference.child(id).removeValue();
        Log.d("FIREBASE", "Delete note " + id);
    }

    @Override
    public void deleteAllNotes() {
        databaseReference.removeValue();
        Log.d("FIREBASE", "Delete all notes");
    }

}
