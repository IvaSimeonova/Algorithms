package Algorithms;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

class SortingPanel extends JPanel{
	private ArrayList<Integer> numbers = new ArrayList(Arrays.asList(1, 2, 3, 4, 5));
	
	private static int noSortingStage = -1;
	private static int incrementIStage = 0;
	private static int incrementJStage = 1;
	private static int compareStage = 2;
	private static int switchStage = 3;
	private static int isSortedStage = 4;
	
	private int stage = noSortingStage;
	
	int currentI = -1;
	int currentJ = -1;
	boolean haveElementsBeenSwapedForCurrentI = false;

public SortingPanel() {

}

public void makeNextStepInSorting() {
	if (stage == isSortedStage){
		return;
	}
	
	if (stage == noSortingStage){
		stage = incrementIStage;
	}
	
	if (stage == incrementIStage){
		currentI++;
		haveElementsBeenSwapedForCurrentI = false;
		stage = incrementJStage;
	}
	
	if (stage == incrementJStage){
		currentJ++;
	} else if (stage == switchStage){
		Integer numberJ = numbers.get(currentJ);
		Integer numberJPlus1 = numbers.get(currentJ + 1);
		if (numberJ > numberJPlus1){
			haveElementsBeenSwapedForCurrentI = true;
			numbers.set(currentJ + 1, numberJ);
			numbers.set(currentJ, numberJPlus1);
		}
	}
	
	repaint();
}

private void nextStage(){
	if (stage == incrementJStage){
		stage = compareStage;
	} else if (stage == compareStage){
		stage = switchStage;
	} else if (stage == switchStage){
		if (currentJ < numbers.size() - 2 - currentI){
			stage = incrementJStage;
		} else {
			currentJ = -1;
			System.out.println(currentI);
			if (currentI < numbers.size() - 2 && haveElementsBeenSwapedForCurrentI){
				stage = incrementIStage;
			} else {
				currentI = -1;
				stage = isSortedStage;
				repaint();
			}
		}
	} 
}

@Override
protected void paintComponent (Graphics g){
	super.paintComponent(g);
	int towerWidth = ((getWidth() - 20) / numbers.size()) - 2;
	int towerHeightIndex = (getHeight() - 20) / maxElement(numbers);
	int downY = getHeight() - 5;
	
	for (int j = 0; j < numbers.size(); j++){
		if (stage == noSortingStage || stage == isSortedStage){
			g.setColor(Color.BLACK);
		} else if (j == currentJ + 1 && (stage == compareStage || stage == switchStage)){
			g.setColor(Color.RED);
		} else if (j == currentJ){
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.BLACK);
		}
		
		int leftX = j * towerWidth + 5 * (j + 1);
		int rightX = leftX + towerWidth;
		int upperY = downY - numbers.get(j) * towerHeightIndex;
		g.drawLine(leftX, downY, leftX, upperY); // left side line
		g.drawLine(leftX, upperY, rightX, upperY); // top line
		g.drawLine(rightX, downY, rightX, upperY); // right line

		g.drawString(numbers.get(j).toString(), leftX + towerWidth / 2, downY - 5);
	}

	if (stage != noSortingStage) {
		nextStage();
	}
}

public static Integer maxElement(ArrayList<Integer> numbers) {
	Integer max = numbers.get(0);
	for (Integer a : numbers) {
		if (a > max) {
			max = a;
		}
	}

	return max;
}

public void nextStepInSorting() {
	// TODO Auto-generated method stub
	
}

}