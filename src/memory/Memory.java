package memory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Memory {

    static Logger monLog = Logger.getLogger(Memory.class.getName());

    public  static void save (Object o, String fileName) throws IOException{
        ObjectOutputStream oos = null;
        try (FileOutputStream file = new FileOutputStream(fileName)){
            oos = new ObjectOutputStream(file);
            oos.writeObject(o);
            oos.flush();
        }
    }

    public  static Object read (String fileName) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = null;
        Object o = null;
        File f = new File(fileName);
        if(!(f.isFile()))
        {
            return null;
        }
        try (FileInputStream file = new FileInputStream(fileName)){
            ois = new ObjectInputStream(file);
            o = ois.readObject();
        }
        return o;
    }
}