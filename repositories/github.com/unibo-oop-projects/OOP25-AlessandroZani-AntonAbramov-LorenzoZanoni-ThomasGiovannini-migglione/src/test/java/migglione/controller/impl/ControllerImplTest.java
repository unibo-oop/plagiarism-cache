package migglione.controller.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import migglione.model.api.Game;
import migglione.persistence.api.ScoreRepository;
import migglione.persistence.api.TutorialRepository;
import migglione.view.api.SwingView;

/**
 * Test for the ControllerImpl.
 * 
 * <p>
 * The usage of mocks is necessary, so it has been
 * considered the usage of Mockito as an external library.
 * This way, copies of the entities that the Controller
 * uses can be made and be used to simulate the outcome.
 * 
 * <p>
 * To implement and use the Mockito library, new package
 * restricted methods have been added to ControllerImpl, and
 * to verify the methods of the library this link can be followed:
 * https://site.mockito.org/javadoc/current/org/mockito/Mockito.html#method_summary
 */
class ControllerImplTest {

    private static final String NAME_MOCK = "player";
    private static final int PLAYER_SCORE_MOCK = 20;
    private static final int CPU_SCORE_MOCK = 4;

    @Mock
    private SwingView view;
    @Mock
    private Game model;
    @Mock
    private ScoreRepository sRep;
    @Mock
    private TutorialRepository tRep;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckFirstSession() {
        assertFalse(tRep.haveTutorialBeenSeen());

        new ControllerImpl(view, sRep, tRep, model);

        verify(tRep).writeOnTutorial();
        verify(view).showTutorialPrompt();
    }

    @Test 
    void testEndSession() {
        when(model.getWinner()).thenReturn(Optional.of(NAME_MOCK));
        when(model.getPlayerScore()).thenReturn(Optional.of(PLAYER_SCORE_MOCK));
        when(model.getCPUScore()).thenReturn(Optional.of(CPU_SCORE_MOCK));

        final ControllerImpl controller = new ControllerImpl(view, sRep, tRep, model);
        controller.setPlayerMockName(NAME_MOCK);
        controller.endSession();

        verify(sRep).writeWinner(NAME_MOCK, PLAYER_SCORE_MOCK);
        verify(view).endMessage(NAME_MOCK, NAME_MOCK, PLAYER_SCORE_MOCK, CPU_SCORE_MOCK);
    }

    @Test
    void testEndSessionNotWorking() {
        when(model.getWinner()).thenReturn(Optional.empty());
        when(model.getPlayerScore()).thenReturn(Optional.empty());
        when(model.getCPUScore()).thenReturn(Optional.empty());

        final ControllerImpl controller = new ControllerImpl(view, sRep, tRep, model);
        controller.endSession();

        verifyNoInteractions(sRep);
        verify(view, never()).endMessage(anyString(), anyString(), anyInt(), anyInt());
    }
}
