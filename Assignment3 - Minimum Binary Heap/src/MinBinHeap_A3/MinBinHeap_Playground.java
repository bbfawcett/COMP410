package MinBinHeap_A3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MinBinHeap_Playground {
	public static void main(String[] args){   
		//Add more tests as methods and call them here!!
		TestBuild();
	}

	public static void TestBuild(){ 
		// constructs a new minbinheap, constructs an array of EntryPair, 
		// passes it into build function. Then print collection and heap.
		ArrayList<EntryPair> x = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			x.add(new EntryPair(getSaltString(), randInt(0, 300000000)));
		}
		EntryPair[] collection = x.toArray(new EntryPair[x.size()]);
		MinBinHeap minBinHeap = new MinBinHeap();
		Collections.shuffle(Arrays.asList(collection));
		minBinHeap.build(collection);
		printHeap(minBinHeap.getHeap(), minBinHeap.size());
	}

	public static void printHeapCollection(EntryPair[] e) { 
		//this will print the entirety of an array of entry pairs you will pass 
		//to your build function.
		System.out.println("Printing Collection to pass in to build function:");
		for(int i=0;i < e.length;i++){
			System.out.print("("+e[i].value+","+e[i].priority+")\t");
		}
		System.out.print("\n");
	}

	public static void printHeap(EntryPair[] e,int len) { 
		//pass in mbh.getHeap(),mbh.size()... this method skips over unused 0th index....
		System.out.println("Printing Heap");
		for(int i=1;i < len+1;i++){
			if (Math.log((double)i)/Math.log(2.0) % 1 == 0) {
				System.out.println();
			}
			System.out.print(e[i].priority + "      ");
		}
		System.out.print("\n");
	}
	
	public static String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 3) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}
	
	public static int randInt(int min, int max) {

	    // NOTE: This will (intentionally) not run as written so that folks
	    // copy-pasting have to think about how to initialize their
	    // Random instance.  Initialization of the Random instance is outside
	    // the main scope of the question, but some decent options are to have
	    // a field that is initialized once and then re-used as needed or to
	    // use ThreadLocalRandom (if using at least Java 1.7).
	    // 
	    // In particular, do NOT do 'Random rand = new Random()' here or you
	    // will get not very good / not very random results.

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = min + (int)(Math.random() * ((max - min) + 1));

	    return randomNum;
	}
}