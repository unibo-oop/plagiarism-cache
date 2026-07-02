package main.coordination.init;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.worldModel.utilities.GameSettings;

public class LoadNatives {
	
	/**
	 * Variable containing the name of the current OS
	 */
	private static String OS = System.getProperty("os.name").toLowerCase();

	/**
	 * Method used to check if current OS is Windows
	 * @return true if Windows, otherwise false
	 */
	public static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

	/**
	 * Method used to check if current OS is MacOS
	 * @return true if MacOS, otherwise false
	 */
	public static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}

	/**
	 * Method used to check if current OS is Linux
	 * @return true if Linux, otherwise false
	 */
	public static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );

	}

	/**
	 * Method used to check if application launched in jar file or Eclipse project
	 * @param checkJar, string of the resource in the module
	 * @return true if app launched in jar file, otherwise false
	 */
	public static boolean isJar(final String checkJar) {
		return (checkJar.indexOf("jar") >= 0);
	}
	
	public static Image loadTile(final String path) throws SlickException {	
		if(!LoadNatives.isJar(StateCoord.class.getResource("StateCoord.class").toString())) {
			try {
				return new Image(new URL("file:///" + path).openStream(), path, false);
			} catch (MalformedURLException e) {
				Logger.getLogger(LoadNatives.class.getName()).log(Level.SEVERE, null, e);
			} catch (SlickException e) {
				Logger.getLogger(LoadNatives.class.getName()).log(Level.SEVERE, null, e);
			} catch (IOException e) {
				Logger.getLogger(LoadNatives.class.getName()).log(Level.SEVERE, null, e);
			}
		} else {
			return new Image(path);
		}
		return null;
	}
	
	/**
	 * Method used to load all libraries and natives to make the project run
	 * @throws IOException
	 * @see IOException
	 */
	public void loadLibs() throws IOException {
		
		String destPath;
		
		if(isWindows()) {
			destPath = System.getProperty("java.io.tmpdir") + "jarg" + GameSettings.SEP;
		} else {
			destPath = System.getProperty("java.io.tmpdir") + File.separator + "jarg" + File.separator;
		}
		
		
		try {
			String currPath = System.getProperty("user.dir") + GameSettings.SEP +"jarg.jar";
			
	        System.out.println("     "+currPath);
	        System.out.println("     "+destPath);
	        
	        
	        JarFile jarFile = new JarFile(currPath);
	        Enumeration<JarEntry> enums = jarFile.entries();
	        while (enums.hasMoreElements()) {
	        	JarEntry entry = enums.nextElement();
	            if (entry.getName().startsWith("natives") || entry.getName().startsWith("libJars") || entry.getName().startsWith("res")) {
	            int nBytes;
	                File toWrite = new File(destPath + entry.getName());
	                if (entry.isDirectory()) {
	                    toWrite.mkdirs();
	                    continue;
	                }
	                InputStream in = new BufferedInputStream(jarFile.getInputStream(entry));
	                final OutputStream out = new BufferedOutputStream(new FileOutputStream(toWrite));
	                byte[] buffer = new byte[2048];
	                while(true) {
	                    nBytes = in.read(buffer);
	                    if (nBytes <= 0) {
	                        break;
	                    }
	                    out.write(buffer, 0, nBytes);
	                }
	                out.flush();
	                out.close();
	                in.close();
	            }
	        }
	        jarFile.close();
	    } catch (IOException ex) {
	    	System.out.println("Could not find file ");
	    }
		
		String libPath;
		
		if(!LoadNatives.isJar(StateCoord.class.getResource("StateCoord.class").toString())) {
			libPath = destPath + "libJars" + GameSettings.SEP;
		} else {	
			libPath = "." + GameSettings.SEP + "lib" + GameSettings.SEP + "libJars" + GameSettings.SEP;
		}
		
		if(isWindows()) {
			System.load(new File(libPath + "lwjgl64.dll").getAbsolutePath());
			System.load(new File(libPath + "OpenAL64.dll").getAbsolutePath());
			System.load(new File(libPath + "jinput-dx8_64.dll").getAbsolutePath());
			System.load(new File(libPath + "jinput-raw_64.dll").getAbsolutePath());
		}
		
		if(isUnix()) {
			System.load(new File(libPath + "liblwjgl64.so").getAbsolutePath());
			System.load(new File(libPath + "libjinput-linux64.so").getAbsolutePath());
			System.load(new File(libPath + "libopenal64.so").getAbsolutePath());
		}
		
		if(isMac()) {
			System.load(libPath + "libjinput-osx.dylib");
			System.load(libPath + "liblwjgl.dylib");
			System.load(libPath + "openal.dylib");
			System.load(libPath + "libjinput-osx.jnilib");
		}
	}
	
}
