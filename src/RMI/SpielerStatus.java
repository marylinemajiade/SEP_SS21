package RMI;

import java.io.Serializable;

public class SpielerStatus implements Serializable {
    private boolean isOnline;
    private String username;
    public SpielerStatus(String username, boolean isOnline){
        this.username = username;
        this.isOnline = isOnline;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public String getUsername() {
        return username;
    }
}
