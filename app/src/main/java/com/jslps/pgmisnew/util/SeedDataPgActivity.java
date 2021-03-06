package com.jslps.pgmisnew.util;

import com.jslps.pgmisnew.R;
import com.jslps.pgmisnew.database.PgActivityModel;

import java.util.ArrayList;
import java.util.List;

public class SeedDataPgActivity {

    private static final int[] id1 = new int[] {
            1,
            3,
            5,
            7,
            9,
            11,
            13,
            15,
            17};

    private static final int[] id2 = new int[] {
            2,
            4,
            6,
            8,
            10,
            12,
            14,
            16,
            18};

    private static final String[] title1 = new String[] {
            "सदस्यों का विवरण",
            "शेयर पूंजी",
            "उत्पादक समूह द्वारा भुगतान",
            "खरीद / स्टॉक",
            "ऋण भुगतान",
            "बैंक से नगद निकासी एवं जमा",
            "BRS Report",
            "स्टॉक रिपोर्ट",
            "आउटपुट मार्केटिंग"
    };

    private static final String[] title2 = new String[] {
            "सदस्यता शुल्क",
            "बैठक का विवरण",
            "उत्पादक समूह को प्राप्ति",
            "ऋण एवं  बिक्री",
            "BRS",
            "प्राप्ति भुगतान रिपोर्ट",
            "अनुदान उपयोग रिपोर्ट",
            "इनपुट मार्केटिंग",
            "इनपुट/आउटपुट रिपोर्ट"
    };

    private static final int[] imageIcon1= new int[] {
            R.drawable.member_detail_icon,
            R.drawable.share_capital_icon,
            R.drawable.ic_pay,
            R.drawable.purchase_cart,
            R.drawable.ic_give_money,
            R.drawable.ic_bank_transfer,
            R.drawable.ic_statement,
            R.drawable.ic_store,
            R.drawable.ic_output
    };

    private static final int[] imageIcon2= new int[] {
            R.drawable.member_fee_icon,
            R.drawable.meeting_details_icon,
            R.drawable.ic_money,
            R.drawable.ic_loan,
            R.drawable.bank_icon,
            R.drawable.receipt_report,
            R.drawable.ic_progress_report,
            R.drawable.ic_seller,
            R.drawable.ic_profit_report
    };

    public static List<PgActivityModel> getListData(){
        List<PgActivityModel> list = new ArrayList<>();
        for (int i = 0; i < title1.length; i++)
        {
            PgActivityModel dashboardFragmentModel = new PgActivityModel(title1[i],title2[i],imageIcon1[i],imageIcon2[i],id1[i],id2[i]);
            list.add(dashboardFragmentModel);
        }
        return list;
    }
}
