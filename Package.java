import java.io.File;

public class Package {

    String name;
    String file_name;
    double size_in_megabytes;
    String url;
    public static final String RESET = "\u001B[0m";
    public static final String INSTALLED = "\u001B[32m";
    public static final String NOT_INSTALLED = "\u001B[31m";


    public Package(String name, double size, String url, String file_name) {

	this.name = name;
	this.size_in_megabytes = size;
	this.url = url;
	this.file_name = file_name;

    }

    private boolean isInstalled(String name) {
	
	if (new File("srcs/" + name).exists()) {
	    
	    return true;
	    
	} else {

	    return false;

	}

    }

    public double getSize() {

	return this.size_in_megabytes;

    }

    public String getName() {
	
	return this.name;

    }

    public String getUrl() {

	return this.url;

    }

    public String getFile() {

	return this.file_name;

    }

    public String toString() {

	
	if (isInstalled(this.name)) {
		
	    return INSTALLED + this.name + " " + size_in_megabytes + "Mb" + RESET;

	} else {

	    return NOT_INSTALLED + this.name + " " + size_in_megabytes + "Mb" + RESET;

	}

    }

}
