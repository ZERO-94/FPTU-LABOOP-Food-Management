package utils;

import dto.Food;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author kiman
 */
public class FileUtils {

    public static void writeBinaryFoods(final String filename, List<Food> data) throws IOException {
        FileOutputStream f = null;
        ObjectOutputStream of = null;
        try {
            f = new FileOutputStream(filename);
            of = new ObjectOutputStream(f);
            of.writeObject(data);
        } finally {
            if (f != null) {
                f.close();
            }
            if (of != null) {
                of.close();
            }
        }
    }

    public static List<Food> readBinaryFoods(final String filename) throws IOException, ClassNotFoundException {
        FileInputStream f = null;
        ObjectInputStream of = null;
        List<Food> list = null;

        try {
            f = new FileInputStream(filename);
            of = new ObjectInputStream(f);
            list = (List<Food>) of.readObject();
            return list;
        } finally {
            if (f != null) {
                f.close();
            }
            if (of != null) {
                of.close();
            }
        }
    }
}
