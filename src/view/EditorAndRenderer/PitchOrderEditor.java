package view.EditorAndRenderer;

import view.Listener.PitchOrderListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PitchOrderEditor extends ButtonEditor {
    private PitchOrderListener pitchOrderListener;

    public PitchOrderEditor(){
        super();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                pitchOrderListener.order(selectedRow);
                stopCellEditing();
            }
        });
    }

    public void setPitchOrderListener(PitchOrderListener pitchOrderListener) {
        this.pitchOrderListener = pitchOrderListener;
    }
}
