package lab.unitec.android.javaloans;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class SingleLoanActivity extends ActionBarActivity {

    static final int ABONO_ACTIVITY = 1;
    private EditText etBalance;
    private Loan selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_loan);

        //actualizar el loan
        EditText etCliente = (EditText)findViewById(R.id.etCliente);
        etBalance = (EditText)findViewById(R.id.etBalance);

        Intent i = getIntent();
        int pos = (int)i.getExtras().get("position");
        selected = ListLoansActivity.loans.get(pos);

        //pongamos los valores
        etCliente.setText(selected.cliente);
        etBalance.setText("Lps. "+ selected.balance);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_loan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void abono(View view) {
        Intent i = new Intent(this, AbonoActivity.class);
        startActivityForResult(i, ABONO_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == ABONO_ACTIVITY) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try{
                    double m = Double.parseDouble(data.getExtras().get("monto").toString());
                    m = selected.pay(m);
                    Toast.makeText(this, "Se abono Lps. "+m, Toast.LENGTH_LONG).show();
                    etBalance.setText("Lps. "+ selected.balance);
                }
                catch(Exception e){
                    Toast.makeText(this,"Monto Invalido", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(this,"Operacion Cancelada", Toast.LENGTH_LONG).show();
            }
        }
    }
}
