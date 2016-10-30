package enums;

public enum Colors {
	WHITE("#ffffff"),
	GREY("#d8d2d2"),
	BLACK("#000000");

    private String color = ""; // in meters
    
    Colors(String color) {
        this.color = color;
    }
    private String color() { return color; }
    
}
