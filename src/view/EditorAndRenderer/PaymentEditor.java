package view.EditorAndRenderer;

import view.Listener.PaymentListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentEditor extends ButtonEditor {
    private PaymentListener paymentListener;

    public PaymentEditor() {
        super();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentListener.pay(selectedRow);
                stopCellEditing();
            }
        });
    }

    public void setPaymentListener(PaymentListener paymentListener) {
        this.paymentListener = paymentListener;
    }
}
