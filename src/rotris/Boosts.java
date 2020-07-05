package rotris;

import java.awt.Color;

import javax.swing.JPanel;

public class Boosts {
	static Color mydarkgray = Color.getHSBColor(0f, 0f, 0.1f);


	private static int checkcount = 0;
	static boolean boosted = false;

	static int scoreboost;


	static void setboosted(boolean isitboosted) {

		boosted = isitboosted;
	}

	static boolean getboosted() {
		return boosted;
	}

	static int getscoreboost() {

		return scoreboost;
	}

	static void Fill(JPanel[][] panel) {


		checkcount = 0;
		for (int y = 19; y>0; y--)
			for (int x = 0; x<10; x++)
			{
				System.out.print("");
				if (panel[x][y].getName() != "full" && checkcount !=1) {
					checkcount = checkcount+1;

					panel[x][y].setName("full");
					panel[x][y].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));


				}



			}

	};



	static void DoubleScore(int booster) {

		if (booster <2 ) {
			scoreboost = 2*booster;}
		else {
			scoreboost = (int) (1.75*booster);
		}


		boosted = true;

		;

		// double score for the next 30 seconds (using the timer)

	};


	static void Gravity(JPanel[][] thecoord) {
		int count = 0;
		int highest[] = {19, 19, 19, 19, 19, 19, 19, 19, 19, 19};
		int empties[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int emptiestrow = 0;

		// find the one with the most spaces below the top square


		// first find the highest block of each
		for(int x =0; x<10; x++) {
			// check every row

			count = 0;

			for (int y=0; y<20;y++)
			{

				if(count == 0) {
					if(thecoord[x][y].getName().equals("full")) {
						count = 1;

						highest[x] = y;
					}	
				}
			}

		};

		for (int x =0; x<10; x++) {
			System.out.println(x);
			empties[x] = 0;
			// count amount of empty space		
			
			// for every column, count the empty below the highest
			
			for (int y = highest[x]; y<20; y++) {
				
				
				if (highest[x] != 19) {
				if (thecoord[x][y].getName().equals("empty")) {
					
					empties[x] = empties[x]+1;
					
				}
			}
			}

		}
		
		
		for (int x = 0; x<10; x++) {
			
			
			if (empties[x]>=empties[emptiestrow]) {
				
				emptiestrow = x;
				
			}
		}
			

		
			for (int a = empties[emptiestrow]; a>0; a--) {
			for (int y=19; y>highest[emptiestrow]; y--) {
				
				if (thecoord[emptiestrow][y].getName().equals("empty")
						&& thecoord[emptiestrow][y-1].getName().equals("full")
						) {
					thecoord[emptiestrow][y-1].setName("empty");
					
					thecoord[emptiestrow][y].setName("full");
					
					thecoord[emptiestrow][y].setBackground(Color.getHSBColor(0.0f, 0f, 1f));
					
					RonaTetris.paused(2);
					// so the falling effect looks cooler make it pause a bit 

					thecoord[emptiestrow][y].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));

					thecoord[emptiestrow][y-1].setBackground(mydarkgray);

				}
			}
				
			}
	}

}

