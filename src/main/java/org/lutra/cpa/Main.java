package org.lutra.cpa;

import com.google.gson.Gson;

public class Main
{
    public static Gson g = new Gson();
    public static void main(String[] args)
    {
        Ws.run();
    }
}
