/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neyboychallanger.run;

import neyboychallanger.view.NeyChallangerJFrame;

/**
 *
 * @author Rodrigo
 */
public class Main {

    private static NeyChallangerJFrame ncjf;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ncjf = new NeyChallangerJFrame();
            ncjf.main(args);
        } catch (Exception e) {
            e.printStackTrace();
            ncjf.dispose();
            System.exit(-1);
        }
        //ncjf.setVisible(true);
    }

}
