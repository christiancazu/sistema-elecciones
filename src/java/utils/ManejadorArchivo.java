package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.Part;

public final class ManejadorArchivo {

    // ex: paths
    // in windows: C:\\...\\sistema-elecciones\\web\\recursos\\imagenes
    // in linux: /home/.../sistema-elecciones/web/recursos/imagenes
    private static final String PATH_IMAGES_FOLDER
            = "/home/ciber/dev/java/sistema-elecciones/web/recursos/imagenes";

    /**
     * save File in server
     *
     * @param filePart file
     * @param fullFileName to save
     * @return <code>boolean</code> if file is saved
     * @throws IOException
     */
    public static boolean save(Part filePart, String fullFileName) throws IOException {
        boolean isFileSaved = false;

        OutputStream out = null;
        InputStream filecontent = null;

        // trying to save the file in server using Stream
        try {
            out = new FileOutputStream(
                    new File(PATH_IMAGES_FOLDER + File.separator + fullFileName));

            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            isFileSaved = true;
        } catch (IOException e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            return isFileSaved;
        }
    }

    /**
     * delete File in server
     *
     * @param fileName to delete
     * @return <code>boolean</code> if file is deleted
     */
    public static boolean delete(String fileName) {
        File archivoParaBorrar = new File(PATH_IMAGES_FOLDER + "/" + fileName);

        return archivoParaBorrar.delete();
    }

    /**
     * Generate a fullFileName with extension
     *
     * @param filePart file
     * @return String ex: 12312312.jpg
     */
    public static String generateFullFileName(Part filePart) {
        // filePart.getSubmittedFileName() return original fileName with extension
        return generateFileName() + getFileExtension(filePart.getSubmittedFileName());
    }

    /**
     * Generate fileName based on system time
     *
     * @return String ex: 201982124545
     */
    private static String generateFileName() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        return dateFormat.format(date);
    }

    /**
     * Getting extension of file
     *
     * @param fileName file
     * @return String ex: .jpg
     */
    private static String getFileExtension(String fileName) {
        // spliting string in array parts
        String[] parts = fileName.split("\\.");

        // return the last index of array concated with a point
        return "." + parts[parts.length - 1];
    }

}
