package rotris;

import javax.swing.JPanel;

public class TetrisBotMaybe implements Runnable{


	static int highest[] = {20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
	static int LayoutDisplacement[] = new int[10];
	static double priorityscore[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	static JPanel thecoord[][] = new JPanel[10][20];
	static String command[] = new String[10];


	static int theshortest=0;
	static String layout;
	static int columnof; 
	static boolean donebot = false;
	static int rotationsNeeded;
	static int layoutA[] = new int[10];
	static boolean runthis = true;
	static String finalcommand;
	static String shortLayout2 = "";
	static String shortLayout3 = "";
	static String shortLayout4 = "";
	static String layoutneeded;
	static int commandcolumn;
	static double highestpri;
	static int currentcolumn;
	static int rotationDisplacement;
	static int columnsaway;
	static String relLayout2; // relative to left
	// per 2 or 3 or 4
	static String relLayout3;
	static String relLayout4;

	static char thetype;






	public void run() {

		RonaTetris.paused(75);
		// pause it a bit to let it load

		while(true) {
			// run forever ?


			while (runthis == true) {
				// when its not stopped



				System.out.print("");
				// it was for testing

				RonaTetris.paused(5);
				RonaTetris.setKey(40);
				// pause and set the key to fall

				while(donebot == false) {
					// when the current block is still in the air
					// pause for half a second
					RonaTetris.paused(50);

					thecoord = RonaTetris.getPanel();
					// re-get the whole grid jpanel
					thetype = RonaTetris.getCurrentType();
					// get the newest type

					theshortest = 0;
					int count = 0;

					//find the highest 
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
						if (highest[x]>theshortest) {

							theshortest = highest[x];
						}
					}






					// in the form of a string so string methods can be used


					layout =""; // reset layout
					for (int x=0;x<10;x++) {

						layout = layout + " " + (Integer.toString(0-(highest[x]-theshortest)));

						layoutA[x] = 0-(highest[x]-theshortest);


					}






					/* PLACEMENT NOTES

		layout:
		block type starting rows
			odd rotation change -> even rotation change
			and they loop e.g. first rotation is odd


					 * I starts at row 4
		  	left two -> right one

					 * L starts at row 45
		 	left one -> same 

					 * J starts at 45
		  	left one -> same

					 * T starts at 345
		 	same -> left one

					 * Z starts at 456 
		  	same -> left one

					 * S starts at 345
		  	same -> left one

					 * O starts at 45
		  always same 

					 */




					// layout 4 could sub for the rest except for the last 3 columns so welp

					// space separates numbers, comma separates each x (delimiter)

					relLayout2 ="";
					relLayout3 = "";
					relLayout4 = "";


					for (int x=0;x<(9);x++){
						relLayout2 = relLayout2 + "0 " 
								+ Integer.toString(layoutA[x+1] - layoutA[x]) 
								+ ", ";
						// the 0 comes from x-x caus they all minus x to be relative
					}
					for (int x=0;x<(8);x++){
						relLayout3 = relLayout3 + "0 "
								+ Integer.toString(layoutA[x+1] - layoutA[x]) 
								+" "
								+ Integer.toString(layoutA[x+2] - layoutA[x])
								+ ", ";
					}
					for (int x=0;x<(7);x++){
						relLayout4 = relLayout4 + "0 " 
								+ Integer.toString(layoutA[x+1] - layoutA[x]) 
								+" "
								+ Integer.toString(layoutA[x+2] - layoutA[x])
								+" "
								+ Integer.toString(layoutA[x+3] - layoutA[x])
								+ ", ";

					}



					for (int x=0; x<10; x++) {
						// reset

						commandcolumn = 0;
						highestpri = 0;
						priorityscore[x] =0;
						LayoutDisplacement[x]=0;
						command[x] = "good luck";

					}


					for (int x =9; x>=0; x--) {

						layoutneeded = "none"; //reset

						// for every column
						/* get the layout associated with the column
						 * for example the layout of the 3rd column is actually the 2nd x (since first x is 0) and has 2 commas beforehand
						 * so the first column is x=0 and the 2 or 3 or 4 things after, and we do not need to remove a comma
						 *we dont really need to limit the rest of the layout so just using contains should be fine but for my ease of checking my code i got rid of the rest too*/ 

						shortLayout2 = relLayout2;
						shortLayout3 = relLayout3;
						shortLayout4 = relLayout4;

						for (int x2=0; x2<x; x2++) { shortLayout2 = shortLayout2.substring(shortLayout2.indexOf(',')+1);}

						if (shortLayout2.contains(",")) {
							shortLayout2 = shortLayout2.substring(0, shortLayout2.indexOf(','));}

						for (int x3=0; x3<x; x3++) { shortLayout3 = shortLayout3.substring(shortLayout3.indexOf(',')+1);}

						if (shortLayout3.contains(",")) {
							shortLayout3 = shortLayout3.substring(0, shortLayout3.indexOf(','));
						}


						for (int x4=0; x4<x; x4++) { shortLayout4 = shortLayout4.substring(shortLayout4.indexOf(',')+1);}
						if (shortLayout4.contains(",")) {
							shortLayout4 = shortLayout4.substring(0, shortLayout4.indexOf(','));}




						shortLayout2 = shortLayout2.trim();
						shortLayout3 = shortLayout3.trim();
						shortLayout4 = shortLayout4.trim();

						LayoutDisplacement[x] = 0;

						if (thetype == 'I') {

							priorityscore[x] = highest[x]*5;

							if(x==9) {
								priorityscore[9] = priorityscore[9] + highest[9]-highest[8];
							}


							if (shortLayout2.contains("0 -18")) {layoutneeded = "0 -18"; 
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +30; 
							command[x] = "as is";}

							else if (shortLayout2.contains("0 18")) {layoutneeded = "0 18";

							priorityscore[x] = priorityscore[x] +25; 
							command[x] = "as is";}

							else if (shortLayout2.contains("0 17")) {layoutneeded = "0 17";
							priorityscore[x] = priorityscore[x] +25; 
							command[x] = "as is";}

							else if (shortLayout2.contains("0 16")) {layoutneeded = "0 16";
							priorityscore[x] = priorityscore[x] +20; 
							command[x] = "as is";}


							else if (shortLayout2.contains("0 -17")) {layoutneeded = "0 -17";
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +25; 
							command[x] = "as is";}

							else if (shortLayout2.contains("0 -16")) {layoutneeded = "0 -16";
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +25; 
							command[x] = "as is";}
							else if (shortLayout2.contains("0 -15")) {layoutneeded = "0 -15";
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +25; 
							command[x] = "as is";}


							else if (shortLayout2.contains("0 -14")) {layoutneeded = "0 -14";
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +20;
							command[x] = "as is";}





							else if (shortLayout2.contains("0 -13")) {layoutneeded = "0 -13";
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +25;
							command[x] = "as is";}


							else if (shortLayout2.contains("0 -12")) {layoutneeded = "0 -12";
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +25; 
							command[x] = "as is";}



							else if (shortLayout2.contains("0 -11")) {layoutneeded = "0 -11";
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +25; 
							command[x] = "as is";}


							else if (shortLayout2.contains("0 11")) {layoutneeded = "0 11";
							priorityscore[x] = priorityscore[x] +25;
							command[x] = "as is";}

							else if (shortLayout2.contains("0 -10")) {layoutneeded = "0 -10";
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +25; 
							command[x] = "as is";}
							else if (shortLayout2.contains("0 10")) {layoutneeded = "0 10"; 
							priorityscore[x] = priorityscore[x] +25;
							command[x] = "as is";}

							else if (shortLayout2.contains("0 9")) {layoutneeded = "0 9";
							priorityscore[x] = priorityscore[x] +24;
							command[x] = "as is";}

							else if (shortLayout2.contains("0 -9")) {layoutneeded = "0 -9";
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +25; 
							command[x] = "as is";}


							else if (shortLayout2.contains("0 -8")) {layoutneeded = "0 -8";
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +22; 
							command[x] = "as is";}




							else if (shortLayout2.contains("0 8")) {layoutneeded = "0 8";
							priorityscore[x] = priorityscore[x] +22; 
							command[x] = "as is";}

							else if (shortLayout2.contains("0 -7")) {layoutneeded = "0 -7";
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +21;
							command[x] = "as is";}


							else if (shortLayout2.contains("0 7")) {layoutneeded = "0 7";
							priorityscore[x] = priorityscore[x] +21; 
							command[x] = "as is";}

							else if (shortLayout2.contains("0 6")) {layoutneeded = "0 6";
							priorityscore[x] = priorityscore[x] +20;
							command[x] = "as is";}


							else if (shortLayout2.contains("0 -6")) {layoutneeded = "0 -6"; 
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +20;
							command[x] = "as is";}


							else if (shortLayout2.contains("0 -5")) {layoutneeded = "0 -5";
							LayoutDisplacement[x] = 1;
							priorityscore[x] = priorityscore[x] +20;
							command[x] = "as is";}


							else if (shortLayout3.contains("0 -5 0")) {layoutneeded = "0 -5 0";
							priorityscore[x] = priorityscore[x] +19;
							LayoutDisplacement[x] = 1;
							command[x] = "as is";}


							else if (shortLayout2.contains("0 5")) {layoutneeded = "0 5"; 
							priorityscore[x] = priorityscore[x] +19; 
							command[x] = "as is";}

							else if (shortLayout2.contains("0 -4")) {layoutneeded = "0 -4"; 
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +20; 
							command[x] = "as is";}

							else if (shortLayout3.contains("0 -4 -1")) {layoutneeded = "0 -4 -1";
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +15;
							command[x] = "as is";}
							else if (shortLayout3.contains("0 -4 0")) {layoutneeded = "0 -4 0";
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +18;
							command[x] = "as is";}


							else if (shortLayout2.contains("0 4")) {layoutneeded = "0 4"; 
							priorityscore[x] = priorityscore[x] +18;
							command[x] = "as is";}

							else if (shortLayout2.contains("0 3")) {layoutneeded = "0 3";
							priorityscore[x] = priorityscore[x] +18; 
							command[x] = "as is";}




							else if (shortLayout2.contains("0 -3")) {layoutneeded = "0 -3";
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +18; 
							command[x] = "as is";}


							else if (shortLayout3.contains("0 -3 0")) {layoutneeded = "0 -3 0";
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +16; 
							command[x] = "as is";}

							else if (shortLayout3.contains("0 -3 1")) {layoutneeded = "0 -3 1"; 
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +14; 
							command[x] = "as is";}




							else if (shortLayout3.contains("0 -2 0")) {layoutneeded = "0 -2 0"; 
							LayoutDisplacement[x] = 1;

							priorityscore[x] = priorityscore[x] +10;
							command[x] = "as is";}


							else if (shortLayout4.contains("0 0 0 0")) {layoutneeded = "0 0 0 0";
							priorityscore[x] = priorityscore[x] +7; 
							command[x] = "rotate 1";}

							else {

								command[x] = "good luck";
							}



							if (layoutneeded.startsWith("0 -")) {
								priorityscore[x+1] = priorityscore[x]+highest[x+1]*2;
								command[x+1] = command[x];

							}



						}


						if (thetype == 'L') {

							if (x!=9) {
								priorityscore[x] = (highest[x] + highest[x+1])/2 ;


								if (Math.abs(highest[x]-highest[x+1])>2) {
									priorityscore[x] = priorityscore[x]-10;
								}
							}

							else {priorityscore[x] = (highest[x] + highest[x-1])/2;
							if (Math.abs(highest[x]-highest[x-1])>2) {
								priorityscore[x] = priorityscore[x]-10;
							}


							}


							if (shortLayout3.contains("0 1 1")) {layoutneeded = "0 1 1"; 

							priorityscore[x] = priorityscore[x] +25; 
							command[x] = "rotate 1";}

							else if (shortLayout2.contains("0 -2")) { layoutneeded = "0 -2";
							priorityscore[x] = priorityscore[x] +20; 
							command[x] = "rotate 2";}


							else if (shortLayout3.contains("0 0 0")) {layoutneeded = "0 0 0"; 
							priorityscore[x] = priorityscore[x] +19; 
							command[x] = "rotate 3";}

							else if (shortLayout2.contains("0 0")) {layoutneeded = "0 0";
							priorityscore[x] = priorityscore[x] +18;
							command[x] = "as is";}
							else if (shortLayout2.contains("0 -4")) { layoutneeded = "0 -4";
							priorityscore[x] = priorityscore[x] +12; 
							command[x] = "rotate 2";}

							else if (shortLayout2.contains("0 -5")) { layoutneeded = "0 -5";
							priorityscore[x] = priorityscore[x] +10; 
							command[x] = "rotate 2";}
							else if (shortLayout2.contains("0 -6")) { layoutneeded = "0 -5";
							priorityscore[x] = priorityscore[x] +15; 
							command[x] = "rotate 2";}

							else if (shortLayout2.contains("0 -7")) { layoutneeded = "0 -5";
							priorityscore[x] = priorityscore[x] +15; 
							command[x] = "rotate 2";}




							else { command[x] = "good luck";}


						}

						if (thetype == 'J') {

							if (x!=9) {
								priorityscore[x] = (highest[x] + highest[x+1])/2 ;

								if (Math.abs(highest[x]-highest[x+1])>2) {
									priorityscore[x] = priorityscore[x]-10;
								}


							}



							else {priorityscore[x] = (highest[x] + highest[x-1])/2;
							if (Math.abs(highest[x]-highest[x-1])>2) {
								priorityscore[x] = priorityscore[x]-10;
							}
							
							}


							if (shortLayout3.contains("0 0 -1")) {layoutneeded = "0 0 -1"; 
							priorityscore[x] = priorityscore[x] +20; 
							command[x] = "rotate 3";}


							else if (shortLayout2.contains("0 2")) {layoutneeded = "0 2"; 
							priorityscore[x] = priorityscore[x] +19; 
							command[x] = "rotate 2";}

							else if (shortLayout3.contains("0 0 0")) {layoutneeded = "0 0 0";
							priorityscore[x] = priorityscore[x] +18;
							command[x] = "rotate 1";}

							else if (shortLayout2.contains("0 0")) {layoutneeded = "0 0";
							priorityscore[x] = priorityscore[x] +16;
							command[x] = "as is";}

							else if (shortLayout2.contains("0 4")) {layoutneeded = "0 2"; 
							priorityscore[x] = priorityscore[x] +10; 
							command[x] = "rotate 2";}
							
							else if (shortLayout2.contains("0 5")) {layoutneeded = "0 2"; 
							priorityscore[x] = priorityscore[x] +12; 
							command[x] = "rotate 2";}
							
							else if (shortLayout2.contains("0 6")) {layoutneeded = "0 2"; 
							priorityscore[x] = priorityscore[x] +12; 
							command[x] = "rotate 2";}
							
							else if (shortLayout2.contains("0 7")) {layoutneeded = "0 2"; 
							priorityscore[x] = priorityscore[x] +12; 
							command[x] = "rotate 2";}


							else {

								command[x] = "good luck";}


						}


						if (thetype == 'Z') {

							if (x!=9) {
								priorityscore[x] = (highest[x] + highest[x+1])/2 ;

								if (Math.abs(highest[x]-highest[x+1])>2) {
									priorityscore[x] = priorityscore[x]-10;
								}


							}



							else {priorityscore[x] = (highest[x] + highest[x-1])/2;
							if (Math.abs(highest[x]-highest[x-1])>2) {
								priorityscore[x] = priorityscore[x]-10;
							}
							}

							if (shortLayout3.contains("0 -1 -1")) {layoutneeded = "0 -1 -1"; 
							priorityscore[x] = priorityscore[x] +15;
							command[x] = "as is";}

							else if (shortLayout2.contains("0 1")) {layoutneeded = "0 1"; 
							priorityscore[x] = priorityscore[x] +10; 
							command[x] = "rotate 1";}

							else {
								command[x] = "rotate 1";}


						}


						if (thetype == 'S') {

							if (x!=9) {
								priorityscore[x] = (highest[x] + highest[x+1])/2 ;

								if (Math.abs(highest[x]-highest[x+1])>2) {
									priorityscore[x] = priorityscore[x]-10;
								}


							}



							else {priorityscore[x] = (highest[x] + highest[x-1])/2;
							if (Math.abs(highest[x]-highest[x-1])>2) {
								priorityscore[x] = priorityscore[x]-10;
							}
							}

							if (shortLayout3.contains("0 0 1")) {layoutneeded = "0 1 1"; 
							priorityscore[x] = priorityscore[x] +25; 
							command[x] = "as is";}

							else if (shortLayout2.contains("0 -1")) {layoutneeded = "0 -1"; 
							priorityscore[x] = priorityscore[x] +20; 
							command[x] = "rotate 1";
							}

							else if (shortLayout3.contains("0 0 -1")) {layoutneeded = "0 -1"; 
							priorityscore[x] = priorityscore[x] +20;
							LayoutDisplacement[x]=1;
							command[x] = "rotate 1";
							}

							else {command[x] = "rotate 1";}
						}


						if (thetype == 'T') {


							if (x<8) {
								priorityscore[x] = (highest[x] + highest[x+1]+highest[x+2])/2 ;

								if (Math.abs(highest[x]-highest[x+1])>2) {
									priorityscore[x] = priorityscore[x]-15;
								}


							}



							else {priorityscore[x] = (highest[x] + highest[x-1])/2;
							if (Math.abs(highest[x]-highest[x-1])>2) {
								priorityscore[x] = priorityscore[x]-10;
							}
							}


							if (shortLayout3.contains("0 -1 0")) {layoutneeded = "0 -1 0"; 
							priorityscore[x] = priorityscore[x] +25;
							command[x] = "rotate 2";}

							else if (shortLayout3.contains("0 0 0")) {layoutneeded = "0 0 0"; 
							priorityscore[x] = priorityscore[x] +17; 
							command[x] = "as is";}


							else if (shortLayout2.contains("0 1")) {layoutneeded = "0 1"; 
							priorityscore[x] = priorityscore[x] +15;
							command[x] = "rotate 1";}

							else if (shortLayout2.contains("0 -1")) {layoutneeded = "0 -1";
							priorityscore[x] = priorityscore[x] +15; 
							command[x] = "rotate 3";}


							else {command[x] = "good luck";}


						}



						if (thetype == 'O') {

							if (x!=9) {
								priorityscore[x] = (highest[x] + highest[x+1])/2 ;

								if (Math.abs(highest[x]-highest[x+1])>1) {
									priorityscore[x] = priorityscore[x]-10;
								}


							}



							else {priorityscore[x] = (highest[x] + highest[x-1])/2;
							if (Math.abs(highest[x]-highest[x-1])>2) {
								priorityscore[x] = priorityscore[x]-10;
							}
							}

							if (shortLayout3.contains("0 -6 -6")) {	layoutneeded = "0 -5 -5"; 
							priorityscore[x] = priorityscore[x] +20; 
							LayoutDisplacement[x]=1;
							command[x] = "as is";}
							else 	if (shortLayout3.contains("0 -5 -5")) {	layoutneeded = "0 -5 -5"; 
							priorityscore[x] = priorityscore[x] +20; 
							LayoutDisplacement[x]=1;

							command[x] = "as is";}

							else 	if (shortLayout3.contains("0 -4 -4")) {	layoutneeded = "0 -4 -4"; 
							priorityscore[x] = priorityscore[x] +19; 
							LayoutDisplacement[x]=1;

							command[x] = "as is";}
							if (shortLayout3.contains("0 -3 -3")) {	layoutneeded = "0 -3 -3"; 
							priorityscore[x] = priorityscore[x] +18; 
							LayoutDisplacement[x]=1;

							command[x] = "as is";}

							if (shortLayout3.contains("0 -2 -2")) {	layoutneeded = "0 -2 -2"; 
							priorityscore[x] = priorityscore[x] +17; 
							LayoutDisplacement[x]=1;

							command[x] = "as is";}
							if (shortLayout3.contains("0 -1 -1")) {	layoutneeded = "0 -1 -1"; 
							priorityscore[x] = priorityscore[x] +16; 
							LayoutDisplacement[x]=1;

							command[x] = "as is";}



							else if (shortLayout2.contains("0 0")) {	layoutneeded = "0 0"; 
							priorityscore[x] = priorityscore[x] +15; 

							command[x] = "as is";}

							else if (shortLayout2.contains("0 -1")) {	layoutneeded = "0 0"; 
							priorityscore[x] = priorityscore[x] +5; 

							command[x] = "as is";}

							else if (shortLayout2.contains("0 1")) {	layoutneeded = "0 0"; 
							priorityscore[x] = priorityscore[x] +5; 

							command[x] = "as is";}



							else {command[x] = "good luck";}
						}




					} // end of for


					// now find the one with the most priority





					for (int x=0; x<10; x++) {

						if (priorityscore[x]>highestpri) {

							highestpri = priorityscore[x];
							commandcolumn = x;	}

					}
					// could change to quicksort 



					command[commandcolumn] = command[commandcolumn].trim();

					finalcommand = Integer.toString(commandcolumn) +  " " + command[commandcolumn];


					if (command[commandcolumn].equals("as is")== false && command[commandcolumn].equals("good luck")== false) {

						rotationsNeeded = Integer.parseInt( command[commandcolumn].substring(command[commandcolumn].indexOf(' ')+1,command[commandcolumn].indexOf(' ')+2));
						// could set them all again so its not a string to int "rotate 2" rather than just saying rotations = 2

					}
					else {rotationsNeeded = 0;

					}

					if (thetype == 'I') {
						if (rotationsNeeded == 1) { rotationDisplacement = -2;}
						else if (rotationsNeeded == 0) { rotationDisplacement = 0;}
					}

					else if (thetype == 'O') {

						rotationDisplacement = 0;
					}


					else if (thetype == 'T' || thetype == 'Z' || thetype == 'S') {
						if (rotationsNeeded == 1) { rotationDisplacement = 0;}
						if (rotationsNeeded == 2) { rotationDisplacement = -1;}
						if (rotationsNeeded == 3) { rotationDisplacement = -1;}
						else if (rotationsNeeded == 0) { rotationDisplacement = 0;}


					}

					else if (thetype == 'L' || thetype == 'J') {

						if (rotationsNeeded == 1) { rotationDisplacement = -1;}
						if (rotationsNeeded == 2) { rotationDisplacement = -1;}
						if (rotationsNeeded == 3) { rotationDisplacement = -2;}
						else if (rotationsNeeded == 0) { rotationDisplacement = 0;}


					};

					if (thetype == 'S' || thetype == 'T') {

						currentcolumn = 3;}

					else {currentcolumn = 4;}




					commandcolumn = commandcolumn + LayoutDisplacement[commandcolumn];


					columnsaway = -rotationDisplacement +commandcolumn - currentcolumn;






					for (int r = 0; r<rotationsNeeded; r++) {

						// set the key to rotate and then pause a bit 
						RonaTetris.setKey(38);
						RonaTetris.paused(10);
					}

					if (columnsaway <0) {
						// set the key to left 
						for (int d=0; d>columnsaway; d--) {
							RonaTetris.setKey(37);
							RonaTetris.paused(10);


						}
					}

					if (columnsaway>0) {
						// set the key to right
						for (int d=0; d<columnsaway; d++) {
							RonaTetris.setKey(39);
							RonaTetris.paused(10);

						}



					}

					donebot = true;
					// done bot for now so its gonna move down

				}
			}
		}
	}





	static void setbot(boolean bot) {

		runthis = bot;
	}

	static boolean getbot() {

		return runthis;
	}

	static void reset() {

		for (int x=0;x<10;x++) {


			highest[x] = 20;
			layoutA[x] = 0;
			shortLayout2 = "";
			shortLayout3 = "";
			shortLayout4 = "";
			relLayout2 = "";
			relLayout3 = "";
			relLayout4 = "";
			command[x] = "";
			commandcolumn = 0;
			priorityscore[x] = 0;
			currentcolumn = 0;
			highestpri = 0;
			LayoutDisplacement[x] = 0;

		}
	}

	static boolean getbotstatus() {

		return donebot;
	}
	static void setbotstatus(boolean trueorfalse) {

		donebot = trueorfalse;
	}



	static void holdOrNot(char thetype) {

		// never used this but it would be easy to implement to see if the bot would like to hold the preview
		System.out.print(" " + thetype  +  " ");

		// layout 4 could sub for the rest except for the last 3 columns 

		// space separates numbers, comma separates each x (delimiter)

		relLayout2 ="";
		relLayout3 = "";
		relLayout4 = "";


		for (int x=0;x<(9);x++){
			relLayout2 = relLayout2 + "0 " 
					+ Integer.toString(layoutA[x+1] - layoutA[x]) 
					+ ", ";
			// the 0 comes from x-x caus they all minus x to be relative

		}
		for (int x=0;x<(8);x++){
			relLayout3 = relLayout3 + "0 "
					+ Integer.toString(layoutA[x+1] - layoutA[x]) 
					+" "
					+ Integer.toString(layoutA[x+2] - layoutA[x])
					+ ", ";
		}
		for (int x=0;x<(7);x++){
			relLayout4 = relLayout4 + "0 " 
					+ Integer.toString(layoutA[x+1] - layoutA[x]) 
					+" "
					+ Integer.toString(layoutA[x+2] - layoutA[x])
					+" "
					+ Integer.toString(layoutA[x+3] - layoutA[x])
					+ ", ";
		}


		if (thetype == 'I') {


			if (relLayout3.contains("0 -5 0")) {}
			else if (relLayout2.contains("0 -6")) {}
			else if (relLayout3.contains("0 -4 0")) {}
			else if (relLayout2.contains("0 -7")) {}
			else if (relLayout2.contains("0 18")) {}
			else if (relLayout2.contains("0 17")) {}
			else if (relLayout2.contains("0 16")) {}
			else if (relLayout2.contains("0 15")) {}
			else if (relLayout2.contains("0 14")) {}
			else if (relLayout2.contains("0 13")) {}
			else if (relLayout2.contains("0 12")) {}
			else if (relLayout2.contains("0 11")) {}
			else if (relLayout2.contains("0 10")) {}
			else if (relLayout2.contains("0 9")) {}
			else if (relLayout2.contains("0 8")) {}
			else if (relLayout2.contains("0 7")) {}
			else if (relLayout2.contains("0 6")) {}
			else if (relLayout2.contains("0 5")) {}
			else if (relLayout2.contains("0 4")) {}
			else if (relLayout2.contains("0 3")) {}
			else if (relLayout2.contains("0 -8")) {}
			else if (relLayout2.contains("0 -9")) {}
			else if (relLayout2.contains("0 -10")) {}
			else if (relLayout2.contains("0 -11")) {}
			else if (relLayout2.contains("0 -12")) {}
			else if (relLayout2.contains("0 -13")) {}
			else if (relLayout2.contains("0 -14")) {}
			else if (relLayout2.contains("0 -15")) {}
			else if (relLayout2.contains("0 -16")) {}
			else if (relLayout2.contains("0 -17")) {}
			else if (relLayout2.contains("0 -18")) {}
			else if (relLayout3.contains("0 -4 -1")) {}
			else if (relLayout3.contains("0 -2 -1")) {}
			else if (relLayout3.contains("0 -3 0")) {}
			else if (relLayout3.contains("0 -3 1")) {}
			else if (relLayout2.contains("0 -5")) {}
			else if (relLayout2.contains("0 -4")) {}
			else if (relLayout4.contains("0 0 0 0")) {}
			else if (relLayout2.contains("0 -3")) {}			
			else {	}			
		}

		if (thetype == 'L') {


			if (relLayout2.contains("0 -2")) { }
			else if (relLayout3.contains("0 1 1")) {}
			else if (relLayout3.contains("0 0 0")) {}
			else if (relLayout2.contains("0 0")) {}
			else {System.out.println("HOLD\n");}}

		if (thetype == 'J') {
			if (relLayout2.contains("0 2")) {}
			else if (relLayout3.contains("0 0 0")) {}
			else {System.out.println("HOLD\n");}
		}

		if (thetype == 'Z') {							
			if (relLayout3.contains("0 -1 -1")) {}
			else if (relLayout3.contains("0 1")) {}			
			else {System.out.println("HOLD\n");}				
		}


		if (thetype == 'S') {				
			if (relLayout3.contains("0 1 1")) {}
			else if (relLayout2.contains("0 -1")) {	}
			else {System.out.println("HOLD\n");}

		}


		if (thetype == 'T') {
			if (relLayout3.contains("0 -1 0")) {}
			else if (relLayout3.contains("0 0 0")) {}
			else if (relLayout2.contains("0 1")) {}
			else if (relLayout2.contains("0 -1")) {}
			else {}
		}


		if (thetype == 'O') {

			if (relLayout2.contains("0 0")) {}
			else {	System.out.println("HOLD\n");}
		}

	}

}











