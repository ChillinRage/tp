package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandExecutionException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_SUCCESS = "Copied the email (%1$s) to your clipboard.";

    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": Copies the email of a person to your clipboard.\n";

    public static final String MESSAGE_USAGE = COMMAND_DESCRIPTION
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 4";

    private final Index targetIndex;

    /**
     * Creates a CopyCommand to copy the email of a person at the specified {@Index}.
     */
    public CopyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult execute(Model model) throws CommandExecutionException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandExecutionException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person targetPerson = lastShownList.get(targetIndex.getZeroBased());
        String targetEmailString = targetPerson.getEmail().toString();
        copyEmail(targetEmailString);
        return new CommandResult(String.format(MESSAGE_SUCCESS, targetEmailString));
    }

    /**
     * Copies the given {@code email} to system's clipboard.
     */
    private void copyEmail(String email) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(email);
        clipboard.setContent(url);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // Instanceof handles nulls.
        if (!(other instanceof CopyCommand)) {
            return false;
        }

        CopyCommand otherCopyCommand = (CopyCommand) other;
        return targetIndex.equals(otherCopyCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
