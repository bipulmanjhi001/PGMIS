package com.jslps.pgmisnew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import com.jslps.pgmisnew.adapter.Pgmisbatchloanadapter;
import com.jslps.pgmisnew.database.BrsReportModel;
import com.jslps.pgmisnew.database.PgmisBatchLoantbl;
import com.jslps.pgmisnew.database.PgmisChequeLoantbl;
import com.jslps.pgmisnew.database.PgmisLoanRepaymentModel;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoanRepaymentActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.recyler_list)
    RecyclerView recylerList;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.imageView14)
    ImageView imageView14;
    @BindView(R.id.textView112)
    TextView textView112;
    @BindView(R.id.textView777)
    TextView textView777;
    public static String pgname, pgcode, grpcode, grpmemcode, memname;
    List<PgmisBatchLoantbl> pgmisBatchLoantblList;
    List<PgmisChequeLoantbl> pgmisChequeLoantbls;
    List<PgmisLoanRepaymentModel> pgmisLoanRepaymentModels;
    Pgmisbatchloanadapter aAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_repayment);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        pgname = PgActivity.pgNameSelected;
        Intent intent = getIntent();
        pgcode = intent.getStringExtra("pgcode");
        grpcode = intent.getStringExtra("grpcode");
        grpmemcode = intent.getStringExtra("grpmemcode");
        memname = intent.getStringExtra("membername");

        getLoanDetailsMember();
        textView112.setText(memname);
        textView23.setText(pgname);
    }

    private void getLoanDetailsMember() {
        pgmisLoanRepaymentModels=new ArrayList<>();
        pgmisBatchLoantblList = Select.from(PgmisBatchLoantbl.class)
                .where(Condition.prop("pgcode").eq(pgcode))
                .where(Condition.prop("grpcode").eq(grpcode))
                .where(Condition.prop("grpmemcode").eq(grpmemcode))
                .list();

        if (pgmisBatchLoantblList.size() > 0) {
            for (int i = 0; i < pgmisBatchLoantblList.size(); i++) {
                PgmisLoanRepaymentModel item = new PgmisLoanRepaymentModel();
                item.setLoanid(pgmisBatchLoantblList.get(i).getLoanid());
                item.setItemuuids(pgmisBatchLoantblList.get(i).getItemuuids());
                item.setPgcode(pgmisBatchLoantblList.get(i).getPgcode());
                item.setGrpcode(pgmisBatchLoantblList.get(i).getGrpcode());
                item.setGrpmemcode(pgmisBatchLoantblList.get(i).getGrpmemcode());
                item.setEntrydate(pgmisBatchLoantblList.get(i).getEntrydate());
                item.setIsexported(pgmisBatchLoantblList.get(i).getIsexported());
                item.setAmount(pgmisBatchLoantblList.get(i).getAmount());
                item.setPaymentmode(pgmisBatchLoantblList.get(i).getPaymentmode());
                pgmisLoanRepaymentModels.add(item);
            }
        }

        pgmisChequeLoantbls=Select.from(PgmisChequeLoantbl.class)
                .where(Condition.prop("pgcode").eq(pgcode))
                .where(Condition.prop("grpcode").eq(grpcode))
                .where(Condition.prop("grpmemcode").eq(grpmemcode))
                .list();

        if (pgmisChequeLoantbls.size() > 0) {
            for (int i = 0; i < pgmisChequeLoantbls.size(); i++) {
                PgmisLoanRepaymentModel item = new PgmisLoanRepaymentModel();
                item.setLoanid(pgmisChequeLoantbls.get(i).getUuid());
                item.setPgcode(pgmisChequeLoantbls.get(i).getPgcode());
                item.setGrpcode(pgmisChequeLoantbls.get(i).getGrpcode());
                item.setGrpmemcode(pgmisChequeLoantbls.get(i).getGrpmemcode());
                item.setIsexported(pgmisChequeLoantbls.get(i).getIsexported());
                item.setEntrydate(pgmisChequeLoantbls.get(i).getChequedate());
                item.setEntryby(pgmisChequeLoantbls.get(i).getEntryby());
                item.setAppliedforloan(pgmisChequeLoantbls.get(i).getAppliedforloan());
                item.setChequedate(pgmisChequeLoantbls.get(i).getChequedate());
                item.setAmount(pgmisChequeLoantbls.get(i).getAmount());
                item.setRemark(pgmisChequeLoantbls.get(i).getRemark());
                item.setPaymentmode(pgmisChequeLoantbls.get(i).getPaymentmode());
                pgmisLoanRepaymentModels.add(item);
            }
        }

        textView777.setText("Total loans: "+pgmisBatchLoantblList.size()+pgmisChequeLoantbls.size());
        aAdapter = new Pgmisbatchloanadapter(this, pgmisLoanRepaymentModels);

        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recylerList.setLayoutManager(verticalLayoutmanager);
        recylerList.setAdapter(aAdapter);
    }
}