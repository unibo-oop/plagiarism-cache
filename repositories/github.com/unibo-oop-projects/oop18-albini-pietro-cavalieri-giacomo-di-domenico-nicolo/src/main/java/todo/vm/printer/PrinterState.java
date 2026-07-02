package todo.vm.printer;

import todo.utils.UniqueId;

/**
 * Internal state of the printer, which is queried or changed by the printable
 * instructions once they're added to the {@link Printer}.
 *
 * It's not supposed to be created outside of the printer.
 */
public interface PrinterState {
    /**
     * Get the label associated with a jump.
     *
     * @param id the {@link UniqueId} of the jump target
     * @return the string representing the jump label
     */
    String getJumpLabel(UniqueId id);
}
