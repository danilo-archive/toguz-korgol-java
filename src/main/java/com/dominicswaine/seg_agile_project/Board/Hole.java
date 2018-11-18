package com.dominicswaine.seg_agile_project.Board;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Danilo Del Busso
 * @version 14-11-2018
 */
public class Hole extends KorgolsContainer {


    private int tuz;
    private int n;
    private Color currentColor;///tuz value (0 = not assigned to any player, 1 = assigned to 1st player, 2= assigned to 2nd player)

    /**
     * The hole is the main korgol container of the game
     * @param n the number of the hole
     */
    Hole(int n) {
        setOpaque(true);
        currentColor = Color.black;


        //todo : make this work with current project structure
        /* setIcon(new ImageIcon(
                    Objects.requireNonNull(
                            getClass().getClassLoader().
                                    getResource(
                                            "src/main/resources/Test.txt.png")
                    )
                )
        );*/

        setLayout(new GridLayout(8,5));

        this.tuz = 0;  //tuz is not assigned to any player

        //ADDING EVENT LISTENERS
        addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                System.out.println("you clicked on hole n:" + n);

            }
        });

    }



    /**
     * Assigns this hole as tuz to the designed player
     * @param player which player the tuz has to b eassigned to (0 = not assigned to any player, 1 = assigned to 1st player, 2= assigned to 2nd player)
      * @return true if the tuz has been assigned successfully
     */
    public boolean assignTuz(int player){
        if(player > -1 && player < 3){
            tuz = player;
            return true;
        }

        return false;
    }

    @Override
    public int getMaxKorgolsPossible() {
        return 21;
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(currentColor);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(10, 15, 150, 330 ,10,10);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(180,360);
    }

}
