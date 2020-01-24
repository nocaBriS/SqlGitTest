/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author bambl
 */
public enum geschlecht {

    MÄNNLICH("M"), WEIBLICH("F"), DIVERS("X");
    public final String label;

    private geschlecht(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static geschlecht byValue(String label) {
        switch (label) {
            case "M":
                return MÄNNLICH;
            case "F":
                return WEIBLICH;
            case "X":
                return DIVERS;
            default:
                return null;

        }

    }

}
