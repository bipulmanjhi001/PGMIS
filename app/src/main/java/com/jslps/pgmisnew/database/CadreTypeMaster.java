package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;

public class CadreTypeMaster extends SugarRecord {

    private String Cadretypeid;
    private String Cadrecbsid;
    private String Cadredesignation;
    private String CadredesignationHin;

    public CadreTypeMaster(){

    }

    public CadreTypeMaster(String cadretypeid, String cadrecbsid, String cadredesignation, String cadredesignationHin) {
        Cadretypeid = cadretypeid;
        Cadrecbsid = cadrecbsid;
        Cadredesignation = cadredesignation;
        CadredesignationHin = cadredesignationHin;
    }

    public String getCadretypeid() {
        return Cadretypeid;
    }

    public void setCadretypeid(String cadretypeid) {
        Cadretypeid = cadretypeid;
    }

    public String getCadrecbsid() {
        return Cadrecbsid;
    }

    public void setCadrecbsid(String cadrecbsid) {
        Cadrecbsid = cadrecbsid;
    }

    public String getCadredesignation() {
        return Cadredesignation;
    }

    public void setCadredesignation(String cadredesignation) {
        Cadredesignation = cadredesignation;
    }

    public String getCadredesignationHin() {
        return CadredesignationHin;
    }

    public void setCadredesignationHin(String cadredesignationHin) {
        CadredesignationHin = cadredesignationHin;
    }
}
