package edu.utsa.cs3443.universityeats;

public class CafeReviews {
    private String title;
    private String content;

    private String userEmail;

    public CafeReviews(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String review){

        this.title = review;
    }

    public String getContent(){

        return content;
    }

    public void setContent(String content){

        this.content = content;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    public CafeReviews(String title, String content){
        this.title = title;
        this.content = content;
        this.userEmail = userEmail;


    }


}
