package com.dominicswaine.seg_agile_project.Logic;

import java.util.ArrayList;

abstract public class KorgoolContainer {
    protected ArrayList<Korgool> korgools;
    protected Side original0wner;

    public KorgoolContainer(Side original0wner){
        korgools = new ArrayList<>();
        this.original0wner = original0wner;
    }

    public void addKorgool(Korgool korgool){
        korgools.add(korgool);
    }

    public void addKorgools(ArrayList<Korgool> korgools){
        korgools.addAll(korgools);
    }

    public boolean isEmpty(){
        return korgools.isEmpty();
    }

    public int getNumberOfKoorgools(){
        return korgools.size();
    }

    public ArrayList<Korgool> getKoorgools(){
        return korgools;
    }

}