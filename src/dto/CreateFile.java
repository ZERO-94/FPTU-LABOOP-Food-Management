package dto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author kiman
 */
public class CreateFile {
    public static void writeBinaryFoods(String filename, List<Food> data) throws Exception {
        FileOutputStream f = new FileOutputStream(filename);
        ObjectOutputStream of = new ObjectOutputStream(f);
        of.writeObject(data);
        f.close(); of.close();
    }
        
     public static List<Food> readBinaryDogs(String filename) throws Exception{
      List<Food> list= null;
      FileInputStream f= new FileInputStream(filename);
      ObjectInputStream of= new ObjectInputStream(f);
      list= (List<Food>)of.readObject();
      f.close(); of.close();
      return list;
    }
}
