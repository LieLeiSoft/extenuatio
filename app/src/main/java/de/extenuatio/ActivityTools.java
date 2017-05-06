package de.extenuatio;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ActivityTools {
	// Gleitkommazahl runden
	public static double fktRunden(double Zahl, int AnzahlStellen) {
		long Dummy =  (long) Math.pow(10, AnzahlStellen);
		double Ergebnis = (long) ((Zahl*Dummy) + (0.5*Math.signum(Zahl))) / (Dummy*1.0); // *1.0 damit's ein double wird!
		
		return Ergebnis;		
	} // fktRunden

	/*
	   Text duplizieren
	   
       Ein Text wird so oft dupliziert, wie im 2. Parameter angegeben
       Beispiel: repeat("ha", 5) 
                 gibt den Text "hahahahaha" zurück
    
	   http://rosettacode.org/wiki/Repeat_a_string#Java
	   Works with: Java version 1.5+
	 */
	public static String repeat(String str, int times){
		return new String(new char[times]).replace("\0", str);
	} // repeat
	
	// Gleitkommazahl in Text umwandeln
	public static String fktDoubleToStringFormat(double Zahl, int AnzahlStellen) {
		double dblWert;
		String strFormat;
		String strErgebnis = "";
		
		dblWert = fktRunden(Zahl, AnzahlStellen);
		
		strFormat = "#0."+repeat("0", AnzahlStellen);
		NumberFormat formatter = new DecimalFormat(strFormat); 
		strErgebnis = formatter.format(dblWert);

		// Punkt durch Komma ersetzen. Dadurch ist sichergestellt, dass im Ergebnis kein Punkt als Dezimaltrennzeichen steht.
    	strErgebnis = strErgebnis.replace(".", ",");
    	
		return strErgebnis;
	} // fktDoubleToStringFormat

	/*
		Text in Zahl umwandeln
		Ist der übergebene Text leer, wird 0 zurückgegeben
	*/
	public static double fktBlankToNumber(String strParameter){
    	double Ergebnis = 0;
    	if (strParameter.equals("") == false) {
           	Ergebnis = Double.parseDouble(strParameter);
   		}
		return Ergebnis;		
    } // fktBlankToNumber

	// n-Wurzel aus x ziehen
	public static double fktnSqrt(double x, int n) {
		   return Math.pow(x, (double) 1/n);
	} // nSqrt
	
	// Berechnung der jeweiligen Masse (Säure, Wasser, Verdünnung), falls möglich
	public static void subMasse(Double dblm1, Double dblm2, Double dblmM)
	{
		if ((dblm1 == 0) && (dblm2 != 0) && (dblmM != 0))
		{
			dblm1 = dblmM - dblm2;
		}
		if ((dblm2 == 0) && (dblm1 != 0) && (dblmM != 0))
		{
			dblm2 = dblmM - dblm1;
		}
		if ((dblmM == 0) && (dblm1 != 0) && (dblm2 != 0))
		{
			dblmM = dblm1 + dblm2;
		}	
	} // subMasse
	
} // ActivityTools
