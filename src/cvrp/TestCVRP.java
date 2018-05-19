package cvrp;

import java.util.List;
import java.util.ArrayList;
import java.io.File;


/**
 *
 * Utility class to test algorithms for the CVRP.
 * Instances are loaded using methods load*.
 * The different methods can then be run on all these instances.
 *
 */

public class TestCVRP {

	private List<File> fileList = new ArrayList<File>();

	public TestCVRP() {
	}


	/**
	 * Load the files of the directory into the list of files
	 * @param directoryName le nom du répertoire
	 */
	public void loadFileList(String directoryName) {
		File directory = new File(directoryName);
		loadFileList(directory);
	}

	private void loadFileList(File directory) {
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			for (File f : files) {
				loadFile(f);
			}

		}
	}

	/**
	 * Load file f into the list of instances.
	 * @param f the file to load
	 */
	private void loadFile(File f) {
		if(f.getName().endsWith(".vrp")){fileList.add(f);}
		else System.out.println("File " + f.getName() + " not loaded (extension is not .vrp");
	}

	/**
	 * Load the file of name fileName into the list of instances
	 * @param fileName the name of the file to load
	 */
	public void loadFile(String fileName){
		loadFile(new File(fileName));
	}


	/**
	 *
	 * @return a list containing the names of the files loaded
	 */
	public List<String> getFileNames(){
		List<String> fileNames = new ArrayList<String>();
		for(File f : fileList){
			fileNames.add(f.getName());
		}
		return fileNames;
	}



    /**
     * Test the heuristic procedure.
     * The method will be run on each instance previously loaded.
     * The value found for each instance is put in the list in the same order the instances were entered.
     */
	public List<Double> testHeuristic(HeuristicCVRP h) {
		List<Double> listValues = new ArrayList<Double>();

		for (File f : fileList) {
			List<List<Integer>> soluce = new ArrayList<List<Integer>>();
			VRPinstance data = null;
			try {
				data = new VRPinstance(f);
			} catch (java.io.FileNotFoundException e) {
				System.out.println("File not found... Strange.");
			}
			double val = h.computeSolution(data.getMatrix(), data.getDemands(), data.getCapacity(), soluce);

			listValues.add(val);
		}

		return listValues;
	}
}