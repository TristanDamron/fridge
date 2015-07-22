public class Package {

    String name;
    String file_name;
    double size_in_megabytes;
    String url;

    public Package(String name, double size, String url, String file_name) {

	this.name = name;
	this.size_in_megabytes = size;
	this.url = url;
	this.file_name = file_name;

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

	return name + " " + size_in_megabytes + "Mb";

    }

}
