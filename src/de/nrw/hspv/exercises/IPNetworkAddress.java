package de.nrw.hspv.exercises;
import java.util.Random;
import java.util.logging.Level;

import de.nrw.hspv.ui.App;

/**
 * Diese Klasse ist eine Subklasse der IPAddress
 * Sie dient dazu die Netwerkadresse zu generieren und von ihr aus die anderen Adressen zu berechnen
 * @author Jannik
 *
 */
public class IPNetworkAddress extends IPAddress{
	private int hostbereich;
	private int cidr;
	private int diff;
	private Random rand = new Random();
	
		/**
		 * erzeugt eine Netzwerkadresse unter vorgabe der CIDR-Klasse
		 * @param cidr
		 */
		IPNetworkAddress(int cidr) {
			this.cidr = cidr;
			this.hostbereich = 32-this.cidr;
			this.diff = Math.abs(24-this.cidr);
			this.values = generateNetworkAddress(cidr);
			App.logger.log(Level.INFO, "IPNetworAddress mit vorgegebener CIDR erzeugt");
		}
		
		/**
		 * Erzeugt eine Netzwerkadresse unter Vorgabe der Adresswerte und der CIDR-Klasse wurde 
		 * Der Haupzweck ist das Testen der Klasse und der einzenen create Methoden
		 * @param cidr CIDR-Klasse
		 * @param values Integer-Array mit den Werten der IP
		 */
		public IPNetworkAddress(int cidr, int[] values) {
			this.cidr = cidr;
			this.hostbereich = 32-this.cidr;
			this.diff = Math.abs(24-this.cidr);
			this.values = values;
			App.logger.log(Level.INFO, "IPNetworAddress mit vorgegebener CIDR und vorgegebenen Werten erzeugt");
		}
		
		/**
		 * Idee hinter dieser Methode:
		 * Die cidr Adressen werden zwischen 16 und 30 generiert.
		 * Abhaengig davon muss lediglich der 3. oder 4. byte der IPv4-Addresse 
		 * gesondert angepasst werden um eine Netzwerkadresse zu erhalten.
		 * @param cidr
		 * @return Integer-Array mit den Werten einer zufälligen Netzwerkadresse
		 */
		public int[] generateNetworkAddress(int cidr) {
			//diff steht fuer differenz
			int[] tempArr  = new int[4];
			
			//Pruefung ob 3. oder 4. Byte betroffen
			if(this.cidr < 24){
				int moegl = (int) Math.pow(2, 8-this.diff);										// Berrechnung der Anzahl an moeglichen Zahlen in Abhaengigkeit der CIDR-Klasse 
				tempArr[2] = ((int) Math.pow(2, this.diff))* rand.nextInt(moegl);		// Auswahl einer moeglichen Zahl
				if(rand.nextBoolean()) {tempArr[0] = 10; tempArr[1] = 10; tempArr[3] = 0;}	// zu 50% kommt diese Art der Formatierung vor
				else {
					for (int i = 0; i<2; i++) {
						tempArr[i] = rand.nextInt(255)+1;
					}
				}
			}
			
			else if(this.cidr > 23) {	//24 und groesser
				if(this.cidr == 24) {
					//Bei einer CIDR-Klasse von 24 loesst sich der letzte Byte auf null setzten
					tempArr[3] = 0;
				}
				else {
					int moegl = (int) Math.pow(2, this.diff);		// Berrechnung der Anzahl an moeglichen Zahlen in Abhaengigkeit der CIDR-Klasse 
					tempArr[3] = ((int) Math.pow(2, (8-this.diff)))*rand.nextInt(moegl);		// Auswahl einer moeglichen Zahl
				}
				
				//um  typische IP-Adressen in den Aufgaben zu haben
				if(rand.nextBoolean()) {tempArr[0] = 192; tempArr[1] = 168; tempArr[2] = 178;} 
				else if(rand.nextBoolean()) {tempArr[0] = 10; tempArr[1] = 10; tempArr[2] = rand.nextInt(256);}
				
				//generiere eigene IP-Adresse
				else {
					for (int i = 0; i<3; i++) {
						tempArr[i] = rand.nextInt(255)+1;
					}
				}			
			}
			App.logger.log(Level.INFO, "Netzwerkaddresse generiert");
			return tempArr;
		}
		
		/**
		 * Getter Methode für die CIDR-Klasse
		 * @return CIDR-Klasse als Integer
		 */
		public int getCidr() {
			return this.cidr;
		}
		
		/**
		 * Hilfsmethode um eine Integerarray zu koppieren um nicht auf dem Objekt zu arbeiten
		 * @param values zu kopierendes Integer-Array
		 * @return Kopie des Integer-Arrays
		 */
		private int[] copyArray(int[] values) {
			int[] copyArr = new int[values.length];	//erzeuge Temporaeres array um nicht "auf dem Objekt" zu arbeiten
			for (int i = 0; i<values.length; i++) {
				copyArr[i] = values[i];	//kopiere
			}
			return copyArr;
		}

		//create Methoden
		/**
		 * Erstellt auf basis der Netzwerkadresse die zugehoerige Broadcastadresse
		 * @return IPAddress welche die Broadcastadresse represaentiert
		 */
		public IPAddress createBroadcastAddress() { //wird auf der Netzwerkadresse aufgerufen
			int[] copyArr = copyArray(this.values);
			if(this.hostbereich == 8) {
				copyArr[3] = 255;
			}
			else if(this.hostbereich<8) {
				//ist der Hostbereich kleiner als 8
				copyArr[3] += Math.pow(2, this.hostbereich)-1;
			}
			else if(this.hostbereich>8) {
				//ist der Hostbereich größer als 8
				copyArr[2] += Math.pow(2, this.hostbereich-8)-1;
				copyArr[3] = 255;
			}
			//erzeuge neues IPAddress-Objekt
			App.logger.log(Level.INFO, "Broadcastadresse erzeugt");
			return new IPAddress(copyArr);
		}
		/**
		 * Erstellt auf Basis der Netzwerkadresse die zugehoerige erste Adresse
		 * @return erste IP-Adresse
		 */
		public IPAddress createFirstAddress() { //wird auf der Netzwerkadresse aufgerufen
			int[] copyArr = copyArray(this.values);
			copyArr[3] += 1;	//den letzten Wert der Netzwerkadresse+1
			App.logger.log(Level.INFO, "Erste Adresse erzeugt");
			return new IPAddress(copyArr);
		}
		/**
		 * Erzeugt mithilfe der BroadcastAdresse die letztmoegliche Adresse
		 * @return IPAddress mit den Werten der letzen moeglichen Adresse
		 */
		public IPAddress createLastAddress() {
			IPAddress tempIP = this.createBroadcastAddress();
			tempIP.values[3] -=1;
			App.logger.log(Level.INFO, "Letze Adresse erzeugt");
			return tempIP;
		}
		
		/**
		 * Erzeug die Subnetzmaske als IPAdress
		 * @return IPAdress mit den Werten der Subnetzmaske
		 */
		public IPAddress createSubnetMask(){ //nutze die CIDR-Klasse
			//initialisisere Array und setze die ersten 2 Slots auf 255
			int[] tempArr = new int[4];
			tempArr[0] = 255;
			tempArr[1] = 255;
			
			if(this.cidr <24) {
				//ist die CIDR kleiner als 24
				int sum = 0;
				for(int i =7; i >= diff; i--) {
					sum += (int) Math.pow(2,i);
				}
				tempArr[2] = sum;
				tempArr[3] = 0;
			}
			else if(this.cidr >= 24) {
				//ist die CIDR groesser oder gleich 24
				tempArr[2] = 255;
				int sum = 0;
				for(int i = 1; i <= diff; i++) {
					sum += (int) Math.pow(2,8-i);
				}
				tempArr[3] = sum;
			}
			//gebe neue IPAddress zurück
			App.logger.log(Level.INFO, "Subnetzmaske erzeugt");
			return new IPAddress(tempArr);
		}
}
