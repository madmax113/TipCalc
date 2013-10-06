package com.example.tipcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CalcActivity extends Activity {

	private EditText bill;
	private float total = 0;
	private float tip_percent = 0;
	private float tip = 0, new_total = 0;
	private TextView tipTV;
	private TextView totalTV;
	private TextView pLabel;
	private TextView eLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);        
        bill = (EditText) findViewById(R.id.billTotal);
        tipTV = (TextView) findViewById(R.id.tvTip);
        totalTV = (TextView) findViewById(R.id.tvTotal);
        pLabel = (TextView) findViewById(R.id.selected_percent_view);
        eLabel = (TextView) findViewById(R.id.errorLabelView);      
        //tipTV.setTextAlignment(0);
        //totalTV.setTextAlignment();
        tipTV.setText("  $0.00");
        totalTV.setText("  $0.00");
        eLabel.setText("");
        pLabel.setText("");
    }

    public float round(float a){
    	return ((float) ((int) (a * 100))) / 100;
    }
    
    public String dollarify(float a){    	
    	if (((float)((a*10) - ((int) (a*10)))) == 0){
    		return "$" + Float.toString(a) + "0";
    	} else
    		return "$" + Float.toString(a);    	
    }
    
    public void update(float m){
    	//refresh multiplier and bill total
    	try{
    		total = Float.parseFloat(bill.getText().toString());
    		eLabel.setText("");
	    	tip_percent = m;
	    	tip = total * tip_percent;
	    	new_total = total + tip;
	    	//refresh the displays
	    	tipTV.setText("  " + dollarify(round(tip)));
	        totalTV.setText("  " + dollarify(round(new_total)));
	        pLabel.setText("  "+Float.toString(((float) ((int) (m * 100))))+"%");
	        
	    } catch (NumberFormatException e) {
	    	eLabel.setText("Please enter a valid number");
	    }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calc, menu);
        return true;
    }
    
    public void onTen(View v){
    	update((float)0.1);    	
    }
    
    public void onFifteen(View v){
    	update((float)0.15);
    }
    
    public void onTwenty(View v){
    	update((float)0.2);
    }
    
}
