package test;

import static org.junit.Assert.*;

import org.junit.Test;

import other.Theme;

public class testTheme {

  @Test
  public void testSetWhiteTheme() {
    Theme theme = new Theme();
    theme.setWhiteTheme();
    assertEquals("White", theme.getActuallyTheme());
  }

  @Test
  public void testSetBlackTheme() {
    Theme theme = new Theme();
    theme.setBlackTheme();
    assertEquals("Black", theme.getActuallyTheme());
  }

  @Test
  public void testSetStandardTheme() {
    Theme theme = new Theme();
    theme.setStandardTheme();
    assertEquals("Standard", theme.getActuallyTheme());
  }

  @Test
  public void testGetActuallyTheme() {
    Theme theme = new Theme();
    theme.setStandardTheme();
    assertEquals("Standard", theme.getActuallyTheme());
  }

  @Test
  public void testGetFirstColor() {
    Theme theme = new Theme();
    switch(theme.getActuallyTheme()) {
    case "Standard" :
      assertEquals("#f9ba32", theme.getFirstColor());
      break;
    case "White" :
      assertEquals("#D1D3D1", theme.getFirstColor());
      break;
    case "Black" :
      assertEquals("#C1BC93", theme.getFirstColor());
      break;
      default:
        break;
    }
  }

  @Test
  public void testGetSecondColor() {
    Theme theme = new Theme();
    switch(theme.getActuallyTheme()) {
    case "Standard" :
      assertEquals("#2f3131", theme.getSecondColor());
      break;
    case "White" :
      assertEquals("#778ECA", theme.getSecondColor());
      break;
    case "Black" :
      assertEquals("#362828", theme.getSecondColor());
      break;
      default:
        break;
    }
  }

  @Test
  public void testGetThirdColor() {
    Theme theme = new Theme();
    switch(theme.getActuallyTheme()) {
    case "Standard" :
      assertEquals("#f8f1e5", theme.getThirdColor());
      break;
    case "White" :
      assertEquals("#E4DFD8", theme.getThirdColor());
      break;
    case "Black" :
      assertEquals("#668067", theme.getThirdColor());
      break;
      default:
        break;
    }
  }

  @Test
  public void testThemeNotChanged() {
    Theme theme = new Theme();
    switch(theme.getActuallyTheme()) {
    case "Standard" :
      theme.setWhiteTheme();
      theme.themeNotChanged();
      assertFalse(theme.isThemeChanged());
      break;
    case "White" :
      theme.setBlackTheme();
      theme.themeNotChanged();
      assertFalse(theme.isThemeChanged());
      break;
    case "Black" :
      theme.setStandardTheme();
      theme.themeNotChanged();
      assertFalse(theme.isThemeChanged());
      break;
      default:
        break;
    }
  }

  @Test
  public void testIsThemeChanged() {
    Theme theme = new Theme();
    switch(theme.getActuallyTheme()) {
    case "Standard" :
      theme.setWhiteTheme();
      assertTrue(theme.isThemeChanged());
      break;
    case "White" :
      theme.setBlackTheme();
      assertTrue(theme.isThemeChanged());
      break;
    case "Black" :
      theme.setStandardTheme();
      assertTrue(theme.isThemeChanged());
      break;
      default:
        break;
    }
  }

}
