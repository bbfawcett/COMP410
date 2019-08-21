package A5_Dijkstra;

public class DiGraphPlayground {

	public static void main (String[] args) {

		// thorough testing is your responsibility
		//
		// you may wish to create methods like 
		//    -- print
		//    -- sort
		//    -- random fill
		//    -- etc.
		// in order to convince yourself your code is producing
		// the correct behavior
		exTest();
	}

	public static void exTest(){
		DiGraph d = new DiGraph();
		d.addNode(1,"f");
		d.addNode(3,"s");
		d.addNode(7,"t");
		System.out.println(d.addEdge(0, "f", "s", 0, null));
		System.out.println(d.addEdge(1, "f", "s", 0, null));
	}
}