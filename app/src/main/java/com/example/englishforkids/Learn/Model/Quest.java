package com.example.englishforkids.Learn.Model;

import java.io.Serializable;

public class Quest implements Serializable {
   public String ID, ND, DA1, DA2, DA3, DA4, DAD;

    public Quest() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getND() {
        return ND;
    }

    public void setND(String ND) {
        this.ND = ND;
    }

    public String getDA1() {
        return DA1;
    }

    public void setDA1(String DA1) {
        this.DA1 = DA1;
    }

    public String getDA2() {
        return DA2;
    }

    public void setDA2(String DA2) {
        this.DA2 = DA2;
    }

    public String getDA3() {
        return DA3;
    }

    public void setDA3(String DA3) {
        this.DA3 = DA3;
    }

    public String getDA4() {
        return DA4;
    }

    public void setDA4(String DA4) {
        this.DA4 = DA4;
    }

    public String getDAD() {
        return DAD;
    }

    public void setDAD(String DAD) {
        this.DAD = DAD;
    }
}
