package com.jslps.pgmisnew.util;

import android.annotation.SuppressLint;
import android.util.Log;

import com.jslps.pgmisnew.PgActivity;
import com.jslps.pgmisnew.database.Blocktbl;
import com.jslps.pgmisnew.database.Clustertbl;
import com.jslps.pgmisnew.database.Logintbl;
import com.jslps.pgmisnew.database.Pgtbl;
import com.jslps.pgmisnew.database.Villagetbl;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

public class ManualJsonConvert {
    private String tblIdentifier;

    public ManualJsonConvert(String tblIdentifier) {
        this.tblIdentifier = tblIdentifier;
    }

    @SuppressLint("LongLogTag")
    public String ConvertJson(){
        String JsonString="";

        if(tblIdentifier.equals("tblProducerGroupMembers")){

            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblProducerGroupMembers\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");

            String userId="";
            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
            }
            for (int i = 0; i< PgActivity.pgmemtblList.size(); i++){
                String districtCode="",blockcode="",clusterCode="",villageCode="";

                List<Pgtbl> pgtblList = Select.from(Pgtbl.class)
                        .where(Condition.prop("Pgcode").eq(PgActivity.pgmemtblList.get(i).getPgcode()))
                        .list();

                if(pgtblList.size()>0){
                    villageCode = pgtblList.get(0).getVillagecode();

                    List<Villagetbl> villagetblList = Select.from(Villagetbl.class)
                            .where(Condition.prop("Villagecode").eq(villageCode))
                            .list();
                    if(villagetblList.size()>0){
                        clusterCode = villagetblList.get(0).getClustercode();
                        List<Clustertbl> clustertblList = Select.from(Clustertbl.class)
                                .where(Condition.prop("Clustercode").eq(clusterCode))
                                .list();
                        blockcode = clustertblList.get(0).getBlockcode();

                        List<Blocktbl> blocktblList = Select.from(Blocktbl.class)
                                .where(Condition.prop("Blockcode").eq(blockcode))
                                .list();
                        if(blocktblList.size()>0){
                            districtCode = blocktblList.get(0).getDistrictcode();
                        }
                    }
                }

                lStringBuilder.append("{");
                lStringBuilder.append("\"Blockcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+blockcode+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Districtcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+districtCode+"\"");

                lStringBuilder.append(",");
                lStringBuilder.append("\"ClusterCode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+clusterCode+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"VillageCode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+villageCode+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"MembershipFee\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemtblList.get(i).getMembershipfee()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"ShareCapital\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemtblList.get(i).getSharecapital()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"PGCode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemtblList.get(i).getPgcode()+"\"");
                lStringBuilder.append(",");

                String guid,membercode;

                guid =PgActivity.pgmemtblList.get(i).getUid();
                if(!guid.equals("")){
                    membercode = "0";
                }else{
                    membercode = PgActivity.pgmemtblList.get(i).getGrpmemcode();
                }

                lStringBuilder.append("\"Guid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemtblList.get(i).getUid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"GroupCode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemtblList.get(i).getGrpcode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Group_M_Code\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+membercode+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"FatherName\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemtblList.get(i).getFathername()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"HusbandName\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemtblList.get(i).getHusbandname()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Designation\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemtblList.get(i).getDesignation()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"StateCode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+"20"+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"PrimaryActivity\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemtblList.get(i).getPrimaryactivity()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Fishery\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemtblList.get(i).getFishery()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"HVA\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemtblList.get(i).getHva()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Ntfp\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemtblList.get(i).getNtfp()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"CreatedDate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+""+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"createdBy\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+userId+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"IsTablet\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+"1"+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"FlagStatus\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+"1"+"\"");

                lStringBuilder.append("}");
                if(i<PgActivity.pgmemtblList.size()-1) {
                    lStringBuilder.append(",");
                }

            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
        }

        //next if
        if(tblIdentifier.equals("tblMstGroupMembers_Johar")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblMstGroupMembers_Johar\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");
            String userId="";
            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
            }

            for (int i = 0; i< PgActivity.shgmemberslocallyaddedtblList.size(); i++){
                String districtCode="",blockcode="",clusterCode="",villageCode="";

                List<Pgtbl> pgtblList = Select.from(Pgtbl.class)
                        .where(Condition.prop("Pgcode").eq(PgActivity.pgmemtblList.get(i).getPgcode()))
                        .list();
                if(pgtblList.size()>0){
                    villageCode = pgtblList.get(0).getVillagecode();

                    List<Villagetbl> villagetblList = Select.from(Villagetbl.class)
                            .where(Condition.prop("Villagecode").eq(villageCode))
                            .list();
                    if(villagetblList.size()>0){
                        clusterCode = villagetblList.get(0).getClustercode();
                        List<Clustertbl> clustertblList = Select.from(Clustertbl.class)
                                .where(Condition.prop("Clustercode").eq(clusterCode))
                                .list();
                        blockcode = clustertblList.get(0).getBlockcode();

                        List<Blocktbl> blocktblList = Select.from(Blocktbl.class)
                                .where(Condition.prop("Blockcode").eq(blockcode))
                                .list();
                        if(blocktblList.size()>0){
                            districtCode = blocktblList.get(0).getDistrictcode();
                        }
                    }
                }
                lStringBuilder.append("{");
                lStringBuilder.append("\"Blockcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+blockcode+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Districtcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+districtCode+"\"");

                lStringBuilder.append(",");

                lStringBuilder.append("\"ClusterCode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+clusterCode+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"VillageCode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+villageCode+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createdby\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+userId+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createdon\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+""+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Guid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.shgmemberslocallyaddedtblList.get(i).getUid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"GroupCode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.shgmemberslocallyaddedtblList.get(i).getGrpcode()+"\"");
                lStringBuilder.append(",");

                String guid,membercode;

                guid =PgActivity.shgmemberslocallyaddedtblList.get(i).getUid();
                if(!guid.equals("")){
                    membercode = "0";
                }else{
                    membercode = PgActivity.shgmemberslocallyaddedtblList.get(i).getGrpmemcode();
                }

                lStringBuilder.append("\"Group_M_Code\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+membercode+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Gender\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.shgmemberslocallyaddedtblList.get(i).getGender()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"CastCategory\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.shgmemberslocallyaddedtblList.get(i).getCast()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Status\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+"1"+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"StateCode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+"20"+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"MemberName\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.shgmemberslocallyaddedtblList.get(i).getMembername()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"FatherHusbandMothNm\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.shgmemberslocallyaddedtblList.get(i).getFathername()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"LiveStock\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.shgmemberslocallyaddedtblList.get(i).getLivestock()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"IsTablet\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+"1"+"\"");

                lStringBuilder.append("}");
                if(i<PgActivity.shgmemberslocallyaddedtblList.size()-1) {
                    lStringBuilder.append(",");
                }
            }

            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
        }

        //next if
        if(tblIdentifier.equals("PgMeetingtbl")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"PgMeetingtbl\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");
            String userId="";
            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
            }
            for (int i = 0; i< PgActivity.pgMeetingtblList.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"Meetingid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgMeetingtblList.get(i).getMeetingid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Meetingdate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgMeetingtblList.get(i).getMeetingdate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Noofpeople\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgMeetingtblList.get(i).getNoofpeople()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgMeetingtblList.get(i).getPgcode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Cadres\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgMeetingtblList.get(i).getCadres()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"CreatedOn\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+""+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"CreatedBy\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+userId+"\"");

                lStringBuilder.append("}");
                if(i<PgActivity.pgMeetingtblList.size()-1) {
                    lStringBuilder.append(",");
                }
            }

            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
        }

        //next if
        if(tblIdentifier.equals("tblmst_PgPaymentTranst")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_PgPaymentTranst\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");
            String userId="";
            String username="";

            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
                username= logintblList.get(0).getUsername();
            }

            for (int i = 0; i< PgActivity.pgPaymentTranstblList.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"uuid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgPaymentTranstblList.get(i).getUuid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"budgetcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgPaymentTranstblList.get(i).getBudgetcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"headname\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgPaymentTranstblList.get(i).getHeadname()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"CreatedDate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgPaymentTranstblList.get(i).getDate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"amount\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgPaymentTranstblList.get(i).getAmount());
                lStringBuilder.append(",");

                lStringBuilder.append("\"remark\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgPaymentTranstblList.get(i).getRemark()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgPaymentTranstblList.get(i).getPgcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"createdby\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+username+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"createdid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+userId+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgPaymentTranstblList.get(i).getIsexported());
                lStringBuilder.append(",");

                lStringBuilder.append("\"paymentmode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgPaymentTranstblList.get(i).getPaymentmode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"qty\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgPaymentTranstblList.get(i).getQty());
                lStringBuilder.append(",");

                lStringBuilder.append("\"paymentunit\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgPaymentTranstblList.get(i).getPaymentunit()+"\"");

                lStringBuilder.append("}");
                if(i<PgActivity.pgPaymentTranstblList.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
            Log.d("tblmst_PgPaymentTranst",JsonString);
        }

        if(tblIdentifier.equals("tblmst_PgCapitalSave")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_PgCapitalSave\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");

            for (int i = 0; i< PgActivity.pgCapitalSavetbls.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"Pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgCapitalSavetbls.get(i).getPgcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Grpmemcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgCapitalSavetbls.get(i).getGrpmemcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Grpcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgCapitalSavetbls.get(i).getGrpcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Membername\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgCapitalSavetbls.get(i).getMembername()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"amount\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgCapitalSavetbls.get(i).getAmount());
                lStringBuilder.append(",");

                lStringBuilder.append("\"isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgCapitalSavetbls.get(i).getIsexported());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Uid\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgCapitalSavetbls.get(i).getUid());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createddate\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgCapitalSavetbls.get(i).getCreateddate());
                lStringBuilder.append(",");

                lStringBuilder.append("\"paymentmode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgCapitalSavetbls.get(i).getPaymentmode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"paymentdate\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgCapitalSavetbls.get(i).getPaymentdate());

                lStringBuilder.append("}");
                if(i<PgActivity.pgCapitalSavetbls.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
            Log.d("tblmst_PgCapitalSave",JsonString);
        }

        if(tblIdentifier.equals("tblmst_Pgmisbrstranstbl")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_Pgmisbrstranstbl\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");

            for (int i = 0; i< PgActivity.pgmisbrstranstbls.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"uuid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisbrstranstbls.get(i).getUuid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"date\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisbrstranstbls.get(i).getDate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"balcashbook\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisbrstranstbls.get(i).getBalcashbook()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"balpassbook\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisbrstranstbls.get(i).getBalpassbook()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"passbooklastpageimg\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisbrstranstbls.get(i).getPassbooklastpageimg()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"cashbooklastpageimg\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisbrstranstbls.get(i).getCashbooklastpageimg()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisbrstranstbls.get(i).getIsexported());
                lStringBuilder.append(",");

                lStringBuilder.append("\"entrydate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisbrstranstbls.get(i).getEntrydate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisbrstranstbls.get(i).getPgcode());

                lStringBuilder.append("}");
                if(i<PgActivity.pgmisbrstranstbls.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
            Log.d("tblmst_Pgmisbrstranstbl",JsonString);
        }

        if(tblIdentifier.equals("tblmst_PgMemShipFeeSave")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_PgMemShipFeeSave\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");

            for (int i = 0; i< PgActivity.pgMemShipFeeSavetbls.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"Pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgMemShipFeeSavetbls.get(i).getPgcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Grpmemcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgMemShipFeeSavetbls.get(i).getGrpmemcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Grpcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgMemShipFeeSavetbls.get(i).getGrpcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Membername\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgMemShipFeeSavetbls.get(i).getMembername()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"amount\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgMemShipFeeSavetbls.get(i).getAmount());
                lStringBuilder.append(",");

                lStringBuilder.append("\"isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgMemShipFeeSavetbls.get(i).getIsexported());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Uid\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgMemShipFeeSavetbls.get(i).getUid());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createddate\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgMemShipFeeSavetbls.get(i).getCreateddate());
                lStringBuilder.append(",");

                lStringBuilder.append("\"paymentmode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgMemShipFeeSavetbls.get(i).getPaymentmode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"paymentdate\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgMemShipFeeSavetbls.get(i).getPaymentdate());

                lStringBuilder.append("}");
                if(i<PgActivity.pgMemShipFeeSavetbls.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
            Log.d("tblmst_PgMemShipFeeSave",JsonString);
        }

        if(tblIdentifier.equals("tblmst_Pgmisbrstransaddsubtbl")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_Pgmisbrstransaddsubtbl\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");

            for (int i = 0; i< PgActivity.pgmisbrstransaddsubtbls.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"uuid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisbrstransaddsubtbls.get(i).getUuid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"particularcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisbrstransaddsubtbls.get(i).getParticularcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"particularflag\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisbrstransaddsubtbls.get(i).getParticularflag()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"amount\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisbrstransaddsubtbls.get(i).getAmount());
                lStringBuilder.append(",");

                lStringBuilder.append("\"entrydate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisbrstransaddsubtbls.get(i).getEntrydate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisbrstransaddsubtbls.get(i).getIsexported());
                lStringBuilder.append(",");

                lStringBuilder.append("\"pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisbrstransaddsubtbls.get(i).getPgcode());

                lStringBuilder.append("}");
                if(i<PgActivity.pgmisbrstransaddsubtbls.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
            Log.d("tblmst_Pgmisbrstransaddsubtbl",JsonString);
        }

        if(tblIdentifier.equals("tblmst_PgmisLoantbl")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_PgmisLoantbl\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");

            for (int i = 0; i< PgActivity.pgmisLoantbls.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"uuid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisLoantbls.get(i).getUuid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisLoantbls.get(i).getPgcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"grpcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisLoantbls.get(i).getGrpcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"grpmemcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisLoantbls.get(i).getGrpmemcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"itemcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisLoantbls.get(i).getItemcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"itemname\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisLoantbls.get(i).getItemname()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"rate\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisLoantbls.get(i).getRate());
                lStringBuilder.append(",");

                lStringBuilder.append("\"unit\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisLoantbls.get(i).getUnit()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisLoantbls.get(i).getIsexported());
                lStringBuilder.append(",");

                lStringBuilder.append("\"entrydate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisLoantbls.get(i).getEntrydate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"entryby\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisLoantbls.get(i).getEntryby()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"appliedforloan\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisLoantbls.get(i).getAppliedforloan()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"quantity\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisLoantbls.get(i).getQuantity());
                lStringBuilder.append(",");

                lStringBuilder.append("\"paymentdate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisLoantbls.get(i).getPaymentdate()+"\"");

                lStringBuilder.append("}");
                if(i<PgActivity.pgmisLoantbls.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
            Log.d("tblmst_PgmisLoantbl",JsonString);
        }

        return JsonString;
    }
}
