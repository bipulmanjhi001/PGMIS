package com.jslps.pgmisnew.database;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class PhCollectingInputnewtbl extends SugarRecord {

    @Unique
    private String uuid;
    private String pgcode;
    private String grpmemcode;
    private String grpcode;
    private String adapterposition;
    private String selecteddate;
    private String type;
    private String itemname;
    private String qty;
    private String rate;
    private String isexported;
    private String createdBy;
    private String createdon;
    boolean isSelected;

    public PhCollectingInputnewtbl(){

    }

    public PhCollectingInputnewtbl(String uuid, String pgcode, String grpmemcode, String grpcode, String adapterposition, String selecteddate, String type, String itemname, String qty, String rate, String isexported, String createdBy, String createdon, boolean isSelected) {
        this.uuid = uuid;
        this.pgcode = pgcode;
        this.grpmemcode = grpmemcode;
        this.grpcode = grpcode;
        this.adapterposition = adapterposition;
        this.selecteddate = selecteddate;
        this.type = type;
        this.itemname = itemname;
        this.qty = qty;
        this.rate = rate;
        this.isexported = isexported;
        this.createdBy = createdBy;
        this.createdon = createdon;
        this.isSelected = isSelected;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPgcode() {
        return pgcode;
    }

    public void setPgcode(String pgcode) {
        this.pgcode = pgcode;
    }

    public String getGrpmemcode() {
        return grpmemcode;
    }

    public void setGrpmemcode(String grpmemcode) {
        this.grpmemcode = grpmemcode;
    }

    public String getGrpcode() {
        return grpcode;
    }

    public void setGrpcode(String grpcode) {
        this.grpcode = grpcode;
    }

    public String getAdapterposition() {
        return adapterposition;
    }

    public void setAdapterposition(String adapterposition) {
        this.adapterposition = adapterposition;
    }

    public String getSelecteddate() {
        return selecteddate;
    }

    public void setSelecteddate(String selecteddate) {
        this.selecteddate = selecteddate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getIsexported() {
        return isexported;
    }

    public void setIsexported(String isexported) {
        this.isexported = isexported;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedon() {
        return createdon;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
