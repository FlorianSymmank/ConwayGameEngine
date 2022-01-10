package ConwayGameEngine.Util;

import ConwayGameEngine.FinalScore;

import java.io.*;

public class Serialize {
    public static byte[] finalScoreSerializer(FinalScore score) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(score);
            return baos.toByteArray();
        } catch (IOException ignored) {
        }

        return new byte[0];
    }

    public static FinalScore finalScoreDeserializer(byte[] bytes) throws ClassNotFoundException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes); ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (FinalScore) ois.readObject();
        } catch (IOException ignored) {

        }

        return null;
    }
}
