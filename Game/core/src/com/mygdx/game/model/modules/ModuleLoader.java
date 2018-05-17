/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.model.modules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class ModuleLoader extends ClassLoader {
  public ModuleLoader( ClassLoader parent) {
    super(parent);    
    classRe = Pattern.compile("([^\\\\]+)\\.class$");
  }
  
  private Pattern classRe;

  @Override
  public Class<?> findClass(String absoluteName) throws ClassNotFoundException {
    Matcher m = classRe.matcher(absoluteName);  
    
    if (!m.find())
        return null;
      
    String classname = m.group(1);
      
    try {   
      
        
      byte b[] = fetchClassFromFS(absoluteName);
      return defineClass(classname, b, 0, b.length);
    } catch (FileNotFoundException ex) {
      return super.findClass(classname);
    } catch (IOException ex) {
      return super.findClass(classname);
    }
  }

  /**
   * Взято из www.java-tips.org/java-se-tips/java.io/reading-a-file-into-a-byte-array.html
   */
  private static byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
    InputStream is = new FileInputStream(new File(path));

    // Get the size of the file
    long length = new File(path).length();

    if (length > Integer.MAX_VALUE) {
      // File is too large
    }

    // Create the byte array to hold the data
    byte[] bytes = new byte[(int)length];

    // Read in the bytes
    int offset = 0;
    int numRead = 0;
    while (offset < bytes.length
        && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
      offset += numRead;
    }

    // Ensure all the bytes have been read in
    if (offset < bytes.length) {
      throw new IOException("Could not completely read file "+path);
    }

    // Close the input stream and return bytes
    is.close();
    return bytes;
  }
}
