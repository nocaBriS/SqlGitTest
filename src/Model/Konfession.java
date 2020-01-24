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
public class Konfession {

    String akbz;
    String Konfession;

    public Konfession(String akbz, String Konfession) {
        this.akbz = akbz;
        this.Konfession = Konfession;
    }

    public String getAkbz() {
        return akbz;
    }

    public void setAkbz(String akbz) {
        this.akbz = akbz;
    }

    public String getKonfession() {
        return Konfession;
    }

    public void setKonfession(String Konfession) {
        this.Konfession = Konfession;
    }

}
