package de.nrw.hspv.exercises;
import java.util.Random;

public class IPNetworkAddress extends IPAddress{
	//Author: Jannik
	private int hostbereich;
	private int cidr;
	private int diff;
	private Random rand = new Random();
	
	//Konstruktor um Netzwerkadresse zu berrechnen
		IPNetworkAddress(int cidr) {
			this.cidr = cidr;
			this.hostbereich = 32-this.cidr;
			this.diff = Math.abs(24-this.cidr);
			this.values = generateNetworkAddress(cidr);
		}
		
		public IPNetworkAddress(int cidr, int[] values) {
			this.cidr = cidr;
			this.hostbereich = 32-this.cidr;
			this.diff = Math.abs(24-this.cidr);
			this.values = values;
		}
		
		private int[] generateNetworkAddress(int cidr) {
			/**
			 * Idee hinter dieser Methode:
			 * Die cidr Adressen werden zwischen 16 und 30 generiert.
			 * Abhängig davon muss lediglich der 3. oder 4. byte der IPv4-Addresse 
			 * gesondert angepasst werden um eine Netzwerkadresse zu erhalten.
			 * 
			 */
			
				//diff steht für differenz
			int[] tempArr  = new int[4];
			
			//Prüfung ob 3. oder 4. Byte betroffen
			if(this.cidr < 24){
				int moegl = (int) Math.pow(2, 8-this.diff);										// Berrechnung der Anzahl an möglichen Zahlen in Abhängigkeit der CIDR-Klasse 
				tempArr[2] = ((int) Math.pow(2, this.diff))* rand.nextInt(moegl);		// Auswahl einer möglichen Zahl
				if(rand.nextBoolean()) {tempArr[0] = 10; tempArr[1] = 10; tempArr[3] = 0;}	// zu 50% kommt diese Art der Formatierung vor
				else {
					for (int i = 0; i<2; i++) {
						tempArr[i] = rand.nextInt(255)+1;
					}
				}
			}
			
			else if(this.cidr > 23) {	//24 und größer
				if(this.cidr == 24) {
					//Bei einer CIDR-Klasse von 24 lässt sich der letzte Byte auf null setzten
					tempArr[3] = 0;
				}
				else {
					int moegl = (int) Math.pow(2, this.diff);		// Berrechnung der Anzahl an möglichen Zahlen in Abhängigkeit der CIDR-Klasse 
					tempArr[3] = ((int) Math.pow(2, (8-this.diff)))*rand.nextInt(moegl);		// Auswahl einer möglichen Zahl
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
			return tempArr;
		}
		//Getter Methoden
		public int getCidr() {
			return this.cidr;
		}
		
		//Hifsmethoden
		private int[] copyArray(int[] values) {
			int[] copyArr = new int[values.length];	//erzeuge Temporäres array um nicht "auf dem Objekt" zu arbeiten
			for (int i = 0; i<values.length; i++) {
				copyArr[i] = values[i];	//kopiere
			}
			return copyArr;
		}

		
		//create Methoden
		public IPAddress createBroadcastAddress() { //wird auf der Netzwerkadresse aufgerufen
			int[] copyArr = copyArray(this.values);
			if(this.hostbereich == 8) {
				copyArr[3] = 255;
			}
			else if(this.hostbereich<8) {
				copyArr[3] += Math.pow(2, this.hostbereich)-1;
			}
			else if(this.hostbereich>8) {
			
				copyArr[2] += Math.pow(2, this.hostbereich-8)-1;
				copyArr[3] = 255;
			}
			
			return new IPAddress(copyArr);
		}
		
		public IPAddress createFirstAddress() { //wird auf der Netzwerkadresse aufgerufen
			int[] copyArr = copyArray(this.values);
			copyArr[3] += 1;
			
			return new IPAddress(copyArr);
		}
		
		public IPAddress createLastAddress() {
			IPAddress tempIP = this.createBroadcastAddress();
			tempIP.values[3] -=1;
			return tempIP;
		}
		
		public IPAddress createSubnetMask(){
			//initialisisere Array und setze die ersten 2 Slots auf 255
			int[] tempArr = new int[4];
			tempArr[0] = 255;
			tempArr[1] = 255;
			
			if(this.cidr <24) {
				int sum = 0;
				for(int i =7; i >= diff; i--) {
					sum += (int) Math.pow(2,i);
				}
				tempArr[2] = sum;
				tempArr[3] = 0;
			}
			else if(this.cidr >= 24) {
				tempArr[2] = 255;
				int sum = 0;
				for(int i = 1; i <= diff; i++) {
					sum += (int) Math.pow(2,8-i);
				}
				tempArr[3] = sum;
			}
			
			return new IPAddress(tempArr);
		}
		
		

}
