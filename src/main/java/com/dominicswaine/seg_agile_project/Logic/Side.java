package com.dominicswaine.seg_agile_project.Logic;


enum Side {
    WHITE, BLACK;

    private boolean hasTuz = false;

    public boolean hasTuz(){
        return hasTuz;
    }

    public void makeTuz(){
        hasTuz = true;
    }
}


