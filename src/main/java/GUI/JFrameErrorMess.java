package GUI;

import javax.swing.*;

public class JFrameErrorMess {
    public JFrame frame = null;

    public JFrameErrorMess(){
        frame = new JFrame("JOptionPane showMessageDialog example");
    }

    public void messerror(String t){
        JOptionPane.showMessageDialog(frame,t,"Error Title", JOptionPane.ERROR_MESSAGE);
    }
}
