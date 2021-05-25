package Kommunikationsverwaltung;


public class Nachricht implements Comparable<Nachricht> {

    int nachrichtId;
    String spielerName;
    String message;
    String dateTime;

    public Nachricht(int nachrichtId, String spielerName, String message, String dateTime) {
        this.nachrichtId = nachrichtId;
        this.spielerName = spielerName;
        this.message = message;
        this.dateTime = dateTime;
    }


    @Override
    public int compareTo(Nachricht o) {
        if (this.nachrichtId < o.nachrichtId)
            return 1;
        else if (this.nachrichtId>o.nachrichtId)
                return -1;
        return 0;
    }
}
