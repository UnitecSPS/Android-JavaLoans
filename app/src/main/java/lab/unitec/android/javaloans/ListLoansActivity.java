package lab.unitec.android.javaloans;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListLoansActivity extends ListActivity {

    static ArrayList<Loan> loans  = new ArrayList<>();

    static{
        loans.add(new Loan("Carlos", 5000));
        loans.add(new Loan("Kevin", 8000));
        loans.add(new Loan("Dennis", 15000));
        loans.add(new Loan("Eduardo", 300));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_loans);

        String[] values = new String[loans.size()];
        //cargar los loans
        for(int l=0; l < loans.size(); l++){
            values[l] = loans.get(l).cliente;
        }

        // use your custom layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_list_loans , R.id.label, values);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //String item = (String) getListAdapter().getItem(position);
        //Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
        loadSingleLoanActivity(position);
    }

    private void loadSingleLoanActivity(int pos){
        Intent intent = new Intent(this, SingleLoanActivity.class);
        intent.putExtra("position", pos);
        startActivity(intent);
    }
}
