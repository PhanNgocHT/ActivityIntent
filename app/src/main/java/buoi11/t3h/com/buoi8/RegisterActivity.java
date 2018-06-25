package buoi11.t3h.com.buoi8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Admin on 4/04/2017.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    private EditText edtUserName;
    private EditText edtPassword;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initViews();
    }

    private void initViews() {
        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                if (userName.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "User name or password invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent data = new Intent();
                data.putExtra(LoginActivity.KEY_USER_NAME, userName);
                data.putExtra(LoginActivity.KEY_PASSWORD, password);
                setResult(RESULT_OK,data);
                finish();
                break;
        }
    }
}
