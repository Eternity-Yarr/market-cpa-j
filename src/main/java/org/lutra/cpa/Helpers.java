package org.lutra.cpa;

import org.lutra.cpa.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Helpers
{
    final private static Logger log = LoggerFactory.getLogger("HLP");
    public static int queryGetInt(HttpRequest req, String key, int def)
    {
        int ret = def;
        String val = null;

        if(req.method().equals("GET"))
            val = req.queryParam(key);
        else if(req.method().equals("POST"))
            val = req.postParam(key);

        if(val != null)
            try
            {
                ret = Integer.parseInt(val);
            }
            catch(NumberFormatException e)
            {
                log.debug(e.toString(), e);
            }

        return ret;
    }

    public static String queryGetString(HttpRequest req, String key, String def)
    {
        String ret;
        String val = null;

        if(req.method().equals("GET"))
            val = req.queryParam(key);
        else if(req.method().equals("POST"))
            val = req.postParam(key);
        ret = val != null ? val : def;

        return ret;
    }

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

    public static void error_500(HttpResponse tx, String message)
    {
        tx.status(500);
        tx.content(message); //TODO: beautify!
        tx.end();
    }

    public static void ok_200(HttpResponse tx, Response response)
    {
        tx.status(200);
        tx.header("Content-Type","application/json;charset=utf-8");
        tx.content(Main.g.toJson(response));
        tx.end();
    }

    public static PreparedStatement createStatement(Connection con, String query, Object... params) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement(query);

        int index = 0;
        for(Object o: params)
        {
            index++;
            if(o instanceof Integer)
                ps.setInt(index, (Integer)o);
            else if(o instanceof Double)
                ps.setDouble(index, (Double)o);
            else if(o instanceof String)
                ps.setString(index, (String)o);
            else if(o instanceof Date)
                ps.setDate(index, (Date)o);
            else throw new SQLException(String.format("Unsupported argument class %s", o.getClass()));
        }

        return ps;
    }
	public static boolean authorize(HttpRequest rx)
	{
		return rx.hasHeader("Authorization") && rx.header("Authorization").equals(Config.oauth_token);
	}
}
