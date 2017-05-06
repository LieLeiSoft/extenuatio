package de.extenuatio;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VerduennungsreiheActivity extends Activity {

    public static final int Verduennungsschritt1_ID=0;
    public static final int Verduennungsschritt2_ID=1;
    public static final int Verduennungsschritt3_ID=2;
	  
	//private String arrVerduennungsschritte[];

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verduennungsreihe);

/*
		arrVerduennungsschritte = getResources().getStringArray(R.array.Verduennungsschritt);
		Spinner spnVerduennungsschritt = (Spinner) findViewById(R.id.spnVerduennungsschritt);		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrVerduennungsschritte);
		spnVerduennungsschritt.setAdapter(adapter);		
		spnVerduennungsschritt.setSelection(Verduennungsschritt2_ID); // standardmäßig "Verduennungsschritt 2" auswählen
*/		
    } // public void onCreate(Bundle savedInstanceState)
    
public void btnRechneOnClickHandler(View v)
{		    	
	EditText et;
	//TextView tv;
	String strMaxVol;
	String strAnfang;
	String strEnd;
	//String strVSF;
	double dblMaxVol = 500;
	double dblAnfangskonz = 0.0;
	double dblEndkonz = 0.0;
	double dblVerduennungsfaktor = 0;
	
	//int intVerduennungsschritt;
		
	
    et = (EditText) findViewById(R.id.MaxVol);
	strMaxVol = et.getText().toString();	

	if ((strMaxVol.equals("") == true) || (strMaxVol.equals("0") == true))
	{
		et = (EditText) findViewById(R.id.MaxVol);
		et.setText("500");
		et = (EditText) findViewById(R.id.Anfangskonz);
		et.requestFocus();
		
		String textWert = "Maximales Volumen wurde auf 500 gesetzt!"; 
    	Toast Meldung = Toast.makeText(this, textWert , Toast.LENGTH_LONG);
    	Meldung.setGravity(Gravity.CENTER, 0, 0);
    	Meldung.show();
	}
	else
	{
		dblMaxVol  = Double.parseDouble(strMaxVol);	
	}
	
    et = (EditText) findViewById(R.id.Anfangskonz);
	strAnfang = et.getText().toString();
		
	if ((strAnfang.equals("") == true) || (strAnfang.equals("0") == true))
	{
        String textWert = "Bitte einen Wert für die Anfangskonzentration eingeben!"; 
    	Toast Meldung = Toast.makeText(this, textWert , Toast.LENGTH_LONG);
    	Meldung.setGravity(Gravity.CENTER, 0, 0);
    	Meldung.show();
	}
		
    et = (EditText) findViewById(R.id.Endkonz);
	strEnd = et.getText().toString();
		
	if ((strEnd.equals("") == true) || (strEnd.equals("0") == true))
	{
        String textWert = "Bitte einen Wert für die Endkonzentration eingeben!"; 
    	Toast Meldung = Toast.makeText(this, textWert , Toast.LENGTH_LONG);
    	Meldung.setGravity(Gravity.CENTER, 0, 0);
    	Meldung.show();	
	}
else 
{
	dblAnfangskonz = Double.parseDouble(strAnfang);
	dblEndkonz  = Double.parseDouble(strEnd);
	
	
	if(dblAnfangskonz <= dblEndkonz)
	{
        String textWert = "Fehler: Die Anfangskonzentration muss größer sein, als die Endkonzentration!"; 
    	Toast Meldung = Toast.makeText(this, textWert , Toast.LENGTH_LONG);
    	Meldung.setGravity(Gravity.CENTER, 0, 0);
    	Meldung.show();	
	}
	
	else
	{
		dblVerduennungsfaktor = dblAnfangskonz / dblEndkonz;	
			
    	TextView text;
    	
	    for (int x=1; x<=8; x++)
    	{  
	    	int resId = getResources().getIdentifier("tvErgebnis_"+(x), "id", getPackageName());
	    	text = (TextView) findViewById(resId);
	    	text.setVisibility(View.GONE);
    	}

	
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    /*
	    
		Spinner sp = (Spinner) findViewById(R.id.spnVerduennungsschritt);		
		intVerduennungsschritt = Integer.parseInt(sp.getSelectedItem().toString());
	    
	    // VSF = Verdünnungsschrittfaktor
	    
		Double dblVSF = ActivityTools.fktnSqrt(dblVerduennungsfaktor, intVerduennungsschritt);
		strVSF = Double.toString(dblVSF);
		
		
		tv = (TextView) findViewById(R.id.tvErgebnis);
		tv.setText(strVSF);
		
		*/
		
    	// Index der Felder im Array beginnt mit 0 (nicht mit 1!)! D.h. erstes Element ist matrix[0][0]
    	int[] arrMesskolben = new int[10];
		
    	arrMesskolben[0] = 10;
    	arrMesskolben[1] = 20;
    	arrMesskolben[2] = 25;
    	arrMesskolben[3] = 50;
    	arrMesskolben[4] = 100;
    	arrMesskolben[5] = 200;
    	arrMesskolben[6] = 250;
    	arrMesskolben[7] = 500;
    	arrMesskolben[8] = 1000;
    	arrMesskolben[9] = 2000;

    	// Index der Felder im Array beginnt mit 0 (nicht mit 1!)! D.h. erstes Element ist matrix[0][0]
    	double[] arrPipetten = new double[14];
		
    	arrPipetten[0] = 1;
    	arrPipetten[1] = 2;
    	arrPipetten[2] = 2.5;
    	arrPipetten[5] = 5;
    	arrPipetten[6] = 6;
    	arrPipetten[7] = 8;
    	arrPipetten[8] = 10;
    	arrPipetten[9] = 15;
    	arrPipetten[10] = 20;
    	arrPipetten[11] = 25;
    	arrPipetten[12] = 50;
    	arrPipetten[13] = 100;    	
    	
    	
    	
    	
    	/************************************************************************
    	 ******************** Ein Verdünnungschritt *****************************
    	 ************************************************************************/
    	int intFeldNr = 0;
    	
    	for (int messkolben=(arrMesskolben.length-1); messkolben>=0; messkolben--)
    	{
        	
           	for (int pipette=(arrPipetten.length-1); pipette>=0; pipette--)
            {
        
        		// Berechnung der theoretischen Verdünnung (dblTV)
        		Double dblTV = arrMesskolben[messkolben] / arrPipetten[pipette];
        		
        		// Berchnung des Volumens der Verdünnungsreihe
        		Double dblIstVol = arrMesskolben[messkolben] - arrPipetten[pipette];
        		
        		if ((dblIstVol <= dblMaxVol) && (dblVerduennungsfaktor == dblTV) && (intFeldNr <= 7))
        			{
        			intFeldNr ++;
            		TextView tv;
            		String strErgebnis = Double.toString(arrPipetten[pipette]) + "mL auf " + Integer.toString(arrMesskolben[messkolben]) + "mL";
            		int resId = getResources().getIdentifier("tvErgebnis_"+(intFeldNr), "id", getPackageName());
            		tv = (TextView) findViewById(resId);
            		tv.setVisibility(View.VISIBLE);
            		tv.setText(strErgebnis);
        			}   		
           	}
    	} 
    		
        	/************************************************************************
        	 ******************** Zwei Verdünnungschritte ***************************
        	 ************************************************************************/
    	
    	int pipette1 = 0;
    	int pipette2 = 0;
    	int messkolben1 = 0;
    	int messkolben2 = 0;
    	
    if (intFeldNr < 1)			// Ergeben sich im ersten Verdünnungsschritt bereits Verdünnungen, wird der zweite Verdünnungsschritt nicht mehr durchlaufen.
    {
        	
    	for (messkolben1=(arrMesskolben.length-1); messkolben1>=0; messkolben1--)
    	{
        	
           	for (pipette1=(arrPipetten.length-1); pipette1>=0; pipette1--)
           	{
               		               	
          		for (messkolben2=(arrMesskolben.length-1); messkolben2>=0; messkolben2--)
           		{
                	
                   	for (pipette2=(arrPipetten.length-1); pipette2>=0; pipette2--)
                   	{
        
                   		// Berechnung der theoretischen Verdünnung (dblTV)
                   		Double dblTV = (arrMesskolben[messkolben1] / arrPipetten[pipette1]) * (arrMesskolben[messkolben2] / arrPipetten[pipette2]);
        		
                		// Berchnung des Volumens der Verdünnungsreihe
                		Double dblIstVol = (arrMesskolben[messkolben1] - arrPipetten[pipette1]) + (arrMesskolben[messkolben2] - arrPipetten[pipette2]);
                		
                		if ((dblIstVol <= dblMaxVol) && (dblVerduennungsfaktor == dblTV) && (intFeldNr <= 7))
                   		{
                		intFeldNr ++;
                       	TextView tv;
                       	String strErgebnis = Double.toString(arrPipetten[pipette1]) + "ml auf " +
                       						 Integer.toString(arrMesskolben[messkolben1]) + "mL / " +
                      						 Double.toString(arrPipetten[pipette2]) + "mL auf " +
                      						 Integer.toString(arrMesskolben[messkolben2]) + "mL";
                      	int resId = getResources().getIdentifier("tvErgebnis_"+(intFeldNr), "id", getPackageName());
                      	tv = (TextView) findViewById(resId);
                     	tv.setVisibility(View.VISIBLE);
                     	tv.setText(strErgebnis);		
                       	}		
        			}
        		}   		
            }
    	} 	
 	}
        	/************************************************************************
        	 ******************** Drei Verdünnungschritte ***************************
        	 ************************************************************************/
    	
    if (intFeldNr < 1)		// Ergeben sich im ersten und zweiten Verdünnungsschritt bereits Verdünnungen, wird der dritte Verdünnungsschritt nicht mehr durchlaufen.	
    {		
    		pipette1 = 0;
    		pipette2 = 0;
    		int pipette3 = 0;
    		messkolben1 = 0;
    		messkolben2 = 0;
    		int messkolben3 = 0;
    	
    	for (messkolben1=(arrMesskolben.length-1); messkolben1>=0; messkolben1--)
    	{
    	
    		for (pipette1=(arrPipetten.length-1); pipette1>=0; pipette1--)
    		{
           		               	
    			for (messkolben2=(arrMesskolben.length-1); messkolben2>=0; messkolben2--)
    			{
            	
    				for (pipette2=(arrPipetten.length-1); pipette2>=0; pipette2--)
    				{
    	    			
    					for (messkolben3=(arrMesskolben.length-1); messkolben3>=0; messkolben3--)
    	    			{
    	            	
    	    				for (pipette3=(arrPipetten.length-1); pipette3>=0; pipette3--)
    	    				{
    	    					// Berechnung der theoretischen Verdünnung (dblTV)
    	    					Double dblTV = (arrMesskolben[messkolben1] / arrPipetten[pipette1]) * (arrMesskolben[messkolben2] / arrPipetten[pipette2]) * (arrMesskolben[messkolben3] / arrPipetten[pipette3]);
               		
    	                		// Berchnung des Volumens der Verdünnungsreihe
    	                		Double dblIstVol = (arrMesskolben[messkolben1] - arrPipetten[pipette1]) + (arrMesskolben[messkolben2] - arrPipetten[pipette2]) + (arrMesskolben[messkolben3] - arrPipetten[pipette3]);
    	                		
    	                		if ((dblIstVol <= dblMaxVol) && (dblVerduennungsfaktor == dblTV) && (intFeldNr <= 7))
    	    					{
    	    					intFeldNr ++;
    	    					TextView tv;
    	    					String strErgebnis = Double.toString(arrPipetten[pipette1]) + "mL-" +
    	    										Integer.toString(arrMesskolben[messkolben1]) + "mL / " +
    	    										Double.toString(arrPipetten[pipette2]) + "mL-" +
    	    										Integer.toString(arrMesskolben[messkolben2]) + "mL / " +
    	    										Double.toString(arrPipetten[pipette3]) + "mL-" +
    	    										Integer.toString(arrMesskolben[messkolben3]) + "mL";
    	    					int resId = getResources().getIdentifier("tvErgebnis_"+(intFeldNr), "id", getPackageName());
    	    					tv = (TextView) findViewById(resId);
    	    					tv.setVisibility(View.VISIBLE);
    	    					tv.setText(strErgebnis);	
    	    					}		
    	    				}
    	    			}
    				}	
    			}   		
    		}
    	} 	
    }
    
    if (intFeldNr == 0)
    {
        String möglicheVerd = "Keine mögliche Verdünnung!"; 
    	Toast Meldung = Toast.makeText(this, möglicheVerd, Toast.LENGTH_LONG);
    	Meldung.setGravity(Gravity.CENTER, 0, 0);
    	Meldung.show();
    }
}} // else
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    	/*
    	
        for (int messkolben=(arrMesskolben.length-1); messkolben>=0; messkolben--) {
    		// Berechnung der theoretischen Pipette (dblTP)
    		Double dblTP = arrMesskolben[messkolben] / dblVSF;
    		Double dblEP = 0.0;
    		
    		if (dblTP < arrPipetten[0]) {
    			dblEP = arrPipetten[0];
    		}    		
    		else {
    			if (dblTP > arrPipetten[(arrPipetten.length-1)]) {
    				dblEP = arrPipetten[(arrPipetten.length-1)];
    			}
    		}
    		
    		if (dblEP == 0.0) {
	            for (int pipette=0; pipette<=(arrPipetten.length-2); pipette++) {
	            	if ((arrPipetten[pipette] < dblTP) && (dblTP < arrPipetten[pipette+1])) {
	        			Double dblDiff1 = dblTP - arrPipetten[pipette];
	        			Double dblDiff2 = arrPipetten[pipette+1] - dblTP;
	        			if (dblDiff1 < dblDiff2) {
	        				dblEP = arrPipetten[pipette];
	        			}
	        			else {
	        				dblEP = arrPipetten[pipette+1];
	        			}

	            		if (dblEP != 0.0) {
	            			break;
	            		}
	        			
	            	} // if ((arrPipetten[pipette] < dblTP) && (dblTP < arrPipetten[pipette+1]))	    		
	            } // for (int pipette=0; pipette<=(arrPipetten.length-1); pipette++)
    		} // if (dblEP == 0.0)
    		
            String text = "Messkolben: " + Integer.toString(arrMesskolben[messkolben])
 				      +"\nTP: " + Double.toString(dblTP)
				      +"\nEP: " + Double.toString(dblEP);
	  		Toast Meldung = Toast.makeText(this, text, Toast.LENGTH_LONG);
	  		Meldung.setGravity(Gravity.TOP, 0, 0);
	  		Meldung.show();
    		
    	} // for (int messkolben=0; messkolben<=(arrMesskolben.length-1); messkolben++)
*/
	} // btnRechneOnClickHandler
    
    // Eingabefelder zurücksetzen
    public void btnClear(View v)
    {
    	EditText et;
    	TextView tv;
    	et = (EditText) findViewById(R.id.Anfangskonz);
		et.setText("");
    	et = (EditText) findViewById(R.id.Endkonz);
		et.setText("");
		
    	for (int x=1; x<=8; x++)
    	{  
	    	int resId = getResources().getIdentifier("tvErgebnis_"+(x), "id", getPackageName());
	    	tv = (TextView) findViewById(resId);
	    	tv.setVisibility(View.GONE);
    	}
   
		// Cursor in erstes Eingabefeld setzen und numerische Tastatur einschalten 
    	et = (EditText) findViewById(R.id.Anfangskonz);
		et.requestFocus();
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);		
    } //btnClear
    
} // public class VerduennungsreiheActivity
