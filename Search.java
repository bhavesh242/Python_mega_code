import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Search {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\bhave\\Desktop\\PathN-7.mb"));
		br.readLine();
		String st;
		String stb;
		Tile t;
		Scanner sc;
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Tile> allTiles = new ArrayList<Tile>();
		while ((st = br.readLine()) != null && !st.equalsIgnoreCase("</Marble>")) {
			stb = st.substring(5);
			sc = new Scanner(stb).useDelimiter("[^0-9]+");
			while (sc.hasNext()) {
				a.add(sc.nextInt());
			}
			t = new Tile(a.get(a.size() - 4), a.get(a.size() - 3), a.get(a.size() - 2), a.get(a.size() - 1));
			allTiles.add(t);

		}
		GlobeCube g = new GlobeCube(allTiles);

		/*
		 * GlobeSolverBFS bfs = new GlobeSolverBFS(); ArrayList<String> solvedList = new
		 * ArrayList<String>(bfs.BFS_Solve(g));
		 */
		AStarSolver astar = new AStarSolver();
		ArrayList<String> solvedList = new ArrayList<String>(astar.aStarSolve(g));
		System.out.println("\nSolution Steps:\n");
		while (!solvedList.isEmpty()) {
			String action = solvedList.remove(0);
			if (action.equalsIgnoreCase("inc0180")) {
				System.out.println("Increment 0-180 longitude by 30");
			} else if (action.equalsIgnoreCase("dec0180")) {
				System.out.println("Decrement 0-180 longitude by 30");
			} else if (action.equalsIgnoreCase("inc90270")) {
				System.out.println("Increment 90-270 longitude by 30");
			} else if (action.equalsIgnoreCase("dec90270")) {
				System.out.println("Deccrement 90-270 longitude by 30");
			} else if (action.equalsIgnoreCase("incEquator")) {
				System.out.println("Increment Equator by 30");
			} else if (action.equalsIgnoreCase("decEquator")) {
				System.out.println("Decrement Equator by 30");
			}
		}

		br.close();
	}
}

class Tile {
	int lat, lon;
	int elat, elon;

	Tile(int lat, int lon, int elat, int elon) {
		this.elat = elat;
		this.elon = elon;
		this.lat = lat;
		this.lon = lon;
	}

	public void printTile() {
		System.out.println("Latitude : " + lat + " Longitude : " + lon + "\nTarget Latitude : " + elat+ " Target Longitude : " + elon);
	}
}

class GlobeCube {
	ArrayList<Tile> allTiles;
	public GlobeCube(ArrayList<Tile> l) {
		this.allTiles = l;
	}

	public void printGloble() {
		Tile cur;
		for (int i = 0; i < allTiles.size(); i++) {
			cur = allTiles.get(i);
			System.out.println(cur.lat + " " + cur.lon);
		}

	}
	public void inc0180() {
		Tile cur;
		for (int i = 0; i < allTiles.size(); i++) {
			cur = allTiles.get(i);
			if (cur.lon == 0) {
				if (cur.lat == 150) {
					cur.lat = 180;
					cur.lon = 180;
				} else {
					cur.lat = cur.lat + 30;
				}
			} else if (cur.lon == 180) {
				if (cur.lat == 30) {
					cur.lat = 0;
					cur.lon = 0;
				} else {
					cur.lat = cur.lat - 30;
				}
			}
		}
	}

	public void dec0180() {
		Tile cur;
		for (int i = 0; i < allTiles.size(); i++) {
			cur = allTiles.get(i);
			if (cur.lon == 0) {
				if (cur.lat == 0) {
					cur.lat = 30;
					cur.lon = 180;
				} else {
					cur.lat = cur.lat - 30;
				}
			} else if (cur.lon == 180) {
				if (cur.lat == 180) {
					cur.lat = 150;
					cur.lon = 0;
				} else {
					cur.lat = cur.lat + 30;
				}
			}
		}
	}

	public void incEquator() {
		Tile cur;
		for (int i = 0; i < allTiles.size(); i++) {
			cur = allTiles.get(i);
			if (cur.lat == 90) {
				cur.lon = (cur.lon + 30) % 360;
			}
		}
	}

	public void decEquator() {
		Tile cur;
		for (int i = 0; i < allTiles.size(); i++) {
			cur = allTiles.get(i);
			if (cur.lat == 90) {
				if (cur.lon == 0) {
					cur.lon = 330;
				} else {
					cur.lon = cur.lon - 30;
				}
			}
		}
	}

	public void inc90270() {
		Tile cur;
		for (int i = 0; i < allTiles.size(); i++) {
			cur = allTiles.get(i);
			if (cur.lat == 0 && cur.lon == 0) {
				cur.lat = 30;
				cur.lon = 90;
			} else if (cur.lat == 180 && cur.lon == 180) {
				cur.lat = 150;
				cur.lon = 270;
			} else if (cur.lat == 30 && cur.lon == 270) {
				cur.lat = 0;
				cur.lon = 0;
			} else if (cur.lat == 150 && cur.lon == 90) {
				cur.lat = 180;
				cur.lon = 180;

			} else {
				if (cur.lon == 270) {
					cur.lat = cur.lat - 30;
				} else if (cur.lon == 90) {
					cur.lat = cur.lat + 30;
				}
			}
		}
	}

	public void dec90270() {
		Tile cur;
		for (int i = 0; i < allTiles.size(); i++) {

			cur = allTiles.get(i);
			if (cur.lat == 0 && cur.lon == 0) {
				cur.lat = 30;
				cur.lon = 270;
			} else if (cur.lat == 180 && cur.lon == 180) {
				cur.lat = 150;
				cur.lon = 90;
			} else if (cur.lat == 150 && cur.lon == 270) {
				cur.lat = 180;
				cur.lon = 180;
			} else if (cur.lat == 30 && cur.lon == 90) {
				cur.lat = 0;
				cur.lon = 0;

			} else {
				if (cur.lon == 270) {
					cur.lat = cur.lat + 30;
				} else if (cur.lon == 90) {
					cur.lat = cur.lat - 30;
				}
			}
		}
	}

	public boolean isGlobeSolved() {
		Tile cur;
		boolean solved = true;
		for (int i = 0; i < allTiles.size(); i++) {
			cur = allTiles.get(i);
			if (cur.lat != cur.elat || cur.elon != cur.lon) {
				solved = false;
				break;
			}
		}
		return solved;
	}

	public int getHashState() {
		StringBuffer allT = new StringBuffer("");
		for (int i = 0; i < allTiles.size(); i++) {
			allT.append(allTiles.get(i).lat).append(allTiles.get(i).lon);
		}

		return allT.toString().hashCode();
	}
}

class BFSGraphNode {
	BFSGraphNode parent;
	String action;

	public BFSGraphNode(BFSGraphNode parent, String action) {
		this.parent = parent;
		this.action = action;
	}
}

class GlobeSolverBFS {
	int exploredStatesNumber;
	ArrayList<Integer> exploredStates = new ArrayList<Integer>();
	String moves[] = { "inc0180", "dec0180", "incEquator", "decEquator", "inc90270", "dec90270" };

	public Stack<String> BFS_Solve(GlobeCube g) {
		if (g.isGlobeSolved())
			return null;
		exploredStatesNumber = 1;
		BFSGraphNode parent = new BFSGraphNode(null, null);
		ArrayList<BFSGraphNode> frontier = new ArrayList<BFSGraphNode>();
		frontier.add(parent);
		BFSGraphNode curNode;
		BFSGraphNode b;
		String nowMove;
		Stack<String> movesToCurent = new Stack<String>();
		while (true) {

			curNode = frontier.remove(0);
			exploredStatesNumber = exploredStatesNumber + 1;

			b = curNode;

			while (b.parent != null && b.action != null) {
				movesToCurent.push(b.action);
				b = b.parent;
			}
			Stack<String> movesToSolution = new Stack<String>();
			while (!movesToCurent.isEmpty()) {
				nowMove = movesToCurent.pop();
				movesToSolution.push(nowMove);
				if (nowMove.equalsIgnoreCase("inc0180")) {
					g.inc0180();
				} else if (nowMove.equalsIgnoreCase("dec0180")) {
					g.dec0180();

				} else if (nowMove.equalsIgnoreCase("incEquator")) {
					g.incEquator();
				} else if (nowMove.equalsIgnoreCase("decEquator")) {

					g.decEquator();
				} else if (nowMove.equalsIgnoreCase("inc90270")) {

					g.inc90270();

				} else if (nowMove.equalsIgnoreCase("dec90270")) {

					g.dec90270();
				}
			}
			exploredStates.add(g.getHashState());
			String negateStep = "";
			if (curNode.action == null) {
				negateStep = "";
			} else if (curNode.action.equalsIgnoreCase("inc0180")) {
				negateStep = "dec0180";
			} else if (curNode.action.equalsIgnoreCase("dec0180")) {
				negateStep = "inc0180";
			} else if (curNode.action.equalsIgnoreCase("inc90270")) {
				negateStep = "dec90270";
			} else if (curNode.action.equalsIgnoreCase("dec90270")) {
				negateStep = "inc90270";
			} else if (curNode.action.equalsIgnoreCase("incEquator")) {
				negateStep = "decEquator";
			} else if (curNode.action.equalsIgnoreCase("decEquator")) {
				negateStep = "incEquator";
			}
			for (int i = 0; i < 6; i++) {
				if (!moves[i].equalsIgnoreCase(negateStep)) {
					if (moves[i].equalsIgnoreCase("inc0180")) {
						g.inc0180();
						if (!exploredStates.contains(g.getHashState())) {
							if (g.isGlobeSolved()) {
								movesToSolution.push(moves[i]);
								return movesToSolution;
							}
						}
						g.dec0180();
					} else if (moves[i].equalsIgnoreCase("dec0180")) {
						g.dec0180();
						if (!exploredStates.contains(g.getHashState())) {
							if (g.isGlobeSolved()) {
								movesToSolution.push(moves[i]);
								return movesToSolution;
							}
						}
						g.inc0180();
					} else if (moves[i].equalsIgnoreCase("inc90270")) {
						g.inc90270();
						if (!exploredStates.contains(g.getHashState())) {
							if (g.isGlobeSolved()) {
								movesToSolution.push(moves[i]);
								return movesToSolution;
							}
						}
						g.dec90270();
					} else if (moves[i].equalsIgnoreCase("dec90270")) {
						g.dec90270();
						if (!exploredStates.contains(g.getHashState())) {
							if (g.isGlobeSolved()) {
								movesToSolution.push(moves[i]);
								return movesToSolution;
							}
						}
						g.inc90270();
					} else if (moves[i].equalsIgnoreCase("incEquator")) {
						g.incEquator();
						if (!exploredStates.contains(g.getHashState())) {
							if (g.isGlobeSolved()) {
								movesToSolution.push(moves[i]);
								return movesToSolution;
							}
						}
						g.decEquator();
					} else if (moves[i].equalsIgnoreCase("decEquator")) {
						g.decEquator();
						if (!exploredStates.contains(g.getHashState())) {
							if (g.isGlobeSolved()) {
								movesToSolution.push(moves[i]);
								return movesToSolution;
							}
						}
						g.incEquator();
					}

					frontier.add(new BFSGraphNode(curNode, moves[i]));
				}
			}
			String revMove;
			while (!movesToSolution.isEmpty()) {
				revMove = movesToSolution.pop();
				if (revMove.equalsIgnoreCase("inc0180")) {
					g.dec0180();
				} else if (revMove.equalsIgnoreCase("dec0180")) {
					g.inc0180();
				} else if (revMove.equalsIgnoreCase("inc90270")) {
					g.dec90270();
				} else if (revMove.equalsIgnoreCase("dec90270")) {
					g.inc90270();
				} else if (revMove.equalsIgnoreCase("incEquator")) {
					g.decEquator();
				} else if (revMove.equalsIgnoreCase("decEquator")) {
					g.incEquator();
				}
			}
			movesToSolution.clear();
		}
	}
}

class AStarNode {
	AStarNode parent;
	String action;
	int f_n;

	public AStarNode(AStarNode parent, String action, int f_n) {
		// TODO Auto-generated constructor stub
		this.parent = parent;
		this.action = action;
		this.f_n = f_n;
	}
}

class AStarNodeComparator implements Comparator<AStarNode> {
	@Override
	public int compare(AStarNode a1, AStarNode a2) {
		if (a1.f_n < a2.f_n)
			return -1;
		else if (a1.f_n > a2.f_n)
			return 1;
		return 0;
	}
}

class AStarSolver {
	int exploredStatesNumber;
	ArrayList<Integer> exploredStates = new ArrayList<Integer>();
	String moves[] = { "inc0180", "dec0180", "incEquator", "decEquator", "inc90270", "dec90270" };

	public int heuristic_function(GlobeCube g) {
		return 0;
	}

	public Stack<String> aStarSolve(GlobeCube g) {
		if (g.isGlobeSolved())
			return null;
		exploredStatesNumber = 1;
		int h_n = 0;
		int turnMoves = 0;
		AStarNode parent = new AStarNode(null, null, 0);
		PriorityQueue<AStarNode> frontier = new PriorityQueue<AStarNode>(new AStarNodeComparator());
		
		frontier.add(new AStarNode(null, null, heuristic_function(g)));
		AStarNode curNode;

		AStarNode b;
		String nowMove;
		Stack<String> movesToCurent = new Stack<String>();
		while (true) {

			curNode = frontier.poll();
			exploredStatesNumber = exploredStatesNumber + 1;
			b = curNode;
			turnMoves = 0;
			while (b.parent != null && b.action != null) {
				movesToCurent.push(b.action);
				b = b.parent;
				turnMoves = turnMoves + 1;
			}

			Stack<String> movesToSolution = new Stack<String>();
			while (!movesToCurent.isEmpty()) {
				nowMove = movesToCurent.pop();
				movesToSolution.push(nowMove);
				if (nowMove.equalsIgnoreCase("inc0180")) {
					g.inc0180();
				} else if (nowMove.equalsIgnoreCase("dec0180")) {
					g.dec0180();

				} else if (nowMove.equalsIgnoreCase("incEquator")) {
					g.incEquator();
				} else if (nowMove.equalsIgnoreCase("decEquator")) {

					g.decEquator();
				} else if (nowMove.equalsIgnoreCase("inc90270")) {

					g.inc90270();

				} else if (nowMove.equalsIgnoreCase("dec90270")) {

					g.dec90270();
				}
			}
			exploredStates.add(g.getHashState());

			String negateStep = "";
			if (curNode.action == null) {
				negateStep = "";
			} else if (curNode.action.equalsIgnoreCase("inc0180")) {
				negateStep = "dec0180";
			} else if (curNode.action.equalsIgnoreCase("dec0180")) {
				negateStep = "inc0180";
			} else if (curNode.action.equalsIgnoreCase("inc90270")) {
				negateStep = "dec90270";
			} else if (curNode.action.equalsIgnoreCase("dec90270")) {
				negateStep = "inc90270";
			} else if (curNode.action.equalsIgnoreCase("incEquator")) {
				negateStep = "decEquator";
			} else if (curNode.action.equalsIgnoreCase("decEquator")) {
				negateStep = "incEquator";
			}
			for (int i = 0; i < 6; i++) {
				if (!moves[i].equalsIgnoreCase(negateStep)) {
					if (moves[i].equalsIgnoreCase("inc0180")) {
						g.inc0180();
						if (!exploredStates.contains(g.getHashState())) {
							if (g.isGlobeSolved()) {
								movesToSolution.push(moves[i]);
								return movesToSolution;
							}
						}
						h_n = heuristic_function(g);
						g.dec0180();
					} else if (moves[i].equalsIgnoreCase("dec0180")) {
						g.dec0180();
						if (!exploredStates.contains(g.getHashState())) {
							if (g.isGlobeSolved()) {
								movesToSolution.push(moves[i]);
								return movesToSolution;
							}
						}
						h_n = heuristic_function(g);
						g.inc0180();
					} else if (moves[i].equalsIgnoreCase("inc90270")) {
						g.inc90270();
						if (!exploredStates.contains(g.getHashState())) {
							if (g.isGlobeSolved()) {
								movesToSolution.push(moves[i]);
								return movesToSolution;
							}
						}
						h_n = heuristic_function(g);
						g.dec90270();
					} else if (moves[i].equalsIgnoreCase("dec90270")) {
						g.dec90270();
						if (!exploredStates.contains(g.getHashState())) {
							if (g.isGlobeSolved()) {
								movesToSolution.push(moves[i]);
								return movesToSolution;
							}
						}
						h_n = heuristic_function(g);
						g.inc90270();
					} else if (moves[i].equalsIgnoreCase("incEquator")) {
						g.incEquator();
						if (!exploredStates.contains(g.getHashState())) {
							if (g.isGlobeSolved()) {
								movesToSolution.push(moves[i]);
								return movesToSolution;
							}
						}
						h_n = heuristic_function(g);
						g.decEquator();
					} else if (moves[i].equalsIgnoreCase("decEquator")) {
						g.decEquator();
						if (!exploredStates.contains(g.getHashState())) {
							if (g.isGlobeSolved()) {
								movesToSolution.push(moves[i]);
								return movesToSolution;
							}
						}
						h_n = heuristic_function(g);
						g.incEquator();
					}
					frontier.add(new AStarNode(curNode, moves[i], h_n + turnMoves + 1));
				}

			}
			String revMove;
			while (!movesToSolution.isEmpty()) {
				revMove = movesToSolution.pop();
				if (revMove.equalsIgnoreCase("inc0180")) {
					g.dec0180();
				} else if (revMove.equalsIgnoreCase("dec0180")) {
					g.inc0180();
				} else if (revMove.equalsIgnoreCase("inc90270")) {
					g.dec90270();
				} else if (revMove.equalsIgnoreCase("dec90270")) {
					g.inc90270();
				} else if (revMove.equalsIgnoreCase("incEquator")) {
					g.decEquator();
				} else if (revMove.equalsIgnoreCase("decEquator")) {
					g.incEquator();
				}
			}
			movesToSolution.clear();

		}
	}
}
