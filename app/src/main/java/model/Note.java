package model;

/**
 * Implementation of a Note
 */
public class Note {

     private String title;

     private String content;

     private String priority;

     /**
      * Empty Note constructor
      */
     public Note(){

     }

     /**
      * Note constructor
      * @param title of note
      * @param content of note
      * @param priority of note
      */
     public Note(String title, String content, String priority) {
          this.title = title;
          this.content = content;
          this.priority = priority;
     }

     /**
      * Get title of note
      * @return title
      */
     public String getTitle() {
          return title;
     }

     /**
      * Set title of note
      * @param title to set
      */
     public void setTitle(String title) {
          this.title = title;
     }

     /**
      * Get content of note
      * @return content
      */
     public String getContent() {
          return content;
     }

     /**
      * Set content of note
      * @param content to set
      */
     public void setContent(String content) {
          this.content = content;
     }

     /**
      * Get priority of note
      * @return priority
      */
     public String getPriority() {
          return priority;
     }

     /**
      * Set priority of note
      * @param priority to set
      */
     public void setPriority(String priority) {
          this.priority = priority;
     }
}
