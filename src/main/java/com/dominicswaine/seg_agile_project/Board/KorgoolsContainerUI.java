package com.dominicswaine.seg_agile_project.Board;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This object contains and displays any number of korgols.
 * It contains, removes, and shows the korgols to screen.
 * @author Danilo Del Busso
 * @version 30-11-2018
 */
public abstract class KorgoolsContainerUI extends JLabel {

    private ArrayList <KorgoolUI> korgols;
    private int lastKorgolInd; //the index of the last korgol that has been added to the container
    private JTextArea extraKorgolsCounter;
    private JPanel extraKorgols;


    /**
     * This container maintains korgols and shows them to screen
     */
    KorgoolsContainerUI() {
        setOpaque(false);
        float[] arr = new float[3];
        float[] vals = Color.RGBtoHSB(166, 128, 100, arr);

        korgols = new ArrayList <KorgoolUI> ();
        lastKorgolInd = -1;

    }

    /**
     * Return the korgols currently present in the container
     * @return the korgols currently present in the container
     */
    public ArrayList <KorgoolUI> getKorgols() {
        return korgols;
    }

    /**
     * Adds korgols to the container so that they are aligned
     */
    void adjustLooks() {
        for (int i = lastKorgolInd + 1; i < getMaxKorgolsPossible(); ++i) {
            KorgoolUI k = new KorgoolUI(true);
            korgols.add(k);
            this.add(k);
            this.repaint();
        }
    }



    /**
     * Add n korgols of the specified color to the hole
     * @param n the numberof korgols to add to the hole
     */
    void addKorgols(int n) {
        for (int i = 0; i < n; ++i) {
            addKorgol();
        }
    }


    /**
     * Add a single korgol of the specified color to the hole
     * if the hole is full, we instead display a counter
     */
    public void addKorgol() {


        if (lastKorgolInd + 1 == getMaxKorgolsPossible()) { //if the next korgol would "overflow" the container
            this.remove(korgols.get(korgols.size() - 1)); //we display a counter instead.
            korgols.remove(korgols.size() - 1);

            extraKorgols = new JPanel(new BorderLayout()); //creating panel for counter
            extraKorgols.setPreferredSize(korgols.get(0).getPreferredSize());
            extraKorgols.setOpaque(false);
            updateCounter();
            extraKorgols.add(extraKorgolsCounter, BorderLayout.SOUTH);
            extraKorgols.setVisible(true);

            lastKorgolInd++;
            this.add(extraKorgols);
            this.revalidate();
            this.repaint();
            return;
        } else if (lastKorgolInd + 1 > getMaxKorgolsPossible()) { //if the counter already exists, we increment it
            if (extraKorgolsCounter != null) {
                ++lastKorgolInd;
                updateCounter();
                return;
            }
        }



        //if there are invisible korgols, we color the first available one black.
        for (KorgoolUI k: korgols) {
            if (k.isWhite()) {
                k.setWhite(false);
                k.render();
                ++lastKorgolInd;
                revalidate();
                return;
            }
        }


        KorgoolUI k = new KorgoolUI(false);
        korgols.add(k);
        ++lastKorgolInd;
        this.add(k);
        this.repaint();

    }

    /**
     * Remove n number of korgols
     * @param n the number of korgols to remove
     */

    public void removeKorgols(int n) {
        //if there is a counter, we update that one
        if (lastKorgolInd >= getMaxKorgolsPossible()) {
            lastKorgolInd--;
            updateCounter();
            return;
        }
        //if the counter reaches "0" we remove it from the hole entirely
        if (extraKorgols != null && lastKorgolInd + 1 == getMaxKorgolsPossible()) {
            lastKorgolInd--;
            this.remove(extraKorgols);
            this.revalidate();
            this.repaint();
        }


        int size = korgols.size();

        if (n <= size) {
            for (int i = size - 1; i > size - n - 1; --i) {
                if (lastKorgolInd >= 0) {
                    korgols.get(lastKorgolInd).dontRender();
                    --lastKorgolInd;
                } else System.out.println("Can't remove any more korgols!");
            }
        }
        this.repaint();
    }

    /**
     * Updates counter shown at the bottom of the hole
     * if there is one
     */
    private void updateCounter() {
        if (extraKorgolsCounter != null) { //if the counter exists we update it
            extraKorgolsCounter.setText("+" + (lastKorgolInd + 1 - getMaxKorgolsPossible()));
            if (("+" + (lastKorgolInd + 1 - getMaxKorgolsPossible())).equals("+0")) {
                this.remove(extraKorgols);
                this.revalidate();
                this.repaint();
            }
        } else { //else we have to create one
            extraKorgolsCounter = new JTextArea("+" + (lastKorgolInd + 2 - getMaxKorgolsPossible()));
            extraKorgolsCounter.setOpaque(false);
            extraKorgolsCounter.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        }
    }


    /**
     * Return the maximum number of korgols that can fit the container without it
     * looking odd
     * @return he maximum number of korgols that can fit the container without it
     *      * looking odd
     */
    public abstract int getMaxKorgolsPossible();



}