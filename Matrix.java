import java.util.*;

class Matrix{
	
	public cell[][] matrix;
	public ArrayList<Integer> list = new ArrayList<Integer>();
	
	private class cell{
		public int num;
		public int distance;
		public int i;
		public int j;
		public boolean visited;
		public cell(int num, int i, int j){
			this.num = num;
			this.i = i;
			this.j = j;
			this.visited = false;
		}
	}
	
	public static void main(String[] args){
		Matrix m = new Matrix();
		Scanner in = new Scanner(System.in);
		System.out.print("Enter matrix height > ");
		int height = in.nextInt();
		System.out.print("Enter matrix width > ");
		int width = in.nextInt();
		m.makeMatrix(height, width);
		m.printMatrix(height, width);
		m.solve(height, width);
		System.out.println();
		System.out.println();
		m.printDistance(height, width);
	}
	
	private void makeMatrix(int height, int width){
		Random rand = new Random();
		matrix = new cell[height][width];
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				matrix[i][j] = new cell(rand.nextInt(2), i, j);
			}
		}
	}
	
	private void printMatrix(int height, int width){
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				System.out.print(matrix[i][j].num + " ");
			}
			System.out.println();
		}
	}
	
	//natsec
	//professional service division
	//cloud practioner
	//get smart on cloud
	//know services
	//wwps worldwoideproserv
	//base salary -> signing bonus for 1st and second year, RSU, stock options 40 
	// herdon, VA -> most TS//SCI stuff
	// also in annpolsi junction
	
	private void solve(int height, int width){
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				cell temp = matrix[i][j];
				matrix[i][j].visited = true;
				matrix[i][j].distance = findDistance(temp, height, width);
				setAllUnVisited(height, width);
			}
		}
	}
	
	/*
	1	1	1	1	1
	1	1	1	0	0
	1	1	0	0	0
	1	1	1	0	0
	
	0,0 -> 1,4: 5::: 1 + 4 = 5
	1,0 -> 2,2: 3::: 1 + 2 = 3
	
	find how to get to that point!
	
	find bigger i
	find how many "hops" need to occur to get to that bigger i
	
	find bigger j
	find how many "hops" need to occur to get to that bigger j
	
	add them = distance
	
	compute for all zeroes on board an compare, pick the smallest
	
	*/
	
	private int findDistance(cell temp, int h, int w){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(temp.num == 0){
			return 0;
		}
		for(int i=0; i<h; i++){
			for(int j=0; j<w; j++){
				if(matrix[i][j].visited == false){
					matrix[i][j].visited = true;
					if(matrix[i][j].num == 0){
						int distance;
						int iCoordG;
						int iCoordL;
						int jCoordG;
						int jCoordL;
						if(i > temp.i){
								iCoordG = i;
								iCoordL = temp.i;
						}
						else{
							iCoordG = temp.i;
							iCoordL = i;
						}
						if(j > temp.j){
							jCoordG = j;
							jCoordL = temp.j;
							
						}
						else{
							jCoordG = temp.j;
							jCoordL = j;
						}
						distance = (iCoordG - iCoordL) + (jCoordG - jCoordL);
						list.add(distance);
					}
				}
			}
		}
		return Collections.min(list);
	}
	
	private void setAllUnVisited(int height, int width){
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				matrix[i][j].visited = false;
			}
		}
	}
	
	private void printDistance(int height, int width){
	for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				System.out.print(matrix[i][j].distance + " ");
			}
			System.out.println();
		}
	}
}
