package dao;

import model.Note;

/**
 * Interface for Dao
 */
public interface IRepository {
    /**
     * Insert Note in DB
     * @param note to insert
     */
    public void insertNote(Note note);

    /**
     * Insert Note in Db
     * @param id of note to remove id DB
     */
    public void deleteNote(String id);

    /**
     * Delete all Notes in DB
     */
    public void deleteAllNotes();

}
