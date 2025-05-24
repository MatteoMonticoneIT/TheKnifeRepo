package theknife.obj;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Utility class that provides paths relative to the JAR file location.
 * <p>
 * This class is useful for accessing files and directories that are located relative to the JAR file at runtime. 
 * It is especially useful when the JAR is distributed and executed from both the jar file itself or from command prompt (CMD), 
 * ensuring consistent access to resources like data files or configuration files.
 * </p>
 *
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public final class AppPaths {

    /**
     * Returns the directory where the JAR file is located.
     * <p>
     * This method uses the location of this class to determine
     * the path of the executing JAR file. If the application is running from an IDE,
     * it will point to the {@code build/classes} or similar directory.
     * </p>
     *
     * @return a {@link File} representing the directory containing the JAR
     * @throws RuntimeException if the path cannot be determined
     */
    public static File getJarDir() {
        try {
            File jarPath = new File(AppPaths.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File dir = jarPath.getParentFile();
            return dir.getName().equals("build") ? new File(dir.getParentFile(), "dist") : dir;
        } catch (URISyntaxException e) {
            throw new RuntimeException("Unable to get the JAR directory: ", e);
        }
    }
    
    /**
     * Returns a path to a required {@code File} in a specific subdirectory.
     *
     * @param folder - subdirectory name
     * @param filename - file name
     * @return File instance pointing to the file
     * @throws RuntimeException if the file does not exist
     */
    public static File getRequiredFile(String folder, String filename) {
        File file = new File(getJarDir(), folder + File.separator + filename);
        if (!file.exists()) 
            throw new RuntimeException("Required file does not exist: " + file.getAbsolutePath());
        return file;
    }

    /**
     * Returns a path to a {@code File} that can be optionally created if missing.
     *
     * @param folder - subdirectory name
     * @param filename - file name
     * @return File instance pointing to the file (may not exist)
     */
    public static File getOptionalFile(String folder, String filename) {
        File dir = new File(getJarDir(), folder);
        if (!dir.exists()) 
            dir.mkdirs();
        return new File(dir, filename);
    }
}