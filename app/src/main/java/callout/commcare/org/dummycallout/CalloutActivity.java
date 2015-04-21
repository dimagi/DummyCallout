package callout.commcare.org.dummycallout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple Android application to demonstrate how to integrate with CommCare
 * https://github.com/dimagi/commcare-odk/
 *
 * This application receives one string "extra" called case_id and returns one
 * string result in the value "odk_intent_data"
 *
 */
public class CalloutActivity extends ActionBarActivity {

    Button returnButton;
    EditText entryBox;
    TextView displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callout);

        Bundle mBundle = getIntent().getExtras();

        //this is how we read in values sent by CommCare
        String caseid = mBundle.getString("case_id", null);

        returnButton = (Button)findViewById(R.id.submit_button);
        entryBox = (EditText)findViewById(R.id.enter_name);
        displayText = (TextView)findViewById(R.id.display_text);

        if(caseid != null){
            displayText.setText(caseid);
        }

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = entryBox.getText().toString();
                Intent data = new Intent();

                //this is how you would add responses to the default bundle.
                Bundle responses = new Bundle();
                responses.putString("example_id", "Example text");
                data.putExtra("odk_intent_bundle", responses);

                // this is the value that CommCare will use as the result of the intent question
                data.putExtra("odk_intent_data", text);
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }

}
