/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neyboychallanger.view.component;

import neyboychallanger.print.PrintScreenController;

/**
 *
 * @author Rodrigo
 */
public interface PrintPreview {

    void setPrintScreenController(PrintScreenController screenController);

    public void repaint();
}
