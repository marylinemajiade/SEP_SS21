package RMI;

import java.io.Serializable;

public class SpielStarten  implements Serializable {
    private Integer raumId ;
    private String message;

    public SpielStarten(Integer raumId){
        this.raumId = raumId;
    }

    public Integer getRaumId() {
        return raumId;
    }
}
