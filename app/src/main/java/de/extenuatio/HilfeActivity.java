package de.extenuatio;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class HilfeActivity extends Activity {
	List<Map<String, String>> hilfeListe = new ArrayList<Map<String,String>>();
	String[][] Hilfe = new String[10][4];
	int intFeldNr_max = 0;

	// 4 Spalten pro Hilfetext:
	// Spalte 0: Kapitel
	// Spalte 1: �berschrift, die in der Liste angezeigt wird (ListView)
	// Spalte 2: �berschrift, die in der Titelleiste des Meldungsfensters angezeigt wird
	// Spalte 3: Hilfetext

	private void erstelle_Hilfe_Menue(String strKapitel) {		
		int intFeldNr = 0;
		/*
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = getResources().getString(R.string.Beschriftung_Urtiter);
		Hilfe[intFeldNr][2] = "Neuer Titer mit Urtiter";
		Hilfe[intFeldNr][3] = "In dieser Routine l�sst sich der Titer von einer bekannten"
				+ " Ma�l�sung bestimmen. Der Titer wird dabei mit einem Urtiter eingestellt.";
		*/
		
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Einstellungen";
		Hilfe[intFeldNr][2] = "Button >Einstellungen<";
		Hilfe[intFeldNr][3] = "Unter Einstellungen l�sst sich die Anzahl der gerundeten Nachkommastellen"
				+ " von dem errechneten Gehalt und der relativen Standardabweichung einstellen. Die"
				+ " Nachkommastellen vom Titer lassen sich nicht eingestellen, weil dieser standardm��ig"
				+ " mit 4 Stellen angegeben wird.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "App verlassen";
		Hilfe[intFeldNr][2] = "App verlassen";
		Hilfe[intFeldNr][3] = "Mit diesem Button kann die App verlassen werden";

		intFeldNr_max = intFeldNr;
	} // erstelle_Hilfe_Menue
		
	private void erstelle_Hilfe_Einwaage(String strKapitel) {		
		int intFeldNr = 0;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Eingaben l�schen";
		Hilfe[intFeldNr][2] = "Button >Eingaben l�schen<";
		Hilfe[intFeldNr][3] = "Mit Hilfe dieses Buttons k�nnen alle Eingaben auf dem aktuellen"
            + " Bildschirm gel�scht werden. Eingaben von dem n�chsten oder vorherigen"
            + " Display bleiben dabei unber�hrt.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "zur Titration";
		Hilfe[intFeldNr][2] = "Button >zur Titration<";
		Hilfe[intFeldNr][3] = "Wurden alle Einwaagen (jedoch mindestens eine Einwaage), der Titer"
            + " und der volumetrische Faktor eingegeben, kann man mit diesem Button zum"
            + " n�chsten Bildschirm gewechseln, in welchem dann die Verbr�uche der einzelnen"
            + " Proben eintragen werden. Mit der Android Zur�cktaste, kann man zum vorheringen"
            + " Display zur�ckkehren, um �nderungen bei den Eingaben vorzunehmen.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Einwaage";
		Hilfe[intFeldNr][2] = "Eingabefeld > Einwaage <";
		Hilfe[intFeldNr][3] = "In diesen Eingabefeldern k�nnen bis zu 8 Einwaagen gleichzeitig eingegeben werden."
            + " Alle Einwaagen m�ssen in Gramm eingegeben werden. Nicht belegte Felder bleiben sp�ter bei"
            + " der Berechnung unber�cksichtigt.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Urtiter";
		Hilfe[intFeldNr][2] = "Eingabefeld > Urtiter (%) <";
		Hilfe[intFeldNr][3] = "Der Urtiter ist ein prim�rer Standard, der vollst�ndig mit einer entsprechenden Ma�l�sung"
            + " reagiert. In diesem Eingabefeld wird die Reinheit des Urtiters in Prozent eingegeben. Wird keine"
            + " Eingabe vorgenommen und zum n�chsten Display gewechselt, wird der Urtiter automatisch auf 100%"
            + " gesetzt. Mit der Android Zur�cktaste, kann man jedoch zum vorheringen"
            + " Display zur�ckkehren, und diese gesetzte Eingabe jederzeit korrigieren.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Titer";
		Hilfe[intFeldNr][2] = "Eingabefeld > Titer <";
		Hilfe[intFeldNr][3] = "Der Titer ist ein Faktor, der die Abweichung der tats�chlichen"
            + " Konzentration einer Ma�l�sung von der Nennkonzentration der L�sung angibt."
            + " In diesem Eingabefeld wird der Titer der Ma�l�sung mit der titriert wird"
            + " eingegeben. Wird keine Eingabe vorgenommen und zum n�chsten Display gewechselt,"
            + " wird der Titer automatisch auf 1,0000 gesetzt. Mit der Android Zur�cktaste,"
            + " kann man jedoch zum vorheringen Display zur�ckkehren, und diese gesetzte Eingabe"
            + " jederzeit korrigieren.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Faktor";
		Hilfe[intFeldNr][2] = "Eingabefeld > Faktor (mg/ml) <";
		Hilfe[intFeldNr][3] = "Der volumetrische Faktor setzt sich aus der Konzentration"
            + " der Ma�l�sung und der Molaren Masse des zu bestimmenden Stoffes zusammen."
            + " Der volumetrische Faktor muss grunds�tzlich bekannt sein und in Milligramm"
            + " pro Milliliter in das Eingabefeld eingegeben werden. Mit der Android"
            + " Zur�cktaste, kann man jederzeit zu diesem Display zur�ckkehren, und"
            + " seine Eingabe korrigieren.";

		intFeldNr_max = intFeldNr;
	} // erstelle_Hilfe_Einwaage

	private void erstelle_Hilfe_Berechnung(String strKapitel) {		
		int intFeldNr = 0;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Menue";
		Hilfe[intFeldNr][2] = "Button >Menue<";
		Hilfe[intFeldNr][3] = "R�ckkehr zum Hauptmenue";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Einstellungen";
		Hilfe[intFeldNr][2] = "Button >Einstellungen<";
		Hilfe[intFeldNr][3] = "Unter Einstellungen l�sst sich die Anzahl der gerundeten Nachkommastellen"
				+ " von dem errechneten Gehalt und der relativen Standardabweichung einstellen. Die"
				+ " Nachkommastellen vom Titer k�nnen nicht eingestellt werden, weil dieser in der Chemie"
				+ " standardm��ig mit 4 Stellen angegeben wird.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Berechne auf TS";
		Hilfe[intFeldNr][2] = "Umrechnung auf TS";
		Hilfe[intFeldNr][3] = "Die Trockensubstanz ist jener Bestandteil"
				+ " einer Substanz, der nach Abzug des Wassergehalts �brig bleibt."
				+ " Trockensubstanz und Wassergehalt erg�nzen sich also zu 100 Prozent."
				+ " Wird zuvor in dem Eingabefeld >Wasser � Probe< der � Wassergehalt der Proben"
				+ " eingeben, so kann mit Hilfe dieses Buttons der Gehalt (as is) auf die"
				+ " Trockensubstanz (TS) umgerechnet werden. Um den Gehalt wieder auf den"
				+ " as is Wert (= so wie es ist) zu berechnen, muss vor dem erneuten Bet�tigen"
				+ " des Buttons bei dem Wassergehalt wieder eine 0 eingegeben werden.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Gehalt";
		Hilfe[intFeldNr][2] = "Gehalt in %";
		Hilfe[intFeldNr][3] = "Der Gehalt as is (so wie es ist, mit Wasser) oder TS"
				+ " (Trockensubstanz ohne Wasser) wird in Gewichtsprozent angegeben."
				+ " Dabei steht Gewichtsprozent f�r die Anzahl Gramm pro 100 Gramm. Der Gehalt"
				+ " ist ein Ma� f�r die Reinheit einer Substanz.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Titer";
		Hilfe[intFeldNr][2] = "Titer";
		Hilfe[intFeldNr][3] = "Der Titer ist ein Faktor, der die Abweichung der tats�chlichen"
				+ " Konzentration einer Ma�l�sung von der Nennkonzentration der L�sung angibt."
				+ " Der Idealwert eines Titers ist 1,0000";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "% RSD";
		Hilfe[intFeldNr][2] = "Relative Standardabweichung RSD%";
		Hilfe[intFeldNr][3] = "Relative Standardabweichung ist ein statistisches Ma�"
				+ " f�r die Streuung der Me�werte in Prozent. Die Anzahl der gerundeten"
				+ " Nachkommastellen lassen sich unter dem Button >Einstellungen< verstellen.";

		intFeldNr_max = intFeldNr;
	} // erstelle_Hilfe_Berechnung

	private void erstelle_Hilfe_Verbrauch(String strKapitel) {		
		int intFeldNr = 0;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Eingaben l�schen";
		Hilfe[intFeldNr][2] = "Button >Eingaben l�schen<";
		Hilfe[intFeldNr][3] = "Mit Hilfe dieses Buttons k�nnen alle Eingaben auf dem aktuellen"
				+ " Bildschirm gel�scht werden. Eingaben von dem n�chsten oder vorherigen"
				+ " Display bleiben dabei unber�hrt.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Berechnung";
		Hilfe[intFeldNr][2] = "Button > Berechnung <";
		Hilfe[intFeldNr][3] = "Wurden alle Verbr�uche (jedoch mindestens ein Verbrauch)"
				+ " eingegeben, kann man mit diesem Button zum n�chsten Bildschirm"
				+ " gewechseln, in welchem dann die Ergebnisse der einzelnen Proben"
				+ " angezeigt werden. Mit der Android Zur�cktaste, kann man jederzeit"
				+ " zum vorheringen Display zur�ckkehren, um �nderungen bei den Eingaben"
				+ " vorzunehmen.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Vorlage / Blindwerte";
		Hilfe[intFeldNr][2] = "Button > Vorlage / Blindwerte <";
		Hilfe[intFeldNr][3] = "Mit diesem Button kann man zu einem Bildschirm"
		+ " gewechseln, in welchem, falls jeweils vorhanden, das Volumen einer Vorlage"
		+ " oder die Verbr�uche von Blindwerten, eingeben werden. Um zu dem Display"
		+ " mit den Probenverbr�uchen zur�ckzukehren muss die Android Zur�cktaste 2x"
		+ " bet�tigt werden.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Verbrauch Probe";
		Hilfe[intFeldNr][2] = "Eingabefeld > Verbrauch Probe <";
		Hilfe[intFeldNr][3] = "In diesen Eingabefeldern k�nnen, entsprechend der Einwaagen,"
				+ " bis zu 8 Verbr�uche gleichzeitig eingegeben werden. Der Verbrauch an"
				+ " Ma�l�sung f�r jede Probe wird in Milliliter eingegeben. Ein Verbrauch von 0 ml"
				+ " ist ebenfalls m�glich.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Verbrauch Blindwerte";
		Hilfe[intFeldNr][2] = "Eingabefeld > Volumen Vorlage oder Verbrauch Blindwerte <";
		Hilfe[intFeldNr][3] = "In diesen Eingabefeldern k�nnen bis zu 8 Verbr�uche von Blindwerten in Milliliter"
				+ " eingegeben werden. Die Verbr�uche von mehreren Blindwerten werden automatisch zu"
				+ " einem � Blindwert gemittelt. Ein Blindwertverbrauch von 0ml ist ebenfalls m�glich."
				+ " Der errechnete � Blindwert wird dann f�r die Berechnung verwendet und standardm��ig von den "
				+ " Probenverbr�uchen abgezogen. Ist der errechnete � Blindwert gr��er, als ein Probenverbrauch,"
				+ " so kann dieser sp�ter nach einer Abfrage in einer Dialogbox von den Verbr�uchen der Proben abgezogen"
				+ " werden. WICHTIG: Vorlage und Blindwerte k�nnen nicht nebeneinander eingegeben werden!"
				+ " Nicht belegte Felder bleiben sp�ter f�r die Berechnung unber�cksichtigt.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Vorlagevolumen";
		Hilfe[intFeldNr][2] = "Eingabefeld > Volumen Vorlage <";
		Hilfe[intFeldNr][3] = " Wird bei einer R�cktitration mit einer Vorlage gearbeitet, kann auch"
				+ " in einem Eingabefeld das entsprechende Volumen in Milliliter der Vorlage eingegeben werden."
				+ " Nicht belegte Felder bleiben sp�ter f�r bei der Berechnung unber�cksichtigt."
				+ " WICHTIG: Vorlage und Blindwerte k�nnen nicht nebeneinander eingesetzt werden!"
				+ " Da das Vorlagevolumen in der Regel gr��er ist, als die Probenverbr�uche, kann dieses"
				+ " sp�ter nach einer Abfrage in einer Dialogbox von dem Verbr�uchen der Proben abgezogen werden.";

		intFeldNr_max = intFeldNr;
	} // erstelle_Hilfe_Verbrauch
		
	private void erstelle_Hilfe_Titereingaben(String strKapitel) {		
		int intFeldNr = 0;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Eingaben l�schen";
		Hilfe[intFeldNr][2] = "Button >Eingaben l�schen<";
		Hilfe[intFeldNr][3] = "Mit Hilfe dieses Buttons k�nnen alle Eingaben auf dem aktuellen"
				+ " Bildschirm gel�scht werden. Eingaben von dem n�chsten oder vorherigen"
				+ " Display bleiben dabei unber�hrt.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "zur Titration";
		Hilfe[intFeldNr][2] = "Button >zur Titration<";
		Hilfe[intFeldNr][3] = "Wurde in alle 4 Eingabefeldern ein Wert eingegeben, kann man mit diesem Button zum"
				+ " n�chsten Bildschirm gewechseln, in welchem dann die Verbr�uche der einzelnen"
				+ " Proben eintragen werden. Mit der Android Zur�cktaste, kann man zum vorheringen"
				+ " Display zur�ckkehren, um �nderungen bei den Eingaben vorzunehmen.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Titer der bekannten Ma�l�sung";
		Hilfe[intFeldNr][2] = "Eingabefeld > Titer der bekannten Ma�l�sung <";
		Hilfe[intFeldNr][3] = "In diesem Eingabefeld wird der Titer der bekannten Ma�l�sung eingeben.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Vorlage (ml) der bekannten Ma�l�sung";
		Hilfe[intFeldNr][2] = "Eingabefeld > Vorlage (ml) der bekannten Ma�l�sung <";
		Hilfe[intFeldNr][3] = "In diesem Eingabefeld wird von der bekannten Ma�l�sung"
				+ " das Volumen der Vorlage in Milliliter eingeben.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Molarit�t (mol/l) der bekannten Ma�l�sung";
		Hilfe[intFeldNr][2] = "Eingabefeld > Molarit�t (mol/l) der bekannten Ma�l�sung <";
		Hilfe[intFeldNr][3] = "In diesem Eingabefeld wird die Molarit�t in Mol pro Liter von der"
				+ " bekannten Ma�l�sung eingeben. Die Molarit�t oder auch die Stoffmengenkonzentration"
				+ " ist der Quotient aus der Stoffmenge eines gel�sten Stoffes.";

		intFeldNr++;
		Hilfe[intFeldNr][0] = strKapitel;
		Hilfe[intFeldNr][1] = "Molarit�t (mol/l) der zu bestimmenden Ma�l�sung";
		Hilfe[intFeldNr][2] = "Eingabefeld > Molarit�t (mol/l) der zu bestimmenden Ma�l�sung <";
		Hilfe[intFeldNr][3] = "In diesem Eingabefeld wird die Molarit�t in Mol pro Liter von der"
				+ " zu bestimmenden Ma�l�sung eingeben. Die Molarit�t oder auch die Stoffmengenkonzentration"
				+ " ist der Quotient aus der Stoffmenge eines gel�sten Stoffes.";

		intFeldNr_max = intFeldNr;
	} // erstelle_Hilfe_Titereingaben
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hilfe);

		// Activity registrieren, damit sie sp�ter an zentraler Stelle (Hauptmenue) geschlossen werden kann
	    ActivityRegistry.register(this);
		
		Bundle extras = getIntent().getExtras();
		String strKapitel;
		
		strKapitel = extras.getString("Kapitel");
		
		if (strKapitel.equals("Menue") == true) {
			erstelle_Hilfe_Menue(strKapitel);
		} else if (strKapitel.equals("Einwaage") == true) {
			erstelle_Hilfe_Einwaage(strKapitel);
		} else if (strKapitel.equals("Verbrauch") == true) {
			erstelle_Hilfe_Verbrauch(strKapitel);
		} else if (strKapitel.equals("Titereingaben") == true) {
			erstelle_Hilfe_Titereingaben(strKapitel);
		} else if (strKapitel.equals("Berechnung") == true) {
			erstelle_Hilfe_Berechnung(strKapitel);
		};
		
		initList();

	    // Die ListView-Komponente kommt aus dem Layout
	    ListView lv = (ListView) findViewById(R.id.lvHilfe);

	    // Der 'SimpleAdapter' erwartet folgende Parameter:
	    //  1. Kontext-Referenz (Activity)
	    //  2. Daten, die angezeigt werden sollen (hier vom Typ 'ArrayList')
	    //  3. Layout, das f�r jede Zeile in der Liste verwendet werden soll
	    //  4. String-Array mit den Schl�sseln, nach denen die Datenliste gefiltert werden soll (hier: 'Kapitel')
	    //  5. Integer-Array mit den View-IDs, die je Zeile angezeigt werden sollen
	    //  Die Gr��e der Arrays der Parameter 4 und 5 muss identisch sein!
	    //SimpleAdapter simpleAdpt = new SimpleAdapter(this, hilfeListe, android.R.layout.simple_list_item_1, new String[] {strKapitel}, new int[] {android.R.id.text1});
	    SimpleAdapter simpleAdpt = new SimpleAdapter(this, hilfeListe, R.layout.my_listview, new String[] {strKapitel}, new int[] {R.id.tvHilfezeile});

	    lv.setAdapter(simpleAdpt);		

	    // hier wird festgelegt, was passieren soll, wenn der Anwender eine Zeile anklickt:
	    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	         public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
	        	 
	        	 LinearLayout ll = (LinearLayout) view;
	             TextView clickedView = (TextView) ll.getChildAt(0);
	        	 
	            // es ist bekannt, dass der angeklickte View eine TextView ist; er wird daher als solcher angesprochen 
	            //TextView clickedView = (TextView) view;
             	
	         	for (int intFeldNr=0; intFeldNr<=intFeldNr_max; intFeldNr++) {
	        	    if (Hilfe[intFeldNr][1] == clickedView.getText()) {	        	    	
	        			AlertDialog.Builder builder = new AlertDialog.Builder(HilfeActivity.this);
	        			builder.setTitle(Hilfe[intFeldNr][2]);
	        			builder.setMessage(Hilfe[intFeldNr][3]);
	        			builder.setPositiveButton("OK",
	        					new DialogInterface.OnClickListener()
	        						{
	        							public void onClick(DialogInterface dialog, int id)
	        							{
	        								dialog.dismiss();
	        							}		
	        						});

	        			AlertDialog dialog = builder.create();
	        			dialog.show();

	        			break; // for-Schleife vorzeitig beenden
	        	    } // if (Hilfe[zeile][1] == clickedView.getText())
	      		} // for
	         } // onItemClick
	    }); // setOnItemClickListener	    
	} // onCreate

	private void initList() {
    	for (int zeile=0; zeile<=intFeldNr_max; zeile++) {
    	    hilfeListe.add(erstelleHilfe(Hilfe[zeile][0], Hilfe[zeile][1]));
  		}
	}

	private LinkedHashMap<String, String> erstelleHilfe(String key, String name) {
	    LinkedHashMap<String, String> hilfetext = new LinkedHashMap<String, String>();
	    hilfetext.put(key, name);
	
	    return hilfetext;
	}
	
} // HilfeActivity
