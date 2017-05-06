package de.extenuatio;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
public class EndkonzentrationActivity extends Activity implements OnFocusChangeListener {

    public static final int Einheit1_ID=0;
    public static final int Einheit2_ID=1;

	private String arrEinheiten[];
		
	private double fktRunden(double Zahl, int AnzahlStellen) {
		long Dummy =  (long) Math.pow(10, AnzahlStellen);
		double Ergebnis = (long) ((Zahl*Dummy) + (0.5*Math.signum(Zahl))) / (Dummy*1.0); // *1.0 damit's ein double wird!
				
		return Ergebnis;		
	}

	/*
	   Repeat a string
       Take a string and repeat it some number of times. Example: repeat("ha", 5) => "hahahahaha"

       There's no method or operator to do this in Java, so you have to do it yourself.
       
	   http://rosettacode.org/wiki/Repeat_a_string#Java
	   Works with: Java version 1.5+
	 */
	public static String repeat(String str, int times){
		return new String(new char[times]).replace("\0", str);
	}
	
	private String fktErgebnisAufbereiten(double dblErgebnis) {
		String strErgebnis;
        int intAnzahl_VK
           ,intAnzahl_NK;
		double dblMin
		      ,dblMax;
    	
    	NumberFormat formatter = new DecimalFormat("#0.#########"); // max. bis zu 9 Nachkommastellen vorsehen

    	intAnzahl_VK = 6; // max. Anzahl Vorkommastellen
		intAnzahl_NK = 9; // max. Anzahl Nachkommastellen
		
		dblMin = 1 / Math.pow(10, (intAnzahl_NK - 1));
		dblMax = Math.pow(10, intAnzahl_VK) - (1 / Math.pow(10, (intAnzahl_NK)));
		
    	strErgebnis = "";
    	
        // es wird schon hier gerundet, damit z.B. 1875,00000000002 später als glatte Zahl erkannt wird
    	dblErgebnis = fktRunden(dblErgebnis, intAnzahl_NK); // auf x Nachkommastellen runden

    	if ((dblErgebnis > 0) && (dblErgebnis < dblMin)) { 
    		strErgebnis = "<" + formatter.format(dblMin);
    	}
    	else if (dblErgebnis > dblMax) { 
    		strErgebnis = ">" + Double.toString(dblMax);
    	}
    	else if ((dblErgebnis == 0) || (dblErgebnis - Math.floor(dblErgebnis)) == 0) {    	
    		// Ergebnis ist 0 oder eine glatte Zahl
    		strErgebnis = Integer.toString((int) dblErgebnis);
    		strErgebnis = repeat(" ", ((intAnzahl_VK+1)-strErgebnis.length())) + strErgebnis; //intAnzahl_VK+1 wegen "<" bzw. ">" an erster Stelle!
    	}
    	else {    	
    		// Ergebnis enthält Nachkommastellen
    		// Zahlenformat explizit setzen, um Exponentialschreibweise zu verhindern (Bsp.: 5.0E-4 ==> 0.5)
    		strErgebnis = formatter.format(dblErgebnis);
    	}

    	// Punkt durch Komma ersetzen. Dadurch ist sichergestellt, dass im Ergebnis kein Punkt als Dezimaltrennzeichen steht.
    	strErgebnis = strErgebnis.replace(".", ",");
    	if (strErgebnis.indexOf(",") > 0) {
    		String text = repeat(" ",(intAnzahl_VK+1)) + strErgebnis + repeat(" ",intAnzahl_NK);
    		strErgebnis = text.substring(text.indexOf(",") - (intAnzahl_VK+1), text.indexOf(",") + (intAnzahl_NK+1)); // intAnzahl_NK+1 weil "," berücksichtigt werden muss!
    	}
    	    	
    	return strErgebnis;
	} // private String fktErgebnisAufbereiten(double dblErgebnis)
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endkonzentration);

		arrEinheiten = getResources().getStringArray(R.array.Einheiten);
		Spinner spnEinheit = (Spinner) findViewById(R.id.spnEinheit);		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrEinheiten);
		spnEinheit.setAdapter(adapter);		
		spnEinheit.setSelection(Einheit2_ID); // standardmäßig "Gramm" auswählen
        
		/*
		 Hintergrundfarbe der Einggabefelder setzen:
		 Wenn die 'Background'-Eigenschaft direkt im Layout (main.xml) gesetzt wird, verkleinert sich das Feld!!! :-(
		 
		http://stackoverflow.com/questions/1625633/problem-with-edittext-background-android
		•"getBackground()" fetches the background from the component
		•"setColorFilter" will call a filtering on the background image itself
		•"Color.<your-color-here>" determines what color you want to pass onto the filter
		•"PorterDuff.Mode.<your-desired-filter-mode>" sets the kind of manipulation you would like to do with the given color and the fetched background image. 
		  People with knowledge of image editing software might recognise the mode. Each mode has a certain effect on how the color is applied to the background image. 
		  To simply "override" the color of the image, while preserving its gradients, borders and such, use MULTIPLY.
		 */
    	for (int zeile=1; zeile<=3; zeile++) {
        	for (int spalte=1; spalte<=2; spalte++) {
        		int resId = getResources().getIdentifier("Zahl"+zeile+'_'+spalte, "id", getPackageName());
        		EditText et = (EditText) findViewById(resId);
        		et.getBackground().setColorFilter(getResources().getColor(R.color.cEingabe), PorterDuff.Mode.MULTIPLY);
      		}	
  		}
		EditText et = (EditText) findViewById(R.id.Substanzreinheit);
		et.getBackground().setColorFilter(getResources().getColor(R.color.cEingabe), PorterDuff.Mode.MULTIPLY);

		// Eingabefelder mit Listener verbinden
		et = (EditText) findViewById(R.id.Zahl1_2);
        et.setOnFocusChangeListener(this);
		et = (EditText) findViewById(R.id.Zahl2_2);
        et.setOnFocusChangeListener(this);
		
		// Cursor in erstes Eingabefeld setzen 
        et = (EditText) findViewById(R.id.Zahl1_1);
        et.requestFocus();
       
/* Testwerte
		et = (EditText) findViewById(R.id.Zahl1_1);
		et.setText("50.694");
		et = (EditText) findViewById(R.id.Zahl1_2);
		et.setText("5");
*/		    	
    } // public void onCreate(Bundle savedInstanceState)

    
    // Implement the onFocusChange callback
    public void onFocusChange(View v, boolean hasFocus) {
		int CurrentID = v.getId();
		if (CurrentID == R.id.Zahl1_2) {
	    	EditText et = (EditText) findViewById(R.id.Zahl2_1);
			et.setVisibility(View.VISIBLE);
			et = (EditText) findViewById(R.id.Zahl2_2);
			et.setVisibility(View.VISIBLE);
		} 
		else {
	    	EditText et = (EditText) findViewById(R.id.Zahl3_1);
			et.setVisibility(View.VISIBLE);
			et = (EditText) findViewById(R.id.Zahl3_2);
			et.setVisibility(View.VISIBLE);
		}	
    }
    
    public void btnRechneOnClickHandler(View v) {
    	Spinner spnEinheit;
    	EditText et;
    	TextView tv;

    	String Eingabetext
    	      ,strErgebnis
    	      ,strEinheit;
    	
    	// Index der Felder im Array beginnt mit 0 (nicht mit 1!)! D.h. erstes Element ist matrix[0][0]
    	double[][] matrix = new double[3][2];
    	double dblErgebnis
     	      ,dblErgebnis2
	          ,dblErgebnis3
    	      ,dblErgebnis4
    	      ,dblSubstanzreinheit;
    	
    	
    	// Ergebnisfelder initialisieren
    	strErgebnis = "";
    	dblErgebnis = 0;

    	// alle Werte aus den Eingabefeldern in internes Array (matrix) übertragen
    	for (int zeile=0; zeile<=2; zeile++) {
        	for (int spalte=0; spalte<=1; spalte++) {
        		int resId = getResources().getIdentifier("Zahl"+(zeile+1)+'_'+(spalte+1), "id", getPackageName());
   	           	et = (EditText) findViewById(resId);
   	           	Eingabetext = et.getText().toString();
           		// Wert wird nur übernommen, wenn Eingabefeld nicht leer ist
   	           	if (Eingabetext.equals("") == false) {
   	           		matrix[zeile][spalte] = Double.parseDouble(Eingabetext);
           		}
      		}	
  		}	
    	
    	// Ergebnis 1 berechnen 
    	// Berechnung für Zeile 1
    	if ((matrix[0][0] != 0) && (matrix[0][1] != 0)) {
    		dblErgebnis = matrix[0][0] / matrix[0][1];
    	}
    	
    	// Berechnung für Zeile 2
    	if ((matrix[1][0] != 0) && (matrix[1][1] != 0)) {
    		dblErgebnis = dblErgebnis * (matrix[1][0] / matrix[1][1]);
    	}
    	
    	// Berechnung für Zeile 3
    	if ((matrix[2][0] != 0) && (matrix[2][1] != 0)) {
    		dblErgebnis = dblErgebnis * (matrix[2][0] / matrix[2][1]);
    	}

    	// ggf. in Einheit Milligramm umrechnen
    	spnEinheit = (Spinner) findViewById(R.id.spnEinheit);
    	strEinheit = spnEinheit.getSelectedItem().toString();    
    	if (strEinheit.equals("Milligramm")) {
    		dblErgebnis = dblErgebnis / 1000;    	
    	}
    	    	    	
    	// Substanzreinheit berücksichtigen
        et = (EditText) findViewById(R.id.Substanzreinheit);
        Eingabetext = et.getText().toString();
   		// Wert wird nur übernommen, wenn Eingabefeld nicht leer ist
        if (Eingabetext.equals("") == false) {
        	dblSubstanzreinheit = Double.parseDouble(Eingabetext);
        	dblErgebnis = dblErgebnis * dblSubstanzreinheit / 100;
   		}
    	
    	// Ergebnis 1 (g/ml) ausgeben
    	strErgebnis = fktErgebnisAufbereiten (dblErgebnis);
    	tv = (TextView) findViewById(R.id.tvErgebnis);
    	tv.setText(strErgebnis);
    	
    	// Ergebnis 2 (mg/ml) berechnen und ausgeben
    	dblErgebnis2 = dblErgebnis * 1000;
    	strErgebnis = fktErgebnisAufbereiten (dblErgebnis2);
    	tv = (TextView) findViewById(R.id.tvErgebnis2);
    	tv.setText(strErgebnis);
    	
    	// Ergebnis 3 (ppm) berechnen und ausgeben
    	dblErgebnis3 = dblErgebnis * 1000 * 1000;
    	strErgebnis = fktErgebnisAufbereiten (dblErgebnis3);
    	tv = (TextView) findViewById(R.id.tvErgebnis3);
    	tv.setText(strErgebnis);

    	// Ergebnis 4 (%) berechnen und ausgeben
    	dblErgebnis4 = dblErgebnis * 100;
    	strErgebnis = fktErgebnisAufbereiten (dblErgebnis4);
    	tv = (TextView) findViewById(R.id.tvErgebnis4);
    	tv.setText(strErgebnis);

		// Anzeige der numerischen Tastatur ausschalten, um mehr Anzeigeplatz zu haben
    	InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    	if (getCurrentFocus() != null) {
    	    imm.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), 0);
    	}
    } // public void btnRechneOnClickHandler(View v)
    
    public void btnClearOnClickHandler(View v) {
        // Eingabefelder zurücksetzen
    	for (int zeile=1; zeile<=3; zeile++) {
        	for (int spalte=1; spalte<=2; spalte++) {
        		int resId = getResources().getIdentifier("Zahl"+zeile+'_'+spalte, "id", getPackageName());
        		EditText et = (EditText) findViewById(resId);
        		et.setText("");
      		}	
  		}   	
		EditText et = (EditText) findViewById(R.id.Substanzreinheit);
		et.setText("");
        // Ausgabefelder (Ergebnisfelder) zurücksetzen
		for (int zeile=2; zeile<=4; zeile++) {
    		int resId = getResources().getIdentifier("tvErgebnis"+zeile, "id", getPackageName());
        	TextView tv = (TextView) findViewById(resId);
        	tv.setText(R.string.Ergebnis);    		
    	}
    	TextView tv = (TextView) findViewById(R.id.tvErgebnis);
    	tv.setText(R.string.Ergebnis);    		
		// Cursor in erstes Eingabefeld setzen und numerische Tastatur einschalten 
		et = (EditText) findViewById(R.id.Zahl1_1);
		et.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);
        
        // Eingabefelder in Zeile 2+3 wieder unsichtbar machen (einklappen)
    	for (int zeile=2; zeile<=3; zeile++) {
        	for (int spalte=1; spalte<=2; spalte++) {
        		int resId = getResources().getIdentifier("Zahl"+zeile+'_'+spalte, "id", getPackageName());
        		et = (EditText) findViewById(resId);
        		et.setVisibility(View.GONE);
      		}
  		}
    } // public void btnClearOnClickHandler(View v)
}