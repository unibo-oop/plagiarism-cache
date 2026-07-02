package controllers.texture.imageLoader;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import model.Direction;
import other.Pair;

public class ImageLoader implements ImageLoaderInterface {

    private final GraphicsConfiguration gfxConfig = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice().getDefaultConfiguration();

    @Override
    public BufferedImage getImageByRowColumn(final BufferedImage image, final int row, final int column,
            final int width, final int height) {
        return this.getOptimizedImage(image.getSubimage(column * width, row * height, width, height));
    }

    @Override
    public LinkedList<BufferedImage> getImageByRow(final BufferedImage image, final int row, final int width,
            final int height) {
        final LinkedList<BufferedImage> listImage = new LinkedList<>();
        int column = 0;
        for (int i = 0; i < image.getWidth(); i = i + width) {
            listImage.add(this.getImageByRowColumn(image, row, column, width, height));
            column++;
        }
        return listImage;
    }

    @Override
    public LinkedList<BufferedImage> getImageByColumn(final BufferedImage image, final int column, final int width,
            final int height) {
        final LinkedList<BufferedImage> listImage = new LinkedList<>();
        int row = 0;
        for (int i = 0; i < image.getHeight(); i = i + height) {
            listImage.add(this.getImageByRowColumn(image, row, column, width, height));
            row++;
        }
        return listImage;
    }

    @Override
    public LinkedList<Pair<Direction, BufferedImage>> textureByDirectionList(final BufferedImage image,
            final Direction direction, final int row, final int width, final int height) {
        final LinkedList<Pair<Direction, BufferedImage>> finalList = new LinkedList<>();

        this.getImageByRow(image, row, width, height).stream()
                .forEach(p -> finalList.add(new Pair<Direction, BufferedImage>(direction, p)));

        return finalList;
    }

    @Override
    public BufferedImage rotateImage(final int angle, final BufferedImage image) {
        final double rads = Math.toRadians(angle);
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads, 0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image, rotatedImage);
        return rotatedImage;
    }

    @Override
    public BufferedImage getOptimizedImage(final BufferedImage image) {

        final BufferedImage newImage = gfxConfig.createCompatibleImage(image.getWidth(), image.getHeight(),
                image.getTransparency());

        final Graphics2D g2d = newImage.createGraphics();

        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return newImage;
    }
}
