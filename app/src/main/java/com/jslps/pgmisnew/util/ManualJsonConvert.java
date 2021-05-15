package com.jslps.pgmisnew.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
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
    Context context;
    String myVersionName;

    public ManualJsonConvert(String tblIdentifier,Context context) {
        this.tblIdentifier = tblIdentifier;
        this.context=context;
    }

    @SuppressLint("LongLogTag")
    public String ConvertJson(){
        String JsonString="";
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        myVersionName = "not available";

        try {
            myVersionName = packageManager.getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

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
                lStringBuilder.append("\""+ PgActivity.pgmemtblList.get(i).getMembershipfee()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"ShareCapital\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmemtblList.get(i).getSharecapital()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"PGCode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmemtblList.get(i).getPgcode()+"\"");
                lStringBuilder.append(",");

                String guid,membercode;

                guid = PgActivity.pgmemtblList.get(i).getUid();
                if(!guid.equals("")){
                    membercode = "0";
                }else{
                    membercode = PgActivity.pgmemtblList.get(i).getGrpmemcode();
                }

                lStringBuilder.append("\"Guid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmemtblList.get(i).getUid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"GroupCode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmemtblList.get(i).getGrpcode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Group_M_Code\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+membercode+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"FatherName\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmemtblList.get(i).getFathername()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"HusbandName\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmemtblList.get(i).getHusbandname()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Designation\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmemtblList.get(i).getDesignation()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"StateCode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+"20"+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"PrimaryActivity\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmemtblList.get(i).getPrimaryactivity()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Fishery\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmemtblList.get(i).getFishery()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"HVA\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmemtblList.get(i).getHva()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Ntfp\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmemtblList.get(i).getNtfp()+"\"");
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
                if(i< PgActivity.pgmemtblList.size()-1) {
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
                lStringBuilder.append("\""+ PgActivity.shgmemberslocallyaddedtblList.get(i).getUid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"GroupCode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.shgmemberslocallyaddedtblList.get(i).getGrpcode()+"\"");
                lStringBuilder.append(",");

                String guid,membercode;

                guid = PgActivity.shgmemberslocallyaddedtblList.get(i).getUid();
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
                lStringBuilder.append("\""+ PgActivity.shgmemberslocallyaddedtblList.get(i).getGender()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"CastCategory\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.shgmemberslocallyaddedtblList.get(i).getCast()+"\"");
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
                lStringBuilder.append("\""+ PgActivity.shgmemberslocallyaddedtblList.get(i).getMembername()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"FatherHusbandMothNm\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.shgmemberslocallyaddedtblList.get(i).getFathername()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"LiveStock\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.shgmemberslocallyaddedtblList.get(i).getLivestock()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"IsTablet\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+"1"+"\"");

                lStringBuilder.append("}");
                if(i< PgActivity.shgmemberslocallyaddedtblList.size()-1) {
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
            String username="";

            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
                username= logintblList.get(0).getUsername();
            }

            for (int i = 0; i< PgActivity.pgMeetingtblList.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"Meetingid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgMeetingtblList.get(i).getMeetingid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Meetingdate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgMeetingtblList.get(i).getMeetingdate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Noofpeople\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgMeetingtblList.get(i).getNoofpeople()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgMeetingtblList.get(i).getPgcode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Cadres\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgMeetingtblList.get(i).getCadres()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"CreatedBy\"");
                lStringBuilder.append(":");
                lStringBuilder.append(username);
                lStringBuilder.append(",");

                lStringBuilder.append("\"AppVersion\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+myVersionName+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"CreatedOn\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+new GetCurrentDate().getDate()+"\"");

                lStringBuilder.append("}");
                if(i< PgActivity.pgMeetingtblList.size()-1) {
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
                lStringBuilder.append("\""+ PgActivity.pgPaymentTranstblList.get(i).getUuid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"budgetcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgPaymentTranstblList.get(i).getBudgetcode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"headname\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgPaymentTranstblList.get(i).getHeadname()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"date\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgPaymentTranstblList.get(i).getDate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"amount\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgPaymentTranstblList.get(i).getAmount());
                lStringBuilder.append(",");

                lStringBuilder.append("\"remark\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgPaymentTranstblList.get(i).getRemark()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgPaymentTranstblList.get(i).getPGCode()+"\"");
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
                lStringBuilder.append("\""+ PgActivity.pgPaymentTranstblList.get(i).getPaymentmode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"qty\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgPaymentTranstblList.get(i).getQty());
                lStringBuilder.append(",");

                lStringBuilder.append("\"paymentunit\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgPaymentTranstblList.get(i).getPaymentunit()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"createddate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ new GetCurrentDate().getDate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"AppVersion\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+myVersionName+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"BMID\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgPaymentTranstblList.get(i).getBMID());

                lStringBuilder.append("}");
                if(i< PgActivity.pgPaymentTranstblList.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
        }

        if(tblIdentifier.equals("tblmst_PgCapitalSave")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_PgCapitalSave\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");
            String userId="";
            String username="";

            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
                username= logintblList.get(0).getUsername();
            }

            for (int i = 0; i< PgActivity.pgcapitalsavetbls.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"Pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgcapitalsavetbls.get(i).getPgcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Grpmemcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgcapitalsavetbls.get(i).getGrpmemcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Grpcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgcapitalsavetbls.get(i).getGrpcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Membername\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgcapitalsavetbls.get(i).getMembername()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"amount\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgcapitalsavetbls.get(i).getAmount());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgcapitalsavetbls.get(i).getIsexported());
                lStringBuilder.append(",");

                lStringBuilder.append("\"uuid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgcapitalsavetbls.get(i).getUuid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"createddate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ new GetCurrentDate().getDate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Paymentdate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgcapitalsavetbls.get(i).getPaymentdate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Paymentmode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgcapitalsavetbls.get(i).getPaymentmode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"AppVersion\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+myVersionName+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"createdby\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+username+"\"");

                lStringBuilder.append("}");
                if(i< PgActivity.pgcapitalsavetbls.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
        }

        if(tblIdentifier.equals("tblmst_Pgmisbrstrans")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_Pgmisbrstrans\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");
            String userId="";
            String username="";

            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
                username= logintblList.get(0).getUsername();
            }

            for (int i = 0; i< PgActivity.pgmisbrstranstbls.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"uuid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmisbrstranstbls.get(i).getUuid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"date\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmisbrstranstbls.get(i).getDate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"balcashbook\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmisbrstranstbls.get(i).getBalcashbook()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"balpassbook\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmisbrstranstbls.get(i).getBalpassbook()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"passbooklastpageimg\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmisbrstranstbls.get(i).getPassbooklastpageimg()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"cashbooklastpageimg\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmisbrstranstbls.get(i).getCashbooklastpageimg()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisbrstranstbls.get(i).getIsexported());
                lStringBuilder.append(",");

                lStringBuilder.append("\"entrydate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmisbrstranstbls.get(i).getEntrydate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisbrstranstbls.get(i).getPgcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createddate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ new GetCurrentDate().getDate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"AppVersion\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+myVersionName+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"createdby\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+username+"\"");

                lStringBuilder.append("}");
                if(i< PgActivity.pgmisbrstranstbls.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
        }

        if(tblIdentifier.equals("tblmst_PgMemShipFeeSave")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_PgMemShipFeeSave\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");
            String userId="";
            String username="";

            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
                username= logintblList.get(0).getUsername();
            }

            for (int i = 0; i< PgActivity.pgmemshipfeesavetbls.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"Pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmemshipfeesavetbls.get(i).getPgcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Grpmemcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmemshipfeesavetbls.get(i).getGrpmemcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Grpcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmemshipfeesavetbls.get(i).getGrpcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Membername\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemshipfeesavetbls.get(i).getMembername()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"amount\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmemshipfeesavetbls.get(i).getAmount());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmemshipfeesavetbls.get(i).getIsexported());
                lStringBuilder.append(",");

                lStringBuilder.append("\"uid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemshipfeesavetbls.get(i).getUid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"createddate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ new GetCurrentDate().getDate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"paymentdate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemshipfeesavetbls.get(i).getPaymentdate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"paymentmode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmemshipfeesavetbls.get(i).getPaymentmode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"AppVersion\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+myVersionName+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"createdby\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+username+"\"");

                lStringBuilder.append("}");
                if(i< PgActivity.pgmemshipfeesavetbls.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
        }

        if(tblIdentifier.equals("tblmst_Pgmisbrstransaddsub")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_Pgmisbrstransaddsub\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");

            String userId="";
            String username="";

            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
                username= logintblList.get(0).getUsername();
            }

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
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createdby\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+username+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"AppVersion\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+myVersionName+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createddate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ new GetCurrentDate().getDate()+"\"");

                lStringBuilder.append("}");
                if(i< PgActivity.pgmisbrstransaddsubtbls.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
        }

        if(tblIdentifier.equals("tblmst_PgReceiptTrans")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_PgReceiptTrans\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");

            String userId="";
            String username="";

            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
                username= logintblList.get(0).getUsername();
            }

            for (int i = 0; i< PgActivity.pgReceiptTranstbls.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"id\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgReceiptTranstbls.get(i).getId()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"uuid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgReceiptTranstbls.get(i).getUuid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"budgetcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgReceiptTranstbls.get(i).getBudgetcode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"headname\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgReceiptTranstbls.get(i).getHeadname()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"date\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgReceiptTranstbls.get(i).getDate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"amount\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgReceiptTranstbls.get(i).getAmount());
                lStringBuilder.append(",");

                lStringBuilder.append("\"remark\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgReceiptTranstbls.get(i).getRemark()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgReceiptTranstbls.get(i).getPgcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"createdby\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgReceiptTranstbls.get(i).getCreatedby()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"createdid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgReceiptTranstbls.get(i).getCreatedid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgReceiptTranstbls.get(i).getIsexported());
                lStringBuilder.append(",");

                lStringBuilder.append("\"quantity\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgReceiptTranstbls.get(i).getQuantity());
                lStringBuilder.append(",");

                lStringBuilder.append("\"unittype\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgReceiptTranstbls.get(i).getUnittype()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createddate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+new GetCurrentDate().getDate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Paymentmode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgReceiptTranstbls.get(i).getPaymentmode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"AppVersion\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+myVersionName+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"BMID\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgReceiptTranstbls.get(i).getBMID());

                lStringBuilder.append("}");
                if(i< PgActivity.pgReceiptTranstbls.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
        }

        if(tblIdentifier.equals("tblmst_Itempurchasedbypg")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_Itempurchasedbypg\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");
            String userId="";
            String username="";

            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
                username= logintblList.get(0).getUsername();
            }

            for (int i = 0; i< PgActivity.itempurchasedbypgtbls.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"uuid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.itempurchasedbypgtbls.get(i).getUuid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"itemcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.itempurchasedbypgtbls.get(i).getItemcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"itemname\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.itempurchasedbypgtbls.get(i).getItemname()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"unit\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.itempurchasedbypgtbls.get(i).getUnit()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"rate\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.itempurchasedbypgtbls.get(i).getRate());
                lStringBuilder.append(",");

                lStringBuilder.append("\"quantity\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.itempurchasedbypgtbls.get(i).getQuantity());
                lStringBuilder.append(",");

                lStringBuilder.append("\"budgetname\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.itempurchasedbypgtbls.get(i).getBudgetname()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"budgetcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.itempurchasedbypgtbls.get(i).getBudgetcode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.itempurchasedbypgtbls.get(i).getPgcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"entrydate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.itempurchasedbypgtbls.get(i).getEntrydate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.itempurchasedbypgtbls.get(i).getIsexported());
                lStringBuilder.append(",");

                lStringBuilder.append("\"paymentmode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.itempurchasedbypgtbls.get(i).getPaymentmode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"selecteddate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.itempurchasedbypgtbls.get(i).getSelecteddate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createddate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ new GetCurrentDate().getDate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createdby\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+username+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"AppVersion\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+myVersionName+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"BMID\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.itempurchasedbypgtbls.get(i).getBMID());

                lStringBuilder.append("}");
                if(i< PgActivity.itempurchasedbypgtbls.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
        }

        if(tblIdentifier.equals("tblmst_PgmisBatchLoan")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_PgmisBatchLoan\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");
            String userId="";
            String username="";

            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
                username= logintblList.get(0).getUsername();
            }

            for (int i = 0; i< PgActivity.pgmisBatchLoantbls.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"loanid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisBatchLoantbls.get(i).getLoanid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"itemuuids\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisBatchLoantbls.get(i).getItemuuids()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisBatchLoantbls.get(i).getPgcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"grpcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisBatchLoantbls.get(i).getGrpcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"grpmemcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisBatchLoantbls.get(i).getGrpmemcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"entrydate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisBatchLoantbls.get(i).getEntrydate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisBatchLoantbls.get(i).getIsexported());
                lStringBuilder.append(",");

                lStringBuilder.append("\"amount\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisBatchLoantbls.get(i).getAmount());
                lStringBuilder.append(",");

                lStringBuilder.append("\"paymentmode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisBatchLoantbls.get(i).getPaymentmode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createdby\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+username+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"AppVersion\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+myVersionName+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createddate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ new GetCurrentDate().getDate()+"\"");

                lStringBuilder.append("}");
                if(i< PgActivity.pgmisBatchLoantbls.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
        }

        if(tblIdentifier.equals("tblmst_Pgmisloanrepayment")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_Pgmisloanrepayment\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");

            String userId="";
            String username="";

            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
                username= logintblList.get(0).getUsername();
            }

            for (int i = 0; i< PgActivity.pgmisloanrepaymenttabls.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisloanrepaymenttabls.get(i).getPgcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"uuid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisloanrepaymenttabls.get(i).getUuid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"loanid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisloanrepaymenttabls.get(i).getLoanid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"entrydate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisloanrepaymenttabls.get(i).getEntrydate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"amount\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisloanrepaymenttabls.get(i).getAmount());
                lStringBuilder.append(",");

                lStringBuilder.append("\"isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisloanrepaymenttabls.get(i).getIsexported());
                lStringBuilder.append(",");

                lStringBuilder.append("\"selectedPaymentMode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisloanrepaymenttabls.get(i).getSelectedPaymentMode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"paymentdate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisloanrepaymenttabls.get(i).getPaymentdate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createddate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ new GetCurrentDate().getDate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"AppVersion\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+myVersionName+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createdby\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+username+"\"");

                lStringBuilder.append("}");
                if(i< PgActivity.pgmisloanrepaymenttabls.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
         }

        if(tblIdentifier.equals("tblmst_PgBankwithdrawcashdeposit")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_PgBankwithdrawcashdeposit\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");
            String userId="";
            String username="";

            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
                username= logintblList.get(0).getUsername();
            }

            for (int i = 0; i< PgActivity.pgBankwithdrawcashdeposits.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"uuid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgBankwithdrawcashdeposits.get(i).getUuid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Headname\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgBankwithdrawcashdeposits.get(i).getHeadname()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Date\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgBankwithdrawcashdeposits.get(i).getDate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Amount\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgBankwithdrawcashdeposits.get(i).getAmount());
                lStringBuilder.append(",");

                lStringBuilder.append("\"PGCode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgBankwithdrawcashdeposits.get(i).getPGCode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgBankwithdrawcashdeposits.get(i).getIsexported());
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createdid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+userId+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"AppVersion\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+myVersionName+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createdby\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+username+"\"");

                lStringBuilder.append("}");
                if(i< PgActivity.pgBankwithdrawcashdeposits.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
        }

        if(tblIdentifier.equals("tblmst_PgmisChequeLoan")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblmst_PgmisChequeLoan\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");
            String userId="";
            String username="";

            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
                username= logintblList.get(0).getUsername();
            }

            for (int i = 0; i< PgActivity.pgmisChequeLoantbls.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"uuid\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ PgActivity.pgmisChequeLoantbls.get(i).getUuid()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisChequeLoantbls.get(i).getPgcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"grpcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisChequeLoantbls.get(i).getGrpcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"grpmemcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisChequeLoantbls.get(i).getGrpmemcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisChequeLoantbls.get(i).getIsexported());
                lStringBuilder.append(",");

                lStringBuilder.append("\"entrydate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisChequeLoantbls.get(i).getEntrydate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"entryby\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+username+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"appliedforloan\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisChequeLoantbls.get(i).getAppliedforloan());
                lStringBuilder.append(",");

                lStringBuilder.append("\"chequedate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisChequeLoantbls.get(i).getChequedate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"amount\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.pgmisChequeLoantbls.get(i).getAmount());
                lStringBuilder.append(",");

                lStringBuilder.append("\"remark\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisChequeLoantbls.get(i).getRemark()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"paymentmode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.pgmisChequeLoantbls.get(i).getPaymentmode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"AppVersion\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+myVersionName+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"Createddate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ new GetCurrentDate().getDate()+"\"");

                lStringBuilder.append("}");
                if(i< PgActivity.pgmisChequeLoantbls.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
        }

        if(tblIdentifier.equals("tblPgMeetingCaders")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"tblPgMeetingCaders\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");

            for (int i = 0; i< PgActivity.tblPgMeetingCaders.size(); i++){

                String strMain=   PgActivity.tblPgMeetingCaders.get(i).getCadreid();
                String Meetingid=PgActivity.tblPgMeetingCaders.get(i).getMeetingid();
                String Pgcode=PgActivity.tblPgMeetingCaders.get(i).getPgcode();
                String Isxported=PgActivity.tblPgMeetingCaders.get(i).getIsxported();
                String[] arrSplit = strMain.split(",");
                String userId="";
                String username="";

                List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
                if(logintblList.size()>0){
                    userId = logintblList.get(0).getUserid();
                    username= logintblList.get(0).getUsername();
                }
                for (int j = 0; j < arrSplit.length; j++) {
                    String caders=arrSplit[j].toString();
                    lStringBuilder.append("{");
                    lStringBuilder.append("\"Meetingid\"");
                    lStringBuilder.append(":");
                    lStringBuilder.append("\""+Meetingid+"\"");
                    lStringBuilder.append(",");

                    lStringBuilder.append("\"Pgcode\"");
                    lStringBuilder.append(":");
                    lStringBuilder.append(Pgcode);
                    lStringBuilder.append(",");

                    lStringBuilder.append("\"Cadreid\"");
                    lStringBuilder.append(":");
                    lStringBuilder.append(caders);
                    lStringBuilder.append(",");

                    lStringBuilder.append("\"Isxported\"");
                    lStringBuilder.append(":");
                    lStringBuilder.append(Isxported);
                    lStringBuilder.append(",");

                    lStringBuilder.append("\"createdby\"");
                    lStringBuilder.append(":");
                    lStringBuilder.append("\""+username+"\"");
                    lStringBuilder.append(",");

                    lStringBuilder.append("\"AppVersion\"");
                    lStringBuilder.append(":");
                    lStringBuilder.append("\""+myVersionName+"\"");
                    lStringBuilder.append(",");

                    lStringBuilder.append("\"createdon\"");
                    lStringBuilder.append(":");
                    lStringBuilder.append("\""+new GetCurrentDate().getDate()+"\"");

                    lStringBuilder.append("}");
                    if(j<arrSplit.length - 1) {
                        lStringBuilder.append(",");
                    }
                }
                if(i< PgActivity.tblPgMeetingCaders.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
            //Log.d("JsonString",JsonString);
        }

        if(tblIdentifier.equals("PhCollectingInputtbl")){
            StringBuilder lStringBuilder = new StringBuilder();
            lStringBuilder.append("{");
            lStringBuilder.append("\"PhCollectingInputtbl\"");
            lStringBuilder.append(":");
            lStringBuilder.append("[");
            String userId="";
            String username="";

            List<Logintbl> logintblList = Logintbl.listAll(Logintbl.class);
            if(logintblList.size()>0){
                userId = logintblList.get(0).getUserid();
                username= logintblList.get(0).getUsername();
            }

            for (int i = 0; i< PgActivity.phCollectingInputtbls.size(); i++){

                lStringBuilder.append("{");
                lStringBuilder.append("\"uuid\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.phCollectingInputtbls.get(i).getUuid());
                lStringBuilder.append(",");

                lStringBuilder.append("\"pgcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.phCollectingInputtbls.get(i).getPgcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"grpmemcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.phCollectingInputtbls.get(i).getGrpmemcode());
                lStringBuilder.append(",");

                lStringBuilder.append("\"grpcode\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.phCollectingInputtbls.get(i).getGrpcode()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"selecteddate\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.phCollectingInputtbls.get(i).getSelecteddate());
                lStringBuilder.append(",");

                lStringBuilder.append("\"type\"");
                lStringBuilder.append(":");
                lStringBuilder.append(PgActivity.phCollectingInputtbls.get(i).getType());
                lStringBuilder.append(",");

                lStringBuilder.append("\"qty\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.phCollectingInputtbls.get(i).getQty()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"rate\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.phCollectingInputtbls.get(i).getRate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("\"isexported\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+PgActivity.phCollectingInputtbls.get(i).getIsexported()+"\"");
                lStringBuilder.append(",");

              /*  lStringBuilder.append("\"AppVersion\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+myVersionName+"\"");
                lStringBuilder.append(",");*/

                lStringBuilder.append("\"createdby\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+username+"\"");

                lStringBuilder.append("\"createdon\"");
                lStringBuilder.append(":");
                lStringBuilder.append("\""+ new GetCurrentDate().getDate()+"\"");
                lStringBuilder.append(",");

                lStringBuilder.append("}");
                if(i< PgActivity.phCollectingInputtbls.size()-1) {
                    lStringBuilder.append(",");
                }
            }
            lStringBuilder.append("]");
            lStringBuilder.append("}");
            JsonString = lStringBuilder.toString();
        }
        return JsonString;
    }
}
