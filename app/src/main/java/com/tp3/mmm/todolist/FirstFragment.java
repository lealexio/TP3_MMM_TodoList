package com.tp3.mmm.todolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.tp3.mmm.todolist.databinding.FragmentFirstBinding;
import model.Note;
import dao.DAONote;


public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_first,container,false);
        setHasOptionsMenu(true);
        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize DAO
        DAONote daoNote = new DAONote();

        // Action on valid button
        binding.buttonValid.setOnClickListener(view1 -> {
            // Check if user inputs are valid to create a note
            if(isNoteValid()) {
                Note note = new Note(binding.editTitle.getText().toString(), binding.editContent.getText().toString(), binding.spinnerPriority.getSelectedItem().toString());
                // Insert note in Realtime DB thanks to DAO
                daoNote.insertNote(note);
                // Redirect user to second fragment
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
            }else{
                Toast.makeText(getActivity().getApplicationContext(),"Please, fill all inputs", Toast.LENGTH_SHORT ).show();
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu , MenuInflater inflater) {
        inflater.inflate(R.menu.menu_second_fragment, menu);
        super.onCreateOptionsMenu(menu , inflater );
    }

    /**
     * Perform action when menu item selected
     * @param item selected
     * @return true if an action is performed else false
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.resetUserData){
            cleanInputs();
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Check if all inputs are valid to create a note
     * @return true if valid else false
     */
    public boolean isNoteValid(){
        return !binding.editTitle.getText().toString().isEmpty() && !binding.editContent.getText().toString().isEmpty() && !binding.spinnerPriority.getSelectedItem().toString().isEmpty();
    }

    /**
     * Clean all user inputs
     */
    public void cleanInputs(){
        binding.editContent.setText("");
        binding.editTitle.setText("");
        binding.spinnerPriority.setSelection(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}