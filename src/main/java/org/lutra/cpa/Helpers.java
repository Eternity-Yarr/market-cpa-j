package org.lutra.cpa;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Helpers
{
    public static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));

        return new String(encoded, encoding);
    }

    public static String readFile(URL url, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(url.getPath()));

        return new String(encoded, encoding);
    }
}
