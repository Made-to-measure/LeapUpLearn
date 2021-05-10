package de.nrw.hspv.exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class IPExercise {
	/**
	 * Erstellt eine Aufgabe zur IP-Adressberechnung/Subnetting
	 * 
	 * @author Jannik
	 * @version 1.0
	 */
	
	private Random rand = new Random();
	private IPNetworkAddress networkAddress;
	private IPAddress hostAddress, broadcastAddress, subnetmask, firstAddress, lastAddress;
	private boolean[] exerciseType;
	
	/**
	 * Konstruiert eine Neue Aufgabe
	 */
	public IPExercise(){
		this.networkAddress =  new IPNetworkAddress(generateCidr());
		this.firstAddress = networkAddress.createFirstAddress();
		this.lastAddress = networkAddress.createLastAddress();
		this.broadcastAddress = networkAddress.createBroadcastAddress();
		this.subnetmask = networkAddress.createSubnetMask();
		this.exerciseType = generateExerciseType();
	}
	
	/**
	 * lediglich eine Testklasse um funktionen des Programms in der Kosnole zu testen
	 */
	private void play() { 
		System.out.println("Geben Sie eine Hostadresse ein:");
		String input = new java.util.Scanner(System.in).nextLine(); 
	}
	/**
	 * Test-Klasse um die einzelnen Werte in der Konsole auszugeben
	 */
	public void testPrint() {	//Klasse f�rs Debugging
		System.out.println(	"\nNetwerkadresse: \t" + networkAddress.toStringIpFormat() + "/" + networkAddress.getCidr() +
							"\nNetzwerkmaske: \t \t" + subnetmask.toStringIpFormat() +
							"\nerste Adresse: \t \t" + firstAddress.toStringIpFormat() +
							"\nletze Adresse: \t \t" + lastAddress.toStringIpFormat() +
							"\nBroadcastadresse: \t" + broadcastAddress.toStringIpFormat() +
							"\nCIDR als String: \t" + cidrToStringIpFormat(String.valueOf(networkAddress.getCidr()))
				);
	}
	
	/**
	 * generiert eine CIDR-Klasse zwischen 16 und 31
	 * @return die CIDR-Klasse als Integer
	 */
	private int generateCidr() {
		int temp = rand.nextInt(24) + rand.nextInt(24);
		if(16<temp && temp <31) {
			return temp;
		}
		return generateCidr();
	}
	
	
	/**
	 * überprüft ob die eingegebene Host-Adresse richtig ist
	 * @param inputHostAddress
	 * @return wahrheitswert ob richtig oder falsch
	 */
	public boolean validateHostAddress(int[] inputHostAddress) {
		//die ersten beiden Zahlen müssen in jedem Falle gleich sein
		for(int i=0; i<2; i++) {
			if(networkAddress.getValue(i) != inputHostAddress[i]) {
				return false;
			}
		}
		//bei CIDR >= 24 gehe in diese Abfrage, ansonsten:
		if(networkAddress.getCidr() >= 24) {
			if(networkAddress.getValue(2) != inputHostAddress[2]) {return false;}
	
			if(inputHostAddress[3] >= firstAddress.getValue(3) && inputHostAddress[3] <= lastAddress.getValue(3)){
				return true;
			}
			else {
				return false;
			}
		}
		
		else {
			//stelle 3 ist bei der Hostadresse mit Stelle 3 der Netzwerkadresse gleich
			if(inputHostAddress[2] == firstAddress.getValue(2)) {	//Stelle 3 der Hostadresse ist gleich der Stelle 3 der Netzwerkadresse
				if(inputHostAddress[3] >= firstAddress.getValue(3) && inputHostAddress[3] <256) {	//Stelle 4 muss also größer oder gleich der 4. Stelle der ersten Adresse sein sein und kleiner als 256
					return true;
				}
			
			}
			else if	(inputHostAddress[2] == lastAddress.getValue(2)) {	//Stelle 3 der Hostadresse ist = Stelle 3 der letzmöglichen Adresse
				if(inputHostAddress[3] <= lastAddress.getValue(3) && inputHostAddress[3] >= 0) {	//dementsprechend muss 0 <= Stelle 4 < 255
					return true;
				}
			}
			else if(inputHostAddress[2] > firstAddress.getValue(2) && inputHostAddress[2] < lastAddress.getValue(2)) {	//Stelle 3 der Hostadresse ist zwischen der ersten bzw. letzen Zahl der 3. Stelle der Netzwerkadresse
				if(inputHostAddress[3] >=0 && inputHostAddress[3] < 256) {		//dementsprechend muss diese Adresse nur zwischen inklusive 0 und 255 sein
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Formatiert einen String zu einem Integer-Array
	 * @param s der String der Formatiert werden soll
	 * @param randix gibt an in welchem Zahlenformat der String vorliegt
	 * @return Integer-Array 
	 */
	public int[] toIntIpFormat (String s, int randix) {	
		String[] stringArr =  s.split("\\.");					//Da "." ein spezieller Ausdruck ist muss man hier die Escape-Funktion nutzen
		 if(stringArr.length >= 4){
			 //hier eine Exception werfen
			 }
		 int[] intArr = new int[stringArr.length];				//Initialisiere Array 
			 for(int i = 0; i < intArr.length; i++) {
				 intArr[i] = Integer.parseInt(stringArr[i], randix);	//randix gibt an in welches Representation das Ausgangsformat ist
				 /** Anmerkung: ParseInt gibt den primitiven Datentypen zurück
				  * 			ValueOf ein Objekt von typ Integer()
				  * 			An dieser Stelle können außerdem eine Vielzahl von Exceptions entstehen
				  * 			die es abzufangen gilt
				  */
				 if(intArr[i] < 0 || intArr[i] > 255) {
					//hier eine neue Exception werfen
				 }	
			 } 
		 //System.out.println(stringArr.length + "\t" + Arrays.toString(stringArr)); debugprints
		 return intArr;
	}
	
	/**
	 * formatiert die dezimale Represäntation der CIDR-Klasse zur Binärrepresentation
	 * @param s CIDR-Klasse dezimal
	 * @return CIDR-Klasse Binär in IP-Format
	 */
	private static String cidrToStringIpFormat(String s) {
		int value = Integer.parseInt(s); // Hier k�nnen schon Exceptions entstehen
		StringBuilder tempSB = new StringBuilder();
		
		for(int i = 1; i<= 32; i++) {
			if(i<= value) {
				tempSB.append("1");
			}
			else {
				tempSB.append("0");
			}
		}
//		System.out.println(tempSB.toString()); //debugprint
		for(int i = 1; i<= 3; i++) {
			tempSB.insert(i*8+(i-1), ".");
		}
//		System.out.println(tempSB.toString());	//debugprint
		return tempSB.toString();
	}
	
	private boolean[] generateExerciseType() {
		boolean[] boolArr = new boolean[8];
		for(int i = 0; i<8; i++) {
			boolArr[i] = false;
		}
		int[] addressComponent = new int[] {1,2,4,5,6}; //diese Array stellt die M�glichkeiten des ersten Sichtbaren Teils der Aufgabe dar
		int[] cidrComponent = new int[] {3,7,8}; //diese Array stellt die M�glichkeiten des ersten Sichtbaren Teils der Aufgabe dar

		boolArr[addressComponent[rand.nextInt(5)]-1] = true;
		boolArr[cidrComponent[rand.nextInt(3)]-1] = true;
		return boolArr;
	}
	
	/**
	 * Lässt die jeweiligen Werte als String abrufen
	 * @param value von 0-7
	 * @return gibt den jeweiligen Wert in IP-Format als String zurück
	 */
	public String getStringFormatByNumber(int value) {
		switch(value) {
			case 0: return this.networkAddress.toStringIpFormat();
			case 1: return this.generateRandomHostAddress().toStringIpFormat(); //Hier Hostadresse
			case 2: return this.subnetmask.toStringIpFormat();
			case 3: return this.firstAddress.toStringIpFormat();
			case 4: return this.lastAddress.toStringIpFormat();
			case 5: return this.broadcastAddress.toStringIpFormat();
			case 6: return String.valueOf(this.networkAddress.getCidr());
			case 7: return cidrToStringIpFormat(String.valueOf(this.networkAddress.getCidr()));
			default: return null;
		}
	}
	
	/**
	 * Dient dazu die jeweiligen Integer-Arrays zu den Adressen abzufragen
	 * @param value kann 0 und 2-7 annehmen
	 * @return gibt das Int-Array zur zugehörigen IP zurück
	 */
	public int[] getValuesByNumber(int value) {
		switch(value) {
			case 0: return this.networkAddress.getValues();
			//case 1: return this.generateRandomHostAddress().getValues(); //Hier Hostadresse
			case 2: return this.subnetmask.getValues();
			case 3: return this.firstAddress.getValues();
			case 4: return this.lastAddress.getValues();
			case 5: return this.broadcastAddress.getValues();
			case 6: return new int[] {this.networkAddress.getCidr()};
			case 7: return toIntIpFormat(cidrToStringIpFormat(String.valueOf(this.networkAddress.getCidr())),10);
			default: return null;
		}
	}
	
	/**
	 * generiert eine zufällige Nummer zwischen den beidne Grenzen, beachte, das die Grenzen inklusiv sind
	 * @param uG untere Grenze
	 * @param oG obere Grenze
	 * @return
	 */
	private int generateRandomNumber(int uG, int oG) {	//berechnet eine ganze Zahl zwischen den Inklusiven Grenzen
		return uG + ((int)rand.nextInt(oG-uG+1));
	}
	
	/**
	 * vergleicht zwei Integer-Arrays miteinander
	 * @param original der originalparameter
	 * @param input die Eingabe die Verglichen werden soll
	 * @return den Wahrheitswert, ob die Arrays übereinstimmen
	 */
	private boolean compareValues(int[] original, int[] input) {
		if(original.length == input.length) {
			for(int i=0; i < input.length; i++) {
				if(original[i] != input[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * generiert eine Zufällige HostAdresse innerhalb des jeweiligen Hostbereichs
	 * @return IPAddress
	 */
	public IPAddress generateRandomHostAddress() {
		int[] tempArr = new int[4];
		
		tempArr[0] = this.networkAddress.getValue(0);
		tempArr[1] = this.networkAddress.getValue(1);
		
		if(networkAddress.getCidr() >= 24) {
			tempArr[2] = this.networkAddress.getValue(2);
			tempArr[3] = generateRandomNumber(this.firstAddress.getValue(3), this.lastAddress.getValue(3));
		}
		else {
			//Stelle 3 auf eine Zahl zwischen Stelle 3 der ersten Adress und Stelle 3 der letzen Addresse
			tempArr[2]	= generateRandomNumber(firstAddress.getValue(2), lastAddress.getValue(2));
			
			if(tempArr[2] == firstAddress.getValue(2)) {
				tempArr[3] = generateRandomNumber(firstAddress.getValue(3), 255);
			
			}
			else if	(tempArr[2] == lastAddress.getValue(2)) {
				tempArr[3] = generateRandomNumber(0, lastAddress.getValue(3));
					
				}
			else if(tempArr[2] > firstAddress.getValue(2) && tempArr[2] < lastAddress.getValue(2)) {
				tempArr[3] = generateRandomNumber(0,255);
				}
		}
		return new IPAddress(tempArr);
	}
	
	/**
	 * überprüft die Eingaben
	 * @param inputs Alle Eingaben als String-Array
	 * @return Boolean-Array, welches die Richtigkeit der jeweiligen Adresse angibt
	 */
	public boolean[] validateInputs(String[] inputs){
		int[][] tempIntArr = new int[inputs.length][];
		boolean[] results = new boolean[8]; 
		for(int i=0; i<tempIntArr.length; i++) {
			tempIntArr[i] = toIntIpFormat(inputs[i], 10);
		}
		
		for(int i = 0; i < results.length; i++) {
			if(!exerciseType[i]) {
				if(i==1) {
					results[i] = validateHostAddress(tempIntArr[i]);
				}
				else {
					results[i] = compareValues(getValuesByNumber(i), tempIntArr[i]);
				}
			}
		}
		return results;
	
	}
	
	/**
	 * gibt den ExerciseTypen zurück, sodass geschaut werden kann, ob die Felder auszufüllen waren
	 * @return Boolean-Array 
	 */
	public boolean[] getExerciseType() {
		return exerciseType;
	}
}

