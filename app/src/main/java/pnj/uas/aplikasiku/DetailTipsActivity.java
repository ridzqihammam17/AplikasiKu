package pnj.uas.aplikasiku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailTipsActivity extends AppCompatActivity {

    ImageView headerTips;
    TextView txtJudul, txtIsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tips);

        headerTips = findViewById(R.id.headerTips);
        txtJudul = findViewById(R.id.txtTittle);
        txtIsi = findViewById(R.id.txtIsi);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Picasso.get().load(extras.getString("imagetips", "")).into(headerTips);
            txtJudul.setText(extras.getString("judultips", ""));
            txtIsi.setText(extras.getString("deskripsitips", ""));
        }
    }
}
