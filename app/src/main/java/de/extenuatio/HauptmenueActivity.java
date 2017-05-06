package de.extenuatio;



import java.util.Map;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

@SuppressLint("NewApi")
public class HauptmenueActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     	setContentView(R.layout.hauptmenue);	     
    }
    
    /** wird ausgeführt, wenn Activicty angezeigt wird */
	@Override
	public void onResume() {
		super.onResume();
		
		// bestimmte Einträge aus Konfigurationsdatei ("Preferences") entfernen
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
   		SharedPreferences.Editor prefEditor = prefs.edit();

   		// prefs.edit().clear().commit(); // setzt ALLE Werte zurück!
   		
   		String strKeyName;
   		
   		// alle Parameter in eine String-Liste schreiben
   		Map<String,?> keys = prefs.getAll();

   		// String-Liste Eintrag für Eintrag durchgehen
   		for(Map.Entry<String,?> entry : keys.entrySet()){
   			strKeyName = entry.getKey();
   			// prüfen, ob Parameter "NachkommastellenGehalt" oder "NachkommastellenRSD" ist
   			// (diese beiden Parameter sollen NICHT entfernt werden)
   			if ("NachkommastellenGehalt,NachkommastellenRSD".indexOf(strKeyName) == -1) {   				
   				// Parameter entfernen 
   				prefEditor.remove(strKeyName);
   			}
   		 }   		
	    prefEditor.apply();	    		
		
	} // onResume
	
    @SuppressLint("NewApi")
	public void btnOnClickEndkonzentration (View v)
    {
        // Zugang zur Konfigurationsdatei ("Preferences") herstellen
     	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
     	SharedPreferences.Editor prefEditor = prefs.edit(); 
     	                         
    	prefEditor.putInt("Maske", 1);
    	prefEditor.apply();		       
    	
    	Intent myIntent = new Intent(v.getContext(), EndkonzentrationActivity.class);
                   
        // verhindern, dass die Activity ein weiteres Mal geöffnet wird, wenn sie bereits geöffnet wurde
        myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

		// Activity aufrufen
        startActivity(myIntent);
    }
    
    @SuppressLint("NewApi")
	public void btnOnClickVerduennungsreihe(View v)
    {
        // Zugang zur Konfigurationsdatei ("Preferences") herstellen
     	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
     	SharedPreferences.Editor prefEditor = prefs.edit(); 
     	
    	prefEditor.putInt("Maske", 2);
    	prefEditor.apply();		       
    	
    	
        Intent myIntent = new Intent(v.getContext(), VerduennungsreiheActivity.class);

        // verhindern, dass die Activity ein weiteres Mal geöffnet wird, wenn sie bereits geöffnet wurde
        myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

		// Activity aufrufen
        startActivity(myIntent);
    }   
    
    @SuppressLint("NewApi")
	public void btnOnClickVerduennen(View v)
    {
        // Zugang zur Konfigurationsdatei ("Preferences") herstellen
     	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
     	SharedPreferences.Editor prefEditor = prefs.edit(); 
     	
    	prefEditor.putInt("Maske", 3);
    	prefEditor.apply();		       
    	
        Intent myIntent = new Intent(v.getContext(), VerduennenActivity.class);

        // verhindern, dass die Activity ein weiteres Mal geöffnet wird, wenn sie bereits geöffnet wurde
        myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

		// Activity aufrufen
        startActivity(myIntent);
    }   
    
    public void btnOnClickEinstellungen(View v)
    {
        Intent myIntent = new Intent(v.getContext(), EinstellungenActivity.class);

        // verhindern, dass die Activity ein weiteres Mal geöffnet wird, wenn sie bereits geöffnet wurde
        myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

		// Activity aufrufen
        startActivity(myIntent);
    }
    public void btnOnClickHilfe(View v)
    {
        Intent myIntent = new Intent(v.getContext(), HilfeActivity.class);

        // verhindern, dass die Activity ein weiteres Mal geöffnet wird, wenn sie bereits geöffnet wurde
        myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        // als Parameter wird der Name des anzuzeigenen Hilfe-Kapitels übergeben
        myIntent.putExtra("Kapitel", "Menue");
        
		// Activity aufrufen
        startActivity(myIntent);
    }
    public void btnOnClickExitApp(View v)
    {
		// alle registrierten Activities schliessen
        ActivityRegistry.finishAll();
        finish(); 
        System.exit(0);
    } // btnOnClickExitApp
}