package com.company;

/**
 * An enum that defines the possible colors a polyline can have
 */
enum Color {

    BLACK ("Black"), RED ("Red"), ORANGE ("Orange"), YELLOW ("Yellow"),
    GREEN ("Green"), BLUE ("Blue"), PURPLE ("Purple"), WHITE ("White");


    /**
     * String to hold the name of the color
     */
    private final String colorName;

    Color(String colorName) {
        this.colorName = colorName;
    }

    /**
     * Method to get the name of the color in use
     * @return A string with the capitalized name of the color
     */
    protected String colorName() { return colorName; }

    }
