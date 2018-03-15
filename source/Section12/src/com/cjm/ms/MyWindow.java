package com.cjm.ms;

// The wildcard (*) imports all classes, interfaces and static objects from within a package.
// This doesn't include packages within packages though, so we must still import 'java.awt.event'.
import java.awt.*;
// Could replace both with 'java.awt.event.*', but this can lead to namespace pollution if there
// are too many common names within a package. Should use * sparingly.
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Used to display a window/GUI.
public class MyWindow extends Frame {
    public MyWindow(String title) {
        super(title);
        setSize(500, 140);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // Used to update the window with graphics.
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Font sansSerifLarge = new Font("SansSerif", Font.BOLD, 18);
        Font sansSerifSmall = new Font("SansSerif", Font.BOLD, 12);
        g.setFont(sansSerifLarge);
        g.drawString("The Complete Java Developer Course", 60, 60);
        g.setFont(sansSerifSmall);
        g.drawString("by Tim Buchalka", 60, 100);
    }
}
