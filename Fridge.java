import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Fridge {
    
    File update;
    ArrayList<Package> queue = new ArrayList<>();
    Package[][] packages = new Package[2][5];
    Scanner in;

    public Fridge() {

	in = new Scanner(System.in);
	
	printMenu();

	String input = "";

	while (!input.equals("q")) {

	    input = in.next();

	    if (input.equals("f")) {

		System.out.print("Enter your selection: ");
		String selection = in.next();
		System.out.println();
		queue.add(find(selection));

	    } else if (input.equals("j")) {

		System.out.println("Enter your deselection: ");
		String selection = in.next();
		System.out.println();
		queue.remove(find(selection));

	    } else if (input.equals("r")) {

		System.out.println(queue + "\nAre you sure you want to download the following packages? " + addFileSizes() + " megabytes (Mb) will be used. Y/n");
		String ans = in.next();

		if (ans.toLowerCase().equals("y")) {

		    download();

		} 

	    } else if (input.equals("c")) {
		try {

		    clear();

		} catch (Exception e) {

		    e.printStackTrace();

		}

	    }

	}

    }

    private void printMenu() {

        System.out.println("**********************************************************************************");
	System.out.println("==================================Fridge==========================================");
	System.out.println("		     Supported Software -- MazamaOS\n");

	getPackages();
	printPackages();
	
	System.out.println("----------------------------------------------------------------------------------");
	System.out.println(" [q-quit]  [f-select] [j-deselect] [r-install selected] [h-help] [d-description] [c-clear]");


    }
    
    private double addFileSizes() {

	double ret = 0;

	for (int i = 0; i < queue.size(); i++) {
	    
	    ret += queue.get(i).getSize();

	}

	return ret;

    }

    private void download() {

	try {

	    for (int i = 0; i < queue.size(); i++) { 

		wget(i);
		unpack(i);
		mv(i);
		configure(i);
		make(i);
		install(i);

	    }

	} catch (Exception e) {

	    e.printStackTrace();

	}

    }

    private void configure(int i) throws IOException {
	
	try {

	    ProcessBuilder configure = new ProcessBuilder("./configure");
	    configure.directory(new File("srcs/" + queue.get(i).getName()));
	    Process configure_process = configure.start();
	    configure_process.waitFor();

	} catch (Exception e) {

	    e.printStackTrace();

	}
    }

    private void clear() throws IOException {

	Runtime.getRuntime().exec("clear");
	printMenu();

    }

    private void wget(int i) throws IOException {

	try {

	    ProcessBuilder wget = new ProcessBuilder("wget",  queue.get(i).getUrl());
	    Process wget_process = wget.start();
	    wget_process.waitFor();

	} catch (Exception e) {

	    e.printStackTrace();

	}

    }

    private void install(int i) throws IOException {

	try {
	
	    ProcessBuilder install = new ProcessBuilder("make install");
	    install.directory(new File("srcs/" + queue.get(i).getName()));
	    Process install_process = install.start();
	    install_process.waitFor();

	} catch (Exception e) {

	    e.printStackTrace();

	}

    }

    private void make(int i) throws IOException {

	try {

	    ProcessBuilder make = new ProcessBuilder("make");
	    make.directory(new File("srcs/" + queue.get(i).getName()));
	    Process make_process = make.start();
	    make_process.waitFor();

	} catch (Exception e) {

	    e.printStackTrace();

	}

    }

    private void mv(int i) throws IOException {

	try {

	    ProcessBuilder mv = new ProcessBuilder("mv", queue.get(i).getName(), "srcs");
	    Process mv_process = mv.start();
	    mv_process.waitFor();

	} catch (Exception e) {

	    e.printStackTrace();

	}

    }

    private void unpack(int i) throws IOException {

	try {
		
	    ProcessBuilder unpack = new ProcessBuilder("tar", "-xf", queue.get(i).getFile());
	    Process unpack_process = unpack.start();
	    unpack_process.waitFor();

	} catch (Exception e) {

	    e.printStackTrace();

	}

    }

    private Package find(String name) {

	for (int i = 0; i < packages.length; i++) {

	    for (int j = 0; j < packages[0].length; j++) {

		if (packages[i][j].getName().equals(name)) {

		    System.out.println("Done");
		    return packages[i][j];

		}

	    }

	}

	System.out.println("Error: Package not found.");
	return null;

    }

    private void getPackages() {

	try {

	    update = new File("update");
	    Scanner scan = new Scanner(update);
	    int line_counter = 0;

	    for (int i = 0; i < packages.length; i++) {
		
		for (int j = 0; j < packages[0].length; j++) {

		    packages[i][j] = new Package(scan.next(), scan.nextDouble(), scan.next(), scan.next());

		}

	    }

	    scan.close();

	} catch (Exception e) {

	    e.printStackTrace();

	}

    }

    private void printPackages() {

	for (int i = 0; i < packages.length; i++) {

	    for (int j = 0; j < packages[0].length; j++) {

		System.out.print(packages[i][j] + "          ");

	    }

	    System.out.println("\n");

	}

    }

    public static void main(String[] args) {
	
	Fridge f = new Fridge();

    }

}
