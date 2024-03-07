package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Changes the remark of an existing person in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
                                + ": Adds/Edit remark to an existing person identified "
                                + "by the index number used in the listing. "
                                + "Existing remark will be overwritten by the input.\n"
                                + "Parameters: INDEX (must be a positive integer) "
                                + "r/ [REMARK]\n"
                                + "Example: " + COMMAND_WORD + " 4 r/ Xue Hua Piao Piao";

    private final Index index;
    private final String remark;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param remark of the person to be updated to
     */
    public RemarkCommand(Index index, String remark) {
        requireAllNonNull(index, remark);
        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, index.getOneBased(), remark));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // "other" is not a remark command
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index) && remark.equals(e.remark);
    }
}