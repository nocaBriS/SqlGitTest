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
public class land {

    String abkz;
    String land;

    public land(String abkz, String land) {
        this.abkz = abkz;
        this.land = land;
    }

    public String getAbkz() {
        return abkz;
    }

    public void setAbkz(String abkz) {
        this.abkz = abkz;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

}
