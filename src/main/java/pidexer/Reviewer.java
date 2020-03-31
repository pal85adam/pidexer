package pidexer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reviewer {
    String personID;
    Date date;
    int score;

    public Reviewer(){
        this.personID = "";
        this.date = null;
        this.score = 0;
    }

    public Reviewer(String personID, Date date, int score){
        this.personID = personID;
        this.date = date;
        this.score = score;
    }

    public String toString(){
        return "Person ID: " + personID
                + ", Date: " + new SimpleDateFormat("yyyy-MM-dd").format(date)
                + ", Score: " + score;
    }
}
