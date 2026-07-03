package it.oop.project.view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * {@inheritDoc}
 */
public class AnimationFactoryImpl implements AnimationFactory {

    /**
     * {@inheritDoc}
     */
    public Thread createSpawnAnimation(final JLabel tile) {
        return new SpawnAnimation(tile);
    }

    /**
     * {@inheritDoc}
     */
    public Thread createFusionAnimation(final JLabel tile) {
        return new FusionAnimation(tile);
    }

    private static abstract class PopAnimation extends Thread {
        private static final float MULTIPLIER = 0.7f;
        private static final int MILLISECONDS = 100;
        private final JLabel tile;
        private Font initialFont;

        private PopAnimation(JLabel tile) {
            super();
            this.tile = tile;
            this.initialFont = this.tile.getFont().deriveFont(
                    ClassicTileFontSize.getSizeFor(this.tile.getText()));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            SwingUtilities.invokeLater(() -> {
                this.setPopFontSize();
            });
            try {
                Thread.sleep(MILLISECONDS);
            } catch (Exception ex) {
            }
            this.setNormalFontSize();
        }

        /**
         * Sets this tile font size when it pops.
         */
        protected abstract void setPopFontSize();

        private void setNormalFontSize() {
            this.tile.setFont(this.initialFont);
        };

        /**
         * Sets this tile font bigger.
         */
        protected void setBiggerFontSize() {
            this.tile.setFont(this.tile.getFont().deriveFont(
                    this.tile.getFont().getSize() * 1 / MULTIPLIER));
        }

        /**
         * Sets this tile font smaller.
         */
        protected void setSmallerFontSize() {
            this.tile.setFont(this.tile.getFont().deriveFont(
                    this.tile.getFont().getSize() * MULTIPLIER));
        }

    }

    private static final class SpawnAnimation extends PopAnimation {

        private SpawnAnimation(JLabel tile) {
            super(tile);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void setPopFontSize() {
            super.setSmallerFontSize();
        }

    }

    private static final class FusionAnimation extends PopAnimation {

        private FusionAnimation(JLabel tile) {
            super(tile);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void setPopFontSize() {
            super.setBiggerFontSize();
        }

    }
}
