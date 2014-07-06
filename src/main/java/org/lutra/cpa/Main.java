package org.lutra.cpa;

import com.google.gson.Gson;
import org.lutra.cpa.model.Outlet;
import org.lutra.cpa.service.OutletsService;

import java.util.List;

public class Main
{
    public static Gson g = new Gson();
    public static void main(String[] args)
    {
        List<Outlet> os = OutletsService.get();
        Ws.run();
    }
}
