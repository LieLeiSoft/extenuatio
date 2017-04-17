package de.extenuatio;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EinstellungenActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     	setContentView(R.layout.einstellungen);	     
  
     	//String arrNachkommastellenGehalt[];
     	//String arrNachkommastellenRSD[];

     	// Spinner "Gehalt" einrichten
    	//arrNachkommastellenGehalt = getResources().getStringArray(R.array.Nachkommastellen);
		Spinner spinnerGehalt = (Spinner) findViewById(R.id.spinnerGehalt);		
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrNachkommastellenGehalt);

		//spezieller Adapter für Spinner: ermöglicht ein eigenes Layout (res\layout\my_spinner.xml)
		//Layout ist mit Style gekoppelt (res\values\my_style.xml). Dort können Android-Attribute 'textSize' und 'textColor' gesetzt werden!
		//http://androidcookbook.com/Recipe.seam?recipeId=4012
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Nachkommastellen, R.layout.my_spinner);
		//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerGehalt.setAdapter(adapter);		
        
     	// Spinner "RSD" einrichten
		// arrNachkommastellenRSD = getResources().getStringArray(R.array.Nachkommastellen);
		Spinner spinnerRSD = (Spinner) findViewById(R.id.spinnerRSD);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Nachkommastellen, R.layout.my_spinner);
		//adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerRSD.setAdapter(adapter2);
		//ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrNachkommastellenRSD);
		//spinnerRSD.setAdapter(adapter2);		
    } // onCreate

    /** wird ausgeführt, wenn Activicty angezeigt wird */
    @Override
    public void onResume() {
    	super.onResume();
    	
    	int intSpinnerID;
    	    	
       	// Zugang zur Konfigurationsdatei ("Preferences") herstellen
   		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());		

    	// Werte aus Konfigurationsdatei ("Preferences") auslesen und in Spinner eintragen
   		intSpinnerID = prefs.getInt("NachkommastellenGehalt", 2); // standardmäßig "2" auswählen   		    			    	
		Spinner spinnerGehalt = (Spinner) findViewById(R.id.spinnerGehalt);		
		spinnerGehalt.setSelection(intSpinnerID); 

		intSpinnerID = prefs.getInt("NachkommastellenRSD", 2); // standardmäßig "2" auswählen   		
		Spinner spinnerRSD = (Spinner) findViewById(R.id.spinnerRSD);		
		spinnerRSD.setSelection(intSpinnerID); 
    } // onResume
    
	/** wird ausgeführt, wenn zu einer anderen Activicty gewechselt wird */
	@Override
	public void onPause() {
		super.onPause();

		int intWert;
		
       	// Zugang zur Konfigurationsdatei ("Preferences") herstellen
   		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
   		SharedPreferences.Editor prefEditor = prefs.edit();

		Spinner spinnerGehalt = (Spinner) findViewById(R.id.spinnerGehalt);		
		intWert = Integer.parseInt(spinnerGehalt.getSelectedItem().toString());   		
   		prefEditor.putInt("NachkommastellenGehalt", intWert);
    
		Spinner spinnerRSD = (Spinner) findViewById(R.id.spinnerRSD);		
		intWert = Integer.parseInt(spinnerRSD.getSelectedItem().toString());   		
   		prefEditor.putInt("NachkommastellenRSD", intWert);
    
   		prefEditor.apply();	    		
	} // onPause
    
} // Public Class   