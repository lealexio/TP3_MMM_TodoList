package com.tp3.mmm.todolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tp3.mmm.todolist.databinding.FragmentSecondBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import dao.DAONote;
import model.Note;
import view.NoteListAdapter;


public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    // DatabaseReference needed for db listening
    DatabaseReference databaseReference;
    // List of note objects
    List<Note> noteList;
    // List of note Id in database
    List<String> noteIdList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DAONote daoNote = new DAONote();

        RecyclerView recyclerView = view.findViewById(R.id.notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference(Note.class.getSimpleName());

        noteList =  new ArrayList<>();
        noteIdList =  new ArrayList<>();

        final NoteListAdapter adapter = new NoteListAdapter();
        recyclerView.setAdapter(adapter);

        // Listener on realtime database, it update noteList and noteIdList when database change
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                noteList =  new ArrayList<>();
                noteIdList =  new ArrayList<>();
                // Loop on each note in db
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    // Create Note object
                    Note note = dataSnapshot1.getValue(Note.class);
                    //Add Note to noteList (displayed list)
                    noteList.add(note);
                    // Add note id to noteIdList
                    noteIdList.add(dataSnapshot1.getKey());
                }
                adapter.setNotes(noteList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Not implemented
            }
        });

        // Listen user interactions on cells
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                // Not implemented
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // When user swipe note, delete it thanks to DAO
                daoNote.deleteNote(noteIdList.get(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        // Listener on New Note button that redirect to first fragment
        view.findViewById(R.id.createNote).setOnClickListener(view1 -> NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_FirstFragment));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu , MenuInflater inflater) {
        inflater.inflate(R.menu.menu_second_fragment , menu);
        super.onCreateOptionsMenu(menu , inflater );
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}