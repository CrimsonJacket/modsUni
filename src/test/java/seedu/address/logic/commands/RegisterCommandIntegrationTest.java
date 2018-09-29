package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCredentials.CREDENTIAL_STUDENT_MAX;
import static seedu.address.testutil.TypicalCredentials.getTypicalCredentialStore;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.credential.Credential;
import seedu.address.model.user.User;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code
 * RegisterCommand}.
 */
public class RegisterCommandIntegrationTest {

    private static Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs(),
            getTypicalCredentialStore());
    }

    @Test
    public void execute_newCredential_success() {
        Credential validCredential = new Credential("u", "p", "k");
        User dummyUser = new StudentBuilder().build();

        Model expectedModel = new ModelManager(new AddressBook(),
            new UserPrefs(),
            getTypicalCredentialStore());
        expectedModel.addCredential(validCredential);

        assertCommandSuccess(new RegisterCommand(validCredential, dummyUser), model,
            commandHistory,
            String.format(RegisterCommand.MESSAGE_SUCCESS, validCredential), expectedModel);
    }

    @Test
    public void execute_duplicateCredential_throwsCommandException() {
        assertCommandFailure(new RegisterCommand(CREDENTIAL_STUDENT_MAX, model.getCurrentUser()),
            model,
            commandHistory,
            RegisterCommand.MESSAGE_DUPLICATE_USERNAME);
    }


}
