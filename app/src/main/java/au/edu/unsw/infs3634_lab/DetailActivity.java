package au.edu.unsw.infs3634_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private final String TAG = "DetailActivity";
    private TextView mText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Log.d(TAG, "DetailActivity onCreate launched");

        // setTitle(<string>) sets the title of the screen to the string value
        setTitle("Detail Activity");

        mText = findViewById(R.id.tvText);
        mButton = findViewById(R.id.btnDetail);

        Intent intent = getIntent();
        String msg = intent.getStringExtra("key");
        Log.d(TAG, "DetailActivity retrieved msg: "+ msg);
        mText.setText(msg);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/"));
                startActivity(intent);
            }
        });
    }
}