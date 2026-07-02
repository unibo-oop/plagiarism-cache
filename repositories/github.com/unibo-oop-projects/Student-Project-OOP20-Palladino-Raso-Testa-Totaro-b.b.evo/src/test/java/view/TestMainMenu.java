//package view;
//
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.testfx.api.FxAssert;
//import org.testfx.framework.junit5.ApplicationTest;
//import org.testfx.matcher.base.NodeMatchers;
//import org.testfx.matcher.control.LabeledMatchers;
//import org.testfx.matcher.control.TextInputControlMatchers;
//
//import launcher.BrickBreakerEvo;
//
//
///**
// * This class represent a navigable test of the menu view.
// */
//
//class TestMainMenu extends ApplicationTest {
//
//    private static final String ID_ANCHORPANE = "#window";
//    private static final String ID_BORDERPANE = "#panel";
//    private static final String ID_BUTTON_PLAY = "#btnPlay";
//    private static final String ID_BUTTON_SETTINGS = "#btnSettings";
//    private static final String ID_BUTTON_TUTORIAL = "#btnTutorial";
//    private static final String ID_BUTTON_RANKING = "#btnRanking";
//    private static final int SLEEP_TIME = 2000;
//
//    @BeforeEach
//    void launch() throws Throwable {
//        ApplicationTest.launch(BrickBreakerEvo.class, "");
//        sleep(SLEEP_TIME);
//    }
//
//    @Test
//    void testComponent() {
//        //Window
//        FxAssert.verifyThat(ID_ANCHORPANE, 
//                            NodeMatchers.isNotNull());
//        FxAssert.verifyThat(ID_ANCHORPANE,
//                            NodeMatchers.isVisible());
//        FxAssert.verifyThat(ID_ANCHORPANE,
//                            NodeMatchers.isEnabled());
//
//        //Panel
//        FxAssert.verifyThat(ID_BORDERPANE, 
//                            NodeMatchers.isNotNull());
//        FxAssert.verifyThat(ID_BORDERPANE,
//                            NodeMatchers.isVisible());
//        FxAssert.verifyThat(ID_BORDERPANE,
//                            NodeMatchers.isEnabled());
//        //Button Play
//        FxAssert.verifyThat(ID_BUTTON_PLAY, 
//                            NodeMatchers.isNotNull());
//        FxAssert.verifyThat(ID_BUTTON_PLAY,
//                            LabeledMatchers.hasText("Play"));
//        FxAssert.verifyThat(ID_BUTTON_PLAY,
//                            NodeMatchers.isVisible());
//        FxAssert.verifyThat(ID_BUTTON_PLAY,
//                            NodeMatchers.isEnabled());
//        //Button Settings
//        FxAssert.verifyThat(ID_BUTTON_SETTINGS, 
//                            NodeMatchers.isNotNull());
//        FxAssert.verifyThat(ID_BUTTON_SETTINGS,
//                            LabeledMatchers.hasText("Settings"));
//        FxAssert.verifyThat(ID_BUTTON_SETTINGS,
//                            NodeMatchers.isVisible());
//        FxAssert.verifyThat(ID_BUTTON_SETTINGS,
//                            NodeMatchers.isEnabled());
//
//        //Button Tutorial
//        FxAssert.verifyThat(ID_BUTTON_TUTORIAL, 
//                            NodeMatchers.isNotNull());
//        FxAssert.verifyThat(ID_BUTTON_TUTORIAL,
//                            LabeledMatchers.hasText("Tutorial"));
//        FxAssert.verifyThat(ID_BUTTON_TUTORIAL,
//                            NodeMatchers.isVisible());
//        FxAssert.verifyThat(ID_BUTTON_TUTORIAL,
//                            NodeMatchers.isEnabled());
//
//        //Button Ranking
//        FxAssert.verifyThat(ID_BUTTON_RANKING, 
//                            NodeMatchers.isNotNull());
//        FxAssert.verifyThat(ID_BUTTON_RANKING,
//                            LabeledMatchers.hasText("Ranking"));
//        FxAssert.verifyThat(ID_BUTTON_RANKING,
//                            NodeMatchers.isVisible());
//        FxAssert.verifyThat(ID_BUTTON_RANKING,
//                            NodeMatchers.isEnabled());
//    }
//
//    @Test
//    void testNavigableView() {
//        assertDoesNotThrow(() -> {
//            clickOn(ID_BUTTON_PLAY);
//            sleep(1000);
//            clickOn("#btnBack");
//            sleep(1000);
//            clickOn("#btnSettings");
//            sleep(1000);
//            clickOn("#btnBack");
//            sleep(1000);
//            clickOn("#btnTutorial");
//            sleep(1000);
//            clickOn("#buttonBack");
//            sleep(1000);
//            clickOn("#btnRanking");
//            sleep(1000);
//            clickOn("#buttonBack");
//        });
//    }
//
//    @Test
//    void testCharacterView() {
//        assertDoesNotThrow(() -> {
//            clickOn(ID_BUTTON_PLAY);
//            sleep(1000);
//            clickOn("#characterNameField");
//            write("GUEST");
//            sleep(1000);
//            FxAssert.verifyThat("#characterNameField", TextInputControlMatchers.hasText("GUEST"));
//        });
//    }
//
//    @Test
//    void testSettingsView() {
//        assertDoesNotThrow(() -> {
//            clickOn(ID_BUTTON_SETTINGS);
//            sleep(1000);
//            clickOn("#ckSoundFX");
//            sleep(1000);
//            clickOn("#ckSound");
//            sleep(1000);
//            clickOn("#rbUseLeftRight");
//            sleep(1000);
//            clickOn("#rbUseUpDown");
//        });
//    }
//
//    @Test
//    void testDifficultyView() {
//        assertDoesNotThrow(() -> {
//            clickOn(ID_BUTTON_PLAY);
//            sleep(1000);
//            clickOn("#btnNext");
//            sleep(1000);
//            clickOn("#ckNormalDifficulty");
//            sleep(1000);
//            clickOn("#ckHardDifficulty");
//        });
//    }
//}
