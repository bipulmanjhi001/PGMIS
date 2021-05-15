package com.jslps.pgmisnew.util;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Environment;

public class AppConstant {

    public AppConstant(){

    }

    public static final String domain="http://johar.swalekha.in/Webservices/WebService.asmx";

    //methods
    public static final String loginMethod="PGMIS_Login_Data";
    //producergroupmembers table
    public static final String Upload_tblMstGroupMembers_Johar="Upload_tblMstGroupMembers_Johar";
    public static final String Upload_PgMeetingtbl="Upload_PgMeetingtbl";
    public static final String Download_Johar_TabletData_Service="Download_Johar_TabletData_Service";
    public static final String GetDisbursementList="GetDisbursementList";
    public static final String DownLoadPGMIS="DownLoadPGMIS";
    public static final String UploadPGMIS="UploadPGMIS";

    public static final String UpLoad_PgPaymentTranst="Upload_PgPaymentTranst";
    public static final String UpLoad_PgCapitalSave="Upload_PgCapitalSave";
    public static final String UpLoad_Pgmisbrstrans="Upload_Pgmisbrstrans";
    public static final String UpLoad_PgMemShipFeeSave="Upload_PgMemShipFeeSave";
    public static final String Upload_Pgmisbrstransaddsub="Upload_Pgmisbrstransaddsub";

    /*public static final String Upload_PgmisLoan="Upload_PgmisLoan";*/

    public static final String Upload_PgReceiptTrans="Upload_PgReceiptTrans";
    public static final String Upload_Itempurchasedbypg="Upload_Itempurchasedbypg";
    public static final String Upload_PgmisBatchLoan="Upload_PgmisBatchLoan";
    public static final String Upload_Pgmisloanrepayment="Upload_Pgmisloanrepayment";
    public static final String Upload_Pgmisbankwithdrawcashdeposit="Upload_tblmst_PgBankwithdrawcashdeposit";
    public static final String Upload_Image="PGMIS_image";
    public static final String Upload_Pgmischequebank="Upload_tblmst_PgmisChequeLoan";
    public static final String Upload_CadreList="Upload_tblPgMeetingCaders";

    public static final String Upload_tblPhCollectingInput="Upload_tblPhCollectingInput";

    //table indentifier
    public static final String logintbl ="1";
    public static final String tblMstGroupMembers_Johar="2";
    public static final String PgMeetingtbl="3";
    public static final String PgMeetingtblDownload="4";
    public static final String PgPaymentReceiptDisDownload="5";
    public static final String PGMISDOWNLOADIdentifier="6";
    public static final String pgpaymentupload="7";
    public static final String PgCapitalSavetbl ="8";
    public static final String PgMemShipFeeSavetbl="9";
    public static final String Pgmisbrstranstbl="10";
    public static final String Pgmisbrstransaddsubtbl="11";

    /*public static final String PgmisLoantbl="12";*/
    public static final String PgReceiptTranstbl="13";
    public static final String Itempurchasedbypgtbl="14";
    public static final String PgmisBatchLoan="15";
    public static final String Pgmisloanrepayment="16";

    public static final String Downloadpgpaymentupload="17";
    public static final String DownloadPgCapitalSavetbl ="18";
    public static final String DownloadPgMemShipFeeSavetbl="19";
    public static final String DownloadPgmisbrstranstbl="20";
    public static final String DownloadPgmisbrstransaddsubtbl="21";


    /*public static final String DownloadPgmisLoantbl="22";*/
    public static final String DownloadPgReceiptTranstbl="23";
    public static final String DownloadItempurchasedbypgtbl="24";
    public static final String DownloadPgmisBatchLoan="25";
    public static final String DownloadPgmisloanrepayment="26";
    public static final String UploadBRSImage="27";
    public static final String DownloadImage="28";
    public static final String DownloadPgmispurchaseitems="29";
    public static final String Pgmisbankwithdrawcashdeposit="30";
    public static final String DownloadPgmisbankwithdrawcashdepositstatus="31";
    public static final String PgmisChequeLoan="32";
    public static final String Downloadpgmichequeloan="33";
    public static final String DownloadCadreTypeMaster="34";
    public static final String PgmisCadreList="35";
    public static final String PgmisCadersList="36";
    public static final String PhCollectingInput="37";

    //flags
    public static final String meetingtblflag ="PgMeetingtbl";
    public static final String DownLoadPGMISflag ="PGMIS";
    public static final String DownLoadPaymentTranst="tblmst_PgPaymentTranst";
    public static final String DownLoadCapitalSave="tblmst_PgCapitalSave";
    public static final String DownLoadMemShipFeeSave="tblmst_PgMemShipFeeSave";
    public static final String DownLoadbrstrans="tblmst_Pgmisbrstrans";
    public static final String DownLoadbrstransaddsub="tblmst_Pgmisbrstransaddsub";
    public static final String DownLoadLoan="tblmst_PgmisLoan";
    public static final String DownLoadReceiptTrans="tblmst_PgReceiptTrans";
    public static final String DownLoadItempurchasedbypg="tblmst_Itempurchasedbypg";
    public static final String DownLoadBatchLoan="tblmst_PgmisBatchLoan";
    public static final String DownLoadloanrepayment="tblmst_Pgmisloanrepayment";
    public static final String DownloadImageMethod="Convert_ImageTo_Base64_PGMIS";
    public static final String DownloadPurcaseItems="tblmst_Pgmispurchaseitemmst";
    public static final String DownloadPgmisbankwithdrawcashdeposit="tblmst_PgBankwithdrawcashdeposit";
    public static final String DownloadPgmisChequeLoan="tblmst_PgmisChequeLoan";
    public static final String DownloadCadreType="CadreTypeMaster";
    public static final String DownloadCadersList="tblPgMeetingCaders";

    //Fixed Amounts
    public static final String MEMBERSHIPFEE="100";
    public static final String SHARECAPITAL="1000";

    //Edits
    public static boolean editpgpaymentrecord=false;
    public static boolean editpgreceiptrecord=false;

    public static int imageuploading = 0;

    //image path name
    public static String imagePath= Environment.getExternalStorageDirectory().toString() + "/PGMISBRS/IMAGES";
    public static boolean fetchMaster= false;

}
