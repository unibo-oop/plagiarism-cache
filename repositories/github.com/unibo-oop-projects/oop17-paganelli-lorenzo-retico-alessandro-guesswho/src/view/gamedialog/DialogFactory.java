package view.gamedialog;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import static java.awt.Dialog.ModalityType.*;

/**
 * Utility class that allows to show game dialogs.
 */
public final class DialogFactory {

    private DialogFactory() {
    }

    /**
     * Allows to show a message dialog (i.e. with the only "ok" button), by giving:
     * @param owner
     *              the owner of the dialog
     * @param message
     *              the message to show
     * @param modal
     *              a boolean to indicate if the dialog should be modal or not
     * @throws InterruptedException
     *              in case of interruption (for instance: opponent's quit)
     */
    public static void showMessageDialog(final Window owner, final String message, final boolean modal) throws InterruptedException {
        DialogFactory.showMessageDialog(owner, message, modal, e -> { });
    }

    /**
     * Allows to show a message dialog (i.e. with the only "ok" button), by giving:
     * @param owner
     *              the owner of the dialog
     * @param message
     *              the message to show
     * @param modal
     *              a boolean to indicate if the dialog should be modal or not
     * @param onClose
     *              an ActionListener to notify on close
     * @throws InterruptedException
     *              in case of interruption (for instance: opponent's quit)
     */
    public static void showMessageDialog(final Window owner, final String message, final boolean modal, final ActionListener onClose) throws InterruptedException {
        final GameDialog dialog = DialogFactory.createMessageDialog(owner, message, modal, onClose);
        dialog.show(owner);
        dialog.getAnswer().orElseThrow(InterruptedException::new);
    }

    /**
     * Allows to show a question dialog (i.e. with "yes" and "no" buttons), by giving:
     * @param owner
     *              the owner of the dialog
     * @param question
     *              the question to show
     * @throws InterruptedException
     *              in case of interruption (for instance: opponent's quit)
     * @return the answer
     */
    public static boolean showQuestionDialog(final Window owner, final String question) throws InterruptedException {
        final GameDialog dialog = DialogFactory.createDialog(owner, "Question", question, true);
        final JButton jYes = new JButton("yes");
        final JButton jNo = new JButton("no");
        jYes.addActionListener(e -> { 
            dialog.setAnswer(true); 
            dialog.dispose(); 
        });
        jNo.addActionListener(e -> { 
            dialog.setAnswer(false); 
            dialog.dispose(); 
        });
        dialog.addComponents(BorderLayout.SOUTH, jYes);
        dialog.addComponents(BorderLayout.SOUTH, jNo);
        dialog.show(owner);
        return dialog.getAnswer().orElseThrow(InterruptedException::new);
    }

    /**
     * Allows to show an error dialog (i.e. a message dialog set always on top), by giving:
     * @param owner 
     *              the owner of the dialog
     * @param message
     *              the message to show
     */
    public static void showErrorDialog(final Window owner, final String message) {
        final GameDialog dialog = DialogFactory.createMessageDialog(owner, message, true, e -> { });
        dialog.setAlwaysOnTop(true);
        dialog.show(owner);
    }

    /*
     * Allows to create a message Dialog.
     */
    private static GameDialog createMessageDialog(final Window owner, final  String msg, final boolean modal, final ActionListener onClose) {
        final GameDialog dialog = DialogFactory.createDialog(owner, "Message", msg, modal);
        final JButton jOk = new JButton("ok");
        jOk.addActionListener(e -> { 
            dialog.setAnswer(true); 
            dialog.dispose(); 
            onClose.actionPerformed(e);
        });
        dialog.addComponents(BorderLayout.SOUTH, jOk);
        return dialog;
    }

    /*
     * Allows to create a GameDialog.
     */
    private static GameDialog createDialog(final Window owner, final String title, final String msg, final boolean modal) {
        final GameDialog dialog = new GameDialog(owner, title, modal ? DOCUMENT_MODAL : MODELESS);
        dialog.addComponents(BorderLayout.CENTER, new JLabel(msg));
        return dialog;
    }

}
