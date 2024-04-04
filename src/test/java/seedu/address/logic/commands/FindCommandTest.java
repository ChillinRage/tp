package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.CourseContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.RoleContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        CourseContainsKeywordsPredicate firstCoursePredicate =
                new CourseContainsKeywordsPredicate(Collections.singletonList("first"));
        CourseContainsKeywordsPredicate secondCoursePredicate =
                new CourseContainsKeywordsPredicate(Collections.singletonList("second"));
        RoleContainsKeywordsPredicate firstRolePredicate =
                new RoleContainsKeywordsPredicate(Collections.singletonList("first"));
        RoleContainsKeywordsPredicate secondRolePredicate =
                new RoleContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstNamePredicate, firstCoursePredicate, firstRolePredicate);
        FindCommand findSecondCommand = new FindCommand(secondNamePredicate, secondCoursePredicate, firstRolePredicate);
        FindCommand findFirstSecondFirstCommand =
                new FindCommand(firstNamePredicate, secondCoursePredicate, firstRolePredicate);
        FindCommand findSecondFirstFirstCommand =
                new FindCommand(secondNamePredicate, firstCoursePredicate, firstRolePredicate);
        FindCommand findFirstFirstSecondCommand =
                new FindCommand(firstNamePredicate, firstCoursePredicate, secondRolePredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy =
                new FindCommand(firstNamePredicate, firstCoursePredicate, firstRolePredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // same name and course but different role -> returns false
        assertFalse(findFirstCommand.equals(findFirstFirstSecondCommand));

        // same course and role but different name -> returns false
        assertFalse(findFirstCommand.equals(findSecondFirstFirstCommand));

        // same name and role but different course -> returns false
        assertFalse(findFirstCommand.equals(findFirstSecondFirstCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        CourseContainsKeywordsPredicate coursePredicate = prepareCoursePredicate(" ");
        RoleContainsKeywordsPredicate rolePredicate = prepareRolePredicate(" ");
        FindCommand command = new FindCommand(namePredicate, coursePredicate, rolePredicate);
        expectedModel.updateFilteredPersonList(namePredicate.or(coursePredicate).or(rolePredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Kurz Elle Kunz");
        CourseContainsKeywordsPredicate coursePredicate = prepareCoursePredicate(" ");
        RoleContainsKeywordsPredicate rolePredicate = prepareRolePredicate(" ");
        FindCommand command = new FindCommand(namePredicate, coursePredicate, rolePredicate);
        expectedModel.updateFilteredPersonList(namePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleCourseKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        CourseContainsKeywordsPredicate coursePredicate = prepareCoursePredicate("IS2218 CS2030S");
        RoleContainsKeywordsPredicate rolePredicate = prepareRolePredicate(" ");
        FindCommand command = new FindCommand(namePredicate, coursePredicate, rolePredicate);
        expectedModel.updateFilteredPersonList(coursePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleRoleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        CourseContainsKeywordsPredicate coursePredicate = prepareCoursePredicate(" ");
        RoleContainsKeywordsPredicate rolePredicate = prepareRolePredicate("STUDENT PROFESSOR");
        FindCommand command = new FindCommand(namePredicate, coursePredicate, rolePredicate);
        expectedModel.updateFilteredPersonList(rolePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Elle");
        CourseContainsKeywordsPredicate coursePredicate = prepareCoursePredicate("IS2218");
        RoleContainsKeywordsPredicate rolePredicate = prepareRolePredicate("STUDENT");
        FindCommand command = new FindCommand(namePredicate, coursePredicate, rolePredicate);
        expectedModel.updateFilteredPersonList(namePredicate.or(coursePredicate).or(rolePredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, ELLE, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        CourseContainsKeywordsPredicate coursePredicate =
                new CourseContainsKeywordsPredicate(Arrays.asList("keyword"));
        RoleContainsKeywordsPredicate rolePredicate =
                new RoleContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(namePredicate, coursePredicate, rolePredicate);
        String expected = FindCommand.class.getCanonicalName() + "{namePredicate=" + namePredicate
                                                               + ", coursePredicate=" + coursePredicate
                                                               + ", rolePredicate=" + rolePredicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code CourseContainsKeywordsPredicate}.
     */
    private CourseContainsKeywordsPredicate prepareCoursePredicate(String userInput) {
        return new CourseContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code RoleContainsKeywordsPredicate}.
     */
    private RoleContainsKeywordsPredicate prepareRolePredicate(String userInput) {
        return new RoleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
