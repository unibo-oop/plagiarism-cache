package vg.model.mystery_box.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteFactoryEasyMysteryBoxTest {

    @Test
    void createFreezeTime() {
        ConcreteFactoryEasyMysteryBox concreteFactoryEasyMysteryBox = new ConcreteFactoryEasyMysteryBox();
        assertNotNull(concreteFactoryEasyMysteryBox.createFreezeTime());
    }

    @Test
    void createSpeedUp() {
        ConcreteFactoryEasyMysteryBox concreteFactoryEasyMysteryBox = new ConcreteFactoryEasyMysteryBox();
        assertNotNull(concreteFactoryEasyMysteryBox.createSpeedUp());
    }

    @Test
    void createScore() {
        ConcreteFactoryEasyMysteryBox concreteFactoryEasyMysteryBox = new ConcreteFactoryEasyMysteryBox();
        assertNotNull(concreteFactoryEasyMysteryBox.createScore());
    }

    @Test
    void createKillMosquitoes() {
        ConcreteFactoryEasyMysteryBox concreteFactoryEasyMysteryBox = new ConcreteFactoryEasyMysteryBox();
        assertNotNull(concreteFactoryEasyMysteryBox.createKillMosquitoes());
    }
}