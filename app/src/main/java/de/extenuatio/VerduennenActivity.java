package de.extenuatio;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class VerduennenActivity extends Activity {
	
    public static final int SL1_ID=0;
    public static final int SL2_ID=1;
    public static final int SL3_ID=2;
    public static final int SL4_ID=3;
    public static final int SL5_ID=4;
    public static final int SL6_ID=5;

    public static final int Konz1_ID=0;
    public static final int Konz2_ID=1;
    
	private String arrSL[];
	private String arrSpinner[];
	private String arrSpinner1[];
	private String arrSpinner2[];
	private String arrSpinner3[];
	// private String arrKonz[];
	// private String arrVerd[];



	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.verduennen);
	        	   

	    Spinner spinnerSL = (Spinner) findViewById(R.id.spinnerSL);
	    Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
	    Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
	    Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
			
		//spezieller Adapter für Spinner: ermöglicht ein eigenes Layout (res\layout\my_spinner.xml)
		//Layout ist mit Style gekoppelt (res\values\my_style.xml). Dort können Android-Attribute 'textSize' und 'textColor' gesetzt werden!
		//http://androidcookbook.com/Recipe.seam?recipeId=4012
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SaeureLauge, R.layout.my_spinner);
	    spinnerSL.setAdapter(adapter);			
	    // spinnerSL.setSelection(SL1_ID);		
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.MasseVolumen, R.layout.my_spinner);
	    spinner1.setAdapter(adapter1);	
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.MasseVolumen, R.layout.my_spinner);
	    spinner2.setAdapter(adapter2);
		ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Konzentration, R.layout.my_spinner);
	    spinner3.setAdapter(adapter3);
	             			
	} // onCreate

	    
		@Override
		public void onResume() {
			super.onResume();
			
			EditText et;
			int resId;
			
			// Cursor in erstes Eingabefeld setzen und numerische Tastatur einschalten 
	    	et = (EditText) findViewById(R.id.MengeSL);
			et.requestFocus();
			
			String strSpinner;
			
			arrSpinner = getResources().getStringArray(R.array.MasseVolumen);
			Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
			strSpinner = spinner1.getSelectedItem().toString(); 
			
			
			if (strSpinner.equals(arrSpinner[0]) == true)
			{
				//resId = getResources().getIdentifier("Masse", "id", getPackageName());
				et.setHint("Masse?");
			}
			else
			{
				et.setHint("else");
			}
				
			
			
		}
	    
	    	    
		public void btnRechneOnClickHandler(View v)
		{
			EditText et;
			TextView tv;
			
			String strVolSL;
			String strVolWasser;
			String strMassVerduennung;
			String strKonzVerduennung;
			String strSL;
			String strSpinner1;
			String strSpinner2;
			String strSpinner3;
			String strErgebnis;
			String strErgebnis2;
			String strText = "";
			String strText2 = "";
			String strTextSL ="";
			String strTextSL2 ="";
			String strTextEinheit ="";
			String strTextEinheit2 ="";
			
			Double dblV1 = 0.0;
			Double dblm1 = 0.0;
			Double dblV2 = 0.0;
			Double dblm2 = 0.0;
			Double dblVM = 0.0;
			Double dblmM = 0.0;
			Double dblwM = 0.0;
			Double dblcM = 0.0;
			Double dblDichte = 0.0;
			Double dblErgebnis = 0.0;
			Double dblErgebnis2 = 0.0;
			
			Integer intKonzSL = 100;
		
		// ###########################################################################################
		// ########################  Tastatur ausschalten ###########################################	
		// ###########################################################################################
	    	
			InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
		    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

		// ########## Spinner der konz. Säure oder Lauge #############	
		
			arrSL = getResources().getStringArray(R.array.SaeureLauge);
			Spinner spinnerSL = (Spinner) findViewById(R.id.spinnerSL);
			strSL = spinnerSL.getSelectedItem().toString(); 
			
		// ########## Spinner1: Masse Volumen der konz. Säure oder Lauge #############	
			
			arrSpinner1 = getResources().getStringArray(R.array.MasseVolumen);
			Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
			strSpinner1 = spinner1.getSelectedItem().toString(); 
						
		// ########## Spinner2: Masse Volumen der Verdünnung #############	
			
			arrSpinner2 = getResources().getStringArray(R.array.MasseVolumen);
			Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
			strSpinner2 = spinner2.getSelectedItem().toString(); 
			
		// ########## Spinner3: Konzentration der Verdünnung (% oder mol/L) #############	
			
			arrSpinner3 = getResources().getStringArray(R.array.Konzentration);
			Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
			strSpinner3 = spinner3.getSelectedItem().toString(); 
			
						
		// ########### Wertzuweisung des Spinners Säure Lauge ###################################################			
			
			for (int x=0; x<=6; x++)
			{
				if (strSL.equals(arrSL[x]) == true)
				{
					switch (x) 
					{
						case 0: dblDichte = 1.19; intKonzSL = 37; strTextSL ="Säure =";	strTextSL2 = strTextSL;		// Dichte konz. Salzsäure
						break; 
						case 1: dblDichte = 1.84; intKonzSL = 96; strTextSL ="Säure =";	strTextSL2 = strTextSL;		// Dichte konz. Schwefelsäure
						break;
						case 2: dblDichte = 1.39; intKonzSL = 65; strTextSL ="Säure =";	strTextSL2 = strTextSL;		// Dichte konz. Salpetersäure
						break; 
						case 3: dblDichte = 1.71; intKonzSL = 85; strTextSL ="Säure =";	strTextSL2 = strTextSL;		// Dichte konz. o-Phosphorsäure
						break;
						case 4: dblDichte = 1.05; intKonzSL = 100; strTextSL ="Säure ="; strTextSL2 = strTextSL;	// Dichte konz. Essigsäure
						break; 
						case 5: dblDichte = 0.903; intKonzSL = 25; strTextSL ="Lauge ="; strTextSL2 = strTextSL;	// Dichte konz. Ammoniaklösung, (25%)
						break;
						case 6: dblDichte = 0.88; intKonzSL = 32; strTextSL ="Lauge =";	strTextSL2 = strTextSL;		// Dichte konz. Ammoniaklösung, (32%)
						break;
					}	
				}
			}	
			
		// ########### Bedingung bei Auswahl Spinner1 ###################################################			
		/*	
			if (strSpinner2.equals(arrSpinner2[0]) == true || strSpinner3.equals(arrSpinner3[0]) == true) // Wenn Spinner2 = g und Spinner3 = %
			{
				spnSpinner2.setAdapter(adapter);                  
				spnSpinner2.setSelection(Einheit1_ID);        // Spinner entsprechend umschalten
			}
				
				
		*/				
						
		// ########### Aufruf Funktion: Bei Leertext: Umwandlung String Leertext in Double 0.0 #################	
			
			
			et = (EditText) findViewById(R.id.MengeSL);
			strVolSL = et.getText().toString();		
			if (strSpinner1.equals("g")== true)
			{
				dblm1 = ActivityTools.fktBlankToNumber(strVolSL);
			}
			else
			{
				dblV1 = ActivityTools.fktBlankToNumber(strVolSL);
			}
						
			
			
			et = (EditText) findViewById(R.id.MengeWasser);
			strVolWasser = et.getText().toString();
			dblV2 = ActivityTools.fktBlankToNumber(strVolWasser);
			dblm2 = dblV2;
			
			
			
			et = (EditText) findViewById(R.id.MengeVerduennung);
			strMassVerduennung = et.getText().toString();
			if (strSpinner2.equals("g")== true)
			{
				dblmM = ActivityTools.fktBlankToNumber(strMassVerduennung);
			}
			else
			{
				dblVM = ActivityTools.fktBlankToNumber(strMassVerduennung);
			}
		
			
			et = (EditText) findViewById(R.id.KonzVerduennung);
			strKonzVerduennung = et.getText().toString();
			if (strSpinner2.equals("%")== true)
			{
				dblwM = ActivityTools.fktBlankToNumber(strKonzVerduennung);
			}
			else
			{
				dblcM = ActivityTools.fktBlankToNumber(strKonzVerduennung);
			}
		

			
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// ++++++ Toasts für fehlerhafte Eingabe - zu viele Werte eingegeben ++++++++
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	
			
	if ((strVolSL.equals(""
			+ "") == false) && (strVolWasser.equals("") == false) && (strMassVerduennung.equals("") == false) && (strKonzVerduennung.equals("") == false))
	{
	   	String text = "\n    Der zu berechnende    \n    Parameter muß offen bleiben!   \n"; 
	   	Toast Meldung = Toast.makeText(this, text, Toast.LENGTH_SHORT);
	   	Meldung.setGravity(Gravity.TOP, 0, 0);
	   	Meldung.show();
	} 		
	else
	{	
		ActivityTools.subMasse(dblm1, dblm2, dblmM); // Berechnung von m1, m2 oder mM falls möglich. Formel: mM = m1 + m2
		

		

		if ((dblV1 == 0) && (dblV2 != 0) && (dblVM != 0))
		{
			dblV1 = dblVM - dblV2;
		}
		if ((dblV2 == 0) && (dblV1 != 0) && (dblVM != 0))
		{
			dblV2 = dblVM - dblV1;
		}
		if ((dblVM == 0) && (dblV1 != 0) && (dblV2 != 0))
		{
			dblVM = dblV1 + dblV2;
		}
		
		
		if (dblErgebnis == 0.0)
		{
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			// ++++++ Toasts für fehlerhafte Eingabe - zu wenig Werte eingegeben ++++++++
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	
   			String text = "\n   Zu viele Unbekannte!   \n"; 
   			Toast Meldung = Toast.makeText(this, text, Toast.LENGTH_SHORT);
   			Meldung.setGravity(Gravity.TOP, 0, 0);
   			Meldung.show();
	    } 
	     // ########### Ausgabe der Berechnung auf Textfeld ########################################################
    		
    	strErgebnis = ActivityTools.fktDoubleToStringFormat(dblErgebnis, 1);   // Eine Nachkommastelle
		tv = (TextView) findViewById(R.id.tvErgebnis);
		tv.setText(strText + strTextSL + strErgebnis + strTextEinheit);
    	
    	if (dblErgebnis2 != 0)	
    	{
	    	strErgebnis2 = ActivityTools.fktDoubleToStringFormat(dblErgebnis2, 1);   // Eine Nachkommastelle
    		tv = (TextView) findViewById(R.id.tvErgebnis3);
    		tv.setText(strText2 + strTextSL2 + strErgebnis2 + strTextEinheit2);
  		}

	}	// else
}	 // btnRechneOnrKonzClickHandler
		
		// ########### Eingabefelder und Ausgabefeld zurücksetzen ###############################################
	    // 
	    public void btnClear(View v) {
	    	EditText et;
	    	TextView tv;
	    	 
		    	et = (EditText) findViewById(R.id.MengeSL);
		    	et.setText("");	
		    	et = (EditText) findViewById(R.id.MengeWasser);
		    	et.setText("");
		    	et = (EditText) findViewById(R.id.MengeVerduennung);
		    	et.setText("");
		    	et = (EditText) findViewById(R.id.KonzVerduennung);
		    	et.setText("");
		    	tv = (TextView) findViewById(R.id.tvErgebnis);
		    	tv.setText("");
		    	tv = (TextView) findViewById(R.id.tvErgebnis3);
		    	tv.setText("");
			
			// Cursor in erstes Eingabefeld setzen und numerische Tastatur einschalten 
	    	et = (EditText) findViewById(R.id.MengeSL);
			et.requestFocus();
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);		

	   
	} // btnClear
}
	    


	    
	    
	    
	    
