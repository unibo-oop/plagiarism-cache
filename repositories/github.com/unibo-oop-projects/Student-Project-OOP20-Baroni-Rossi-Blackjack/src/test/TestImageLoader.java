/**
 * 
 */
package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utility.ImageLoader;

/**
 * 
 *
 */
public class TestImageLoader {
	ImageLoader image = new ImageLoader();
	@Test
	public void testImage() {
		this.image.findImages();
		assertEquals(this.image.getClubs().size(),14);
		assertEquals(this.image.getDiamonds().size(),14);
		assertEquals(this.image.getHeart().size(),14);
		assertEquals(this.image.getSpades().size(),14);
	}
}
